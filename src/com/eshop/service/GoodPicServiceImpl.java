package com.eshop.service;

import java.util.List;

import com.eshop.dao.GoodPicDao;
import com.eshop.entrity.GoodPics;
import com.eshop.entrity.Goods;
import com.eshop.service.interf.GoodPicService;

public class GoodPicServiceImpl implements GoodPicService {
	
	GoodPicDao dao=new GoodPicDao();
	
	@Override
	public void addGoodPic(GoodPics goodpic, String company) {
		dao.addGoodPic(goodpic,company);
	}

	@Override
	public String findGoodByPic(String storeFile, String company) {
		return dao.findGoodByPic(storeFile,company);
	}
	
	@Override
	public List<GoodPics> queryAllPic(String good_id, String company) {
		return dao.queryAllPic(good_id,company);
	}

	@Override
	public GoodPics queryOne(String good_id, String company) {
		return dao.queryOne(good_id,company);
	}

}
