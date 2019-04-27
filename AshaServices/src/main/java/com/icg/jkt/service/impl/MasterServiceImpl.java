package com.icg.jkt.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.MasterDao;
import com.icg.jkt.entity.MasAdministrativeSex;
import com.icg.jkt.entity.MasAppointmentType;
import com.icg.jkt.entity.MasBloodGroup;
import com.icg.jkt.entity.MasCommand;
import com.icg.jkt.entity.MasCommandType;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasDepartmentType;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasEmpanelledHospital;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasEmployeeCategory;
import com.icg.jkt.entity.MasFrequency;
import com.icg.jkt.entity.MasHospital;
import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.entity.MasIdealWeight;
import com.icg.jkt.entity.MasMainChargecode;
import com.icg.jkt.entity.MasMaritalStatus;
import com.icg.jkt.entity.MasMedicalCategory;
import com.icg.jkt.entity.MasNursingCare;
import com.icg.jkt.entity.MasRange;
import com.icg.jkt.entity.MasRank;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.MasReligion;
import com.icg.jkt.entity.MasRole;
import com.icg.jkt.entity.MasSample;
import com.icg.jkt.entity.MasServiceType;
import com.icg.jkt.entity.MasState;
import com.icg.jkt.entity.MasStoreUnit;
import com.icg.jkt.entity.MasTrade;
import com.icg.jkt.entity.MasUOM;
import com.icg.jkt.entity.MasUnit;
import com.icg.jkt.entity.MasUnitType;
import com.icg.jkt.entity.Users;
import com.icg.jkt.service.MasterService;

@Repository
public class MasterServiceImpl implements MasterService {

	@Autowired
	MasterDao md;

