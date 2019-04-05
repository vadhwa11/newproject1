package com.icg.jkt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icg.jkt.entity.DgMasInvestigation;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasEmpanelledHospital;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasFrequency;
import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.entity.MasStoreItem;
import com.icg.jkt.entity.OpdTemplate;

@Repository
public interface OpdMasterDao {

	List<MasDepartment> getDepartmentList();

	MasEmployee checkEmp(Long i);

	List<MasIcd> getIcd();

	List<DgMasInvestigation> getInvestigationList(Long mainChargeCode);

	List<MasStoreItem> getMasStoreItem();

	List<MasFrequency> getMasFrequency();

	List<OpdTemplate> getTemplateName();

	List<MasEmpanelledHospital> getEmpanelledHospital();

	List<MasDisposal> getMasDisposal();

}