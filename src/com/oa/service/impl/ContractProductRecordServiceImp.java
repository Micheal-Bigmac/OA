package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.ContractProductRecord;
import com.oa.service.ContractProductRecordService;
@Component("contractProductRecordService")
public class ContractProductRecordServiceImp implements
		ContractProductRecordService {

	private SuperDao superDao;
	public Serializable addContractProductRecord(ContractProductRecord ContractProduct) {
		return superDao.add(ContractProduct);
	}

	public void deleteContractProductRecord(ContractProductRecord ContractProduct) {
		superDao.delete(ContractProduct);
	}

	public ContractProductRecord getContractProductRecord(Serializable id) {
		return (ContractProductRecord) superDao.select(ContractProductRecord.class, id);
	}

	public List<ContractProductRecord> getAllContractProductRecords(Class clazz,
			String hql) {
		return objectToContractProductRecords(superDao.getAllObjects(clazz, hql));
	}
	
	private List<ContractProductRecord> objectToContractProductRecords(List<Object> list){
		List<ContractProductRecord> contractProductRecords =new ArrayList<ContractProductRecord>();
		for(Object o: list){
			contractProductRecords.add((ContractProductRecord)o);
		}
		return contractProductRecords;
	}

	public List<ContractProductRecord> getpageContractProductRecords(int index, Class clazz, String hql) {
		return objectToContractProductRecords(superDao.getPage(index, clazz, hql));
	}

	public void deletegetpageContractProductRecords(String[] ids) {
		superDao.deleteList(ContractProductRecord.class, ids, "delete from ContractProductRecord c where c.id");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updateContractProductRecords(
			ContractProductRecord contractProduct) {
		superDao.update(contractProduct);
	}

}
