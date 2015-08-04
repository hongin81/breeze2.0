package kr.brzc.common.i18n.service;

import org.broadleafcommerce.common.i18n.domain.TranslatedEntity;
import org.broadleafcommerce.common.i18n.service.TranslationServiceImpl;

/**
 * TranslationService 를 이용하고 싶은 대상이 있다면 아래와 같이 TranslatedEntity 로 추가하면 된다.
 * 새로 생성한 클래스는 applicationContext-admin.xml 에 기존 blTranslationService bean과 병합한다.
 * <bean id="blTranslationService" class="kr.brzc.common.i18n.service.CustomTranslationServiceImpl"/>
 * 
 * @author kunner
 *
 */
public class CustomTranslationServiceImpl extends TranslationServiceImpl{

	// 대상 entity의 field 단위까지 기술한다.
	// public static final TranslatedEntity [필드명] = new TranslatedEntity("대상도메인엔티티", "대상필드오브젝트");
	public static final TranslatedEntity SEARCH_FIELD =
			new TranslatedEntity("org.broadleafcommerce.core.search.domain.Field", "searchField");
	
}
