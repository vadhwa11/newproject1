/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author SKILLLOTO G006
 */
@Entity
@Table(name = "gstr1")
public class GstnEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
    
    @Id
     @Column(name = "gstin")
    String gstin;

    @Column(name = "fp")
    String fp;

    @Column(name = "gt")
    String gt;

    public String getGstinno() {
        return gstin;
    }

    public void setGstinno(String gstinno) {
        this.gstin = gstinno;
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
    
}
