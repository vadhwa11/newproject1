/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.controller;

import com.test.entity.B2BInvoicesEntity;
import com.test.entity.GstnEntity;
import com.test.entity.ItemsEntity;
import com.test.model.GstinModel;
import com.test.service.gstr1.B2bService;
import com.test.service.gstr1.Gstr1Service;
import com.test.service.gstr1.ItemsService;
import com.test.utils.JsonUtil;
import java.io.StringWriter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author SKILLLOTO G006
 */
@Controller
public class BaseController {
    
    
  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String loadhello(ModelMap modelMap) {
    return "hello";
  }
   
//    @RequestMapping(value = "/getinvoice", method = RequestMethod.GET)
//	public String getinvoice(@ModelAttribute(value = "getinvoice") GstinModel gstinModel,BindingResult result,ModelMap modelMap) {
//		      System.out.println("com.test.controller.BaseController.getinvoice()"+gstinModel.getGstin());
//		modelMap.put("getinvoice", "getinvoice");
//		return "addinvoice";		
//	}
  
  
   @RequestMapping(value = "/getinvoice", method = RequestMethod.GET)
    public String addinvoice(ModelMap modelMap) {
        modelMap.put("getinvoice", new GstinModel());
        return "getinvoice";
    }
    
    
     @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public String addinvoice1(@ModelAttribute(value = "getinvoice") GstinModel gstinModel,ModelMap modelMap,BindingResult result) {
      //  modelMap.put("getinvoice", new GstinModel());
        return "getinvoice";
    }
    
    
    
    @Autowired
    Gstr1Service gstr1Service;

    @Autowired
    B2bService b2bService;

    @Autowired
    ItemsService itemsService;
     
    String respGstn="";
    String resPpb2BInvoicesEntitys=""; 
    String resPitemsEntity ="";
    
    
    
    @RequestMapping(value = "/savegstr1", method = RequestMethod.GET)
    public @ResponseBody String savegstr1() {
        System.out.println("inside /savegstr1....");
        
        List<GstnEntity> gstinentity = gstr1Service.getGstndata();
        System.out.println("jhlkjhsdkljhjk"+gstinentity.get(0).getGstinno());
        respGstn = JsonUtil.toString(gstinentity);
        
        
        
         List<B2BInvoicesEntity> b2BInvoicesEntitys = b2bService.getAllinvoice();
         resPpb2BInvoicesEntitys = JsonUtil.toString(b2BInvoicesEntitys);
        
        
        List<ItemsEntity> itemsEntity = itemsService.getAllitems();
        resPitemsEntity = JsonUtil.toString(itemsEntity);
        
       try{ 
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("gstin", ""+gstinentity.get(0).getGstinno());
        jsonBuilder.add("fp", gstinentity.get(0).getFp());
        jsonBuilder.add("gt", gstinentity.get(0).getGt());
       
        
        
        
        JsonArrayBuilder kvArrBld = Json.createArrayBuilder();
        
        kvArrBld.add(Json.createObjectBuilder().add("ctin", ""+b2BInvoicesEntitys.get(0).getCtin())
               .add("inv", ""+resPpb2BInvoicesEntitys).add("itms", resPitemsEntity)
               .build()); 
      
        
         JsonArray contactsArr = kvArrBld.build();
        // add contacts array object
        
        jsonBuilder.add("b2b", contactsArr);
        
       
        
        JsonObject empObj = jsonBuilder.build();
        
        StringWriter strWtr = new StringWriter();
            try (JsonWriter jsonWtr = Json.createWriter(strWtr)) {
                jsonWtr.writeObject(empObj);
            }
         
        System.out.println(strWtr.toString());
       }catch(Exception e){
           System.out.println(""+e.toString());
       }
       
        return respGstn+" "+resPpb2BInvoicesEntitys+" "+resPitemsEntity;
    }


}
