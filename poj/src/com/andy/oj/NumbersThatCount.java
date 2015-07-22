package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class NumbersThatCount {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		long n = Long.parseLong(s);
		List<Long> list = new LinkedList<Long>();
		while (n != -1) {
			long n1 = n;
			int count = 0;
			while (count < 15) {
				long in = inventory(n);
				count++;
				if (n == in && count == 1) {
					System.out.println(n1 + " is self-inventorying");
					break;
				} else if (n == in && count > 1) {
					System.out.println(n1 + " is self-inventorying after "
							+ (count - 1) + " steps");
					break;
				} else {
					if (list.contains(in)) {
						// System.out.println(list + ":");
						System.out.println(n1
								+ " enters an inventory loop of length "
								+ (list.size() - list.indexOf(in)));
						break;
					}
					list.add(in);
				}

				n = in;
			}

			if (count == 15) {
				System.out.println(n1
						+ " can not be classified after 15 iterations");
			}
			s = br.readLine();
			n = Long.parseLong(s);
			list.clear();
		}
	}

	public static long inventory(long n) {
		int[] digits = new int[10];
		String s1 = String.valueOf(n);
		for (int i = 0; i < s1.length() - 1; i++) {
			int digit = Integer.parseInt(s1.substring(i, i + 1));
			digits[digit]++;
		}
		int digit = Integer.parseInt(s1.substring(s1.length() - 1));
		digits[digit]++;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			if (digits[i] != 0) {
				sb.append(digits[i]).append(i);
			}
		}

		return Long.parseLong(sb.toString());
	}
}
