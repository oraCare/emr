/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Nishith Shah
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
	public boolean authorizeRequest(HttpServletRequest request){
		if(request.getAttribute("aid")!=null)
			return true;
		else
			return false;
	}
	public JSONObject getJsonFromRequest(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject(request.getParameterMap());
		System.out.println("request param : " + jsonObject );
		return jsonObject;
	}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        JSONObject jObj = getJsonFromRequest(request);
        
        System.out.println(" json object mobile number  : " +										 jObj.has("mob"));
        String requestURI = request.getRequestURI();
        
        System.out.println("****************************Inspecting***************************");
//        if (!requestURI.contains("login")) {
//            if (!ApplicationUtil.checkAuthentication(request.getSession())) {
//                response.sendRedirect("login.htm");
//                result = false;
//            }
//        }
        return result;
    }

}
