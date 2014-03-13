package org.emr.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.emr.factory.FactoryBean;

/**
 * 
 * @author Nimesh Makwana
 *
 *
 */
@Entity
@Table(name="user")
public class UserBean extends FactoryBean{
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	@Column(name="username", nullable = false)
	private String userName;
	

	/**
	 * @return the id
	 */
	

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
