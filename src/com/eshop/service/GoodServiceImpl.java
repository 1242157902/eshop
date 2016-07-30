package com.eshop.service;

import java.util.List;
import java.util.Map;

import com.eshop.dao.GoodDao;
import com.eshop.entrity.BrowseLog;
import com.eshop.entrity.GoodFav;
import com.eshop.entrity.Goods;
import com.eshop.service.interf.GoodService;
import com.eshop.utils.Page;

/**
 * 商品服务实现类
 * @author Jane
 *
 */
public class GoodServiceImpl implements GoodService {

	GoodDao goodDao=new GoodDao();
	
	@Override
	public List<Goods> findGoodByPagination(int currentPage, int pageSize,
			Map<String, Object> m,String company) {
		return goodDao.findGoodByPagination(currentPage, pageSize, m,company);
	}

	@Override
	public int getTotal(Map<String, Object> m,String company) throws Exception {
		return goodDao.getTotal(m,company);
	}

	@Override
	public Goods findGoodById(String good_id,String company) throws Exception {
		return goodDao.findGoodById(good_id,company);
	}

	@Override
	public void updateGood(Goods good,String company) throws Exception {
		goodDao.updateGood(good,company);

	}

	@Override
	public List<Goods> findSellGoodByPagination(int currentPage, int pageSize,
			Map<String, Object> m,String company) {
		return goodDao.findSellGoodByPagination(currentPage, pageSize, m,company);
	}

	public int getSellTotal(Map<String, Object> m,String company) throws Exception {
		return goodDao.getSellTotal(m,company);
	}

	
	@Override
	public void deleteGood(String good_id, String company) {
		goodDao.deleteGood(good_id,company);
	}

	@Override
	public void addGood(Goods good, String company) {
		goodDao.addGood(good,company);
	}

	
	//更新商品价格和积分信息
	@Override
	public void updateGoodPrice(Goods good, String company) {
		goodDao.updateGoodPrice(good,company);
	}
    
	//查询新品推荐商品
	@Override
	public List<Goods> queryAll(String company) {
		
		return goodDao.queryAll(company);
	}
	
	
	//查询兑换排行榜商品
    public List<Goods> queryRank(String company) {
		
		return goodDao.queryRank(company);
	}

	@Override
	public Page<Goods> lookGoods(String c_parent, String company,String pagenum) {
		return goodDao.lookGoods(c_parent, company,pagenum);
	}

	@Override
	public Page<Goods> lookMerchById(String c_id, String c_parent,String company,String nowprice,String sale_num,String pagenum) {
		return goodDao.lookMerchById(c_id,c_parent,company,nowprice,sale_num,pagenum);
	}

	@Override
	public String findGoodIdByCId(String c_id, String company) {
		return goodDao.findGoodIdByCId(c_id,company);
	}
	
	//查询所有商品
	public Page<Goods> lookAll(String company,String pagenum,String c_id,String c_parent) {
		return goodDao.lookAll(company,pagenum,c_id,c_parent);
	}

	
	public void updateGoodSaleNum(Goods good, String company){
		goodDao.updateGoodSaleNum(good, company);
	}

	//根据关键词查询商品
	@Override
	public Page<Goods> queryGoodsByKeyword(String keyword, String company,String pagenum) {
		return goodDao.queryGoodsByKeyword(keyword,company,pagenum);
	}

	@Override
	public List<Goods> queryRecommend(String c_id,String good_id, String company) {
		return goodDao.queryRecommend(c_id,good_id,company);
	}

	@Override
	public void updateGoodPics(Goods good, String company) {
		goodDao.updateGoodPics(good,company);
	}

	@Override
	public Page<Goods> lookMerchByKeyword(String c_id, String keyword,
			String company, String pagenum) {
		return goodDao.lookMerchByKeyword(c_id,keyword,company,pagenum);
	}

	@Override
	public Page<Goods> lookNewGoods(String company, String pagenum,
			String c_id, String c_parent) {
		return goodDao.lookNewGoods(company,pagenum, c_id, c_parent);
	}

	@Override
	public List<String> getKeyWord(String company) {
		return goodDao.getKeyWord(company);
	}

	@Override
	public void addFavGood(GoodFav goodFav, String company) {
		goodDao.addFavGood(goodFav,company);
	}

	@Override
	public List<Goods> queryMyFavByUser(int user_id, String company,
			int currentPage, int pageSize) {
		return goodDao.queryMyFavByUser(user_id, company, currentPage, pageSize);
	}

	@Override
	public int getFavTotal(int user_id, String company) {
		
		return goodDao.getFavTotal(user_id,company);
	}

	@Override
	public void addBrowseLog(BrowseLog browseLog, String company) {
		goodDao.addBrowseLog(browseLog,company);
		
	}

	@Override
	public List<GoodFav> queryFavGoodByUserId(int user_id, String company) {
		// TODO Auto-generated method stub
		return goodDao.queryFavGoodByUserId(user_id,company);
	}

	

	

	

	
	
}
