package org.emr.bean;

import org.emr.factory.FactoryBean;

public class EntityBean extends FactoryBean {
	private Long id;
	private Long moduleId;
	private String name,description;
	private ModuleBean moduleBean;
	@Override
	public Long getId() {
		
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
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

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public ModuleBean getModuleBean() {
		return moduleBean;
	}

	public void setModuleBean(ModuleBean moduleBean) {
		this.moduleBean = moduleBean;
	}
	
}
