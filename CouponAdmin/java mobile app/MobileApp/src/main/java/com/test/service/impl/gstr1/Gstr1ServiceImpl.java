/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service.impl.gstr1;

import com.test.dao.gstr1.Gstr1Dao;
import com.test.entity.B2BInvoicesEntity;
import com.test.entity.GstnEntity;
import com.test.entity.ItemsEntity;
import com.test.service.gstr1.Gstr1Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SKILLLOTO G006
 */
@Service("Gstr1SericeImpl")
@Transactional

public class Gstr1ServiceImpl implements Gstr1Service{

    @Autowired
    Gstr1Dao  gstr1Dao;

    @Override
    public List<GstnEntity> getGstndata() {
        return gstr1Dao.getGstndata();
    }

    
    
    
    
}
