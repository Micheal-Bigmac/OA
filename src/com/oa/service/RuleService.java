package com.oa.service;

import java.io.Serializable;
import java.util.List;

import com.oa.model.Rules;

public interface RuleService {
	public Serializable add(Rules rules);
	
	public void UpdateRule(Rules rules);
	
	public void deleteRule(Rules rules);

	public Rules getRule(Serializable id);
	
	public List<Rules> getAllRule(String hql);
}
