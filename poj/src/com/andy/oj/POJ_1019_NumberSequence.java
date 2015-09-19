package com.andy.oj;

import java.math.BigInteger;

public class POJ_1019_NumberSequence {
	public static void main(String[] args){
		System.out.println(getDigitsOfssk(999));
		int k = 1;
		StringBuffer sk = new StringBuffer();
		sk.append(k);
		StringBuffer ssk = new StringBuffer();
		ssk.append(sk.toString());
		while(k < 999){
			k++;
			sk.append(k);
			ssk.append(sk.toString());
		}
		
		System.out.println(ssk.toString().length());
		
		
		//the digit at 2147483647
		BigInteger target = new BigInteger("2147483647");
		int i = 1;
		while(getDigitsOfssk(i).compareTo(target) < 0){
			i = i + 1;
		}
		System.out.println(i);
		
		System.out.println(getDigitsOfssk(31267));
		System.out.println("2147483647");
		System.out.println(getDigitsOfssk(31268));
		
		//the digit at 105170
		int i2 = 1;
		while(getDigitsOfsk(i2) < 105170){
			i2 = i2 + 1;
		}
		System.out.println(i2);
		
		System.out.println(getDigitsOfsk(23255));
		System.out.println(105170);
		System.out.println(getDigitsOfsk(23256));
		
		//the result is 2.
		//解法说明：
		//1.用方法getDigitsOfssk(k)找到长度刚刚小于目标值的ssk.
		//2.将k值拿到后，计算ssk的长度比目标值小的量m.
		//3.用方法getDigitsOfsk(k1)找到长度刚刚小于m的sk1.
		//4.将k1值拿到后，计算sk1的长度比m小的量n.
		//5.整数k1 + 1中，第n个数字就是结果！
		
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
