package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MAS_STORE_UNIT database table.
 * 
 */
@Entity
@Table(name="MAS_STORE_UNIT")
@NamedQuery(name="MasStoreUnit.findAll", query="SELECT m FROM MasStoreUnit m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasStoreUnit implements Serializable {
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 3880545462109463334L;

	@Id
	@SequenceGenerator(name="MAS_STORE_UNIT_SEQ", sequenceName="MAS_STORE_UNIT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_STORE_UNIT_SEQ")
	@Column(name="STORE_UNIT_ID")
	private long storeUnitId;

	@Column(name="LAST_CHG_BY")
	private Long lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	private String status;

	@Column(name="STORE_UNIT_NAME")
	private String storeUnitName;
	
	@OneToMany(mappedBy="masStoreUnit", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<MasStoreItem> masStoreItem;

	public MasStoreUnit() {
	}

	public long getStoreUnitId() {
		return this.storeUnitId;
	}

	public void setStoreUnitId(long storeUnitId) {
		this.storeUnitId = storeUnitId;
	}

	public Long getLastChgBy() {
		return this.lastChgBy;
	}

	public void setLastChgBy(Long lastChgBy) {
		this.lastChgBy = lastChgBy;
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

	public String getStoreUnitName() {
		return this.storeUnitName;
	}

	public void setStoreUnitName(String storeUnitName) {
		this.storeUnitName = storeUnitName;
	}
	
	public List<MasStoreItem> getMasStoreItem() {
		return masStoreItem;
	}

	public void setMasStoreItem(List<MasStoreItem> masStoreItem) {
		this.masStoreItem = masStoreItem;
	}
	
	

}