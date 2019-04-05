package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the MAS_FREQUENCY database table.
 * 
 */
@Entity
@Table(name="MAS_FREQUENCY")
@NamedQuery(name="MasFrequency.findAll", query="SELECT m FROM MasFrequency m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasFrequency implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -71373866342875305L;

	@Id
	@SequenceGenerator(name="MAS_FREQUENCY_FREQUENCYID_GENERATOR", sequenceName="MAS_FREQUENCY_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_FREQUENCY_FREQUENCYID_GENERATOR")
	@Column(name="FREQUENCY_ID")
	private Long frequencyId;

	@Column(name="FREQUENCY_CODE")
	private String frequencyCode;
	
	@Column(name="FEQ")
	private Long feq;

	@Column(name="FREQUENCY_NAME")
	private String frequencyName;

	@Column(name="LAST_CHG_BY")
	private Long lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;
	
	@Column(name="STATUS")
	private String status;
    
	public Long getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(Long frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getFrequencyCode() {
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public Long getFeq() {
		return feq;
	}

	public void setFeq(Long feq) {
		this.feq = feq;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
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


}