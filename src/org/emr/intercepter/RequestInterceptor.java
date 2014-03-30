/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.intercepter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.emr.bean.LoginBean;
import org.emr.factory.FactoryBean;
import org.emr.factory.Model;
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
	public boolean authorizeRequest(HttpServletRequest request){
		if(request.getAttribute("aid")!=null)
			return true;
		else
			return false;
	}
	public final boolean validateSession(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
		HttpSession session = request.getSession();
		boolean status = true;
		if(session.isNew()){
			System.out.println("new session found :");
			String username = (String)request.getParameter("username");
			String password = (String)request.getParameter("password");
			ArrayList<FactoryBean> loginBean = model.select("from LoginBean where username = '"+username+"' and password = '" +password +"'");
			if(loginBean.size()==0){
				System.out.println("invalidate session on first request if login failed:");
				session.invalidate();
				return false;
			}
			System.out.println("username : " + username);
			session.setAttribute("loginBean",(LoginBean)loginBean.get(0));
			session.setMaxInactiveInterval(60);
		}
		return status;
	}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	System.out.println("START ****************************Inspecting***************************");
    	if(!validateSession(request,response))
    		request.getRequestDispatcher("login.htm").forward(request, response);
    	
        boolean result = true;
        
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
