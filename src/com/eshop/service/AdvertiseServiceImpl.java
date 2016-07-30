/**
 * 
 */
package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.AdvertiseDao;
import com.eshop.entrity.Advertise;
import com.eshop.entrity.AdvertiseList;
import com.eshop.service.interf.AdvertiseService;

/**
 * yy 2015 2015年11月24日 下午8:58:51
 *
 */
public class AdvertiseServiceImpl implements AdvertiseService{
     
	
	AdvertiseDao advertiseDao=new AdvertiseDao();
	@Override
	public void addAdvertise(Advertise advertise, String company) {
		advertiseDao.addAdvertise(advertise,company);
	}
	@Override
	public List<Advertise> queryPic(String company) {
		return advertiseDao.queryPic(company);
	}
	@Override
	public int getTotal(String company)
			throws Exception {
		return advertiseDao.getTotal(company);
	}
	@Override
	public void insertAdvList(String advertise_ids, String company,int userId) {
		advertiseDao.insertAdvList(advertise_ids,company,userId);
	}
	@Override
	public List<AdvertiseList> queryAdvList(int user_id, String company) {
		return advertiseDao.queryAdvList(user_id,company);
	}
	@Override
	public void updateTmpAdvList(String id, String showtime, String company) {
		advertiseDao.updateTmpAdvList(id,showtime,company);
	}
	@Override
	public AdvertiseList queryTmpAdvList(String id, String company) {
		return advertiseDao.queryTmpAdvList(id,company);
	}
	@Override
	public void finishAdvList(AdvertiseList advertiseList, String company) {
		advertiseDao.finishAdvList(advertiseList,company);
	}
	@Override
	public void deleteTmpAdvList(String id,String company) {
		advertiseDao.deleteTmpAdvList(id,company);
	}
	@Override
	public List<AdvertiseList> queryQueue(String company) {
		return advertiseDao.queryQueue(company);
	}
	@Override
	public AdvertiseList getAdvList(String company) {
		return advertiseDao.getAdvList(company);
	}
	@Override
	public void deleteAdvList(String id, String company) {
		advertiseDao.deleteAdvList(id,company);
	}

}
