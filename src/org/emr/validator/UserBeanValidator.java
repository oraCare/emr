package org.emr.validator;

import org.emr.bean.UserBean;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Service
public class UserBeanValidator implements Validator{
	
	
	@Override
	public boolean supports(Class<?> arg0) {
		return UserBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		System.out.println("validator called");
		//ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "name", "name is blank","bbbb");
		notNimesh(arg0, arg1);
	}
	public void notNimesh(Object arg0,Errors ar1){
		System.out.println("arg ---");
		System.out.println("validating and printing for nimesh " + ((UserBean)arg0).getUserName());
		/*if(((UserBean)arg0).getName() != "nimesh"){
			ar1.reject("it is eeeemppptttyy" , "it is more then empty");
		}else{
			System.out.println("returning as it is");
			return;
		}*/
	}

}
