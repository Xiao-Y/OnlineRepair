package com.xiaoy.evaluate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entites.Evaluate;
import com.xiaoy.base.entites.User;
import com.xiaoy.base.util.DateHelper;
import com.xiaoy.evaluate.dao.EvaluateDao;
import com.xiaoy.evaluate.service.EvaluateService;
import com.xiaoy.evaluate.web.form.EvaluateForm;
import com.xiaoy.resource.dao.DictionaryDao;
import com.xiaoy.resource.web.form.DictionaryForm;
import com.xiaoy.user.dao.UserDao;

@Service
@Transactional(readOnly = true)
public class EvaluateServiceImpl implements EvaluateService
{
	@Resource
	private EvaluateDao evaluateDao;

	// 数据字典
	@Resource
	private DictionaryDao dictionaryDao;

	// 用户
	@Resource
	private UserDao userDao;

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void createEvaluate(EvaluateForm eForm)
	{
		Evaluate evaluate = new Evaluate();
		BeanUtils.copyProperties(eForm, evaluate);

		evaluateDao.saveObject(evaluate);
	}

	@Override
	public List<EvaluateForm> findEvaluateList(EvaluateForm evaluateForm)
	{
		List<Object[]> list = evaluateDao.findEvaluateList(evaluateForm);
		List<EvaluateForm> form = this.evaluateVoToPoList(list);
		return form;
	}

	/**
	 * vo转po
	 * 
	 * @param list
	 * @return
	 */
	private List<EvaluateForm> evaluateVoToPoList(List<Object[]> list)
	{
		List<EvaluateForm> from = null;
		if (list != null && list.size() > 0)
		{
			from = new ArrayList<EvaluateForm>();
			for (Object[] o : list)
			{
				EvaluateForm e = new EvaluateForm();
				this.evaluateVoToPoForm(o, e);
				from.add(e);
			}
		}
		return from;
	}

	@Override
	public int countEvaluate(EvaluateForm evaluateForm)
	{
		return evaluateDao.countEvaluate(evaluateForm);
	}

	@Override
	public EvaluateForm findEvaluateByUuid(EvaluateForm evaluateForm)
	{
		Object[] o = evaluateDao.findEvaluateByUuid(evaluateForm.getEvaluateUuid());
		EvaluateForm e = new EvaluateForm();
		this.evaluateVoToPoForm(o, e);
		e.setVersion((String) o[11]);
		e.setAuditUuid((String) o[12]);
		e.setOrderTime(o[13] != null ? DateHelper.dateConverString((Date) o[13]) : "");
		e.setDevicePicUrl((String) o[14]);
		e.setAccount((String) o[15]);
		e.setRemark((String) o[16]);
		e.setMaintainUuid((String) o[17]);
		if (!StringUtils.isEmpty((String) o[17]))
		{
			User user = userDao.findObjectById((String) o[17]);
			if (user != null)
			{
				e.setMaintainName(user.getName());
				e.setMaintainTypePhone(user.getPhone());
				String maintainTypeCode = user.getMaintainTypeCode();
				if (!StringUtils.isEmpty(maintainTypeCode))
				{
					e.setMaintainTypeName(dictionaryDao.findDDLName(maintainTypeCode, DictionaryForm.MAINTAIN_TYPE_NAME));
				}
			}
		}
		e.setAuditTime(o[18] != null ? DateHelper.dateConverString((Date) o[18]) : "");
		e.setRankCode((String) o[19]);
		if (!StringUtils.isEmpty((String) o[19]))
		{
			e.setRankName(dictionaryDao.findDDLName((String) o[19], DictionaryForm.RANK));
		}
		return e;
	}

	/**
	 * Po转Vo
	 * 
	 * @param o
	 * @param e
	 */
	private void evaluateVoToPoForm(Object[] o, EvaluateForm e)
	{
		e.setAreaCode((String) o[0]);
		if (!StringUtils.isEmpty((String) o[0]))
		{
			e.setAreaName(dictionaryDao.findDDLName((String) o[0], DictionaryForm.AREA_NAME));
		}
		e.setInstallationSite((String) o[1]);
		if (!StringUtils.isEmpty((String) o[1]))
		{
			e.setInstallationSiteName(dictionaryDao.findDDLName((String) o[1], DictionaryForm.INSTALLATION_SITE_NAME));
		}

		e.setDeviceName((String) o[2]);
		e.setReportingUserName((String) o[3]);
		e.setReportingPhone((String) o[4]);
		e.setFinishTime(o[5] != null ? DateHelper.dateConverString((Date) o[5]) : "");
		e.setEvaluateUuid((String) o[6]);
		e.setEvaluateStatCode((String) o[7]);
		if (!StringUtils.isEmpty((String) o[7]))
		{
			e.setEvaluateStatName(dictionaryDao.findDDLName((String) o[7], DictionaryForm.EVALUATE_STAT));
		}
		e.setReportingUuid((String) o[8]);
		e.setUserUuid((String) o[9]);
		e.setReportingTime(o[10] != null ? DateHelper.dateConverString((Date) o[10]) : "");
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void evaluateInfoSave(EvaluateForm evaluateForm)
	{
		Evaluate evaluate = evaluateDao.findObjectById(evaluateForm.getEvaluateUuid());
		evaluate.setEvaluateStatCode(DictionaryForm.EVALUATE_STAT_OK);
		evaluate.setEvaluateTime(new Date());
		evaluate.setRankCode(evaluateForm.getRankCode());
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void updateEvaluateInfo(String evaluateUuid)
	{
		Evaluate evaluate = evaluateDao.findObjectById(evaluateUuid);
		evaluate.setDeleteFlag(DictionaryForm.DELETE_FLAG_FALSE);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void deletesEvaluateInfo(String[] ids)
	{
		if (ids != null)
		{
			for (String id : ids)
			{
				Evaluate evaluate = evaluateDao.findObjectById(id);
				evaluate.setDeleteFlag(DictionaryForm.DELETE_FLAG_FALSE);
			}
		}
	}
}
