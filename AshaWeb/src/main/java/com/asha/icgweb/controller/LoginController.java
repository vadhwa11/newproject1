package com.asha.icgweb.controller;

import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.OutputStream;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLActionHandler;
import org.eclipse.birt.report.engine.api.HTMLImageHandler;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IImage;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.asha.icgweb.entity.Registration;
import com.asha.icgweb.model.LoginModel;
import com.asha.icgweb.service.LoginService;
import com.asha.icgweb.utils.HMSUtil;
import com.asha.icgweb.utils.RestUtils;
import org.apache.commons.codec.binary.Base64;

@RequestMapping("/dashboard")
@RestController
@CrossOrigin
public class LoginController {
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView masterModules(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("dashboard");
		return mav;

	}

	@RequestMapping(value = "/empRegistration", method = RequestMethod.POST)
	public String empRegistration(@RequestBody HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) {
		return "hello jkt";
		// return ls.empRegistration(payload, request, response);
	}

	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	// HashMap<String,String> listMap = new HashMap<String,String>();
	public String registration(@ModelAttribute LoginModel payload, HttpServletRequest request,
			HttpServletResponse response) {

		return loginService.registration(payload, request, response);
	}

	@RequestMapping(value = "/getEmpList", method = RequestMethod.GET)
	public String getRegistration(HttpServletRequest request, HttpServletResponse response) {
		return loginService.getRegistration(request, response);
	}

	@RequestMapping(value = "/manageRegistration", method = RequestMethod.GET)
	public ModelAndView managemerchant(HttpServletRequest httpServletRequest) {

		String resp = null;
		List<Registration> registrationlist = loginService.getallRegistration(httpServletRequest);

		ModelAndView modelAndView = new ModelAndView("managemerchant");
		modelAndView.addObject("registationlistobject", registrationlist);

		return modelAndView;
	}

	/* Added By Kaushal Mishra */

	@RequestMapping(value = "/executeDbProcedure", method = RequestMethod.POST)
	public String executeDbProcedure(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {

		String resp = loginService.executeDbProcedure(payload, request, response);

		return resp;
	}

	@RequestMapping(value = "/openBirtReport", method = RequestMethod.POST)
	public ModelAndView openBirtReport(@RequestBody HashMap<String, Object> payload, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		synchronized (this) {

			HttpSession session = request.getSession();
			EngineConfig ec = null;
			IReportEngine engine = null;
			String fileName = "";
			int userHospitalId = 0;
			int userId = 0;
			int hospitalId=0;
			String fromDate=""; String toDate=""; 
			 

			if (payload.get("hospitalId") != null)
				hospitalId = Integer.parseInt(payload.get("hospitalId").toString());
			
			
			  if (payload.get("fromDate") != null && !payload.get("fromDate").equals("") )
			  fromDate = (String)payload.get("fromDate");
			  
			  Date fromDate1=HMSUtil.convertStringTypeDateToDateType(fromDate); 
			 
			  if (payload.get("toDate") != null && !payload.get("toDate").equals(""))
			  toDate = (String) payload.get("toDate");
			  
			  Date toDate1=HMSUtil.convertStringTypeDateToDateType(toDate); 
			
			if (session.getAttribute("hospital_id") != null)
				userHospitalId = (Integer) session.getAttribute("hospital_id");

			
			  if(session.getAttribute("user_id")!=null)
			  userId=(Integer)session.getAttribute("user_id");
			 

			if (payload.get("fileName") != null)
				fileName = (String) payload.get("fileName");

			if (!fileName.trim().equalsIgnoreCase(""))
				fileName += ".rptdesign";

			try {

				RegistryProviderFactory.releaseDefault();
				ec = new EngineConfig();
				Platform.startup(ec);
				IReportEngineFactory factory = (IReportEngineFactory) Platform
						.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
				engine = factory.createReportEngine(ec);
				IReportRunnable DESIGN = engine
						.openReportDesign(request.getServletContext().getRealPath("/reports/" + fileName));
				IRunAndRenderTask TASK = engine.createRunAndRenderTask(DESIGN);

				Map<String, Object> param = new HashMap<String, Object>();
				FileReader reader = new FileReader(
						request.getServletContext().getRealPath("") + "/WEB-INF/classes/application.properties");
				
				System.out.println(request.getServletContext().getRealPath(""));
				System.out.println(request.getServletContext().getRealPath("resources"));
				Properties prop = new Properties();
				prop.load(reader);
				param.put("url", prop.get("jdbc.url"));
				
				param.put("driver", prop.get("jdbc.driverClassName"));
				param.put("user", prop.get("jdbc.username"));
				param.put("password", prop.get("jdbc.password"));
				param.put("userHospitalId", userHospitalId);
				param.put("hospitalId", hospitalId);
				param.put("userId", userId);
				param.put("fromDate", fromDate1);
				param.put("toDate", toDate1);
				
				 System.out.println("url : " + prop.get("jdbc.url"));
				 System.out.println("driver : " + prop.get("jdbc.driverClassName"));
				 System.out.println("user: " + prop.get("jdbc.username"));
				 
				System.out.println("fromDate : " + fromDate);
				System.out.println("toDate : "+ toDate);
					 
				 

				TASK.setParameterValues(param);
				HTMLRenderOption HTML_OPTIONS = new HTMLRenderOption();
				HTML_OPTIONS.setActionHandler(new HTMLActionHandler());
				HTML_OPTIONS.setImageHandler(new HTMLImageHandler() {
					public String onDesignImage(IImage image, Object context) {
						return handleImage(image, context, "design", true);
					}

					public String onDocImage(IImage image, Object context) {
						return null;
					}

					public String onURLImage(IImage image, Object context) {
						assert (image != null);
						String uri = image.getID();
						if (uri.startsWith("http:") || uri.startsWith("https:")) {
							return uri;
						}
						return handleImage(image, context, "uri", true);
					}

					public String onCustomImage(IImage image, Object context) {
						return handleImage(image, context, "custom", false);
					}

					public String onFileImage(IImage image, Object context) {
						return handleImage(image, context, "file", true);
					}

					protected String handleImage(IImage image, Object context, String prefix, boolean needMap) {
						String rtn = "no data";
						byte[] myimagedata = image.getImageData();
						String mt = "data:" + image.getMimeType() + ";base64,";
						String encodedText = new String(Base64.encodeBase64(myimagedata));
						rtn = mt + encodedText;
						return rtn;
					}

					protected String getImageMapID(IImage image) {
						if (image.getReportRunnable() != null) {
							return image.getReportRunnable().hashCode() + image.getID();
						}
						return image.getID();
					}
				});
				OutputStream os = response.getOutputStream();
				HTML_OPTIONS.setOutputFormat(HTMLRenderOption.OUTPUT_FORMAT_HTML);
				HTML_OPTIONS.setOutputStream(os);
				HTML_OPTIONS.setEmbeddable(true);
				TASK.setRenderOption(HTML_OPTIONS);
				TASK.run();
				TASK.close();
				Platform.shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}
	
	
	@RequestMapping(value = "/executeDbProcedureforStatistics", method = RequestMethod.POST)
	public String executeDbProcedureforStatistics(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {

		String resp = loginService.executeDbProcedureforStatistics(payload, request, response);

		return resp;
	}

	@RequestMapping(value = "/executeProcedureForDashBoard", method = RequestMethod.POST)
	public String executeProcedureForDashBoard(@RequestBody String payload, HttpServletRequest request,
			HttpServletResponse response) {

		String resp = loginService.executeProcedureForDashBoard(payload, request, response);

		return resp;
	}
}
