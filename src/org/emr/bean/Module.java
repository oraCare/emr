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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.emr.factory.FactoryBean;

/**
 *
 * @author Nishith Shah
 */
@Entity
@Table(name = "tblmodule")
public class Module extends FactoryBean {

    @Id
    @Column(name = "moduleid")
    private Long moduleId;

    @Column(name = "modulename")
    private String moduleName;

    @Column(name = "modulestatus")
    private Character moduleStatus;

    @OneToMany(mappedBy = "module")
    private Set<SubModule> subModules;

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Character getModuleStatus() {
        return moduleStatus;
    }

    public void setModuleStatus(Character moduleStatus) {
        this.moduleStatus = moduleStatus;
    }

    public Set<SubModule> getSubModules() {
        return subModules;
    }

    public void setSubModules(Set<SubModule> subModules) {
        this.subModules = subModules;
    }

    @Override
    public Long getId() {
        return getModuleId();
    }

    @Override
    public void setId(Long id) {
        setModuleId(id);
    }

}
