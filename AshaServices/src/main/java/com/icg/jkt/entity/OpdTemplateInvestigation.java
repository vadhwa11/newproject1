package com.icg.jkt.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the OPD_TEMPLATE_INVESTIGATION database table.
 * 
 */
@Entity
@Table(name="OPD_TEMPLATE_INVESTIGATION")
@NamedQuery(name="OpdTemplateInvestigation.findAll", query="SELECT o FROM OpdTemplateInvestigation o")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OpdTemplateInvestigation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2338245462993157886L;


	@Id
	@SequenceGenerator(name="OPD_TEMPLATE_INVESTIGATION_SEQ", sequenceName="OPD_TEMPLATE_INVESTIGATION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OPD_TEMPLATE_INVESTIGATION_SEQ")
	
	@Column(name="TEMPLATE_INVESTIGATION_ID")
	private Long templateInvestigationId;

	
	@Column(name="INVESTIGATION_ID")
	private Long investigationId;
	
	@Column(name="LAST_CHG_BY")
	private Long lastChgBy;

	

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	private String status;

	@Column(name="TEMPLATE_ID")
	private Long templateId;


	 @OneToMany(mappedBy="opdTemplateInvestigation", cascade = CascadeType.ALL)
	 @JsonBackReference
	 private List<OpdTemplate> opdTemplate;

	 
	 public Long getTemplateInvestigationId() {
			return templateInvestigationId;
		}


		public void setTemplateInvestigationId(Long templateInvestigationId) {
			this.templateInvestigationId = templateInvestigationId;
		}


		public Long getInvestigationId() {
			return investigationId;
		}


		public void setInvestigationId(Long investigationId) {
			this.investigationId = investigationId;
		}


		public Long getLastChgBy() {
			return lastChgBy;
		}


		public void setLastChgBy(Long lastChgBy) {
			this.lastChgBy = lastChgBy;
		}


		public Timestamp getLastChgDate() {
			return lastChgDate;
		}


		public void setLastChgDate(Timestamp lastChgDate) {
			this.lastChgDate = lastChgDate;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public Long getTemplateId() {
			return templateId;
		}


		public void setTemplateId(Long templateId) {
			this.templateId = templateId;
		}


		public List<OpdTemplate> getOpdTemplate() {
			return opdTemplate;
		}


		public void setOpdTemplate(List<OpdTemplate> opdTemplate) {
			this.opdTemplate = opdTemplate;
		}


}