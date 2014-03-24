package org.emr.bean;

import org.emr.factory.FactoryBean;

public class ProfileBean extends FactoryBean{
	private Long id;
	private String name;
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
