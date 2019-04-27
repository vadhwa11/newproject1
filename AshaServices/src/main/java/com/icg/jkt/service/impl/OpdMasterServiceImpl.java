package com.icg.jkt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icg.jkt.dao.MasterDao;
import com.icg.jkt.dao.OpdMasterDao;
import com.icg.jkt.entity.DgMasInvestigation;
import com.icg.jkt.entity.MasDepartment;
import com.icg.jkt.entity.MasDisposal;
import com.icg.jkt.entity.MasEmpanelledHospital;
import com.icg.jkt.entity.MasEmployee;
import com.icg.jkt.entity.MasFrequency;
import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.entity.MasNursingCare;
import com.icg.jkt.entity.MasStoreItem;
import com.icg.jkt.entity.OpdTemplate;
import com.icg.jkt.entity.OpdTemplateInvestigation;
import com.icg.jkt.entity.Users;
import com.icg.jkt.entity.Visit;
import com.icg.jkt.service.MasterService;
import com.icg.jkt.service.OpdMasterService;

@Repository
public class OpdMasterServiceImpl implements OpdMasterService{

	@Autowired
	OpdMasterDao md;
	
	/////////////////////// MasDepartmrent get Method /////////////////////////
	@Override
	public String departmentList(HashMap<String, String> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
        try
        {
		if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals(""))
		{
			return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID as a  key or value or it is null\"}";
		} 
		else
		{
			   Users checkEmp = md.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
			    if(checkEmp!=null)
			    {
				List<MasDepartment> mst_depart = md.getDepartmentList();
			    if (mst_depart.size() == 0)
			    {
			    	 return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			    } 
			    else 
			    {
			    	json.put("departmentList", checkEmp);
			    	json.put("departmentList", mst_depart);
			    	json.put("msg", "department List  get  sucessfull... ");
			    	json.put("status", "1");

			   }

		}
	   else
	   {
		   return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";
    	
		}

		return json.toString();
	}
        }finally
        {
        	System.out.println("Exception Occured");
        }
	}
	
	//////////////////////// Get Master ICD Business Logic //////////////////////

	@Override
	public String getICD(HashMap<String, String> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
        try
        {
		if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals(""))
		{
			return "{\"status\":\"0\",\"msg\":\"json is not contain employeeId as a  key or value or it is null\"}";
		} 
		else
		{
				Users checkEmp = md.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
			    if(checkEmp!=null)
			    {
				List<MasIcd> mst_icd = md.getIcd();
			    if (mst_icd.size() == 0)
			    {
			    	 return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			    } 
			    else 
			    {
			    	json.put("ICDList", checkEmp);
			    	json.put("ICDList", mst_icd);
			    	json.put("msg", "ICD List  get  sucessfull... ");
			    	json.put("status", "1");

			   }

		}
	   else
	   {
		   return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";
    	
		}

		return json.toString();
	}
        }finally
        {
        	System.out.println("Exception Occured");
        }
	}

	@Override
	public String getInvestigation(HashMap<String, String> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
        try
        {
		if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals(""))
		{
			return "{\"status\":\"0\",\"msg\":\"json is not contain employeeId as a  key or value or it is null\"}";
		} 
		else
		{
				Users checkEmp = md.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
			    if(checkEmp!=null)
			    {
				List<DgMasInvestigation> mst_investigation = md.getInvestigationList(Long.parseLong(jsondata.get("mainChargeCode").toString()));
			    if (mst_investigation.size() == 0)
			    {
			    	 return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			    } 
			    else 
			    {
			    	json.put("InvestigationList", checkEmp);
			    	json.put("InvestigationList", mst_investigation);
			    	json.put("msg", "Investigation List  get  sucessfull... ");
			    	json.put("status", "1");
			    	json.put("size",mst_investigation.size());

			   }

		}
	   else
	   {
		   return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";
    	
		}

		
	}
        }
        catch(Exception e)	{
		e.printStackTrace();
	     }
        return json.toString();
        
        
	}

	@Override
	public String getMasStoreItem(HashMap<String, String> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
        try
        {
		if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals(""))
		{
			return "{\"status\":\"0\",\"msg\":\"json is not contain employeeId as a  key or value or it is null\"}";
		} 
		else
		{
				Users checkEmp = md.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
			    if(checkEmp!=null)
			    {
				List<MasStoreItem> mst_store = md.getMasStoreItem();
				
			    if (mst_store.size() == 0)
			    {
			    	 return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			    } 
			    else 
			    {
			    	List<HashMap<String, Object>> mst_storeItem = new ArrayList<HashMap<String, Object>>();
			    	
			    	for (MasStoreItem msi : mst_store) {
			    		
			    		HashMap<String, Object> mst = new HashMap<String, Object>();
			    		
			    		mst.put("itemId", msi.getItemId());
			    		mst.put("pvmsNo", msi.getPvmsNo());
			    		mst.put("nomenclature", msi.getNomenclature());
			    		if(msi.getMasStoreUnit()!=null && StringUtils.isNotBlank(msi.getMasStoreUnit().getStoreUnitName()))
			    		mst.put("dispUnit",msi.getMasStoreUnit().getStoreUnitName());
			    		
			    		mst_storeItem.add(mst);
				    	json.put("MasStoreItemList", checkEmp);
				    	json.put("MasStoreItemList", mst_storeItem);
				    	json.put("Size", mst_store.size());
				    	json.put("msg", "MasStoreItemList  get  sucessfull... ");
				    	json.put("status", "1");
			    	}
			    }
			    }
	   else
	   {
		   return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";
    	
		}

		
	}
        }
        catch(Exception e)	{
    		e.printStackTrace();
	     }
        return json.toString();
	}

	@Override
	public String getMasFrequency(HashMap<String, String> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
        try
        {
		if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals(""))
		{
			return "{\"status\":\"0\",\"msg\":\"json is not contain employeeId as a  key or value or it is null\"}";
		} 
		else
		{
				Users checkEmp = md.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
			    if(checkEmp!=null)
			    {
				List<MasFrequency> mas_frequency = md.getMasFrequency();
			    if (mas_frequency.size() == 0)
			    {
			    	 return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			    } 
			    else 
			    {
			    	json.put("MasFrequency", checkEmp);
			    	json.put("MasFrequencyList", mas_frequency);
			    	json.put("msg", "MasFrequencyList  get  sucessfull... ");
			    	json.put("status", "1");

			   }

		}
	   else
	   {
		   return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";
    	
		}
	}
        }
        catch(Exception e)	{
    		e.printStackTrace();
	     }
        return json.toString();
	}

	@Override
	public String getTemplateName(HashMap<String, String> jsondata,HttpServletRequest request) {
		
		JSONObject json = new JSONObject();
		List<OpdTemplate> opdTemplates = md.getTemplateName();
	    json.put("template", opdTemplates);
		
		return json.toString();
	}

	@Override
	public String getEmpanelledHospital(HashMap<String, String> jsondata, HttpServletRequest request) {
		
		JSONObject json = new JSONObject();
        try
        {
		if (jsondata.get("employeeId") == null || jsondata.get("employeeId").toString().trim().equals(""))
		{
			return "{\"status\":\"0\",\"msg\":\"json is not contain employeeId as a  key or value or it is null\"}";
		} 
		else
		{
				Users checkEmp = md.checkUser(Long.parseLong(jsondata.get("employeeId").toString()));
			    if(checkEmp!=null)
			    {
				List<MasEmpanelledHospital> mas_empaneHospital = md.getEmpanelledHospital();
			    if (mas_empaneHospital.size() == 0)
			    {
			    	 return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			    } 
			    else 
			    {
			    	json.put("masEmpanelledHospital", checkEmp);
			    	json.put("masEmpanelledHospital", mas_empaneHospital);
			    	json.put("msg", "MasFrequencyList  get  sucessfull... ");
			    	json.put("status", "1");

			   }

		}
	   else
	   {
		   return "{\"status\":\"0\",\"msg\":\"json is not contain EMPLOYEE_ID Not Found\"}";
    	
		}
	}
        }
        catch(Exception e)	{
    		e.printStackTrace();
	     }
        return json.toString();
	}

	@Override
	public String getDisposalList(HashMap<String, String> jsondata,HttpServletRequest request) {
		     JSONObject json = new JSONObject();
       			List<MasDisposal> mas_disposal= md.getMasDisposal();
			    if (mas_disposal.size() == 0)
			    {
			    	 return "{\"status\":\"0\",\"msg\":\"Data not found\"}";
			    } 
			    else 
			    {
			    	
			    	json.put("MasDisposal", mas_disposal);
			    	json.put("msg", "MasDisposal  get  sucessfull... ");
			    	json.put("status", "1");

			   }

	
        return json.toString();
	}

	@Override
	public String getMasNursingCare(HashMap<String, Object> jsondata, HttpServletRequest request)  {
		 JSONObject json = new JSONObject();
			if (jsondata.get("nursingType") == null || jsondata.get("nursingType").toString().trim().equals("")) {
				return "{\"status\":\"0\",\"msg\":\"json is not contain Nursing Type as a  key or value or it is null\"}";
				
			}
					
			else {
				
				
				Map<String,Object> map = md.getMasNursingCare(jsondata);
				List<MasNursingCare> getInvestigationData = (List<MasNursingCare>) map.get("list");
				List<HashMap<String, Object>> mc = new ArrayList<HashMap<String, Object>>();
				try
				{
					
				for (MasNursingCare masNur : getInvestigationData) {
					HashMap<String, Object> mn = new HashMap<String, Object>();
					mn.put("nursingCode", masNur.getNursingCode());
					mn.put("nursingId",masNur.getNursingId());
					mn.put("nursingType",masNur.getNursingType());
					mn.put("nursingName", masNur.getNursingName());
					
					mc.add(mn);
					
			}
				if(mc != null && mc.size()>0){
					json.put("data", mc);
					json.put("count", mc.size());
					json.put("msg", "Visit List  get  sucessfull... ");
					json.put("status", "1");
				}else{
					return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
				}
				}
				catch(Exception e)
				{
				  System.out.println(e);
				}

				return json.toString();
			}
		
	}

	@Override
	public String getTemplateInvestData(HashMap<String, Object> jsondata, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		
		if (jsondata.get("templateId") == null || jsondata.get("templateId").toString().trim().equals("")) {
			return "{\"status\":\"0\",\"msg\":\"json is not contain Template ID as a  key or value or it is null\"}";
			
		}
				
		else {
			
			
			Map<String,Object> map = md.getTemplateInvestigation(jsondata);
			List<OpdTemplateInvestigation> getInvestigationData = (List<OpdTemplateInvestigation>) map.get("list");
			List<HashMap<String, Object>> c = new ArrayList<HashMap<String, Object>>();
			try
			{
				
			for (OpdTemplateInvestigation tempInve : getInvestigationData) {
				HashMap<String, Object> ti = new HashMap<String, Object>();
				ti.put("templateName", tempInve.getOpdTemplate().getTemplateCode());
				ti.put("templateCode",tempInve.getOpdTemplate().getTemplateName());
				ti.put("templateInvestgationId",tempInve.getInvestigationId());
				ti.put("investigationName", tempInve.getDgMasInvestigation().getInvestigationName());
				
				c.add(ti);
				
		}
			if(c != null && c.size()>0){
				json.put("data", c);
				json.put("count", c.size());
				json.put("msg", "Visit List  get  sucessfull... ");
				json.put("status", "1");
			}else{
				return "{\"status\":\"0\",\"msg\":\"Pending Status Not Found\"}";
			}
			}
			catch(Exception e)
			{
			  System.out.println(e);
			}

			return json.toString();
		}
	}
}
