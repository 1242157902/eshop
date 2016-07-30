package com.eshop.test;

import java.util.List;

import com.eshop.entrity.Stock;
import com.eshop.service.StockServiceImpl;
import com.eshop.service.interf.StockService;

public class Test {
	public static void main(String[] args) {
		
		//用于测试StockDao
		//StockDao sd = new StockDao();
		StockService ss = new StockServiceImpl();
		List<Stock> stockList = ss.findStockByPagination(1, 3, null);
		 for (Stock stock : stockList) {
			System.out.println(stock.getGood_name());
		}
		
	}
}
