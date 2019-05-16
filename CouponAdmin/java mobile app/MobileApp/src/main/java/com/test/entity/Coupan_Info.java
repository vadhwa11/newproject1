package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "distributor_coupan")
public class Coupan_Info {
	@Id
	@Column(name = "Distributor_coupan_code_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Distributor_coupan_code_id;

	private String coupan_code;
	
	


	
	private Integer Distributor_id;

	

	public Integer getDistributor_id() {
		return Distributor_id;
	}

	public void setDistributor_id(Integer distributor_id) {
		Distributor_id = distributor_id;
	}

	public Integer getDistributor_coupan_code_id() {
		return Distributor_coupan_code_id;
	}

	public void setDistributor_coupan_code_id(Integer distributor_coupan_code_id) {
		Distributor_coupan_code_id = distributor_coupan_code_id;
	}

	public String getCoupan_code() {
		return coupan_code;
	}

	public void setCoupan_code(String coupan_code) {
		this.coupan_code = coupan_code;
	}

}
