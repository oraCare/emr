package org.emr.bean;

import org.emr.factory.FactoryBean;

public class ProfileBean extends FactoryBean{
	private Long id;
	private String name;
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
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

}
