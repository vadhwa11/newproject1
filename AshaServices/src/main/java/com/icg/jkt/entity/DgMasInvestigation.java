package com.icg.jkt.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * The persistent class for the DG_MAS_INVESTIGATION database table.
 * 
 */
@Entity
@Table(name="DG_MAS_INVESTIGATION")
@NamedQuery(name="DgMasInvestigation.findAll", query="SELECT d FROM DgMasInvestigation d")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DgMasInvestigation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7354271829155665226L;

	@Id
	@SequenceGenerator(name="DG_MAS_INVESTIGATION_SEQ", sequenceName="DG_MAS_INVESTIGATION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DG_MAS_INVESTIGATION_SEQ")
	@Column(name="INVESTIGATION_ID")
	private Long investigationId;

	@Column(name="APPEAR_IN_DISCHARGE_SUMMARY")
	private String appearInDischargeSummary;

	@Column(name="APPOINTMENT_REQUIRED")
	private String appointmentRequired;

	@Column(name="BLOOD_BANK_SCREEN_TEST")
	private String bloodBankScreenTest;

	@Column(name="BLOOD_REACTION_TEST")
	private String bloodReactionTest;

	@Column(name="CHARGE_CODE_ID")
	private Long chargeCodeId;

	@Column(name="COLLECTION_ID")
	private Long collectionId;

	private String confidential;

	@Column(name="EQUIPMENT_ID")
	private Long equipmentId;

	@Column(name="HIC_CODE")
	private String hicCode;

	private String instructions;

	@Column(name="INVESTIGATION_NAME")
	private String investigationName;

	@Column(name="INVESTIGATION_TYPE")
	private String investigationType;

	@Column(name="LAST_CHG_BY")
	private Long lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="MAIN_CHARGECODE_ID")
	private Long mainChargecodeId;

	@Column(name="MAX_NORMAL_VALUE")
	private String maxNormalValue;

	@Column(name="MIN_NORMAL_VALUE")
	private String minNormalValue;

	@Column(name="MULTIPLE_RESULTS")
	private String multipleResults;

	@Column(name="NORMAL_VALUE")
	private String normalValue;

	@Column(name="NUMERIC_OR_STRING")
	private String numericOrString;

	private String quantity;

	@Column(name="SAMPLE_ID")
	private Long sampleId;

	private String status;

	@Column(name="SUB_CHARGECODE_ID")
	private Long subChargecodeId;

	@Column(name="TEST_ORDER_NO")
	private Long testOrderNo;

	@Column(name="UOM_ID")
	private Long uomId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CHARGE_CODE_ID",nullable=false,insertable=false,updatable=false)
	private MainCharge mainChargeCode;

	public Long getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(Long investigationId) {
		this.investigationId = investigationId;
	}

	public String getAppearInDischargeSummary() {
		return appearInDischargeSummary;
	}

	public void setAppearInDischargeSummary(String appearInDischargeSummary) {
		this.appearInDischargeSummary = appearInDischargeSummary;
	}

	public String getAppointmentRequired() {
		return appointmentRequired;
	}

	public void setAppointmentRequired(String appointmentRequired) {
		this.appointmentRequired = appointmentRequired;
	}

	public String getBloodBankScreenTest() {
		return bloodBankScreenTest;
	}

	public void setBloodBankScreenTest(String bloodBankScreenTest) {
		this.bloodBankScreenTest = bloodBankScreenTest;
	}

	public String getBloodReactionTest() {
		return bloodReactionTest;
	}

	public void setBloodReactionTest(String bloodReactionTest) {
		this.bloodReactionTest = bloodReactionTest;
	}

	public Long getChargeCodeId() {
		return chargeCodeId;
	}

	public void setChargeCodeId(Long chargeCodeId) {
		this.chargeCodeId = chargeCodeId;
	}

	public Long getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}

	public String getConfidential() {
		return confidential;
	}

	public void setConfidential(String confidential) {
		this.confidential = confidential;
	}

	public Long getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getHicCode() {
		return hicCode;
	}

	public void setHicCode(String hicCode) {
		this.hicCode = hicCode;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getInvestigationName() {
		return investigationName;
	}

	public void setInvestigationName(String investigationName) {
		this.investigationName = investigationName;
	}

	public String getInvestigationType() {
		return investigationType;
	}

	public void setInvestigationType(String investigationType) {
		this.investigationType = investigationType;
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

	public Long getMainChargecodeId() {
		return mainChargecodeId;
	}

	public void setMainChargecodeId(Long mainChargecodeId) {
		this.mainChargecodeId = mainChargecodeId;
	}

	public String getMaxNormalValue() {
		return maxNormalValue;
	}

	public void setMaxNormalValue(String maxNormalValue) {
		this.maxNormalValue = maxNormalValue;
	}

	public String getMinNormalValue() {
		return minNormalValue;
	}

	public void setMinNormalValue(String minNormalValue) {
		this.minNormalValue = minNormalValue;
	}

	public String getMultipleResults() {
		return multipleResults;
	}

	public void setMultipleResults(String multipleResults) {
		this.multipleResults = multipleResults;
	}

	public String getNormalValue() {
		return normalValue;
	}

	public void setNormalValue(String normalValue) {
		this.normalValue = normalValue;
	}

	public String getNumericOrString() {
		return numericOrString;
	}

	public void setNumericOrString(String numericOrString) {
		this.numericOrString = numericOrString;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Long getSampleId() {
		return sampleId;
	}

	public void setSampleId(Long sampleId) {
		this.sampleId = sampleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSubChargecodeId() {
		return subChargecodeId;
	}

	public void setSubChargecodeId(Long subChargecodeId) {
		this.subChargecodeId = subChargecodeId;
	}

	public Long getTestOrderNo() {
		return testOrderNo;
	}

	public void setTestOrderNo(Long testOrderNo) {
		this.testOrderNo = testOrderNo;
	}

	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}

	public MainCharge getMainChargeCode() {
		return mainChargeCode;
	}

	public void setMainChargeCode(MainCharge mainChargeCode) {
		this.mainChargeCode = mainChargeCode;
	}
	
}