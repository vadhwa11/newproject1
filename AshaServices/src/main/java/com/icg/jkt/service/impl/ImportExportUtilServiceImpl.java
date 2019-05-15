package com.icg.jkt.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.icg.jkt.dao.ImportExportUtilDao;
import com.icg.jkt.entity.MasIcd;
import com.icg.jkt.entity.SyncTable;
import com.icg.jkt.service.ImportExportUtilService;
import com.icg.jkt.utils.HMSUtil;

@Service("ImportExportUtilService")
public class ImportExportUtilServiceImpl implements ImportExportUtilService {
	
	@Autowired
	ImportExportUtilDao ieuDao;
	
	@Override
	public String getExportSyncTableList(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObj = new JSONObject();	
		List<Map<String, Object>> respList = new ArrayList<Map<String,Object>>();
		List<SyncTable> exportTablist = ieuDao.getExportSyncTableList();
		if (exportTablist != null && exportTablist.size() > 0) {
			
			for (SyncTable sync:exportTablist)
			{
				Timestamp lcd=sync.getLastChgDate() ;
				Calendar lCal = Calendar.getInstance();
				if(lcd !=null) {
				    lCal.setTime(lcd);
				}
				    int yr=lCal.get(Calendar.YEAR);
				    int mn=lCal.get(Calendar.MONTH) + 1;
				    int dt=lCal.get(Calendar.DATE);
				    int hh=lCal.get(Calendar.HOUR);
				    int mm=lCal.get(Calendar.MINUTE);
				    LocalDate syncDate = LocalDate.of(yr,mn,dt) ; 
				    LocalTime localTime=LocalTime.of(hh, mm);				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("id", sync.getSyncTableId());
				map.put("tableName", sync.getTableName());				
				map.put("syncDate", (sync.getLastChgDate() !=null ? syncDate+" "+localTime :" "));				
				map.put("fileName", sync.getSyncFilename());
				map.put("orderNo", sync.getOrderNo());
				jsonObj.put("count", exportTablist.size());
				respList.add(map);
			}
			jsonObj.put("data", respList);
			
		} else {
			jsonObj.put("data", exportTablist);
			jsonObj.put("count", 0);
			jsonObj.put("msg", "No Record Found");
			
		}
		System.out.println(jsonObj.toString());
		return jsonObj.toString();
		
	}

	@Override
	public String genrateCSV(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		String status="";
		status=ieuDao.genrateCSV();
		return status;
	}
	
	@Override
	public String importCSV(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		String status="";
		status=ieuDao.importCSV();
		return status;
		}
	
	
	@Override
	public List<MasIcd> getICDNameSearch(JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {	
		
		List<MasIcd> result = new ArrayList<MasIcd>();
		
		List<MasIcd> icdnamelist = ieuDao.getICDNameSearch(jsonObject);

		if (icdnamelist != null && icdnamelist.size() > 0) {

			for (MasIcd icd : icdnamelist) {
				if (icd.getIcdName().contains(jsonObject.get("icdName").toString())) {
					if (icd.getIcdId() != null && icd.getIcdName() != null) {
						//result.add(new MasIcd(icd.getIcdId(), icd.getIcdName()));
					}
				}

			}

		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getImportSyncTableList(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		List<Map<String,Object>> dataList = new ArrayList<>();
		Map<String,Object> map = ieuDao.getImportSyncTableList();
		if(map != null && !map.isEmpty()) {
			List<SyncTable> syncTable = (List<SyncTable>) map.get("list");
			for(SyncTable st : syncTable) {
				Map<String,Object> data = new HashMap<>();
				data.put("id", HMSUtil.convertNullToEmptyString(st.getSyncTableId()));
				data.put("table_name", HMSUtil.convertNullToEmptyString(st.getTableName()));
				data.put("sync_data", HMSUtil.convertNullToEmptyString(st.getLastChgDate()));
				data.put("file_name", HMSUtil.convertNullToEmptyString(st.getSyncFilename()));
				data.put("order_no", HMSUtil.convertNullToEmptyString(st.getOrderNo()));
				dataList.add(data);				
			}
			obj.put("import_list", dataList);
		}else {
			obj.put("import_list", new JSONArray());
		}
		
		return obj.toString();
	}
}