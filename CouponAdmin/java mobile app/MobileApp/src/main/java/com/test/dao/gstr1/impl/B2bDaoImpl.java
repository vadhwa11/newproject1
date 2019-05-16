/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.dao.gstr1.impl;

import com.test.dao.genericdao.AbstractDao;
import com.test.dao.gstr1.B2bInvoiceDao;
import com.test.entity.B2BInvoicesEntity;
import com.test.entity.ItemsEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SKILLLOTO G006
 */
@Repository("B2bDaoImpl")
public class B2bDaoImpl extends AbstractDao<Integer, B2BInvoicesEntity>implements B2bInvoiceDao{
    
    @Override
    public List<B2BInvoicesEntity> getAllinvoice() {
        
        Criteria criteria = createEntityCriteria();
        return (List<B2BInvoicesEntity>) criteria.list();
    }

   
    
}
