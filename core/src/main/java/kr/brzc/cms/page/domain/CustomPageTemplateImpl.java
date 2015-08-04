package kr.brzc.cms.page.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.broadleafcommerce.cms.page.domain.PageTemplateImpl;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.PopulateToOneFieldsEnum;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeEntry;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeOverride;
import org.broadleafcommerce.common.presentation.override.AdminPresentationMergeOverrides;
import org.broadleafcommerce.common.presentation.override.PropertyType;

/**
 * @author kunner
 * Created by Kunner, 2015/08/03, kunner@kunner.com
 * 
 * 작성 사유
 * 	- 기존 PageTemplateImpl 의 @AdminPresentation Override 
 * 
 * 작성 내용
 * 	- Class 신규 작성 
 * 
 * 특기 사항
 *		- AdminPresentationMergeOverrides를 위해 기존 PageTemplateImpl 을 일부 수정하였음 - 해당 내용은 수정된 PageTemplateImpl 파일 참조 
 *		- 엔티티 추가에 따른 XML 설정 필요 core/src/main/resources/META-INF/persistence-core.xml, core/src/main/resources/WEB-INF/applicationContext-entity.xml
 *
 */

@Entity
@DiscriminatorValue("CustomPageTemplate")
@AdminPresentationMergeOverrides({ 
		@AdminPresentationMergeOverride( 
				name="templateName", 
				mergeEntries={
							@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=100)
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue="CUSTOM_PAGE_TEMPLATE_GROUP_GENERAL")
							} )
		, @AdminPresentationMergeOverride( 
				name="templateDescription", 
				mergeEntries={
							@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="CUSTOM_PAGE_TEMPLATE_DESCR")
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=200)
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue="CUSTOM_PAGE_TEMPLATE_GROUP_GENERAL")
							} )
		, @AdminPresentationMergeOverride( 
				name="templatePath", 
				mergeEntries={
							@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=150)
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.VISIBILITY, overrideValue="VISIBLE_ALL")
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.READONLY, booleanOverrideValue=false)
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue="CUSTOM_PAGE_TEMPLATE_GROUP_GENERAL")
							} )
		, @AdminPresentationMergeOverride( 
				name="fieldGroups", 
				mergeEntries={
							@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationAdornedTargetCollection.FRIENDLYNAME, overrideValue="CUSTOM_PAGE_TEMPLATE_FIELDGROUP")
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationAdornedTargetCollection.TARGETOBJECTPROPERTY, overrideValue="fieldGroup")
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationAdornedTargetCollection.PARENTOBJECTPROPERTY, overrideValue="pageTemplate")
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationAdornedTargetCollection.SORTPROPERTY, overrideValue="groupOrder")
							, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationAdornedTargetCollection.GRIDVISIBLEFIELDS, stringArrayOverrideValue={"name","initCollapsedFlag"})
							} )
		
})
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.TRUE, friendlyName = "PageTemplateImpl_basePageTemplate")
public class CustomPageTemplateImpl extends PageTemplateImpl {

	private static final long serialVersionUID = 1L;
	

}
