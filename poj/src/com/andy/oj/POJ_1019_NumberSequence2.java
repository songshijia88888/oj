package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class POJ_1019_NumberSequence2 {
	// 解法说明：
	// 1.用方法getDigitsOfssk(k)找到长度刚刚小于目标值的ssk.
	// 2.将k值拿到后，计算ssk的长度比目标值小的量m.
	// 3.用方法getDigitsOfsk(k1)找到长度刚刚小于m的sk1.
	// 4.将k1值拿到后，计算sk1的长度比m小的量n.
	// 5.整数k1 + 1中，第n个数字就是结果！
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int cases = Integer.parseInt(s);
		int count = 0;
		StringBuffer sb = new StringBuffer();
		while(count < cases){
			s = br.readLine();
			count++;
			
			BigInteger target = new BigInteger(s);
			int i = 1;
			while(getDigitsOfssk(i).compareTo(target) < 0){
				i = i + 1;
			}
			long target2 = target.subtract(getDigitsOfssk(i - 1)).longValue();
			
			int i2 = 1;
			while(getDigitsOfsk(i2) < target2){
				i2 = i2 + 1;
			}
			int n = (int)(target2 - getDigitsOfsk(i2 - 1));
			String k1_1  = String.valueOf(i2);
			
			sb.append(k1_1.charAt(n - 1));
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	
	public static BigInteger getDigitsOfssk(long k){
		long d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0;
		if(k >= 1 && k <= 9){
			d1 = k * (k + 1);
		}
		if(k >= 10 && k <= 99){
			d1 = k * (k + 1);
			d2 = (k - 8) * (k - 9);
		}
		if(k >= 100 && k <= 999){
			d1 = k * (k + 1);
			d2 = (k - 8) * (k - 9);
			d3 = (k - 98) * (k - 99);
		}
		if(k >= 1000 && k <= 9999){
			d1 = k * (k + 1);
			d2 = (k - 8) * (k - 9);
			d3 = (k - 98) * (k - 99);
			d4 = (k - 998) * (k - 999);
		}
		if(k >= 10000 && k <= 99999){
			d1 = k * (k + 1);
			d2 = (k - 8) * (k - 9);
			d3 = (k - 98) * (k - 99);
			d4 = (k - 998) * (k - 999);
			d5 = (k - 9998) * (k - 9999);
		}
		
		BigInteger b1 = new BigInteger(String.valueOf(d1));
		BigInteger b2 = new BigInteger(String.valueOf(d2));
		BigInteger b3 = new BigInteger(String.valueOf(d3));
		BigInteger b4 = new BigInteger(String.valueOf(d4));
		BigInteger b5 = new BigInteger(String.valueOf(d5));
		
		return b1.add(b2).add(b3).add(b4).add(b5).divide(new BigInteger("2"));
	}
	
	
	public static long getDigitsOfsk(long k){
		
		if(k >= 1 && k <= 9){
			return k;
		}
		if(k >= 10 && k <= 99){
			return 2 * k - 9;
		}
		if(k >= 100 && k <= 999){
			return 3 * k - 108;
		}
		if(k >= 1000 && k <= 9999){
			return 4 * k - 1107;
		}
		if(k >= 10000 && k <= 99999){
			return 5 * k - 11106;
		}
		
		return -1;
	}
}
