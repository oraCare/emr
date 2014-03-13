/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.controller;

import org.emr.entity.PageDetail;
import org.emr.factory.Model;

import org.json.JSONObject;
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
//    public @ResponseBody String getTest(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test.html");
        return mv;
//        return "Nishith";
    }
    @RequestMapping(value = "/data")
    @ResponseBody
    public String getJSON() {
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