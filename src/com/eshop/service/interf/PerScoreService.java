package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.PerScore;

/**
 * 
 * <p>Title：        PerScoreService<p>
 * <p>Description: 修改导入积分比例 <p>
 * @date:           2015年12月6日下午12:17:47
 * @author:         ysl
 * @version         1.0
 */
public interface PerScoreService {

	public PerScore getImportPercent(String status ,String company);
	
	//查询积分比例列表
	public List<PerScore> findperScoreByPagination(Integer page, Integer rows,
			Map<String, Object> m, String company);

	//查询记录总数
	public Integer getTotal(Map<String, Object> m, String company);
	
	//新添加信息
	public void addPerScore(PerScore perscore, String company, int userID);

	//更新比例记录信息的状态为0；
	public void updatePerScore(String company);
}
