/**
 * 
 */
package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.Advertise;
import com.eshop.entrity.AdvertiseList;

/**
 * yy 2015 2015年11月24日 下午8:56:44
 *
 */
public interface AdvertiseService {
	
	public void addAdvertise(Advertise advertise,String company);
	
	//查询图片
	public List<Advertise> queryPic(String company);
    
	
	//就是图片数量
	public int getTotal(String company)throws Exception;
	
	//生成图片列表
	public void insertAdvList(String advertise_ids,String company,int userId);
	
	//查询临时图片列表
	public List<AdvertiseList>  queryAdvList(int user_id,String company);
	
	//修改临时图片列表展示时间
	public void updateTmpAdvList(String id,String showtime,String company);
	
	//根据id查询临时图片列表
	public AdvertiseList queryTmpAdvList(String id,String company);
	
	//生成图片列表
	public void finishAdvList(AdvertiseList advertiseList,String company);
	
	//删除临时图片列表
	public void deleteTmpAdvList(String id,String company);
	
	//查询图片列表
	public List<AdvertiseList>  queryQueue(String company);
	
	//获取当前图片列表
	public AdvertiseList getAdvList(String company);
	
	//删除图片列表
    public void deleteAdvList(String id,String company);
	
}
