package com.oa.service;

import java.io.Serializable;
import java.util.List;
import com.oa.model.EventCalender;

public interface EventCalenderService {
	
	public Serializable addEventCalender(EventCalender EventCalender);
	
	public void deleteEventCalender(EventCalender EventCalender);
	
	public void updateEventCalender(EventCalender EventCalender);
	public EventCalender getEventCalender(Serializable id);
	
	public List<EventCalender> getAllEventCalenders(Class clazz,String hql);
	
	public List<EventCalender> getPageEventCalenders(int index, Class clazz,String hql);
	
	public void deleteEventCalenders(String []ids);
	
}
