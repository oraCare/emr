/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.bean;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.emr.entity.PageDetail;
import org.emr.factory.FactoryBean;

/**
 *
 * @author Nishith Shah
 */
@Entity
@Table(name = "tblsubmodule")
public class SubModule extends FactoryBean {

    @Id
    @Column(name = "submoduleid")
    private Long subModuleId;

    @Column(name = "submodulename")
    private String subModuleName;

    @ManyToOne
    @JoinColumn(name = "moduleid")
    private Module module;

  //  @OneToMany(mappedBy = "subModule")
   // private Set<PageDetail> pageDetail;

    public Long getSubModuleId() {
        return subModuleId;
    }

    public void setSubModuleId(Long subModuleId) {
        this.subModuleId = subModuleId;
    }

    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public Long getId() {
        return getSubModuleId();
    }

    @Override
    public void setId(Long id) {
        setSubModuleId(subModuleId);
    }

}
