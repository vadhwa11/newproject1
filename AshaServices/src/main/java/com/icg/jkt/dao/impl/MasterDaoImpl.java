package com.icg.jkt.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
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
import com.icg.jkt.hibernateutils.GetHibernateUtils;
import com.icg.jkt.utils.HMSUtil;
import com.icg.jkt.utils.JavaUtils;

@Repository
public class MasterDaoImpl implements MasterDao {

	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<MasDepartment> getDepartmentList() {
		List<MasDepartment> list = null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(MasDepartment.class);
			// r.add(Restrictions.eq("user_name", string));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("DEPARTMENT_CODE").as("DEPARTMENT_CODE"));
			projectionList.add(Projections.property("DEPARTMENT_NAME").as("DEPARTMENT_NAME"));
			projectionList.add(Projections.property("DEPARTMENT_ID").as("DEPARTMENT_ID"));
			cr.setProjection(projectionList);
			list = cr.setResultTransformer(new AliasToBeanResultTransformer(MasDepartment.class)).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return list;
	}

	@Override
	public MasEmployee checkEmp(Long i) {

		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(MasEmployee.class);
		cr.add(Restrictions.eq("employeeId", i));
		MasEmployee list = (MasEmployee) cr.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	@Override
	public List<MasIcd> getIcd() {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria cr = session.createCriteria(MasIcd.class);

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("icdCode").as("icdCode"));
		projectionList.add(Projections.property("icdName").as("icdName"));
		cr.setProjection(projectionList);
		List<MasIcd> list = cr.setResultTransformer(new AliasToBeanResultTransformer(MasIcd.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return list;
	}

	/**
	 * @author rajdeo.kumar
	 */
	@Override
	public MasState checkMasState(String stateCode) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria = session.createCriteria(MasState.class);
		criteria.add(Restrictions.eq("stateCode", stateCode));
		MasState masStateObj = (MasState) criteria.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return masStateObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<MasState>> getAllStates(JSONObject jsonObject) {
		Map<String, List<MasState>> map = new HashMap<String, List<MasState>>();
		List<MasState> stateList = new ArrayList<MasState>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

		Criteria criteria = session.createCriteria(MasState.class, "masstate");
		criteria.createAlias("masstate.country", "country").add(Restrictions.eq("country.countryId", new Long(1)));

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("stateCode").as("stateCode"));
		projectionList.add(Projections.property("stateName").as("stateName"));
		projectionList.add(Projections.property("status").as("status"));
		criteria.setProjection(projectionList);
		stateList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasState.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();

		map.put("stateList", stateList);
		return map;
	}

	@Override
	public List<MasCommand> validateMasCommand(String commandCode, String commandName) {
		List<MasCommand> cmdList = null;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasCommand.class);
			criteria.add(Restrictions.or(Restrictions.eq("commandCode", commandCode),
					Restrictions.eq("commandName", commandName)));
			cmdList = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return cmdList;
	}

	@Override
	public String addMasCommand(MasCommand masCommand) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj = session.save(masCommand);
			tx.commit();
			if (savedObj != null) {
				result = "200";
			} else {
				result = "500";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasCommand chkCommand(String commandCode) {

		MasCommand masCmd = new MasCommand();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasCommand.class);
			criteria.add(Restrictions.eq("commandCode", commandCode));
			masCmd = (MasCommand) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return masCmd;
	}

	@Override
	public Map<String, List<MasCommand>> getAllCommand(JSONObject jsonObj) {
		Map<String, List<MasCommand>> mapObj = new HashMap<String, List<MasCommand>>();
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo= 0;

		List totalMatches = new ArrayList();
		List<MasCommand> masCmdList = new ArrayList<MasCommand>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria = session.createCriteria(MasCommand.class);

		if (jsonObj.get("PN") != null)
			pageNo = Integer.parseInt(jsonObj.get("PN").toString());

		String cName = "";
		if (jsonObj.has("commandName")) {
			cName = jsonObj.get("commandName") + "%";
			if (jsonObj.get("commandName").toString().length() > 0
					&& !jsonObj.get("commandName").toString().trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.like("commandName", cName));

			}
		}
		criteria.addOrder(Order.asc("commandName"));

		totalMatches = criteria.list();
		criteria.setFirstResult((pageSize) * (pageNo - 1));
		criteria.setMaxResults(pageSize);

