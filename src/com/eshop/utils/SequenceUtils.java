package com.eshop.utils;




import java.util.Date;

import com.eshop.entrity.Sequence;
import com.eshop.service.SequenceService;
import com.eshop.service.interf.SequenceServiceImpl;

public class SequenceUtils {
	private SequenceUtils(){};
	private static final SequenceUtils su = new SequenceUtils();
	public static synchronized SequenceUtils getInstance()
	{
		return su;
	}
	public static String getNextId(String type,String company)
	{
		String timeNextId = null;
		int nextId = 1;
		SequenceService ss = new SequenceServiceImpl();
		try {
			Sequence s = 	ss.getSequenceByType(type, company);
			//1、判断日期，是否是同一天，
			Date date = new Date();
			String time = DateUtil.formatDate3(date);
			String time1 = DateUtil.formatDate3(s.getDate());
			if(s!=null)
			{
				if (time1.compareTo(time) ==0) //2、若是，则将nextId保存，然后将值加1，更新到数据库
				{
					int  oldNextId = s.getNextId();
					 nextId = oldNextId ;
					 int newNextId = oldNextId + 1;
					 s.setNextId(newNextId);
				}
				else
					//3、如不是则将nextId赋值为01，更新到数据库
				{
					s.setNextId(1);
					nextId = 1;
					s.setDate(new java.sql.Date(date.getTime()));
				}
				ss.updateSequence(s, company);
				 timeNextId = s.getDate() + String.format("%02d", s.getNextId());
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	 
		
		return timeNextId;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		 Date d = new Date();
		System.out.println(new java.sql.Date(d.getTime()));
	}
}
