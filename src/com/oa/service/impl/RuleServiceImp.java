package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.Rules;
import com.oa.service.RuleService;
@Component("ruleServiceImp")
public class RuleServiceImp implements RuleService {

	private SuperDao superDao;
	public Serializable add(Rules rules) {
		return superDao.add(rules);
	}

	public void UpdateRule(Rules rules) {
		superDao.update(rules);
	}

	public void deleteRule(Rules rules) {
		superDao.delete(rules);
	}

	public Rules getRule(Serializable id) {
		return (Rules) superDao.select(Rules.class, id);
	}

	public List<Rules> getAllRule( String hql) {
		return objectToRules(superDao.getAllObjects(Rules.class, hql));
	}
	private List<Rules> objectToRules(List<Object> list){
		List<Rules> rules=new ArrayList<Rules>();
		for(Object o: list){
			rules.add((Rules)o);
		}
		return rules;
	}
	public SuperDao getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

}
