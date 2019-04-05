package com.icg.jkt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MAS_APPLICATION")
public class MasApplication implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3933939796458409072L;

	@Id
	@SequenceGenerator(name="MAS_APPLICATION_GENERATOR", sequenceName="MAS_APPLICATION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_APPLICATION_GENERATOR")
	@Column(name="APP_ID")
	private String applicationtId;
	
	@Column(name="NAME")
	private String applicationName;
	
	@Column(name="PARENT_ID")
	private String parentId;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="ORDER_NO")
	private long orderNo;

	

	public String getApplicationtId() {
		return applicationtId;
	}

	public void setApplicationtId(String applicationtId) {
		this.applicationtId = applicationtId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}