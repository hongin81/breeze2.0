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
package org.broadleafcommerce.cms.field.domain;

import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.copy.CreateResponse;
import org.broadleafcommerce.common.copy.MultiTenantCopyContext;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransform;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransformMember;
import org.broadleafcommerce.common.extensibility.jpa.copy.DirectCopyTransformTypes;
import org.broadleafcommerce.common.extensibility.jpa.copy.ProfileEntity;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationMap;
import org.broadleafcommerce.common.presentation.PopulateToOneFieldsEnum;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import kr.brzc.cms.field.domain.CustomFieldDefinitionImpl;

import java.util.ArrayList;
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
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by bpolster.
 */
/**
 * 
 * @author kunner
 * Modified by Kunner, 2015/08/01, kunner@kunner.com
 * 
 * 수정 사유
 * 	- 기존 name, initCollapsedFlag, fieldGroup에 @AdminPresentation이 없어 MergeOverride 불가
 * 
 * 수정 내용
 * 	- name, initCollapsedFlag, fieldGroup에 MergeOverride 할 수 있도록 기본적인 @AdminPresentation 추가
 * 
 * 특기 사항
 * 	- 기존 BLC 소스를 수정한 것이므로, admin/site 프로젝트에 각각 소스 복사 필요
 * 	- BLC 버전업 시 변경 사항 체크 필요 
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_FLD_GROUP")
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.TRUE, friendlyName = "PageTemplateImpl_FieldGroupImpl")
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blCMSElements")
@DirectCopyTransform({
        @DirectCopyTransformMember(templateTokens = DirectCopyTransformTypes.SANDBOX, skipOverlaps = true),
        @DirectCopyTransformMember(templateTokens = DirectCopyTransformTypes.MULTITENANT_SITE)
})
public class FieldGroupImpl implements FieldGroup, AdminMainEntity, ProfileEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "FieldGroupId")
    @GenericGenerator(
        name="FieldGroupId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="FieldGroupImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.cms.field.domain.FieldGroupImpl")
        }
    )
    @Column(name = "FLD_GROUP_ID")
    protected Long id;

    @Column (name = "NAME")
    @AdminPresentation(friendlyName = "Field Group name", prominent=true)			// @AdminPresentation 추가 
    protected String name;

    @Column (name = "INIT_COLLAPSED_FLAG")
    @AdminPresentation(friendlyName = "Field Group flag")		// @AdminPresentation 추가
    protected Boolean initCollapsedFlag = false;

    @OneToMany(mappedBy = "fieldGroup", targetEntity = FieldDefinitionImpl.class, cascade = { CascadeType.ALL }, orphanRemoval = true)
    @MapKey(name="name")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blCMSElements")
    @BatchSize(size = 20)
    @AdminPresentationMap(friendlyName = "Fields", forceFreeFormKeys = true
        	, deleteEntityUponRemove = true)	// @AdminPresentationMap 추가
    protected Map<String, FieldDefinition> fieldDefinitions = new HashMap<String, FieldDefinition>();		// 페이지에서 직접 하위 항목을 편집할 수 있도록 fieldDefinitions를 List 에서 Map 으로 변경

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Boolean getInitCollapsedFlag() {
        return initCollapsedFlag;
    }

    @Override
    public void setInitCollapsedFlag(Boolean initCollapsedFlag) {
        this.initCollapsedFlag = initCollapsedFlag;
    }

    @Override
    public List<FieldDefinition> getFieldDefinitions() {
        return new ArrayList(fieldDefinitions.values());
    	//return fieldDefinitions;
    }

    @Override
    public void setFieldDefinitions(Map<String, FieldDefinition> fieldDefinitions) {
        this.fieldDefinitions = fieldDefinitions;
    }

    @Override
    public <G extends FieldGroup> CreateResponse<G> createOrRetrieveCopyInstance(MultiTenantCopyContext context)
            throws CloneNotSupportedException {
        CreateResponse<G> createResponse = context.createOrRetrieveCopyInstance(this);
        if (createResponse.isAlreadyPopulated()) {
            return createResponse;
        }
        FieldGroup cloned = createResponse.getClone();
        cloned.setInitCollapsedFlag(initCollapsedFlag);
        cloned.setName(name);
        List<FieldDefinition> clonedFieldDefinitions = new ArrayList(fieldDefinitions.values()); 
        for (FieldDefinition fieldDefinition : clonedFieldDefinitions) {
            FieldDefinition clonedDef = fieldDefinition.createOrRetrieveCopyInstance(context).getClone();
            cloned.getFieldDefinitions().add(clonedDef);
        }
        return createResponse;
    }

	@Override
	public String getMainEntityName() {
		return getName();
	}
}

