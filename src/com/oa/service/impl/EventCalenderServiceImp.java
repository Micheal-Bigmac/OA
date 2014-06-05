package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.EventCalender;
import com.oa.service.EventCalenderService;
@Component("eventCalenderService")
public class EventCalenderServiceImp implements EventCalenderService {

	private SuperDao superDao;
	public Serializable addEventCalender(EventCalender EventCalender) {
		return superDao.add(EventCalender);
	}

	public void deleteEventCalender(EventCalender EventCalender) {
		superDao.delete(EventCalender);
	}

	public EventCalender getEventCalender(Serializable id) {
		return (EventCalender) superDao.select(EventCalender.class, id);
	}

	public List<EventCalender> getAllEventCalenders(Class clazz, String hql) {
		return ObjectToEventCalender(superDao.getAllObjects(clazz, hql));
	}
	private List<EventCalender> ObjectToEventCalender(List<Object> list){
		List<EventCalender> EventCalenders=new ArrayList<EventCalender>();
		for(Object o: list){
			EventCalenders.add((EventCalender)o);
		}
		return EventCalenders;
	}

	public List<EventCalender> getPageEventCalenders(int index, Class clazz, String hql) {
		return ObjectToEventCalender(superDao.getPage(index, clazz, hql));
	}

	public void deleteEventCalenders(String[] ids) {
		superDao.deleteList(EventCalender.class, ids, "delete from EventCalender p where p.id ");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updateEventCalender(EventCalender EventCalender) {
		superDao.update(EventCalender);
	}

}
