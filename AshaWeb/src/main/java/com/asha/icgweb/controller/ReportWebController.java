package com.asha.icgweb.controller;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.asha.icgweb.dao.ReportDao;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.service.ReportService;
import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.Box;

@RequestMapping("/report")
@RestController
@CrossOrigin
public class ReportWebController {

	@Autowired
	ReportService reportService;
	@Autowired
	ReportDao reportDao;
	
	
	
	
	@RequestMapping(value = "/printVisitTokenReport", method = RequestMethod.POST)
	public ModelAndView printVisitTokenReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		System.out.println("json="+json);
		JSONArray sVISIT_ID= new JSONArray();
		if(json.get("visit_id") !=null)
		{
			sVISIT_ID = json.getJSONArray(("visit_id"));
		}
		int VISIT_ID= Integer.parseInt(sVISIT_ID.getString(0));
		System.out.println("VISIT_ID="+VISIT_ID);
		
		parameters.put("VISIT_ID", VISIT_ID);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		System.out.println(request.getServletContext().getRealPath("/reports/"));

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Token_Slip_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/printCaseSheet", method = RequestMethod.POST)
	public ModelAndView printCaseSheet(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		
		JSONArray sVISIT_ID= new JSONArray();
		if(json.get("visit_id") !=null)
		{
			sVISIT_ID = json.getJSONArray(("visit_id"));
		}
		int VISIT_ID= Integer.parseInt(sVISIT_ID.getString(0));
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");
		String imagePath = userHome+"/icg.png";
	    parameters.put("path", imagePath);
	    
		parameters.put("VISIT_ID", VISIT_ID);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		System.out.println(request.getServletContext().getRealPath("/reports/"));

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("opd_casesheet_new_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/printPrescriptionSlip", method = RequestMethod.POST)
	public ModelAndView printPrescriptionSlip(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		
		JSONArray sVISIT_ID= new JSONArray();
		if(json.get("visit_id") !=null)
		{
			sVISIT_ID = json.getJSONArray(("visit_id"));
		}
		int VISIT_ID= Integer.parseInt(sVISIT_ID.getString(0));
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");
		String imagePath = userHome+"/icg.png";
	    parameters.put("path", imagePath);
		
		parameters.put("VISIT_ID", VISIT_ID);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("OPD_Prescription_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	
	@RequestMapping(value = "/printInvestigationSlip", method = RequestMethod.POST)
	public ModelAndView printInvestigationSlip(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		
		JSONArray sVISIT_ID= new JSONArray();
		if(json.get("visit_id") !=null)
		{
			sVISIT_ID = json.getJSONArray(("visit_id"));
		}
		int VISIT_ID= Integer.parseInt(sVISIT_ID.getString(0));
		
		
		parameters.put("VISIT_ID", VISIT_ID);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");
		String imagePath = userHome+"/icg.png";
	    parameters.put("path", imagePath);

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Investigation_Slip_report_3", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	
	
	@RequestMapping(value = "/generateUnitReport", method = RequestMethod.POST)
	public ModelAndView generateUnitReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		System.out.println("json="+json);
		JSONArray sVISIT_ID= new JSONArray();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");
		String imagePath = userHome+"/icg.png";
	    parameters.put("path", imagePath);
		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Unit_Master _report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/printMIReport", method = RequestMethod.POST)
	public ModelAndView printMIReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		System.out.println("json="+json);
		
		JSONArray fromdate_array= new JSONArray();
		if(json.get("fromdate") !=null)
		{
			fromdate_array = json.getJSONArray(("fromdate"));
		}
		
		String fromdate=fromdate_array.getString(0);
		
		Date fd= new Date();
		try {
			fd = HMSUtil.dateFormatteryyyymmdd(fromdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parameters.put("FROM_DATE", fd);
		System.out.println("FROM_DATE"+fd);
		
		JSONArray todate_array= new JSONArray();
		if(json.get("todate") !=null)
		{
			todate_array = json.getJSONArray(("todate"));
		}
		
		String todate=todate_array.getString(0);
		Date td= new Date();
		try {
			td = HMSUtil.dateFormatteryyyymmdd(todate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TO_DATE"+td);
		parameters.put("TO_DATE", td);
		
			
		JSONArray radio_array= new JSONArray();
		if(json.get("inlineRadioOptions") !=null)
		{
			radio_array = json.getJSONArray(("inlineRadioOptions"));
		}
		
		String reportFlag=radio_array.getString(0);
		
		JSONArray disposal_array= new JSONArray();
		if(json.get("disposalId") !=null)
		{
			disposal_array = json.getJSONArray(("disposalId"));		}
		
		String disposalId=disposal_array.getString(0);
		
		
		JSONArray app_array= new JSONArray();
		if(json.get("appointmentTypeId") !=null)
		{
			app_array = json.getJSONArray(("appointmentTypeId"));		}
		
		String appointmentTypeId=app_array.getString(0);
		
		
		JSONArray dept_array= new JSONArray();
		if(json.get("department") !=null)
		{
			dept_array = json.getJSONArray(("department"));		}
		
		String departmentId=dept_array.getString(0);
		
			
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");
		String imagePath = userHome+"/icg.png";
	    parameters.put("path", imagePath);
	    
	    parameters.put("APPOINTMENT_TYPE_ID", Integer.parseInt(appointmentTypeId));
	    parameters.put("DEPARTMENT_ID", Integer.parseInt(departmentId));
	    parameters.put("ICD_ID", 0);
	    parameters.put("DISPOSAL1_ID", Integer.parseInt(disposalId));
	    
		

		connectionMap = reportDao.getConnectionForReport();
		if(reportFlag.equalsIgnoreCase("D"))
		HMSUtil.generateReport("MI_Appointment_detailed_report", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		if(reportFlag.equalsIgnoreCase("C"))
			HMSUtil.generateReport("MI_Appointment_count_report", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/generateRegionMasterReport", method = RequestMethod.POST)
	public ModelAndView generateRegionMasterReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	 
	    parameters.put("path", imagePath);
		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Command_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateAppointmentTypeReport", method = RequestMethod.POST)
	public ModelAndView generateAppointmentTypeReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	 
	    parameters.put("path", imagePath);
		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Appointment_Type_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/generateDepartmentReport", method = RequestMethod.POST)
	public ModelAndView generateDepartmentReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);
		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Department Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateDisposalReport", method = RequestMethod.POST)
	public ModelAndView generateDisposalReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);
		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Disposal_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateEmpanelledHospitalReport", method = RequestMethod.POST)
	public ModelAndView generateEmpanelledHospitalReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);
		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Empanelled Hospital Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateFrequencyReport", method = RequestMethod.POST)
	public ModelAndView generateFrequencyReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);
		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Frequency_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateHospitalReport", method = RequestMethod.POST)
	public ModelAndView generateHospitalReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Hospital_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/generateIdealWeightReport", method = RequestMethod.POST)
	public ModelAndView generateIdealWeightReport(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		
	    System.out.println("path="+imagePath);
		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Ideal_Weight_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	@RequestMapping(value = "/generateRelationReport", method = RequestMethod.POST)
	public ModelAndView generateRelationReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Relation_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	/*06052019*/
	@RequestMapping(value = "/generateBloodGroupReport", method = RequestMethod.POST)
	public ModelAndView generateBloodGroupReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Blood_Group_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateEmployeeCategoryReport", method = RequestMethod.POST)
	public ModelAndView generateEmployeeCategoryReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Employee_Category_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateGenderReport", method = RequestMethod.POST)
	public ModelAndView generateGenderReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Gender_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateItemUnitReport", method = RequestMethod.POST)
	public ModelAndView generateItemUnitReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Item_Unit_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateMaritalStatusReport", method = RequestMethod.POST)
	public ModelAndView generateMaritalStatusReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("MaritalStatus_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateMedicalCategoryReport", method = RequestMethod.POST)
	public ModelAndView generateMedicalCategoryReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Medical_Category_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generatePhysiothreapyReport", method = RequestMethod.POST)
	public ModelAndView generatePhysiothreapyReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Physiotherapy_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateRangeMasterReport", method = RequestMethod.POST)
	public ModelAndView generateRangeMasterReport(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Range_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateRankMasterReport", method = RequestMethod.POST)
	public ModelAndView generateRankMasterReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Rank_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateReligionMasterReport", method = RequestMethod.POST)
	public ModelAndView generateReligionMasterReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Religion_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateRoleMasterReport", method = RequestMethod.POST)
	public ModelAndView generateRoleMasterReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Role_Master _report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateSampleContainerReport", method = RequestMethod.POST)
	public ModelAndView generateSampleContainerReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Sample_Container_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateServiceTypeReport", method = RequestMethod.POST)
	public ModelAndView generateServiceTypeReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Service_Type_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateTradeMasterReport", method = RequestMethod.POST)
	public ModelAndView generateTradeMasterReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Trade_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateUOMReport", method = RequestMethod.POST)
	public ModelAndView generateUOMReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Unit_of_Measurement_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	@RequestMapping(value = "/generateUsersReport", method = RequestMethod.POST)
	public ModelAndView generateUsersReport(@RequestBody String requestData,HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");		
	    String imagePath = userHome+"/icg.png";	
	    parameters.put("path", imagePath);		

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("Users_Master_report_1", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	
	
	@RequestMapping(value = "/generateEHRReport", method = RequestMethod.POST)
	public ModelAndView generateEHRReport(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ass");
		Map<String, Object> connectionMap = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		Box box= HMSUtil.getBox(request);
		JSONObject json = new JSONObject(box);
		
		JSONArray sPatientId= new JSONArray();
		if(json.get("patientId") !=null)
		{
			sPatientId = json.getJSONArray(("patientId"));
		}
		int nPatientId= Integer.parseInt(sPatientId.getString(0));
		
		JSONArray sUserId= new JSONArray();
		if(json.get("userId") !=null)
		{
			sUserId = json.getJSONArray(("userId"));
		}
		int nUserId= Integer.parseInt(sUserId.getString(0));
		
		
		parameters.put("P_USERID", nUserId);
		parameters.put("P_PATIENTID", nPatientId);
		parameters.put("SUBREPORT_DIR", request.getServletContext().getRealPath("/reports/"));
		
		
		String userHome = request.getServletContext().getRealPath("/resources/images/");
		String imagePath = userHome+"/icg.png";
	    parameters.put("path", imagePath);

		connectionMap = reportDao.getConnectionForReport();
		
		HMSUtil.generateReport("EHR_report", parameters, (Connection)connectionMap.get("conn"), response, request.getSession().getServletContext());
		return null;
	
	}
	  
}



