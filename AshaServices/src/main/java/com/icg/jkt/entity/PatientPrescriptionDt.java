package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PATIENT_PRESCRIPTION_DT database table.
 * 
 */
@Entity
@Table(name="PATIENT_PRESCRIPTION_DT")
@NamedQuery(name="PatientPrescriptionDt.findAll", query="SELECT p FROM PatientPrescriptionDt p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PatientPrescriptionDt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9013533870782682351L;

	@Id
	@SequenceGenerator(name="PATIENT_PRESCRIPTION_DT_SEQ", sequenceName="PATIENT_PRESCRIPTION_DT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PATIENT_PRESCRIPTION_DT_SEQ")
	@Column(name="PRESCRIPTION_DT_ID")
	private Long prescriptionDtId;

	@Column(name="BATCH_EXPIRY_DATE")
	private String batchExpiryDate;

	@Column(name="DISP_STOCK")
	private String dispStock;

	private String dosage;

	@Column(name="FREQUENCY_ID")
	private Long frequencyId;

	@Column(name="IMMUNIZATION_FLAG")
	private String immunizationFlag;

	@Column(name="INJECTION_STATUS")
	private String injectionStatus;

	private String instruction;

	@Column(name="ITEM_ID")
	private Long itemId;

	@Column(name="ITEM_STOP_BY")
	private Long itemStopBy;

	@Column(name="NIS_FLAG")
	private String nisFlag;

	@Column(name="NIS_NO")
	private String nisNo;

	@Column(name="NO_OF_DAYS")
	private Long noOfDays;

	@Column(name="OT_STAGE")
	private String otStage;

	@Column(name="PRESCRIPTION_HD_ID")
	private Long prescriptionHdId;

	@Column(name="QTY_BALANCE")
	private Long qtyBalance;

	@Column(name="QTY_ISSUED")
	private Long qtyIssued;

	private String route;

	@Column(name="STORE_STOCK")
	private String storeStock;

	private Long total;

	//bi-directional many-to-one association to PatientPrescriptionHd
		@ManyToOne
		@JoinColumn(name="PRESCRIPTION_HD_ID",nullable=false,insertable=false,updatable=false)
		private PatientPrescriptionHd patientPrescriptionHd;
		
	public Long getPrescriptionDtId() {
		return prescriptionDtId;
	}

	public void setPrescriptionDtId(Long prescriptionDtId) {
		this.prescriptionDtId = prescriptionDtId;
	}

	public String getBatchExpiryDate() {
		return batchExpiryDate;
	}

	public void setBatchExpiryDate(String batchExpiryDate) {
		this.batchExpiryDate = batchExpiryDate;
	}

	public String getDispStock() {
		return dispStock;
	}

	public void setDispStock(String dispStock) {
		this.dispStock = dispStock;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Long getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(Long frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getImmunizationFlag() {
		return immunizationFlag;
	}

	public void setImmunizationFlag(String immunizationFlag) {
		this.immunizationFlag = immunizationFlag;
	}

	public String getInjectionStatus() {
		return injectionStatus;
	}

	public void setInjectionStatus(String injectionStatus) {
		this.injectionStatus = injectionStatus;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemStopBy() {
		return itemStopBy;
	}

	public void setItemStopBy(Long itemStopBy) {
		this.itemStopBy = itemStopBy;
	}

	public String getNisFlag() {
		return nisFlag;
	}

	public void setNisFlag(String nisFlag) {
		this.nisFlag = nisFlag;
	}

	public String getNisNo() {
		return nisNo;
	}

	public void setNisNo(String nisNo) {
		this.nisNo = nisNo;
	}

	public Long getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getOtStage() {
		return otStage;
	}

	public void setOtStage(String otStage) {
		this.otStage = otStage;
	}

	public Long getPrescriptionHdId() {
		return prescriptionHdId;
	}

	public void setPrescriptionHdId(Long prescriptionHdId) {
		this.prescriptionHdId = prescriptionHdId;
	}

	public Long getQtyBalance() {
		return qtyBalance;
	}

	public void setQtyBalance(Long qtyBalance) {
		this.qtyBalance = qtyBalance;
	}

	public Long getQtyIssued() {
		return qtyIssued;
	}

	public void setQtyIssued(Long qtyIssued) {
		this.qtyIssued = qtyIssued;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getStoreStock() {
		return storeStock;
	}

	public void setStoreStock(String storeStock) {
		this.storeStock = storeStock;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	
}