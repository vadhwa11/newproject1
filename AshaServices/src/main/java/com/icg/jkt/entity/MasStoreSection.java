package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.registry.infomodel.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the MAS_STORE_SECTION database table.
 * 
 */
@Entity
@Table(name="MAS_STORE_SECTION")
@NamedQuery(name="MasStoreSection.findAll", query="SELECT m FROM MasStoreSection m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasStoreSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MAS_STORE_SECTION_SEQ", sequenceName="MAS_STORE_SECTION_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_STORE_SECTION_SEQ")
	@Column(name="SECTION_ID")
	private long sectionId;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="SECTION_CODE")
	private String sectionCode;

	@Column(name="SECTION_NAME")
	private String sectionName;

	private String status;

	//bi-directional many-to-one association to MasItemType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_TYPE_ID")
	private MasItemType masItemType;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users users;

	@OneToMany(mappedBy="masStoreSection")
	private List<MasItemClass> masItemClasses;
	
	public MasStoreSection() {
	}

	public long getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public String getSectionCode() {
		return this.sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MasItemType getMasItemType() {
		return this.masItemType;
	}

	public void setMasItemType(MasItemType masItemType) {
		this.masItemType = masItemType;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	public List<MasItemClass> getMasItemClasses() {
		return this.masItemClasses;
	}

	public void setMasItemClasses(List<MasItemClass> masItemClasses) {
		this.masItemClasses = masItemClasses;
	}

	public MasItemClass addMasItemClass(MasItemClass masItemClass) {
		getMasItemClasses().add(masItemClass);
		masItemClass.setMasStoreSection(this);

		return masItemClass;
	}

	public MasItemClass removeMasItemClass(MasItemClass masItemClass) {
		getMasItemClasses().remove(masItemClass);
		masItemClass.setMasStoreSection(null);

		return masItemClass;
	}

}