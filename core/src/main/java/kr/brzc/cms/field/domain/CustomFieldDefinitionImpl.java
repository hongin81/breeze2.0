package kr.brzc.cms.field.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.broadleafcommerce.cms.field.domain.FieldDefinitionImpl;
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
 * 	- 기존 FieldDefinitionImpl @AdminPresentationClass 에 fiendlyName이 없어 Admin 화면에 표시되지 않는 문제 
 * 	- 기존 FieldDefinitionImpl 의 필드의 @AdminPresentation Override  
 * 
 * 작성 내용
 * 	- Class 신규 작성 
 * 
 * 특기 사항
 *		- 엔티티 추가에 따른 XML 설정 필요 core/src/main/resources/META-INF/persistence-core.xml, core/src/main/resources/WEB-INF/applicationContext-entity.xml
 *
 */
@Entity
@DiscriminatorValue("CustomFieldDefinition")
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.FALSE, friendlyName = "FieldDefinitionImpl_baseFieldDefinition")

@AdminPresentationMergeOverrides({ 
	@AdminPresentationMergeOverride( 
			name="friendlyName", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_FriendlyName_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=100)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.REQUIREDOVERRIDE, booleanOverrideValue=true)
					} )
	, @AdminPresentationMergeOverride( 
			name="fieldType", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_FieldType_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					} )
	, @AdminPresentationMergeOverride( 
			name="securityLevel", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_SecurityLevel")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_SecurityLevel_Tooltip")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=2000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.Advanced)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.Advanced)
					} )
	, @AdminPresentationMergeOverride( 
			name="hiddenFlag", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_HiddenFlag")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_HiddenFlag_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=1000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.Advanced)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.Advanced)
					} )
	, @AdminPresentationMergeOverride( 
			name="validationRegEx", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_ValidationRegEx")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_ValidationRegEx_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=1000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Validation)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Validation)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.Advanced)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.Advanced)
					} )
	, @AdminPresentationMergeOverride( 
			name="validationErrorMesageKey", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_ValidationErrorMessageKey")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_ValidationErrorMessageKey_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=2000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Validation)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Validation)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.Advanced)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.Advanced)
					} )
	, @AdminPresentationMergeOverride( 
			name="validationErrorMesageKey", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_ValidationErrorMessageKey")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_ValidationErrorMessageKey_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=2000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Validation)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Validation)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.Advanced)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.Advanced)
					} )
	, @AdminPresentationMergeOverride( 
			name="maxLength", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_MaxLength")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_MaxLength_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=5000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					} )
	, @AdminPresentationMergeOverride( 
			name="columnWidth", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_ColumnWidth")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_ColumnWidth_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=5000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Additional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Additional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					} )
	, @AdminPresentationMergeOverride( 
			name="textAreaFlag", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_TextAreaFlag")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_TextAreaFlag_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=4000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.Advanced)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.Advanced)
					} )
	, @AdminPresentationMergeOverride( 
			name="requiredFlag", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_RequiredFlag")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_RequiredFlag_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=2000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.Advanced)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.Advanced)
					} )
	, @AdminPresentationMergeOverride( 
			name="dataDrivenEnumeration", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_DataDrivenEnumeration")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_DataDrivenEnumeration_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=10)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentationToOneLookup.LOOKUPDISPLAYPROPERTY, overrideValue="key")
					} )
	, @AdminPresentationMergeOverride( 
			name="allowMultiples", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_AllowMultiples")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_AllowMultiples_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=2000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Optional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.Advanced)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.Advanced)
					} )
	, @AdminPresentationMergeOverride( 
			name="fieldGroup", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_FieldGroup")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_FieldGroup_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=2000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					} )
	, @AdminPresentationMergeOverride( 
			name="fieldOrder", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_FieldOrder")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_FieldOrder_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=2000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					} )
	, @AdminPresentationMergeOverride( 
			name="tooltip", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_Tooltip")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_Tooltip_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=4000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Additional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Additional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					} )
	, @AdminPresentationMergeOverride( 
			name="helpText", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_HelpText")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_HelpText_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=5000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Additional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Additional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					} )
	, @AdminPresentationMergeOverride( 
			name="hint", 
			mergeEntries={
					@AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.FRIENDLYNAME, overrideValue="FieldDefinitionImpl_Hint")	
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TOOLTIP, overrideValue="FieldDefinitionImpl_Hint_Tooltip")
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.ORDER, intOverrideValue=6000)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUP, overrideValue=CustomFieldDefinitionImpl.Presentation.Group.Name.Additional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.GROUPORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Group.Order.Additional)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TAB, overrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Name.General)
					, @AdminPresentationMergeEntry(propertyType = PropertyType.AdminPresentation.TABORDER, intOverrideValue=CustomFieldDefinitionImpl.Presentation.Tab.Order.General)
					} )
	
})
public class CustomFieldDefinitionImpl extends FieldDefinitionImpl {

private static final long serialVersionUID = 1L;
    

	
	
	public static class Presentation {

        public static class Tab {

            public static class Name {

                public static final String General = "FieldDefinitionImpl_General_Tab";
                public static final String Advanced= "FieldDefinitionImpl_Advanced_Tab";
            }

            public static class Order {

                public static final int General = 1000;
                public static final int Advanced = 2000;
            }
        }

        public static class Group {

            public static class Name {

                public static final String General = "FieldDefinitionImpl_General_Group";
                public static final String Additional = "FieldDefinitionImpl_Additional_Group";
                public static final String Optional = "FieldDefinitionImpl_Optional_Group";
                public static final String Validation = "FieldDefinitionImpl_Validation_Group";
            }

            public static class Order {

            	public static final int General = 1000;
                public static final int Additional = 2000;
            	public static final int Optional = 3000;
                public static final int Validation = 4000;
                
            }
        }
    }
	
}
