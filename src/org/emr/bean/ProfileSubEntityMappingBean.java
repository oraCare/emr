package org.emr.bean;

import org.emr.factory.FactoryBean;

public class ProfileSubEntityMappingBean extends FactoryBean{
	private Long id;
	private Long profileId,subEntityId;
	private int accessType;
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getSubEntityId() {
		return subEntityId;
	}

	public void setSubEntityId(Long subEntityId) {
		this.subEntityId = subEntityId;
	}

	public int getAccessType() {
		return accessType;
	}

	public void setAccessType(int accessType) {
		this.accessType = accessType;
	}

	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
