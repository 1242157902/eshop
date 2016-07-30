package com.eshop.utils;

/**
 * 数字字符串累加1工具类;
 * @author xia
 *
 */
public class NumStringAddUtil {
	
	//一级分类id累加1；
	public static String numStringAdd(String num){
		//转成整数并执行累加操作，但是位数变少了(如："01")，怎么办？
		int i=Integer.valueOf(num)+1;
		//累加后转换为字符串;
		String Num=String.valueOf(i);
		if(i<10){
			//为了拼接字符串使用；
			StringBuffer sb=new StringBuffer();
			//补缺前面缺失的0；
			sb.append("0");
			Num=sb.toString()+Num;
		}
		return Num;
	}
	
	//多位数的累加1操作；
	public static String numStringAdd2(String id,String num){
				//转成整数并执行累加操作但是位数变少了(如：0101或020101)
				int i=Integer.valueOf(num)+1;
				int j=Integer.valueOf(id);
				//累加后转换为字符串;
				String Num=String.valueOf(i);
				if(j<10){
					//为了拼接字符串使用；
					StringBuffer sb=new StringBuffer();
					//补缺前面缺失的0；
					sb.append("0");
					Num=sb.toString()+Num;
				}else if(j<1000){
					//为了拼接字符串使用；
					StringBuffer sb=new StringBuffer();
					//补缺前面缺失的0；
					sb.append("0");
					Num=sb.toString()+Num;
				}else if(j<100000){
					//为了拼接字符串使用；
					StringBuffer sb=new StringBuffer();
					//补缺前面缺失的0；
					sb.append("0");
					Num=sb.toString()+Num;
				}
				return Num;
	}
	
	//拼接类别的两个字符串；
	public static String StringAddString(String id){
		//为了拼接字符串使用；
		StringBuffer sb=new StringBuffer();
		sb.append("01");
		return id+sb.toString();
	}
	
	//拼接第一个商品的两个字符串；
		public static String StringAddString2(String id){
			//为了拼接字符串使用；
			StringBuffer sb=new StringBuffer();
			sb.append("001");
			return id+sb.toString();
		}
	
}
