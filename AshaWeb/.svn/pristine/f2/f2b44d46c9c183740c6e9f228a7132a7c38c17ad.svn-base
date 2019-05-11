package com.asha.icgweb.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.asha.icgweb.entity.UploadDocument;


@Repository
public interface FileUploadDAO {

	Map<String, Object> save(UploadDocument uploadFile);
	Map<String, Object> showUploadedDocumentsForPatient(long patientId);
	List<UploadDocument> viewUploadDocuments(long documentId);

}

