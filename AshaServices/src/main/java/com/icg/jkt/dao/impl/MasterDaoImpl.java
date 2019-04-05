package com.icg.jkt.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.icg.jkt.hibernateutils.GetHibernateUtils;
import com.icg.jkt.utils.HMSUtil;

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
	public MasTrade checkTrade(String tradeName) {
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria = session.createCriteria(MasTrade.class);
		// criteria.add(Restrictions.eq("status", "y"));
		MasTrade masTradeObj = (MasTrade) criteria.setMaxResults(1).uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return masTradeObj;
	}

	@Override
	public List<MasTrade> getTradeDetails(String tradeName) {
		Object[] statusObj = new Object[] { "t", "n", "y" };
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		List<MasTrade> masTradeList = new ArrayList<MasTrade>();
		Criteria criteria = session.createCriteria(MasTrade.class, "mastrade");
		criteria.add(Restrictions.in("mastrade.status", statusObj));

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
	public Map<String, Object> getTradeList() {
		Map<String, Object> map = new HashMap<String, Object>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Object[] statusObj = new Object[] { "t", "n", "y" };
		List<MasTrade> masTradeList = new ArrayList<MasTrade>();
		Criteria criteria = session.createCriteria(MasTrade.class, "mastrade");

		criteria.add(Restrictions.in("mastrade.status", statusObj));

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("tradeId").as("tradeId"));
		projectionList.add(Projections.property("tradeName").as("tradeName"));
		projectionList.add(Projections.property("status").as("status"));

		criteria.setProjection(projectionList);
		masTradeList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasTrade.class)).list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		map.put("masTradeList", masTradeList);
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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());

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
	public List<MasTrade> validateMasTrade(String tradeName) {
		List<MasTrade> masTrade = new ArrayList<MasTrade>();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria = session.createCriteria(MasTrade.class);
		criteria.add(Restrictions.eq("tradeName", tradeName));
		masTrade = criteria.list();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return masTrade;
	}

	@Override
	public String addTradeDetails(MasTrade masTrade) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj = session.save(masTrade);
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

	/***************************************
	 * MAS UOM
	 *************************************************************/
	@Override
	public List<MasUOM> validateMasUOM(String UOMCode, String UOMName) {
		List<MasUOM> masUnitList = new ArrayList<MasUOM>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUOM.class);
			criteria.add(Restrictions.or(Restrictions.eq("UOMCode", UOMCode), Restrictions.eq("UOMName", UOMName)));
			masUnitList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return masUnitList;
	}

	@Override
	public String addMasUOM(MasUOM masUOM) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Transaction tx = session.beginTransaction();
			Serializable savedObj = session.save(masUOM);
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
	public Map<String, List<MasUOM>> getAllUOM(JSONObject jsonObject) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());
		
		List totalMatches = new ArrayList();
		List<MasUOM> unitList = new ArrayList<MasUOM>();
		Map<String, List<MasUOM>> mapObj = new HashMap<String, List<MasUOM>>();
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			Criteria criteria = session.createCriteria(MasUOM.class);
			if (jsonObject.get("PN") != null)
				pageNo = Integer.parseInt(jsonObject.get("PN").toString());

			String uName = "";

			if (jsonObject.has("UOMName")) {
				uName = jsonObject.get("UOMName") + "%";
				if (jsonObject.get("UOMName").toString().length() > 0
						&& !jsonObject.get("UOMName").toString().trim().equalsIgnoreCase("")) {
					criteria.add(Restrictions.like("UOMName", uName));
				}
			}

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("UOMId").as("UOMId"));
			projectionList.add(Projections.property("UOMCode").as("UOMCode"));
			projectionList.add(Projections.property("UOMName").as("UOMName"));
			projectionList.add(Projections.property("UOMStatus").as("UOMStatus"));
			projectionList.add(Projections.property("UOMStatus").as("UOMStatus"));

			totalMatches = criteria.list();

			criteria.setFirstResult((pageSize) * (pageNo - 1));

			criteria.setProjection(projectionList).setMaxResults(pageSize);
			unitList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasUOM.class)).list();

			mapObj.put("totalMatches", totalMatches);
			mapObj.put("unitList", unitList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return mapObj;
	}

	@Override
	public String updateUOMDetails(Long UOMId, String UOMCode, String UOMName) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object uomObject = session.load(MasUOM.class, UOMId);
			MasUOM masUnit = (MasUOM) uomObject;

			Transaction transaction = session.beginTransaction();
			masUnit.setUOMCode(UOMCode.toUpperCase());
			masUnit.setUOMName(UOMName.toUpperCase());

			Long lastChgBy = new Long(1);
			String uomStatus = "y";
			masUnit.setLastChgBy(lastChgBy);
			masUnit.setUOMStatus(uomStatus.toUpperCase());

			long d = System.currentTimeMillis();
			Date date = new Date(d);
			String lastChgTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

			masUnit.setLastChgDate(date);
			session.update(masUnit);
			transaction.commit();
			result = "200";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}
		return result;
	}

	@Override
	public MasUOM chkMasUnit(String UOMCode) {
		MasUOM masUnit = new MasUOM();
		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		Criteria criteria = session.createCriteria(MasUOM.class);
		criteria.add(Restrictions.eq("UOMCode", UOMCode));
		masUnit = (MasUOM) criteria.uniqueResult();
		getHibernateUtils.getHibernateUtlis().CloseConnection();
		return masUnit;
	}

	@Override
	public String updateUOMStatus(Long UOMId, String UOMCode, String UOMStatus) {
		String result = "";
		try {
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();

			Object uomObject = session.load(MasUOM.class, UOMId);
			MasUOM masUnit = (MasUOM) uomObject;

			Transaction transaction = session.beginTransaction();
			if (masUnit.getUOMStatus().equalsIgnoreCase("y")) {
				masUnit.setUOMStatus("n".toUpperCase());

			} else if (masUnit.getUOMStatus().equalsIgnoreCase("n")) {
				masUnit.setUOMStatus("y".toUpperCase());

			} else {
				masUnit.setUOMStatus("y".toUpperCase());
			}

			session.update(masUnit);
			transaction.commit();
			result = "200";
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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());

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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());

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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());

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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());

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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());

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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());
		
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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());
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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());

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
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());
		
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

	/***********************************************/
	@Override
	public Map<String, List<MasNursingCare>> getAllmNursingData(JSONObject jsonObj) {
		int pageSize = Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageSize").trim());
		int pageNo= Integer.parseInt(HMSUtil.getProperties("adt.properties", "pageNo").trim());

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
			projectionList.add(Projections.property("fromAge").as("fromAge"));
			projectionList.add(Projections.property("toAge").as("toAge"));
			criteria.setProjection(projectionList);

			weightList = criteria.setResultTransformer(new AliasToBeanResultTransformer(MasIdealWeight.class)).list();
			Iterator iterator = weightList.iterator();
			while (iterator.hasNext()) {
				MasIdealWeight object = (MasIdealWeight) iterator.next();
				Map<Object, Object> map = new HashMap<Object, Object>();
				long idealWeightId = object.getIdealWeightId();
				String fromAge = object.getFromAge();
				String toAge = object.getToAge();
				String age = fromAge.concat("-").concat(toAge);
				map.put("age", age);
				map.put("idealWeightId", idealWeightId);
				System.out.println(age);
				listObject.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getHibernateUtils.getHibernateUtlis().CloseConnection();
		}

		return listObject;
	}

}