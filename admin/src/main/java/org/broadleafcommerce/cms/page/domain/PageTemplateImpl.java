/*
 * #%L
 * BroadleafCommerce CMS Module
 * %%
 * Copyright (C) 2009 - 2013 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.broadleafcommerce.cms.page.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.cms.field.domain.FieldDefinition;
import org.broadleafcommerce.cms.field.domain.FieldDefinitionImpl;
import org.broadleafcommerce.cms.field.domain.FieldGroup;
import org.broadleafcommerce.cms.field.domain.FieldGroupImpl;
import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.copy.CreateResponse;
import org.broadleafcommerce.common.copy.MultiTenantCopyContext;
import org.broadleafcommerce.common.extensibility.jpa.clone.ClonePolicyCollectionOverride;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransform;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransformMember;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransformTypes;
import org.broadleafcommerce.common.extensibility.jpa.copy.ProfileEntity;
import org.broadleafcommerce.common.locale.domain.Locale;
import org.broadleafcommerce.common.locale.domain.LocaleImpl;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationAdornedTargetCollection;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationMap;
import org.broadleafcommerce.common.presentation.PopulateToOneFieldsEnum;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by bpolster.
 */
/**
 * 
 * @author kunner
 *
 *Modified by Kunner, 2015/08/03, kunner@kunner.com
 * PageTemplateImpl 확장을 위한 수정
 * 
 * 수정 내용
 *     - templatePath에 @AdminPresentation annotation 추가
 *     - fieldGroup 에 @AdminPresentationAdornedTargetCollection() 추가
 * 
 * 수정 사유
 *     - templatePath에 @AdminPresentation이 없어 MergeOverride 불가 - annotation 추가 
 *     - fieldGroup에 @AdminPresentation이 없어 MergeOverride 불가 - annotation 추가
 *     - 
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_PAGE_TMPLT")
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blCMSElements")
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.TRUE, friendlyName = "PageTemplateImpl_basePageTemplate")
@DirectCopyTransform({
        @DirectCopyTransformMember(templateTokens = DirectCopyTransformTypes.SANDBOX, skipOverlaps = true),
        @DirectCopyTransformMember(templateTokens = DirectCopyTransformTypes.MULTITENANT_SITE)
})
public class PageTemplateImpl implements PageTemplate, AdminMainEntity, ProfileEntity {

    private static final Log LOG = LogFactory.getLog(PageTemplateImpl.class);
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "PageTemplateId")
    @GenericGenerator(
        name="PageTemplateId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="PageTemplateImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.cms.page.domain.PageTemplateImpl")
        }
    )
    @Column(name = "PAGE_TMPLT_ID")
    @AdminPresentation(friendlyName = "PageTemplateImpl_Template_Id", 
        visibility = VisibilityEnum.HIDDEN_ALL, 
        readOnly = true)
    protected Long id;

    @Column (name = "TMPLT_NAME")
    @AdminPresentation(friendlyName = "PageTemplateImpl_Template_Name", 
        prominent = true, gridOrder = 1)
    protected String templateName;

    @Column (name = "TMPLT_DESCR")
    @AdminPresentation(friendlyName = "PageTemplateImpl_Template_description")		// Customizing - MergeOverrides 를 위해 추가한 부분, @Kunner, 2015/07/27
    protected String templateDescription;

    @Column (name = "TMPLT_PATH")
    @AdminPresentation(friendlyName = "PageTemplateImpl_Template_Path", 
    visibility = VisibilityEnum.HIDDEN_ALL, 
    readOnly = true)
    protected String templatePath;

    @ManyToOne(targetEntity = LocaleImpl.class)
    @JoinColumn(name = "LOCALE_CODE")
    @AdminPresentation(excluded = true)
    @Deprecated
    protected Locale locale;    
    
    // 기존 내용 수정: @AdminPresentationAdornedTargetCollection() 추가
    @OneToMany(targetEntity = PageTemplateFieldGroupXrefImpl.class, cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = "pageTemplate")
    @OrderBy("groupOrder")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blCMSElements")
    @BatchSize(size = 20)
    @ClonePolicyCollectionOverride
    @AdminPresentationAdornedTargetCollection(
    		friendlyName="CUSTOM_PAGE_TEMPLATE_FIELDGROUP"
    		, targetObjectProperty="fieldGroup"
    		, parentObjectProperty="pageTemplate"
    		, sortProperty = "groupOrder"
    		, gridVisibleFields={"name","initCollapsedFlag"}
    		)		// Customizing - MergeOverrides 를 위해 추가한 부분, @Kunner, 2015/07/27
    protected List<PageTemplateFieldGroupXref> fieldGroups = new ArrayList<PageTemplateFieldGroupXref>();

    @Transient
    protected List<FieldGroup> legacyFieldGroups = new ArrayList<FieldGroup>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTemplateName() {
        return templateName;
    }

    @Override
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String getTemplateDescription() {
        return templateDescription;
    }

    @Override
    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    @Override
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    @Deprecated
    public List<FieldGroup> getFieldGroups() {
        if (legacyFieldGroups.isEmpty()) {
            for (PageTemplateFieldGroupXref xref : getFieldGroupXrefs()) {
                legacyFieldGroups.add(xref.getFieldGroup());
            }
        }
        return Collections.unmodifiableList(legacyFieldGroups);
    }

    @Override
    @Deprecated
    public void setFieldGroups(List<FieldGroup> fieldGroups) {
        this.legacyFieldGroups.clear();
        this.fieldGroups.clear();
        for (FieldGroup group : fieldGroups) {
            this.fieldGroups.add(new PageTemplateFieldGroupXrefImpl(this, group));
        }
    }

    @Override
    public List<PageTemplateFieldGroupXref> getFieldGroupXrefs() {
        return fieldGroups;
    }

    @Override
    public void setFieldGroupXrefs(List<PageTemplateFieldGroupXref> fieldGroups) {
        this.fieldGroups = fieldGroups;
    }

    @Override
    public String getMainEntityName() {
        return getTemplateName();
    }

    @Override
    public <G extends PageTemplate> CreateResponse<G> createOrRetrieveCopyInstance(MultiTenantCopyContext context) throws CloneNotSupportedException {
        CreateResponse<G> createResponse = context.createOrRetrieveCopyInstance(this);
        if (createResponse.isAlreadyPopulated()) {
            return createResponse;
        }
        PageTemplate cloned = createResponse.getClone();
        cloned.setTemplateName(templateName);
        cloned.setTemplateDescription(templateDescription);
        cloned.setTemplatePath(templatePath);
        cloned.setLocale(locale);

        for (PageTemplateFieldGroupXref fieldGroup : fieldGroups) {
            CreateResponse<PageTemplateFieldGroupXref> clonedGroupResponse = fieldGroup.createOrRetrieveCopyInstance(context);
            PageTemplateFieldGroupXref clonedGroup = clonedGroupResponse.getClone();
            cloned.getFieldGroupXrefs().add(clonedGroup);
        }
        
        return createResponse;
    }

}

