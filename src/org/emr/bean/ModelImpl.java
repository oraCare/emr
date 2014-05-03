package org.emr.bean;

import java.util.ArrayList;
import org.emr.factory.FactoryBean;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.emr.factory.Model;


/**
 *
 * @author Nimesh Makwana
 *
 * Each method in this must have to be annotated with @Transactional
 *
 */
@Service
@Transactional
public class ModelImpl implements Model {

    @Autowired(required=true)
    private SessionFactory sessionFactory;

    @Override
    public int saveOrUpdate(FactoryBean factoryBean) {
        sessionFactory.getCurrentSession().save(factoryBean);
        return 0;
    }

    /**
     * Any bean inherited from FactoryBean will be used in this method.
     * @param factoryBean
     * @return 
     */
    @Override
    public ArrayList<FactoryBean> getBeanList(FactoryBean factoryBean) {
        System.out.println(" class name == " + factoryBean.getClass().getName());
        ArrayList<FactoryBean> beanList = (ArrayList<FactoryBean>) sessionFactory.getCurrentSession().createQuery("from " + factoryBean.getClass().getName()).list();
        System.out.println("beans generated from table" + beanList);
        return beanList;
    }

    /**
     * Ask this method for read only quries only , this will return FactoryBean
     * for expected bean
     * @param query
     * @return List<FactoryBean>
     */
    @Override
    public ArrayList<FactoryBean> select(String query) {
        Query query1 = sessionFactory.getCurrentSession().createQuery(query).setReadOnly(true);
        return (ArrayList<FactoryBean>) query1.list();
    }

    @Override
    public void delete(FactoryBean factoryBean) {
        sessionFactory.getCurrentSession().delete(factoryBean);
    }
    
	@Override
	@Transactional
	public String getValidatorForBean(String beanName) {
		System.out.println("getting bean ref " + beanName);
		Session session = sessionFactory.getCurrentSession();
		session.setDefaultReadOnly(true);
		
	//							select name from fbeanvalidator where beanid = (select id from fbean where name = 'org.emr.bean.UserBean');
		return session.createSQLQuery("select name from fbeanvalidator where beanid = (select id from fbean where name ='" +beanName + "')").uniqueResult().toString();
	}
	
    public void className(Class className){
        System.out.println(":::::::::::::::::::::::::::::::::::"+className.getClass());
    }

	@Override
	@Transactional
	public FactoryBean getBean(String className) {
		
		return null;
	}

	@Override
	@Transactional
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

}