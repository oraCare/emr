/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emr.entity;

import java.util.List;
import org.emr.entity.PageDetail;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nishith Shah
 */
@Repository
public interface PageDetailDAO {

    public void addPageDetail(PageDetail pageDetail);

    public List<PageDetail> getAllPageDetail();
    
    public PageDetail getPageDetailById(Integer id);

    public void deletePageDetail(Integer id);
}
