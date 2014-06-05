package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.oa.model.Rules;
import com.oa.service.RuleService;
import com.opensymphony.xwork2.ActionSupport;

public class RuleAction extends ActionSupport {
	private RuleService ruleServiceImp;
	private Rules rules;
	private Integer id;
	
	public String rulesList(){
		List<Rules> rules=ruleServiceImp.getAllRule("");
		System.out.println(rules.size()+" rules ");
		ServletActionContext.getRequest().setAttribute("ruleLists", rules);
		ServletActionContext.getRequest().setAttribute("total", rules.size());
		return "rulesList";
	}
	public String addRule(){
		if(rules.getId()==null){
			Serializable flag=ruleServiceImp.add(rules);
			return flag == null ? "operator_failure" : "operator_success";
		}
		ruleServiceImp.UpdateRule(rules);
		return "operator_success";
	}
	public String addRuleView(){
		rules=ruleServiceImp.getRule(id);
		System.out.println(rules.toString());
		ServletActionContext.getRequest().setAttribute("rule", rules);
		return "ruleView";
	}
	public RuleService getRuleServiceImp() {
		return ruleServiceImp;
	}
	@Resource
	public void setRuleServiceImp(RuleService ruleServiceImp) {
		this.ruleServiceImp = ruleServiceImp;
	}
	public Rules getRules() {
		return rules;
	}
	public void setRules(Rules rules) {
		this.rules = rules;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
