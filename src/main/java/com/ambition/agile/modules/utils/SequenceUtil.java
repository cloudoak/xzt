package com.ambition.agile.modules.utils;

import java.util.Random;

public class SequenceUtil {
	
	public static String generateSeq(){
		String ret = "";
		long currentTimeMillis = System.currentTimeMillis();
		Random random = new Random();
		int nextInt = random.nextInt();
		ret = currentTimeMillis + nextInt + "";
		return ret;
	}
	
	public static void main(String[] args) {
		System.out.println(generateSeq());
		
	}
	
}
