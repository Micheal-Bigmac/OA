package com.oa.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.model.OnAndOffRegister;
import com.oa.model.UserOnAndOffRegister;
import com.oa.model.Users;
import com.oa.service.AttendanceService;
import com.opensymphony.xwork2.ActionSupport;

public class AttendanceManagementAction extends ActionSupport{
	private AttendanceService attendanceService; 
	private String registerHourOn;
	private String registerMinuteOn;
	private String registerHourOff;
	private String registerMinuteOff;
	private String ontime;
	private String offtime;
	private String returns;
	public String getRegisterHourOn() {
		return registerHourOn;
	}

	public void setRegisterHourOn(String registerHourOn) {
		this.registerHourOn = registerHourOn;
	}

	public String getRegisterMinuteOn() {
		return registerMinuteOn;
	}

	public void setRegisterMinuteOn(String registerMinuteOn) {
		this.registerMinuteOn = registerMinuteOn;
	}

	public String getRegisterHourOff() {
		return registerHourOff;
	}

	public void setRegisterHourOff(String registerHourOff) {
		this.registerHourOff = registerHourOff;
	}

	public String getRegisterMinuteOff() {
		return registerMinuteOff;
	}

	public void setRegisterMinuteOff(String registerMinuteOff) {
		this.registerMinuteOff = registerMinuteOff;
	}

	public AttendanceService getAttendanceService() {
		return attendanceService;
	}
	@Resource
	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	
	public String getOntime() {
		return ontime;
	}

	public void setOntime(String ontime) {
		this.ontime = ontime;
	}

	public String getOfftime() {
		return offtime;
	}

	public void setOfftime(String offtime) {
		this.offtime = offtime;
	}
	public String AttendanceSet() {
		List<String> list = new ArrayList<String>();
		list.add("未设置");
		for(int i=0; i<10; i++) {
			String str = "0";
			str += String.valueOf(i);
			list.add(str);
		}
		for(int i=11; i<24; i++) {
			list.add(String.valueOf(i));
		}
		
		List<String> list2 = new ArrayList<String>();
		list2.add("未设置");
		for(int i=0; i<10; i++) {
			String str = "0";
			str += String.valueOf(i);
			list2.add(str);
		}
		for(int i=11; i<60; i++) {
			list2.add(String.valueOf(i));
		}
		
		List<OnAndOffRegister> listOnAndOffRegisters = attendanceService.getRegisterSet();
		//上班时间
		registerHourOn = listOnAndOffRegisters.get(0).getRegularTime().substring(0, listOnAndOffRegisters.get(0).getRegularTime().lastIndexOf(":"));
		registerMinuteOn = listOnAndOffRegisters.get(0).getRegularTime().substring(listOnAndOffRegisters.get(0).getRegularTime().lastIndexOf(":")+1, listOnAndOffRegisters.get(0).getRegularTime().length());
		System.out.println("hour on is "+registerHourOn);
		System.out.println("minute on is "+registerMinuteOn);
		//下班时间
		registerHourOff = listOnAndOffRegisters.get(1).getRegularTime().substring(0, listOnAndOffRegisters.get(1).getRegularTime().lastIndexOf(":"));
		registerMinuteOff = listOnAndOffRegisters.get(1).getRegularTime().substring(listOnAndOffRegisters.get(1).getRegularTime().lastIndexOf(":")+1, listOnAndOffRegisters.get(1).getRegularTime().length());
		System.out.println("hour off is "+registerHourOff);
		System.out.println("minute off is "+registerMinuteOff);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession  session = request.getSession();
		session.setAttribute("listHours", list);
		session.setAttribute("listMinutes", list2);
		request.setAttribute("requestHourOn", registerHourOn);
		request.setAttribute("requestMinuteOn", registerMinuteOn);
		request.setAttribute("requestHourOff", registerHourOff);
		request.setAttribute("requestMinuteOff", registerMinuteOff);
		
		return "attendanceSet";
	}
	
	
	public String AttendanceSetSubmit() {
		System.out.println("shang hour on "+registerHourOn);
		System.out.println("shang minute on "+registerMinuteOn);
		System.out.println("xia hour off " + registerHourOff);
		System.out.println("xia minute off " + registerMinuteOff);
		String registerOn = registerHourOn + ":" + registerMinuteOn;
		String registerOff = registerHourOff + ":" + registerMinuteOff;
		attendanceService.addOnAndOffTimeSet(registerOn,registerOff);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("requestHourOn", registerHourOn);
		request.setAttribute("requestMinuteOn", registerMinuteOn);
		request.setAttribute("requestHourOff", registerHourOff);
		request.setAttribute("requestMinuteOff", registerMinuteOff);
		
		return "attendanceSet";
	}
	
