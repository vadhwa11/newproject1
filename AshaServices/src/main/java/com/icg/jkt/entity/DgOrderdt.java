package com.icg.jkt.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the DG_ORDERDT database table.
 * 
 */
@Entity
@Table(name="DG_ORDER_DT")
@NamedQuery(name="DgOrderdt.findAll", query="SELECT d FROM DgOrderdt d")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DgOrderdt implements Serializable {

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6182435377667966821L;

	@Id
	@SequenceGenerator(name="DG_ORDERDT_ORDERDTID_GENERATOR", sequenceName="DG_ORDERDT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DG_ORDERDT_ORDERDTID_GENERATOR")
	@Column(name="ORDERDT_ID")
	private Long orderdtId;

	@Column(name="INVESTIGATION_ID")
	private Long investigationId;

	@Column(name="LAB_MARK")
	private String labMark;

	@Column(name="LAST_CHG_BY")
	private Long lastChgBy;

	@Column(name="LAST_CHG_DATE")
	private Timestamp lastChgDate;

	@Column(name="ORDER_STATUS")
	private String orderStatus;

	@Column(name="ORDERHD_ID")
	private Long orderhdId;
	
	@Column(name="ORDER_DATE")
	private Date orderDate;

	private String urgent;

	public Long getOrderdtId() {
		return orderdtId;
	}

	public void setOrderdtId(Long orderdtId) {
		this.orderdtId = orderdtId;
	}

	public Long getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(Long investigationId) {
		this.investigationId = investigationId;
	}

	public String getLabMark() {
		return labMark;
	}

	public void setLabMark(String labMark) {
		this.labMark = labMark;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getOrderhdId() {
		return orderhdId;
	}

	public void setOrderhdId(Long orderhdId) {
		this.orderhdId = orderhdId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getUrgent() {
		return urgent;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	} 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDERHD_ID",nullable=false,insertable=false,updatable=false)
	private DgOrderhd dgOrderhd;
	
	public DgOrderhd getDgOrderhd() {
		return dgOrderhd;
	}

	public void setDgOrderhd(DgOrderhd dgOrderhd) {
		this.dgOrderhd = dgOrderhd;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "INVESTIGATION_ID", referencedColumnName = "INVESTIGATION_ID", insertable = false, updatable = false)
	private List<DgMasInvestigation> dgMasInvestigations;

	public List<DgMasInvestigation> getDgMasInvestigations() {
		return dgMasInvestigations;
	}

	public void setDgMasInvestigations(List<DgMasInvestigation> dgMasInvestigations) {
		this.dgMasInvestigations = dgMasInvestigations;
	}
	
}