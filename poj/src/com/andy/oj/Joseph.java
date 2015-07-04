package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Joseph {
	/*
	 * 										Joseph 
	 * Time Limit: 1000MS 			Memory Limit: 10000K 
	 * Total Submissions: 50046 Accepted: 19012 
	 * 
	 * Description£º
	 * The Joseph's problem is notoriously known. For those who are not familiar
	 * with the original problem: from among n people, numbered 1, 2, . . ., n,
	 * standing in circle every mth is going to be executed and only the life of
	 * the last remaining person will be saved. Joseph was smart enough to
	 * choose the position of the last remaining person, thus saving his life to
	 * give us the message about the incident. For example when n = 6 and m = 5
	 * then the people will be executed in the order 5, 4, 6, 2, 3 and 1 will be
	 * saved.
	 * Suppose that there are k good guys and k bad guys. In the circle the
	 * first k are good guys and the last k bad guys. You have to determine such
	 * minimal m that all the bad guys will be executed before the first good
	 * guy.
	 * 
	 * Input£º
	 * The input file consists of separate lines containing k. The last line in
	 * the input file contains 0. You can suppose that 0 < k < 14. 
	 * 
	 * Output£º
	 * The output file will consist of separate lines containing m corresponding
	 * to k in the input file. 
	 * 
	 * Sample Input£º
	 * 3 
	 * 4 
	 * 0
	 * 
	 * Sample Output:
	 * 5 
	 * 30
	 * 
	 * Source£º
	 * Central Europe 1995
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> mapresult = new HashMap<Integer, Integer>();
		StringBuffer sb = new StringBuffer();

		String s = br.readLine();
		int k = 0;
		int m = 0;
		int index = 0;
		while (!"0".equals(s)) {
			k = Integer.parseInt(s);
			if (mapresult.containsKey(k)) {
				sb.append(mapresult.get(k)).append("\n");
				s = br.readLine();
				continue;
			}

			m = k;
			index = 0;
			int size = 2 * k;
			int varysize = 2 * k;

			while (varysize > k) {
				index = (m + index) % varysize;
				if (index > k - 1) {
					varysize--;
				} else {
					m++;
					index = 0;
					varysize = size;
				}
			}
			sb.append(m + 1).append("\n");
			mapresult.put(k, m + 1);
			s = br.readLine();
		}
		System.out.println(sb.toString());
	}
}
