
package org.emr.bean;

import java.util.HashSet;
import java.util.Set;

import org.emr.factory.FactoryBean;

/*
 * @author : Nimesh Makwana
 */
public class ModuleBean extends FactoryBean {

    private Long id;

    private String name;
    private Set<EntityBean> entityBeanSet  = new HashSet<EntityBean>(0);

	@Override
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Set<EntityBean> getEntityBeanSet() {
		return entityBeanSet;
	}

	public void setEntityBeanSet(Set<EntityBean> entityBeanSet) {
		this.entityBeanSet = entityBeanSet;
	}


}
