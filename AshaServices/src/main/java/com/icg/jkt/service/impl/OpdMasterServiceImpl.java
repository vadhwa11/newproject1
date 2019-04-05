package com.icg.jkt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.icg.jkt.entity.MasStoreItem;
import com.icg.jkt.entity.OpdTemplate;
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
			    MasEmployee checkEmp = md.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
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
			    MasEmployee checkEmp = md.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
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
			    MasEmployee checkEmp = md.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
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
			    MasEmployee checkEmp = md.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
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
			    MasEmployee checkEmp = md.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
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
			    MasEmployee checkEmp = md.checkEmp(Long.parseLong(jsondata.get("employeeId").toString()));
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

	
}