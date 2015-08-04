package kr.brzc.cms.admin.web.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.cms.admin.web.controller.AdminPageController;
import org.broadleafcommerce.cms.page.domain.Page;
import org.broadleafcommerce.cms.page.domain.PageTemplate;
import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.openadmin.dto.ClassMetadata;
import org.broadleafcommerce.openadmin.server.domain.PersistencePackageRequest;
import org.broadleafcommerce.openadmin.web.form.entity.DynamicEntityFormInfo;
import org.broadleafcommerce.openadmin.web.form.entity.EntityForm;
import org.broadleafcommerce.openadmin.web.form.entity.Field;
import org.broadleafcommerce.openadmin.web.form.entity.FieldGroup;
import org.broadleafcommerce.openadmin.web.form.entity.Tab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * BLC 버그 수정
 * AdminPageController 에서 ajax로 넘겨 받은 정보로 dynamicForm을 생성할 때 기존 페이지의 pagesId를 받아 오지 못하는 부분 수정
 * 
 * 연관 파일
 * 1. kr.brzc.cms.admin.server.handler.CustomPageTemplateCustomPersistenceHandler
 * 2. kr.brzc.cms.admin.web.controller.CustomAdminPageController
 * 3. open_admin_style.js.admin.components.listGrid.js
 * 
 * 수정 내용
 * 1. listGrid.js 에서 ajax 호출 url에 pagesId 를 parameter로 전송
 * 2. CustomAdminPageController로 AdminPageController를 확장해 pagesId 값 넘겨 받기
 * 3. CustomPageTemplateCustomPersistenceHandler 의 버그 수정 -- 해당 내용은 CustomPageTemplateCustomPersistenceHandler 참조
 * @author kunner, 2015/08/01, kunner@kunner.com
 * 
 * Controller 확장 방법
 * 1. 확장하고 싶은 Controller를 extends 해 새로운 클래스 생성
 * 2. 클래스 맨 위에 @Controller 선언
 * 3. applicationContext-admin.xml 에 기존 Controller id 로 새 클래스 병합
 *
 */
@Controller
public class CustomAdminPageController extends AdminPageController{

	@Override
	@RequestMapping(value = "/{propertyName}/dynamicForm", method = RequestMethod.GET)
	public String getDynamicForm(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable  Map<String, String> pathVars,
            @PathVariable("propertyName") String propertyName,
            @RequestParam("propertyTypeId") String propertyTypeId) throws Exception {

        DynamicEntityFormInfo info = new DynamicEntityFormInfo()
                .withCeilingClassName(PageTemplate.class.getName())
                .withSecurityCeilingClassName(Page.class.getName())
                .withCriteriaName("constructForm")
                .withPropertyName(propertyName)
                .withPropertyValue(propertyTypeId);

        /*
         * Added by Kunner, 2015/08/01, kunner@kunner.com
         * Root Cause: BLC 버그
         * Description
         *     Page > PageTemplate 에 한해 pagesId를 customCriteria 로 등록한다. 
         */

    	if (propertyName.equals("pageTemplate")){
    		String[] customCriteriaForPageTemplate = {request.getParameter("pagesId")};
    		info.setCustomCriteriaOverride(customCriteriaForPageTemplate);
    	}
    	// 추가된 내용 끝

        return super.getDynamicForm(request, response, model, pathVars, info);
    }
	
	@Override
	protected EntityForm getBlankDynamicFieldTemplateForm(DynamicEntityFormInfo info, EntityForm dynamicFormOverride) 
            throws ServiceException {
        // We need to inspect with the second custom criteria set to the id of
        // the desired structured content type
    	/*  Original Source code
        PersistencePackageRequest ppr = PersistencePackageRequest.standard()
                .withCeilingEntityClassname(info.getCeilingClassName())
                .withSecurityCeilingEntityClassname(info.getSecurityCeilingClassName())
                .withCustomCriteria(new String[] { info.getCriteriaName(), null, info.getPropertyName(), info.getPropertyValue() });
        */
        PersistencePackageRequest ppr = PersistencePackageRequest.standard()
                .withCeilingEntityClassname(info.getCeilingClassName())
                .withSecurityCeilingEntityClassname(info.getSecurityCeilingClassName());
        
        /*
         * Added by Kunner, 2015/08/01, kunner@kunner.com
         * Root Cause: BLC 버그
         * Description
         *     Page > PageTemplate 에 한해 pagesId를 customCriteria 로 등록한다. 
         *     AdminPageControll 에서 넘긴 customCriteriaForPageTemplate 을 customCriteria 에 적용하는 구문
         */
        if (info.getPropertyName().equals("pageTemplate")&& info.getCustomCriteriaOverride()!=null){
        	ppr.setCustomCriteria(new String[] { info.getCriteriaName(), info.getCustomCriteriaOverride()[0], info.getPropertyName(), info.getPropertyValue() });
        }else{
        	ppr.setCustomCriteria(new String[] { info.getCriteriaName(), null, info.getPropertyName(), info.getPropertyValue() });
        }
        // 추가한 내용 끝

        
        
        ClassMetadata cmd = service.getClassMetadata(ppr).getDynamicResultSet().getClassMetaData();
        
        EntityForm dynamicForm = formService.createEntityForm(cmd, null);
        dynamicForm.clearFieldsMap();

        if (dynamicFormOverride != null) {
            dynamicFormOverride.clearFieldsMap();
            Map<String, Field> fieldOverrides = dynamicFormOverride.getFields();
            for (Entry<String, Field> override : fieldOverrides.entrySet()) {
                if (dynamicForm.getFields().containsKey(override.getKey())) {
                    dynamicForm.getFields().get(override.getKey()).setValue(override.getValue().getValue());
                }
            }
        }
        
        // Set the specialized name for these fields - we need to handle them separately
        dynamicForm.clearFieldsMap();
        for (Tab tab : dynamicForm.getTabs()) {
            for (FieldGroup group : tab.getFieldGroups()) {
                for (Field field : group.getFields()) {
                    field.setName(info.getPropertyName() + DynamicEntityFormInfo.FIELD_SEPARATOR + field.getName());
                }
            }
        }

        //extensionManager.getProxy().modifyDynamicForm(dynamicForm, );

        return dynamicForm;
    }
	
	
	
}
