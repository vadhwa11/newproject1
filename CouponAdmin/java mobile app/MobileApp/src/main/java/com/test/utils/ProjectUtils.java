/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SKILLLOTO G006
 */
public class ProjectUtils {
    
    public static String getTranId() {
        DateFormat sdf = new SimpleDateFormat("YYMMHHmmss");
        Date date = new Date();
        System.out.println("Tran Id:"+"LAPN"+sdf.format(date));
        
        return "LAPN"+sdf.format(date);
    }
    
    
    
}
