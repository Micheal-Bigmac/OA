package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.ContractProductRecord;

public interface ContractProductRecordService {
	
	public Serializable addContractProductRecord(ContractProductRecord ContractProduct);
	
	public void deleteContractProductRecord(ContractProductRecord ContractProduct);
	
	public ContractProductRecord getContractProductRecord(Serializable id);
	
	public List<ContractProductRecord> getAllContractProductRecords(Class clazz,String hql);
	
	public List<ContractProductRecord> getpageContractProductRecords(int index,Class clazz,String hql);
	
	public void deletegetpageContractProductRecords(String []ids);
	
	public void updateContractProductRecords(ContractProductRecord contractProduct);
	
}