	/////////////////////// MasDepartmrent get Method /////////////////////////
	@Override
	public String departmentList(HashMap<String, String> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			if (jsondata.get("EMPLOYEE_ID") == null || jsondata.get("EMPLOYEE_ID").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID as a  key or value or it is null\"}";
			} else {
				MasEmployee checkEmp = md.checkEmp(Long.parseLong(jsondata.get("EMPLOYEE_ID").toString()));
				if (checkEmp != null) {
					List<MasDepartment> mst_depart = md.getDepartmentList();
					if (mst_depart.size() == 0) {
						return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
					} else {
						json.put("departmentList", checkEmp);
						json.put("departmentList", mst_depart);
						json.put("msg", "department List  get  sucessfull... ");
						json.put("status", "1");

					}

				} else {
					return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";

				}

				return json.toString();
			}
		} finally {
			System.out.println("Exception Occured");
		}
	}

	//////////////////////// Get Master ICD Business Logic //////////////////////

	@Override
	public String getICD(HashMap<String, String> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"json is not contain employeeId as a  key or value or it is null\"}";
			} else {
				MasEmployee checkEmp = md.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
				if (checkEmp != null) {
					List<MasIcd> mst_icd = md.getIcd();
					if (mst_icd.size() == 0) {
						return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
					} else {
						json.put("ICDList", checkEmp);
						json.put("ICDList", mst_icd);
						json.put("msg", "ICD List  get  sucessfull... ");
						json.put("status", "1");

					}

				} else {
					return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";

				}

				return json.toString();
			}
		} finally {
			System.out.println("Exception Occured");
		}
	}

	@Override
	public String getAllStates(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = new JSONObject();
		List<MasState> sList = new ArrayList<MasState>();
		Map<String, List<MasState>> stateMap = md.getAllStates(jsonObject);
		if (stateMap.get("stateList") != null) {
			sList = stateMap.get("stateList");

			if (sList != null && sList.size() > 0) {
				json.put("data", sList);
				json.put("count", sList.size());
				json.put("status", 1);
			} else {
				json.put("data", sList);
				json.put("count", sList.size());
				json.put("msg", "State Not Found");
				json.put("status", 0);
			}

		} else {
			json.put("msg", "Data Not Found... ");
			json.put("status", 0);
		}

		return json.toString();

	}

	@Override
	public String addCommand(JSONObject json, HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObj = new JSONObject();
		if (json != null) {

			MasCommand masCommand = new MasCommand();

			masCommand.setCommandCode(json.get("commandCode").toString().toUpperCase());
			masCommand.setCommandName(json.get("commandName").toString().toUpperCase());

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masCommand.setLastChgDate(date);

			Users users = new Users();
			users.setUserId(new Long(1));
			masCommand.setUser(users);
			masCommand.setStatus("Y");

			MasCommandType commandType = new MasCommandType();
			commandType.setCommandtypeId(Long.parseLong(json.get("commandtypeId").toString()));

			masCommand.setMasCommandType(commandType);

			List<MasCommand> masCmd = md.validateMasCommand(masCommand.getCommandCode().toString(),
					masCommand.getCommandName().toString());
			if (masCmd.size() != 0) {
				if (masCmd != null && masCmd.size() > 0) {
					if (masCmd.get(0).getCommandCode().equalsIgnoreCase(json.get("commandCode").toString())) {

						return "{\"status\":\"2\",\"msg\":\"Command Code is already Existing.\"}";
					}
					if (masCmd.get(0).getCommandName().equalsIgnoreCase(json.get("commandName").toString())) {

						return "{\"status\":\"2\",\"msg\":\"Command Name is already Existing.\"}";
					}
				}
			} else {
				String masCmdObj = md.addMasCommand(masCommand);

				if (masCmdObj != null && masCmdObj.equalsIgnoreCase("200")) {
					jsonObj.put("status", 1);
					jsonObj.put("msg", "Record Added Successfully");

				} else if (masCmdObj != null && masCmdObj.equalsIgnoreCase("403")) {
					jsonObj.put("status", 0);
					jsonObj.put("msg", "You are not authorized person!!!");

				} else {
					jsonObj.put("msg", masCmdObj);
					jsonObj.put("status", 0);
				}
			}

		} else {
			jsonObj.put("msg", "Cannot Contains Any Data!!!");
			jsonObj.put("status", 0);
		}

		return jsonObj.toString();
	}

	@Override
	public String getCommand(HashMap<String, Object> command, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		if (command.get("commandName") == null && command.get("commandName").toString().trim().equalsIgnoreCase("")) {

			return "{\"status\":\"0\",\"msg\":\"Command Name is not available !!!\"}";
		} else {
			MasCommand chkCommand = md.chkCommand(command.get("commandName").toString());
			if (chkCommand != null) {
				List<MasCommand> masCmdLst = md.getCommand(command.get("commandName").toString());
				if (masCmdLst != null && masCmdLst.size() > 0) {

					jsonObject.put("masCmdLst", masCmdLst);
					jsonObject.put("msg", "List of command successfully...");
					jsonObject.put("status", 1);
				} else {
					return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
				}
			} else {
				return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			}
		}

		return jsonObject.toString();
	}

	@Override
	public String getAllCommand(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasCommand> cList = new ArrayList<MasCommand>();

		Map<String, List<MasCommand>> cmdMap = md.getAllCommand(jsonObj);
		List cListObj = new ArrayList();
		List totalMatches = new ArrayList();
		if (cmdMap.get("masCmdList") != null) {
			cList = cmdMap.get("masCmdList");
			totalMatches = cmdMap.get("totalMatches");

			for (MasCommand mCommand : cList) {
				HashMap<String, Object> mapObj = new HashMap<String, Object>();

				mapObj.put("cmdId", mCommand.getCommandId());
				mapObj.put("cmdCode", mCommand.getCommandCode());
				mapObj.put("cmdName", mCommand.getCommandName());
				mapObj.put("status", mCommand.getStatus());
				mapObj.put("cmdTypeId", mCommand.getMasCommandType().getCommandtypeId());
				if (mCommand.getStatus().equalsIgnoreCase("Y"))
					mapObj.put("cmdType", mCommand.getMasCommandType().getCommandtypeName());
				else
					mapObj.put("cmdType", "*Parent-Inactivated-" + mCommand.getMasCommandType().getCommandtypeName());
				cListObj.add(mapObj);
			}

			if (cListObj != null && cListObj.size() > 0) {
				json.put("data", cListObj);

				json.put("count", totalMatches.size());
				json.put("status", 1);
			} else {
				json.put("data", cListObj);
				json.put("count", totalMatches.size());
				json.put("msg", "Data not found");
				json.put("status", 0);
			}
		}

		return json.toString();
	}

	@Override
	public String updateCommand(HashMap<String, Object> command, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (command.get("commandCode") != null && !command.get("commandCode").toString().equalsIgnoreCase("")) {

			String cmdUpdate = md.updateCommand(Long.parseLong(command.get("commandId").toString()),
					command.get("commandCode").toString().toString(), command.get("commandName").toString(),
					Long.parseLong(command.get("commandtypeId").toString()));

			if (cmdUpdate != null && !cmdUpdate.equalsIgnoreCase("")) {
				json.put("cmdUpdate", cmdUpdate);
				json.put("msg", "Record Updated Successfully");
				json.put("status", 1);
			} else if (cmdUpdate == null && cmdUpdate.equalsIgnoreCase("")) {
				json.put("msg", "Record Not Updated!!!");
				json.put("status", 0);
			}

			else {
				json.put("msg", "Command Code is not available");
				json.put("status", 0);

			}

		} else {
			json.put("msg", "Command Code is not available");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String statusCommand(HashMap<String, Object> command, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (command.get("commandCode") != null && !command.get("commandCode").toString().equalsIgnoreCase("")) {
			MasCommand chkCommand = md.chkCommand(command.get("commandCode").toString());

			if (chkCommand != null) {

				String masCmdStatus = md.updateCommandStatus(Long.parseLong(command.get("commandId").toString()),
						command.get("commandCode").toString(), command.get("status").toString());

				if (masCmdStatus != null && masCmdStatus.equalsIgnoreCase("200")) {
					json.put("masCmdStatus", masCmdStatus);
					json.put("msg", "Status Updated Successfully");
					json.put("status", 1);
				} else {
					// json.put("masCmdStatus", masCmdStatus);
					json.put("msg", "Status Not Updated Successfully");
					json.put("status", 0);
				}
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"Command Code is not available\"}";
		}

		return json.toString();
	}

	/********************************
	 * UNIT MASTER
	 ***********************************************/
	@Override
	public String getAllUnit(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasUnit> uList = new ArrayList<MasUnit>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasUnit>> mapUnit = md.getAllUnit(jsondata);
			List totalMatches = new ArrayList();
			if (mapUnit.get("masUnitList") != null) {
				uList = mapUnit.get("masUnitList");
				totalMatches = mapUnit.get("totalMatches");
				for (MasUnit unit : uList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (unit != null) {
						mapObj.put("unitId", unit.getUnitId());
						mapObj.put("unitName", unit.getUnitName());
						mapObj.put("status", unit.getStatus());
						mapObj.put("unitAddress", unit.getUnitAddress());
						mapObj.put("unitType", unit.getMasUnittype().getUnitTypeName());
						mapObj.put("cmdId", unit.getMasCommand().getCommandId());
						mapObj.put("commandName", unit.getMasCommand().getCommandName());
						mapObj.put("unitTypeId", unit.getMasUnittype().getUnitTypeId());
						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addUnits(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);
			// Long unitType = new Long(1);
			String unitAddress = "delhi";

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

			MasUnit masUnit = new MasUnit();
			MasCommand command = new MasCommand();

			masUnit.setUnitName(jsondata.get("unitName").toString().toUpperCase());
			masUnit.setUnitAddress(unitAddress);
			command.setCommandId(Long.parseLong(jsondata.get("cmdId").toString()));

			masUnit.setMasCommand(command);

			Users users = new Users();
			users.setUserId(new Long(1));

			masUnit.setUser(users);
			masUnit.setStatus(status.toUpperCase());
			masUnit.setLastChgDate(date);

			MasUnitType unitType = new MasUnitType();

			unitType.setUnitTypeId(Long.parseLong(jsondata.get("unitTypId").toString()));
			masUnit.setMasUnittype(unitType);

			List<MasUnit> chkUnitLst = md.validateUnit(masUnit.getUnitName());
			if (chkUnitLst != null && chkUnitLst.size() > 0) {
				if (chkUnitLst.get(0).getUnitName().equalsIgnoreCase(jsondata.get("unitName").toString())) {

					return "{\"status\":\"2\",\"msg\":\"Unit Name is already Existing.\"}";
				}

			} else {
				String addUnit = md.addMasUnit(masUnit);
				if (addUnit != null && addUnit.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Record Added Successfully");
				} else {
					json.put("status", 0);
					json.put("msg", "Record Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String getCommandList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasCommand> cList = md.getCommandList();
		if (cList != null && cList.size() > 0) {
			json.put("data", cList);
			json.put("count", cList.size());
			json.put("status", 1);
		} else {
			json.put("data", cList);
			json.put("count", cList.size());
			json.put("msg", "No Record Found");
			json.put("status", 0);

		}
		return json.toString();
	}

	@Override
	public String getUnitTypeList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();

		List<MasUnitType> utList = md.getUnitTypeList();
		if (utList != null && utList.size() > 0) {
			json.put("data", utList);
			json.put("count", utList.size());
			json.put("status", 1);
		} else {
			json.put("data", utList);
			json.put("count", utList.size());
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateUnit(HashMap<String, Object> unitPayload, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (unitPayload != null) {

			if (unitPayload.get("unitName") != null && !unitPayload.get("unitName").toString().equalsIgnoreCase("")
					|| unitPayload.get("cmdId").toString() != null || unitPayload.get("unitTypId").toString() != null) {
				String updateUnit = md.updateUnit(Long.parseLong(unitPayload.get("unitId").toString()),
						unitPayload.get("unitName").toString(), Long.parseLong(unitPayload.get("cmdId").toString()),
						unitPayload.get("unitAddress").toString(),
						Long.parseLong(unitPayload.get("unitTypeId").toString()));
				if (updateUnit != null && !updateUnit.equalsIgnoreCase("")) {
					json.put("updateUnit", updateUnit);
					json.put("msg", "Record Updated Successfully");
					json.put("status", 1);
				} else {
					json.put("updateUnit", updateUnit);
					json.put("msg", "Record Not Updated");
					json.put("status", 0);
				}

			} else {
				json.put("msg", "Invalid Input Paramter");
				json.put("status", 0);
			}
		} else {
			json.put("msg", "Data Not Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String getCommandTypeList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		List<MasCommandType> cmdTypList = md.getCommandTypeList();
		if (cmdTypList != null && cmdTypList.size() > 0) {

			jsonObj.put("data", cmdTypList);
			jsonObj.put("count", cmdTypList.size());
			jsonObj.put("status", 1);
		} else {
			jsonObj.put("data", cmdTypList);
			jsonObj.put("count", cmdTypList.size());
			jsonObj.put("msg", "No Record Found");
			jsonObj.put("status", 0);
		}
		return jsonObj.toString();
	}

	/********************************************
	 * MAS HOSPITAL
	 *******************************************************/
	@Override
	public String addMasHospital(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			MasHospital hospitalObj = new MasHospital();

			hospitalObj.setHospitalCode(jsonObject.get("hospitalCode").toString().toUpperCase());
			hospitalObj.setHospitalName(jsonObject.get("hospitalName").toString().toUpperCase());
			hospitalObj.setStatus("Y");

			Users user = new Users();
			user.setUserId(new Long(1));
			hospitalObj.setUser(user);

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			hospitalObj.setLastChgDate(date);

			MasUnit masUnit = new MasUnit();
			masUnit.setUnitId(Long.parseLong(jsonObject.get("unitId").toString()));

			hospitalObj.setMasUnit(masUnit);

			List<MasHospital> hospitalList = md.validateMasHospital(hospitalObj.getHospitalCode(),
					hospitalObj.getHospitalName());
			if (hospitalList.size() != 0) {
				if (hospitalList != null && hospitalList.size() > 0) {

					if (hospitalList.get(0).getHospitalCode()
							.equalsIgnoreCase(jsonObject.get("hospitalCode").toString())) {

						return "{\"status\":\"2\",\"msg\":\"HospitalCode is already Existing.\"}";
					}

					if (hospitalList.get(0).getHospitalName()
							.equalsIgnoreCase(jsonObject.get("hospitalName").toString())) {

						return "{\"status\":\"2\",\"msg\":\"HospitalName is already Existing.\"}";
					}
				}
			} else {
				String addHospital = md.addMasHospital(hospitalObj);

				if (addHospital != null && addHospital.equalsIgnoreCase("200")) {
					json.put("msg", "Record Added Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Data has been Not Added");
					json.put("status", 0);
				}
			}

		} else {
			json.put("msg", "Data Not Found Error");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String getUnitNameList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();

		List<MasUnit> mUnitList = md.getUnitNameList();
		if (mUnitList != null && mUnitList.size() > 0) {

			json.put("data", mUnitList);
			json.put("count", mUnitList.size());
			json.put("status", 1);
		} else {
			json.put("status", 0);
			json.put("count", mUnitList.size());
			json.put("msg", "No Record Found");
		}

		return json.toString();
	}

	@Override
	public String getAllHospital(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {
			List<MasHospital> mHospitalList = new ArrayList<MasHospital>();
			List totalMatches = new ArrayList();
			List hList = new ArrayList();

			Map<String, List<MasHospital>> map = md.getAllHospital(jsonObject);
			if (map.get("mHospitalList") != null) {
				mHospitalList = map.get("mHospitalList");
				totalMatches = map.get("totalMatches");

				for (MasHospital hospital : mHospitalList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (hospital != null) {
						mapObj.put("hospitalId", hospital.getHospitalId());
						mapObj.put("hospitalCode", hospital.getHospitalCode());
						mapObj.put("hospitalName", hospital.getHospitalName());
						mapObj.put("status", hospital.getStatus());
						mapObj.put("unitName", hospital.getMasUnit().getUnitName());
						mapObj.put("unitId", hospital.getMasUnit().getUnitId());
						hList.add(mapObj);
					}
				}

				if (hList != null && hList.size() > 0) {
					json.put("data", hList);
					json.put("count", totalMatches.size());
					json.put("status", 1);
				} else {
					json.put("data", hList);
					json.put("count", totalMatches.size());
					json.put("msg", "Data not found");
					json.put("status", 0);
				}

			} else {
				json.put("status", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("status", 0);
			json.put("msg", "Record Not Found");
		}

		return json.toString();
	}

	@Override
	public String updateHospitalMasterStatus(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json = new JSONObject();

		if (jsonObject != null) {

			if (jsonObject.get("hospitalCode").toString() != null
					&& !jsonObject.get("hospitalCode").toString().equalsIgnoreCase("")) {
				MasHospital mHospital = md.checkMasHospital(jsonObject.get("hospitalCode").toString());

				if (mHospital != null) {
					String masHospStatus = md.updateHospitalMasterStatus(
							Long.parseLong(jsonObject.get("hospitalId").toString()),
							jsonObject.get("hospitalCode").toString(), jsonObject.get("status").toString());

					if (masHospStatus != null && masHospStatus.equalsIgnoreCase("200")) {
						json.put("masHospStatus", masHospStatus);
						json.put("msg", "Status Updated Successfully");
						json.put("status", 1);
					} else {
						json.put("msg", "Status Not Updated");
						json.put("status", 0);
					}
				} else {
					json.put("msg", "Data Not Found");
				}

			}
		}

		return json.toString();
	}

	@Override
	public String updateHospitalDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("hospitalCode").toString() != null
					&& !jsonObject.get("hospitalCode").toString().equalsIgnoreCase("")) {
				/*
				 * MasHospital mHospital =
				 * md.checkMasHospital(jsonObject.get("hospitalCode").toString());
				 * System.out.println("mHospital :: "+mHospital); if(mHospital!=null) {
				 */
				String updateHospitalDetail = md.updateHospitalDetails(
						Long.parseLong(jsonObject.get("hospitalId").toString()),
						jsonObject.get("hospitalCode").toString(), jsonObject.get("hospitalName").toString(),
						Long.parseLong(jsonObject.get("unitId").toString()));

				if (updateHospitalDetail != null && updateHospitalDetail.equalsIgnoreCase("200")) {
					json.put("updateHospitalDetail", updateHospitalDetail);
					json.put("msg", "Record Updated Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Data has not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}

		return json.toString();
	}

	/************************************************
	 * MAS RELATION
	 *****************************************************************/
	@Override
	public String getAllRelation(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {
			List<MasRelation> mRelationList = new ArrayList<MasRelation>();
			List totalMatches = new ArrayList();
			List relList = new ArrayList();

			Map<String, List<MasRelation>> map = md.getAllRelation(jsonObject);
			if (map.get("mRelationList") != null) {
				mRelationList = map.get("mRelationList");
				totalMatches = map.get("totalMatches");

				for (MasRelation relation : mRelationList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (relation != null) {
						mapObj.put("relationId", relation.getRelationId());
						mapObj.put("relationCode", relation.getRelationCode());
						mapObj.put("newRelationCode", relation.getNewRelationCode());
						mapObj.put("relationName", relation.getRelationName());
						mapObj.put("newRelationName", relation.getNewRelationName());
						mapObj.put("status", relation.getStatus());

						relList.add(mapObj);
					}
				}

				if (relList != null && relList.size() > 0) {
					json.put("data", relList);
					json.put("count", totalMatches.size());
					json.put("status", 1);
				} else {
					json.put("data", relList);
					json.put("count", totalMatches.size());
					json.put("msg", "Data not found");
					json.put("status", 0);
				}

			} else {
				json.put("status", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("status", 0);
			json.put("msg", "Record Not Found");
		}

		return json.toString();
	}

	@Override
	public String updateRelationDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (Long.parseLong(jsonObject.get("relationCode").toString()) != 0
					&& Long.parseLong(jsonObject.get("relationCode").toString()) != -1) {

				String updateRelationDetail = md.updateRelationDetails(
						Long.parseLong(jsonObject.get("relationId").toString()),
						Long.parseLong(jsonObject.get("relationCode").toString()),
						jsonObject.get("relationName").toString(),
						Long.parseLong(jsonObject.get("newRelationCode").toString()),
						jsonObject.get("newRelationName").toString());

				if (updateRelationDetail != null && updateRelationDetail.equalsIgnoreCase("200")) {
					json.put("updateRelationDetail", updateRelationDetail);
					json.put("msg", "Record Updated Successfully.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateRelationStatus(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (jsonObject != null) {

			if (Long.parseLong(jsonObject.get("relationCode").toString()) != 0
					&& Long.parseLong(jsonObject.get("relationCode").toString()) != -1) {
				MasRelation mRelation = md.checkMasRelation(Long.parseLong(jsonObject.get("relationCode").toString()));

				if (mRelation != null) {
					String masRelStatus = md.updateRelationStatus(
							Long.parseLong(jsonObject.get("relationId").toString()),
							Long.parseLong(jsonObject.get("relationCode").toString()),
							jsonObject.get("status").toString());

					if (masRelStatus != null && masRelStatus.equalsIgnoreCase("200")) {
						json.put("masRelStatus", masRelStatus);
						json.put("msg", "Status Updated Successfully");
						json.put("status", 1);
					} else {
						json.put("msg", "Status Not Updated");
						json.put("status", 0);
					}
				} else {
					json.put("msg", "Data Not Found");
				}

			}
		}

		return json.toString();
	}

	@Override
	public String addRelation(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasRelation masRelation = new MasRelation();
			masRelation.setRelationCode(Long.parseLong(jsonObject.get("relationCode").toString()));
			masRelation.setRelationName(jsonObject.get("relationName").toString().toUpperCase());
			masRelation.setNewRelationCode(Long.parseLong(jsonObject.get("newRelationCode").toString()));
			masRelation.setNewRelationName(jsonObject.get("newRelationName").toString().toUpperCase());
			masRelation.setStatus("Y");
			Users user = new Users();
			user.setUserId(new Long(1));
			masRelation.setUser(user);
			masRelation.setLastChgDate(date);

			List<MasRelation> checkRel = md.validateRelation(Long.parseLong(jsonObject.get("relationCode").toString()),
					jsonObject.get("relationName").toString());

			if (checkRel != null && checkRel.size() > 0) {
				if (checkRel.get(0).getRelationCode()
						.equals(Long.parseLong(jsonObject.get("relationCode").toString()))) {

					json.put("status", 2);
					json.put("msg", "Relation Code is already Existing");
				}

				if (checkRel.get(0).getRelationName().equalsIgnoreCase(jsonObject.get("relationName").toString())) {

					json.put("status", 2);
					json.put("msg", "Relation Name is already Existing");
				}

			} else {

				String addRelationObj = md.addRelation(masRelation);

				if (addRelationObj != null && addRelationObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Record Added Successfully");
				} else {
					json.put("status", 0);
					json.put("msg", "Record Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	/****************************************************
	 * MAS DISPOSAL
	 *********************************************************************/
	@Override
	public String addDisposal(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			Long lastChgBy = new Long(1);
			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasDisposal masDisposal = new MasDisposal();
			masDisposal.setDisposalCode(jsonObject.get("disposalCode").toString().toUpperCase());
			masDisposal.setDisposalName(jsonObject.get("disposalName").toString().toUpperCase());
			masDisposal.setStatus("Y");

			Users users = new Users();
			users.setUserId(new Long(1));
			masDisposal.setUsers(users);

			masDisposal.setLastChgDate(date);
			masDisposal.setDisposalType(jsonObject.get("disposalType").toString().toUpperCase());

			List<MasDisposal> checkDispo = md.validateDisposal(masDisposal.getDisposalCode(),
					masDisposal.getDisposalName());

			if (checkDispo != null && checkDispo.size() > 0) {
				if (checkDispo.get(0).getDisposalCode().equalsIgnoreCase(jsonObject.get("disposalCode").toString())) {

					json.put("status", 2);
					json.put("msg", "Disposal Code is already Existing");
				}

				if (checkDispo.get(0).getDisposalName().equalsIgnoreCase(jsonObject.get("disposalName").toString())) {

					json.put("status", 2);
					json.put("msg", "Disposal Name is already Existing");
				}

			} else {

				String addDisposalObj = md.addDisposal(masDisposal);

				if (addDisposalObj != null && addDisposalObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String getAllDisposal(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {
			List<MasDisposal> mdisposalList = new ArrayList<MasDisposal>();
			List totalMatches = new ArrayList();
			List dispoList = new ArrayList();

			Map<String, List<MasDisposal>> map = md.getAllDisposal(jsonObject);
			if (map.get("mdisposalList") != null) {
				mdisposalList = map.get("mdisposalList");
				totalMatches = map.get("totalMatches");

				for (MasDisposal disposal : mdisposalList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (disposal != null) {
						mapObj.put("disposalId", disposal.getDisposalId());
						mapObj.put("disposalCode", disposal.getDisposalCode());
						mapObj.put("disposalName", disposal.getDisposalName());
						mapObj.put("disposalType", disposal.getDisposalType());
						mapObj.put("status", disposal.getStatus());

						dispoList.add(mapObj);
					}
				}

				if (dispoList != null && dispoList.size() > 0) {
					json.put("data", dispoList);
					json.put("count", totalMatches.size());
					json.put("status", 1);
				} else {
					json.put("data", dispoList);
					json.put("count", totalMatches.size());
					json.put("msg", "Data not found");
					json.put("status", 0);
				}

			} else {
				json.put("status", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("status", 0);
			json.put("msg", "Record Not Found");
		}

		return json.toString();
	}

	@Override
	public String updateDisposalDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("disposalCode").toString() != null
					&& !jsonObject.get("disposalCode").toString().trim().equalsIgnoreCase("")) {

				String updateDisposalDetail = md.updateDisposalDetails(
						Long.parseLong(jsonObject.get("disposalId").toString()),
						jsonObject.get("disposalCode").toString(), jsonObject.get("disposalName").toString(),
						jsonObject.get("disposalType").toString());

				if (updateDisposalDetail != null && updateDisposalDetail.equalsIgnoreCase("200")) {
					json.put("updateDisposalDetail", updateDisposalDetail);
					json.put("msg", "Record Updated Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateDisposalStatus(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (jsonObject != null) {

			if (jsonObject.get("disposalCode").toString() != null
					&& !jsonObject.get("disposalCode").toString().trim().equalsIgnoreCase("")) {

				MasDisposal mDisposal = md.checkMasDisposal(jsonObject.get("disposalCode").toString());

				if (mDisposal != null) {
					String masDispoStatus = md.updateDisposalStatus(
							Long.parseLong(jsonObject.get("disposalId").toString()),
							jsonObject.get("disposalCode").toString(), jsonObject.get("status").toString());

					if (masDispoStatus != null && masDispoStatus.equalsIgnoreCase("200")) {
						json.put("masDispoStatus", masDispoStatus);
						json.put("msg", "Status Updated Suceessfully");
						json.put("status", 1);
					} else {
						json.put("masDispoStatus", 201);
						json.put("msg", "Status Not Updated");
						json.put("status", 0);
					}
				} else {
					json.put("msg", "Data Not Found");
				}

			}
		}

		return json.toString();
	}

	/*********************************
	 * MAS APPOINTMENT TYPE
	 *****************************************************************/
	@Override
	public String addAppointmentType(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasAppointmentType masAppointmentType = new MasAppointmentType();
			masAppointmentType.setAppointmentTypeCode(jsonObject.get("appointmentTypeCode").toString().toUpperCase());
			masAppointmentType.setAppointmentTypeName(jsonObject.get("appointmentTypeName").toString().toUpperCase());
			masAppointmentType.setStatus("Y");
			Users users = new Users();
			users.setUserId(new Long(1));
			masAppointmentType.setUser(users);
			masAppointmentType.setLastChgDate(date);

			List<MasAppointmentType> checkAppointment = md.validateAppointmentType(
					masAppointmentType.getAppointmentTypeCode(), masAppointmentType.getAppointmentTypeName());

			if (checkAppointment != null && checkAppointment.size() > 0) {
				if (checkAppointment.get(0).getAppointmentTypeCode()
						.equalsIgnoreCase(jsonObject.get("appointmentTypeCode").toString())) {

					json.put("status", 2);
					json.put("msg", "Appointment Type Code is already Existing");
				}

				if (checkAppointment.get(0).getAppointmentTypeName()
						.equalsIgnoreCase(jsonObject.get("appointmentTypeName").toString())) {

					json.put("status", 2);
					json.put("msg", "Appointment Type Name is already Existing");
				}

			} else {

				String addppointmentTypeObj = md.addAppointmentType(masAppointmentType);

				if (addppointmentTypeObj != null && addppointmentTypeObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Record Added Successfully");
				} else {
					json.put("status", 0);
					json.put("msg", "Record Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String getAllAppointmentType(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {
			List<MasAppointmentType> mAppointmentTypeList = new ArrayList<MasAppointmentType>();
			List totalMatches = new ArrayList();
			List appointmentTypeList = new ArrayList();

			Map<String, List<MasAppointmentType>> map = md.getAllAppointmentType(jsonObject);
			if (map.get("mAppointmentTypeList") != null) {
				mAppointmentTypeList = map.get("mAppointmentTypeList");
				totalMatches = map.get("totalMatches");

				for (MasAppointmentType appointmentType : mAppointmentTypeList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (appointmentType != null) {
						mapObj.put("appointmentTypeId", appointmentType.getAppointmentTypeId());
						mapObj.put("appointmentTypeCode", appointmentType.getAppointmentTypeCode());
						mapObj.put("appointmentTypeName", appointmentType.getAppointmentTypeName());
						mapObj.put("status", appointmentType.getStatus());

						appointmentTypeList.add(mapObj);
					}
				}

				if (appointmentTypeList != null && appointmentTypeList.size() > 0) {
					json.put("data", appointmentTypeList);
					json.put("count", totalMatches.size());
					json.put("status", 1);
				} else {
					json.put("data", appointmentTypeList);
					json.put("count", totalMatches.size());
					json.put("msg", "Data not found");
					json.put("status", 0);
				}

			} else {
				json.put("status", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("status", 0);
			json.put("msg", "Record Not Found");
		}

		return json.toString();
	}

	@Override
	public String updateAppointmentTypeDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("appointmentTypeCode").toString() != null
					&& !jsonObject.get("appointmentTypeCode").toString().trim().equalsIgnoreCase("")) {

				String updateAppointmentTypeDetail = md.updateAppointmentTypeDetails(
						Long.parseLong(jsonObject.get("appointmentTypeId").toString()),
						jsonObject.get("appointmentTypeCode").toString(),
						jsonObject.get("appointmentTypeName").toString());

				if (updateAppointmentTypeDetail != null && updateAppointmentTypeDetail.equalsIgnoreCase("200")) {
					json.put("updateAppointmentTypeDetail", updateAppointmentTypeDetail);
					json.put("msg", "Record Updated Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Record Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateAppointmentTypeStatus(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (jsonObject != null) {

			if (jsonObject.get("appointmentTypeCode").toString() != null
					&& !jsonObject.get("appointmentTypeCode").toString().trim().equalsIgnoreCase("")) {

				MasAppointmentType mAppointmentType = md
						.checkMasAppointmentType(jsonObject.get("appointmentTypeCode").toString());

				if (mAppointmentType != null) {
					String masAppointmentTypeStatus = md.updateAppointmentTypeStatus(
							Long.parseLong(jsonObject.get("appointmentTypeId").toString()),
							jsonObject.get("appointmentTypeCode").toString(), jsonObject.get("status").toString());

					if (masAppointmentTypeStatus != null && masAppointmentTypeStatus.equalsIgnoreCase("200")) {
						json.put("masAppointmentTypeStatus", masAppointmentTypeStatus);
						json.put("msg", "Status Updated Successfully");
						json.put("status", 1);
					} else {
						json.put("msg", "Status Not Updated");
						json.put("status", 0);
					}
				} else {
					json.put("msg", "Data Not Found");
				}

			}
		}

		return json.toString();
	}

	/*****************************
	 * MAS DEPARTMENT
	 ****************************************************/
	@Override
	public String getAllDepartment(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasDepartment> deptList = new ArrayList<MasDepartment>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasDepartment>> mapDepart = md.getAllDepartment(jsondata);
			List totalMatches = new ArrayList();
			if (mapDepart.get("departmentList") != null) {
				deptList = mapDepart.get("departmentList");
				totalMatches = mapDepart.get("totalMatches");
				for (MasDepartment department : deptList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (department != null) {
						mapObj.put("departmentId", department.getDepartmentId());
						mapObj.put("departmentCode", department.getDepartmentCode());
						mapObj.put("departmentName", department.getDepartmentName());
						mapObj.put("status", department.getStatus());
						mapObj.put("departmentTypeId", department.getMasDepartmentType().getDepartmentTypeId());
						mapObj.put("departmentTypeName", department.getMasDepartmentType().getDepartmentTypeName());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String getDepartmentTypeList(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasDepartmentType> depTypeList = md.getDepartmentTypeList();
		if (depTypeList != null && depTypeList.size() > 0) {
			json.put("data", depTypeList);
			json.put("count", depTypeList.size());
			json.put("status", 1);
		} else {
			json.put("data", depTypeList);
			json.put("count", depTypeList.size());
			json.put("msg", "No Record Found");
			json.put("status", 0);

		}
		return json.toString();
	}

	@Override
	public String addDepartment(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {

			long d = System.currentTimeMillis();
			Timestamp date = new Timestamp(d);

			MasDepartment masDepartment = new MasDepartment();
			MasDepartmentType masDepartmentType = new MasDepartmentType();

			masDepartment.setDepartmentCode(jsondata.get("departmentCode").toString().toUpperCase());
			masDepartment.setDepartmentName(jsondata.get("departmentName").toString().toUpperCase());

			masDepartmentType.setDepartmentTypeId(Long.parseLong(jsondata.get("departmentTypeId").toString()));

			masDepartment.setMasDepartmentType(masDepartmentType);
			Users users = new Users();
			users.setUserId(new Long(1));
			masDepartment.setUser(users);
			masDepartment.setStatus("Y");
			masDepartment.setLastChgDate(date);

			List<MasDepartment> checkDeptList = md.validateDepartment(masDepartment.getDepartmentCode(),
					masDepartment.getDepartmentName());
			if (checkDeptList != null && checkDeptList.size() > 0) {
				if (checkDeptList.get(0).getDepartmentCode()
						.equalsIgnoreCase(jsondata.get("departmentCode").toString())) {

					json.put("status", 2);
					json.put("msg", "Department Code is already Existing");
				}
				if (checkDeptList.get(0).getDepartmentName()
						.equalsIgnoreCase(jsondata.get("departmentName").toString())) {

					json.put("status", 2);
					json.put("msg", "Department Name is already Existing");
				}

			} else {
				String addDepartObj = md.addDepartment(masDepartment);
				if (addDepartObj != null && addDepartObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Record Added Successfully");
				} else {
					json.put("status", 0);
					json.put("msg", "Record Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateDepartmentDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("departmentCode").toString() != null
					&& !jsonObject.get("departmentCode").toString().trim().equalsIgnoreCase("")) {

				String updateDeptDetail = md.updateDepartmentDetails(
						Long.parseLong(jsonObject.get("departmentId").toString()),
						jsonObject.get("departmentCode").toString(), jsonObject.get("departmentName").toString(),
						Long.parseLong(jsonObject.get("departmentTypeId").toString()));

				if (updateDeptDetail != null && updateDeptDetail.equalsIgnoreCase("200")) {
					json.put("updateDeptDetail", updateDeptDetail);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateDepartmentStatus(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (jsonObject != null) {

			if (jsonObject.get("departmentCode").toString() != null
					&& !jsonObject.get("departmentCode").toString().trim().equalsIgnoreCase("")) {

				MasDepartment mDepartment = md.checkDepartment(jsonObject.get("departmentCode").toString());

				if (mDepartment != null) {
					String deptStatus = md.updateDepartmentStatus(
							Long.parseLong(jsonObject.get("departmentId").toString()),
							jsonObject.get("departmentCode").toString(), jsonObject.get("status").toString());

					if (deptStatus != null && deptStatus.equalsIgnoreCase("200")) {
						json.put("deptStatus", deptStatus);
						json.put("msg", "Status Updated Successfully");
						json.put("status", 1);
					} else {
						json.put("msg", "Status Not Updated");
						json.put("status", 0);
					}
				} else {
					json.put("msg", "Data Not Found");
				}

			}
		}

		return json.toString();
	}

	/***************************************
	 * MAS FREQUENCY
	 ***********************************************************************/

	@Override
	public String getAllOpdFrequency(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasFrequency> freqList = new ArrayList<MasFrequency>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasFrequency>> mapFreq = md.getAllOpdFrequency(jsondata);
			List totalMatches = new ArrayList();
			if (mapFreq.get("frequencyList") != null) {
				freqList = mapFreq.get("frequencyList");
				totalMatches = mapFreq.get("totalMatches");
				for (MasFrequency frequency : freqList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (frequency != null) {
						mapObj.put("frequencyId", frequency.getFrequencyId());
						mapObj.put("frequencyCode", frequency.getFrequencyCode());
						mapObj.put("frequencyName", frequency.getFrequencyName());
						mapObj.put("status", frequency.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addOpdFrequency(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			Long lastChgBy = new Long(1);
			MasFrequency masFrequency = new MasFrequency();

			masFrequency.setFrequencyCode(jsondata.get("frequencyCode").toString().toUpperCase());
			masFrequency.setFrequencyName(jsondata.get("frequencyName").toString().toUpperCase());
			masFrequency.setLastChgBy(lastChgBy);
			masFrequency.setStatus("Y");
			Timestamp timestamp = new Timestamp(new Date().getTime());
			masFrequency.setLastChgDate(timestamp);

			List<MasFrequency> checkFreqtList = md.validateFrequency(masFrequency.getFrequencyCode(),
					masFrequency.getFrequencyName());
			if (checkFreqtList != null && checkFreqtList.size() > 0) {
				if (checkFreqtList.get(0).getFrequencyCode()
						.equalsIgnoreCase(jsondata.get("frequencyCode").toString())) {

					json.put("status", 2);
					json.put("msg", "Frequency Code is already Existing");
				}
				if (checkFreqtList.get(0).getFrequencyName()
						.equalsIgnoreCase(jsondata.get("frequencyName").toString())) {

					json.put("status", 2);
					json.put("msg", "FrequencyName Name is already Existing");
				}

			} else {
				String addFreqObj = md.addOpdFrequency(masFrequency);
				if (addFreqObj != null && addFreqObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateOpdFrequencyDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("frequencyCode").toString() != null
					&& !jsonObject.get("frequencyCode").toString().trim().equalsIgnoreCase("")) {

				String updatefreq = md.updateDepartmentDetails(Long.parseLong(jsonObject.get("frequencyId").toString()),
						jsonObject.get("frequencyCode").toString(), jsonObject.get("frequencyName").toString());

				if (updatefreq != null && updatefreq.equalsIgnoreCase("200")) {
					json.put("updatefreq", updatefreq);
					json.put("msg", "Record Updated Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Record Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateOpdFrequencyStatus(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (jsonObject != null) {
			if (jsonObject.get("frequencyCode").toString() != null
					&& !jsonObject.get("frequencyCode").toString().trim().equalsIgnoreCase("")) {

				MasFrequency mFrequency = md.checkFrequency(jsonObject.get("frequencyCode").toString());

				if (mFrequency != null) {
					String freqStatus = md.updateOpdFrequencyStatus(
							Long.parseLong(jsonObject.get("frequencyId").toString()),
							jsonObject.get("frequencyCode").toString(), jsonObject.get("status").toString());

					if (freqStatus != null && freqStatus.equalsIgnoreCase("200")) {
						json.put("freqStatus", freqStatus);
						json.put("msg", "Status Updated Successfully");
						json.put("status", 1);
					} else {
						json.put("msg", "Status Not Updated");
						json.put("status", 0);
					}
				} else {
					json.put("msg", "Data Not Found");
				}

			}
		}

		return json.toString();
	}

	@Override
	public String updateStatus(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (jsonObject != null) {
			if (jsonObject.get("unitName").toString() != null
					&& !jsonObject.get("unitName").toString().trim().equalsIgnoreCase("")) {

				MasUnit mUnit = md.checkUnit(jsonObject.get("unitName").toString());

				if (mUnit != null) {
					String uStatus = md.updateUnitStatus(Long.parseLong(jsonObject.get("unitId").toString()),
							jsonObject.get("unitName").toString(), jsonObject.get("status").toString());

					if (uStatus != null && uStatus.equalsIgnoreCase("200")) {
						json.put("uStatus", uStatus);
						json.put("msg", "Status Updated Successfully");
						json.put("status", 1);
					} else {
						json.put("msg", "Status Not Updated");
						json.put("status", 0);
					}
				} else {
					json.put("msg", "Data Not Found");
				}

			}
		}

		return json.toString();
	}

	/*************************
	 * MAS EMPANELLED HOSPITAL
	 ************************************************/
	@Override
	public String getAllEmpanelledHospital(JSONObject jsondata, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json = new JSONObject();
		List<MasEmpanelledHospital> empanelledHospitalList = new ArrayList<MasEmpanelledHospital>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasEmpanelledHospital>> mapEmpanelledHosp = md.getAllEmpanelledHospital(jsondata);
			List totalMatches = new ArrayList();
			if (mapEmpanelledHosp.get("mImpanneledHospitalList") != null) {
				empanelledHospitalList = mapEmpanelledHosp.get("mImpanneledHospitalList");
				totalMatches = mapEmpanelledHosp.get("totalMatches");
				for (MasEmpanelledHospital empanelledHospital : empanelledHospitalList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (empanelledHospital != null) {
						mapObj.put("empanelledHospitalId", empanelledHospital.getEmpanelledHospitalId());
						mapObj.put("empanelledHospitalCode", empanelledHospital.getEmpanelledHospitalCode());
						mapObj.put("empanelledHospitalName", empanelledHospital.getEmpanelledHospitalName());
						mapObj.put("empanelledHospitalAddress", empanelledHospital.getEmpanelledHospitalAddress());
						mapObj.put("status", empanelledHospital.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addEmpanelledHospital1(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			MasEmpanelledHospital masEmpanelledHospital = new MasEmpanelledHospital();

			masEmpanelledHospital
					.setEmpanelledHospitalCode(jsonObject.get("impanneledHospitalCode").toString().toUpperCase());
			masEmpanelledHospital
					.setEmpanelledHospitalId(Long.parseLong(jsonObject.get("impanneledHospitalId").toString()));
			masEmpanelledHospital
					.setEmpanelledHospitalName(jsonObject.get("impanneledHospitalName").toString().toUpperCase());
			masEmpanelledHospital
					.setEmpanelledHospitalAddress(jsonObject.get("impanneledHospitalAddress").toString().toUpperCase());
			masEmpanelledHospital.setStatus(jsonObject.get("status").toString());
			masEmpanelledHospital.setStatus("Y");
			// masEmpanelledHospital.setLastChgBy(new Long(1));
			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masEmpanelledHospital.setLastChgDate(date);

			List<MasEmpanelledHospital> empanelledHospitalList = md
					.validateEmpanelledHospital(masEmpanelledHospital.getEmpanelledHospitalName());
			if (empanelledHospitalList.size() != 0) {

				if (empanelledHospitalList != null && empanelledHospitalList.size() > 0) {
					if (empanelledHospitalList.get(0).getEmpanelledHospitalCode()
							.equalsIgnoreCase(jsonObject.get("empanelledHospitalCode").toString())) {
						json.put("status", 2);
						json.put("msg", "Empanelled Hospital Code is already Existing");

					}

					if (empanelledHospitalList.get(0).getEmpanelledHospitalName()
							.equalsIgnoreCase(jsonObject.get("empanelledHospitalName").toString())) {
						json.put("status", 2);
						json.put("msg", "Impanneled Hospital Name is already Existing");
					}
				}
			} else {
				String addEmpanellHospital = md.addEmpanelledHospital(masEmpanelledHospital);

				if (addEmpanellHospital != null && addEmpanellHospital.equalsIgnoreCase("200")) {
					json.put("msg", "Record Added Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Record Not Added");
					json.put("status", 0);
				}

				if (addEmpanellHospital != null && addEmpanellHospital.equalsIgnoreCase("201")) {
					json.put("msg", "Record Updated Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated");
					json.put("status", 0);
				}

			}

		} else {
			json.put("msg", "Data Not Found Error");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String addEmpanelledHospital(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			MasEmpanelledHospital masEmpanelledHospital = new MasEmpanelledHospital();

			masEmpanelledHospital
					.setEmpanelledHospitalCode(jsonObject.get("empanelledHospitalCode").toString().toUpperCase());
			masEmpanelledHospital
					.setEmpanelledHospitalName(jsonObject.get("empanelledHospitalName").toString().toUpperCase());
			masEmpanelledHospital
					.setEmpanelledHospitalAddress(jsonObject.get("empanelledHospitalAddress").toString().toUpperCase());
			masEmpanelledHospital.setStatus("Y");
			Users users = new Users();
			users.setUserId(new Long(1));
			masEmpanelledHospital.setUser(users);
			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masEmpanelledHospital.setLastChgDate(date);

			List<MasEmpanelledHospital> empanelledHospitalList = md
					.validateEmpanelledHospital(jsonObject.get("empanelledHospitalName").toString());
			if (empanelledHospitalList.size() != 0) {
				if (empanelledHospitalList != null && empanelledHospitalList.size() > 0) {
					if (empanelledHospitalList.get(0).getEmpanelledHospitalName()
							.equalsIgnoreCase(jsonObject.get("empanelledHospitalName").toString())) {
						json.put("status", 2);
						json.put("msg", "Empanelled Hospital Name is already Existing");
					}
				}
			} else {
				String addempaneledHospital = md.addEmpanelledHospital(masEmpanelledHospital);
				if (addempaneledHospital != null && addempaneledHospital.equalsIgnoreCase("200")) {
					json.put("msg", "Record Added Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Record Not Added");
					json.put("status", 0);
				}

			}

		} else {
			json.put("msg", "Data Not Found Error");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateEmpanelledHospital(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {
			System.out.println("service :: " + jsonObject);
			String updateImpanneled = md.updateEmpanelledHospital(jsonObject);

			if (updateImpanneled != null && updateImpanneled.equalsIgnoreCase("200")) {
				json.put("updateImpanneled", updateImpanneled);
				json.put("msg", "Record Updated Successfully.");
				json.put("status", 1);
			} else {
				json.put("msg", "Record Not Updated.");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	/*************************************
	 * MAS IDEAL WEIGHT
	 **********************************************************/
	@Override
	public String getAllIdealWeight(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasIdealWeight> idealWeightList = new ArrayList<MasIdealWeight>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasIdealWeight>> mapIdealWeight = md.getAllIdealWeight(jsondata);
			List totalMatches = new ArrayList();
			if (mapIdealWeight.get("idealWeightsList") != null) {
				idealWeightList = mapIdealWeight.get("idealWeightsList");
				totalMatches = mapIdealWeight.get("totalMatches");
				for (MasIdealWeight idealWeight : idealWeightList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (idealWeight != null) {
						mapObj.put("idealWeightId", idealWeight.getIdealWeightId());
						mapObj.put("genderId", idealWeight.getMasAdministrativeSex().getAdministrativeSexId());
						mapObj.put("rangeFlag", idealWeight.getMasRange2().getRangeFlag());
						if(idealWeight.getMasRange2().getRangeFlag().equalsIgnoreCase("A")) {
							mapObj.put("fromAge", idealWeight.getMasRange2().getFromRange());
							mapObj.put("toAge", idealWeight.getMasRange2().getToRange());
						}
						if(idealWeight.getMasRange1().getRangeFlag().equalsIgnoreCase("H")) {
							mapObj.put("fromHeight", idealWeight.getMasRange1().getFromRange());
							mapObj.put("toHeight", idealWeight.getMasRange1().getToRange());
						}
						mapObj.put("sd", idealWeight.getSd());
						mapObj.put("weight", idealWeight.getWeight());
						mapObj.put("status", idealWeight.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String getAllPhsiotherapy(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if (jsonObject != null) {
			List<MasNursingCare> mNursingList = new ArrayList<MasNursingCare>();
			List totalMatches = new ArrayList();
			List hList = new ArrayList();

			Map<String, List<MasNursingCare>> map = md.getAllmNursingData(jsonObject);
			if (map.get("mHospitalList") != null) {
				mNursingList = map.get("mHospitalList");
				totalMatches = map.get("totalMatches");

				for (MasNursingCare nursing : mNursingList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (nursing != null) {
						mapObj.put("nursingId", nursing.getNursingId());
						mapObj.put("nursingCode", nursing.getNursingCode());
						mapObj.put("nursingName", nursing.getNursingName());
						mapObj.put("nursingType", nursing.getNursingType());
						mapObj.put("status", nursing.getStatus());
						hList.add(mapObj);
					}
				}

				if (hList != null && hList.size() > 0) {
					json.put("data", hList);
					json.put("count", totalMatches.size());
					json.put("status", 1);
				} else {
					json.put("data", hList);
					json.put("count", totalMatches.size());
					json.put("msg", "Data not found");
					json.put("status", 0);
				}

			} else {
				json.put("status", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("status", 0);
			json.put("msg", "Record Not Found");
		}

		return json.toString();
	}

	@Override
	public String addPhsiotherapy(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			MasNursingCare nursingObj = new MasNursingCare();

			nursingObj.setNursingCode(jsonObject.get("nursingCode").toString().toUpperCase());
			nursingObj.setNursingName(jsonObject.get("nursingName").toString().toUpperCase());
			nursingObj.setNursingType(jsonObject.get("nursingType").toString().toUpperCase());
			nursingObj.setDefaultstatus("y");
			nursingObj.setStatus("Y");

			Users user = new Users();
			user.setUserId(new Long(1));
			nursingObj.setUser(user);

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			nursingObj.setLastChgDate(date);

			List<MasNursingCare> nursinglList = md.validateMasNursing(nursingObj.getNursingCode(),
					nursingObj.getNursingName(), nursingObj.getNursingType());
			if (nursinglList.size() != 0) {
				if (nursinglList != null && nursinglList.size() > 0) {

					if (nursinglList.get(0).getNursingCode()
							.equalsIgnoreCase(jsonObject.get("nursingCode").toString())) {

						return "{\"status\":\"2\",\"msg\":\"NursingCode is already Exist.\"}";
					}

					if (nursinglList.get(0).getNursingName()
							.equalsIgnoreCase(jsonObject.get("nursingName").toString())) {

						return "{\"status\":\"2\",\"msg\":\"NursingName is already Exist.\"}";
					}

					if (nursinglList.get(0).getNursingType()
							.equalsIgnoreCase(jsonObject.get("nursingType").toString())) {

						return "{\"status\":\"2\",\"msg\":\"NursingType is already Exist.\"}";
					}
				}
			} else {
				String addHospital = md.addMasNursing(nursingObj);

				if (addHospital != null && addHospital.equalsIgnoreCase("200")) {
					json.put("msg", "Successfully Added");
					json.put("status", 1);
				} else {
					json.put("msg", "Data has been Not Added");
					json.put("status", 0);
				}
			}

		} else {
			json.put("msg", "Data Not Found Error");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updatePhysiotherapyDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			String updateMasNursing = md.updateMasNursing(jsonObject);

			if (updateMasNursing != null && updateMasNursing.equalsIgnoreCase("200")) {
				json.put("updateMasNursing", updateMasNursing);
				json.put("msg", "Record Updated Successfully.");
				json.put("status", 1);
			} else {
				json.put("msg", "Record Not Updated.");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String getAge(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasIdealWeight> idealWtList = new ArrayList<MasIdealWeight>();
		if (jsonObject != null) {

			idealWtList = md.getAge(jsonObject);
			
			json.put("data", idealWtList);
			json.put("count", idealWtList.size());

		} else {
			json.put("status", 0);
			json.put("count", idealWtList.size());
			json.put("msg", "Record Not Found");
		}

		return json.toString();
	}

	/***************************************
	 * SERVICE TYPE
	 ***********************************************************************/

	@Override
	public String getAllServiceType(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasServiceType> stList = new ArrayList<MasServiceType>();

		Map<String, List<MasServiceType>> stMap = md.getAllServiceType(jsonObj);
		List stListObj = new ArrayList();
		List totalMatches = new ArrayList();
		if (stMap.get("masServiceTypeList") != null) {
			stList = stMap.get("masServiceTypeList");
			totalMatches = stMap.get("totalMatches");

			for (MasServiceType serviceType : stList) {
				HashMap<String, Object> mapObj = new HashMap<String, Object>();

				mapObj.put("serviceTypeId", serviceType.getServiceTypeId());
				mapObj.put("serviceTypeCode", serviceType.getServiceTypeCode());
				mapObj.put("serviceTypeName", serviceType.getServiceTypeName());
				mapObj.put("status", serviceType.getStatus());
				stListObj.add(mapObj);
			}

			if (stListObj != null && stListObj.size() > 0) {
				json.put("data", stListObj);

				json.put("count", totalMatches.size());
				json.put("status", 1);
			} else {
				json.put("data", stListObj);
				json.put("count", totalMatches.size());
				json.put("msg", "Data not found");
				json.put("status", 0);
			}
		}

		return json.toString();
	}

	@Override
	public String updateServiceType(HashMap<String, Object> serviceType, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (serviceType.get("serviceTypeName") != null
				&& !serviceType.get("serviceTypeName").toString().equalsIgnoreCase("")) {

			String serviceTypeUpdate = md.updateServiceType(Long.parseLong(serviceType.get("serviceTypeId").toString()),
					serviceType.get("serviceTypeName").toString());

			if (serviceTypeUpdate != null && !serviceTypeUpdate.equalsIgnoreCase("")) {
				json.put("serviceTypeUpdate", serviceTypeUpdate);
				json.put("msg", "Record Updated Successfully");
				json.put("status", 1);
			} else if (serviceTypeUpdate == null && serviceTypeUpdate.equalsIgnoreCase("")) {
				json.put("msg", "Record Not Updated!!!");
				json.put("status", 0);
			}

			else {
				json.put("msg", "Service Name is not available");
				json.put("status", 0);

			}

		} else {
			json.put("msg", "Service Type Code is not available");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String addServiceType(JSONObject json, HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObj = new JSONObject();
		if (json != null) {

			MasServiceType masServiceType = new MasServiceType();
			masServiceType.setServiceTypeCode(json.getString("serviceTypeCode").toUpperCase());
			masServiceType.setServiceTypeName(json.getString("serviceTypeName").toUpperCase());
			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masServiceType.setLastChgDate(date);

			Users users = new Users();
			users.setUserId(new Long(1));
			masServiceType.setUser(users);
			masServiceType.setStatus("Y");

			String masStTypeObj = md.addServiceType(masServiceType);

			if (masStTypeObj != null && masStTypeObj.equalsIgnoreCase("200")) {
				jsonObj.put("status", 1);
				jsonObj.put("msg", "Record Added Successfully");

			} else if (masStTypeObj != null && masStTypeObj.equalsIgnoreCase("403")) {
				jsonObj.put("status", 0);
				jsonObj.put("msg", "You are not authorized person!!!");

			} else if (masStTypeObj != null && masStTypeObj.equalsIgnoreCase("serviceTypeCodeExist")) {
				jsonObj.put("status", 2);
				jsonObj.put("msg", "Service Type Code Already Existing");

			} else if (masStTypeObj != null && masStTypeObj.equalsIgnoreCase("serviceTypeNameExist")) {
				jsonObj.put("status", 2);
				jsonObj.put("msg", "Service Type Name Already Existing");

			} else {
				jsonObj.put("msg", masStTypeObj);
				jsonObj.put("status", 0);
			}
		}
		return jsonObj.toString();
	}

	@Override
	public String statusServiceType(HashMap<String, Object> serviceType, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (serviceType.get("serviceTypeCode") != null
				&& !serviceType.get("serviceTypeCode").toString().equalsIgnoreCase("")) {
			String masStStatus = md.updateServiceTypeStatus(Long.parseLong(serviceType.get("serviceTypeId").toString()),
					serviceType.get("serviceTypeCode").toString(), serviceType.get("status").toString());

			if (masStStatus != null && masStStatus.equalsIgnoreCase("200")) {
				json.put("masStStatus", masStStatus);
				json.put("msg", "Status Updated Successfully");
				json.put("status", 1);
			} else {
				// json.put("masCmdStatus", masCmdStatus);
				json.put("msg", "Status Not Updated Successfully");
				json.put("status", 0);
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"Service Type Code is not available\"}";
		}

		return json.toString();
	}

	@Override
	public String updateIdealWeight(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			String updateidealWt = md.updateIdealWeight(jsonObject);

			if (updateidealWt != null && updateidealWt.equalsIgnoreCase("200")) {
				json.put("updateidealWt", updateidealWt);
				json.put("msg", "Record Updated Successfully.");
				json.put("status", 1);
			} else {
				json.put("msg", "Record Not Updated.");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String addIdealWeight(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			if(jsonObject!=null) {
				MasIdealWeight masIdealWeight = new MasIdealWeight();				
					MasAdministrativeSex masAdministrativeSex = new MasAdministrativeSex();
					masAdministrativeSex.setAdministrativeSexId(Long.parseLong(jsonObject.get("administrativeSexId").toString()));				
				masIdealWeight.setMasAdministrativeSex(masAdministrativeSex);
				MasRange masRange2 = new MasRange();
				MasRange masRange1 = new MasRange();				
					masRange2.setRangeId(Long.parseLong(jsonObject.get("masRange2").toString()));				
					masRange1.setRangeId(Long.parseLong(jsonObject.get("masRange1").toString()));				
				masIdealWeight.setMasRange1(masRange1);
				masIdealWeight.setMasRange2(masRange2);
				masIdealWeight.setWeight(Long.parseLong(jsonObject.get("weight").toString()));
				masIdealWeight.setStatus("Y");
				
				String savedObject = md.addIdealWeight(masIdealWeight);	
					if(savedObject!=null && savedObject.equalsIgnoreCase("200")) {
						json.put("status", 1);
						json.put("msg", "Record Added Successfully");
					}else {
						json.put("status", 0);
						json.put("msg", "Record Not Added");
					}
					
			}else {
				json.put("data", "Invalid Input Parameters");
				json.put("status", 0);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/*-------------------MAS RANK----------------------*/
	@Override
	public String addRank(JSONObject json, HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObj = new JSONObject();
		MasRank masRank = new MasRank();

		if (!json.equals(null)) {

			if (json.get("rankCode") == null) {
				return "{\"status\":\"0\",\"msg\":\"rankCode is not contain in json data or it will be null or blank please check\"}";
			}
			if (json.get("rankName") == null) {
				return "{\"status\":\"0\",\"msg\":\"rankName is not contain in json data or it will be null or blank please check\"}";
			}

			else {
				masRank.setRankCode(json.get("rankCode").toString().toUpperCase());
				masRank.setRankName(json.get("rankName").toString().toUpperCase());

				long d = System.currentTimeMillis();
				Date date = new Date(d);
				masRank.setLastChgDate(date);

				String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

				// masRank.setLastChgTime(lastChgTime);
				// Users usr = new Users();
				// usr.setUserId(new Long(1)); // userId will be fetch from session
				// masRank.setLastChgBy(new Long(1));
				masRank.setStatus("y");

				MasEmployeeCategory employeeCategory = new MasEmployeeCategory();
				employeeCategory.setEmployeeCategoryId(Long.parseLong(json.get("employeeCategoryId").toString()));
				masRank.setMasEmployeeCategory(employeeCategory);

				List<MasRank> masRank1 = md.validateMasRank(masRank.getRankCode().toString(),
						masRank.getRankName().toString());
				if (masRank1.size() != 0) {
					if (masRank1 != null && masRank1.size() > 0) {
						if (masRank1.get(0).getRankCode().equalsIgnoreCase(json.get("rankCode").toString())) {

							return "{\"status\":\"2\",\"msg\":\"Rank Code is already Existing.\"}";
						}
						if (masRank1.get(0).getRankName().equalsIgnoreCase(json.get("rankName").toString())) {

							return "{\"status\":\"2\",\"msg\":\"Rank Name is already Existing.\"}";
						}
					}
				} else {
					String masRankObj = md.addMasRank(masRank);
					if (masRankObj != null && masRankObj.equalsIgnoreCase("200")) {
						jsonObj.put("status", 1);
						jsonObj.put("msg", "Rank has been Successfully Added !!");

					} else if (masRankObj != null && masRankObj.equalsIgnoreCase("403")) {
						jsonObj.put("status", 0);
						jsonObj.put("msg", "You are not authorized person!!!");

					} else {
						jsonObj.put("msg", masRankObj);
						jsonObj.put("status", 0);
					}
				}
			}
		} else {
			jsonObj.put("msg", "Cannot Contains Any Data!!!");
			jsonObj.put("status", 0);
		}

		return jsonObj.toString();

	}

	@Override
	public String getAllRank(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasRank> rankList = new ArrayList<MasRank>();
		List list = new ArrayList();

		if (jsonObj != null) {
			Map<String, List<MasRank>> mapRank = md.getAllRank(jsonObj);
			List totalMatches = new ArrayList();
			if (mapRank.get("masRankList") != null) {
				rankList = mapRank.get("masRankList");
				totalMatches = mapRank.get("totalMatches");
				for (MasRank rank : rankList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();

					mapObj.put("rankId", rank.getRankId());
					mapObj.put("rankName", rank.getRankName());
					mapObj.put("rankCode", rank.getRankCode());
					mapObj.put("status", rank.getStatus());
					mapObj.put("employeeCategoryName", rank.getMasEmployeeCategory()!=null?rank.getMasEmployeeCategory().getEmployeeCategoryName():"");
					mapObj.put("employeeCategoryId", rank.getMasEmployeeCategory()!=null?rank.getMasEmployeeCategory().getEmployeeCategoryId():"0");
					list.add(mapObj);
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String getRank(HashMap<String, Object> rank, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		if (rank.get("rankName") == null && rank.get("rankName").toString().trim().equalsIgnoreCase("")) {

			return "{\"status\":\"0\",\"msg\":\"rankCode is not available !!!\"}";
		} else {
			MasRank chkRank = md.chkRank(rank.get("rankName").toString());
			if (chkRank != null) {
				List<MasRank> masRankLst = md.getRank(rank.get("rankName").toString());
				if (masRankLst != null && masRankLst.size() > 0) {
					System.out.println("masRankLst :: " + masRankLst);
					jsonObject.put("masRankLst", masRankLst);
					jsonObject.put("msg", "List of rank successfully...");
					jsonObject.put("status", 1);
				} else {
					return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
				}
			} else {
				return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			}
		}

		return jsonObject.toString();
	}

	@Override
	public String updateRank(HashMap<String, Object> rank, HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = new JSONObject();

		if (rank.get("rankId") != null && !rank.get("rankId").toString().equalsIgnoreCase("")) {
			List<MasRank> msRankList = md.validateMasRankUpdate(
					Long.parseLong(rank.get("employeeCategoryId").toString()), rank.get("rankName").toString());
			if (msRankList.size() > 0) {
				return "{\"status\":\"0\",\"msg\":\"rankCode is already exist !!!\"}";
			}
			String rankUpdate = md.updateRank(Long.parseLong(rank.get("rankId").toString()),
					rank.get("rankCode").toString(), rank.get("rankName").toString(),
					Long.parseLong(rank.get("employeeCategoryId").toString()));
			if (rankUpdate != null && !rankUpdate.equalsIgnoreCase("")) {
				json.put("rankUpdate", rankUpdate);
				json.put("msg", "Successfully Updated!!!");
				json.put("status", 1);
			} else if (rankUpdate == null && rankUpdate.equalsIgnoreCase("")) {
				json.put("msg", "Not Updated!!!");
				json.put("status", 0);
			}

			else {
				return "{\"status\":\"0\",\"msg\":\"rankCode is not available !!!\"}";
			}

		} else {
			return "{\"status\":\"0\",\"msg\":\"rankCode is not available !!!\"}";
		}

		return json.toString();

	}

	@Override
	public String statusRank(HashMap<String, Object> rank, HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = new JSONObject();
		if (rank.get("rankCode") != null && !rank.get("rankCode").toString().equalsIgnoreCase("")) {
			MasRank chkRank = md.chkRank(rank.get("rankCode").toString());
			if (chkRank != null) {
				String masRankStatus = md.updateRankStatus(Long.parseLong(rank.get("rankId").toString()),
						rank.get("rankCode").toString(), rank.get("status").toString());
				if (masRankStatus != null && masRankStatus.equalsIgnoreCase("200")) {
					json.put("masRankStatus", masRankStatus);
					json.put("msg", "Status Updated Successfully");
					json.put("status", 1);
				} else {
					// json.put("masCmdStatus", masCmdStatus);
					json.put("msg", "Status Not Updated");
					json.put("status", 0);
				}
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"rankCode is not available !!!\"}";
		}

		return json.toString();

	}

	@Override
	public String getEmployeeCategoryList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		List<MasEmployeeCategory> employeeCategoryList = md.getEmployeeCategoryList();
		if (employeeCategoryList != null && employeeCategoryList.size() > 0) {

			jsonObj.put("data", employeeCategoryList);
			jsonObj.put("count", employeeCategoryList.size());
			jsonObj.put("status", 1);
		} else {
			jsonObj.put("data", employeeCategoryList);
			jsonObj.put("count", employeeCategoryList.size());
			jsonObj.put("msg", "No Record Found");
			jsonObj.put("status", 0);
		}
		return jsonObj.toString();
	}

	/********************************************************
	 * TRADE MASTER
	 *********************************************************/

	@Override
	public String addTrade(JSONObject json, HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObj = new JSONObject();
		MasTrade masTrade = new MasTrade();
		System.out.println("json - TRADE NAME :: " + json);
		if (!json.equals(null)) {

			if (json.get("tradeName") == null) {
				return "{\"status\":\"0\",\"msg\":\"tradeName is not contain in json data or it will be null or blank please check\"}";
			} else {
				masTrade.setTradeName(json.get("tradeName").toString().toUpperCase());

				long d = System.currentTimeMillis();
				Date date = new Date(d);
				masTrade.setLastChgDate(date);

				String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

				// masRank.setLastChgTime(lastChgTime);
				Users usr = new Users();
				usr.setUserId(new Long(1)); // userId will be fetch from session
				// masTrade.setLastChgBy(new Long(1));
				masTrade.setStatus("y");

				MasServiceType masServiceType = new MasServiceType();
				masServiceType.setServiceTypeId(Long.parseLong(json.get("serviceTypeId").toString()));
				masTrade.setMasServiceType(masServiceType);
				;

				List<MasTrade> masTrade1 = md.validateMasTrade(masTrade.getTradeName().toString());
				if (masTrade1.size() != 0) {
					if (masTrade1 != null && masTrade1.size() > 0) {
						if (masTrade1.get(0).getTradeName().equalsIgnoreCase(json.get("tradeName").toString())) {

							return "{\"status\":\"2\",\"msg\":\"Trade Name is already Existing.\"}";
						}
					}
				} else {
					String masTradeObj = md.addMasTrade(masTrade);
					if (masTradeObj != null && masTradeObj.equalsIgnoreCase("200")) {
						jsonObj.put("status", 1);
						jsonObj.put("msg", "Trade has been Successfully Added !!");

					} else if (masTradeObj != null && masTradeObj.equalsIgnoreCase("403")) {
						jsonObj.put("status", 0);
						jsonObj.put("msg", "You are not authorized person!!!");

					} else {
						jsonObj.put("msg", masTradeObj);
						jsonObj.put("status", 0);
					}
				}
			}
		} else {
			jsonObj.put("msg", "Cannot Contains Any Data!!!");
			jsonObj.put("status", 0);
		}

		return jsonObj.toString();

	}

	@Override
	public String getAllTrade(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasTrade> tradeList = new ArrayList<MasTrade>();
		List list = new ArrayList();

		if (jsonObj != null) {
			Map<String, List<MasTrade>> mapTrade = md.getAllTrade(jsonObj);
			List totalMatches = new ArrayList();
			if (mapTrade.get("masTradeList") != null) {
				tradeList = mapTrade.get("masTradeList");
				totalMatches = mapTrade.get("totalMatches");
				for (MasTrade trade : tradeList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					
						mapObj.put("tradeId", trade.getTradeId());
						mapObj.put("tradeName", trade.getTradeName());
						mapObj.put("status", trade.getStatus());
						mapObj.put("serviceTypeName", trade.getMasServiceType()!=null?trade.getMasServiceType().getServiceTypeName():"");
						mapObj.put("serviceTypeId", trade.getMasServiceType()!=null?trade.getMasServiceType().getServiceTypeId():"0");
						list.add(mapObj);
					
				}
				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String getTrade(HashMap<String, Object> trade, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		if (trade.get("tradeName") == null && trade.get("tradeName").toString().trim().equalsIgnoreCase("")) {

			return "{\"status\":\"0\",\"msg\":\"tradeName is not available !!!\"}";
		} else {
			MasTrade checkTrade = md.checkTrade(trade.get("tradeName").toString());
			if (checkTrade != null) {
				List<MasTrade> masTradeLst = md.getTrade(trade.get("tradeName").toString());
				if (masTradeLst != null && masTradeLst.size() > 0) {
					System.out.println("masTradeLst :: " + masTradeLst);
					jsonObject.put("masTradeLst", masTradeLst);
					jsonObject.put("msg", "List of Trade successfully...");
					jsonObject.put("status", 1);
				} else {
					return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
				}
			} else {
				return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			}
		}

		return jsonObject.toString();
	}

	@Override
	public String updateTrade(HashMap<String, Object> trade, HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = new JSONObject();

		if (trade.get("tradeId") != null && !trade.get("tradeId").toString().equalsIgnoreCase("")) {

			List<MasTrade> msTradeList = md.validateMasTradeUpdate(trade.get("tradeName").toString());
			if (msTradeList.size() > 0) {
				return "{\"status\":\"0\",\"msg\":\"Trade Name is already exist !!!\"}";
			}

			String tradeUpdate = md.updateTrade(Long.parseLong(trade.get("tradeId").toString()),
					trade.get("tradeName").toString(), Long.parseLong(trade.get("serviceTypeId").toString()));
			if (tradeUpdate != null && !tradeUpdate.equalsIgnoreCase("")) {
				json.put("tradeUpdate", tradeUpdate);
				json.put("msg", "Successfully Updated!!!");
				json.put("status", 1);
			} else if (tradeUpdate == null && tradeUpdate.equalsIgnoreCase("")) {
				json.put("msg", "Not Updated!!!");
				json.put("status", 0);
			}

			else {
				return "{\"status\":\"0\",\"msg\":\"tradeName is not available !!!\"}";
			}
		}

		return json.toString();

	}

	@Override
	public String statusTrade(HashMap<String, Object> trade, HttpServletRequest request, HttpServletResponse response) {

		JSONObject json = new JSONObject();
		if (trade.get("tradeName") != null && !trade.get("tradeName").toString().equalsIgnoreCase("")) {
			MasTrade checkTrade = md.checkTrade(trade.get("tradeName").toString());
			if (checkTrade != null) {
				String masTradeStatus = md.updateTradeStatus(Long.parseLong(trade.get("tradeId").toString()),
						trade.get("status").toString());
				if (masTradeStatus != null && masTradeStatus.equalsIgnoreCase("200")) {
					json.put("masTradeStatus", masTradeStatus);
					json.put("msg", "Status Updated Successfully");
					json.put("status", 1);
				} else {
					// json.put("masCmdStatus", masCmdStatus);
					json.put("msg", "Status Not Updated");
					json.put("status", 0);
				}
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"tradeName is not available !!!\"}";
		}

		return json.toString();

	}

	@Override
	public String getServiceTypeList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		List<MasServiceType> serviceTypeList = md.getServiceTypeList();
		if (serviceTypeList != null && serviceTypeList.size() > 0) {

			jsonObj.put("data", serviceTypeList);
			jsonObj.put("count", serviceTypeList.size());
			jsonObj.put("status", 1);
		} else {
			jsonObj.put("data", serviceTypeList);
			jsonObj.put("count", serviceTypeList.size());
			jsonObj.put("msg", "No Record Found");
			jsonObj.put("status", 0);
		}
		return jsonObj.toString();
	}

	/***************************************
	 * MAS RELIGION
	 ***********************************************************************/

	@Override
	public String getAllReligion(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasReligion> reliList = new ArrayList<MasReligion>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasReligion>> mapReli = md.getAllReligion(jsondata);
			List totalMatches = new ArrayList();
			if (mapReli.get("religionList") != null) {
				reliList = mapReli.get("religionList");
				totalMatches = mapReli.get("totalMatches");
				for (MasReligion religion : reliList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (religion != null) {
						mapObj.put("religionId", religion.getReligionId());
						mapObj.put("religionCode", religion.getReligionCode());
						mapObj.put("religionName", religion.getReligionName());
						mapObj.put("status", religion.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addReligion(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasReligion masReligion = new MasReligion();

			masReligion.setReligionCode(jsondata.get("religionCode").toString().toUpperCase());
			masReligion.setReligionName(jsondata.get("religionName").toString().toUpperCase());
			// masReligion.setLastChgBy(lastChgBy);
			masReligion.setStatus("Y");
			masReligion.setLastChgDate(date);

			List<MasReligion> checkReliList = md.validateReligion(masReligion.getReligionCode(),
					masReligion.getReligionName());
			if (checkReliList != null && checkReliList.size() > 0) {
				if (checkReliList.get(0).getReligionCode().equalsIgnoreCase(jsondata.get("religionCode").toString())) {

					json.put("status", 2);
					json.put("msg", "checkReliList Code is already Existing");
				}
				if (checkReliList.get(0).getReligionName().equalsIgnoreCase(jsondata.get("religionName").toString())) {

					json.put("status", 2);
					json.put("msg", "Religion Name is already Existing");
				}

			} else {
				String addReliObj = md.addReligion(masReligion);
				if (addReliObj != null && addReliObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateReligionDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("religionCode").toString() != null
					&& !jsonObject.get("religionCode").toString().trim().equalsIgnoreCase("")) {

				List<MasReligion> msReligionList = md.validateReligionUpdate(jsonObject.get("religionCode").toString(),
						jsonObject.get("religionName").toString());
				if (msReligionList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"Religion Name is already exist !!!\"}";
				}

				String updatereligion = md.updateReligionDetails(
						Long.parseLong(jsonObject.get("religionId").toString()),
						jsonObject.get("religionCode").toString(), jsonObject.get("religionName").toString());

				if (updatereligion != null && updatereligion.equalsIgnoreCase("200")) {
					json.put("updatefreq", updatereligion);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateReligionStatus(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (jsonObject != null) {
			if (jsonObject.get("religionCode").toString() != null
					&& !jsonObject.get("religionCode").toString().trim().equalsIgnoreCase("")) {

				MasReligion mReligion = md.checkReligion(jsonObject.get("religionCode").toString());

				if (mReligion != null) {
					String reliStatus = md.updateReligionStatus(Long.parseLong(jsonObject.get("religionId").toString()),
							jsonObject.get("religionCode").toString(), jsonObject.get("status").toString());

					if (reliStatus != null && reliStatus.equalsIgnoreCase("200")) {
						json.put("reliStatus", reliStatus);
						json.put("msg", "Status Updated Successfully");
						json.put("status", 1);
					} else {
						json.put("msg", "Status Not Updated");
						json.put("status", 0);
					}
				} else {
					json.put("msg", "Data Not Found");
				}

			}
		}

		return json.toString();
	}

	/***************************************
	 * MAS MARITAL STATUS
	 ***********************************************************************/

	@Override
	public String getAllMaritalStatus(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasMaritalStatus> maritalStatusList = new ArrayList<MasMaritalStatus>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasMaritalStatus>> mapMaritalStatus = md.getAllMaritalStatus(jsondata);
			List totalMatches = new ArrayList();
			if (mapMaritalStatus.get("maritalStatusList") != null) {
				maritalStatusList = mapMaritalStatus.get("maritalStatusList");
				totalMatches = mapMaritalStatus.get("totalMatches");
				for (MasMaritalStatus maritalStatus : maritalStatusList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (maritalStatus != null) {
						mapObj.put("maritalStatusId", maritalStatus.getMaritalStatusId());
						mapObj.put("maritalStatusCode", maritalStatus.getMaritalStatusCode());
						mapObj.put("maritalStatusName", maritalStatus.getMaritalStatusName());
						mapObj.put("status", maritalStatus.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addMaritalStatus(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasMaritalStatus masMaritalStatus = new MasMaritalStatus();

			masMaritalStatus.setMaritalStatusCode(jsondata.get("maritalStatusCode").toString().toUpperCase());
			masMaritalStatus.setMaritalStatusName(jsondata.get("maritalStatusName").toString().toUpperCase());
			// masMaritalStatus.setLastChgBy(lastChgBy);
			masMaritalStatus.setStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasMaritalStatus> checkMaritalStatusList = md.validateMaritalStatus(
					masMaritalStatus.getMaritalStatusCode(), masMaritalStatus.getMaritalStatusName());
			if (checkMaritalStatusList != null && checkMaritalStatusList.size() > 0) {
				if (checkMaritalStatusList.get(0).getMaritalStatusCode()
						.equalsIgnoreCase(jsondata.get("maritalStatusCode").toString())) {

					json.put("status", 2);
					json.put("msg", "checkMaritalStatusList Code is already Existing");
				}
				if (checkMaritalStatusList.get(0).getMaritalStatusName()
						.equalsIgnoreCase(jsondata.get("maritalStatusName").toString())) {

					json.put("status", 2);
					json.put("msg", "MaritalStatus Name is already Existing");
				}

			} else {
				String addMaritalStatusObj = md.addMaritalStatus(masMaritalStatus);
				if (addMaritalStatusObj != null && addMaritalStatusObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateMaritalStatusDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		System.out.println("jsonObject+jsonObject :: " + jsonObject);
		if (jsonObject != null) {

			if (jsonObject.get("maritalStatusCode").toString() != null
					&& !jsonObject.get("maritalStatusCode").toString().trim().equalsIgnoreCase("")) {

				List<MasMaritalStatus> msMaritalStatusList = md
						.validateMaritalStatusUpdate(jsonObject.get("maritalStatusName").toString());
				if (msMaritalStatusList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"Marital Status Name is already exist !!!\"}";
				}

				String updateMaritalStatus = md.updateMaritalStatusDetails(
						Long.parseLong(jsonObject.get("maritalStatusId").toString()),
						jsonObject.get("maritalStatusCode").toString(), jsonObject.get("maritalStatusName").toString());

				if (updateMaritalStatus != null && updateMaritalStatus.equalsIgnoreCase("200")) {
					json.put("updateMaritalStatus", updateMaritalStatus);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateMaritalStatusStatus(JSONObject MaritalStatus, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();

		if (jsonObject != null) {
			if (MaritalStatus.get("maritalStatusCode").toString() != null
					&& !MaritalStatus.get("maritalStatusCode").toString().trim().equalsIgnoreCase("")) {

				MasMaritalStatus mMaritalStatus = md
						.checkMaritalStatus(MaritalStatus.get("maritalStatusCode").toString());

				if (mMaritalStatus != null) {
					String maritalStatusStatus = md.updateMaritalStatusStatus(
							Long.parseLong(MaritalStatus.get("maritalStatusId").toString()),
							MaritalStatus.get("maritalStatusCode").toString(), MaritalStatus.get("status").toString());

					if (maritalStatusStatus != null && maritalStatusStatus.equalsIgnoreCase("200")) {
						jsonObject.put("maritalStatusStatus", maritalStatusStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}

	/***************************************
	 * MAS EMPLOYEE CATEGORY
	 ***********************************************************************/

	@Override
	public String getAllEmployeeCategory(JSONObject jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasEmployeeCategory> employeeCategoryList = new ArrayList<MasEmployeeCategory>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasEmployeeCategory>> mapEmployeeCategory = md.getAllEmployeeCategory(jsondata);
			List totalMatches = new ArrayList();
			if (mapEmployeeCategory.get("employeeCategoryList") != null) {
				employeeCategoryList = mapEmployeeCategory.get("employeeCategoryList");
				totalMatches = mapEmployeeCategory.get("totalMatches");
				for (MasEmployeeCategory employeeCategory : employeeCategoryList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (employeeCategory != null) {
						mapObj.put("employeeCategoryId", employeeCategory.getEmployeeCategoryId());
						mapObj.put("employeeCategoryCode", employeeCategory.getEmployeeCategoryCode());
						mapObj.put("employeeCategoryName", employeeCategory.getEmployeeCategoryName());
						mapObj.put("status", employeeCategory.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addEmployeeCategory(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasEmployeeCategory masEmployeeCategory = new MasEmployeeCategory();

			masEmployeeCategory.setEmployeeCategoryCode(
					Long.parseLong((jsondata.get("employeeCategoryCode")).toString().toUpperCase()));
			masEmployeeCategory.setEmployeeCategoryName(jsondata.get("employeeCategoryName").toString().toUpperCase());
			// masMaritalStatus.setLastChgBy(lastChgBy);
			masEmployeeCategory.setStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasEmployeeCategory> checkEmployeeCategoryList = md.validateEmployeeCategory(
					masEmployeeCategory.getEmployeeCategoryCode(), masEmployeeCategory.getEmployeeCategoryName());
			if (checkEmployeeCategoryList != null && checkEmployeeCategoryList.size() > 0) {
				if (checkEmployeeCategoryList.get(0).getEmployeeCategoryCode().toString()
						.equalsIgnoreCase(jsondata.get("employeeCategoryCode").toString())) {

					json.put("status", 2);
					json.put("msg", "checkEmployeeCategoryList Code is already Existing");
				}
				if (checkEmployeeCategoryList.get(0).getEmployeeCategoryName()
						.equalsIgnoreCase(jsondata.get("employeeCategoryName").toString())) {

					json.put("status", 2);
					json.put("msg", "EmployeeCategory Name is already Existing");
				}

			} else {
				String addEmployeeCategoryObj = md.addEmployeeCategory(masEmployeeCategory);
				if (addEmployeeCategoryObj != null && addEmployeeCategoryObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateEmployeeCategoryDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("employeeCategoryCode").toString() != null
					&& !jsonObject.get("employeeCategoryCode").toString().trim().equalsIgnoreCase("")) {

				List<MasEmployeeCategory> msEmployeeCategoryList = md.validateEmployeeCategoryUpdate(
						Long.parseLong(jsonObject.get("employeeCategoryCode").toString()),
						jsonObject.get("employeeCategoryName").toString());
				if (msEmployeeCategoryList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"EmployeeCategory Name is already exist !!!\"}";
				}

				String updateEmployeeCategory = md.updateEmployeeCategoryDetails(
						Long.parseLong(jsonObject.get("employeeCategoryId").toString()),
						jsonObject.get("employeeCategoryCode").toString(),
						jsonObject.get("employeeCategoryName").toString());

				if (updateEmployeeCategory != null && updateEmployeeCategory.equalsIgnoreCase("200")) {
					json.put("updateEmployeeCategory", updateEmployeeCategory);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);
			}
		}
		return json.toString();
	}

	@Override
	public String updateEmployeeCategoryStatus(JSONObject EmployeeCategory, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();

		if (jsonObject != null) {
			if (EmployeeCategory.get("employeeCategoryCode").toString() != null
					&& !EmployeeCategory.get("employeeCategoryCode").toString().trim().equalsIgnoreCase("")) {

				MasEmployeeCategory mEmployeeCategory = md
						.checkEmployeeCategory(EmployeeCategory.get("employeeCategoryCode").toString());

				if (mEmployeeCategory != null) {
					String employeeCategoryStatus = md.updateEmployeeCategoryStatus(
							Long.parseLong(EmployeeCategory.get("employeeCategoryId").toString()),
							EmployeeCategory.get("employeeCategoryCode").toString(),
							EmployeeCategory.get("status").toString());

					if (employeeCategoryStatus != null && employeeCategoryStatus.equalsIgnoreCase("200")) {
						jsonObject.put("employeeCategoryStatus", employeeCategoryStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}

	/***************************************
	 * MAS Administrative Sex
	 ***********************************************************************/

	@Override
	public String getAllAdministrativeSex(JSONObject jsondata, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasAdministrativeSex> administrativeSexList = new ArrayList<MasAdministrativeSex>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasAdministrativeSex>> mapAdministrativeSex = md.getAllAdministrativeSex(jsondata);
			List totalMatches = new ArrayList();
			if (mapAdministrativeSex.get("administrativeSexList") != null) {
				administrativeSexList = mapAdministrativeSex.get("administrativeSexList");
				totalMatches = mapAdministrativeSex.get("totalMatches");
				for (MasAdministrativeSex administrativeSex : administrativeSexList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (administrativeSex != null) {
						mapObj.put("administrativeSexId", administrativeSex.getAdministrativeSexId());
						mapObj.put("administrativeSexCode", administrativeSex.getAdministrativeSexCode());
						mapObj.put("administrativeSexName", administrativeSex.getAdministrativeSexName());
						mapObj.put("status", administrativeSex.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addAdministrativeSex(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasAdministrativeSex masAdministrativeSex = new MasAdministrativeSex();

			masAdministrativeSex
					.setAdministrativeSexCode(jsondata.get("administrativeSexCode").toString().toUpperCase());
			masAdministrativeSex
					.setAdministrativeSexName(jsondata.get("administrativeSexName").toString().toUpperCase());
			// masMaritalStatus.setLastChgBy(lastChgBy);
			masAdministrativeSex.setStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasAdministrativeSex> checkAdministrativeSexList = md.validateAdministrativeSex(
					masAdministrativeSex.getAdministrativeSexCode(), masAdministrativeSex.getAdministrativeSexName());
			if (checkAdministrativeSexList != null && checkAdministrativeSexList.size() > 0) {
				if (checkAdministrativeSexList.get(0).getAdministrativeSexCode()
						.equalsIgnoreCase(jsondata.get("administrativeSexCode").toString())) {

					json.put("status", 2);
					json.put("msg", "checkAdministrativeSexList Code is already Existing");
				}
				if (checkAdministrativeSexList.get(0).getAdministrativeSexName()
						.equalsIgnoreCase(jsondata.get("administrativeSexName").toString())) {

					json.put("status", 2);
					json.put("msg", "AdministrativeSex Name is already Existing");
				}

			} else {
				String addAdministrativeSexObj = md.addAdministrativeSex(masAdministrativeSex);
				if (addAdministrativeSexObj != null && addAdministrativeSexObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateAdministrativeSexDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("administrativeSexCode").toString() != null
					&& !jsonObject.get("administrativeSexCode").toString().trim().equalsIgnoreCase("")) {

				List<MasAdministrativeSex> msAdministrativeSexList = md.validateAdministrativeSexUpdate(
						jsonObject.get("administrativeSexCode").toString(),
						jsonObject.get("administrativeSexName").toString());
				if (msAdministrativeSexList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"Gender Name is already exist !!!\"}";
				}

				String updateAdministrativeSex = md.updateAdministrativeSexDetails(
						Long.parseLong(jsonObject.get("administrativeSexId").toString()),
						jsonObject.get("administrativeSexCode").toString(),
						jsonObject.get("administrativeSexName").toString());

				if (updateAdministrativeSex != null && updateAdministrativeSex.equalsIgnoreCase("200")) {
					json.put("updateAdministrativeSex", updateAdministrativeSex);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateAdministrativeSexStatus(JSONObject AdministrativeSex, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();

		if (jsonObject != null) {
			if (AdministrativeSex.get("administrativeSexCode").toString() != null
					&& !AdministrativeSex.get("administrativeSexCode").toString().trim().equalsIgnoreCase("")) {

				MasAdministrativeSex mAdministrativeSex = md
						.checkAdministrativeSex(AdministrativeSex.get("administrativeSexCode").toString());

				if (mAdministrativeSex != null) {
					String administrativeSexStatus = md.updateAdministrativeSexStatus(
							Long.parseLong(AdministrativeSex.get("administrativeSexId").toString()),
							AdministrativeSex.get("administrativeSexCode").toString(),
							AdministrativeSex.get("status").toString());

					if (administrativeSexStatus != null && administrativeSexStatus.equalsIgnoreCase("200")) {
						jsonObject.put("administrativeSexStatus", administrativeSexStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}

	/***************************************
	 * MAS Medical Category
	 ***********************************************************************/

	@Override
	public String getAllMedicalCategory(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasMedicalCategory> medicalCategoryList = new ArrayList<MasMedicalCategory>();
		List list = new ArrayList();
		System.out.println("jsondata+jsondata :: " + jsondata);
		if (jsondata != null) {
			Map<String, List<MasMedicalCategory>> mapMedicalCategory = md.getAllMedicalCategory(jsondata);
			List totalMatches = new ArrayList();
			if (mapMedicalCategory.get("medicalCategoryList") != null) {
				medicalCategoryList = mapMedicalCategory.get("medicalCategoryList");
				totalMatches = mapMedicalCategory.get("totalMatches");
				for (MasMedicalCategory medicalCategory : medicalCategoryList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (medicalCategory != null) {
						mapObj.put("medicalCategoryId", medicalCategory.getMedicalCategoryId());
						mapObj.put("medicalCategoryCode", medicalCategory.getMedicalCategoryCode());
						mapObj.put("medicalCategoryName", medicalCategory.getMedicalCategoryName());
						mapObj.put("status", medicalCategory.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addMedicalCategory(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasMedicalCategory masMedicalCategory = new MasMedicalCategory();

			masMedicalCategory.setMedicalCategoryCode(Long.parseLong((String) jsondata.get("medicalCategoryCode")));
			masMedicalCategory.setMedicalCategoryName(jsondata.get("medicalCategoryName").toString().toUpperCase());
			// masMaritalStatus.setLastChgBy(lastChgBy);
			masMedicalCategory.setStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasMedicalCategory> checkMedicalCategoryList = md.validateMedicalCategory(
					masMedicalCategory.getMedicalCategoryCode(), masMedicalCategory.getMedicalCategoryName());
			if (checkMedicalCategoryList != null && checkMedicalCategoryList.size() > 0) {
				if ((checkMedicalCategoryList.get(0).getMedicalCategoryCode() + "")
						.equalsIgnoreCase((String) jsondata.get("medicalCategoryCode"))) {

					json.put("status", 2);
					json.put("msg", "checkMedicalCategoryList Code is already Existing");
				}
				if (checkMedicalCategoryList.get(0).getMedicalCategoryName()
						.equalsIgnoreCase(jsondata.get("medicalCategoryName").toString())) {

					json.put("status", 2);
					json.put("msg", "MedicalCategory Name is already Existing");
				}

			} else {
				String addmedicalCategoryObj = md.addMedicalCategory(masMedicalCategory);
				if (addmedicalCategoryObj != null && addmedicalCategoryObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateMedicalCategoryDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("medicalCategoryCode").toString() != null
					&& !jsonObject.get("medicalCategoryCode").toString().trim().equalsIgnoreCase("")) {

				List<MasMedicalCategory> msMedicalCategoryList = md.validateMedicalCategoryUpdate(
						Long.parseLong(jsonObject.get("medicalCategoryCode").toString()),
						jsonObject.get("medicalCategoryName").toString());
				if (msMedicalCategoryList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"MedicalCategory Name is already exist !!!\"}";
				}
				String updateMedicalCategory = md.updateMedicalCategoryDetails(
						Long.parseLong(jsonObject.get("medicalCategoryId").toString()),
						(Long.parseLong(jsonObject.get("medicalCategoryCode").toString())),
						jsonObject.get("medicalCategoryName").toString());

				if (updateMedicalCategory != null && updateMedicalCategory.equalsIgnoreCase("200")) {
					json.put("updateMedicalCategory", updateMedicalCategory);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateMedicalCategoryStatus(JSONObject MedicalCategory, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();

		if (jsonObject != null) {
			if (MedicalCategory.get("medicalCategoryCode").toString() != null
					&& !MedicalCategory.get("medicalCategoryCode").toString().trim().equalsIgnoreCase("")) {

				MasMedicalCategory mMedicalCategory = md
						.checkMedicalCategory(Long.parseLong((MedicalCategory.get("medicalCategoryCode").toString())));

				if (mMedicalCategory != null) {
					String medicalCategoryStatus = md.updateMedicalCategoryStatus(
							Long.parseLong(MedicalCategory.get("medicalCategoryId").toString()),
							(Long.parseLong(MedicalCategory.get("medicalCategoryCode").toString())),
							MedicalCategory.get("status").toString());

					if (medicalCategoryStatus != null && medicalCategoryStatus.equalsIgnoreCase("200")) {
						jsonObject.put("medicalCategoryStatus", medicalCategoryStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}

	/***************************************
	 * MAS Blood Group
	 ***********************************************************************/

	@Override
	public String getAllBloodGroup(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasBloodGroup> bloodGroupList = new ArrayList<MasBloodGroup>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasBloodGroup>> mapBloodGroup = md.getAllBloodGroup(jsondata);
			List totalMatches = new ArrayList();
			if (mapBloodGroup.get("bloodGroupList") != null) {
				bloodGroupList = mapBloodGroup.get("bloodGroupList");
				totalMatches = mapBloodGroup.get("totalMatches");
				for (MasBloodGroup bloodGroup : bloodGroupList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (bloodGroup != null) {
						mapObj.put("bloodGroupId", bloodGroup.getBloodGroupId());
						mapObj.put("bloodGroupCode", bloodGroup.getBloodGroupCode());
						mapObj.put("bloodGroupName", bloodGroup.getBloodGroupName());
						mapObj.put("status", bloodGroup.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addBloodGroup(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasBloodGroup masBloodGroup = new MasBloodGroup();

			masBloodGroup.setBloodGroupCode((String) jsondata.get("bloodGroupCode"));
			masBloodGroup.setBloodGroupName(jsondata.get("bloodGroupName").toString().toUpperCase());
			// masBloodGroup.setLastChgBy(lastChgBy);
			masBloodGroup.setStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasBloodGroup> checkBloodGroupList = md.validateBloodGroup(masBloodGroup.getBloodGroupCode(),
					masBloodGroup.getBloodGroupName());
			if (checkBloodGroupList != null && checkBloodGroupList.size() > 0) {
				if ((checkBloodGroupList.get(0).getBloodGroupCode() + "")
						.equalsIgnoreCase((String) jsondata.get("bloodGroupCode"))) {

					json.put("status", 2);
					json.put("msg", "checkBloodGroupList Code is already Existing");
				}
				if (checkBloodGroupList.get(0).getBloodGroupName()
						.equalsIgnoreCase(jsondata.get("bloodGroupName").toString())) {

					json.put("status", 2);
					json.put("msg", "BloodGroup Name is already Existing");
				}

			} else {
				String addBloodGroupObj = md.addBloodGroup(masBloodGroup);
				if (addBloodGroupObj != null && addBloodGroupObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateBloodGroupDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("bloodGroupCode").toString() != null
					&& !jsonObject.get("bloodGroupCode").toString().trim().equalsIgnoreCase("")) {

				List<MasBloodGroup> msBloodGroupList = md.validateBloodGroupUpdate(
						jsonObject.get("bloodGroupCode").toString(), jsonObject.get("bloodGroupName").toString());
				if (msBloodGroupList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"BloodGroup Name is already exist !!!\"}";
				}

				String updateBloodGroup = md.updateBloodGroupDetails(
						Long.parseLong(jsonObject.get("bloodGroupId").toString()),
						(jsonObject.get("bloodGroupCode").toString()), jsonObject.get("bloodGroupName").toString());

				if (updateBloodGroup != null && updateBloodGroup.equalsIgnoreCase("200")) {
					json.put("updateBloodGroup", updateBloodGroup);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateBloodGroupStatus(JSONObject BloodGroup, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();

		if (jsonObject != null) {
			if (BloodGroup.get("bloodGroupCode").toString() != null
					&& !BloodGroup.get("bloodGroupCode").toString().trim().equalsIgnoreCase("")) {

				MasBloodGroup mBloodGroup = md.checkBloodGroup(BloodGroup.get("bloodGroupCode").toString());

				if (mBloodGroup != null) {
					String bloodGroupStatus = md.updateBloodGroupStatus(
							Long.parseLong(BloodGroup.get("bloodGroupId").toString()),
							(BloodGroup.get("bloodGroupCode").toString()), BloodGroup.get("status").toString());

					if (bloodGroupStatus != null && bloodGroupStatus.equalsIgnoreCase("200")) {
						jsonObject.put("bloodGroupStatus", bloodGroupStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}

	/***************************************
	 * MAS Sample
	 ***********************************************************************/

	@Override
	public String getAllSample(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasSample> sampleList = new ArrayList<MasSample>();
		List list = new ArrayList();
		System.out.println("jsondata_SERVICE" + jsondata);
		if (jsondata != null) {
			Map<String, List<MasSample>> mapSample = md.getAllSample(jsondata);
			List totalMatches = new ArrayList();
			if (mapSample.get("sampleList") != null) {
				sampleList = mapSample.get("sampleList");
				totalMatches = mapSample.get("totalMatches");
				for (MasSample sample : sampleList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (sample != null) {
						mapObj.put("sampleId", sample.getSampleId());
						mapObj.put("sampleCode", sample.getSampleCode());
						mapObj.put("sampleDescription", sample.getSampleDescription());
						mapObj.put("status", sample.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addSample(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		System.out.println("jsondata + SAMPLE:::::" + jsondata);
		if (jsondata != null) {
			String status = "y";
			// Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasSample masSample = new MasSample();

			masSample.setSampleCode(jsondata.get("sampleCode").toString().toUpperCase());
			masSample.setSampleDescription(jsondata.get("sampleDescription").toString().toUpperCase());
			// masSample.setLastChgBy(lastChgBy);
			masSample.setStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasSample> checkSampleList = md.validateSample(masSample.getSampleCode(),
					masSample.getSampleDescription());
			if (checkSampleList != null && checkSampleList.size() > 0) {
				if (checkSampleList.get(0).getSampleCode().equalsIgnoreCase(jsondata.get("sampleCode").toString())) {

					json.put("status", 2);
					json.put("msg", "checkSampleList Code is already Existing");
				}
				if (checkSampleList.get(0).getSampleDescription()
						.equalsIgnoreCase(jsondata.get("sampleDescription").toString())) {

					json.put("status", 2);
					json.put("msg", "Sample Name is already Existing");
				}

			} else {
				String addSampleObj = md.addSample(masSample);
				if (addSampleObj != null && addSampleObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateSampleDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("sampleCode").toString() != null
					&& !jsonObject.get("sampleCode").toString().trim().equalsIgnoreCase("")) {

				List<MasSample> msSampleList = md.validateSampleUpdate(jsonObject.get("sampleCode").toString(),
						jsonObject.get("sampleDescription").toString());
				if (msSampleList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"Sample Name is already exist !!!\"}";
				}

				String updateSample = md.updateSampleDetails(Long.parseLong(jsonObject.get("sampleId").toString()),
						jsonObject.get("sampleCode").toString(), jsonObject.get("sampleDescription").toString());

				if (updateSample != null && updateSample.equalsIgnoreCase("200")) {
					json.put("updateSample", updateSample);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateSampleStatus(JSONObject Sample, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();

		if (jsonObject != null) {
			if (Sample.get("sampleCode").toString() != null
					&& !Sample.get("sampleCode").toString().trim().equalsIgnoreCase("")) {

				MasSample mSample = md.checkSample(Sample.get("sampleCode").toString());

				if (mSample != null) {
					String sampleStatus = md.updateSampleStatus(Long.parseLong(Sample.get("sampleId").toString()),
							Sample.get("sampleCode").toString(), Sample.get("status").toString());

					if (sampleStatus != null && sampleStatus.equalsIgnoreCase("200")) {
						jsonObject.put("sampleStatus", sampleStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}

	/***************************************
	 * MAS UOM
	 ***********************************************************************/

	@Override
	public String getAllUOM(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasUOM> UOMList = new ArrayList<MasUOM>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasUOM>> mapUOM = md.getAllUOM(jsondata);
			List totalMatches = new ArrayList();
			if (mapUOM.get("UOMList") != null) {
				UOMList = mapUOM.get("UOMList");
				totalMatches = mapUOM.get("totalMatches");
				for (MasUOM UOM : UOMList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (UOM != null) {
						mapObj.put("UOMId", UOM.getUOMId());
						mapObj.put("UOMCode", UOM.getUOMCode());
						mapObj.put("UOMName", UOM.getUOMName());
						mapObj.put("status", UOM.getUOMStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addUOM(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasUOM masUOM = new MasUOM();

			masUOM.setUOMCode(jsondata.get("UOMCode").toString().toUpperCase());
			masUOM.setUOMName(jsondata.get("UOMName").toString().toUpperCase());
			// masUOM.setLastChgBy(lastChgBy);
			masUOM.setUOMStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasUOM> checkUOMList = md.validateUOM(masUOM.getUOMCode(), masUOM.getUOMName());
			if (checkUOMList != null && checkUOMList.size() > 0) {
				if (checkUOMList.get(0).getUOMCode().equalsIgnoreCase(jsondata.get("UOMCode").toString())) {

					json.put("status", 2);
					json.put("msg", "checkUOMList Code is already Existing");
				}
				if (checkUOMList.get(0).getUOMName().equalsIgnoreCase(jsondata.get("UOMName").toString())) {

					json.put("status", 2);
					json.put("msg", "UOM Name is already Existing");
				}

			} else {
				String addUOMObj = md.addUOM(masUOM);
				if (addUOMObj != null && addUOMObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateUOMDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("UOMCode").toString() != null
					&& !jsonObject.get("UOMCode").toString().trim().equalsIgnoreCase("")) {

				List<MasUOM> msUOMList = md.validateUOMUpdate(jsonObject.get("UOMCode").toString(),
						jsonObject.get("UOMName").toString());
				if (msUOMList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"UOM Name is already exist !!!\"}";
				}

				String updateUOM = md.updateUOMDetails(Long.parseLong(jsonObject.get("UOMId").toString()),
						jsonObject.get("UOMCode").toString(), jsonObject.get("UOMName").toString());

				if (updateUOM != null && updateUOM.equalsIgnoreCase("200")) {
					json.put("updateUOM", updateUOM);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateUOMStatus(JSONObject UOM, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		if (UOM != null) {
			if (UOM.get("UOMCode").toString() != null && !UOM.get("UOMCode").toString().trim().equalsIgnoreCase("")) {

				MasUOM mUOM = md.checkUOM(UOM.get("UOMCode").toString());

				if (mUOM != null) {
					String UOMStatus = md.updateUOMStatus(Long.parseLong(UOM.get("UOMId").toString()),
							UOM.get("UOMCode").toString(), UOM.get("status").toString());

					if (UOMStatus != null && UOMStatus.equalsIgnoreCase("200")) {
						jsonObject.put("UOMStatus", UOMStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}

	/***************************************
	 * MAS ItemUnit
	 ***********************************************************************/

	@Override
	public String getAllItemUnit(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasStoreUnit> ItemUnitList = new ArrayList<MasStoreUnit>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasStoreUnit>> mapItemUnit = md.getAllItemUnit(jsondata);
			List totalMatches = new ArrayList();
			if (mapItemUnit.get("ItemUnitList") != null) {
				ItemUnitList = mapItemUnit.get("ItemUnitList");
				totalMatches = mapItemUnit.get("totalMatches");
				for (MasStoreUnit ItemUnit : ItemUnitList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (ItemUnit != null) {
						mapObj.put("storeUnitId", ItemUnit.getStoreUnitId());
						mapObj.put("storeUnitName", ItemUnit.getStoreUnitName());
						mapObj.put("status", ItemUnit.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addItemUnit(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasStoreUnit masStoreUnit = new MasStoreUnit();

			masStoreUnit.setStoreUnitName(jsondata.get("storeUnitName").toString().toUpperCase());
			// masStoreUnit.setLastChgBy(lastChgBy);
			masStoreUnit.setStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasStoreUnit> checkItemUnitList = md.validateItemUnit(masStoreUnit.getStoreUnitName());
			if (checkItemUnitList != null && checkItemUnitList.size() > 0) {

				if (checkItemUnitList.get(0).getStoreUnitName()
						.equalsIgnoreCase(jsondata.get("storeUnitName").toString())) {

					json.put("status", 2);
					json.put("msg", "Item Unit Name is already Existing");
				}

			} else {
				String addItemUnitObj = md.addItemUnit(masStoreUnit);
				if (addItemUnitObj != null && addItemUnitObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateItemUnitDetails(JSONObject jsonObject, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("storeUnitName").toString() != null
					&& !jsonObject.get("storeUnitName").toString().trim().equalsIgnoreCase("")) {

				List<MasStoreUnit> msStoreUnitList = md
						.validateItemUnitUpdate(jsonObject.get("storeUnitName").toString());
				if (msStoreUnitList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"StoreUnit Name is already exist !!!\"}";
				}

				String updateItemUnitStatus = md.updateItemUnitDetails(
						Long.parseLong(jsonObject.get("storeUnitId").toString()),
						jsonObject.get("storeUnitName").toString());

				if (updateItemUnitStatus != null && updateItemUnitStatus.equalsIgnoreCase("200")) {
					json.put("updateItemUnitStatus", updateItemUnitStatus);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);

			}

		}
		return json.toString();
	}

	@Override
	public String updateItemUnitStatus(JSONObject ItemUnit, HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObject = new JSONObject();
		if (ItemUnit != null) {
			if (ItemUnit.get("storeUnitName").toString() != null
					&& !ItemUnit.get("storeUnitName").toString().trim().equalsIgnoreCase("")) {

				MasStoreUnit mItemUnit = md.checkItemUnit(ItemUnit.get("storeUnitName").toString());

				if (mItemUnit != null) {
					String itemUnitStatus = md.updateItemUnitStatus(
							Long.parseLong(ItemUnit.get("storeUnitId").toString()),
							ItemUnit.get("storeUnitName").toString(), ItemUnit.get("status").toString());

					if (itemUnitStatus != null && itemUnitStatus.equalsIgnoreCase("200")) {
						jsonObject.put("itemUnitStatus", itemUnitStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}

	/********************************************************
	 * MAINCHARGECODE MASTER
	 *********************************************************/

	@Override
	public String addMainChargecode(JSONObject json, HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObj = new JSONObject();
		MasMainChargecode masMainChargecode = new MasMainChargecode();
		if (!json.equals(null)) {

			if (json.get("mainChargecodeCode") == null) {
				return "{\"status\":\"0\",\"msg\":\"MainChargecode Code is not contain in json data or it will be null or blank please check\"}";
			}

			if (json.get("mainChargecodeName") == null) {
				return "{\"status\":\"0\",\"msg\":\"MainChargecode Name is not contain in json data or it will be null or blank please check\"}";
			}

			else {
				masMainChargecode.setMainChargecodeCode(json.get("mainChargecodeCode").toString().toUpperCase());
				masMainChargecode.setMainChargecodeName(json.get("mainChargecodeName").toString().toUpperCase());
				/*
				 * long d = System.currentTimeMillis(); Date date = new Date(d);
				 * users.setLastChgDate(date);
				 */

				// String lastChgTime = new
				// SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

				// masRank.setLastChgTime(lastChgTime);
				MasMainChargecode usr = new MasMainChargecode();
				usr.setMainChargecodeId(new Long(1)); // userId will be fetch from session
				// masTrade.setLastChgBy(new Long(1));
				masMainChargecode.setStatus("y");

				MasDepartment masDepartment = new MasDepartment();
				masDepartment.setDepartmentId(Long.parseLong(json.get("departmentId").toString()));
				masMainChargecode.setMasDepartment(masDepartment);
				;

				List<MasMainChargecode> masMainChargecode1 = md.validateMainChargecode(
						masMainChargecode.getMainChargecodeCode().toString(),
						masMainChargecode.getMainChargecodeName().toString());
				if (masMainChargecode1.size() != 0) {
					if (masMainChargecode1 != null && masMainChargecode1.size() > 0) {
						if (masMainChargecode1.get(0).getMainChargecodeCode()
								.equalsIgnoreCase(json.get("mainChargecodeCode").toString())) {

							return "{\"status\":\"2\",\"msg\":\"MainChargecode Code is already Existing.\"}";
						}

						if (masMainChargecode1.get(0).getMainChargecodeName()
								.equalsIgnoreCase(json.get("mainChargecodeName").toString())) {

							return "{\"status\":\"2\",\"msg\":\"MainChargecode Name is already Existing.\"}";
						}
					}
				} else {
					String masMainChargecodeObj = md.addMainChargecode(masMainChargecode);
					if (masMainChargecodeObj != null && masMainChargecodeObj.equalsIgnoreCase("200")) {
						jsonObj.put("status", 1);
						jsonObj.put("msg", "MainChargecode has been Successfully Added !!");

					} else if (masMainChargecodeObj != null && masMainChargecodeObj.equalsIgnoreCase("403")) {
						jsonObj.put("status", 0);
						jsonObj.put("msg", "You are not authorized person!!!");

					} else {
						jsonObj.put("msg", masMainChargecodeObj);
						jsonObj.put("status", 0);
					}
				}
			}
		} else {
			jsonObj.put("msg", "Cannot Contains Any Data!!!");
			jsonObj.put("status", 0);
		}

		return jsonObj.toString();

	}

	@Override
	public String getAllMainChargecode(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasMainChargecode> mainChargecodeList = new ArrayList<MasMainChargecode>();
		List list = new ArrayList();
		if (jsonObj != null) {
			Map<String, List<MasMainChargecode>> mapMainChargecode = md.getAllMainChargecode(jsonObj);
			List totalMatches = new ArrayList();
			if (mapMainChargecode.get("mainChargecodeList") != null) {
				mainChargecodeList = mapMainChargecode.get("mainChargecodeList");
				totalMatches = mapMainChargecode.get("totalMatches");
				for (MasMainChargecode mainChargecode : mainChargecodeList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();

					if (mainChargecode.getMasDepartment() != null) {
						mapObj.put("mainChargecodeId", mainChargecode.getMainChargecodeId());
						mapObj.put("mainChargecodeCode", mainChargecode.getMainChargecodeCode());
						mapObj.put("mainChargecodeName", mainChargecode.getMainChargecodeName());
						mapObj.put("status", mainChargecode.getStatus());
						mapObj.put("departmentName", mainChargecode.getMasDepartment().getDepartmentName());
						mapObj.put("departmentId", mainChargecode.getMasDepartment().getDepartmentId());

						list.add(mapObj);
					}
				}
				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String updateMainChargecodeDetails(HashMap<String, Object> mainChargecode, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (mainChargecode.get("mainChargecodeId") != null
				&& !mainChargecode.get("mainChargecodeId").toString().equalsIgnoreCase("")) {

			List<MasMainChargecode> mainChargecodeList = md.validateMainChargecodeUpdate(
					mainChargecode.get("mainChargecodeCode").toString(),
					mainChargecode.get("mainChargecodeName").toString());
			if (mainChargecodeList.size() > 0) {
				return "{\"status\":\"0\",\"msg\":\"MainChargecode Name is already exist !!!\"}";
			}

			String mainChargecodeUpdate = md.updateMainChargecode(
					Long.parseLong(mainChargecode.get("mainChargecodeId").toString()),
					mainChargecode.get("mainChargecodeCode").toString(),
					mainChargecode.get("mainChargecodeName").toString(),
					Long.parseLong(mainChargecode.get("departmentId").toString()));
			if (mainChargecodeUpdate != null && !mainChargecodeUpdate.equalsIgnoreCase("")) {
				json.put("mainChargecodeUpdate", mainChargecodeUpdate);
				json.put("msg", "Successfully Updated!!!");
				json.put("status", 1);
			} else if (mainChargecodeUpdate == null && mainChargecodeUpdate.equalsIgnoreCase("")) {
				json.put("msg", "Not Updated!!!");
				json.put("status", 0);
			}

			else {
				return "{\"status\":\"0\",\"msg\":\"MainChargecode Code is not available !!!\"}";
			}
		}

		return json.toString();

	}

	@Override
	public String updateMainChargecodeStatus(HashMap<String, Object> mainChargecode, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json = new JSONObject();
		if (mainChargecode.get("mainChargecodeCode") != null
				&& !mainChargecode.get("mainChargecodeName").toString().equalsIgnoreCase("")) {
			MasMainChargecode checkMainChargecode = md
					.checkMainChargecode(mainChargecode.get("mainChargecodeCode").toString());
			if (checkMainChargecode != null) {
				String mainChargecodeStatus = md.updateMainChargecodeStatus(
						Long.parseLong(mainChargecode.get("mainChargecodeId").toString()),
						mainChargecode.get("mainChargecodeCode").toString(), mainChargecode.get("status").toString());
				if (mainChargecodeStatus != null && mainChargecodeStatus.equalsIgnoreCase("200")) {
					json.put("mainChargecodeStatus", mainChargecodeStatus);
					json.put("msg", "Status Updated Succsefully");
					json.put("status", 1);
				} else {
					json.put("msg", "Status Not Updated");
					json.put("status", 0);
				}
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"MainChargecode code is not available !!!\"}";
		}

		return json.toString();

	}

	@Override
	public String getDepartmentList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		List<MasDepartment> departmentList = md.getDepartmentsList();
		if (departmentList != null && departmentList.size() > 0) {

			jsonObj.put("data", departmentList);
			jsonObj.put("count", departmentList.size());
			jsonObj.put("status", 1);
		} else {
			jsonObj.put("data", departmentList);
			jsonObj.put("count", departmentList.size());
			jsonObj.put("msg", "No Record Found");
			jsonObj.put("status", 0);
		}
		return jsonObj.toString();
	}

	/********************************************************
	 * USERS MASTER
	 *********************************************************/

	@Override
	public String addUsers(JSONObject json, HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObj = new JSONObject();
		Users users = new Users();
		System.out.println("ADD_UserSERVICE"+json);
		if (!json.equals(null)) {

			if (json.get("loginName") == null) {
				return "{\"status\":\"0\",\"msg\":\"Login Name is not contain in json data or it will be null or blank please check\"}";
			}

			if (json.get("firstName") == null) {
				return "{\"status\":\"0\",\"msg\":\"First Name is not contain in json data or it will be null or blank please check\"}";
			}

			else {
				users.setLoginName(json.get("loginName").toString().toUpperCase());
				users.setUserName(json.get("loginName").toString().toUpperCase());
				users.setPassword(json.get("loginName").toString().toUpperCase());
				users.setLastName(json.get("firstName").toString().toUpperCase());
				users.setFirstName(json.get("firstName").toString().toUpperCase());
				/*
				 * long d = System.currentTimeMillis(); Date date = new Date(d);
				 * users.setLastChgDate(date);
				 */

				// String lastChgTime = new
				// SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

				// masRank.setLastChgTime(lastChgTime);
				//Users usr = new Users();
				//usr.setUserId(new Long(1)); // userId will be fetch from session
				// masTrade.setLastChgBy(new Long(1));
				users.setStatus("y");

				MasHospital masHospital = new MasHospital();
				masHospital.setHospitalId(Long.parseLong(json.get("hospitalId").toString()));
				users.setMasHospital(masHospital);

				List<Users> users1 = md.validateUsers(users.getLoginName().toString(), users.getFirstName().toString());
				if (users1.size() != 0) {
					if (users1 != null && users1.size() > 0) {
						if (users1.get(0).getLoginName().equalsIgnoreCase(json.get("loginName").toString())) {

							return "{\"status\":\"2\",\"msg\":\"Login Name is already Existing.\"}";
						}
						if (users1.get(0).getLoginName().equalsIgnoreCase(json.get("loginName").toString())) {

							return "{\"status\":\"2\",\"msg\":\"Login Name is already Existing.\"}";
						}

						if (users1.get(0).getFirstName().equalsIgnoreCase(json.get("firstName").toString())) {

							return "{\"status\":\"2\",\"msg\":\"First Name is already Existing.\"}";
						}
					}
				} else {
					String masUsersObj = md.addUsers(users);
					if (masUsersObj != null && masUsersObj.equalsIgnoreCase("200")) {
						jsonObj.put("status", 1);
						jsonObj.put("msg", "User has been Successfully Added !!");

					} else if (masUsersObj != null && masUsersObj.equalsIgnoreCase("403")) {
						jsonObj.put("status", 0);
						jsonObj.put("msg", "You are not authorized person!!!");

					} else {
						jsonObj.put("msg", masUsersObj);
						jsonObj.put("status", 0);
					}
				}
			}
		} else {
			jsonObj.put("msg", "Cannot Contains Any Data!!!");
			jsonObj.put("status", 0);
		}

		return jsonObj.toString();

	}

	@Override
	public String getAllUsers(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<Users> userList = new ArrayList<Users>();
		List list = new ArrayList();
		if (jsonObj != null) {
			Map<String, List<Users>> mapUsers = md.getAllUsers(jsonObj);
			List totalMatches = new ArrayList();
			if (mapUsers.get("usersList") != null) {
				userList = mapUsers.get("usersList");
				totalMatches = mapUsers.get("totalMatches");
				for (Users users : userList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();

					
						mapObj.put("userId", users.getUserId());
						mapObj.put("loginName", users.getLoginName());
						mapObj.put("firstName", users.getFirstName());
						mapObj.put("status", users.getStatus());
						mapObj.put("hospitalName", users.getMasHospital()!=null?users.getMasHospital().getHospitalName():"");
						mapObj.put("hospitalId", users.getMasHospital()!=null?users.getMasHospital().getHospitalId():"0");

						list.add(mapObj);
					
				}
				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String updateUsersDetails(HashMap<String, Object> users, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (users.get("userId") != null && !users.get("userId").toString().equalsIgnoreCase("")) {

			List<Users> usersList = md.validateUsersUpdate(users.get("loginName").toString(),(Long.parseLong(users.get("hospitalId").toString()))
					);
			if (usersList.size() > 0) {
				return "{\"status\":\"0\",\"msg\":\"User Name is already exist !!!\"}";
			}

			String usersUpdate = md.updateUsers(Long.parseLong(users.get("userId").toString()),
					users.get("loginName").toString(), users.get("firstName").toString(),
					Long.parseLong(users.get("hospitalId").toString()));
			if (usersUpdate != null && !usersUpdate.equalsIgnoreCase("")) {
				json.put("usersUpdate", usersUpdate);
				json.put("msg", "Successfully Updated!!!");
				json.put("status", 1);
			} else if (usersUpdate == null && usersUpdate.equalsIgnoreCase("")) {
				json.put("msg", "Not Updated!!!");
				json.put("status", 0);
			}

			else {
				return "{\"status\":\"0\",\"msg\":\"Login Name is not available !!!\"}";
			}
		}

		return json.toString();

	}

	@Override
	public String updateUsersStatus(HashMap<String, Object> users, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json = new JSONObject();
		if (users.get("loginName") != null && !users.get("loginName").toString().equalsIgnoreCase("")) {
			Users checkUsers = md.checkUsers(users.get("loginName").toString());
			if (checkUsers != null) {
				String usersStatus = md.updateUsersStatus(Long.parseLong(users.get("userId").toString()),
						users.get("loginName").toString(), users.get("status").toString());
				if (usersStatus != null && usersStatus.equalsIgnoreCase("200")) {
					json.put("usersStatus", usersStatus);
					json.put("msg", "Status Updated Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Status Not Updated");
					json.put("status", 0);
				}
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"loginName is not available !!!\"}";
		}

		return json.toString();

	}

	@Override
	public String getHospitalList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		List<MasHospital> hospitalList = md.getHospitalList();
		if (hospitalList != null && hospitalList.size() > 0) {

			jsonObj.put("data", hospitalList);
			jsonObj.put("count", hospitalList.size());
			jsonObj.put("status", 1);
		} else {
			jsonObj.put("data", hospitalList);
			jsonObj.put("count", hospitalList.size());
			jsonObj.put("msg", "No Record Found");
			jsonObj.put("status", 0);
		}
		return jsonObj.toString();
	}

	/***************************************
	 * MAS ROLE
	 ***********************************************************************/

	@Override
	public String getAllRole(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasRole> roleList = new ArrayList<MasRole>();
		List list = new ArrayList();
		if (jsondata != null) {
			Map<String, List<MasRole>> mapRole = md.getAllRole(jsondata);
			List totalMatches = new ArrayList();
			if (mapRole.get("roleList") != null) {
				roleList = mapRole.get("roleList");
				totalMatches = mapRole.get("totalMatches");
				for (MasRole role : roleList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();
					if (role != null) {
						mapObj.put("roleId", role.getRoleId());
						mapObj.put("roleCode", role.getRoleCode());
						mapObj.put("roleName", role.getRoleName());
						mapObj.put("status", role.getStatus());

						list.add(mapObj);
					}
				}

				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String addRole(JSONObject jsondata, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsondata != null) {
			String status = "y";
			Long lastChgBy = new Long(1);

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasRole masRole = new MasRole();

			masRole.setRoleCode((jsondata.get("roleCode")).toString().toUpperCase());
			masRole.setRoleName(jsondata.get("roleName").toString().toUpperCase());
			// masMaritalStatus.setLastChgBy(lastChgBy);
			masRole.setStatus("Y");
			// masMaritalStatus.setLastChgDate(date);

			List<MasRole> checkRoleList = md.validateRole(masRole.getRoleCode(), masRole.getRoleName());
			if (checkRoleList != null && checkRoleList.size() > 0) {
				if (checkRoleList.get(0).getRoleCode().toString()
						.equalsIgnoreCase(jsondata.get("roleCode").toString())) {

					json.put("status", 2);
					json.put("msg", "checkRoleList Code is already Existing");
				}
				if (checkRoleList.get(0).getRoleName().equalsIgnoreCase(jsondata.get("roleName").toString())) {

					json.put("status", 2);
					json.put("msg", "Role Name is already Existing");
				}

			} else {
				String addRoleObj = md.addRole(masRole);
				if (addRoleObj != null && addRoleObj.equalsIgnoreCase("200")) {
					json.put("status", 1);
					json.put("msg", "Successfully Added");
				} else {
					json.put("status", 0);
					json.put("msg", "Not Added");
				}
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}

		return json.toString();
	}

	@Override
	public String updateRoleDetails(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {

			if (jsonObject.get("roleCode").toString() != null
					&& !jsonObject.get("roleCode").toString().trim().equalsIgnoreCase("")) {

				List<MasRole> msRoleList = md.validateRoleUpdate(jsonObject.get("roleCode").toString(),
						jsonObject.get("roleName").toString());
				if (msRoleList.size() > 0) {
					return "{\"status\":\"0\",\"msg\":\"Role Name is already exist !!!\"}";
				}

				String updateRole = md.updateRoleDetails(Long.parseLong(jsonObject.get("roleId").toString()),
						jsonObject.get("roleCode").toString(), jsonObject.get("roleName").toString());

				if (updateRole != null && updateRole.equalsIgnoreCase("200")) {
					json.put("updateRole", updateRole);
					json.put("msg", "Successfully Updated.");
					json.put("status", 1);
				} else {
					json.put("msg", "Not Updated.");
					json.put("status", 0);

				}

			} else {
				json.put("msg", "Data Not Found");
				json.put("status", 0);
			}
		}
		return json.toString();
	}

	@Override
	public String updateRoleStatus(JSONObject role, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();

		if (jsonObject != null) {
			if (role.get("roleCode").toString() != null
					&& !role.get("roleCode").toString().trim().equalsIgnoreCase("")) {

				MasRole mRole = md.checkRole(role.get("roleCode").toString());

				if (mRole != null) {
					String roleStatus = md.updateRoleStatus(Long.parseLong(role.get("roleId").toString()),
							role.get("roleCode").toString(), role.get("status").toString());

					if (roleStatus != null && roleStatus.equalsIgnoreCase("200")) {
						jsonObject.put("roleStatus", roleStatus);
						jsonObject.put("msg", "Status Updated Successfully");
						jsonObject.put("status", 1);
					} else {
						jsonObject.put("msg", "Status Not Updated");
						jsonObject.put("status", 0);
					}
				} else {
					jsonObject.put("msg", "Data Not Found");
				}

			}
		}

		return jsonObject.toString();
	}
	/********************************************************
	 * RANGE MASTER
	 *********************************************************/

	@Override
	public String addRange(JSONObject json, HttpServletRequest request, HttpServletResponse response) {

		JSONObject jsonObj = new JSONObject();
		MasRange range = new MasRange();
		
		if (!json.equals(null)) {

			if (json.get("fromRange") == null) {
				return "{\"status\":\"0\",\"msg\":\"From Range is not contain in json data or it will be null or blank please check\"}";
			}

			if (json.get("toRange") == null) {
				return "{\"status\":\"0\",\"msg\":\"To Range is not contain in json data or it will be null or blank please check\"}";
			}
			if(json.get("rangeFlag")==null) {
				return "{\"status\":\"0\",\"msg\":\"Range Type is not contain in json data or it will be null or blank please check\"}";
			}

			else {
				range.setFromRange(Long.parseLong(json.get("fromRange").toString().toUpperCase()));
				range.setToRange(Long.parseLong(json.get("toRange").toString().toUpperCase()));
				//Users usr = new Users();
				//usr.setUserId(new Long(1)); // userId will be fetch from session
				// range.setLastChgBy(new Long(1));
				range.setRangeFlag(json.get("rangeFlag").toString().toUpperCase());
				range.setStatus("y");

				MasAdministrativeSex masAdministrativeSex = new MasAdministrativeSex();
				masAdministrativeSex.setAdministrativeSexId(Long.parseLong(json.get("administrativeSexId").toString()));
				range.setMasAdministrativeSex(masAdministrativeSex);

				List<MasRange> range1 = md.validateRange(Long.parseLong(range.getFromRange().toString()),(Long.parseLong(range.getToRange().toString())),range.getRangeFlag().toString());
				if (range1.size() != 0) {
					if (range1 != null && range1.size() > 0) {
						if (range1.get(0).getFromRange().equals(json.get("fromRange").toString())){

							return "{\"status\":\"2\",\"msg\":\" From Range is already Existing.\"}";
						}
						if (range1.get(0).getToRange().equals(json.get("toRange").toString())) {

							return "{\"status\":\"2\",\"msg\":\"To Range is already Existing.\"}";
						}
					}
				} else {
					String masRangeObj = md.addRange(range);
					if (masRangeObj != null && masRangeObj.equalsIgnoreCase("200")) {
						jsonObj.put("status", 1);
						jsonObj.put("msg", "Range has been Successfully Added !!");

					} else if (masRangeObj != null && masRangeObj.equalsIgnoreCase("403")) {
						jsonObj.put("status", 0);
						jsonObj.put("msg", "You are not authorized person!!!");

					} else {
						jsonObj.put("msg", masRangeObj);
						jsonObj.put("status", 0);
					}
				}
			}
		} else {
			jsonObj.put("msg", "Cannot Contains Any Data!!!");
			jsonObj.put("status", 0);
		}

		return jsonObj.toString();
	}
	
	@Override
	public String getAllRange(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MasRange> rangeList = new ArrayList<MasRange>();
		List list = new ArrayList();
		System.out.println("jsonObj+RANGE"+jsonObj);
		if (jsonObj != null) {
			Map<String, List<MasRange>> mapRange = md.getAllRange(jsonObj);
			List totalMatches = new ArrayList();
			if (mapRange.get("rangeList") != null) {
				rangeList = mapRange.get("rangeList");
				totalMatches = mapRange.get("totalMatches");
				for (MasRange range : rangeList) {
					HashMap<String, Object> mapObj = new HashMap<String, Object>();

					
						mapObj.put("rangeId", range.getRangeId());
						mapObj.put("fromRange", range.getFromRange());
						mapObj.put("toRange", range.getToRange());
						mapObj.put("status", range.getStatus());
						mapObj.put("rangeFlag", range.getRangeFlag());
						mapObj.put("administrativeSexName", range.getMasAdministrativeSex()!=null?range.getMasAdministrativeSex().getAdministrativeSexName():"");
						mapObj.put("administrativeSexId", range.getMasAdministrativeSex()!=null?range.getMasAdministrativeSex().getAdministrativeSexId():"0");

						list.add(mapObj);
					
				}
				if (list != null && list.size() > 0) {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "successfully");
					json.put("status", 1);
				} else {
					json.put("data", list);
					json.put("count", totalMatches.size());
					json.put("msg", "No Record Found");
					json.put("status", 0);
				}

			} else {
				json.put("statut", 0);
				json.put("msg", "No Record Found");
			}

		} else {
			json.put("msg", "No Record Found");
			json.put("status", 0);
		}
		return json.toString();
	}

	@Override
	public String updateRangeDetails(HashMap<String, Object> range, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (range.get("rangeId") != null && !range.get("rangeId").toString().equalsIgnoreCase("")) {
			String rangeUpdate = md.updateRange(Long.parseLong(range.get("rangeId").toString()),
					Long.parseLong(range.get("fromRange").toString()), Long.parseLong(range.get("toRange").toString()));
			if (rangeUpdate != null && !rangeUpdate.equalsIgnoreCase("")) {
				json.put("rangeUpdate", rangeUpdate);
				json.put("msg", "Successfully Updated!!!");
				json.put("status", 1);
			} else if (rangeUpdate == null && rangeUpdate.equalsIgnoreCase("")) {
				json.put("msg", "Not Updated!!!");
				json.put("status", 0);
			}

			else {
				return "{\"status\":\"0\",\"msg\":\"From Range is not available !!!\"}";
			}
		}

		return json.toString();

	}

	@Override
	public String updateRangeStatus(HashMap<String, Object> range, HttpServletRequest request,
			HttpServletResponse response) {

		JSONObject json = new JSONObject();
		if (range.get("fromRange") != null && !range.get("fromRange").toString().equalsIgnoreCase("")) {
			MasRange checkRange = md.checkRange(Long.parseLong(range.get("fromRange").toString()));
			if (checkRange != null) {
				String rangeStatus = md.updateRangeStatus(Long.parseLong(range.get("rangeId").toString()),
						Long.parseLong(range.get("fromRange").toString()), range.get("status").toString());
				if (rangeStatus != null && rangeStatus.equalsIgnoreCase("200")) {
					json.put("rangeStatus", rangeStatus);
					json.put("msg", "Status Updated Successfully");
					json.put("status", 1);
				} else {
					json.put("msg", "Status Not Updated");
					json.put("status", 0);
				}
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"fromRange is not available !!!\"}";
		}

		return json.toString();

	}

	@Override
	public String getGenderList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();
		List<MasAdministrativeSex> genderList = md.getGenderList();
		if (genderList != null && genderList.size() > 0) {

			jsonObj.put("data", genderList);
			jsonObj.put("count", genderList.size());
			jsonObj.put("status", 1);
		} else {
			jsonObj.put("data", genderList);
			jsonObj.put("count", genderList.size());
			jsonObj.put("msg", "No Record Found");
			jsonObj.put("status", 0);
		}
		return jsonObj.toString();
	}
	@Override
	public String getMasRange(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<Object> listObject = new ArrayList<Object>();
		try {
			if(jsonObject!=null) {				
				List<MasRange> rangeListObject = md.getMasRange();
				if(CollectionUtils.isNotEmpty(rangeListObject)) {
					for(Iterator<?> iterator = rangeListObject.iterator();iterator.hasNext();) {
						Map<String, Object> mapRange = new HashMap<String, Object>();
						MasRange masRange = (MasRange)iterator.next();
						mapRange.put("rangeId", masRange.getRangeId());
						mapRange.put("genderId", masRange.getMasAdministrativeSex().getAdministrativeSexId());
						mapRange.put("fromRange", masRange.getFromRange());
						mapRange.put("toRange", masRange.getToRange());
						mapRange.put("rangeFlag", masRange.getRangeFlag());
						if(masRange.getRangeFlag().equalsIgnoreCase("A")) {
							String fromAgeToAge = masRange.getFromRange().toString().concat("-").concat(masRange.getToRange().toString());
							mapRange.put("fromAgeToAge", fromAgeToAge);
						}
						if(masRange.getRangeFlag().equalsIgnoreCase("H")) {
							String fromHeightToHeight = masRange.getFromRange().toString().concat("-").concat(masRange.getToRange().toString());
							mapRange.put("fromHeightToHeight", fromHeightToHeight);
						}
						listObject.add(mapRange);
					}
				}
				
				if(listObject!=null && listObject.size()>0) {
					json.put("data", listObject);					
					json.put("count", listObject.size());
					json.put("msg", "Record Fetched Successfully");
					json.put("status", 1);
				}else {
					json.put("data", listObject);					
					json.put("count", listObject.size());
					json.put("msg", "Record Not Fetched");
					json.put("status", 0);
				}
			}else {
				json.put("msg", "Invalid Input Parameter");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
