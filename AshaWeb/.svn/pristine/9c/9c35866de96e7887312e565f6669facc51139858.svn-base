package com.asha.icgweb.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asha.icgweb.dao.FileUploadDAO;
import com.asha.icgweb.entity.UploadDocument;
import com.asha.icgweb.hibernateutils.GetHibernateUtils;

@Repository
public class FileUploadDAOImpl implements  FileUploadDAO {
	
	@Autowired
	GetHibernateUtils getHibernateUtils;

	@Autowired
	SessionFactory sessionFactory;
	
	     
	    public FileUploadDAOImpl() {
	    }
	 
	    public FileUploadDAOImpl(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	 
	    @Override
	    @Transactional
	    public Map<String, Object> save(UploadDocument uploadFile) {
	    	Map <String,Object> map =  new HashMap<String, Object>();
	    	boolean flag = false;
	    	String message ="";
	    	String status="";
	    	try {
	    		Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
		    	Transaction tx = session.beginTransaction();
		    	session.save(uploadFile);
		    	tx.commit();
		    	flag = true;
		    	message="File uploaded Sucessfully";
		    	status="1";
		    	if(flag) {
		    		//map = showUploadedDocumentsForPatient(uploadFile.getPatient().getPatientId());
		    	}
		    	
	    	}catch(Exception e) {
	    		 message="File is not uploaded Sucessfully, some error is occurred";
	    		 status="0";
				getHibernateUtils.getHibernateUtlis().CloseConnection();
				e.printStackTrace();
			}
	    	map.put("message",message);
	    	map.put("status",status);
	    	return map;
	    }

	    
	    
	    @SuppressWarnings("unchecked")
		@Transactional
		@Override
		public Map<String, Object> showUploadedDocumentsForPatient(long patientId) {
			List<UploadDocument> documentList = new ArrayList<UploadDocument>();
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			
			documentList =session.createCriteria(UploadDocument.class)
							.add(Restrictions.eq("patient.patientId", patientId)).list();
			
			map.put("documentList",documentList);
			
			return map;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<UploadDocument> viewUploadDocuments(long documentId) {
			List<UploadDocument> uploadDocuments = new ArrayList<UploadDocument>();
			
			Session session = getHibernateUtils.getHibernateUtlis().OpenSession();
			uploadDocuments = session.createCriteria(UploadDocument.class).add(Restrictions.eq("fileId", documentId)).list();
			return uploadDocuments;
		}

	    
	}