	public String OnAndOffRegister() {
		List<OnAndOffRegister> listOnAndOffRegisters = attendanceService.getRegisterSet();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		request.setAttribute("onTime", listOnAndOffRegisters.get(0).getRegularTime());
		request.setAttribute("offTime", listOnAndOffRegisters.get(1).getRegularTime());
		Users user = (Users)session.getAttribute("admin");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		
		System.out.println("date is "+date);
		List<UserOnAndOffRegister> userOnAndOffRegisterList = attendanceService.select(user.getId(),date);
		System.out.println("userONandoff list size is "+userOnAndOffRegisterList.size());
		if(userOnAndOffRegisterList.size() != 0) {
			if(userOnAndOffRegisterList.get(0).getOnTime() == null &&
			   userOnAndOffRegisterList.get(0).getOffTime() != null) {
				request.setAttribute("hasClickedOff", "hasClicked");
				return "OnAndOffRegister";
			} 
			if(userOnAndOffRegisterList.get(0).getOnTime() != null &&
			   userOnAndOffRegisterList.get(0).getOffTime() == null) {
				request.setAttribute("hasClickedOn", "hasClicked");
				return "OnAndOffRegister";
			}
			if(userOnAndOffRegisterList.get(0).getOnTime() != null &&
			   userOnAndOffRegisterList.get(0).getOffTime() != null) {
				request.setAttribute("hasClickedOff", "hasClicked");
				request.setAttribute("hasClickedOn", "hasClicked");
				return "OnAndOffRegister";
			}
		} 
		
		return "OnAndOffRegister";
	}
	
	public String AttendanceRegisterSubmitOn() {
		List<OnAndOffRegister> listOnAndOffRegisters = attendanceService.getRegisterSet();
		String regularOnTime = listOnAndOffRegisters.get(0).getRegularTime();
		Calendar cal=Calendar.getInstance();    
		int y=cal.get(Calendar.YEAR);    
		int m=cal.get(Calendar.MONTH);    
		int d=cal.get(Calendar.DATE);   
		int h=cal.get(Calendar.HOUR);
		int mm=cal.get(Calendar.MINUTE);
		String regularOnTimeStr = y+"-"+m+"-"+d+" "+regularOnTime;
		String userOnTime = y+"-"+m+"-"+d+" "+h+":"+mm;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Users user = (Users)session.getAttribute("admin");
			String timeOn = h+":"+mm;
			String timeOff = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			
			System.out.println("date is "+date);
			List<UserOnAndOffRegister> userOnAndOffRegisterList = attendanceService.select(user.getId(),date);
			System.out.println("userONandoff list size is "+userOnAndOffRegisterList.size());
			Date regularOnDate = simpleDate.parse(regularOnTimeStr);
			Date userOnDate = simpleDate.parse(userOnTime);
			if(userOnAndOffRegisterList.size() == 0) {
				UserOnAndOffRegister userOnAndOffRegister = new UserOnAndOffRegister();;
				userOnAndOffRegister.setDate(date);
				userOnAndOffRegister.setOnTime(timeOn);
				userOnAndOffRegister.setOffTime(timeOff);
				userOnAndOffRegister.setUserId(user);
				
				if(regularOnDate.after(userOnDate)) {
					//正常
					userOnAndOffRegister.setNormalState("1");
				} else {
					//迟到
					userOnAndOffRegister.setLateState("1");
				} 
				
				attendanceService.add(userOnAndOffRegister);
			} else {
				UserOnAndOffRegister userOnAndOffRegister = attendanceService.getUoor(userOnAndOffRegisterList.get(0).getId()); 
				userOnAndOffRegister.setId(userOnAndOffRegisterList.get(0).getId());
				userOnAndOffRegister.setDate(date);
				userOnAndOffRegister.setOnTime(timeOn);
				userOnAndOffRegister.setOffTime(userOnAndOffRegisterList.get(0).getOffTime());
				userOnAndOffRegister.setUserId(user);
				attendanceService.update(userOnAndOffRegister);
				request.setAttribute("hasClickedOff","hasClicked");
			}
			
			if(regularOnDate.after(userOnDate)) {
				request.setAttribute("onTime", listOnAndOffRegisters.get(0).getRegularTime());
				request.setAttribute("offTime", listOnAndOffRegisters.get(1).getRegularTime());
				request.setAttribute("hasClickedOn","hasClicked");
				return "OnAndOffRegister";
			} 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setAttribute("hasClickedOn","hasClicked");
		request.setAttribute("lated","lated");
		return "OnAndOffRegister";
	}
	
