package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.PerScoreDao;
import com.eshop.entrity.PerScore;
import com.eshop.service.interf.PerScoreService;

 /**
  * 
  * <p>Title：        PerScoreServiceImpl<p>
  * <p>Description:  导入积分架构<p>
  * @date:           2015年12月6日下午12:12:28
  * @author:         ysl
  * @version         1.0
  */
public class PerScoreServiceImpl implements PerScoreService
{
	PerScoreDao perDao=new PerScoreDao();
	@Override
	public PerScore getImportPercent(String status ,String company) {
		return perDao.getImportPercent(status,company);
	}

	@Override
	public List<PerScore> findperScoreByPagination(Integer page, Integer rows,
			Map<String, Object> m, String company) {
		return perDao.findperScoreByPagination(page,rows,m,company);
	}

	@Override
	public Integer getTotal(Map<String, Object> m, String company) {
		return perDao.getTotal(m,company);
	}

	@Override
	public void addPerScore(PerScore perscore, String company, int userID) {
		perDao.addPerScore(perscore,company,userID);
	}
	
	@Override
	public void updatePerScore(String company) {
		perDao.updatePerScore(company);
	}

}
