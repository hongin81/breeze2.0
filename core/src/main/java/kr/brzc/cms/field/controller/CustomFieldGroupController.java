package kr.brzc.cms.field.controller;

import java.util.Map;

import org.broadleafcommerce.openadmin.web.controller.entity.AdminBasicEntityController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.brzc.cms.field.domain.CustomFieldGroupImpl;

/**
 * @author kunner, 2015/08/01, kunner@kunner.com
 * 엔티티 확장에 따른 Type Selection 용 컨트롤러 추가 
 * 기존 엔티티를 확장 후 Type Selection 처리 
 * 
 * 연관 파일
 * 1. kr.brzc.cms.field.domain.CustomFieldGroupImpl
 * 
 * 수정 내용
 * 1. 신규 작성
 * 
 * 콘트롤러 등록 
 * 1. /admin/src/main/webapp/WEB-INF/applicationContext-servlet-admin.xml 에 component-scan 등록
 *
 */
@Controller
@RequestMapping("/"+CustomFieldGroupController.SECTION_KEY)
public class CustomFieldGroupController extends AdminBasicEntityController{
	
	protected static final String SECTION_KEY = "page-fields-group";
	
	@Override
	protected String getSectionKey(Map<String, String> pathVars) {
		if (super.getSectionKey(pathVars) != null){
			return super.getSectionKey(pathVars); 
		}
		return SECTION_KEY;
	}
	
	protected String getDefaultEntityType(){
		return CustomFieldGroupImpl.class.getName();
	}
}
