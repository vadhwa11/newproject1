/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.service.gstr1;

import com.test.entity.B2BInvoicesEntity;
import com.test.entity.GstnEntity;
import com.test.entity.ItemsEntity;
import java.util.List;

/**
 *
 * @author SKILLLOTO G006
 */
public interface Gstr1Service {
    List<GstnEntity> getGstndata();
    
}
