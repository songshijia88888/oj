package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class POJ_1019_NumberSequence3 {
	/*
	 * 			Number Sequence
	 * 	Time Limit: 1000MS 		 Memory Limit: 10000K 
	 * 	Total Submissions: 36088 Accepted: 10426
	 * 
	 * Description
	 * 
	 * A single positive integer i is given. Write a program to find the digit
	 * located in the position i in the sequence of number groups S1S2...Sk.
	 * Each group Sk consists of a sequence of positive integer numbers ranging
	 * from 1 to k, written one after another. For example, the first 80 digits
	 * of the sequence are as follows:
	 * 11212312341234512345612345671234567812345678912345678910123456789101112345678910
	 * 
	 * Input
	 * 
	 * The first line of the input file contains a single integer t (1 ≤ t ≤
	 * 10), the number of test cases, followed by one line for each test case.
	 * The line for a test case contains the single integer i (1 ≤ i ≤
	 * 2147483647)
	 * 
	 * Output
	 * 
	 * There should be one output line per test case containing the digit
	 * located in the position i.
	 * 
	 * Sample Input 2 8 3
	 * 
	 * Sample Output 2 2
	 * 
	 * Source
	 * 
	 * Tehran 2002, First Iran Nationwide Internet Programming Contest
	 */

	// 解法说明：
	// 1.用方法getDigitsOfssk(k)找到长度刚刚小于目标值的ssk.
	// 2.将k值拿到后，计算ssk的长度比目标值小的量m.
	// 3.用方法getDigitsOfsk(k1)找到长度刚刚小于m的sk1.
	// 4.将k1值拿到后，计算sk1的长度比m小的量n.
	// 5.整数k1 + 1中，第n个数字就是结果！
	// 6.特别注意两次计算k k1值时刚刚相等的情况
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int cases = Integer.parseInt(s);
		int count = 0;
		StringBuffer sb = new StringBuffer();
		while (count < cases) {
			s = br.readLine();
			count++;

			BigInteger target = new BigInteger(s);

			int k = 1;
			while (getDigitsOfssk(k).compareTo(target) <= 0) {
				k = k + 1;
			}
			if (getDigitsOfssk(k - 1).compareTo(target) == 0) {
				String re = String.valueOf(k - 1);
				int n = re.length();
				sb.append(re.charAt(n - 1));
				sb.append("\n");
				continue;
			}
			long target2 = target.subtract(getDigitsOfssk(k - 1)).longValue();

			int k2 = 1;
			while (getDigitsOfsk(k2) <= target2) {
				k2 = k2 + 1;
			}

			int n = (int) (target2 - getDigitsOfsk(k2 - 1));
			if (n > 0) {
				String afterk2 = String.valueOf(k2);
				sb.append(afterk2.charAt(n - 1));
			} else {
				String beforek2 = String.valueOf(k2 - 1);
				n = beforek2.length();
				sb.append(beforek2.charAt(n - 1));
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	public static BigInteger getDigitsOfssk(long k) {
		long d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0;
		if (k >= 1 && k <= 9) {
			d1 = k * (k + 1);
		}
		if (k >= 10 && k <= 99) {
			d1 = k * (k + 1);
			d2 = (k - 8) * (k - 9);
		}
		if (k >= 100 && k <= 999) {
			d1 = k * (k + 1);
			d2 = (k - 8) * (k - 9);
			d3 = (k - 98) * (k - 99);
		}
		if (k >= 1000 && k <= 9999) {
			d1 = k * (k + 1);
			d2 = (k - 8) * (k - 9);
			d3 = (k - 98) * (k - 99);
			d4 = (k - 998) * (k - 999);
		}
		if (k >= 10000 && k <= 99999) {
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

	public static long getDigitsOfsk(long k) {

		if (k >= 1 && k <= 9) {
			return k;
		}
		if (k >= 10 && k <= 99) {
			return 2 * k - 9;
		}
		if (k >= 100 && k <= 999) {
			return 3 * k - 108;
		}
		if (k >= 1000 && k <= 9999) {
			return 4 * k - 1107;
		}
		if (k >= 10000 && k <= 99999) {
			return 5 * k - 11106;
		}

		return -1;
	}
}
