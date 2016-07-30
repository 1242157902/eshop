package com.eshop.utils;

import java.util.Date;
import java.util.UUID;



/**
 * @author wzq
 *
 *version 1.0 2015-9-13 上午11:26:39
 */
public class UUIDUtil {

	public static  String  getuuid() {
		
       return UUID.randomUUID().toString().replace("-", "");
	
	}

}
