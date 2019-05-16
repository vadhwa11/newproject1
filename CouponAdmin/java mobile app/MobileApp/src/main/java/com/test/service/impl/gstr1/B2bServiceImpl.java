/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service.impl.gstr1;

import com.test.dao.gstr1.B2bInvoiceDao;
import com.test.entity.B2BInvoicesEntity;
import com.test.entity.ItemsEntity;
import com.test.service.gstr1.B2bService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SKILLLOTO G006
 */
@Service("B2bServiceImpl")
@Transactional
public class B2bServiceImpl implements B2bService{
    
    @Autowired
    B2bInvoiceDao  gstr1Dao;
    
    @Override
    public List<B2BInvoicesEntity> getAllinvoice() {
         return gstr1Dao.getAllinvoice();
    }

   
    
}
