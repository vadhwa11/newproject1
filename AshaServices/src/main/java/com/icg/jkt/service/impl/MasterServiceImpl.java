package com.icg.jkt.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.MasterDao;
import com.icg.jkt.entity.MasAppointmentType;
import com.icg.jkt.entity.MasCommand;
import com.icg.jkt.entity.MasCommandType;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasDepartmentType;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasEmpanelledHospital;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasFrequency;
import com.icg.jkt.entity.MasHospital;
import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.entity.MasIdealWeight;
import com.icg.jkt.entity.MasNursingCare;
import com.icg.jkt.entity.MasRelation;
import com.icg.jkt.entity.MasState;
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
	public String getTradeDetails(HashMap<String, String> tradePayload, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		try {
			if (tradePayload.get("tradeName") == null && tradePayload.get("tradeName").trim().equalsIgnoreCase("")) {
				return "{\"status\":\"0\",\"msg\":\"Invalid Trade Name !!!\"}";
			} else {
				MasTrade chkTrade = md.checkTrade(tradePayload.get("tradeName"));
				if (chkTrade != null) {
					List<MasTrade> masTradeLst = md.getTradeDetails(tradePayload.get("tradeName"));

					if (masTradeLst != null && masTradeLst.size() > 0) {
						json.put("masTradeLst", masTradeLst);
						json.put("msg", "List of State get sucessfully... ");
						json.put("status", "1");
					}

				} else {
					return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
				}
			}

			return json.toString();
		} finally {
			System.out.println("Exception Occured");
		}
	}

	@Override
	public Map<String, Object> getTradeList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map = md.getTradeList();
		if (map.get("masTradeList") != null) {
			// return map;

			map.put("status", "1");
			map.put("msg", "Data found");
			return map;
		} else {
			map.put("status", "0");
			map.put("msg", "Data not found");
			return map;
		}
	}

	/*
	 * @Override public List<MasTrade> getTradeList(HttpServletRequest request,
	 * HttpServletResponse response) { List<MasTrade> masTradeList = new
	 * ArrayList<MasTrade>(); masTradeList = md.getTradeList();
	 * if(masTradeList.size()>0) { return masTradeList; }else { return
	 * masTradeList.add("No Record found"); }
	 * 
	 * }
	 */

	@Override
	public String addTradeDetails(JSONObject json, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		MasTrade masTrade = new MasTrade();

		if (json.get("tradeName") == null) {
			return "{\"status\":\"0\",\"msg\":\"tradeName is not contain in json data or it will be null or blank please check\"}";
		}

		else {
			// masTrade.setTradeId(Long.parseLong(json.get("tradeId").toString()));
			masTrade.setTradeName(json.get("tradeName").toString());
			masTrade.setStatus("y");
			Users users = new Users();
			users.setUserId(new Long(1));
			masTrade.setUser(users);

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masTrade.setLastChgDate(date);

			List<MasTrade> masTradeList = md.validateMasTrade(json.get("tradeName").toString());
			if (masTradeList != null && masTradeList.size() > 0) {
				if (masTradeList.get(0).getTradeName().equalsIgnoreCase(json.get("tradeName").toString())) {

					return "{\"status\":\"2\",\"msg\":\"Trade Name is already Existing.\"}";

				} else {
					String addTrade = md.addTradeDetails(masTrade);
					if (addTrade != null && addTrade.equalsIgnoreCase("200")) {
						jsonObject.put("msg", "Successfully Added!!");
						jsonObject.put("addTrade", addTrade);
						jsonObject.put("status", 1);
					} else if (addTrade == null && addTrade.equalsIgnoreCase("500")) {
						jsonObject.put("msg", "fail to Added!!");
						jsonObject.put("addTrade", addTrade);
						jsonObject.put("status", 0);
					} else {
						jsonObject.put("msg", addTrade);
						jsonObject.put("status", 0);
					}
				}
			}

		}

		return jsonObject.toString();
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
				mapObj.put("cmdType", mCommand.getMasCommandType().getCommandtypeName());
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

	/*********************************
	 * MAS UOM
	 ****************************************************/
	@Override
	public String addMasUOM(JSONObject jsonObject, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		MasUOM masUOM = new MasUOM();
		if (jsonObject != null) {
			String status = "y";
			Long lastChgBy = new Long(1);
			masUOM.setUOMCode(jsonObject.get("UOMCode").toString().toUpperCase());
			masUOM.setUOMName(jsonObject.get("UOMName").toString().toUpperCase());

			masUOM.setUOMStatus(status.toUpperCase());

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

			masUOM.setLastChgBy(lastChgBy);
			masUOM.setLastChgDate(date);

			List<MasUOM> checkMasUOM = md.validateMasUOM(masUOM.getUOMCode(), masUOM.getUOMName());
			if (checkMasUOM.size() != 0) {
				if (checkMasUOM != null && checkMasUOM.size() > 0) {

					if (checkMasUOM.get(0).getUOMCode().equalsIgnoreCase(jsonObject.get("UOMCode").toString())) {

						return "{\"status\":\"2\",\"message\":\"UOM Code is already Existing.\"}";
					}
					if (checkMasUOM.get(0).getUOMName().equalsIgnoreCase(jsonObject.get("UOMName").toString())) {

						return "{\"status\":\"2\",\"message\":\"UOM Name is already Existing.\"}";
					}
				}
			} else {
				String masUOMObject = md.addMasUOM(masUOM);
				if (masUOMObject != null && masUOMObject.equalsIgnoreCase("200")) {
					jsonObj.put("status", 1);
					jsonObj.put("msg", "Successfully Added");
				} else {
					jsonObj.put("status", 0);
					jsonObj.put("msg", "Not Added");
				}

			}

		}
		return jsonObj.toString();
	}

	@Override
	public String getAllUOM(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (jsonObject != null) {
			List totalMatches = new ArrayList();
			List<MasUOM> uList = new ArrayList<MasUOM>();
			Map<String, List<MasUOM>> map = md.getAllUOM(jsonObject);
			if (map.get("unitList") != null) {
				uList = map.get("unitList");
				totalMatches = map.get("totalMatches");

				if (uList != null && uList.size() > 0) {
					json.put("data", uList);
					json.put("msg", "unitList");
					json.put("count", totalMatches.size());
					json.put("status", 1);
				} else {
					json.put("data", uList);
					json.put("count", totalMatches.size());
					json.put("msg", "Data not found");
					json.put("status", 0);
				}
			}
		} else {
			json.put("msg", "Object Not Found!!!");
		}
		return json.toString();
	}

	@Override
	public String updateUOMDetails(HashMap<String, Object> masUnit, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();

		if (masUnit.get("UOMCode") != null && !masUnit.get("UOMCode").toString().equalsIgnoreCase("")) {

			String updateUOM = md.updateUOMDetails(Long.parseLong(masUnit.get("UOMId").toString()),
					masUnit.get("UOMCode").toString().trim(), masUnit.get("UOMName").toString().trim());

			if (updateUOM != null && !updateUOM.equalsIgnoreCase("")) {
				json.put("updateUOM", updateUOM);
				json.put("msg", "Record Has been Successfully Updated");
				json.put("status", 1);
			} else if (updateUOM == null && updateUOM.equalsIgnoreCase("")) {
				json.put("msg", "Record Not Updated");
				json.put("status", 0);
			}

			else {
				return "{\"status\":\"0\",\"msg\":\"UOM Code is not available !!!\"}";
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"UOMCode is not available !!!\"}";
		}
		return json.toString();
	}

	@Override
	public String updateUOMStatus(HashMap<String, Object> masUnit, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if (masUnit.get("UOMCode") != null && !masUnit.get("UOMCode").toString().equalsIgnoreCase("")) {
			MasUOM chkMasUnit = md.chkMasUnit(masUnit.get("UOMCode").toString());
			if (chkMasUnit != null) {
				String masUnitStatus = md.updateUOMStatus(Long.parseLong(masUnit.get("UOMId").toString()),
						masUnit.get("UOMCode").toString(), masUnit.get("UOMStatus").toString());
				if (masUnitStatus != null && masUnitStatus.equalsIgnoreCase("200")) {
					json.put("masUnitStatus", masUnitStatus);
					json.put("msg", "Status Updated Successfully");
					json.put("status", 1);
				} else {
					// json.put("masCmdStatus", masCmdStatus);
					json.put("msg", "Status Not Updated");
					json.put("status", 0);
				}
			}
		} else {
			return "{\"status\":\"0\",\"msg\":\"UOMCode is not Existing !!!\"}";
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

			long d = System.currentTimeMillis();
			Date date = new Date(d);

			MasUnit masUnit = new MasUnit();
			MasCommand command = new MasCommand();

			masUnit.setUnitName(jsondata.get("unitName").toString().toUpperCase());
			masUnit.setUnitAddress(jsondata.getString("unitAddress").toString().toUpperCase());
			command.setCommandId(Long.parseLong(jsondata.get("commandId").toString()));

			masUnit.setMasCommand(command);

			Users users = new Users();
			users.setUserId(new Long(1));

			masUnit.setUser(users);
			masUnit.setStatus("Y");
			masUnit.setLastChgDate(date);

			MasUnitType unitType = new MasUnitType();

			unitType.setUnitTypeId(Long.parseLong(jsondata.get("unitTypeId").toString()));
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
					|| unitPayload.get("commandId").toString() != null || unitPayload.get("unitTypeId").toString() != null) {
				String updateUnit = md.updateUnit(Long.parseLong(unitPayload.get("unitId").toString()),
						unitPayload.get("unitName").toString(), Long.parseLong(unitPayload.get("commandId").toString()),
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
						mapObj.put("genderId", idealWeight.getGenderId());
						mapObj.put("fromHeight", idealWeight.getFromHeight());
						mapObj.put("toHeight", idealWeight.getToHeight());
						mapObj.put("fromAge", idealWeight.getFromAge());
						mapObj.put("toAge", idealWeight.getToAge());
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
			System.out.println("idealWtList :: " + idealWtList.size());
			json.put("data", idealWtList);
			json.put("count", idealWtList.size());

		} else {
			json.put("status", 0);
			json.put("count", idealWtList.size());
			json.put("msg", "Record Not Found");
		}

		return json.toString();
	}
}