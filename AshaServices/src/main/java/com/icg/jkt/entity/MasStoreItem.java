package com.icg.jkt.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;



/**
 * The persistent class for the MAS_STORE_ITEM database table.
 * 
 */
@Entity
@Table(name="MAS_STORE_ITEM")
@NamedQuery(name="MasStoreItem.findAll", query="SELECT m FROM MasStoreItem m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasStoreItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3865545846568127047L;

	@Id
	@SequenceGenerator(name="MAS_STORE_ITEM_ITEMID_GENERATOR", sequenceName="MAS_STORE_ITEM_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_STORE_ITEM_ITEMID_GENERATOR")
	@Column(name="ITEM_ID")
	private long itemId;

	@Column(name="DISP_UNIT_ID")
	private Long dispUnitId;

	@Column(name="DISP_UNIT_QTY")
	private Long dispUnitQty;

	private String expiry;

	@Column(name="GROUP_ID")
	private Long groupId;

	@Column(name="HOSPITAL_ID")
	private Long hospitalId;

	@Column(name="ITEM_CLASS_ID")
	private Long itemClassId;

	@Column(name="ITEM_TYPE_ID")
	private Long itemTypeId;

	@Column(name="ITEM_UNIT_ID")
	private Long itemUnitId;

	@Column(name="LAST_CHG_BY")
	private Long lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	private String nomenclature;

	@Column(name="PVMS_NO")
	private String pvmsNo;

	@Column(name="ROL_D")
	private String rolD;

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public Long getDispUnitId() {
		return dispUnitId;
	}

	public void setDispUnitId(Long dispUnitId) {
		this.dispUnitId = dispUnitId;
	}

	public Long getDispUnitQty() {
		return dispUnitQty;
	}

	public void setDispUnitQty(Long dispUnitQty) {
		this.dispUnitQty = dispUnitQty;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(Long itemClassId) {
		this.itemClassId = itemClassId;
	}

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public Long getItemUnitId() {
		return itemUnitId;
	}

	public void setItemUnitId(Long itemUnitId) {
		this.itemUnitId = itemUnitId;
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

	public String getNomenclature() {
		return nomenclature;
	}

	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}

	public String getPvmsNo() {
		return pvmsNo;
	}

	public void setPvmsNo(String pvmsNo) {
		this.pvmsNo = pvmsNo;
	}

	public String getRolD() {
		return rolD;
	}

	public void setRolD(String rolD) {
		this.rolD = rolD;
	}

	public String getRolS() {
		return rolS;
	}

	public void setRolS(String rolS) {
		this.rolS = rolS;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public MasStoreUnit getMasStoreUnit() {
		return masStoreUnit;
	}

	public void setMasStoreUnit(MasStoreUnit masStoreUnit) {
		this.masStoreUnit = masStoreUnit;
	}

	@Column(name="ROL_S")
	private String rolS;

	@Column(name="SECTION_ID")
	private Long sectionId;

	private String status;

	private String temperature;

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name = "DISP_UNIT_ID",nullable=false,insertable=false,updatable=false)
	private MasStoreUnit masStoreUnit;
	
}