/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.entity;

import javax.persistence.*;

import org.emr.factory.FactoryBean;

/**
 *
 * @author Nishith Shah
 * @param <SubModule>
 */
@Entity
@Table(name = "tblpagedetail")
public class PageDetail<SubModule> extends FactoryBean {

    @Id
    @Column(name = "pageid")
    private Integer pageID;
    @Column(name = "page")
    private String page;
    public Integer getPageID() {
        return pageID;
    }

    public void setPageID(Integer pageID) {
        this.pageID = pageID;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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
