package org.emr.factory;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Nimesh Makwana
 *
 * Model : provides Db connection and will be used for data manipulation and for view generation.
 * 		   Actual model implementation will be in ModelImpl
 *
 */
public interface Model {
	@Transactional
	public int saveOrUpdate(FactoryBean factoryBean);
	@Transactional
	public ArrayList<FactoryBean> getBeanList(FactoryBean factoryBean);
	@Transactional
	public ArrayList<FactoryBean> select(String query);
    @Transactional
    public void delete(FactoryBean factoryBean);
    @Transactional
    public String getValidatorForBean(String beanName);
        
}
