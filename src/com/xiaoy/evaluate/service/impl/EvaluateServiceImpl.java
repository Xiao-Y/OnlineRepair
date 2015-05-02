package com.xiaoy.evaluate.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.Evaluate;
import com.xiaoy.evaluate.dao.EvaluateDao;
import com.xiaoy.evaluate.service.EvaluateService;
import com.xiaoy.evaluate.web.form.EvaluateForm;

@Service
@Transactional(readOnly=true)
public class EvaluateServiceImpl implements EvaluateService
{

	@Resource
	private EvaluateDao evaluateDao;
	
	@Override
	@Transactional(readOnly=false,isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public void createEvaluate(EvaluateForm eForm)
	{
		Evaluate evaluate = new Evaluate();
		BeanUtils.copyProperties(eForm, evaluate);
		
		evaluateDao.saveObject(evaluate);
	}

}
