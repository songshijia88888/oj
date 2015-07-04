package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CounterfeitDollar {
	public static void main(String[] args) throws IOException {
		List<Character> trueCoins = new LinkedList<Character>();
		List<Character> unbalanceCoins = new LinkedList<Character>();
		String condition = "";
		StringBuffer result = new StringBuffer();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = br.readLine();
		int n = Integer.parseInt(inputLine);
		int count = 0;
		while (count < 3 * n) {
			inputLine = br.readLine();
			count++;
			String[] ss = inputLine.split(" ");
			//Thread.sleep(500);
			//System.out.println("\ninputLine:" + inputLine);
			if ("even".equals(ss[2])) {
				char[] chs = ss[0].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					trueCoins.add(chs[i]);
				}
				chs = ss[1].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					trueCoins.add(chs[i]);
				}
				//System.out.println("trueCoins:" + trueCoins);
			} else {
				condition = inputLine;
				char[] chs = ss[0].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					unbalanceCoins.add(chs[i]);
				}
				chs = ss[1].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					unbalanceCoins.add(chs[i]);
				}
				//System.out.println("unbalanceCoins" + unbalanceCoins);
			}//读入数据，将真的金币放入trueCoins链表，将不平衡的币组放入unbalanceCoins.
			
			if (count % 3 == 0) {
				//System.out.println("True:" + trueCoins);
				//System.out.println("Unbanlance:" + unbalanceCoins);
				Random rd = new Random();
				while (unbalanceCoins.size() > 1) {
					int index = rd.nextInt(unbalanceCoins.size());
					if (trueCoins.contains(unbalanceCoins.get(index))) {
						unbalanceCoins.remove(index);
						//System.out.println("\tunbalanceCoins" + unbalanceCoins);
					}
				}
				//System.out.println("\tUnbanlance:" + unbalanceCoins);
				char counterfeit = unbalanceCoins.get(0);
				String s1 = String.valueOf(counterfeit);
				int counterfeitIndex = condition.indexOf(s1);
				if (condition.charAt(10) == 'u') {
					if (counterfeitIndex > 3) {
						result.append(counterfeit).append(
								" is the counterfeit coin and it is light.");
					} else {
						result.append(counterfeit).append(
								" is the counterfeit coin and it is heavy.");
					}
				} else {
					if (counterfeitIndex > 3) {
						result.append(counterfeit).append(
								" is the counterfeit coin and it is heavy.");
					} else {
						result.append(counterfeit).append(
								" is the counterfeit coin and it is light.");
					}
				}
				result.append("\n");
				trueCoins.clear();
				unbalanceCoins.clear();
			}
		}
		System.out.println(result.toString());
	}
}
