package com.xiaoy.count.sevice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.count.dao.CountDao;
import com.xiaoy.count.sevice.CountService;
import com.xiaoy.resource.servic.DictionaryService;
import com.xiaoy.resource.web.form.DictionaryForm;

@Service
@Transactional(readOnly = true)
public class CountServiceImpl implements CountService
{

	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private CountDao countDao;

	@Override
	public Map<String, Double> userSexCount()
	{
		Map<String, Double> map = null;
		List<Object[]> list = countDao.userSexCount();
		if (list != null && list.size() > 0)
		{
			map = new HashMap<String, Double>();
			List<DictionaryForm> ddl = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.SEX_NAME);

			for (Object[] o : list)
			{
				for (int i = 0; i < ddl.size(); i++)
				{
					DictionaryForm d = ddl.get(i);
					if (o[0].equals(d.getDdlCode()))
					{
						map.put(d.getDdlName(), (double) ((Long) o[1]));
						ddl.remove(i);
					}
				}
			}
		}
		return map;
	}

	@Override
	public Map<String, Double> userTypeCount()
	{
		Map<String, Double> map = null;
		List<Object[]> list = countDao.userTypeCount();
		if (list != null && list.size() > 0)
		{
			map = new HashMap<String, Double>();
			List<DictionaryForm> ddl = dictionaryService.findDictionaryListByKeyWord(DictionaryForm.MAINTAIN_TYPE_NAME);

			for (Object[] o : list)
			{
				for (int i = 0; i < ddl.size(); i++)
				{
					DictionaryForm d = ddl.get(i);
					if (o[0].equals(d.getDdlCode()))
					{
						map.put(d.getDdlName(), (double) ((Long) o[1]));
						ddl.remove(i);
					}
				}
			}
		}
		return map;
	}

	@Override
	public Map<String, Double> deviceBreakdownCount()
	{
		Map<String, Double> map = null;
		List<Object[]> list = countDao.deviceBreakdownCount();
		if (list != null && list.size() > 0)
		{
			map = new HashMap<String, Double>();

			for (Object[] o : list)
			{
				map.put((String) o[0], (double) ((Long) o[1]));
			}
		}
		return map;
	}

	@Override
	public Map<String, Double> deviceSum()
	{
		Map<String, Double> map = null;
		List<Object[]> list = countDao.deviceSum();
		if (list != null && list.size() > 0)
		{
			map = new HashMap<String, Double>();

			for (Object[] o : list)
			{
				map.put((String) o[0], (double) ((Long) o[1]));
			}
		}
		return map;
	}
}
