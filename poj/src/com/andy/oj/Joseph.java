package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Joseph {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> listsrc = new LinkedList<Integer>();
		List<Integer> listtemp = new LinkedList<Integer>();
		Map<Integer, Integer> mapresult = new HashMap<Integer, Integer>();
		StringBuffer sb = new StringBuffer();
		String s = br.readLine();
		int k = 0;
		int m = 0;
		int index = 0;
		while(!"0".equals(s)){
			listsrc.clear();
			listtemp.clear();
			k = Integer.parseInt(s);
			if(mapresult.containsKey(k)){
				sb.append(mapresult.get(k)).append("\n");
				s = br.readLine();
				continue;
			}
			for(int i = 0; i < 2 * k; i++){
				listsrc.add(i);
			}
			
			listtemp.addAll(listsrc);

			m = k;
			index = 0;
			while (listtemp.size() > k) {
				index = (m + index) % listtemp.size();
				if (index > k - 1) {
					listtemp.remove(index);
				} else {
					m++;
					index = 0;
					listtemp.clear();
					listtemp.addAll(listsrc);
				}
			}
			sb.append(m + 1).append("\n");
			mapresult.put(k, m + 1);
			s = br.readLine();
		}
		System.out.println(sb.toString());
	}
}
