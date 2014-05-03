/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.intercepter;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.emr.bean.EntityBean;
import org.emr.bean.LoginBean;
import org.emr.bean.LoginProfileBean;
import org.emr.bean.ModuleBean;
import org.emr.bean.ProfileBean;
import org.emr.bean.ProfileSubEntityMappingBean;
import org.emr.bean.SubEntityBean;
import org.emr.bean.UserBean;
import org.emr.factory.FactoryBean;
import org.emr.factory.Model;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonParser;

/**
 *
 * @author Nishith Shah
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	Model model;
	public void classPrinter(String object){
		System.out.println("RequestInterceptor : " + object); 
	}
	public boolean authorizeRequest(HttpServletRequest request){
		if(request.getAttribute("aid")!=null)
			return true;
		else
			return false;
	}
	private int checkACL(LoginBean loginBean,HttpServletRequest request){
		ArrayList<FactoryBean> fBean = model.select("from LoginProfileBean where loginId = " + loginBean.getId());
		int requestID = Integer.parseInt((String)request.getParameter("requestid"));
		
		if(fBean!=null){
			LoginProfileBean l = (LoginProfileBean)fBean.get(0);
			System.out.println("RequestInterceptor : login Profile bean id : " + l.getProfileId());
			
			ProfileSubEntityMappingBean profentityMapping  = (ProfileSubEntityMappingBean)model.select("from ProfileSubEntityMappingBean where profileId = " + l.getProfileId()).get(0);
		}else{
			System.out.println("RequestInterceptor : no profile binded to loginId -> " + loginBean.getId());
			return 0;
		}
		return 0;
	}
	
	private void testBean(){
		{
			// LOGINBEAN
			ArrayList<FactoryBean> loginBean = model
					.getBeanList(new LoginBean());
			if (loginBean != null) {
				Iterator<FactoryBean> lBean = (Iterator<FactoryBean>) loginBean
						.iterator();
				while (lBean.hasNext()) {
					System.out.println(" login : "
							+ ((LoginBean) lBean.next()).getUsername());
				}
			}
		}
		{
			// PROFILEBEAN

			ArrayList<FactoryBean> profileBean = model
					.getBeanList(new ProfileBean());
			if (profileBean != null) {
				Iterator<FactoryBean> lBean = (Iterator<FactoryBean>) profileBean
						.iterator();
				while (lBean.hasNext()) {
					System.out.println(" profile : "
							+ ((ProfileBean) lBean.next()).getName());
				}
			}
		}
		{
			// MODULEBEAN

			ArrayList<FactoryBean> moduleBean = model
					.getBeanList(new ModuleBean());
			if (moduleBean != null) {
				Iterator<FactoryBean> lBean = (Iterator<FactoryBean>) moduleBean
						.iterator();
				while (lBean.hasNext()) {
					Set<EntityBean> set = ((ModuleBean) lBean.next()).getEntityBeanSet();
					Iterator<EntityBean> itr= set.iterator();
					while(itr.hasNext())
					System.out.println(" module : entity set : name and description " + ((EntityBean)itr.next()).getName() );
							
				}
			}
		}
		{
			// ENTITYBEAN

			ArrayList<FactoryBean> entityBean = model
					.getBeanList(new EntityBean());
			if (entityBean != null) {
				Iterator<FactoryBean> lBean = (Iterator<FactoryBean>) entityBean
						.iterator();
				while (lBean.hasNext()) {
					System.out.println(" entity : "
							+ ((EntityBean) lBean.next()).getName());
				}
			}
		}
		{
			// SUBENTITYBEAN

			ArrayList<FactoryBean> entityBean = model
					.getBeanList(new SubEntityBean());
			if (entityBean != null) {
				Iterator<FactoryBean> lBean = (Iterator<FactoryBean>) entityBean
						.iterator();
				while (lBean.hasNext()) {
					System.out.println(" entity : "
							+ ((SubEntityBean) lBean.next()).getName());
				}
			}
		}
		{
			// LOGINPROFILEBEAN

			ArrayList<FactoryBean> entityBean = model
					.getBeanList(new LoginProfileBean());
			if (entityBean != null) {
				Iterator<FactoryBean> lBean = (Iterator<FactoryBean>) entityBean
						.iterator();
				while (lBean.hasNext()) {
					System.out.println(" login profile : profile id  : "
							+ ((LoginProfileBean) lBean.next()).getProfileId());
				}
			}
		}
		{
			// SUBENTITYBEAN

			ArrayList<FactoryBean> entityBean = model
					.getBeanList(new ProfileSubEntityMappingBean());
			if (entityBean != null) {
				Iterator<FactoryBean> lBean = (Iterator<FactoryBean>) entityBean
						.iterator();
				while (lBean.hasNext()) {
					System.out.println(" profile entity mapping with subentity id  : "
							+ ((ProfileSubEntityMappingBean) lBean.next()).getSubEntityId());
				}
			}
		}
		
	}
	public  boolean validateSession(HttpServletRequest request,HttpServletResponse response,FactoryBean loginBean) throws IOException, JSONException{
		HttpSession session = request.getSession();
		boolean status = true;
		if(session.isNew()){
			System.out.println("new session found :");
			String username = (String)request.getParameter("username");
			String password = (String)request.getParameter("password");
			ArrayList<FactoryBean> factoryBean = model.select("from LoginBean where username = '"+username+"' and password = '" +password +"'");
			if(factoryBean.size()==0){
				System.out.println("invalidate session on first request if login failed:");
				session.invalidate();
				return false;
			}
			/*
			 * get user profile : 
			 */
			loginBean = (LoginBean)factoryBean.get(0);
			System.out.println("username : " + username);
			session.setAttribute("loginBean",((LoginBean)loginBean));
			session.setMaxInactiveInterval(60);
		}
		return status;
	}
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	System.out.println("START ****************************Inspecting***************************");
    	LoginBean loginBean = new LoginBean();
    	boolean result = true;
    	if(!validateSession(request,response,loginBean)){
    		System.out.println("!!!!!! REQ !!!!! redirect to login.htm");
    		response.sendRedirect("login.htm");
    		return false;
    	}else{
    		loginBean = (LoginBean)request.getSession().getAttribute("loginBean");
    	}
        checkACL(loginBean,request);
        
//        if (!requestURI.contains("login")) {
//            if (!ApplicationUtil.checkAuthentication(request.getSession())) {
//                response.sendRedirect("login.htm");
//                result = false;
//            }
//        }
        
        System.out.println("END ****************************Inspecting***************************");
        return result;
    }

}
