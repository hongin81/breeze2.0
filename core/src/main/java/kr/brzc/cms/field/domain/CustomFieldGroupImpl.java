package kr.brzc.cms.field.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.broadleafcommerce.cms.field.domain.FieldGroupImpl;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.PopulateToOneFieldsEnum;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeEntry;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeOverride;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeOverrides;
import org.broadleafcommerce.common.presentation.override.PropertyType;

/**
 * @author kunner
 * Created by Kunner, 2015/08/03, kunner@kunner.com
 * 
 * 작성 사유
 * 	- 기존 FieldGroupImpl @AdminPresentationClass 에 fiendlyName이 없어 Admin 화면에 표시되지 않는 문제 
 * 	- 기존 FieldGroupImpl 의 필드의 @AdminPresentation Override  
 * 
 * 작성 내용
 * 	- Class 신규 작성 
 * 
 * 특기 사항
 *		- 엔티티 추가에 따른 XML 설정 필요 core/src/main/resources/META-INF/persistence-core.xml, core/src/main/resources/WEB-INF/applicationContext-entity.xml
 *
 */

@Entity
@DiscriminatorValue("CustomFieldGroup")

@AdminPresentationMergeOverrides({ 
	@AdminPresentationMergeOverride( 
			name="name", 
			mergeEntries={
						@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=100)
						, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.PROMINENT, booleanOverrideValue=true)
						, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="CUSTOM_FIELD_GROUP_NAME")
						, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GRIDORDER, intOverrideValue=100)
						} )
	, @AdminPresentationMergeOverride( 
			name="initCollapsedFlag", 
			mergeEntries={
						@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.EXCLUDED, booleanOverrideValue=true)
						} )
	, @AdminPresentationMergeOverride( 
			name="fieldDefinitions", 
			mergeEntries={
						@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationMap.FRIENDLYNAME, overrideValue="CUSTOM_FIELD_GROUP_FIELDDEFINITIONS")
						, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationMap.DELETEENTITYUPONREMOVE, booleanOverrideValue=true)
						, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationMap.FORCEFREEFORMKEYS, booleanOverrideValue=true)
						} )
	
})
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.TRUE, friendlyName = "PageTemplateImpl_FieldGroupImpl")
public class CustomFieldGroupImpl extends FieldGroupImpl{

	private static final long serialVersionUID = 1L;
    


}
