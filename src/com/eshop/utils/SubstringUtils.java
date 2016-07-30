package com.eshop.utils;


/**
 * 处理商品图片的路径工具
 * @author Jane
 *
 */
public class SubstringUtils {

	public static String subString(String store){
		
		return store.substring(store.indexOf("/upload"),store.length());
		
	/*	//JAVA中正则表达式,用"\\\\"表示"\";;;java 文件路径 反斜杠转为斜杠
		storeFile= storeFile.replaceAll("\\\\", "/");*/
	}
}
