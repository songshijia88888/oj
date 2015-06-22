package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PhoneNumbers {
	/*
	 * Description: 企业喜欢用容易被记住的电话号码。让电话号码容易被记住的一个办法是将它写成一个容易记住的单词或者短语。
	 * 例如，你需要给滑铁卢大学打电话时，可以拨打TUT-GLOP。有时，只将电话号码中部分数字拼写成单词。
	 * 当你晚上回到酒店，可以通过拨打310-GINO来向Gino's订一份pizza。
	 * 让电话号码容易被记住的另一个办法是以一种好记的方式对号码的数字进行分组。
	 * 通过拨打必胜客的“三个十”号码3-10-10-10，你可以从他们那里订pizza。
	 * 
	 * 电话号码的标准格式是七位十进制数，并在第三、第四位数字之间有一个连接符。 电话拨号盘提供了从字母到数字的映射，映射关系如下： A, B, 和C
	 * 映射到 2 D, E, 和F 映射到 3 G, H, 和I 映射到 4 J, K, 和L 映射到 5 M, N, 和O 映射到 6 P, R,
	 * 和S 映射到 7 T, U, 和V 映射到 8 W, X, 和Y 映射到 9
	 * 
	 * Q和Z没有映射到任何数字，连字符不需要拨号，可以任意添加和删除。 TUT-GLOP的标准格式是888-4567，
	 * 310-GINO的标准格式是310-4466，3-10-10-10的标准格式是310-1010。
	 * 如果两个号码有相同的标准格式，那么他们就是等同的（相同的拨号）
	 * 你的公司正在为本地的公司编写一个电话号码薄。作为质量控制的一部分，你想要检查是否有两个和多个公司拥有相同的 电话号码。
	 * 
	 * Input: 输入的格式是，第一行是一个正整数，指定电话号码薄中号码的数量（最多100000）。余下的每行是一个电话号码。
	 * 每个电话号码由数字，大写字母（除了Q和Z）以及连接符组成。每个电话号码中只会刚好有7个数字或者字母。
	 * 
	 * Output: 对于每个出现重复的号码产生一行输出，输出是号码的标准格式紧跟一个空格然后是它的重复次数。如果存在多个重复的号码，
	 * 则按照号码的字典升序输出。如果输入数据中没有重复的号码，输出一行： No duplicates.
	 * 
	 * Sample Input: 12 4873279 ITS-EASY 888-4567 3-10-10-10 888-GLOP TUT-GLOP
	 * 967-11-11 310-GINO F101010 888-1200 -4-8-7-3-2-7-9- 487-3279
	 * 
	 * Sample Output: 310-1010 2 487-3279 4 888-4567 3 Source
	 * 
	 * East Central North America 1999 Translator
	 * 
	 * 北京大学程序设计实习2007
	 */

	private static Map<String, String> map = new TreeMap<String, String>();
	private static Map<String, Integer> resultmap = new TreeMap<String, Integer>();
	static {
		map.put("A", "2");
		map.put("B", "2");
		map.put("C", "2");
		map.put("D", "3");
		map.put("E", "3");
		map.put("F", "3");
		map.put("G", "4");
		map.put("H", "4");
		map.put("I", "4");
		map.put("J", "5");
		map.put("K", "5");
		map.put("L", "5");
		map.put("M", "6");
		map.put("N", "6");
		map.put("O", "6");
		map.put("P", "7");
		map.put("R", "7");
		map.put("S", "7");
		map.put("T", "8");
		map.put("U", "8");
		map.put("V", "8");
		map.put("W", "9");
		map.put("X", "9");
		map.put("Y", "9");
	}

	public static void main(String[] args) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			String s = null;
			String s1 = null;
			String sn = null;
			String nu = null;
			StringBuffer sb = null;
			int count = 0;
			int max = 0;
			char[] cs = new char[1];
			while (true) {
				s = bf.readLine();
				
				if (count == 0) {
					max = Integer.parseInt(s);
				} else {
					if (s.contains("-")) {
						s = s.replaceAll("-", "");
					}

					if (s.matches(".*?[A-Z]+.*?")) {
						sb = new StringBuffer(s);
						for (int i = 0; i < s.length(); i++) {
							cs[0] = sb.charAt(i);
							sn = new String(cs);
							if (null != map.get(sn)) {
								nu = map.get(sn);
								sb.replace(i, i + 1, nu);							}
						}
						s = sb.toString();//直接使用s来做替换等操作，在POJ超时，StringBuffer效率高啊！
					}
					if (null != resultmap.get(s)) {
						resultmap.put(s, resultmap.get(s) + 1);
					} else {
						resultmap.put(s, 1);
					}
					//System.out.println(resultmap);
				}
				count++;
				if (count > max) {
					break;
				}
			}
			boolean duplicates = false;
			
			Set<String> set = resultmap.keySet();
			
			for (Iterator<String> ite = set.iterator();ite.hasNext();) {
				s1 = ite.next();
				Integer nus = resultmap.get(s1);
				if (nus >= 2) {
					sb = new StringBuffer(s1);
					sb.insert(3, '-');
					s1 = sb.toString();//直接使用s1来做插入“-”的操作，在POJ超时，StringBuffer效率高啊！
					System.out.println(s1 + " " + nus);
					duplicates = true;
				}

			}
			if (!duplicates) {
				System.out.println("No duplicates.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}