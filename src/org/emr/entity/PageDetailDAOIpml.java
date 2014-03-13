/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.entity;

import java.util.List;
import org.emr.entity.PageDetail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nishith Shah
 */
@Service
public class PageDetailDAOIpml implements PageDetailDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPageDetail(PageDetail pageDetail) {
        this.sessionFactory.getCurrentSession().save(pageDetail);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PageDetail> getAllPageDetail() {
        return this.sessionFactory.getCurrentSession().createQuery("from PageDetail").list();
    }

    @Override
    public void deletePageDetail(Integer id) {
        PageDetail pageDetail = (PageDetail) sessionFactory.getCurrentSession().load(PageDetail.class, id);
        if (null != pageDetail) {
            this.sessionFactory.getCurrentSession().delete(pageDetail);
        }
    }

    @Override
    public PageDetail getPageDetailById(Integer id) {
        PageDetail pageDetail = (PageDetail) sessionFactory.getCurrentSession().load(PageDetail.class, id);
        return pageDetail;
    }

}
