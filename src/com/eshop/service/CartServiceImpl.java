package com.eshop.service;

import java.util.ArrayList;
import java.util.List;

import com.eshop.dao.CartDao;
import com.eshop.dao.GoodDao;
import com.eshop.dao.LoginDao;
import com.eshop.entrity.CartItem;
import com.eshop.entrity.User;






/**
 * @author  JYM
 * 2015-11-13 下午8:42:23
 */
public class CartServiceImpl {

	/**
	 * @param cartItem
	 */
	CartDao cartDao=new CartDao();
	LoginDao loginDao=new LoginDao();
	GoodDao goodDao=new GoodDao();
	
	public void save(CartItem cartItem,String company) {
	
		
        CartItem _cartItem = cartDao.getByuserAndgoodid(cartItem.getUser(),cartItem.getGood_id());
        System.out.println(_cartItem);
        
		
		if(_cartItem == null) {//如果原来没有这个条目，那么添加条目
			
			cartDao.save(cartItem,company);
		} else {//如果原来有这个条目，修改数量
			// 使用原有数量和新条目数量之各，来做为新的数量
			int quantity = cartItem.getGood_quantity() + _cartItem.getGood_quantity();
			// 修改这个老条目的数量
			
		      _cartItem.setGood_quantity(quantity);
		      
			
			cartDao.update(_cartItem,  company);
		}
		
	}

	/**
	 * @param userid
	 * @param phonenum 
	 * @param uninno 
	 * @return
	 */
	public List<CartItem> getitems(String userid, String uninno, String phonenum) {
		
		List<CartItem> cartItems=cartDao.find(userid,uninno);
		 User user =loginDao.getuser(phonenum, uninno);
		 for (CartItem cartItem : cartItems) {
		     cartItem.setUser(user);
			 cartItem.setGoods(goodDao.findGoodById(cartItem.getGood_id(),uninno));
	    } 
    	return cartItems;
		
	}

	/**
	 * @param cartitemid
	 * @param quantity
	 * @param uninno 
	 * @return
	 */
	public CartItem updatequantity(String cartitemid, int quantity, String uninno) {
		
		  CartItem cartItem=cartDao.getCartById(cartitemid,uninno);
		   
		   cartItem.setGood_quantity(quantity);
		   
		   cartItem.setGoods(goodDao.findGoodById(cartItem.getGood_id(), uninno));
		   
		   cartDao.update(cartItem, uninno);
		
		return cartItem;
	}

	/**
	 * @param cartitemids
	 * @param company
	 */
	public void delete(String cartitemids, String company) {
	String cids[]=cartitemids.split(",");
		
		
		for (int i = 0; i < cids.length; i++) {
			
		  CartItem cartItem=cartDao.getCartById(cids[i], company);
		  if (cartItem!=null) {
			
			  cartDao.delete(cartItem,company);
		    }
			
		}
		
	}

	/**
	 * @param cartitemids
	 * @param company
	 * @return
	 */
	public List<CartItem> getitemsbyid(String cartitemids, String company) {
          String ids[]=cartitemids.split(",");
		
		
		List<CartItem> cartItems=new ArrayList<CartItem>();
		
		for (int i = 0; i < ids.length; i++) {
		   cartItems.add(cartDao.getCartById(ids[i], company));
		}
		
		
		for (CartItem cartItem : cartItems) {
		    cartItem.setUser(loginDao.findUserById(cartItem.getUser_id(), company));
			cartItem.setGoods(goodDao.findGoodById(cartItem.getGood_id(), company));
		  }
		
		return cartItems;
	}

	
	
	
	
	
	

}