		masCmdList = criteria.list();
		// masCmdList = criteria.setResultTransformer(new
		// AliasToBeanResultTransformer(MasCommand.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		mapObj.put("masCmdList", masCmdList);
		mapObj.put("totalMatches", totalMatches);
		return mapObj;
	}

	@Override
	public List<MasCommand> getCommand(String commandName) {
		Object[] status = new Object[] { "y" };

		List<MasCommand> masCmdList = new ArrayList<MasCommand>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasCommand.class);
			if (commandName.length() > 0 && !commandName.trim().equalsIgnoreCase("")) {

				criteria.add(Restrictions.like("commandName", commandName));
			}
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("commandId").as("commandId"));
			projectionList.add(Projections.property("commandCode").as("commandCode"));
			projectionList.add(Projections.property("commandName").as("commandName"));
			projectionList.add(Projections.property("status").as("status"));

			criteria.setProjection(projectionList);

			masCmdList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasCommand.class)).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return masCmdList;
	}

	@Override
	public String updateCommand(Long commandId, String commandCode, String commandName, Long commandtypeId) {
		String result = "";
		try {
			List<MasCommand> masCmdList = new ArrayList<MasCommand>();
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			if (commandId != 0) {
				Object cmdObject = session.load(MasCommand.class, commandId);
				MasCommand masCommand = (MasCommand) cmdObject;

				Transaction transaction = session.beginTransaction();
				masCommand.setCommandCode(commandCode.toUpperCase());
				masCommand.setCommandName(commandName.toUpperCase());

				MasCommandType commandType = new MasCommandType();
				commandType.setCommandtypeId(commandtypeId);
				masCommand.setMasCommandType(commandType);

				masCommand.setStatus("Y");
				Users users = new Users();
				users.setUserId(new Long(1));
				masCommand.setUser(users);

				long d = System.currentTimeMillis();
				Date date = new Date(d);

				masCommand.setLastChgDate(date);
				session.update(masCommand);
				transaction.commit();
				result = "200";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	@Override
	public String updateCommandStatus(Long commandId, String commandCode, String status) {
		// Object[] status = new Object[] {"y"};
		String result = "";
		try {

			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object cmdObject = session.load(MasCommand.class, commandId);
			MasCommand masCommand = (MasCommand) cmdObject;

			Transaction transaction = session.beginTransaction();
			if (masCommand.getStatus().equalsIgnoreCase("y")) {
				masCommand.setStatus("n");

			} else if (masCommand.getStatus().equalsIgnoreCase("n")) {
				masCommand.setStatus("y");

			} else {
				masCommand.setStatus("y");
			}

			session.update(masCommand);
			transaction.commit();
			result = "200";
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/*****************************
	 * MAS UNIT
	 ********************************************/
	@Override
	public Map<String, List<MasUnit>> getAllUnit(JSONObject jsondata) {
		Map<String, List<MasUnit>> map = new HashMap<String, List<MasUnit>>();
		List<MasUnit> masUnitList = new ArrayList<MasUnit>();
		List<String[]> masObject = null;
		
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUnit.class).createAlias("masCommand", "masCommand")
					.createAlias("masUnitType", "masUnitType");

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("unitId").as("unitId"));
			projectionList.add(Projections.property("unitName").as("unitName"));
			projectionList.add(Projections.property("status").as("status"));
			projectionList.add(Projections.property("unitAddress").as("unitAddress"));
			projectionList.add(Projections.property("masCommand.commandName"));
			projectionList.add(Projections.property("masCommand.commandId"));
			projectionList.add(Projections.property("masUnitType.unitTypeName"));
			projectionList.add(Projections.property("masUnitType.unitTypeId"));
			criteria.setProjection(projectionList);

			if (jsondata.get("PN") != null)
				pageNo = Integer.parseInt(jsondata.get("PN").toString());

			String uName = "";
			if (jsondata.has("unitName")) {
				uName = jsondata.get("unitName") + "%";
				if (jsondata.get("unitName").toString().length() > 0
						&& !jsondata.get("unitName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("unitName", uName));
				}
			}

			criteria.addOrder(Order.asc("unitName"));
			List totalMatches = criteria.list();
			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			masObject = criteria.list();

			MasUnit masUnit = null;
			MasUnitType masUnittype = null;
			MasCommand masCommand = null;

			if (CollectionUtils.isNotEmpty(masObject)) {
				for (Iterator<?> it = masObject.iterator(); it.hasNext();) {
					Object[] row = (Object[]) it.next();

					masUnit = new MasUnit();
					masCommand = new MasCommand();
					masUnittype = new MasUnitType();
					if (row[0] != null) {
						masUnit.setUnitId(Long.parseLong(row[0].toString()));
					}

					if (row[1] != null) {
						masUnit.setUnitName(row[1].toString());
					}
					if (row[2] != null) {
						masUnit.setStatus(row[2].toString());
					}
					if (row[3] != null) {
						masUnit.setUnitAddress(row[3].toString());
					}
					if (row[4] != null) {

						masUnittype.setUnitTypeName(row[4].toString());
						masUnit.setMasUnittype(masUnittype);
					}
					if (row[5] != null) {

						masCommand.setCommandId(Long.parseLong(row[5].toString()));
						masUnit.setMasCommand(masCommand);
					}
					if (row[6] != null) {
						masCommand.setCommandName(row[6].toString());
						masUnit.setMasCommand(masCommand);
					}
					if (row[7] != null) {
						masUnittype.setUnitTypeId(Long.parseLong(row[7].toString()));
						masUnit.setMasUnittype(masUnittype);
					}
					masUnitList.add(masUnit);
				}
			}
			map.put("masUnitList", masUnitList);
			map.put("totalMatches", totalMatches);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasCommand> getCommandList() {
		List<MasCommand> cList = new ArrayList<MasCommand>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasCommand.class);

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("commandId").as("commandId"));
			projectionList.add(Projections.property("commandName").as("commandName"));
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("commandName"));
			cList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasCommand.class)).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return cList;
	}

	@Override
	public List<MasUnit> validateUnit(String unitName) {
		List<MasUnit> masUnitList = new ArrayList<MasUnit>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUnit.class);
			criteria.add(Restrictions.eq("unitName", unitName));
			masUnitList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return masUnitList;
	}

	@Override
	public String addMasUnit(MasUnit masUnit) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj = session.save(masUnit);
			tx.commit();
			if (savedObj != null) {
				result = "200";
			} else {
				result = "500";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;

	}

	@Override
	public List<MasUnitType> getUnitTypeList() {
		List<MasUnitType> unitTypeList = new ArrayList<MasUnitType>();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUnitType.class);

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("unitTypeId").as("unitTypeId"));
			projectionList.add(Projections.property("unitTypeName").as("unitTypeName"));
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("unitTypeName"));
			unitTypeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasUnitType.class)).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return unitTypeList;
	}

	@Override
	public String updateUnit(Long uId, String uName, Long commandId, String unitAddress, Long uTypId) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			List<MasUnit> masUnitList = new ArrayList<MasUnit>();
			Criteria criteria = session.createCriteria(MasUnit.class);
			criteria.add(Restrictions.eq("unitId", uId));
			masUnitList = criteria.list();

			if (masUnitList != null && masUnitList.size() > 0) {
				for (int i = 0; i < masUnitList.size(); i++) {
					Long Id = masUnitList.get(i).getUnitId();

					Object unitObject = session.load(MasUnit.class, Id);
					MasUnit masUnit = (MasUnit) unitObject;

					long d = System.currentTimeMillis();
					Date date = new Date(d);

					Transaction tx = session.beginTransaction();
					masUnit.setUnitName(uName.toUpperCase());
					masUnit.setUnitAddress(unitAddress.toUpperCase());

					MasCommand command = new MasCommand();
					command.setCommandId(commandId);
					masUnit.setMasCommand(command);

					MasUnitType masUnitType = new MasUnitType();
					masUnitType.setUnitTypeId(uTypId);
					masUnit.setMasUnittype(masUnitType);

					Users users = new Users();
					users.setUserId(new Long(1));

					masUnit.setUser(users);
					masUnit.setLastChgDate(date);
					session.update(masUnit);
					tx.commit();
					result += "200";
				}
			} else {
				result += "500";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public List<MasCommandType> getCommandTypeList() {
		List<MasCommandType> cmdTypList = new ArrayList<MasCommandType>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasCommandType.class);

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("commandtypeId").as("commandtypeId"));
			projectionList.add(Projections.property("commandtypeName").as("commandtypeName"));
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("commandtypeName"));
			cmdTypList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasCommandType.class)).list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cmdTypList;
	}

	/****************************************
	 * MAS HOSPITAL
	 **********************************************************/
	@Override
	public List<MasUnit> getUnitNameList() {
		List<MasUnit> mUnitList = new ArrayList<MasUnit>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUnit.class);

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("unitId").as("unitId"));
			projectionList.add(Projections.property("unitName").as("unitName"));
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("unitName"));
			mUnitList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasUnit.class)).list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mUnitList;
	}

	@Override
	public Map<String, List<MasHospital>> getAllHospital(JSONObject jsonObj) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;

		Map<String, List<MasHospital>> mapObj = new HashMap<String, List<MasHospital>>();
		List<MasHospital> mHospitalList = new ArrayList<MasHospital>();
		List totalMatches = new ArrayList();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasHospital.class);

			if (jsonObj.get("PN") != null)
				pageNo = Integer.parseInt(jsonObj.get("PN").toString());

			String hName = "";
			String hCode = "";
			if (jsonObj.has("hospitalName")) {
				hName = jsonObj.get("hospitalName") + "%";
				if (jsonObj.get("hospitalName").toString().length() > 0
						&& !jsonObj.get("hospitalName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("hospitalName", hName));

				}
			}
			if (jsonObj.has("hospitalCode")) {

				hCode = jsonObj.get("hospitalCode") + "%";
				if (jsonObj.get("hospitalCode").toString().length() > 0
						&& !jsonObj.get("hospitalCode").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("hospitalCode", hCode));
				}
			}
			criteria.addOrder(Order.asc("hospitalName"));
			totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			System.out.println("criteria :: " + criteria);
			mHospitalList = criteria.list();
			mapObj.put("totalMatches", totalMatches);
			mapObj.put("mHospitalList", mHospitalList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public MasHospital checkMasHospital(String hospitalCode) {
		MasHospital mHospital = new MasHospital();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasHospital.class);
			criteria.add(Restrictions.eq("hospitalCode", hospitalCode));
			mHospital = (MasHospital) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mHospital;
	}

	@Override
	public String updateHospitalMasterStatus(Long hospitalId, String hospitalCode, String status) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasHospital.class, hospitalId);
			MasHospital masHospital = (MasHospital) object;
			Transaction transaction = session.beginTransaction();

			if (masHospital.getStatus().equalsIgnoreCase("Y") || masHospital.getStatus().equalsIgnoreCase("y")) {
				masHospital.setStatus("N");
			} else if (masHospital.getStatus().equalsIgnoreCase("N") || masHospital.getStatus().equalsIgnoreCase("n")) {
				masHospital.setStatus("Y");
			} else {
				masHospital.setStatus("Y");
			}
			session.update(masHospital);
			transaction.commit();
			result = "200";
			// }

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public String updateHospitalDetails(Long hospitalId, String hospitalCode, String hospitalName, Long unitId) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasHospital.class, hospitalId);
			MasHospital masHospital = (MasHospital) object;
			Transaction transaction = session.beginTransaction();
			masHospital.setHospitalCode(hospitalCode.toUpperCase());
			masHospital.setHospitalName(hospitalName.toUpperCase());

			MasUnit masUnit = new MasUnit();
			masUnit.setUnitId(unitId);
			masHospital.setMasUnit(masUnit);

			Users user = new Users();
			user.setUserId(new Long(1));
			masHospital.setUser(user);

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masHospital.setLastChgDate(date);
			session.update(masHospital);
			transaction.commit();

			result = "200";
			// }
			// }

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public List<MasHospital> validateMasHospital(String hospitalCode, String hospitalName) {
		List<MasHospital> hospitalList = new ArrayList<MasHospital>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasHospital.class);
			criteria.add(Restrictions.or(Restrictions.eq("hospitalCode", hospitalCode),
					Restrictions.eq("hospitalName", hospitalName)));
			hospitalList = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return hospitalList;
	}

	@Override
	public String addMasHospital(MasHospital hospital) {
		String result = "";
		try {

			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction transaction = session.beginTransaction();
			Serializable savedObj = session.save(hospital);

			transaction.commit();

			if (savedObj != null) {

				result = "200";
			} else {
				result = "500";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	/******************************************
	 * MAS RELATION
	 ********************************************************************/
	@Override
	public Map<String, List<MasRelation>> getAllRelation(JSONObject jsonObj) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;

		Map<String, List<MasRelation>> mapObj = new HashMap<String, List<MasRelation>>();
		List<MasRelation> mRelationList = new ArrayList<MasRelation>();
		List totalMatches = new ArrayList();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasRelation.class);

			if (jsonObj.get("PN") != null)
				pageNo = Integer.parseInt(jsonObj.get("PN").toString());

			String rName = "";
			String rCode = "";
			if (jsonObj.has("relationName")) {
				rName = jsonObj.get("relationName") + "%";
				if (jsonObj.get("relationName").toString().length() > 0
						&& !jsonObj.get("relationName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("relationName", rName));
				}
			}

			criteria.addOrder(Order.asc("relationCode"));
			totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			mRelationList = criteria.list();

			mapObj.put("totalMatches", totalMatches);
			mapObj.put("mRelationList", mRelationList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public String updateRelationDetails(Long relationId, Long relationCode, String relationName, Long newRelationCode,
			String newRelationName) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasRelation.class, relationId);
			MasRelation masRelation = (MasRelation) object;
			Transaction transaction = session.beginTransaction();
			masRelation.setRelationCode(relationCode);
			masRelation.setRelationName(relationName.toUpperCase());
			masRelation.setNewRelationCode(newRelationCode);
			masRelation.setNewRelationName(newRelationName.toUpperCase());

			Users user = new Users();
			user.setUserId(new Long(1));
			masRelation.setUser(user);

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masRelation.setLastChgDate(date);
			session.update(masRelation);
			transaction.commit();

			result = "200";

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasRelation checkMasRelation(Long relationCode) {
		MasRelation mRelation = new MasRelation();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasRelation.class);
			criteria.add(Restrictions.eq("relationCode", relationCode));
			mRelation = (MasRelation) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mRelation;
	}

	@Override
	public String updateRelationStatus(Long realtionId, Long relationCode, String status) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasRelation.class, realtionId);
			MasRelation masRelation = (MasRelation) object;
			Transaction transaction = session.beginTransaction();

			if (masRelation.getStatus().equalsIgnoreCase("Y") || masRelation.getStatus().equalsIgnoreCase("y")) {
				masRelation.setStatus("N");
			} else if (masRelation.getStatus().equalsIgnoreCase("N") || masRelation.getStatus().equalsIgnoreCase("n")) {
				masRelation.setStatus("Y");
			} else {
				masRelation.setStatus("Y");
			}
			session.update(masRelation);
			transaction.commit();
			result = "200";
			// }

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public List<MasRelation> validateRelation(Long relationCode, String relationName) {
		List<MasRelation> relList = new ArrayList<MasRelation>();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasRelation.class);
			criteria.add(Restrictions.or(Restrictions.eq("relationCode", relationCode),
					Restrictions.eq("relationName", relationName)));
			relList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return relList;
	}

	@Override
	public String addRelation(MasRelation masRelation) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			session.beginTransaction();
			Serializable savedObj = session.save(masRelation);
			session.getTransaction().commit();
			// tx.commit();
			if (savedObj != null) {
				result = "200";
			} else {
				result = "500";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	/***************************************
	 * MAS DISPOSAL
	 **************************************************************/
	@Override
	public List<MasDisposal> validateDisposal(String disposalCode, String disposalName) {
		List<MasDisposal> disposalList = new ArrayList<MasDisposal>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasDisposal.class);
			criteria.add(Restrictions.or(Restrictions.eq("disposalCode", disposalCode),
					Restrictions.eq("disposalName", disposalName)));
			disposalList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return disposalList;
	}

	@Override
	public String addDisposal(MasDisposal masDisposal) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			session.beginTransaction();
			Serializable savedObj = session.save(masDisposal);
			session.getTransaction().commit();
			// tx.commit();
			if (savedObj != null) {
				result = "200";
			} else {
				result = "500";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	@Override
	public Map<String, List<MasDisposal>> getAllDisposal(JSONObject jsonObj) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0; 

		Map<String, List<MasDisposal>> mapObj = new HashMap<String, List<MasDisposal>>();
		List<MasDisposal> mdisposalList = new ArrayList<MasDisposal>();
		List totalMatches = new ArrayList();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasDisposal.class);

			if (jsonObj.get("PN") != null)
				pageNo = Integer.parseInt(jsonObj.get("PN").toString());

			String dName = "";
			String rCode = "";
			if (jsonObj.has("disposalName")) {
				dName = jsonObj.get("disposalName") + "%";
				if (jsonObj.get("disposalName").toString().length() > 0
						&& !jsonObj.get("disposalName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("disposalName", dName));
				}
			}

			criteria.addOrder(Order.asc("disposalCode"));
			totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			mdisposalList = criteria.list();

			mapObj.put("totalMatches", totalMatches);
			mapObj.put("mdisposalList", mdisposalList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public String updateDisposalDetails(Long disposalId, String disposalCode, String disposalName,
			String disposalType) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasDisposal.class, disposalId);
			MasDisposal masDisposal = (MasDisposal) object;
			Transaction transaction = session.beginTransaction();
			masDisposal.setDisposalCode(disposalCode.toUpperCase());
			masDisposal.setDisposalName(disposalName.toUpperCase());
			if (disposalType.equalsIgnoreCase("ip")) {
				masDisposal.setDisposalType(disposalType.toUpperCase());
			}
			if (disposalType.equalsIgnoreCase("op")) {
				masDisposal.setDisposalType(disposalType.toUpperCase());
			}
			
			Users users = new Users();
			users.setUserId(new Long(1));
			masDisposal.setUsers(users);

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masDisposal.setLastChgDate(date);
			session.update(masDisposal);
			transaction.commit();

			result = "200";
			// }
			// }

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasDisposal checkMasDisposal(String disposalCode) {
		MasDisposal mDisposal = new MasDisposal();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasDisposal.class);
			criteria.add(Restrictions.eq("disposalCode", disposalCode));
			mDisposal = (MasDisposal) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mDisposal;
	}

	@Override
	public String updateDisposalStatus(Long disposalId, String disposalCode, String status) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasDisposal.class, disposalId);
			MasDisposal masDisposal = (MasDisposal) object;
			Transaction transaction = session.beginTransaction();

			if (masDisposal.getStatus().equalsIgnoreCase("Y") || masDisposal.getStatus().equalsIgnoreCase("y")) {
				masDisposal.setStatus("N");
			} else if (masDisposal.getStatus().equalsIgnoreCase("N") || masDisposal.getStatus().equalsIgnoreCase("n")) {
				masDisposal.setStatus("Y");
			} else {
				masDisposal.setStatus("Y");
			}
			session.update(masDisposal);
			transaction.commit();
			result = "200";

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/**************************************
	 * MAS APPOINTMENT TYPE
	 ************************************************************************/
	@Override
	public List<MasAppointmentType> validateAppointmentType(String appointmentTypeCode, String appointmentTypeName) {
		List<MasAppointmentType> appointmentTypesList = new ArrayList<MasAppointmentType>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasAppointmentType.class);
			criteria.add(Restrictions.or(Restrictions.eq("appointmentTypeCode", appointmentTypeCode),
					Restrictions.eq("appointmentTypeName", appointmentTypeName)));
			appointmentTypesList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return appointmentTypesList;
	}

	@Override
	public String addAppointmentType(MasAppointmentType masAppointmentType) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			session.beginTransaction();
			Serializable savedObj = session.save(masAppointmentType);
			session.getTransaction().commit();
			// tx.commit();
			if (savedObj != null) {
				result = "200";
			} else {
				result = "500";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	@Override
	public Map<String, List<MasAppointmentType>> getAllAppointmentType(JSONObject jsonObj) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;

		Map<String, List<MasAppointmentType>> mapObj = new HashMap<String, List<MasAppointmentType>>();
		List<MasAppointmentType> mAppointmentTypeList = new ArrayList<MasAppointmentType>();
		List totalMatches = new ArrayList();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasAppointmentType.class);

			if (jsonObj.get("PN") != null)
				pageNo = Integer.parseInt(jsonObj.get("PN").toString());

			String aName = "";
			String rCode = "";
			if (jsonObj.has("appointmentTypeName")) {
				aName = jsonObj.get("appointmentTypeName") + "%";
				if (jsonObj.get("appointmentTypeName").toString().length() > 0
						&& !jsonObj.get("appointmentTypeName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("appointmentTypeName", aName));
				}
			}

			criteria.addOrder(Order.asc("appointmentTypeCode"));
			totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			mAppointmentTypeList = criteria.list();

			mapObj.put("totalMatches", totalMatches);
			mapObj.put("mAppointmentTypeList", mAppointmentTypeList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public String updateAppointmentTypeDetails(Long appointmentTypeId, String appointmentTypeCode,
			String appointmentTypeName) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasAppointmentType.class, appointmentTypeId);
			MasAppointmentType masAppointmentType = (MasAppointmentType) object;

			Transaction transaction = session.beginTransaction();
			masAppointmentType.setAppointmentTypeCode(appointmentTypeCode.toUpperCase());
			masAppointmentType.setAppointmentTypeName(appointmentTypeName.toUpperCase());
			Users users = new Users();
			users.setUserId(new Long(1));
			masAppointmentType.setUser(users);

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			masAppointmentType.setLastChgDate(date);
			session.update(masAppointmentType);
			transaction.commit();

			result = "200";

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasAppointmentType checkMasAppointmentType(String appointmentTypeCode) {
		MasAppointmentType mAppointmentType = new MasAppointmentType();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasAppointmentType.class);
			criteria.add(Restrictions.eq("appointmentTypeCode", appointmentTypeCode));
			mAppointmentType = (MasAppointmentType) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mAppointmentType;
	}

	@Override
	public String updateAppointmentTypeStatus(Long appointmentTypeId, String appointmentTypeCode, String status) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasAppointmentType.class, appointmentTypeId);

			MasAppointmentType masAppointmentType = (MasAppointmentType) object;
			Transaction transaction = session.beginTransaction();

			if (masAppointmentType.getStatus().equalsIgnoreCase("Y")
					|| masAppointmentType.getStatus().equalsIgnoreCase("y")) {
				masAppointmentType.setStatus("N");
			} else if (masAppointmentType.getStatus().equalsIgnoreCase("N")
					|| masAppointmentType.getStatus().equalsIgnoreCase("n")) {
				masAppointmentType.setStatus("Y");
			} else {
				masAppointmentType.setStatus("Y");
			}
			session.update(masAppointmentType);
			transaction.commit();
			result = "200";

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************
	 * MAS DEPARTMENT
	 *****************************************************/
	@Override
	public List<MasDepartmentType> getDepartmentTypeList() {
		List<MasDepartmentType> deptTypeList = new ArrayList<MasDepartmentType>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasDepartmentType.class);

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("departmentTypeId").as("departmentTypeId"));
			projectionList.add(Projections.property("departmentTypeName").as("departmentTypeName"));
			criteria.setProjection(projectionList);

			deptTypeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasDepartmentType.class))
					.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return deptTypeList;
	}

	@Override
	public Map<String, List<MasDepartment>> getAllDepartment(JSONObject jsondata) {
		Map<String, List<MasDepartment>> map = new HashMap<String, List<MasDepartment>>();
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;
		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasDepartment.class);

			if (jsondata.get("PN") != null)
				pageNo = Integer.parseInt(jsondata.get("PN").toString());

			String dName = "";
			if (jsondata.has("departmentName")) {
				dName = jsondata.get("departmentName") + "%";
				if (jsondata.get("departmentName").toString().length() > 0
						&& !jsondata.get("departmentName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("departmentName", dName));

				}
			}
			criteria.addOrder(Order.asc("departmentCode"));
			List totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			departmentList = criteria.list();

			map.put("departmentList", departmentList);
			map.put("totalMatches", totalMatches);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public String addDepartment(MasDepartment masDepartment) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj = session.save(masDepartment);
			tx.commit();
			if (savedObj != null) {
				result = "200";
			} else {
				result = "500";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	@Override
	public List<MasDepartment> validateDepartment(String departmentCode, String departmentName) {
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasDepartment.class);
			criteria.add(Restrictions.or(Restrictions.eq("departmentCode", departmentCode),
					Restrictions.eq("departmentName", departmentName)));
			departmentList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return departmentList;
	}

	@Override
	public MasDepartment checkDepartment(String departmentCode) {
		MasDepartment mdepart = new MasDepartment();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasDepartment.class);
			criteria.add(Restrictions.eq("departmentCode", departmentCode));
			mdepart = (MasDepartment) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mdepart;
	}

	@Override
	public String updateDepartmentStatus(Long departmentId, String departmentCode, String status) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasDepartment.class, departmentId);

			MasDepartment masDepartment = (MasDepartment) object;
			Transaction transaction = session.beginTransaction();

			if (masDepartment.getStatus().equalsIgnoreCase("Y") || masDepartment.getStatus().equalsIgnoreCase("y")) {
				masDepartment.setStatus("N");
			} else if (masDepartment.getStatus().equalsIgnoreCase("N")
					|| masDepartment.getStatus().equalsIgnoreCase("n")) {
				masDepartment.setStatus("Y");
			} else {
				masDepartment.setStatus("Y");
			}
			session.update(masDepartment);
			transaction.commit();
			result = "200";
			// }

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public String updateDepartmentDetails(Long departmentId, String departmentCode, String departmentName,
			Long departmentTypeId) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasDepartment.class, departmentId);
			MasDepartment masDepartment = (MasDepartment) object;

			Transaction transaction = session.beginTransaction();
			masDepartment.setDepartmentCode(departmentCode.toUpperCase());
			masDepartment.setDepartmentName(departmentName.toUpperCase());

			MasDepartmentType masDepartmentType = new MasDepartmentType();
			masDepartmentType.setDepartmentTypeId(departmentTypeId);
			masDepartment.setMasDepartmentType(masDepartmentType);
			Users users = new Users();
			users.setUserId(new Long(1));
			masDepartment.setUser(users);
			long d = System.currentTimeMillis();
			Timestamp date = new Timestamp(d);
			masDepartment.setLastChgDate(date);
			session.update(masDepartment);
			transaction.commit();

			result = "200";
			// }
			// }

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************
	 * MAS FREQUENCY
	 *********************************************************/
	@Override
	public Map<String, List<MasFrequency>> getAllOpdFrequency(JSONObject jsondata) {
		Map<String, List<MasFrequency>> map = new HashMap<String, List<MasFrequency>>();
		List<MasFrequency> frequencyList = new ArrayList<MasFrequency>();
		
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasFrequency.class);

			if (jsondata.get("PN") != null)
				pageNo = Integer.parseInt(jsondata.get("PN").toString());

			String fName = "";
			if (jsondata.has("frequencyName")) {
				fName = jsondata.get("frequencyName") + "%";
				if (jsondata.get("frequencyName").toString().length() > 0
						&& !jsondata.get("frequencyName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("frequencyName", fName));

				}
			}
			criteria.addOrder(Order.asc("frequencyCode"));
			List totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			frequencyList = criteria.list();

			map.put("frequencyList", frequencyList);
			map.put("totalMatches", totalMatches);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasFrequency> validateFrequency(String frequencyCode, String frequencyName) {
		List<MasFrequency> freqList = new ArrayList<MasFrequency>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasFrequency.class);
			criteria.add(Restrictions.or(Restrictions.eq("frequencyCode", frequencyCode),
					Restrictions.eq("frequencyName", frequencyName)));
			freqList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return freqList;
	}

	@Override
	public String addOpdFrequency(MasFrequency masFrequency) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj = session.save(masFrequency);
			tx.commit();
			if (savedObj != null) {
				result = "200";
			} else {
				result = "500";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	@Override
	public String updateDepartmentDetails(Long frequencyId, String frequencyCode, String frequencyName) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasFrequency.class, frequencyId);
			MasFrequency masOpdFrequency = (MasFrequency) object;

			Transaction transaction = session.beginTransaction();
			masOpdFrequency.setFrequencyCode(frequencyCode.toUpperCase());
			masOpdFrequency.setFrequencyName(frequencyName.toUpperCase());

			masOpdFrequency.setLastChgBy(new Long(1));
			long d = System.currentTimeMillis();
			Timestamp date = new Timestamp(new Date().getTime());
			masOpdFrequency.setLastChgDate(date);
			session.update(masOpdFrequency);
			transaction.commit();

			result = "200";

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasFrequency checkFrequency(String frequencyCode) {
		MasFrequency mOpdfreq = new MasFrequency();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasFrequency.class);
			criteria.add(Restrictions.eq("frequencyCode", frequencyCode));
			mOpdfreq = (MasFrequency) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mOpdfreq;
	}

	@Override
	public String updateOpdFrequencyStatus(Long frequencyId, String frequencyCode, String status) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasFrequency.class, frequencyId);

			MasFrequency masOpdFrequency = (MasFrequency) object;
			Transaction transaction = session.beginTransaction();
			if (masOpdFrequency.getStatus().equalsIgnoreCase("Y")
					|| masOpdFrequency.getStatus().equalsIgnoreCase("y")) {
				masOpdFrequency.setStatus("N");
			} else if (masOpdFrequency.getStatus().equalsIgnoreCase("N")
					|| masOpdFrequency.getStatus().equalsIgnoreCase("n")) {
				masOpdFrequency.setStatus("Y");
			} else {
				masOpdFrequency.setStatus("Y");
			}
			session.update(masOpdFrequency);
			transaction.commit();
			result = "200";

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasUnit checkUnit(String unitName) {
		MasUnit mUnit = new MasUnit();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUnit.class);
			criteria.add(Restrictions.eq("unitName", unitName));
			mUnit = (MasUnit) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mUnit;
	}

	@Override
	public String updateUnitStatus(Long unitId, String uName, String status) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object object = session.load(MasUnit.class, unitId);
			MasUnit masUnit = (MasUnit) object;
			Transaction transaction = session.beginTransaction();

			if (masUnit.getStatus().equalsIgnoreCase("Y") || masUnit.getStatus().equalsIgnoreCase("y")) {
				masUnit.setStatus("N");
			} else if (masUnit.getStatus().equalsIgnoreCase("N") || masUnit.getStatus().equalsIgnoreCase("n")) {
				masUnit.setStatus("Y");
			} else {
				masUnit.setStatus("Y");
			}
			session.update(masUnit);
			transaction.commit();
			result = "200";

		} catch (Exception e) {

		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/*************************************
	 * MAS IMPANNELED HOSPITAL
	 **************************************************************/
	@Override
	public Map<String, List<MasEmpanelledHospital>> getAllEmpanelledHospital(JSONObject jsonObj) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;

		Map<String, List<MasEmpanelledHospital>> mapObj = new HashMap<String, List<MasEmpanelledHospital>>();
		List<MasEmpanelledHospital> mImpanneledHospitalList = new ArrayList<MasEmpanelledHospital>();
		List totalMatches = new ArrayList();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasEmpanelledHospital.class);

			if (jsonObj.get("PN") != null)
				pageNo = Integer.parseInt(jsonObj.get("PN").toString());

			String empanelledHospName = "";
			String hCode = "";
			if (jsonObj.has("empanelledHospitalName")) {
				empanelledHospName = jsonObj.get("empanelledHospitalName") + "%";
				if (jsonObj.get("empanelledHospitalName").toString().length() > 0
						&& !jsonObj.get("empanelledHospitalName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("empanelledHospitalName", empanelledHospName));
				}
			}

			criteria.addOrder(Order.asc("empanelledHospitalCode"));
			totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			mImpanneledHospitalList = criteria.list();

			mapObj.put("totalMatches", totalMatches);
			mapObj.put("mImpanneledHospitalList", mImpanneledHospitalList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public List<MasEmpanelledHospital> validateEmpanelledHospital(String empanelledHospitalName) {

		List<MasEmpanelledHospital> impannelHospitalList = new ArrayList<MasEmpanelledHospital>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasEmpanelledHospital.class);
			criteria.add(Restrictions.eq("empanelledHospitalName", empanelledHospitalName));

			impannelHospitalList = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return impannelHospitalList;
	}

	@Override
	public String addImpanneledHospital1(MasEmpanelledHospital masEmpanelledHospital) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria cr = session.createCriteria(MasEmpanelledHospital.class);
			cr.add(Restrictions.eq("empanelledHospitalId", masEmpanelledHospital.getEmpanelledHospitalId()));
			MasEmpanelledHospital empanelledHospital = (MasEmpanelledHospital) cr.uniqueResult();
			Transaction transaction = session.beginTransaction();
			if (empanelledHospital != null) {

				empanelledHospital.setEmpanelledHospitalCode(masEmpanelledHospital.getEmpanelledHospitalCode());
				empanelledHospital.setEmpanelledHospitalName(masEmpanelledHospital.getEmpanelledHospitalName());
				empanelledHospital.setEmpanelledHospitalAddress(masEmpanelledHospital.getEmpanelledHospitalAddress());

				if (masEmpanelledHospital.getStatus() != null) {

					if (masEmpanelledHospital.getStatus() == "Y" || masEmpanelledHospital.getStatus() == "y") {
						masEmpanelledHospital.setStatus("N");
					}
					if (masEmpanelledHospital.getStatus() == "N" || masEmpanelledHospital.getStatus() == "n") {
						masEmpanelledHospital.setStatus("Y");
					}

				}
				session.update(empanelledHospital);
				transaction.commit();
				result = "201";
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			} else {
				session.save(masEmpanelledHospital);
				transaction.commit();
				result = "200";
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}

		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	@Override
	public String addEmpanelledHospital(MasEmpanelledHospital masEmpanelledHospital) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction transaction = session.beginTransaction();
			Serializable savedObj = session.save(masEmpanelledHospital);
			transaction.commit();
			if (savedObj != null) {
				result = "200";
			} else {
				result = "500";
			}
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		} catch (Exception e) {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public String updateEmpanelledHospital(JSONObject jsonObject) {
		String result = "";
		long d = System.currentTimeMillis();
		Date lastChgDate = new Date(d);
		try {
			if (jsonObject != null) {
				System.out.println("jsonObject :: " + jsonObject);
				List<MasEmpanelledHospital> empanelledHospitalList = new ArrayList<MasEmpanelledHospital>();
				Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasEmpanelledHospital.class);
				String hStatus = "";
				Long empHospId;
				if (jsonObject.has("empanelledHospitalId")) {
					empHospId = Long.parseLong(jsonObject.get("empanelledHospitalId").toString());
					criteria.add(Restrictions.eq("empanelledHospitalId", empHospId));
					empanelledHospitalList = criteria.list();

					for (int i = 0; i < empanelledHospitalList.size(); i++) {
						Long empanelledHospId = empanelledHospitalList.get(i).getEmpanelledHospitalId();
						System.out.println("empanelledHospId :: " + empanelledHospId);
						Object object = session.load(MasEmpanelledHospital.class, empanelledHospId);
						MasEmpanelledHospital masEmpanelledHospital = (MasEmpanelledHospital) object;

						Transaction transaction = session.beginTransaction();

						if (jsonObject.has("status")) {
							System.out.println("status ::  " + jsonObject.has("status"));
							hStatus = jsonObject.get("status").toString();
							System.out.println("hStatus :: " + hStatus);
							if (hStatus.equalsIgnoreCase("active") || hStatus.equalsIgnoreCase("inactive")) {
								if (hStatus.equalsIgnoreCase("active"))
									masEmpanelledHospital.setStatus("Y");
								else
									masEmpanelledHospital.setStatus("N");
								session.update(masEmpanelledHospital);
								transaction.commit();
								result = "200";

							} else {
								masEmpanelledHospital.setEmpanelledHospitalCode(
										jsonObject.get("empanelledHospitalCode").toString().toUpperCase());
								masEmpanelledHospital.setEmpanelledHospitalName(
										jsonObject.get("empanelledHospitalName").toString().toUpperCase());
								masEmpanelledHospital.setEmpanelledHospitalAddress(
										jsonObject.get("empanelledHospitalAddress").toString().toUpperCase());

								Users user = new Users();
								user.setUserId(new Long(1));
								masEmpanelledHospital.setUser(user);

								masEmpanelledHospital.setLastChgDate(lastChgDate);
								session.update(masEmpanelledHospital);
								transaction.commit();
								result = "200";
							}

						}
					}

				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/*****************************
	 * MAS IDEAL WEIGHT
	 ******************************************************/
	@Override
	public Map<String, List<MasIdealWeight>> getAllIdealWeight(JSONObject jsonObj) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;
		
		Map<String, List<MasIdealWeight>> mapObj = new HashMap<String, List<MasIdealWeight>>();
		List<MasIdealWeight> idealWeightsList = new ArrayList<MasIdealWeight>();
		List totalMatches = new ArrayList();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasIdealWeight.class);

			if (jsonObj.get("PN") != null)
				pageNo = Integer.parseInt(jsonObj.get("PN").toString());

			Long genderId;
			String hCode = "";
			if (jsonObj.has("genderId")) {
				genderId = Long.parseLong(jsonObj.get("genderId").toString());
				if (jsonObj.get("genderId").toString().length() > 0
						&& !jsonObj.get("genderId").toString().equalsIgnoreCase("0")) {
					criteria.add(Restrictions.like("genderId", genderId));
				}
			}

			totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			idealWeightsList = criteria.list();

			mapObj.put("totalMatches", totalMatches);
			mapObj.put("idealWeightsList", idealWeightsList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public List<MasIdealWeight> validateIdealWeight(Long genderId, String fromAge, String toAge, Long weight) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria = null;
		criteria = session.createCriteria(MasIdealWeight.class)
							.add(Restrictions.eq("genderId", genderId)).add(Restrictions.eq("fromAge", fromAge))
									.add(Restrictions.eq("toAge", toAge)).add(Restrictions.eq("weight", weight));
		List<MasIdealWeight> idealWeightList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return idealWeightList;
	}
	

	@Override
	public String addIdealWeight (MasIdealWeight idealWeightObj) {
		String result="";
		System.out.println("idealWeightObj"+idealWeightObj);
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();		
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(idealWeightObj);
			tx.commit();
			if(savedObj!=null) {
				result = "200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}
	
	@Override
	public String updateIdealWeight(JSONObject jsonObject) {
	 String result="";
	 long d = System.currentTimeMillis();
	 Date lastChgDate = new Date(d);
	 try {
	  if (jsonObject != null) {
	   List<MasIdealWeight> masIdealWeightsList = new ArrayList<MasIdealWeight>();
	   Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
	   //Criteria criteria = session.createCriteria(MasIdealWeight.class);
	   String hStatus = "";
	   Long idealWtId;
	   System.out.println("jsonObject+jsonObject+jsonObject ::"+jsonObject);
	   if (jsonObject.has("idealWeightId")) {
	    String selectAge = jsonObject.get("selectAge").toString();
	    String[] ageArray = JavaUtils.getSplitString(selectAge);
	    String fromAge = ageArray[0];
	    String toAge = ageArray[1];
	    idealWtId = Long.parseLong(jsonObject.get("idealWeightId").toString());
	     /*criteria.add(Restrictions.eq("idealWeightId", idealWtId));
	     masIdealWeightsList = criteria.list();*/

	    /*for (int i = 0; i < masIdealWeightsList.size(); i++) {
	     Long idealWeightId = masIdealWeightsList.get(i).getIdealWeightId();*/

	     Object object = session.load(MasIdealWeight.class, idealWtId);
	     MasIdealWeight idealWeight = (MasIdealWeight) object;

	     Transaction transaction = session.beginTransaction();

	     if (jsonObject.has("status")) {       
	      hStatus = jsonObject.get("status").toString();       
	      if (hStatus.equalsIgnoreCase("active") || hStatus.equalsIgnoreCase("inactive")) {
	       if (hStatus.equalsIgnoreCase("active"))
	        idealWeight.setStatus("N");
	       else
	        idealWeight.setStatus("Y");
	       session.update(idealWeight);
	       transaction.commit();
	       result = "200";
	      } else {
	       
	      // idealWeight.setGenderId(Long.parseLong(jsonObject.get("genderId").toString()));	       
	       idealWeight.setWeight(Long.parseLong(jsonObject.get("weight").toString()));

	       /*Users user = new Users();
	       user.setUserId(new Long(1));
	       idealWeight.setUser(user);*/

	       // masNursingCare.setLastChgBy(new Long(1));
	       idealWeight.setLastChgDate(lastChgDate);
	       session.update(idealWeight);
	       transaction.commit();
	       result = "200";
	      }

	     }

	    //}
	   }
	  }

	 } catch (Exception e) {
	  e.printStackTrace();
	 } finally {
	  getHibernateUtils.getHibernateUtlis().CloseConnection();
	 }
	 return result;
	}
	
	
	/***********************************************/
	@Override
	public Map<String, List<MasNursingCare>> getAllmNursingData(JSONObject jsonObj) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo=0;

		Map<String, List<MasNursingCare>> mapObj = new HashMap<String, List<MasNursingCare>>();
		List<MasNursingCare> mHospitalList = new ArrayList<MasNursingCare>();
		List totalMatches = new ArrayList();

		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasNursingCare.class);

			if (jsonObj.get("PN") != null)
				pageNo = Integer.parseInt(jsonObj.get("PN").toString());

			String hName = "";
			String hCode = "";
			if (jsonObj.has("nursingName")) {
				hName = jsonObj.get("nursingName") + "%";
				if (jsonObj.get("nursingName").toString().length() > 0
						&& !jsonObj.get("nursingName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("nursingName", hName));
				}
			}
			if (jsonObj.has("nursingCode")) {

				hCode = jsonObj.get("nursingCode") + "%";
				if (jsonObj.get("nursingCode").toString().length() > 0
						&& !jsonObj.get("nursingCode").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("nursingCode", hCode));
				}
			}

			totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setMaxResults(pageSize);
			mHospitalList = criteria.list();

			mapObj.put("totalMatches", totalMatches);
			mapObj.put("mHospitalList", mHospitalList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public List<MasNursingCare> validateMasNursing(String nursingCode, String nursingName, String nursingType) {
		// TODO Auto-generated method stub
		List<MasNursingCare> nursingList = new ArrayList<MasNursingCare>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasNursingCare.class);
			criteria.add(Restrictions.and(Restrictions.eq("nursingCode", nursingCode),
					Restrictions.eq("nusingName", nursingName), Restrictions.eq("nursingType", nursingType)));
			nursingList = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return nursingList;
	}

	@Override
	public String addMasNursing(MasNursingCare nursingObj) {
		// TODO Auto-generated method stub
		String result = "";
		try {

			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction transaction = session.beginTransaction();
			Serializable savedObj = session.save(nursingObj);

			transaction.commit();

			if (savedObj != null) {

				result = "200";
			} else {
				result = "500";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}

	@Override
	public String updateMasNursing(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		String result = "";
		long d = System.currentTimeMillis();
		Date lastChgDate = new Date(d);
		try {
			if (jsonObject != null) {

				List<MasNursingCare> masNursingCarelList = new ArrayList<MasNursingCare>();
				Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasNursingCare.class);
				String hStatus = "";
				Long nursingId;
				if (jsonObject.has("nursingId")) {
					nursingId = Long.parseLong(jsonObject.get("nursingId").toString());

					criteria.add(Restrictions.eq("nursingId", nursingId));

					masNursingCarelList = criteria.list();

					for (int i = 0; i < masNursingCarelList.size(); i++) {
						Long masNursingId = masNursingCarelList.get(i).getNursingId();

						Object object = session.load(MasNursingCare.class, masNursingId);
						MasNursingCare masNursingCare = (MasNursingCare) object;

						Transaction transaction = session.beginTransaction();

						if (jsonObject.has("status")) {
							System.out.println("status :: " + jsonObject.has("status"));
							hStatus = jsonObject.get("status").toString();
							System.out.println("status :: " + hStatus);
							if (hStatus.equalsIgnoreCase("active") || hStatus.equalsIgnoreCase("inactive")) {
								if (hStatus.equalsIgnoreCase("active"))
									masNursingCare.setStatus("Y");
								else
									masNursingCare.setStatus("N");
								session.update(masNursingCare);
								transaction.commit();
								result = "200";
							} else {
								masNursingCare.setNursingCode(jsonObject.get("nursingCode").toString().toUpperCase());
								masNursingCare.setNursingName(jsonObject.get("nursingName").toString().toUpperCase());
								masNursingCare.setNursingType(jsonObject.get("nursingType").toString().toUpperCase());

								Users user = new Users();
								user.setUserId(new Long(1));
								masNursingCare.setUser(user);

								// masNursingCare.setLastChgBy(new Long(1));
								masNursingCare.setLastChgDate(lastChgDate);
								session.update(masNursingCare);
								transaction.commit();
								result = "200";
							}

						}

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public List<MasIdealWeight> getAge(JSONObject jsonObject) {
		List<MasIdealWeight> weightList = new ArrayList<MasIdealWeight>();
		StringBuffer buffer = new StringBuffer();
		List listObject = new ArrayList();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasIdealWeight.class);
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("idealWeightId").as("idealWeightId"));
			
			criteria.setProjection(projectionList);

			weightList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasIdealWeight.class)).list();
			Iterator iterator = weightList.iterator();
			while (iterator.hasNext()) {
				MasIdealWeight object = (MasIdealWeight) iterator.next();
				Map<Object, Object> map = new HashMap<Object, Object>();
				long idealWeightId = object.getIdealWeightId();
				map.put("idealWeightId", idealWeightId);
				listObject.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return listObject;
	}

		/****************************Service Type*********************************************************/

	@Override
	public Map<String, List<MasServiceType>> getAllServiceType(JSONObject jsonObj) {
		Map<String, List<MasServiceType>> mapObj = new HashMap<String, List<MasServiceType>>();
		int pageSize = 5;
		int pageNo = 1;

		List totalMatches = new ArrayList();
		List<MasServiceType> masServiceTypeList = new ArrayList<MasServiceType>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria = session.createCriteria(MasServiceType.class);

		if (jsonObj.get("PN") != null)
			pageNo = Integer.parseInt(jsonObj.get("PN").toString());

		String stName = "";
		if (jsonObj.has("serviceTypeName")) {
			stName = jsonObj.get("serviceTypeName") + "%";
			if (jsonObj.get("serviceTypeName").toString().length() > 0
					&& !jsonObj.get("serviceTypeName").toString().trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.like("serviceTypeName", stName));

			}
		}
		criteria.addOrder(Order.asc("serviceTypeName"));

		totalMatches = criteria.list();
		criteria.setFirstResult((pageSize) * (pageNo - 1));
		criteria.setMaxResults(pageSize);

		masServiceTypeList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		mapObj.put("masServiceTypeList", masServiceTypeList);
		mapObj.put("totalMatches", totalMatches);
		return mapObj;
	}

	@Override
	public String updateServiceType(Long serviceTypeId, String serviceTypeName) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			if (serviceTypeId != 0) {
				Object stObject = session.load(MasServiceType.class, serviceTypeId);
				MasServiceType masServiceType = (MasServiceType) stObject;					
				Transaction transaction = session.beginTransaction();				
				masServiceType.setServiceTypeName(serviceTypeName);
				masServiceType.setStatus("Y");			
				Users users = new Users(); 
				users.setUserId(new Long(1));
				masServiceType.setUser(users);				
				long d = System.currentTimeMillis();
				Date date = new Date(d);
				masServiceType.setLastChgDate(date);
				session.merge(masServiceType);
				transaction.commit();
				result = "200";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return result;
	}



	@Override
	public String addServiceType(MasServiceType masServiceType) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Criteria cr1=session.createCriteria(MasServiceType.class).add(Restrictions.eq("serviceTypeCode", masServiceType.getServiceTypeCode()));
			Criteria cr2=session.createCriteria(MasServiceType.class).add(Restrictions.eq("serviceTypeName", masServiceType.getServiceTypeName()));
			List<MasServiceType> masList1=cr1.list();
			List<MasServiceType> masList2=cr2.list();
			if(masList1 !=null && !masList1.isEmpty()){
				result="serviceTypeCodeExist";
			}
			else if(masList2 !=null && !masList2.isEmpty()) {
				result="serviceTypeNameExist";
			}else {
					Serializable savedObj = session.save(masServiceType);
					tx.commit();
					if (savedObj != null) {
						result = "200";
					} else {
						result = "500";
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateServiceTypeStatus(Long serviceTypeId, String serviceTypeCode, String status) {
		String result = "";
		try {

			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object stObject = session.load(MasServiceType.class, serviceTypeId);
			MasServiceType masServiceType = (MasServiceType) stObject;

			Transaction transaction = session.beginTransaction();
			if (masServiceType.getStatus().equalsIgnoreCase("Y")) {
				masServiceType.setStatus("N");

			} else if (masServiceType.getStatus().equalsIgnoreCase("N")) {
				masServiceType.setStatus("Y");

			} else {
				masServiceType.setStatus("Y");	
			}

			session.update(masServiceType);
			transaction.commit();
			result = "200";
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}
	
	
	
	/**----------------MAS RANK--------------------*/

	@Override
	public List<MasRank> validateMasRank(String rankCode, String rankName) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		//List<MasRank> rankList = new ArrayList<MasRank>();
		//List<MasRank> rankList = null;
		Criteria criteria = null;
		criteria = session.createCriteria(MasRank.class)
							.add(Restrictions.or(Restrictions.eq("rankCode", rankCode), Restrictions.eq("rankName", rankName)));
		List<MasRank> rankList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return rankList;
	}

	/*
	 * @Override public List<MasRank> validateMasRankUpdate(Long employeeCategoryId,
	 * String rankName) { Session session =
	 * getHibernateUtils.getHibernateUtlis().OpenSession(); //List<MasRank> rankList
	 * = new ArrayList<MasRank>(); //List<MasRank> rankList = null; Criteria
	 * criteria = null; criteria = session.createCriteria(MasRank.class)
	 * .add(Restrictions.and(Restrictions.eq("employeeCategoryId",
	 * employeeCategoryId), Restrictions.eq("rankName", rankName))); List<MasRank>
	 * rankList = criteria.list();
	 * getHibernateUtils.getHibernateUtlis().CloseConnection(); return rankList; }
	 */
	
	@Override
	public List<MasRank> validateMasRankUpdate(Long employeeCategoryId, String rankName){
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasRank> rankList = new ArrayList<MasRank>();
		Criteria criteria = session.createCriteria(MasRank.class);
		criteria.createAlias( "masEmployeeCategory", "mec" );
		criteria.add(Restrictions.and(Restrictions.eq("mec.employeeCategoryId",employeeCategoryId), Restrictions.eq("rankName", rankName)));
		rankList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return rankList;
	}

	@Override
	public String addMasRank(MasRank masRank) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();		
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masRank);
			tx.commit();
			if(savedObj!=null) {
				result = "200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasRank chkRank(String rankName) {
		
		MasRank masRank = new MasRank();	
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasRank.class);
		criteria.add(Restrictions.eq("rankName", rankName));
		masRank = (MasRank)criteria.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		return masRank;
	}

	@Override
	public Map<String, List<MasRank>>  getAllRank(JSONObject jsonObj){
		Map<String, List<MasRank>> mapObj = new HashMap<String, List<MasRank>>();
		int pageSize = 5;
		int pageNo=1;
		
		List totalMatches = new ArrayList();
		 
		List<MasRank> masRankList = new ArrayList<MasRank>();
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasRank.class);		
			System.out.println("jsonObj dao  :: " +jsonObj);		
			if( jsonObj.get("PN")!=null)
			 pageNo = Integer.parseInt(jsonObj.get("PN").toString());
			System.out.println("pageNo  :: " +pageNo);		
			String rankName="";
				 if (jsonObj.has("rankName"))
				 {
					  rankName = jsonObj.get("rankName")+"%";
					  if(jsonObj.get("rankName").toString().length()>0 && !jsonObj.get("rankName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("rankName", rankName));
						}
				 }	
				 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("rankId").as("rankId"));
			projectionList.add(Projections.property("rankCode").as("rankCode"));
			projectionList.add(Projections.property("rankName").as("rankName"));
			projectionList.add(Projections.property("status").as("status"));
			projectionList.add(Projections.property("masEmployeeCategory").as("masEmployeeCategory"));
			criteria.addOrder(Order.asc("rankName"));
			
			totalMatches = criteria.list();
			System.out.println("totalMatches :: "+totalMatches.size());
					
			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setProjection(projectionList).setMaxResults(pageSize);
			masRankList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasRank.class)).list();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			mapObj.put("masRankList", masRankList);
			mapObj.put("totalMatches", totalMatches);
			return mapObj;
		}
		
		
		


	@Override
	public List<MasRank> getRank(String rankName){
		Object[] status = new Object[] {"y"};
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasRank> masRankList = new ArrayList<MasRank>();
		Criteria criteria = session.createCriteria(MasRank.class);
		if(rankName.length()>0 && !rankName.trim().equalsIgnoreCase("")) {
			
			criteria.add(Restrictions.like("rankName", rankName));
		}
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("rankId").as("rankId"));
		projectionList.add(Projections.property("rankCode").as("rankCode"));
		projectionList.add(Projections.property("rankName").as("rankName"));
		projectionList.add(Projections.property("status").as("status"));
		
		criteria.setProjection(projectionList);
		
		masRankList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasRank.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return masRankList;
	}

	@Override
	public String updateRank(Long rankId, String rankCode, String rankName, Long employeeCategoryId) {	
		String result="";
		try {
			System.out.println("dao 1 rankId :: "+rankId);
			System.out.println("dao 1 rankCode :: "+rankCode);
			System.out.println("dao 1 rankName :: "+rankName);
			System.out.println("dao 1 employeeCategoryId :: "+employeeCategoryId);
		
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				MasRank masRank =  (MasRank)session.get(MasRank.class, rankId);
				
			
				
				if(masRank != null)
				{
				
				Transaction transaction = session.beginTransaction();
				masRank.setRankCode(rankCode);
				masRank.setRankName(rankName);
				masRank.setStatus("y");
				masRank.getMasEmployeeCategory().getEmployeeCategoryName();
				//Users usr = new Users();
				//usr.setUserId(new Long(1)); // userId will be fetch from session
				//masRank.setLastChgBy(new Long(1));
				MasEmployeeCategory employeeCategory = new MasEmployeeCategory();
				employeeCategory.setEmployeeCategoryId(employeeCategoryId);
				
				masRank.setMasEmployeeCategory(employeeCategory);
				
				long d = System.currentTimeMillis();
				Date date = new Date(d);
				
				masRank.setLastChgDate(date);
				//String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				session.update(masRank);
				
				transaction.commit();
				
				result="200";	
				}	
				else {
					session.update("msg","RankId dose not found");
				}
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
		
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		
		return result;
	}

	@Override
	public String updateRankStatus(Long rankId,String rankCode,String status) {
		//Object[] status = new Object[] {"y"};
		String result = "";
		try {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasRank> masRankStatusList = new ArrayList<MasRank>();
		Criteria criteria = session.createCriteria(MasRank.class,"masrank");
		criteria.add(Restrictions.and(Restrictions.eq("rankId", rankId),Restrictions.eq("rankCode", rankCode),Restrictions.eq("masrank.status", status)));
		//criteria.add(Restrictions.in("mascommand.status", status));
		masRankStatusList = criteria.list();
			for(int i=0;i<masRankStatusList.size();i++) {
				Long rankId1 = masRankStatusList.get(i).getRankId();
				
				Object rankObject =  session.load(MasRank.class, rankId1);
				MasRank masRank = (MasRank)rankObject;
				
				Transaction transaction = session.beginTransaction();
				if(masRank.getStatus().equalsIgnoreCase("y")){
					masRank.setStatus("n");
					//result="400";
				}else if(masRank.getStatus().equalsIgnoreCase("n")) {
					masRank.setStatus("y");
					//result="200";
				}else {
					masRank.setStatus("y");
				}
				
				session.update(masRank);
				transaction.commit();
				result="200";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public List<MasEmployeeCategory> getEmployeeCategoryList() {
		List<MasEmployeeCategory> employeeCategoryList = new ArrayList<MasEmployeeCategory>();
		try {	 
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasEmployeeCategory.class);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("employeeCategoryId").as("employeeCategoryId"));		
		projectionList.add(Projections.property("employeeCategoryName").as("employeeCategoryName"));
		criteria.setProjection(projectionList);
		
		employeeCategoryList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasEmployeeCategory.class)).list();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return employeeCategoryList;
	}

	/***************************************MAS TRADE*************************************************************/

	@Override
	public List<MasTrade> validateMasTrade(String tradeName) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasTrade> tradeList = new ArrayList<MasTrade>();
		Criteria criteria = session.createCriteria(MasTrade.class);
		criteria.add(Restrictions.or(Restrictions.eq("tradeName", tradeName)));
		tradeList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return tradeList;
	}

	@Override
	public List<MasTrade> validateMasTradeUpdate(String tradeName) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasTrade> tradeList = new ArrayList<MasTrade>();
		Criteria criteria = session.createCriteria(MasTrade.class);
		criteria.add(Restrictions.and(Restrictions.eq("tradeName", tradeName)));
		tradeList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return tradeList;
	}

	@Override
	public String addMasTrade(MasTrade masTrade) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();		
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masTrade);
			tx.commit();
			if(savedObj!=null) {
				result = "200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasTrade checkTrade(String tradeName) {
		
		MasTrade masTrade = new MasTrade();	
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasTrade.class);
		criteria.add(Restrictions.eq("tradeName", tradeName));
		masTrade = (MasTrade)criteria.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		return masTrade;
	}

	@Override
	public Map<String, List<MasTrade>>  getAllTrade(JSONObject jsonObj){
		Map<String, List<MasTrade>> mapObj = new HashMap<String, List<MasTrade>>();
		int pageSize = 5;
		int pageNo=1;
		
		List totalMatches = new ArrayList();
		 
		List<MasTrade> masTradeList = new ArrayList<MasTrade>();
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasTrade.class);		
			System.out.println("jsonObj dao  :: " +jsonObj);		
			if( jsonObj.get("PN")!=null)
			 pageNo = Integer.parseInt(jsonObj.get("PN").toString());
			System.out.println("pageNo  :: " +pageNo);		
			String tradeName="";
				 if (jsonObj.has("tradeName"))
				 {
					 tradeName = jsonObj.get("tradeName")+"%";
					  if(jsonObj.get("tradeName").toString().length()>0 && !jsonObj.get("tradeName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("tradeName", tradeName));
						}
				 }	
				 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("tradeId").as("tradeId"));
			projectionList.add(Projections.property("tradeName").as("tradeName"));
			projectionList.add(Projections.property("status").as("status"));
			projectionList.add(Projections.property("masServiceType").as("masServiceType"));
			criteria.addOrder(Order.asc("tradeName"));
			
			totalMatches = criteria.list();
			System.out.println("totalMatches :: "+totalMatches.size());
					
			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setProjection(projectionList).setMaxResults(pageSize);
			masTradeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasTrade.class)).list();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			mapObj.put("masTradeList", masTradeList);
			mapObj.put("totalMatches", totalMatches);
			return mapObj;
		}
		
		
		


	@Override
	public List<MasTrade> getTrade(String tradeName){
		Object[] status = new Object[] {"y"};
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasTrade> masTradeList = new ArrayList<MasTrade>();
		Criteria criteria = session.createCriteria(MasTrade.class);
		if(tradeName.length()>0 && !tradeName.trim().equalsIgnoreCase("")) {
			
			criteria.add(Restrictions.like("tradeName", tradeName));
		}
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("tradeId").as("tradeId"));
		projectionList.add(Projections.property("tradeName").as("tradeName"));
		projectionList.add(Projections.property("status").as("status"));
		
		criteria.setProjection(projectionList);
		
		masTradeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasTrade.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return masTradeList;
	}

	@Override
	public String updateTrade(Long tradeId, String tradeName, Long serviceTypeId) {	
		String result="";
		try {
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				MasTrade masTrade =  (MasTrade)session.get(MasTrade.class, tradeId);
				
			
				
				if(masTrade != null)
				{
				
				Transaction transaction = session.beginTransaction();
				masTrade.setTradeName(tradeName);
				masTrade.setStatus("y");
				masTrade.getMasServiceType().getServiceTypeName();
				Users usr = new Users();
				usr.setUserId(new Long(1)); // userId will be fetch from session
				//masTrade.setLastChgBy(new Long(1));
				MasServiceType masServiceType = new MasServiceType();
				masServiceType.setServiceTypeId(serviceTypeId);
				
				masTrade.setMasServiceType(masServiceType);
				
				long d = System.currentTimeMillis();
				Date date = new Date(d);
				
				masTrade.setLastChgDate(date);
				//String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				session.update(masTrade);
				
				transaction.commit();
				
				result="200";	
				}	
				else {
					session.update("msg","TradeId dose not found");
				}
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
		
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		
		return result;
	}

	@Override
	public String updateTradeStatus(Long tradeId,String status) {
		//Object[] status = new Object[] {"y"};
		String result = "";
		try {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasTrade> masTradeStatusList = new ArrayList<MasTrade>();
		Criteria criteria = session.createCriteria(MasTrade.class,"masTrade");
		criteria.add(Restrictions.and(Restrictions.eq("tradeId", tradeId),Restrictions.eq("masTrade.status", status)));
		//criteria.add(Restrictions.in("mascommand.status", status));
		masTradeStatusList = criteria.list();
			for(int i=0;i<masTradeStatusList.size();i++) {
				Long tradeId1 = masTradeStatusList.get(i).getTradeId();
				
				Object tradeObject =  session.load(MasTrade.class, tradeId1);
				MasTrade masTrade = (MasTrade)tradeObject;
				
				Transaction transaction = session.beginTransaction();
				if(masTrade.getStatus().equalsIgnoreCase("y")){
					masTrade.setStatus("n");
					//result="400";
				}else if(masTrade.getStatus().equalsIgnoreCase("n")) {
					masTrade.setStatus("y");
					//result="200";
				}else {
					masTrade.setStatus("y");
				}
				
				session.update(masTrade);
				transaction.commit();
				result="200";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public List<MasServiceType> getServiceTypeList() {
		List<MasServiceType> serviceTypeList = new ArrayList<MasServiceType>();
		try {	 
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasServiceType.class);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("serviceTypeId").as("serviceTypeId"));		
		projectionList.add(Projections.property("serviceTypeName").as("serviceTypeName"));
		criteria.setProjection(projectionList);
		
		serviceTypeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasServiceType.class)).list();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return serviceTypeList;
	}

	/****************************MAS RELIGION*********************************************************/
	@Override
	public Map<String, List<MasReligion>> getAllReligion(JSONObject jsondata) {
		Map<String, List<MasReligion>> map = new HashMap<String, List<MasReligion>>();
		List<MasReligion> religionList = new ArrayList<MasReligion>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasReligion.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String rgName="";
				 if (jsondata.has("religionName"))
				 {
					  rgName = jsondata.get("religionName")+"%";
					  if(jsondata.get("religionName").toString().length()>0 && !jsondata.get("religionName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("religionName", rgName));
							//criteria.addOrder(Order.asc(jsondata.get("religionName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 religionList = criteria.list();
			
			
		map.put("religionList", religionList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasReligion> validateReligion(String religionCode, String religionName) {
		List<MasReligion> reliList =  new ArrayList<MasReligion>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasReligion.class);
				criteria.add(Restrictions.or(Restrictions.eq("religionCode", religionCode), Restrictions.eq("religionName", religionName)));
				reliList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return reliList;
	}

	@Override
	public List<MasReligion> validateReligionUpdate(String religionCode, String religionName) {
		List<MasReligion> reliList =  new ArrayList<MasReligion>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasReligion.class);
				criteria.add(Restrictions.and(Restrictions.eq("religionCode", religionCode), Restrictions.eq("religionName", religionName)));
				reliList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return reliList;
	}

	@Override
	public String addReligion(MasReligion masReligion) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masReligion);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateReligionDetails(Long religionId, String religionCode, String religionName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					Object object = session.load(MasReligion.class, religionId);
					MasReligion masReligion = (MasReligion)object;
					
					Transaction transaction = session.beginTransaction();
					masReligion.setReligionCode(religionCode.toUpperCase());
					masReligion.setReligionName(religionName.toUpperCase());			
									
					//masReligion.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					masReligion.setLastChgDate(date);
					session.update(masReligion);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasReligion checkReligion(String religionCode) {
		MasReligion mReligion = new MasReligion();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasReligion.class);		
			criteria.add(Restrictions.eq("religionCode", religionCode));
			mReligion = (MasReligion)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mReligion;
	}

	@Override
	public String updateReligionStatus(Long religionId, String religionCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Object object =  session.load(MasReligion.class, religionId);
				
				MasReligion masReligion = (MasReligion)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masReligion.getStatus().equalsIgnoreCase("Y") || masReligion.getStatus().equalsIgnoreCase("y")) {
					masReligion.setStatus("N");
				}else if(masReligion.getStatus().equalsIgnoreCase("N") || masReligion.getStatus().equalsIgnoreCase("n")) {
					masReligion.setStatus("Y");
				}else {
					masReligion.setStatus("Y");
				}
				session.update(masReligion);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************MAS MARITAL STATUS*********************************************************/
	@Override
	public Map<String, List<MasMaritalStatus>> getAllMaritalStatus(JSONObject jsondata) {
		Map<String, List<MasMaritalStatus>> map = new HashMap<String, List<MasMaritalStatus>>();
		List<MasMaritalStatus> maritalStatusList = new ArrayList<MasMaritalStatus>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasMaritalStatus.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String msName="";
				 if (jsondata.has("maritalStatusName"))
				 {
					  msName = jsondata.get("maritalStatusName")+"%";
					  if(jsondata.get("maritalStatusName").toString().length()>0 && !jsondata.get("maritalStatusName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("maritalStatusName", msName));
							//criteria.addOrder(Order.asc(jsondata.get("maritalStatusName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 maritalStatusList = criteria.list();
			
			
		map.put("maritalStatusList", maritalStatusList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasMaritalStatus> validateMaritalStatus(String maritalStatusCode, String maritalStatusName) {
		List<MasMaritalStatus> marsList =  new ArrayList<MasMaritalStatus>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasMaritalStatus.class);
				criteria.add(Restrictions.or(Restrictions.eq("maritalStatusCode", maritalStatusCode), Restrictions.eq("maritalStatusName", maritalStatusName)));
				marsList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return marsList;
	}

	@Override
	public List<MasMaritalStatus> validateMaritalStatusUpdate(String maritalStatusName) {
		List<MasMaritalStatus> marsList =  new ArrayList<MasMaritalStatus>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasMaritalStatus.class);
				criteria.add(Restrictions.eq("maritalStatusName", maritalStatusName));
				marsList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return marsList;
	}

	@Override
	public String addMaritalStatus(MasMaritalStatus masMaritalStatus) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masMaritalStatus);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateMaritalStatusDetails(Long maritalStatusId, String maritalStatusCode, String maritalStatusName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					Object object = session.load(MasMaritalStatus.class, maritalStatusId);
					MasMaritalStatus masMaritalStatus = (MasMaritalStatus)object;
					
					Transaction transaction = session.beginTransaction();
					masMaritalStatus.setMaritalStatusCode(maritalStatusCode.toUpperCase());
					masMaritalStatus.setMaritalStatusName(maritalStatusName.toUpperCase());			
									
					//masReligion.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					//masMaritalStatus.setLastChgDate(date);
					session.update(masMaritalStatus);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasMaritalStatus checkMaritalStatus(String maritalStatusCode) {
		MasMaritalStatus mMaritalStatus = new MasMaritalStatus();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasMaritalStatus.class);		
			criteria.add(Restrictions.eq("maritalStatusCode", maritalStatusCode));
			mMaritalStatus = (MasMaritalStatus)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mMaritalStatus;
	}

	@Override
	public String updateMaritalStatusStatus(Long maritalStatusId, String maritalStatusCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Object object =  session.load(MasMaritalStatus.class, maritalStatusId);
				
				MasMaritalStatus masMaritalStatus = (MasMaritalStatus)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masMaritalStatus.getStatus().equalsIgnoreCase("Y") || masMaritalStatus.getStatus().equalsIgnoreCase("y")) {
					masMaritalStatus.setStatus("N");
				}else if(masMaritalStatus.getStatus().equalsIgnoreCase("N") || masMaritalStatus.getStatus().equalsIgnoreCase("n")) {
					masMaritalStatus.setStatus("Y");
				}else {
					masMaritalStatus.setStatus("Y");
				}
				session.update(masMaritalStatus);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************MAS EMPLOYEE CATEGORY*********************************************************/
	@Override
	public Map<String, List<MasEmployeeCategory>> getAllEmployeeCategory(JSONObject jsondata) {
		Map<String, List<MasEmployeeCategory>> map = new HashMap<String, List<MasEmployeeCategory>>();
		List<MasEmployeeCategory> employeeCategoryList = new ArrayList<MasEmployeeCategory>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasEmployeeCategory.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String ecName="";
				 if (jsondata.has("employeeCategoryName"))
				 {
					  ecName = jsondata.get("employeeCategoryName")+"%";
					  if(jsondata.get("employeeCategoryName").toString().length()>0 && !jsondata.get("employeeCategoryName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("employeeCategoryName", ecName));
							//criteria.addOrder(Order.asc(jsondata.get("employeeCategoryName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 employeeCategoryList = criteria.list();
			
			
		map.put("employeeCategoryList", employeeCategoryList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasEmployeeCategory> validateEmployeeCategory(Long employeeCategoryCode, String employeeCategoryName) {
		List<MasEmployeeCategory> ecList =  new ArrayList<MasEmployeeCategory>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasEmployeeCategory.class);
				criteria.add(Restrictions.or(Restrictions.eq("employeeCategoryCode", employeeCategoryCode), Restrictions.eq("employeeCategoryName", employeeCategoryName)));
				ecList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return ecList;
	}

	@Override
	public List<MasEmployeeCategory> validateEmployeeCategoryUpdate(Long employeeCategoryCode, String employeeCategoryName) {
		List<MasEmployeeCategory> ecList =  new ArrayList<MasEmployeeCategory>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasEmployeeCategory.class);
				criteria.add(Restrictions.and(Restrictions.eq("employeeCategoryCode", employeeCategoryCode), Restrictions.eq("employeeCategoryName", employeeCategoryName)));
				ecList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return ecList;
	}

	@Override
	public String addEmployeeCategory(MasEmployeeCategory masEmployeeCategory) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masEmployeeCategory);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateEmployeeCategoryDetails(Long employeeCategoryId, String employeeCategoryCode, String employeeCategoryName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					
					Object object = session.load(MasEmployeeCategory.class, employeeCategoryId);
					MasEmployeeCategory masEmployeeCategory = (MasEmployeeCategory)object;
					
					Transaction transaction = session.beginTransaction();
					masEmployeeCategory.setEmployeeCategoryCode(Long.parseLong(employeeCategoryCode.toString().toUpperCase()));
					masEmployeeCategory.setEmployeeCategoryName(employeeCategoryName.toUpperCase());			
									
					//masEmployeeCategory.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					//masMaritalStatus.setLastChgDate(date);
					session.update(masEmployeeCategory);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasEmployeeCategory checkEmployeeCategory(String employeeCategoryCode) {
		MasEmployeeCategory mEmployeeCategory = new MasEmployeeCategory();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasEmployeeCategory.class);		
			criteria.add(Restrictions.eq("employeeCategoryCode", employeeCategoryCode));
			mEmployeeCategory = (MasEmployeeCategory)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mEmployeeCategory;
	}

	@Override
	public String updateEmployeeCategoryStatus(Long employeeCategoryId, String employeeCategoryCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Object object =  session.load(MasEmployeeCategory.class, employeeCategoryId);
				
				MasEmployeeCategory masEmployeeCategory = (MasEmployeeCategory)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masEmployeeCategory.getStatus().equalsIgnoreCase("Y") || masEmployeeCategory.getStatus().equalsIgnoreCase("y")) {
					masEmployeeCategory.setStatus("N");
				}else if(masEmployeeCategory.getStatus().equalsIgnoreCase("N") || masEmployeeCategory.getStatus().equalsIgnoreCase("n")) {
					masEmployeeCategory.setStatus("Y");
				}else {
					masEmployeeCategory.setStatus("Y");
				}
				session.update(masEmployeeCategory);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************MAS Administrative Sex*********************************************************/
	@Override
	public Map<String, List<MasAdministrativeSex>> getAllAdministrativeSex(JSONObject jsondata) {
		Map<String, List<MasAdministrativeSex>> map = new HashMap<String, List<MasAdministrativeSex>>();
		List<MasAdministrativeSex> administrativeSexList = new ArrayList<MasAdministrativeSex>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasAdministrativeSex.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String asName="";
				 if (jsondata.has("administrativeSexName"))
				 {
					  asName = jsondata.get("administrativeSexName")+"%";
					  if(jsondata.get("administrativeSexName").toString().length()>0 && !jsondata.get("administrativeSexName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("administrativeSexName", asName));
							//criteria.addOrder(Order.asc(jsondata.get("administrativeSexName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 administrativeSexList = criteria.list();
			
			
		map.put("administrativeSexList", administrativeSexList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasAdministrativeSex> validateAdministrativeSex(String administrativeSexCode, String administrativeSexName) {
		List<MasAdministrativeSex> ecList =  new ArrayList<MasAdministrativeSex>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasAdministrativeSex.class);
				criteria.add(Restrictions.or(Restrictions.eq("administrativeSexCode", administrativeSexCode), Restrictions.eq("administrativeSexName", administrativeSexName)));
				ecList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return ecList;
	}

	@Override
	public List<MasAdministrativeSex> validateAdministrativeSexUpdate(String administrativeSexCode, String administrativeSexName) {
		List<MasAdministrativeSex> ecList =  new ArrayList<MasAdministrativeSex>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasAdministrativeSex.class);
				criteria.add(Restrictions.and(Restrictions.eq("administrativeSexCode", administrativeSexCode), Restrictions.eq("administrativeSexName", administrativeSexName)));
				ecList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return ecList;
	}

	@Override
	public String addAdministrativeSex(MasAdministrativeSex masAdministrativeSex) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masAdministrativeSex);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateAdministrativeSexDetails(Long administrativeSexId, String administrativeSexCode, String administrativeSexName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					Object object = session.load(MasAdministrativeSex.class, administrativeSexId);
					MasAdministrativeSex masAdministrativeSex = (MasAdministrativeSex)object;
					
					Transaction transaction = session.beginTransaction();
					masAdministrativeSex.setAdministrativeSexCode(administrativeSexCode.toUpperCase());
					masAdministrativeSex.setAdministrativeSexName(administrativeSexName.toUpperCase());			
									
					//masEmployeeCategory.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					//masMaritalStatus.setLastChgDate(date);
					session.update(masAdministrativeSex);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasAdministrativeSex checkAdministrativeSex(String administrativeSexCode) {
		MasAdministrativeSex mAdministrativeSex = new MasAdministrativeSex();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasAdministrativeSex.class);		
			criteria.add(Restrictions.eq("administrativeSexCode", administrativeSexCode));
			mAdministrativeSex = (MasAdministrativeSex)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mAdministrativeSex;
	}

	@Override
	public String updateAdministrativeSexStatus(Long administrativeSexId, String administrativeSexCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				
				Object object =  session.load(MasAdministrativeSex.class, administrativeSexId);
				
				MasAdministrativeSex masAdministrativeSex = (MasAdministrativeSex)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masAdministrativeSex.getStatus().equalsIgnoreCase("Y") || masAdministrativeSex.getStatus().equalsIgnoreCase("y")) {
					masAdministrativeSex.setStatus("N");
				}else if(masAdministrativeSex.getStatus().equalsIgnoreCase("N") || masAdministrativeSex.getStatus().equalsIgnoreCase("n")) {
					masAdministrativeSex.setStatus("Y");
				}else {
					masAdministrativeSex.setStatus("Y");
				}
				session.update(masAdministrativeSex);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************MAS MedicalCategory*********************************************************/
	@Override
	public Map<String, List<MasMedicalCategory>> getAllMedicalCategory(JSONObject jsondata) {
		Map<String, List<MasMedicalCategory>> map = new HashMap<String, List<MasMedicalCategory>>();
		List<MasMedicalCategory> medicalCategoryList = new ArrayList<MasMedicalCategory>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasMedicalCategory.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String mCatName="";
				 if (jsondata.has("medicalCategoryName"))
				 {
					  mCatName = jsondata.get("medicalCategoryName")+"%";
					  if(jsondata.get("medicalCategoryName").toString().length()>0 && !jsondata.get("medicalCategoryName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("medicalCategoryName", mCatName));
							//criteria.addOrder(Order.asc(jsondata.get("medicalCategoryName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 medicalCategoryList = criteria.list();
			
			
		map.put("medicalCategoryList", medicalCategoryList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasMedicalCategory> validateMedicalCategory(Long medicalCategoryCode, String medicalCategoryName) {
		List<MasMedicalCategory> mCatList =  new ArrayList<MasMedicalCategory>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasMedicalCategory.class);
				criteria.add(Restrictions.or(Restrictions.eq("medicalCategoryCode", medicalCategoryCode), Restrictions.eq("medicalCategoryName", medicalCategoryName)));
				mCatList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return mCatList;
	}

	@Override
	public List<MasMedicalCategory> validateMedicalCategoryUpdate(Long medicalCategoryCode, String medicalCategoryName) {
		List<MasMedicalCategory> mCatList =  new ArrayList<MasMedicalCategory>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasMedicalCategory.class);
				criteria.add(Restrictions.and(Restrictions.eq("medicalCategoryCode", medicalCategoryCode), Restrictions.eq("medicalCategoryName", medicalCategoryName)));
				mCatList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return mCatList;
	}

	@Override
	public String addMedicalCategory(MasMedicalCategory masMedicalCategory) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masMedicalCategory);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateMedicalCategoryDetails(Long medicalCategoryId, Long medicalCategoryCode, String medicalCategoryName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					
					Object object = session.load(MasMedicalCategory.class, medicalCategoryId);
					MasMedicalCategory masMedicalCategory = (MasMedicalCategory)object;
					
					Transaction transaction = session.beginTransaction();
					masMedicalCategory.setMedicalCategoryCode(medicalCategoryCode);
					masMedicalCategory.setMedicalCategoryName(medicalCategoryName.toUpperCase());			
									
					//masEmployeeCategory.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					//masMaritalStatus.setLastChgDate(date);
					session.update(masMedicalCategory);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasMedicalCategory checkMedicalCategory(Long medicalCategoryCode) {
		MasMedicalCategory mMedicalCategory = new MasMedicalCategory();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasMedicalCategory.class);		
			criteria.add(Restrictions.eq("medicalCategoryCode", medicalCategoryCode));
			mMedicalCategory = (MasMedicalCategory)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mMedicalCategory;
	}

	@Override
	public String updateMedicalCategoryStatus(Long medicalCategoryId, Long medicalCategoryCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Object object =  session.load(MasMedicalCategory.class, medicalCategoryId);
				
				MasMedicalCategory masMedicalCategory = (MasMedicalCategory)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masMedicalCategory.getStatus().equalsIgnoreCase("Y") || masMedicalCategory.getStatus().equalsIgnoreCase("y")) {
					masMedicalCategory.setStatus("N");
				}else if(masMedicalCategory.getStatus().equalsIgnoreCase("N") || masMedicalCategory.getStatus().equalsIgnoreCase("n")) {
					masMedicalCategory.setStatus("Y");
				}else {
					masMedicalCategory.setStatus("Y");
				}
				session.update(masMedicalCategory);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************MAS Blood Group*********************************************************/
	@Override
	public Map<String, List<MasBloodGroup>> getAllBloodGroup(JSONObject jsondata) {
		Map<String, List<MasBloodGroup>> map = new HashMap<String, List<MasBloodGroup>>();
		List<MasBloodGroup> bloodGroupList = new ArrayList<MasBloodGroup>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasBloodGroup.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String bgName="";
				 if (jsondata.has("bloodGroupName"))
				 {
					  bgName = jsondata.get("bloodGroupName")+"%";
					  if(jsondata.get("bloodGroupName").toString().length()>0 && !jsondata.get("bloodGroupName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("bloodGroupName", bgName));
							//criteria.addOrder(Order.asc(jsondata.get("bloodGroupName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 bloodGroupList = criteria.list();
			
			
		map.put("bloodGroupList", bloodGroupList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasBloodGroup> validateBloodGroup(String bloodGroupCode, String bloodGroupName) {
		List<MasBloodGroup> ecList =  new ArrayList<MasBloodGroup>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasBloodGroup.class);
				criteria.add(Restrictions.or(Restrictions.eq("bloodGroupCode", bloodGroupCode), Restrictions.eq("bloodGroupName", bloodGroupName)));
				ecList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return ecList;
	}

	@Override
	public List<MasBloodGroup> validateBloodGroupUpdate(String bloodGroupCode, String bloodGroupName) {
		List<MasBloodGroup> ecList =  new ArrayList<MasBloodGroup>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasBloodGroup.class);
				criteria.add(Restrictions.and(Restrictions.eq("bloodGroupCode", bloodGroupCode), Restrictions.eq("bloodGroupName", bloodGroupName)));
				ecList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return ecList;
	}

	@Override
	public String addBloodGroup(MasBloodGroup masBloodGroup) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masBloodGroup);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateBloodGroupDetails(Long bloodGroupId, String bloodGroupCode, String bloodGroupName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					Object object = session.load(MasBloodGroup.class, bloodGroupId);
					MasBloodGroup masBloodGroup = (MasBloodGroup)object;
					
					Transaction transaction = session.beginTransaction();
					masBloodGroup.setBloodGroupCode(bloodGroupCode.toUpperCase());
					masBloodGroup.setBloodGroupName(bloodGroupName.toUpperCase());			
									
					//masEmployeeCategory.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					//masMaritalStatus.setLastChgDate(date);
					session.update(masBloodGroup);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasBloodGroup checkBloodGroup(String bloodGroupCode) {
		MasBloodGroup mBloodGroup = new MasBloodGroup();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasBloodGroup.class);		
			criteria.add(Restrictions.eq("bloodGroupCode", bloodGroupCode));
			mBloodGroup = (MasBloodGroup)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mBloodGroup;
	}

	@Override
	public String updateBloodGroupStatus(Long bloodGroupId, String bloodGroupCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Object object =  session.load(MasBloodGroup.class, bloodGroupId);
				
				MasBloodGroup masBloodGroup = (MasBloodGroup)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masBloodGroup.getStatus().equalsIgnoreCase("Y") || masBloodGroup.getStatus().equalsIgnoreCase("y")) {
					masBloodGroup.setStatus("N");
				}else if(masBloodGroup.getStatus().equalsIgnoreCase("N") || masBloodGroup.getStatus().equalsIgnoreCase("n")) {
					masBloodGroup.setStatus("Y");
				}else {
					masBloodGroup.setStatus("Y");
				}
				session.update(masBloodGroup);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************MAS Sample*********************************************************/
	@Override
	public Map<String, List<MasSample>> getAllSample(JSONObject jsondata) {
		Map<String, List<MasSample>> map = new HashMap<String, List<MasSample>>();
		List<MasSample> sampleList = new ArrayList<MasSample>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasSample.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String sName="";
				 if (jsondata.has("sampleDescription"))
				 {
					  sName = jsondata.get("sampleDescription")+"%";
					  if(jsondata.get("sampleDescription").toString().length()>0 && !jsondata.get("sampleDescription").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("sampleDescription", sName));
							//criteria.addOrder(Order.asc(jsondata.get("sampleDescription").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 sampleList = criteria.list();
			
			
		map.put("sampleList", sampleList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasSample> validateSample(String sampleCode, String sampleDescription) {
		List<MasSample> ecList =  new ArrayList<MasSample>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasSample.class);
				criteria.add(Restrictions.or(Restrictions.eq("sampleCode", sampleCode), Restrictions.eq("sampleDescription", sampleDescription)));
				ecList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return ecList;
	}

	@Override
	public List<MasSample> validateSampleUpdate(String sampleCode, String sampleDescription) {
		List<MasSample> ecList =  new ArrayList<MasSample>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasSample.class);
				criteria.add(Restrictions.and(Restrictions.eq("sampleCode", sampleCode), Restrictions.eq("sampleDescription", sampleDescription)));
				ecList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return ecList;
	}

	@Override
	public String addSample(MasSample masSample) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masSample);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateSampleDetails(Long sampleId, String sampleCode, String sampleDescription) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					Object object = session.load(MasSample.class, sampleId);
					MasSample masSample = (MasSample)object;
					
					Transaction transaction = session.beginTransaction();
					masSample.setSampleCode(sampleCode.toUpperCase());
					masSample.setSampleDescription(sampleDescription.toUpperCase());			
									
					//masSample.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					//masMaritalStatus.setLastChgDate(date);
					session.update(masSample);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasSample checkSample(String sampleCode) {
		MasSample mSample = new MasSample();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasSample.class);		
			criteria.add(Restrictions.eq("sampleCode", sampleCode));
			mSample = (MasSample)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mSample;
	}

	@Override
	public String updateSampleStatus(Long sampleId, String sampleCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				
				Object object =  session.load(MasSample.class, sampleId);
				
				MasSample masSample = (MasSample)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masSample.getStatus().equalsIgnoreCase("Y") || masSample.getStatus().equalsIgnoreCase("y")) {
					masSample.setStatus("N");
				}else if(masSample.getStatus().equalsIgnoreCase("N") || masSample.getStatus().equalsIgnoreCase("n")) {
					masSample.setStatus("Y");
				}else {
					masSample.setStatus("Y");
				}
				session.update(masSample);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************MAS UOM*********************************************************/
	@Override
	public Map<String, List<MasUOM>> getAllUOM(JSONObject jsondata) {
		Map<String, List<MasUOM>> map = new HashMap<String, List<MasUOM>>();
		List<MasUOM> UOMList = new ArrayList<MasUOM>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUOM.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String uName="";
				 if (jsondata.has("UOMName"))
				 {
					  uName = jsondata.get("UOMName")+"%";
					  if(jsondata.get("UOMName").toString().length()>0 && !jsondata.get("UOMName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("UOMName", uName));
							//criteria.addOrder(Order.asc(jsondata.get("UOMName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 UOMList = criteria.list();
			
			
		map.put("UOMList", UOMList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasUOM> validateUOM(String UOMCode, String UOMName) {
		List<MasUOM> UOMList =  new ArrayList<MasUOM>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasUOM.class);
				criteria.add(Restrictions.or(Restrictions.eq("UOMCode", UOMCode), Restrictions.eq("UOMName", UOMName)));
				UOMList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return UOMList;
	}

	@Override
	public List<MasUOM> validateUOMUpdate(String UOMCode, String UOMName) {
		List<MasUOM> UOMList =  new ArrayList<MasUOM>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasUOM.class);
				criteria.add(Restrictions.and(Restrictions.eq("UOMCode", UOMCode), Restrictions.eq("UOMName", UOMName)));
				UOMList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return UOMList;
	}

	@Override
	public String addUOM(MasUOM masUOM) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masUOM);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateUOMDetails(Long UOMId, String UOMCode, String UOMName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction transaction = session.beginTransaction();		
					Object object = session.load(MasUOM.class, UOMId);
					MasUOM masUOM = (MasUOM)object;
					
					
					masUOM.setUOMCode(UOMCode.toUpperCase());
					masUOM.setUOMName(UOMName.toUpperCase());			
					System.out.println(masUOM.getUOMCode());
					System.out.println(masUOM.getUOMName());			
					//masUOM.setLastChgBy(new Long(1));				
				/*
				 * long d = System.currentTimeMillis(); Date date = new Date(d);
				 */
					//masMaritalStatus.setLastChgDate(date);
					session.update(masUOM);
					transaction.commit();				
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasUOM checkUOM(String UOMCode) {
		MasUOM mUOM = new MasUOM();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUOM.class);		
			criteria.add(Restrictions.eq("UOMCode", UOMCode));
			mUOM = (MasUOM)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mUOM;
	}

	@Override
	public String updateUOMStatus(Long UOMId, String UOMCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				
				Object object =  session.load(MasUOM.class, UOMId);
				
				MasUOM masUOM = (MasUOM)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masUOM.getUOMStatus().equalsIgnoreCase("Y") || masUOM.getUOMStatus().equalsIgnoreCase("y")) {
					masUOM.setUOMStatus("N");
				}else if(masUOM.getUOMStatus().equalsIgnoreCase("N") || masUOM.getUOMStatus().equalsIgnoreCase("n")) {
					masUOM.setUOMStatus("Y");
				}else {
					masUOM.setUOMStatus("Y");
				}
				session.update(masUOM);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/****************************MAS Item Unit*********************************************************/
	@Override
	public Map<String, List<MasStoreUnit>> getAllItemUnit(JSONObject jsondata) {
		Map<String, List<MasStoreUnit>> map = new HashMap<String, List<MasStoreUnit>>();
		List<MasStoreUnit> ItemUnitList = new ArrayList<MasStoreUnit>();
		int pageNo=1;
		int pageSize = 5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasStoreUnit.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String uName="";
				 if (jsondata.has("storeUnitName"))
				 {
					  uName = jsondata.get("storeUnitName")+"%";
					  if(jsondata.get("storeUnitName").toString().length()>0 && !jsondata.get("storeUnitName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("storeUnitName", uName));
							//criteria.addOrder(Order.asc(jsondata.get("storeUnitName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 ItemUnitList = criteria.list();
			
			
		map.put("ItemUnitList", ItemUnitList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasStoreUnit> validateItemUnit( String storeUnitName) {
		List<MasStoreUnit> itemUnitList =  new ArrayList<MasStoreUnit>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasStoreUnit.class);
				criteria.add(Restrictions.eq("storeUnitName", storeUnitName));
				itemUnitList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return itemUnitList;
	}

	@Override
	public List<MasStoreUnit> validateItemUnitUpdate( String storeUnitName) {
		List<MasStoreUnit> itemUnitList =  new ArrayList<MasStoreUnit>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasStoreUnit.class);
				criteria.add(Restrictions.eq("storeUnitName", storeUnitName));
				itemUnitList = criteria.list();
				System.out.println("itemUnitList"+itemUnitList);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return itemUnitList;
	}

	@Override
	public String addItemUnit(MasStoreUnit masStoreUnit) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masStoreUnit);
			System.out.println("masStoreUnit_DAO :: "+masStoreUnit);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateItemUnitDetails(Long storeUnitId, String storeUnitName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					
					Object object = session.load(MasStoreUnit.class, storeUnitId);
					MasStoreUnit masStoreUnit = (MasStoreUnit)object;
					
					Transaction transaction = session.beginTransaction();
					masStoreUnit.setStoreUnitName(storeUnitName.toUpperCase());			
									
					//masStoreUnit.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					//masMaritalStatus.setLastChgDate(date);
					session.update(masStoreUnit);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasStoreUnit checkItemUnit(String storeUnitName) {
		MasStoreUnit mItemUnit = new MasStoreUnit();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasStoreUnit.class);		
			criteria.add(Restrictions.eq("storeUnitName", storeUnitName));
			mItemUnit = (MasStoreUnit)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mItemUnit;
	}

	@Override
	public String updateItemUnitStatus(Long storeUnitId, String storeUnitName, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			
				Object object =  session.load(MasStoreUnit.class, storeUnitId);
				
				MasStoreUnit masStoreUnit = (MasStoreUnit)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masStoreUnit.getStatus().equalsIgnoreCase("Y") || masStoreUnit.getStatus().equalsIgnoreCase("y")) {
					masStoreUnit.setStatus("N");
				}else if(masStoreUnit.getStatus().equalsIgnoreCase("N") || masStoreUnit.getStatus().equalsIgnoreCase("n")) {
					masStoreUnit.setStatus("Y");
				}else {
					masStoreUnit.setStatus("Y");
				}
				session.update(masStoreUnit);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	/**----------------Users--------------------*/

	@Override
	public List<Users> validateUsers(String loginName, String firstName) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<Users> usersList = new ArrayList<Users>();
		Criteria criteria = session.createCriteria(Users.class);
		criteria.add(Restrictions.or(Restrictions.eq("loginName", loginName), Restrictions.eq("firstName", firstName)));
		usersList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return usersList;
	}

	@Override
	public List<Users> validateUsersUpdate(String loginName, Long hospitalId) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<Users> usersList = new ArrayList<Users>();
		Criteria criteria = session.createCriteria(Users.class);
		criteria.createAlias( "masHospitals", "mh" );
		criteria.add(Restrictions.and(Restrictions.eq("loginName", loginName), Restrictions.eq("mh.hospitalId", hospitalId)));
		usersList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return usersList;
	}

	@Override
	public String addUsers(Users users) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();	
			
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(users);
			tx.commit();
			if(savedObj!=null) {
				result = "200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public Users checkUsers(String loginName) {
		
		Users users = new Users();	
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(Users.class);
		criteria.add(Restrictions.eq("loginName", loginName));
		users = (Users)criteria.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		return users;
	}

	@Override
	public Map<String, List<Users>>  getAllUsers(JSONObject jsonObj){
		Map<String, List<Users>> mapObj = new HashMap<String, List<Users>>();
		int pageSize = 5;
		int pageNo=1;
		
		List totalMatches = new ArrayList();
		 
		List<Users> usersList = new ArrayList<Users>();
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(Users.class);				
			if( jsonObj.get("PN")!=null)
			 pageNo = Integer.parseInt(jsonObj.get("PN").toString());		
			String loginName="";
				 if (jsonObj.has("loginName"))
				 {
					  loginName = jsonObj.get("loginName")+"%";
					  if(jsonObj.get("loginName").toString().length()>0 && !jsonObj.get("loginName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("loginName", loginName));
						}
				 }	
				 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("userId").as("userId"));
			projectionList.add(Projections.property("loginName").as("loginName"));
			projectionList.add(Projections.property("firstName").as("firstName"));
			projectionList.add(Projections.property("status").as("status"));
			projectionList.add(Projections.property("masHospital").as("masHospital"));
			criteria.addOrder(Order.asc("firstName"));
			
			totalMatches = criteria.list();
			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setProjection(projectionList).setMaxResults(pageSize);
			usersList = criteria.setResultTransformer(new AliasToBeanResultTransformer(Users.class)).list();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			mapObj.put("usersList", usersList);
			mapObj.put("totalMatches", totalMatches);
			return mapObj;
		}
		
		
		


	@Override
	public List<Users> getUsers(String loginName){
		Object[] status = new Object[] {"y"};
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<Users> usersList = new ArrayList<Users>();
		Criteria criteria = session.createCriteria(Users.class);
		if(loginName.length()>0 && !loginName.trim().equalsIgnoreCase("")) {
			
			criteria.add(Restrictions.like("loginName", loginName));
		}
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("userId").as("userId"));
		projectionList.add(Projections.property("loginName").as("loginName"));
		projectionList.add(Projections.property("firstName").as("firstName"));
		projectionList.add(Projections.property("status").as("status"));
		
		criteria.setProjection(projectionList);
		
		usersList = criteria.setResultTransformer(new AliasToBeanResultTransformer(Users.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return usersList;
	}

	@Override
	public String updateUsers(Long userId, String loginName, String firstName, Long hospitalId) {	
		String result="";
		try {
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Users users =  (Users)session.get(Users.class, userId);
				
			
				
				if(users != null)
				{
				
				Transaction transaction = session.beginTransaction();
				users.setLoginName(loginName);
				users.setFirstName(firstName);
				users.setStatus("y");
				users.getMasHospital().getHospitalName();
				//Users usr = new Users();
				//usr.setUserId(new Long(1)); // userId will be fetch from session
				//masRank.setLastChgBy(new Long(1));
				MasHospital hospital = new MasHospital();
				hospital.setHospitalId(hospitalId);
				
				users.setMasHospital(hospital);
				
				long d = System.currentTimeMillis();
				Date date = new Date(d);
				
				//users.setLastChgDate(date);
				//String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				session.update(users);
				
				transaction.commit();
				
				result="200";	
				}	
				else {
					session.update("msg","UserId dose not found");
				}
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
		
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		
		return result;
	}

	@Override
	public String updateUsersStatus(Long userId,String loginName,String status) {
		//Object[] status = new Object[] {"y"};
		String result = "";
		try {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<Users> userstatusList = new ArrayList<Users>();
		Criteria criteria = session.createCriteria(Users.class,"users");
		criteria.add(Restrictions.and(Restrictions.eq("userId", userId),Restrictions.eq("loginName", loginName),Restrictions.eq("users.status", status)));
		//criteria.add(Restrictions.in("mascommand.status", status));
		userstatusList = criteria.list();
			for(int i=0;i<userstatusList.size();i++) {
				Long userId1 = userstatusList.get(i).getUserId();
				
				Object usersObject =  session.load(Users.class, userId1);
				Users users = (Users)usersObject;
				
				Transaction transaction = session.beginTransaction();
				if(users.getStatus().equalsIgnoreCase("y")){
					users.setStatus("n");
					//result="400";
				}else if(users.getStatus().equalsIgnoreCase("n")) {
					users.setStatus("y");
					//result="200";
				}else {
					users.setStatus("y");
				}
				
				session.update(users);
				transaction.commit();
				result="200";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public List<MasHospital> getHospitalList() {
		List<MasHospital> hospitalList = new ArrayList<MasHospital>();
		try {	 
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasHospital.class);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("hospitalId").as("hospitalId"));		
		projectionList.add(Projections.property("hospitalName").as("hospitalName"));
		criteria.setProjection(projectionList);
		
		hospitalList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasHospital.class)).list();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hospitalList;
	}

	/**----------------Mas MainChargecode--------------------*/

	@Override
	public List<MasMainChargecode> validateMainChargecode(String mainChargecodeCode, String mainChargecodeName) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasMainChargecode> mainChargecodeList = new ArrayList<MasMainChargecode>();
		Criteria criteria = session.createCriteria(MasMainChargecode.class);
		criteria.add(Restrictions.or(Restrictions.eq("mainChargecodeCode", mainChargecodeCode), Restrictions.eq("mainChargecodeName", mainChargecodeName)));
		mainChargecodeList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return mainChargecodeList;
	}

	@Override
	public List<MasMainChargecode> validateMainChargecodeUpdate(String mainChargecodeCode, String mainChargecodeName) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasMainChargecode> mainChargecodeList = new ArrayList<MasMainChargecode>();
		Criteria criteria = session.createCriteria(MasMainChargecode.class);
		criteria.add(Restrictions.and(Restrictions.eq("mainChargecodeCode", mainChargecodeCode), Restrictions.eq("mainChargecodeName", mainChargecodeName)));
		mainChargecodeList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return mainChargecodeList;
	}

	@Override
	public String addMainChargecode(MasMainChargecode mainChargecode) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();	
			
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(mainChargecode);
			tx.commit();
			if(savedObj!=null) {
				result = "200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasMainChargecode checkMainChargecode(String mainChargecodeCode) {
		
		MasMainChargecode mainChargecode = new MasMainChargecode();	
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasMainChargecode.class);
		criteria.add(Restrictions.eq("mainChargecodeCode", mainChargecodeCode));
		mainChargecode = (MasMainChargecode)criteria.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		return mainChargecode;
	}

	@Override
	public Map<String, List<MasMainChargecode>>  getAllMainChargecode(JSONObject jsonObj){
		Map<String, List<MasMainChargecode>> mapObj = new HashMap<String, List<MasMainChargecode>>();
		int pageSize = 5;
		int pageNo=1;
		
		List totalMatches = new ArrayList();
		 
		List<MasMainChargecode> mainChargecodeList = new ArrayList<MasMainChargecode>();
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasMainChargecode.class);				
			if( jsonObj.get("PN")!=null)
			 pageNo = Integer.parseInt(jsonObj.get("PN").toString());		
			String mainChargecodeCode="";
				 if (jsonObj.has("mainChargecodeCode"))
				 {
					 mainChargecodeCode = jsonObj.get("mainChargecodeCode")+"%";
					  if(jsonObj.get("mainChargecodeCode").toString().length()>0 && !jsonObj.get("mainChargecodeCode").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("mainChargecodeCode", mainChargecodeCode));
						}
				 }	
				 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("mainChargecodeId").as("mainChargecodeId"));
			projectionList.add(Projections.property("mainChargecodeCode").as("mainChargecodeCode"));
			projectionList.add(Projections.property("mainChargecodeName").as("mainChargecodeName"));
			projectionList.add(Projections.property("status").as("status"));
			projectionList.add(Projections.property("masDepartment").as("masDepartment"));
			criteria.addOrder(Order.asc("mainChargecodeCode"));
			
			totalMatches = criteria.list();
			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setProjection(projectionList).setMaxResults(pageSize);
			mainChargecodeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasMainChargecode.class)).list();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			mapObj.put("mainChargecodeList", mainChargecodeList);
			mapObj.put("totalMatches", totalMatches);
			return mapObj;
		}
		
		
		


	@Override
	public List<MasMainChargecode> getMainChargecode(String mainChargecodeCode){
		Object[] status = new Object[] {"y"};
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasMainChargecode> mainChargecodeList = new ArrayList<MasMainChargecode>();
		Criteria criteria = session.createCriteria(MasMainChargecode.class);
		if(mainChargecodeCode.length()>0 && !mainChargecodeCode.trim().equalsIgnoreCase("")) {
			
			criteria.add(Restrictions.like("mainChargecodeCode", mainChargecodeCode));
		}
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("mainChargecodeId").as("mainChargecodeId"));
		projectionList.add(Projections.property("MainChargecodeCode").as("mainChargecodeCode"));
		projectionList.add(Projections.property("mainChargecodeName").as("mainChargecodeName"));
		projectionList.add(Projections.property("status").as("status"));
		
		criteria.setProjection(projectionList);
		
		mainChargecodeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasMainChargecode.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return mainChargecodeList;
	}

	@Override
	public String updateMainChargecode(Long mainChargecodeId, String mainChargecodeCode, String mainChargecodeName, Long departmentId) {	
		String result="";
		try {
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			MasMainChargecode mainChargecode =  (MasMainChargecode)session.get(MasMainChargecode.class, mainChargecodeId);
				
			
				
				if(mainChargecode != null)
				{
				
				Transaction transaction = session.beginTransaction();
				mainChargecode.setMainChargecodeCode(mainChargecodeCode);
				mainChargecode.setMainChargecodeName(mainChargecodeName);
				mainChargecode.setStatus("y");
				mainChargecode.getMasDepartment().getDepartmentName();
				//Users usr = new Users();
				//usr.setUserId(new Long(1)); // userId will be fetch from session
				//masRank.setLastChgBy(new Long(1));
				MasDepartment department = new MasDepartment();
				department.setDepartmentId(departmentId);
				
				mainChargecode.setMasDepartment(department);
				
				long d = System.currentTimeMillis();
				Date date = new Date(d);
				
				//users.setLastChgDate(date);
				//String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				session.update(mainChargecode);
				
				transaction.commit();
				
				result="200";	
				}	
				else {
					session.update("msg","mainChargecodeId dose not found");
				}
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
		
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		
		return result;
	}

	@Override
	public String updateMainChargecodeStatus(Long mainChargecodeId,String mainChargecodeCode,String status) {
		//Object[] status = new Object[] {"y"};
		String result = "";
		try {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasMainChargecode> mainChargecodeStatusList = new ArrayList<MasMainChargecode>();
		Criteria criteria = session.createCriteria(MasMainChargecode.class,"mainChargecode");
		criteria.add(Restrictions.and(Restrictions.eq("mainChargecodeId", mainChargecodeId),Restrictions.eq("mainChargecodeCode", mainChargecodeCode),Restrictions.eq("mainChargecodeCode.status", status)));
		//criteria.add(Restrictions.in("mascommand.status", status));
		mainChargecodeStatusList = criteria.list();
			for(int i=0;i<mainChargecodeStatusList.size();i++) {
				Long mainChargecodeId1 = mainChargecodeStatusList.get(i).getMainChargecodeId();
				
				Object mainChargecodeCodeObject =  session.load(MasMainChargecode.class, mainChargecodeId1);
				MasMainChargecode mainChargecode = (MasMainChargecode)mainChargecodeCodeObject;
				
				Transaction transaction = session.beginTransaction();
				if(mainChargecode.getStatus().equalsIgnoreCase("y")){
					mainChargecode.setStatus("n");
					//result="400";
				}else if(mainChargecode.getStatus().equalsIgnoreCase("n")) {
					mainChargecode.setStatus("y");
					//result="200";
				}else {
					mainChargecode.setStatus("y");
				}
				
				session.update(mainChargecode);
				transaction.commit();
				result="200";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public List<MasDepartment> getDepartmentsList() {
		List<MasDepartment> departmentList = new ArrayList<MasDepartment>();
		try {	 
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasDepartment.class);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("departmentId").as("departmentId"));		
		projectionList.add(Projections.property("departmentName").as("departmentName"));
		criteria.setProjection(projectionList);
		
		departmentList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasDepartment.class)).list();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return departmentList;
	}
	
	/****************************MAS ROLE*********************************************************/
	@Override
	public Map<String, List<MasRole>> getAllRole(JSONObject jsondata) {
		Map<String, List<MasRole>> map = new HashMap<String, List<MasRole>>();
		List<MasRole> roleList = new ArrayList<MasRole>();
		int pageNo=0;
		int pageSize=5;
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasRole.class);
			
					
			if( jsondata.get("PN")!=null)
			 pageNo = Integer.parseInt(jsondata.get("PN").toString());
					
			String rName="";
				 if (jsondata.has("roleName"))
				 {
					  rName = jsondata.get("roleName")+"%";
					  if(jsondata.get("roleName").toString().length()>0 && !jsondata.get("roleName").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("roleName", rName));
							//criteria.addOrder(Order.asc(jsondata.get("roleName").toString()));
						}
				 }
				 List totalMatches = criteria.list();
				 
				 criteria.setFirstResult((pageSize) * (pageNo - 1));
				 criteria.setMaxResults(pageSize);
				 roleList = criteria.list();
			
			
		map.put("roleList", roleList);
		map.put("totalMatches", totalMatches);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return map;
	}

	@Override
	public List<MasRole> validateRole(String roleCode, String roleName) {
		List<MasRole> rList =  new ArrayList<MasRole>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasRole.class);
				criteria.add(Restrictions.or(Restrictions.eq("roleCode", roleCode), Restrictions.eq("roleName", roleName)));
				rList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return rList;
	}

	@Override
	public List<MasRole> validateRoleUpdate(String roleCode, String roleName) {
		List<MasRole> rList =  new ArrayList<MasRole>();	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Criteria criteria = session.createCriteria(MasRole.class);
				criteria.add(Restrictions.and(Restrictions.eq("roleCode", roleCode), Restrictions.eq("roleName", roleName)));
				rList = criteria.list();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getHibernateUtils.getHibernateUtlis().CloseConnection();
			}
		return rList;
	}

	@Override
	public String addRole(MasRole masRole) {
		String result="";		
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(masRole);
			tx.commit();
			if(savedObj!=null) {
				result="200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		return result;
	}

	@Override
	public String updateRoleDetails(Long roleId, String roleCode, String roleName) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
					
					Object object = session.load(MasRole.class, roleId);
					MasRole masRole = (MasRole)object;
					
					Transaction transaction = session.beginTransaction();
					masRole.setRoleCode(roleCode.toString().toUpperCase());
					masRole.setRoleName(roleName.toUpperCase());			
									
					//masEmployeeCategory.setLastChgBy(new Long(1));				
					long d = System.currentTimeMillis();
					Date date = new Date(d);
					//masMaritalStatus.setLastChgDate(date);
					session.update(masRole);
					transaction.commit();
					
					result = "200";
				//}
			//}
											
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasRole checkRole(String roleCode) {
		MasRole mRole = new MasRole();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasRole.class);		
			criteria.add(Restrictions.eq("roleCode", roleCode));
			mRole = (MasRole)criteria.uniqueResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mRole;
	}

	@Override
	public String updateRoleStatus(Long roleId, String roleCode, String status) {
		String result = "";	
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
				Object object =  session.load(MasRole.class, roleId);
				
				MasRole masRole = (MasRole)object;
				Transaction transaction = session.beginTransaction();
				
				
				if(masRole.getStatus().equalsIgnoreCase("Y") || masRole.getStatus().equalsIgnoreCase("y")) {
					masRole.setStatus("N");
				}else if(masRole.getStatus().equalsIgnoreCase("N") || masRole.getStatus().equalsIgnoreCase("n")) {
					masRole.setStatus("Y");
				}else {
					masRole.setStatus("Y");
				}
				session.update(masRole);
				transaction.commit();
				result = "200";
			//}
			
		}catch(Exception e) {
			
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}
	/******************************************RANGE************************************************/
	@Override
	public List<MasRange> validateRange(Long fromRange, Long toRange,String rangeFlag) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasRange> rangeList = new ArrayList<MasRange>();
		Criteria criteria = session.createCriteria(MasRange.class);
		criteria.add(Restrictions.and(Restrictions.eq("fromRange", fromRange), Restrictions.eq("toRange", toRange),Restrictions.eq("rangeFlag", rangeFlag)));
		rangeList = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return rangeList;
	}

	@Override
	public MasRange checkRange(Long fromRange) {
		
		MasRange range = new MasRange();	
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasRange.class);
		criteria.add(Restrictions.eq("fromRange", fromRange));
		range = (MasRange)criteria.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		
		return range;
	}
	
	@Override
	public String addRange(MasRange range) {
		String result="";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();	
			
			Transaction tx = session.beginTransaction();
			Serializable savedObj =  session.save(range);
			tx.commit();
			if(savedObj!=null) {
				result = "200";
			}else {
				result = "500";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}
	
	@Override
	public Map<String, List<MasRange>>  getAllRange(JSONObject jsonObj){
		Map<String, List<MasRange>> mapObj = new HashMap<String, List<MasRange>>();
		int pageSize = 5;
		int pageNo=1;
		
		List totalMatches = new ArrayList();
		 
		List<MasRange> rangeList = new ArrayList<MasRange>();
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasRange.class);				
			if( jsonObj.get("PN")!=null)
			 pageNo = Integer.parseInt(jsonObj.get("PN").toString());		
			String fromRange="";
				 if (jsonObj.has("fromRange"))
				 {
					 fromRange = jsonObj.get("fromRange")+"%";
					  if(jsonObj.get("fromRange").toString().length()>0 && !jsonObj.get("fromRange").toString().trim().equalsIgnoreCase("")) {
							criteria.add(Restrictions.like("fromRange", fromRange));
						}
				 }	
				 
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("masAdministrativeSex").as("masAdministrativeSex"));
			projectionList.add(Projections.property("rangeId").as("rangeId"));
			projectionList.add(Projections.property("fromRange").as("fromRange"));
			projectionList.add(Projections.property("toRange").as("toRange"));
			projectionList.add(Projections.property("status").as("status"));
			projectionList.add(Projections.property("rangeFlag").as("rangeFlag"));
			
			totalMatches = criteria.list();
			criteria.setFirstResult((pageSize) * (pageNo - 1));
			criteria.setProjection(projectionList).setMaxResults(pageSize);
			rangeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasRange.class)).list();
			getHibernateUtils.getHibernateUtlis().CloseConnection();
			mapObj.put("rangeList", rangeList);
			mapObj.put("totalMatches", totalMatches);
			return mapObj;
		}

	@Override
	public String updateRange(Long rangeId,Long fromRange, Long toRange) {	
		String result="";
		try {
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			MasRange range =  (MasRange)session.get(MasRange.class, rangeId);
				
			
				
				if(range != null)
				{
				
				Transaction transaction = session.beginTransaction();
				range.setFromRange(fromRange);
				range.setToRange(toRange);
				range.setStatus("y");
				//range.getMasAdministrativeSex().getAdministrativeSexName();
				//Users usr = new Users();
				//usr.setUserId(new Long(1)); // userId will be fetch from session
				//masRank.setLastChgBy(new Long(1));
				/*MasHospital hospital = new MasHospital();
				hospital.setHospitalId(hospitalId);
				
				users.setMasHospital(hospital);*/
				
				long d = System.currentTimeMillis();
				Date date = new Date(d);
				
				//users.setLastChgDate(date);
				//String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				session.update(range);
				
				transaction.commit();
				
				result="200";	
				}	
				else {
					session.update("msg","RangeId dose not found");
				}
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		
		
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		
		
		return result;
	}
	
	@Override
	public List<MasAdministrativeSex> getGenderList() {
		List<MasAdministrativeSex> genderList = new ArrayList<MasAdministrativeSex>();
		try {	 
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria =  session.createCriteria(MasAdministrativeSex.class);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("administrativeSexId").as("administrativeSexId"));		
		projectionList.add(Projections.property("administrativeSexName").as("administrativeSexName"));
		criteria.setProjection(projectionList);
		
		genderList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasAdministrativeSex.class)).list();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return genderList;
	}
	
	@Override
	public String updateRangeStatus(Long rangeId,Long fromRange,String status) {
		//Object[] status = new Object[] {"y"};
		String result = "";
		try {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasRange> rangeStatusList = new ArrayList<MasRange>();
		Criteria criteria = session.createCriteria(MasRange.class,"users");
		criteria.add(Restrictions.and(Restrictions.eq("rangeId", rangeId),Restrictions.eq("fromRange", fromRange),Restrictions.eq("status", status)));
		//criteria.add(Restrictions.in("mascommand.status", status));
		rangeStatusList = criteria.list();
			for(int i=0;i<rangeStatusList.size();i++) {
				Long rangeId1 = rangeStatusList.get(i).getRangeId();
				
				Object rangeObject =  session.load(MasRange.class, rangeId1);
				MasRange range = (MasRange)rangeObject;
				
				Transaction transaction = session.beginTransaction();
				if(range.getStatus().equalsIgnoreCase("y")){
					range.setStatus("n");
					//result="400";
				}else if(range.getStatus().equalsIgnoreCase("n")) {
					range.setStatus("y");
					//result="200";
				}else {
					range.setStatus("y");
				}
				
				session.update(range);
				transaction.commit();
				result="200";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}
	
	@Override
	public List<MasRange> getMasRange() {
		List<MasRange> listMasRange = new ArrayList<MasRange>();
		try {
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasRange.class);
					 /*criteria.createAlias("masRange.masAdministrativeSex", "administrativeSex");
					 criteria.add(Restrictions.eq("administrativeSex.administrativeSexId", masRange.getMasAdministrativeSex().getAdministrativeSexId()));*/
			listMasRange = criteria.list();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return listMasRange;
	}
}