package com.oa.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.oa.model.EventCalender;
import com.oa.service.EventCalenderService;
import com.opensymphony.xwork2.ActionSupport;

public class EventAction extends ActionSupport {
	private EventCalenderService eventCalenderService;
	private EventCalender event=new EventCalender();
	private String title;
	private Boolean allDay;
	private Long start;
	private Long end;
	private Integer id;
	public String setEvent() throws Exception{
		if(id==null){
			//添加
			event.setTitle(title);
			event.setAllDay(allDay);
			event.setStart(LongToDate(start));
			event.setEnd(LongToDate(end));
			event.setId(id);
			System.out.println(event.toString());
			Integer id=(Integer) eventCalenderService.addEventCalender(event);
			ServletActionContext.getResponse().getWriter().println(id);
		}else {
			//修改
			eventCalenderService.updateEventCalender(event);
		}
//		getListEvent();
		return null;
	}
	public String deleteEvent() throws Exception{
		event.setId(id);
		System.out.println(event.toString());
		eventCalenderService.deleteEventCalender(event);
//		getListEvent();
		return null;
	}
	private Date LongToDate(Long misLong){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(misLong*1000);
		return calendar.getTime();
	}
	public String updateEvent() throws Exception{
		event.setTitle(title);
		event.setAllDay(allDay);
//		event.setStart(start);
//		event.setEnd(end);
		event.setId(id);
		eventCalenderService.updateEventCalender(event);
//		getListEvent();
		return  null;
	}
	public void getListEvent() throws Exception{
		List<EventCalender> eventCalenders=eventCalenderService.getAllEventCalenders(EventCalender.class, "");
		JSONArray array=new JSONArray();
		JSONObject object=new JSONObject();
		for(EventCalender calender : eventCalenders){
			object.put("id", calender.getId());
			object.put("title", calender.getTitle());
			object.put("allDay", calender.getAllDay());
			object.put("start", calender.getStart().getTime()/1000);
			object.put("end", calender.getEnd().getTime()/1000);
			object.put("location", calender.getLocation());
			array.put(object);
		}
		ServletActionContext.getResponse().getWriter().println(array.toString());
	}
/*	private Long dateToMissLong(Date datee){
		  String date = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(datee);
		  return datee.getTime();
	}*/
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ChartAction [title=" + title + ", allDay=" + allDay
				+ ", start=" + start + ", end=" + end + "]";
	}

	public EventCalenderService getEventCalenderService() {
		return eventCalenderService;
	}
	@Resource
	public void setEventCalenderService(EventCalenderService eventCalenderService) {
		this.eventCalenderService = eventCalenderService;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EventCalender getEvent() {
		return event;
	}
	public void setEvent(EventCalender event) {
		this.event = event;
	}
	public Boolean getAllDay() {
		return allDay;
	}
	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}


}
