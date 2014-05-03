<%@page import="java.beans.PropertyDescriptor"%>
<%@page import="java.beans.Introspector"%>
<%@page import="java.beans.BeanInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
BeanInfo beanInfo = Introspector.getBeanInfo(Class.forName("com.ccc.entity.system.certificate.UICertificateRelationBean"));
PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
for (PropertyDescriptor p : propertyDescriptors) {
out.println(p.getName());
}
%>
</body>
</html>