package com.oa.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.FieldItemDao;
import com.oa.model.FieldItem;
import com.oa.service.FieldItemService;
@Component("fieldItemService")
public class FieldItemServiceImp implements FieldItemService {

	private FieldItemDao fieldItemDao;
	
	public List<FieldItem> getPageFieldItems(int index, String hql) {
		return fieldItemDao.getPageFieldItems(index, hql, FieldItem.class);
	}

	public List<FieldItem> getAllFieldItems(String hql) {
		return fieldItemDao.getAllFieldItems(hql, FieldItem.class);
	}

	public Serializable addFieldItem(FieldItem item) {
		return fieldItemDao.addFieldItem(item);
	}

	public void updateFieldItem(FieldItem item) {
		fieldItemDao.updateFieldItem(item);
	}

	public void deleteFieldItem(FieldItem item) {
		fieldItemDao.deleteFieldItem(item);
	}

	public void DeleteListFieldItems(String[] ids) {
		String hql="delete from FieldItem item where item.id ";
		fieldItemDao.DeleteListFieldItems(FieldItem.class, ids, hql);
	}

	public FieldItemDao getFieldItemDao() {
		return fieldItemDao;
	}

	@Resource
	public void setFieldItemDao(FieldItemDao fieldItemDao) {
		this.fieldItemDao = fieldItemDao;
	}

}
