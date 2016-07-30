package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.BrowseLog;
import com.eshop.entrity.GoodFav;
import com.eshop.entrity.Goods;
import com.eshop.utils.Page;


/**
 * 商品服务
 * @author Jane
 *
 */
public interface GoodService {
		//分页查询商品信息；
		public List<Goods> findGoodByPagination(int currentPage, int pageSize,Map<String ,Object> m,String company);
		
		/**
		 * 
		 * @return:       List<Goods> 
		 * @param currentPage
		 * @param pageSize
		 * @param m
		 * @return
		 * <p>Description:查询所有上架商品 <p>
		 * @date:          2015年11月3日下午8:41:18
		 * @author         ysl
		 */
		public List<Goods> findSellGoodByPagination(int currentPage, int pageSize,Map<String ,Object> m,String company);
		
		//查询总记录数；
		public int getTotal(Map<String ,Object> m,String company)throws Exception;
		/**
		 * 
		 * @return:       int 
		 * @param m
		 * @return
		 * @throws Exception
		 * <p>Description: 查询所有上架商品的总记录数<p>
		 * @date:          2015年11月3日下午8:41:58
		 * @author         ysl
		 */
		public int getSellTotal(Map<String ,Object> m,String company)throws Exception;
		
		//通过商品编号查询信息；
		public Goods findGoodById(String good_id,String company)throws Exception;
		
		//更新商品信息
		public void updateGood(Goods good,String company)throws Exception;
		//删除商品
		public void deleteGood(String good_id, String company);

		//增加商品
		public void addGood(Goods good, String company);
		
		//更新商品价格和积分信息；
		public void updateGoodPrice(Goods good, String company);
		
		//查询新品推荐商品
		public List<Goods> queryAll(String company);
		
		//查询兑换排行榜商品
		public List<Goods> queryRank(String company);
		
		
		//查询属于某一级类别下的所有商品
		public Page<Goods> lookGoods(String c_parent,String company,String pagenum);
		
		//查询二级类别下的商品
		
		public Page<Goods>  lookMerchById(String c_id,String c_parent,String company,String nowprice,String sale_num,String currentpage);
		
		//查询所有商品
	    public Page<Goods>  lookAll(String company,String pagenum,String c_id,String c_parent);

		//通过C_id查询商品表中的相同类别中Good_id最大的记录；
		public String findGoodIdByCId(String c_id, String company);
		
		
		public void updateGoodSaleNum(Goods good, String company);
		
		//根据关键词查询商品
		public Page<Goods> queryGoodsByKeyword(String keyword,String company,String pagenum);
		
		
		public List<Goods> queryRecommend(String c_id,String good_id,String company);
		
		public void updateGoodPics(Goods good, String company);
		
		//根据关键词查询某类别下的商品
		public Page<Goods> lookMerchByKeyword(String c_id,String keyword,String company,String pagenum);
		
		
		//查询所有新品
	    public Page<Goods>  lookNewGoods(String company,String pagenum,String c_id,String c_parent);
        
	    //获取所有商品的商品名
	    public List<String> getKeyWord(String company);
	    
		//增加商品
		public void addFavGood(GoodFav goodFav, String company);

		

		public List<Goods> queryMyFavByUser(int user_id, String company,
				int currentPage, int pageSize);

		public int getFavTotal(int user_id, String company);

		public void addBrowseLog(BrowseLog browseLog, String company);

		public List<GoodFav> queryFavGoodByUserId(int user_id, String company);

		
	    
}
