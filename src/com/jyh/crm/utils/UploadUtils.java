package com.jyh.crm.utils;

import java.util.UUID;

/**
  * �ļ��ϴ��Ĺ�����
 * @author jt
 *
 */
public class UploadUtils {
	/**
	  * ���Ŀ¼���ļ����ظ�������
	 * @param fileName
	 * @return
	 */
	public static String getUuidFileName(String fileName){
		int idx = fileName.lastIndexOf("."); // aa.txt
		String extions = fileName.substring(idx);
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	
	/**
	  * Ŀ¼����ķ���
	 * @param args
	 */
	public static String getPath(String uuidFileName){
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf; // ��Ϊһ��Ŀ¼
		int code2 = code1 >>> 4;
		int d2 = code2 & 0xf; // ��Ϊ����Ŀ¼
		return "/"+d1+"/"+d2;
	}
	
	public static void main(String[] args) {
		System.out.println(getUuidFileName("aa.txt"));
	}
}
