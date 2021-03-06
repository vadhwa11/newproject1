package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MAS_ICD database table.
 * 
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="MAS_ICD")
@NamedQuery(name="MasIcd.findAll", query="SELECT m FROM MasIcd m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasIcd implements Serializable {
	private static final long serialVersionUID = -7505013476362497072L;

	@Id
	@SequenceGenerator(name="MAS_ICD_ICDID_GENERATOR", sequenceName="MAS_ICD_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_ICD_ICDID_GENERATOR")
	@Column(name="ICD_ID")
	private Long icdId;

	@Column(name="ICD_CAUSE_ID")
	private Long icdCauseId;

	@Column(name="ICD_CODE")
	private String icdCode;

	@Column(name="ICD_NAME")
	private String icdName;

	@Column(name="ICD_SUB_CATEGORY_ID")
	private Long icdSubCategoryId;

	@Column(name="LAST_CHG_BY")
	private String lastChgBy;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHG_DATE")
	private Date lastChgDate;

	private String status;
	
	@OneToMany(mappedBy="masIcd")
	@JsonBackReference
	private List<ReferralPatientDt> referralPatientDt;

	public Long getIcdId() {
		return icdId;
	}

	public void setIcdId(Long icdId) {
		this.icdId = icdId;
	}

	public Long getIcdCauseId() {
		return icdCauseId;
	}

	public void setIcdCauseId(Long icdCauseId) {
		this.icdCauseId = icdCauseId;
	}

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}

	public String getIcdName() {
		return icdName;
	}

	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}

	public Long getIcdSubCategoryId() {
		return icdSubCategoryId;
	}

	public void setIcdSubCategoryId(Long icdSubCategoryId) {
		this.icdSubCategoryId = icdSubCategoryId;
	}

	public String getLastChgBy() {
		return lastChgBy;
	}

	public void setLastChgBy(String lastChgBy) {
		this.lastChgBy = lastChgBy;
	}

	public Date getLastChgDate() {
		return lastChgDate;
	}

	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ReferralPatientDt> getReferralPatientDt() {
		return referralPatientDt;
	}

	public void setReferralPatientDt(List<ReferralPatientDt> referralPatientDt) {
		this.referralPatientDt = referralPatientDt;
	}
	

}