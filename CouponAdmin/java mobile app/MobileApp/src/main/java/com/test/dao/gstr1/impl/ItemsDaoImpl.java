/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.dao.gstr1.impl;

import com.test.dao.genericdao.AbstractDao;
import com.test.dao.gstr1.ItemsDao;
import com.test.entity.ItemsEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SKILLLOTO G006
 */
@Repository("ItemsDaoImpl")
public class ItemsDaoImpl extends AbstractDao<Integer, ItemsEntity> implements ItemsDao{
    
    
     @Override
    public List<ItemsEntity> getAllitems() {
       Criteria criteria = createEntityCriteria();
        return (List<ItemsEntity>) criteria.list();
    }
    
}