	public String AttendanceRegisterSubmitOff() {
		List<OnAndOffRegister> listOnAndOffRegisters = attendanceService.getRegisterSet();
		String regularOffTime = listOnAndOffRegisters.get(1).getRegularTime();
		Calendar cal=Calendar.getInstance();    
		int y=cal.get(Calendar.YEAR);    
		int m=cal.get(Calendar.MONTH);    
		int d=cal.get(Calendar.DATE); 
		int h=cal.get(Calendar.HOUR);
		int mm=cal.get(Calendar.MINUTE);
		String regularOffTimeStr = y+"-"+m+"-"+d+" "+regularOffTime;
		String userOffTime = y+"-"+m+"-"+d+" "+h+":"+mm;
		
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			HttpSession session = request.getSession();
			Users user = (Users)session.getAttribute("admin");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			String timeOff = h+":"+mm;
			String timeOn = "";
			List<UserOnAndOffRegister> userOnAndOffRegisterList = attendanceService.select(user.getId(),date);
			Date regularOffDate = simpleDate.parse(regularOffTimeStr);
			Date userOffDate = simpleDate.parse(userOffTime);
			
			System.out.println("userONandoff list size is "+userOnAndOffRegisterList.size());
			if(userOnAndOffRegisterList.size() == 0) {
				UserOnAndOffRegister userOnAndOffRegister = new UserOnAndOffRegister();
				userOnAndOffRegister.setDate(date);
				userOnAndOffRegister.setOnTime(timeOn);
				userOnAndOffRegister.setOffTime(timeOff);
				userOnAndOffRegister.setUserId(user);
				if(regularOffDate.after(userOffDate)) {
					//正常
					userOnAndOffRegister.setBackState("1");
				} else {
					//早退
					userOnAndOffRegister.setNormalState("1");
				}
				attendanceService.add(userOnAndOffRegister);
			} else {
				UserOnAndOffRegister userOnAndOffRegister = attendanceService.getUoor(userOnAndOffRegisterList.get(0).getId()); 
				userOnAndOffRegister.setId(userOnAndOffRegisterList.get(0).getId());
				userOnAndOffRegister.setDate(date);
				userOnAndOffRegister.setOnTime(userOnAndOffRegisterList.get(0).getOnTime());
				userOnAndOffRegister.setOffTime(timeOff);
				userOnAndOffRegister.setUserId(user);
				attendanceService.update(userOnAndOffRegister);
				request.setAttribute("hasClickedOn","hasClicked");
			}
			if(regularOffDate.after(userOffDate)) {
				request.setAttribute("onTime", listOnAndOffRegisters.get(0).getRegularTime());
				request.setAttribute("offTime", listOnAndOffRegisters.get(1).getRegularTime());
				request.setAttribute("hasClickedOff","hasClicked");
				request.setAttribute("zaotui","zaotui");
				return "OnAndOffRegister";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setAttribute("hasClickedOff","hasClicked");
		return "OnAndOffRegister";
	}
}
