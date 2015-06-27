package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EdgeDetection {
	private static int column;
	private static int row;
	private static int[][] pic;

	public static int getEdges(int m, int n) {
		int rs = m - 1 > 0 ? m - 1 : 0;
		int re = m + 1 > EdgeDetection.row - 1 ? EdgeDetection.row - 1 : m + 1;
		int cs = n - 1 > 0 ? n - 1 : 0;
		int ce = n + 1 > EdgeDetection.column - 1 ? EdgeDetection.column - 1
				: n + 1;
		int max = 0;

		for (int i = rs; i <= re; i++) {
			for (int j = cs; j <= ce; j++) {
				if (i == m && j == n) {
					continue;
				}
				if (Math.abs(pic[m][n] - pic[i][j]) > max) {
					max = Math.abs(pic[m][n] - pic[i][j]);
				}
			}
		}// 这两个循环的起始条件浪费了我近一个小时时间，faint!!!!

		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> list = new LinkedList<String>();
		StringBuffer sb = new StringBuffer();
		String s = null;
		while (!"0".equals(s)) {
			s = br.readLine();
			int column = Integer.parseInt(s);
			while (!"0 0".equals(s)) {
				s = br.readLine();
				list.add(s);
			}// 读完一幅图片的所有数据

			int pixels = 0;
			List<Integer> jumplist = new LinkedList<Integer>();
			for (int i = 0; i < list.size(); i++) {
				int thispixel = Integer.parseInt(list.get(i).split(" ")[1]);
				if (thispixel / column >= 7) {
					jumplist.add(i);
					jumplist.add((pixels % column + pixels) / column + 2);
				}
				pixels = pixels + thispixel;
				if (thispixel / column >= 7) {
					jumplist.add((pixels % column + pixels) / column - 2);
				}
			}
			
			Map<Integer, Integer> jumpmap = new HashMap<Integer, Integer>();
			if(!jumplist.isEmpty()){
				int i = 0;
				System.out.println(jumplist);
				while(i < jumplist.size() - 1){
					int listindex = jumplist.get(i);
					int rows = jumplist.get(i + 2) - jumplist.get(i + 1);
					jumpmap.put(listindex, rows * column);
					pixels = pixels - rows * column;
					i = i + 3;
				}
							
							
			}



			
			int row = pixels / column;
			int[][] pic = new int[row][column];
			int filledpixels = 0;
			int pixels2 = 0;
			int pixelsvalue = 0;
			int listindex = 0;

			while (filledpixels < pixels) {
				int i = 0;
				int j = 0;
				pixels2 = Integer.parseInt(list.get(listindex).split(" ")[1]);
				pixelsvalue = Integer
						.parseInt(list.get(listindex).split(" ")[0]);
				if(jumpmap.containsKey(jumplist)){
					pixels2 = pixels2 - jumpmap.get(jumplist);
				}//这里记录下filledpixels表示开始压缩的点，在后面输出时，计数到这个点时累加0回去!
				
				int k = 0;
				while (k < pixels2) {
					j = filledpixels % column;
					i = (filledpixels - j) / column;
					pic[i][j] = pixelsvalue;
					filledpixels++;
					k++;
				}
				listindex++;
			}// 将RLE编码的压缩数据还原为原始的数据矩阵

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					System.out.print(pic[i][j] + " ");
				}
				System.out.println("");
			}// 输出原始数据矩阵

			EdgeDetection.column = column;
			EdgeDetection.row = row;
			EdgeDetection.pic = pic;

			int[][] res = new int[row][column];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
						res[i][j] = getEdges(i, j);
				}
			}
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					System.out.print(res[i][j] + " ");
				}
				System.out.println("");
			}// 生成及输出edges矩阵

			sb.append(column + "\n");
			Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					if (map.get(res[i][j]) == null) {
						if (i == 0 && j == 0) {
							map.put(res[i][j], 1);
						} else {
							Set<Integer> set = map.keySet();
							int key = set.iterator().next();
							int len = map.get(key);
							sb.append(key + " " + len + "\n");
							map.clear();
							map.put(res[i][j], 1);
						}
					} else {
						map.put(res[i][j], 1 + map.get(res[i][j]));
					}

				}
			}
			Set<Integer> set = map.keySet();
			int key = set.iterator().next();
			int len = map.get(key);
			sb.append(key + " " + len + "\n");
			map.clear();
			sb.append("0 0");
			System.out.println(sb.toString());
			// 压缩edges矩阵并输出

			s = br.readLine();
		}
	}
}
