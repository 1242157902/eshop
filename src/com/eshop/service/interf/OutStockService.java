package com.eshop.service.interf;

import java.util.List;
import java.util.Map;

import com.eshop.entrity.OutStock;

 /**
  * 
  * <p>Title：        OutStockService<p>
  * <p>Description:  出库单服务类<p>
  * @date:           2015年11月4日下午3:06:18
  * @author:         ysl
  * @version         1.0
  */
public interface OutStockService {
	
 
 public void InsertOutStock(OutStock outStock,String company);
 
 public List<OutStock> findOutStockByPagination(int currentPage, int pageSize  ,Map<String ,Object> m);
 
 
public int getTotal(Map<String ,Object> m) throws Exception ;


public OutStock findOutStockById(String out_id,String company);

 
public void updateOutStock(OutStock outStock,String company);
}
