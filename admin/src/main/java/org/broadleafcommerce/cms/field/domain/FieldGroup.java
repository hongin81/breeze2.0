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

import org.broadleafcommerce.common.copy.MultiTenantCloneable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by bpolster.
 */
/**
 * 
 * @author kunner
 *Modified by Kunner, 2015/08/01, kunner@kunner.com
 *
 *수정 내용
 *		- setFieldDefinitions을 (List)에서 (Map<String, FieldDefinition>) 으로 변경
 *		- 기존 List를 유지할 경우, 넘겨 받을 List 객체가 존재해야 하므로 해당 메소드 제거
 *
 * 특기 사항
 * 	- 기존 BLC 소스를 수정한 것이므로, admin/site 프로젝트에 각각 소스 복사 필요
 * 	- BLC 버전업 시 변경 사항 체크 필요 
 */
public interface FieldGroup extends Serializable, MultiTenantCloneable<FieldGroup> {

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public Boolean getInitCollapsedFlag();

    public void setInitCollapsedFlag(Boolean initCollapsedFlag);

    public List<FieldDefinition> getFieldDefinitions();
    
    // 기존 List 로 된 fieldDefinitions 를 Map 으로 변경 
    public void setFieldDefinitions(Map<String, FieldDefinition> fieldDefinitions);

}
