/**
 * Author : Kaushal Mishra
 */


package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import oracle.jdbc.oracore.OracleType;


@Entity
@Table(name="HIERARCHY_DATA")
//@Getter
//@Setter
/*
 * @NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name="birtProcedure"
 * ,procedureName="Asp_Hierarchy_Data_Show",
 * parameters={@StoredProcedureParameter(mode=ParameterMode.IN,name="tUserId",
 * type=Long.class),@StoredProcedureParameter(mode=ParameterMode.IN,name=
 * "tHospitalId",type=Long.class)})})
 */
public class HierarchyData implements Serializable {
	

	private static final long serialVersionUID = 3253609914766418117L;
	
	@Id
	@SequenceGenerator(name="HIERARCHY_DATA_ID_GENERATOR", sequenceName="HIERARCHY_DATA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HIERARCHY_DATA_ID_GENERATOR")
	@Column(name="ID")
	private long Id;
	
	@Column(name="HOSPITAL_ID")
	private long hospitalId;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="USER_HOSPITAL_ID")
	private Long userHospitalId;

	@Column(name="USER_ID")
	private Long userId;
	
	public HierarchyData() {
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserHospitalId() {
		return userHospitalId;
	}

	public void setUserHospitalId(Long userHospitalId) {
		this.userHospitalId = userHospitalId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}