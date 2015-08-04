package kr.brzc.cms.admin.server.handler;

import java.util.ArrayList;
import java.util.List;

import org.broadleafcommerce.cms.admin.server.handler.PageTemplateCustomPersistenceHandler;
import org.broadleafcommerce.cms.field.domain.FieldGroup;
import org.broadleafcommerce.cms.page.domain.Page;
import org.broadleafcommerce.cms.page.domain.PageTemplate;
import org.broadleafcommerce.cms.page.domain.PageTemplateFieldGroupXref;

/**
 * BLC 버그 수정
 * AdminPageController 에서 ajax로 넘겨 받은 정보로 dynamicForm을 생성할 때 템플릿 ID를 DB에서 가져온 정보를 우선하는 문제
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
 * PersistenceHandler 확장 방법
 * 1. 확장하고 싶은 Controller를 extends 해 새로운 클래스 생성
 * 2. applicationContext-servlet-admin.xml 에서 기존 PersistenceHandler bean에 새로 만든 클래스 병합
 * <bean id="blPageTemplateCustomPersistenceHandler" class="kr.brzc.cms.admin.server.handler.CustomPageTemplateCustomPersistenceHandler"/>
 *
 */
public class CustomPageTemplateCustomPersistenceHandler extends PageTemplateCustomPersistenceHandler{

	@Override
	protected List<FieldGroup> getFieldGroups(Page page, PageTemplate template) {
        List<PageTemplateFieldGroupXref> fieldGroupXrefs = null;

        List<FieldGroup> fieldGroups = new ArrayList<FieldGroup>();
        
        /*
         * Modified by Kunner, 2015/08/01, kunner@kunner.com
         * Root Cause: BLC 버그
         * Description
         *     Lookup 으로 PageTemplate을 선택한 경우, 선택한 템플릿 값이 적용되도록 if 절의 순서를 바꿈
         */
        /**
         * 에러 수정 - 원본의 아래 두 if 절이 자리가 바뀌어 있음        
         */
                if (page.getPageTemplate() != null) {
                    fieldGroupXrefs = page.getPageTemplate().getFieldGroupXrefs();
                }

                
                if (template != null) {
                    fieldGroupXrefs = template.getFieldGroupXrefs();
                }
        /** ************************************************************ */
        if (fieldGroupXrefs != null) {
            for (PageTemplateFieldGroupXref xref : fieldGroupXrefs) {
                fieldGroups.add(xref.getFieldGroup());
            }
        }
       
        return fieldGroups;
    }
	
}
