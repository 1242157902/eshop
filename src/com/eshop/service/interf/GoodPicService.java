package com.eshop.service.interf;

import java.util.List;

import com.eshop.entrity.GoodPics;
import com.eshop.entrity.Goods;


/**
 * 商品详细页图片管理服务类
 * @author Jane
 *
 */
public interface GoodPicService {

	//增加商品详细页图片；
	void addGoodPic(GoodPics goodpic, String company);
	//查询商品图片表中有没有该条记录；
	String findGoodByPic(String storeFile, String companys);
	
	public List<GoodPics> queryAllPic(String good_id,String company);
	
	public GoodPics queryOne(String good_id,String company);
}
