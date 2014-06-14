package com.oa.service.impl;

import java.awt.peer.ListPeer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.SuperDaoInte;
import com.oa.model.PerformanceExamine;
import com.oa.model.PerformanceParameters;
import com.oa.model.Person;
import com.oa.service.PerformanceExamineSerivce;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.commons.digester.rss.Image;
import com.oa.model.ListPerformanceExamine;
@Component("performanceExamineService")
public class PerformanceExamineServiceImpl implements PerformanceExamineSerivce{

	private SuperDaoInte superDao;

	public List<PerformanceExamine> selectPerformanceExamine() {
		return convertToPerformanceExamine(superDao.find("from PerformanceExamine a"));
	}

	private List<PerformanceExamine> convertToPerformanceExamine(List<Object> objects) {
		List<PerformanceExamine> list = new ArrayList<PerformanceExamine>();
		for(int i=0; i<objects.size(); i++) {
			list.add((PerformanceExamine)objects.get(i));
		}
		return list;
	}

	private List<ListPerformanceExamine> convertToListPerformanceExamine(List<Object> objects) {
		List<ListPerformanceExamine> list = new ArrayList<ListPerformanceExamine>();
		for(int i=0; i<objects.size(); i++) {
			list.add((ListPerformanceExamine)objects.get(i));
		}
		return list;
	}
	
