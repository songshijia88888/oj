package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CounterfeitDollar2 {
	/*
	 * 						Counterfeit Dollar 
	 * Time Limit: 1000MS 			Memory Limit: 10000K Total
	 * Submissions: 41674 			Accepted: 13273 
	 * 
	 * Description:
	 * Sally Jones has a dozen Voyageur silver dollars. However, only eleven of
	 * the coins are true silver dollars; one coin is counterfeit even though
	 * its color and size make it indistinguishable from the real silver
	 * dollars. The counterfeit coin has a different weight from the other coins
	 * but Sally does not know if it is heavier or lighter than the real coins.
	 * Happily, Sally has a friend who loans her a very accurate balance scale.
	 * The friend will permit Sally three weighings to find the counterfeit
	 * coin. For instance, if Sally weighs two coins against each other and the
	 * scales balance then she knows these two coins are true. Now if Sally
	 * weighs one of the true coins against a third coin and the scales do not
	 * balance then Sally knows the third coin is counterfeit and she can tell
	 * whether it is light or heavy depending on whether the balance on which it
	 * is placed goes up or down, respectively. 
	 * By choosing her weighings carefully, Sally is able to ensure that she will find the counterfeit
	 * coin with exactly three weighings. 
	 * 
	 * Input:
	 * The first line of input is an integer n (n > 0) specifying the number of
	 * cases to follow. Each case consists of three lines of input, one for each
	 * weighing. Sally has identified each of the coins with the letters A--L.
	 * Information on a weighing will be given by two strings of letters and
	 * then one of the words ``up'', ``down'', or ``even''. The first string of
	 * letters will represent the coins on the left balance; the second string,
	 * the coins on the right balance. (Sally will always place the same number
	 * of coins on the right balance as on the left balance.) The word in the
	 * third position will tell whether the right side of the balance goes up,
	 * down, or remains even. 
	 * 
	 * Output:
	 * For each case, the output will identify the counterfeit coin by its
	 * letter and tell whether it is heavy or light. The solution will always be
	 * uniquely determined.
	 * 
	 *  Sample Input:
	 * 1
	 *  ABCD EFGH even
	 *  ABCI EFJK up 
	 *  ABIJ EFGH even
	 *  
	 * Sample Output:
	 * K is the counterfeit coin and it is light. 
	 * 
	 * Source:
	 * East Central North America 1998
	 */

	public static void main(String[] args) throws IOException {
		Map<Character, Integer> upMap = new HashMap<Character, Integer>();
		Map<Character, Integer> downMap = new HashMap<Character, Integer>();
		List<Character> evenList = new LinkedList<Character>();
		List<Character> targetList = new LinkedList<Character>();
		StringBuffer result = new StringBuffer();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ss = null;
		String s = br.readLine();
		int n = Integer.parseInt(s);
		int count = 0;
		int downs = 0;
		while (count < 3 * n) {
			s = br.readLine();
			count++;
			ss = s.split(" ");
			if ("even".equals(ss[2])) {
				char[] chs = ss[0].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					evenList.add(chs[i]);
				}
				chs = ss[1].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					evenList.add(chs[i]);
				}
			} else if ("down".equals(ss[2])) {
				downs++;
				char[] chs = ss[1].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					if (downMap.get(chs[i]) == null) {
						downMap.put(chs[i], 1);
					} else {
						downMap.put(chs[i], downMap.get(chs[i]) + 1);
					}
				}
				chs = ss[0].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					if (upMap.get(chs[i]) == null) {
						upMap.put(chs[i], 1);
					} else {
						upMap.put(chs[i], upMap.get(chs[i]) + 1);
					}
				}
			} else {
				downs++;
				char[] chs = ss[0].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					if (downMap.get(chs[i]) == null) {
						downMap.put(chs[i], 1);
					} else {
						downMap.put(chs[i], downMap.get(chs[i]) + 1);
					}
				}
				chs = ss[1].toCharArray();
				for (int i = 0; i < chs.length; i++) {
					if (upMap.get(chs[i]) == null) {
						upMap.put(chs[i], 1);
					} else {
						upMap.put(chs[i], upMap.get(chs[i]) + 1);
					}
				}
			}

			if (count % 3 == 0) {
				Set<Character> set = downMap.keySet();
				for (Iterator<Character> ite = set.iterator(); ite.hasNext();) {
					Character cha = ite.next();
					if (downMap.get(cha).intValue() == downs) {
						targetList.add(cha);
					}
				}
				set = upMap.keySet();
				for (Iterator<Character> ite = set.iterator(); ite.hasNext();) {
					Character cha = ite.next();
					if (upMap.get(cha).intValue() == downs) {
						targetList.add(cha);
					}
				}
				Random rd = new Random();
				while (targetList.size() > 1) {
					int index = rd.nextInt(targetList.size());
					if (evenList.contains(targetList.get(index))) {
						targetList.remove(index);
					}
				}
				Character counterfeit = targetList.get(0);
				if (downMap.containsKey(counterfeit)) {
					result.append(counterfeit).append(
							" is the counterfeit coin and it is heavy.");
				} else {
					result.append(counterfeit).append(
							" is the counterfeit coin and it is light.");
				}

				result.append("\n");
				downMap.clear();
				upMap.clear();
				evenList.clear();
				targetList.clear();
				downs = 0;
			}
		}

		System.out.println(result.toString());
	}
}
