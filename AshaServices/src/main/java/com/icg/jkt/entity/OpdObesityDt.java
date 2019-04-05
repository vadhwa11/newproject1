package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the OPD_OBESITY_DT database table.
 * 
 */
@Entity
@Table(name="OPD_OBESITY_DT")
@NamedQuery(name="OpdObesityDt.findAll", query="SELECT o FROM OpdObesityDt o")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OpdObesityDt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981364693530984751L;

	@Id
	@SequenceGenerator(name="OPD_OBESITY_DT_SEQ1", sequenceName="OPD_OBESITY_DT_SEQ1")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OPD_OBESITY_DT_SEQ1")
	@Column(name="OBESITY_DT_ID")
	private long obesityDtId;

	@Column(name="BMI")
	private BigDecimal bmi;

	@Temporal(TemporalType.DATE)
	@Column(name="OBESITY_DATE")
	private Date obesityDate;

	@Column(name="HEIGHT")
	private BigDecimal height;

	public BigDecimal getBmi() {
		return bmi;
	}

	public void setBmi(BigDecimal bmi) {
		this.bmi = bmi;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getIdealWeight() {
		return idealWeight;
	}

	public void setIdealWeight(BigDecimal idealWeight) {
		this.idealWeight = idealWeight;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getVariation() {
		return variation;
	}

	public void setVariation(BigDecimal variation) {
		this.variation = variation;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Column(name="IDEAL_WEIGHT")
	private BigDecimal idealWeight;

	@Column(name="MONTH")
	private String month;

	@Column(name="VARIATION")
	private BigDecimal variation;

	@Column(name="WEIGHT")
	private BigDecimal weight;

	//bi-directional many-to-one association to OpdObesityHd
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OBESITY_HD_ID",nullable=false,insertable=false,updatable=false)
	private OpdObesityHd opdObesityHd;

	public OpdObesityDt() {
	}

	public long getObesityDtId() {
		return this.obesityDtId;
	}

	public void setObesityDtId(long obesityDtId) {
		this.obesityDtId = obesityDtId;
	}

	public Date getObesityDate() {
		return this.obesityDate;
	}

	public void setObesityDate(Date obesityDate) {
		this.obesityDate = obesityDate;
	}

	public OpdObesityHd getOpdObesityHd() {
		return this.opdObesityHd;
	}

	public void setOpdObesityHd(OpdObesityHd opdObesityHd) {
		this.opdObesityHd = opdObesityHd;
	}

}