	public SuperDaoInte getSuperDao() {
		return superDao;
	}
	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}


	public void updatePerformanceExamine(PerformanceExamine pement) {
		superDao.update(pement);
	}

	public List<PerformanceExamine> selectPerformanceExamine(ListPerformanceExamine clazz) {
		List<Object> pe = superDao.find("from ListPerformanceExamine pe where pe.name = '" + clazz.getName() + "'");
		return convertToPerformanceExamine(pe);
	}

	public void deletePerformanceExamine(PerformanceExamine pe) {
		System.out.println("pe isd is  "+pe.getId());
		superDao.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("delete from PerformanceExamine a where a.id = "+pe.getId()).executeUpdate();
	}

	public List<ListPerformanceExamine> getPerformanceExaminePages(int index, Class<ListPerformanceExamine> clazz,
			String hql) {
		List<ListPerformanceExamine> list = convertToListPerformanceExamine(superDao.getPage(index, clazz, hql));
		return list;
		
	}

	public int getAllPerformanceExamines(Class<ListPerformanceExamine> clazz, String hql) {
		int listPE = convertToListPerformanceExamine(superDao.getDistinctAllObject(clazz, hql)).size();
		return listPE;
	}

	public List  getAllParams() {
		List<PerformanceParameters> list = convertToPerformanceParameters(superDao.find("from PerformanceParameters"));
		return list;
	}

	
	public List  getAllParams(Serializable Id) {
		List<PerformanceExamine> listPE = convertToPerformanceExamine(superDao.find("from PerformanceExamine pe where pe.lpe = "+Id));
		List list = new ArrayList();
		for(int i=0; i<listPE.size(); i++) {
			list.add(listPE.get(i).getParamId().getName());
		}
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
          Object element = iter.next();
          if (set.add(element))
             newList.add(element);
		}
		list.clear();
		list.addAll(newList);
		System.out.println( " remove duplicate " + list);
		
		return list;
	}

	public List<PerformanceParameters> convertToPerformanceParameters(List<Object> objects) {
		
		List<PerformanceParameters> list = new ArrayList<PerformanceParameters>();
		for(int i=0; i<objects.size(); i++) {
			list.add(((PerformanceParameters)objects.get(i)));
		}
		return list;
	}

	public List<Person> getAllPerson(Serializable peId) {
		/*List<PerformanceExamine> pe = convertToPerformanceExamine(superDao.find("from PerformanceExamine pe where pe.lpe = "+peId));
		Set set = new HashSet();
		System.out.println(pe.size());
		for(int i=0; i<pe.size(); i++) {
			System.out.println(pe.get(i).getPersonId().getId());
			set.add(pe.get(i).getPersonId().getId());
		}*/
		return convertToPerson(superDao.find("from Person p"));
	}
	
	public List<Person> convertToPerson(List<Object> objects) {
		List<Person> list = new ArrayList<Person>();
		for(int i=0; i<objects.size(); i++) {
			list.add((Person)objects.get(i));
		}
		return list;
	}

	public void addPerformanceExamine(PerformanceExamine performanceExamine) {
//		PerformanceExamine pe = new PerformanceExamine();
//		ListPerformanceExamine lpe = (ListPerformanceExamine)superDao.check("from ListPerformanceExamine lpe where lpe.id = ?", new Object[]{id});
//		System.out.println("i");
//		performanceExamine.getLpe().setId(id);
		superDao.add(performanceExamine);
	}


	public ListPerformanceExamine getLpe(Serializable peId) {
		ListPerformanceExamine LPE = (ListPerformanceExamine)superDao.select(ListPerformanceExamine.class, peId);
		return LPE;
	}

	public void deleteListPerformanceExamine(ListPerformanceExamine lpe) {
		superDao.delete(lpe);
	}

	


	public Serializable addListPerformanceExamine(String name,
		String instruction, String date, String recordUser) {
		ListPerformanceExamine lpe = new ListPerformanceExamine();
		lpe.setName(name);
		lpe.setDate(date);
		lpe.setInstruction(instruction);
		lpe.setRecordUser(recordUser);
		
		return superDao.add(lpe);
	}

	public ListPerformanceExamine select(Serializable peId) {
		ListPerformanceExamine lpe = (ListPerformanceExamine)superDao.select(ListPerformanceExamine.class, peId);
		return lpe;
	}

	public Map<Integer, Map<String, List>> getAllData(Integer peId) {
		List<PerformanceExamine> pe = convertToPerformanceExamine(superDao.find("from PerformanceExamine pe where pe.lpe = "+peId));
		Map<Integer, Map<String, List>> maps = new LinkedHashMap<Integer, Map<String,List>>();
		
		List list = new ArrayList();
		List listName  = new ArrayList();
		System.out.println(pe.size());
		for(int i=0; i<pe.size(); i++) {
			System.out.println(pe.get(i).getPersonId().getId());
			//set.add(pe.get(i).getPersonId().getId());
			list.add(pe.get(i).getPersonId().getId());
			listName.add(pe.get(i).getPersonId().getName());
		}
		
	   Set set = new HashSet();
	   List newList = new ArrayList();
	   for (Iterator iter = list.iterator(); iter.hasNext();) {
          Object element = iter.next();
          if (set.add(element))
             newList.add(element);
       }
	   list.clear();
	   list.addAll(newList);
	   System.out.println( " remove duplicate " + list);
		
	   
	   Set setName = new HashSet();
	   List newListName = new ArrayList();
	   for (Iterator iter = listName.iterator(); iter.hasNext();) {
          Object element = iter.next();
          if (setName.add(element))
             newListName.add(element);
       }
	   listName.clear();
	   listName.addAll(newListName);
	   System.out.println( " remove duplicate name is " + listName);
	   Map<String, List> map = null;
	   for (int j=0; j<list.size(); j++) {
			List lists = new ArrayList();
			for(int i=0; i<pe.size(); i++) {
				if(pe.get(i).getPersonId().getId()==list.get(j)) {
					System.out.println("pe.get(i).getPersonId().getId() "+pe.get(i).getPersonId().getId());
					System.out.println("list.get(j) "+list.get(j));
					lists.add(pe.get(i).getScore());
				}
			}
			map = new LinkedHashMap<String, List>();
			System.out.println("listName.get(j).toString() is "+listName.get(j).toString());
			for(int n=0; n<lists.size(); n++) {
				System.out.println("list(n is) "+lists.get(n));
			}
			map.put(listName.get(j).toString(), lists);
			maps.put(Integer.valueOf(list.get(j).toString()),map);
		}
		System.out.println("ddddddddddddddddddddddddddd");
		Iterator it=maps.entrySet().iterator();           
		System.out.println( maps.entrySet().size());    
		String key;           
		String value;    
		while(it.hasNext()){    
		        Map.Entry entry = (Map.Entry)it.next();           
		        key=entry.getKey().toString();           
		        value=entry.getValue().toString();           
		        System.out.println(key+"===="+value);                     
		}   
		return maps;
	}
}
