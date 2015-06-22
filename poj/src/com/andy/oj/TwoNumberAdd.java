package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoNumberAdd {
	public static void main(String[] args) throws IOException{
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		String s=null;
		s=bf.readLine();
		String[] ss = s.split(" ");
		int a = Integer.parseInt(ss[0]);
		int b = Integer.parseInt(ss[1]);
		System.out.println(a + b);
	}
}
