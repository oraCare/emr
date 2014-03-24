/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.emr.bean.LoginBean;
import org.emr.entity.PageDetail;
import org.emr.factory.FactoryBean;
import org.emr.factory.Model;
import org.emr.intercepter.RequestInterceptor;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Nishith Shah
 */
@Controller
public class ApplicationController {
	static Logger logger;
	static {
		logger = LoggerFactory.getLogger(RequestInterceptor.class.getName());
	}
    @Autowired
    Model model;

    @RequestMapping(value =  "/page")
    public ModelAndView home(@RequestParam("json") String jsonString) {
        ModelAndView mv = new ModelAndView();
        JSONObject json;
        try {
            System.out.println(jsonString);
            json = new JSONObject(jsonString);
            int pageid = json.has("pageid") ? json.getInt("pageid") : 0;
            switch (pageid) {
                case 2:
                    mv.setViewName("index.html");
                    break;
                default:
                    mv.setViewName("login.html"); 
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        mv.setViewName("index.html");
        return mv;
    }
    @RequestMapping(value = "/test")
    public ModelAndView getTest(){
    	ArrayList<FactoryBean>factoryBean = model.getBeanList(new LoginBean());
    	if(factoryBean!= null)
    		System.out.println("login bean is not null");
    	System.out.println("request for test.htm ");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        return mv;
    }
    @RequestMapping(value = "/test1")
    public ModelAndView responseJson(){
    	System.out.println("request for test ");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage.html");
        return mv;
    }
    @RequestMapping(value = "/data")
    @ResponseBody
    public String getJSON() {
    	logger.debug("debug info : ");
    	logger.info("info info ");
    	logger.error("error message error ");
        PageDetail pageDetail = new PageDetail();
//    	factoryBean.setId(1l);
//    	ArrayList<FactoryBean> beanList = model.getBeanList(factoryBean);
//    	System.out.println("json " + factoryBean.toJSON());
//		int i =0;
//		String nameList = new String();
//		while(i<beanList.size()){
//			UserBean bean = (UserBean)beanList.get(i);
//			nameList += bean.getUserName() + "!!! --- !!! ";
//			System.out.println("bean i " + i + " value : " + bean.getUserName());
//			i++;
//		}
//		nameList = "count = " + i + " =  " + nameList;
        System.out.println("hi nimc");
        return model.getBeanList(pageDetail).size() + "";
    }

//    @RequestMapping("/login")
//    public ModelAndView loginService() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("login.html");
//        return mv;
//    }
//
//    @RequestMapping("/auth")
//    public ModelAndView authService(@RequestParam("json") String json) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index.html");
//        return mv;
//    }
}