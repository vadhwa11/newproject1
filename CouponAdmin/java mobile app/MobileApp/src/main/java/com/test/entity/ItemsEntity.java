/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author SKILLLOTO G006
 */

@Entity
@Table(name = "items")
public class ItemsEntity {

//    @Id
//    private Long id;

    @Column(name = "num")
    String  num;
    
    @Column(name = "status")
    String status;
    
    @Column(name = "ty")
    String ty;
    
    @Id
    @Column(name = "hsn_sc")
    String hsn_sc;
   
    @Column(name = "ctin")
    String ctin;
    
    @Column(name = "txval")
    String txval;
    
    @Column(name = "irt")
    String irt;
    
    @Column(name = "iamt")
    String iamt;
   
    @Column(name = "crt")
    String crt;
   
    @Column(name = "camt")
    String camt;
   
    @Column(name = "srt")
    String srt;

    @Column(name = "samt")
    String samt;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public String getHsn_sc() {
        return hsn_sc;
    }

    public void setHsn_sc(String hsn_sc) {
        this.hsn_sc = hsn_sc;
    }

    public String getCtin() {
        return ctin;
    }

    public void setCtin(String ctin) {
        this.ctin = ctin;
    }

    public String getTxval() {
        return txval;
    }

    public void setTxval(String txval) {
        this.txval = txval;
    }

    public String getIrt() {
        return irt;
    }

    public void setIrt(String irt) {
        this.irt = irt;
    }

    public String getIamt() {
        return iamt;
    }

    public void setIamt(String iamt) {
        this.iamt = iamt;
    }

    public String getCrt() {
        return crt;
    }

    public void setCrt(String crt) {
        this.crt = crt;
    }

    public String getCamt() {
        return camt;
    }

    public void setCamt(String camt) {
        this.camt = camt;
    }

    public String getSrt() {
        return srt;
    }

    public void setSrt(String srt) {
        this.srt = srt;
    }

    public String getSamt() {
        return samt;
    }

    public void setSamt(String samt) {
        this.samt = samt;
    }
    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
    
    
    
//    CREATE TABLE `` (
//  `` varchar(50) default NULL,
//  `` varchar(50) default NULL,
//  `` varchar(50) default NULL,
//  `` varchar(50) NOT NULL default '',
//  `` varchar(50) default NULL,
//  `` varchar(50) default NULL,
//  `` tinyint(50) default NULL,
//  `` varchar(50) default NULL,
//  `` varchar(50) default NULL,
//  `` varchar(50) default NULL,
//  `` varchar(50) default NULL,
//  `` varchar(50) default NULL,
//  PRIMARY KEY  (`hsn_sc`),
    
}
