package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Sticks {
	private static List<Integer> list = new LinkedList<Integer>();
	private static Stack<Integer> stack = new Stack<Integer>();
	private static String result = "";
	private static int count = 0;
	private static int digit = 0;
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ss = null;
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		

		while (!"0".equals(s)) {
			int part = Integer.parseInt(s);
			if (part > 64) {
				br.readLine();
				s = br.readLine();
				continue;
			}
			s = br.readLine();
			ss = s.split(" ");
			if (part != ss.length) {
				s = br.readLine();
				continue;
			}
			int maxlen = 0;
			int minlen = 50;
			int sum = 0;
			int[] lens = new int[ss.length];
			for (int i = 0; i < ss.length; i++) {
				lens[i] = Integer.parseInt(ss[i]);
				if(lens[i] % 10 != 0){
					list.add(lens[i] % 10);
				}
				
				sum = sum + lens[i];
				if (lens[i] < minlen) {
					minlen = lens[i];
				}
				if (lens[i] > maxlen) {
					maxlen = lens[i];
				}
			}
			
			if (maxlen > 50) {
				s = br.readLine();
				continue;
			}

			if (minlen < 0) {
				s = br.readLine();
				continue;
			}
			while (sum % maxlen != 0) {
				maxlen++;
			}
			
			List<Integer> d10 = new LinkedList<Integer>();
			System.out.println("number:");
			for(int i = 0; i < lens.length; i++){
				System.out.print(lens[i] + " ");
				d10.add(lens[i] / 10);
			}
			System.out.println("\nd1:" + list);
			System.out.println("d10:" + d10);
			sum = 0;
			for(int i = 0; i < list.size(); i++){
				sum = sum + list.get(i);
			}
			System.out.println("sum d1:" + sum);
			sum = 0;
			for(int i = 0; i < d10.size(); i++){
				sum = sum + d10.get(i);
			}
			System.out.println("sum d10:" + sum);
			//maxlen = 454;
			digit = maxlen % 10;
			//combine();

			s = br.readLine();
			//sb.append(maxlen + "\n");
		}

		//System.out.println(sb.toString());
	}
	
	public static void combine() throws InterruptedException{
		int sum = 0;
		int part = 0;
		int adjust = 0;
		while(!list.isEmpty()){
			sum = digit + 10;
			//part = 9;
			part = 9 + adjust;
			while(sum > 0 && part > 0){
				if(list.contains(part)){
					stack.push(part);
					sum = (sum - part) % 10;
					list.remove((Integer)part);
					part = sum;
				}
				else{
					part--;
				}
			}
			if(sum == 0){
				while(!stack.empty()){
					result = result + stack.pop() + " ";
				}
				result = result.trim() + "\n";
				count++;
			}
			else{
				while(!stack.empty()){
					list.add(stack.pop());
				}
				adjust--;
			}
			
			if( 9 + adjust < 0){
				break;
			}
			
			Collections.sort(list);
			System.out.println(list);
			Thread.sleep(1000);
		}
		System.out.println("\ncombinations:\n" + result);
		System.out.println("number of combination:\n" + count);
		System.out.println(list);
	}
}
