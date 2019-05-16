/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.controller;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author SKILLLOTO G006
 */
public class NewClass {
    
    
    public static void main(String[] args) {
        createJson();
    }
    
    public static void createJson() {
        try {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("gstin", "25dfgdfgdfgdfg");
            jsonObjectBuilder.add("fp", "25-16-2015");
            jsonObjectBuilder.add("gt", "56645656");
            
            jsonObjectBuilder.add("b2b", Json.createArrayBuilder().add(Json.createObjectBuilder().add("ctin", "21ABCDE3180F8Z6")
                            )
            
            
            );
            
            
            
            JsonObject empObj = jsonObjectBuilder.build();

            StringWriter strWtr = new StringWriter();
            try (JsonWriter jsonWtr = Json.createWriter(strWtr)) {
                jsonWtr.writeObject(empObj);
            }

            System.out.println(strWtr.toString());
            
            
            
        } catch (Exception e) {
        }
        
        
        
    }
    
    
    
    
    
    
    public static void json() {
        try{ 
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("gstin", "25dfgdfgdfgdfg");
        jsonObjectBuilder.add("fp", "25-16-2015");
        jsonObjectBuilder.add("gt", "56645656");
       
        
        //{"gstin":"25ABCDE1028F6Z4","fp":"062016","gt":"3782969.01","b2b":[{"ctin":"21ABCDE3180F8Z6","inv":"[{\"ctin\":\"21ABCDE3180F8Z6\",\"flag\":\"D\",\"inum\":\"98678\",\"idt\":\"25-10-2016\",\"gstinno\":\"25ABCDE1028F6Z4\",\"checksum\":\"AflJufPlFStqKBZ\",\"val\":\"776522.02\",\"pos\":\"01\",\"rchrg\":\"N\",\"pro_ass\":\"Y\"}]","itms":"[{\"num\":\"1\",\"status\":\"A\",\"ty\":\"S\",\"hsn_sc\":\"S\",\"ctin\":\"21ABCDE3180F8Z6\",\"txval\":\"6210.99\",\"irt\":\"0\",\"iamt\":\"0\",\"crt\":\"37.4\",\"camt\":\"8874614.44\",\"srt\":\"33.41\",\"samt\":\"5.68\"}]"}]}
        
        
        
        
        
        
        
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        jsonArrayBuilder.add(Json.createObjectBuilder().add("ctin", "21ABCDE3180F8Z6")
                                                      // .add("inv", Json.createArrayBuilder().add("value"))
                
                                                       .add("inv", "[{\"flag\":\"D\",\"inum\":\"98678\",\"idt\":\"25-10-2016\",\"checksum\":\"AflJufPlFStqKBZ\",\"val\":\"776522.02\",\"pos\":\"01\",\"rchrg\":\"N\",\"pro_ass\":\"Y\"}]")
                                                      
                .add("itms", "[{\"num\":\"1\",\"status\":\"A\",\"ty\":\"S\",\"hsn_sc\":\"S\",\"ctin\":\"21ABCDE3180F8Z6\",\"txval\":\"6210.99\",\"irt\":\"0\",\"iamt\":\"0\",\"crt\":\"37.4\",\"camt\":\"8874614.44\",\"srt\":\"33.41\",\"samt\":\"5.68\"}]") 
                
                                                       .build() );
                                                       
        
       
        
         JsonArray contactsArr = jsonArrayBuilder.build();
        // add contacts array object
        
        jsonObjectBuilder.add("b2b", contactsArr);
        
       
        
        JsonObject empObj = jsonObjectBuilder.build();
        
        StringWriter strWtr = new StringWriter();
            try (JsonWriter jsonWtr = Json.createWriter(strWtr)) {
                jsonWtr.writeObject(empObj);
            }
         
        System.out.println(strWtr.toString());
       }catch(Exception e){
           System.out.println(""+e.toString());
       }
    }
    }
    
    
    

