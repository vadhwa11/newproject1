/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service.impl.gstr1;

import com.test.dao.gstr1.ItemsDao;
import com.test.entity.ItemsEntity;
import com.test.service.gstr1.ItemsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SKILLLOTO G006
 */
@Service("ItemsServiceImpl")
@Transactional
public class ItemsServiceImpl implements ItemsService{
    
    @Autowired
    ItemsDao itemsDao;
    
     @Override
    public List<ItemsEntity> getAllitems() {
         return itemsDao.getAllitems();
    }
    
}
