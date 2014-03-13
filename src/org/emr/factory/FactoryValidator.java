package org.emr.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
/**
 *  * 
 * @author Nimesh Makwana
 *
 * Interceptor should use this method to validate request param/bean.
 */
@Service
public class FactoryValidator{
	@Autowired
	Model model;
	public void validate(Object bean) {
		System.out.println("FactoryValidator.validate");
		Class cls;
		try {
			System.out.println(" in Factory Validator");
			cls = Class.forName(bean.getClass().getName());
			DataBinder db = new DataBinder(bean);
			if(model != null){
				String validator = model.getValidatorForBean(cls.getName());
				db.setValidator((Validator)Class.forName(validator).newInstance());
				db.validate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}//END OF validate method

}

