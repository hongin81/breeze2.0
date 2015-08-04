package kr.brzc.common.enumeration.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.broadleafcommerce.common.admin.domain.AdminMainEntity;
import org.broadleafcommerce.common.enumeration.domain.DataDrivenEnumerationImpl;
import org.broadleafcommerce.common.extensibility.jpa.copy.ProfileEntity;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.PopulateToOneFieldsEnum;

/**
 * @author kunner
 * Created by Kunner, 2015/08/01, kunner@kunner.com
 * 
 * 작성 사유
 * 	- 기존 DataDrivenEnumerationImpl 에 getMainEntityName 메소드가 없어 해당 내용 추가
 * 	- Enum 편집 화면 상단에 Entity 명이 출력되도록 개선  
 * 
 * 작성 내용
 * 	- Class 신규 작성 
 * 
 * 특기 사항
 *		- 엔티티 추가에 따른 XML 설정 필요 core/src/main/resources/META-INF/persistence-core.xml, core/src/main/resources/WEB-INF/applicationContext-entity.xml
 *
 */

@Entity
@DiscriminatorValue("CustomDataDrivenEnumeration")
@AdminPresentationClass(populateToOneFields = PopulateToOneFieldsEnum.TRUE, friendlyName = "DataDrivenEnumerationImpl_friendyName")
public class CustomDataDrivenEnumerationImpl extends DataDrivenEnumerationImpl implements AdminMainEntity, ProfileEntity {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMainEntityName() {
		return getKey();
	}
	
}
