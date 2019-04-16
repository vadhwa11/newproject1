package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TEMPLATE_APPLICATION database table.
 * 
 */
@Entity
@Table(name="TEMPLATE_APPLICATION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NamedQuery(name="TemplateApplication.findAll", query="SELECT t FROM TemplateApplication t")
public class TemplateApplication implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9037613934511547340L;

	@Id
	@SequenceGenerator(name="TEMPLATE_APPLICATION_GENERATOR", sequenceName="TEMPLATE_APPLICATION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEMPLATE_APPLICATION_GENERATOR")
	@Column(name="TEMP_APP_ID")
	private long tempAppId;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;
	
	@Column(name="STATUS")
	private String status;

	//bi-directional many-to-one association to MasApplication
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APP_ID")
	private MasApplication masApplication;

	//bi-directional many-to-one association to MasTemplate
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TEMPLATE_ID")
	private MasTemplate masTemplate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users user;
	
	public TemplateApplication() {
	}

	public long getTempAppId() {
		return this.tempAppId;
	}

	public void setTempAppId(long tempAppId) {
		this.tempAppId = tempAppId;
	}

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MasApplication getMasApplication() {
		return this.masApplication;
	}

	public void setMasApplication(MasApplication masApplication) {
		this.masApplication = masApplication;
	}

	public MasTemplate getMasTemplate() {
		return this.masTemplate;
	}

	public void setMasTemplate(MasTemplate masTemplate) {
		this.masTemplate = masTemplate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}