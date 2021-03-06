package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the MAS_SAMPLE database table.
 * 
 */
@Entity
@Table(name="MAS_SAMPLE")
@NamedQuery(name="MasSample.findAll", query="SELECT m FROM MasSample m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasSample implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAS_SAMPLE_SAMPLEID_GENERATOR", sequenceName="MAS_SAMPLE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_SAMPLE_SAMPLEID_GENERATOR")
	@Column(name="SAMPLE_ID")
	private long sampleId;

	/*
	 * @Column(name="LAST_CHG_BY") private long lastChgBy;
	 */
	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="SAMPLE_CODE")
	private String sampleCode;

	@Column(name="SAMPLE_DESCRIPTION")
	private String sampleDescription;

	private String status;

	public MasSample() {
	}

	public long getSampleId() {
		return this.sampleId;
	}

	public void setSampleId(long sampleId) {
		this.sampleId = sampleId;
	}

	/*
	 * public long getLastChgBy() { return this.lastChgBy; }
	 * 
	 * public void setLastChgBy(long lastChgBy) { this.lastChgBy = lastChgBy; }
	 */

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getSampleCode() {
		return this.sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getSampleDescription() {
		return this.sampleDescription;
	}

	public void setSampleDescription(String sampleDescription) {
		this.sampleDescription = sampleDescription;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}