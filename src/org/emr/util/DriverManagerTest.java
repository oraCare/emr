package org.emr.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
@Service
public class DriverManagerTest extends DriverManagerDataSource{
	public DriverManagerTest(){
		super();
		
	}
	private String driverClassName;
	private String url,username,password;
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		System.out.println("setting ~~~~~~~~~ driverClassName" + driverClassName);
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
