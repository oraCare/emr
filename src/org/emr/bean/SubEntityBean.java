package org.emr.bean;

import org.emr.factory.FactoryBean;

public class SubEntityBean extends FactoryBean{
	private Long id ;
	private Long entityId;
	private String name,description;
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id ;
	}

}
