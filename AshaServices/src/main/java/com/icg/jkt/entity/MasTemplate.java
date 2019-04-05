package com.icg.jkt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="MAS_TEMPLATE")
public class MasTemplate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1781679160382157427L;
	@Id
	@SequenceGenerator(name="MAS_TEMPLATE_GENERATOR", sequenceName="MAS_TEMPLATE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_TEMPLATE_GENERATOR")
	@Column(name="TEMPLATE_ID")
	private long templateId;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="TEMPLATE_CODE")
	private String templateCode;

	@Column(name="TEMPLATE_NAME")
	private String templateName;

	//bi-directional many-to-one association to MasHospital
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOSPITAL_ID")
	private MasHospital masHospital;

	/*//bi-directional many-to-one association to MasTemplate
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TEMPLATE_PARENT_ID")
	private MasTemplate masTemplate;*/

	/*//bi-directional many-to-one association to MasTemplate
	@OneToMany(mappedBy="masTemplate")
	private List<MasTemplate> masTemplates;*/

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users user;

	public MasTemplate() {
	}

	public long getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
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

	public String getTemplateCode() {
		return this.templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public MasHospital getMasHospital() {
		return this.masHospital;
	}

	public void setMasHospital(MasHospital masHospital) {
		this.masHospital = masHospital;
	}

	/*public MasTemplate getMasTemplate() {
		return this.masTemplate;
	}

	public void setMasTemplate(MasTemplate masTemplate) {
		this.masTemplate = masTemplate;
	}

	public List<MasTemplate> getMasTemplates() {
		return this.masTemplates;
	}

	public void setMasTemplates(List<MasTemplate> masTemplates) {
		this.masTemplates = masTemplates;
	}*/

	/*public MasTemplate addMasTemplate(MasTemplate masTemplate) {
		getMasTemplates().add(masTemplate);
		masTemplate.setMasTemplate(this);

		return masTemplate;
	}

	public MasTemplate removeMasTemplate(MasTemplate masTemplate) {
		getMasTemplates().remove(masTemplate);
		masTemplate.setMasTemplate(null);

		return masTemplate;
	}*/

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
