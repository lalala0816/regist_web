package com.hitsz.utils;

import java.util.UUID;

/**
 * ��������ַ����Ĺ�����
 * @author Dell
 *
 */
public class UUIDUtils {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
