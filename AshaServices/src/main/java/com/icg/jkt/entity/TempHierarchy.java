package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import oracle.jdbc.oracore.OracleType;


/**
 * Author : Kaushal Mishra
 */
@Entity
@Table(name="TEMP_HIERARCHY")
@Getter
@Setter
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name="birtProcedure11",procedureName="Asp_Hierarchy_Data_Show",
parameters={@StoredProcedureParameter(mode=ParameterMode.IN,name="tUserId",type=Long.class),@StoredProcedureParameter(mode=ParameterMode.IN,name="tHospitalId",type=Long.class)})})
public class TempHierarchy implements Serializable {
	

	private static final long serialVersionUID = 3253609914766418117L;
	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="CODE")
	private String code;
	
	
	
	@Column(name="NAME")
	private String name;

	public TempHierarchy() {
	}

}