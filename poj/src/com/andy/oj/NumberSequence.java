package com.andy.oj;

import java.math.BigInteger;

public class NumberSequence {
	public static void main(String[] args){
		System.out.println(getDigitsNumbers(9));
		
		
	}
	
	
	public static String getDigitsNumbers(int k){
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
		
		return b1.add(b2).add(b3).add(b3).add(b4).add(b5).divide(new BigInteger("2")).toString();
	}
}
