package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sticks {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ss = null;
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		while(!"0".equals(s)){
			int part = Integer.parseInt(s);
			
			if(part > 64){
				br.readLine();
				s = br.readLine();
				continue;
			}
			s = br.readLine();
			ss = s.split(" ");
			if(part != ss.length){
				s = br.readLine();
				continue;
			}
			int maxlen = 0;
			int minlen = 50;
			int sum = 0;
			int sum2 = 0;
			int[] lens = new int[ss.length];
			int[] factor2 = new int[ss.length];
			for(int i = 0; i < ss.length; i++){
				lens[i] = Integer.parseInt(ss[i]);
				factor2[i] = lens[i] % 10;
				sum = sum + lens[i];
				sum2 = sum2 + factor2[i];
				if(lens[i] < minlen){
					minlen = lens[i];
				}
				if(lens[i] > maxlen){
					maxlen = lens[i];
				}
			}
			if(maxlen > 50){
				s = br.readLine();
				continue;
			}
			
			if(minlen < 0){
				s = br.readLine();
				continue;
			}
			int maxlen2 = maxlen;
			sb2.append(s + "\n");
			while(maxlen2 <= sum){
				if(sum % maxlen2 == 0){
					sb2.append(maxlen2 + " ");
				}
				maxlen2++;
			}
			while(sum % maxlen != 0){
				maxlen++;
			}
			
			System.out.println("--------------------------------");
			
			System.out.println("L:" + maxlen + ",N:" + sum / maxlen);
			System.out.println("sum2:" + sum2);
			System.out.println("--------------------------------");
			
			s = br.readLine();
			sb2.append("\n");
			sb.append(maxlen + "\n");
		}
		
		System.out.println(sb.toString());
		System.out.println(sb2.toString());
	}
}
