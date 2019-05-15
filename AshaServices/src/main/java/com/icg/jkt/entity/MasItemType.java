package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MAS_ITEM_TYPE database table.
 * 
 */
@Entity
@Table(name="MAS_ITEM_TYPE")
@NamedQuery(name="MasItemType.findAll", query="SELECT m FROM MasItemType m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasItemType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAS_ITEM_TYPE_SEQ", sequenceName="MAS_ITEM_TYPE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_ITEM_TYPE_SEQ")
	@Column(name="ITEM_TYPE_ID")
	private long itemTypeId;

	@Column(name="ITEM_TYPE_CODE")
	private String itemTypeCode;

	@Column(name="ITEM_TYPE_NAME")
	private String itemTypeName;

	@Column(name="LAST_CHG_BY")
	private String lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	private String status;

	//bi-directional many-to-one association to MasStoreSection
		@OneToMany(mappedBy="masItemType")
		private List<MasStoreSection> masStoreSections;

	public MasItemType() {
	}

	public long getItemTypeId() {
		return this.itemTypeId;
	}

	public void setItemTypeId(long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemTypeCode() {
		return this.itemTypeCode;
	}

	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}

	public String getItemTypeName() {
		return this.itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getLastChgBy() {
		return this.lastChgBy;
	}

	public void setLastChgBy(String lastChgBy) {
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
	public List<MasStoreSection> getMasStoreSections() {
		return this.masStoreSections;
	}

	public void setMasStoreSections(List<MasStoreSection> masStoreSections) {
		this.masStoreSections = masStoreSections;
	}

	public MasStoreSection addMasStoreSection(MasStoreSection masStoreSection) {
		getMasStoreSections().add(masStoreSection);
		masStoreSection.setMasItemType(this);

		return masStoreSection;
	}

	public MasStoreSection removeMasStoreSection(MasStoreSection masStoreSection) {
		getMasStoreSections().remove(masStoreSection);
		masStoreSection.setMasItemType(null);

		return masStoreSection;
	}

}