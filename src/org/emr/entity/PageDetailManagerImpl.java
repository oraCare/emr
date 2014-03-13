/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.entity;

import java.util.List;
import org.emr.entity.PageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nishith Shah
 */
@Service
public class PageDetailManagerImpl implements PageDetailManager {

    @Autowired
    private PageDetailDAOIpml pageDetailDAOIpml;

    @Override
    @Transactional
    public void addPageDetail(PageDetail pageDetail) {
        pageDetailDAOIpml.addPageDetail(pageDetail);
    }

    @Override
    @Transactional
    public List<PageDetail> getAllPageDetail() {
        return pageDetailDAOIpml.getAllPageDetail();
    }

    @Override
    @Transactional
    public void deletePageDetail(Integer id) {
        pageDetailDAOIpml.deletePageDetail(id);
    }

    @Override
    @Transactional
    public PageDetail getPageDetailById(Integer id) {
        return pageDetailDAOIpml.getPageDetailById(id);
    }
}
