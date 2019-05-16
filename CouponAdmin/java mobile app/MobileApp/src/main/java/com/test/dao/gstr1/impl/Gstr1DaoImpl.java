/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.dao.gstr1.impl;

import com.test.dao.genericdao.AbstractDao;
import com.test.dao.gstr1.Gstr1Dao;
import com.test.entity.B2BInvoicesEntity;
import com.test.entity.GstnEntity;
import com.test.entity.ItemsEntity;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SKILLLOTO G006
 */
@Repository("Gstr1DaoImpl")
public class Gstr1DaoImpl extends AbstractDao<Serializable, GstnEntity> implements Gstr1Dao{

    
    @Override
    public List<GstnEntity> getGstndata() {
        Criteria criteria = createEntityCriteria();
         return (List<GstnEntity>) criteria.list();
    }

    

    
    
}
