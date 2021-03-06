package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SYNC_TABLE database table.
 * 
 */
@Entity
@Table(name="SYNC_TABLE")
@NamedQuery(name="SyncTable.findAll", query="SELECT s FROM SyncTable s")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SyncTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYNC_TABLE_SYNCTABLEID_GENERATOR", sequenceName="SYNC_TABLE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYNC_TABLE_SYNCTABLEID_GENERATOR")
	@Column(name="SYNC_TABLE_ID")
	private long syncTableId;

	@Column(name="ASHA_SYNC_FLAG")
	private long ashaSyncFlag;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="ORDER_NO")
	private long orderNo;

	@Column(name="SYNC_FILENAME")
	private String syncFilename;

	@Column(name="SYNC_LOCATION")
	private String syncLocation;

	@Column(name="TABLE_NAME")
	private String tableName;

	public SyncTable() {
	}

	public long getSyncTableId() {
		return this.syncTableId;
	}

	public void setSyncTableId(long syncTableId) {
		this.syncTableId = syncTableId;
	}

	public long getAshaSyncFlag() {
		return this.ashaSyncFlag;
	}

	public void setAshaSyncFlag(long ashaSyncFlag) {
		this.ashaSyncFlag = ashaSyncFlag;
	}

	public Timestamp getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Timestamp lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	public long getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public String getSyncFilename() {
		return this.syncFilename;
	}

	public void setSyncFilename(String syncFilename) {
		this.syncFilename = syncFilename;
	}

	public String getSyncLocation() {
		return this.syncLocation;
	}

	public void setSyncLocation(String syncLocation) {
		this.syncLocation = syncLocation;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}