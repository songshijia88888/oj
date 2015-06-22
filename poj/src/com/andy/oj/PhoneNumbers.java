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
	 * Description: ��ҵϲ�������ױ���ס�ĵ绰���롣�õ绰�������ױ���ס��һ���취�ǽ���д��һ�����׼�ס�ĵ��ʻ��߶��
	 * ���磬����Ҫ������¬��ѧ��绰ʱ�����Բ���TUT-GLOP����ʱ��ֻ���绰�����в�������ƴд�ɵ��ʡ�
	 * �������ϻص��Ƶ꣬����ͨ������310-GINO����Gino's��һ��pizza��
	 * �õ绰�������ױ���ס����һ���취����һ�ֺüǵķ�ʽ�Ժ�������ֽ��з��顣
	 * ͨ�������ʤ�͵ġ�����ʮ������3-10-10-10������Դ��������ﶩpizza��
	 * 
	 * �绰����ı�׼��ʽ����λʮ�����������ڵ���������λ����֮����һ�����ӷ��� �绰�������ṩ�˴���ĸ�����ֵ�ӳ�䣬ӳ���ϵ���£� A, B, ��C
	 * ӳ�䵽 2 D, E, ��F ӳ�䵽 3 G, H, ��I ӳ�䵽 4 J, K, ��L ӳ�䵽 5 M, N, ��O ӳ�䵽 6 P, R,
	 * ��S ӳ�䵽 7 T, U, ��V ӳ�䵽 8 W, X, ��Y ӳ�䵽 9
	 * 
	 * Q��Zû��ӳ�䵽�κ����֣����ַ�����Ҫ���ţ�����������Ӻ�ɾ���� TUT-GLOP�ı�׼��ʽ��888-4567��
	 * 310-GINO�ı�׼��ʽ��310-4466��3-10-10-10�ı�׼��ʽ��310-1010��
	 * ���������������ͬ�ı�׼��ʽ����ô���Ǿ��ǵ�ͬ�ģ���ͬ�Ĳ��ţ�
	 * ��Ĺ�˾����Ϊ���صĹ�˾��дһ���绰���뱡����Ϊ�������Ƶ�һ���֣�����Ҫ����Ƿ��������Ͷ����˾ӵ����ͬ�� �绰���롣
	 * 
	 * Input: ����ĸ�ʽ�ǣ���һ����һ����������ָ���绰���뱡�к�������������100000�������µ�ÿ����һ���绰���롣
	 * ÿ���绰���������֣���д��ĸ������Q��Z���Լ����ӷ���ɡ�ÿ���绰������ֻ��պ���7�����ֻ�����ĸ��
	 * 
	 * Output: ����ÿ�������ظ��ĺ������һ�����������Ǻ���ı�׼��ʽ����һ���ո�Ȼ���������ظ�������������ڶ���ظ��ĺ��룬
	 * ���պ�����ֵ�����������������������û���ظ��ĺ��룬���һ�У� No duplicates.
	 * 
	 * Sample Input: 12 4873279 ITS-EASY 888-4567 3-10-10-10 888-GLOP TUT-GLOP
	 * 967-11-11 310-GINO F101010 888-1200 -4-8-7-3-2-7-9- 487-3279
	 * 
	 * Sample Output: 310-1010 2 487-3279 4 888-4567 3 Source
	 * 
	 * East Central North America 1999 Translator
	 * 
	 * ������ѧ�������ʵϰ2007
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
						s = sb.toString();//ֱ��ʹ��s�����滻�Ȳ�������POJ��ʱ��StringBufferЧ�ʸ߰���
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
					s1 = sb.toString();//ֱ��ʹ��s1�������롰-���Ĳ�������POJ��ʱ��StringBufferЧ�ʸ߰���
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