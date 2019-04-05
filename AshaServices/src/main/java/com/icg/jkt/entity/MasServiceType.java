package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MAS_SERVICE_TYPE database table.
 * 
 */
@Entity
@Table(name="MAS_SERVICE_TYPE")
@NamedQuery(name="MasServiceType.findAll", query="SELECT m FROM MasServiceType m")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasServiceType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6039151152468567078L;

	@Id
	@SequenceGenerator(name="MAS_SERVICE_TYPE_SERVICETYPEID_GENERATOR", sequenceName="SERVICE_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAS_SERVICE_TYPE_SERVICETYPEID_GENERATOR")
	@Column(name="SERVICE_TYPE_ID")
	private long serviceTypeId;

	@Column(name="LAST_CHG_BY")
	private String lastChgBy;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHG_DATE")
	private Date lastChgDate;

	
	@Column(name="SERVICE_TYPE_CODE")
	private BigDecimal serviceTypeCode;

	@Column(name="SERVICE_TYPE_NAME")
	private String serviceTypeName;

	private String status;

	//bi-directional many-to-one association to MasEmployee
	@OneToMany(mappedBy="masServiceType")
	@JsonBackReference
	private List<MasEmployee> masEmployees;

	public MasServiceType() {
	}

	public long getServiceTypeId() {
		return this.serviceTypeId;
	}

	public void setServiceTypeId(long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getLastChgBy() {
		return this.lastChgBy;
	}

	public void setLastChgBy(String lastChgBy) {
		this.lastChgBy = lastChgBy;
	}

	public Date getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}


	public BigDecimal getServiceTypeCode() {
		return this.serviceTypeCode;
	}

	public void setServiceTypeCode(BigDecimal serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}

	public String getServiceTypeName() {
		return this.serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MasEmployee> getMasEmployees() {
		return this.masEmployees;
	}

	public void setMasEmployees(List<MasEmployee> masEmployees) {
		this.masEmployees = masEmployees;
	}

	public MasEmployee addMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().add(masEmployee);
		masEmployee.setMasServiceType(this);

		return masEmployee;
	}

	public MasEmployee removeMasEmployee(MasEmployee masEmployee) {
		getMasEmployees().remove(masEmployee);
		masEmployee.setMasServiceType(null);

		return masEmployee;
	}

}