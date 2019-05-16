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
@Table(name = "b2binvoices")
public class B2BInvoicesEntity {

//    @Id
//    private Long id;

   
    @Id
    @Column(name = "ctin")
    String  ctin;

    
    @Column(name = "flag")
    public String  flag;
    
    @Column(name = "inum")
    public String  inum;
    
    @Column(name = "idt")
    public String  idt;
    
    @Column(name = "gstinno")
    public String  gstinno;
    
    @Column(name = "checksum")
    public String  checksum;
    
    @Column(name = "val")
    public String  val;
    
    @Column(name = "pos")
    public String  pos;
    
    @Column(name = "rchrg")
    public String  rchrg;
    
    @Column(name = "pro_ass")
    public String  pro_ass;
    
    
    
    
    
    
    public String getCtin() {
        return ctin;
    }

    public void setCtin(String ctin) {
        this.ctin = ctin;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getInum() {
        return inum;
    }

    public void setInum(String inum) {
        this.inum = inum;
    }

    public String getIdt() {
        return idt;
    }

    public void setIdt(String idt) {
        this.idt = idt;
    }

    public String getGstinno() {
        return gstinno;
    }

    public void setGstinno(String gstinno) {
        this.gstinno = gstinno;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getRchrg() {
        return rchrg;
    }

    public void setRchrg(String rchrg) {
        this.rchrg = rchrg;
    }

    public String getPro_ass() {
        return pro_ass;
    }

    public void setPro_ass(String pro_ass) {
        this.pro_ass = pro_ass;
    }
    
    
//    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
    
}
