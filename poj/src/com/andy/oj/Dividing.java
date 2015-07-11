package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Dividing {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		StringBuffer sb = new StringBuffer();
		String s = br.readLine();
		int count = 1;
		while (!"0 0 0 0 0 0".equals(s)) {
			String[] ss = s.split(" ");
			for (int i = 0; i < ss.length; i++) {
				int temp = Integer.parseInt(ss[i]);
				if (temp % 2 != 0) {
					stack.push(i + 1);
					stack2.push(temp);
				}
			}
			int max = 0;
			if (!stack.isEmpty()) {
				max = stack.pop() * stack2.pop();
			}

			int sum = 0;

			while (!stack.isEmpty()) {
				sum = sum + stack.pop() * stack2.pop();
			}
			if (sum == max) {
				sb.append("Collection #").append(count)
						.append(":\nCan be divided.").append("\n");
			} else {
				for (int i = 0; i < ss.length; i++) {
					int temp = Integer.parseInt(ss[i]);
					if (temp % 2 != 0) {
						stack.push(i + 1);
					}
				}
				max = 0;
				if (!stack.isEmpty()) {
					max = stack.pop();
				}

				sum = 0;

				while (!stack.isEmpty()) {
					sum = sum + stack.pop();
				}

				if (sum == max) {
					sb.append("Collection #").append(count)
							.append(":\nCan be divided.").append("\n");
				} else {
					sb.append("Collection #").append(count)
							.append(":\nCan't be divided.").append("\n");
				}
			}
			count++;
			s = br.readLine();
		}

		System.out.println(sb.toString());
	}
}
