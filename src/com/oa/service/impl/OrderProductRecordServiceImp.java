package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.OrderProductRecord;
import com.oa.service.OrderProductRecordService;
@Component("orderProductRecordService")
/**
 * 订单产品记录 Service
 * @author Big mac
 *
 */
public class OrderProductRecordServiceImp implements OrderProductRecordService {

	private SuperDao superDao;
	public Serializable addOrderProductRecord(OrderProductRecord OrderProductRecord) {
		return superDao.add(OrderProductRecord);
	}

	public void deleteOrderProductRecord(OrderProductRecord OrderProductRecord) {
		superDao.delete(OrderProductRecord);
	}

	public OrderProductRecord getOrderProductRecord(Serializable id) {
		return (OrderProductRecord) superDao.select(OrderProductRecord.class, id);
	}

	public List<OrderProductRecord> getAllOrderProductRecords(Class clazz, String hql) {
		return ObjectToOrderProductRecord(superDao.getAllObjects(clazz, hql));
	}
	private List<OrderProductRecord> ObjectToOrderProductRecord(List<Object> list){
		List<OrderProductRecord> OrderProductRecords=new ArrayList<OrderProductRecord>();
		for(Object o: list){
			OrderProductRecords.add((OrderProductRecord)o);
		}
		return OrderProductRecords;
	}

	public List<OrderProductRecord> getPageOrderProductRecords(int index, Class clazz, String hql) {
		return ObjectToOrderProductRecord(superDao.getPage(index, clazz, hql));
	}

	public void deleteOrderProductRecords(String[] ids) {
		superDao.deleteList(OrderProductRecord.class, ids, "delete from OrderProductRecord o where o.id");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updateOrderProductRecord(OrderProductRecord orderProductRecord) {
		superDao.update(orderProductRecord);
	}
}
