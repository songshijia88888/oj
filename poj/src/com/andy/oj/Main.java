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

public class Main {
	private static int column;
	private static int row;
	private static int[][] pic;

	public static int getEdges(int m, int n) {
		int rs = m - 1 > 0 ? m - 1 : 0;
		int re = m + 1 > Main.row - 1 ? Main.row - 1 : m + 1;
		int cs = n - 1 > 0 ? n - 1 : 0;
		int ce = n + 1 > Main.column - 1 ? Main.column - 1 : n + 1;
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

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> list = new LinkedList<String>();
		StringBuffer sb = new StringBuffer();
		String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!"0".equals(s)) {
			int column = Integer.parseInt(s);

			list.clear();
			try {
				s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (!"0 0".equals(s)) {
				list.add(s);
				try {
					s = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}// 读完一幅图片的所有数据

			int pixels = 0;
			Map<Integer, Integer> jumpmap3 = new HashMap<Integer, Integer>();
			for (int i = 0; i < list.size(); i++) {
				int thispixel = Integer.parseInt(list.get(i).split(" ")[1]);
				if (thispixel / column > 7) {
					// System.out.println("thispixel:" + thispixel);
					thispixel = 4 * column;
					pixels = pixels + thispixel;
					jumpmap3.put(pixels,
							Integer.parseInt(list.get(i).split(" ")[1]) - 4
									* column);
					// System.out.println(jumpmap3);
				} else {
					pixels = pixels + thispixel;
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
				if (pixels2 / column > 7) {
					pixels2 = 4 * column;
				}
				pixelsvalue = Integer
						.parseInt(list.get(listindex).split(" ")[0]);

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

			/*
			 * for (int i = 0; i < row; i++) { for (int j = 0; j < column; j++)
			 * { System.out.print(pic[i][j] + " "); } System.out.println("");
			 * }// 输出原始数据矩阵
			 */
			Main.column = column;
			Main.row = row;
			Main.pic = pic;

			int[][] res = new int[row][column];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					res[i][j] = getEdges(i, j);
				}
			}
			/*
			 * for (int i = 0; i < row; i++) { for (int j = 0; j < column; j++)
			 * { System.out.print(res[i][j] + " "); } System.out.println("");
			 * }// 生成及输出edges矩阵
			 */

			filledpixels = 0;
			sb.append(column + "\n");
			Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					if (map.get(res[i][j]) == null) {
						if (i == 0 && j == 0) {
							map.put(res[i][j], 1);
							filledpixels++;
							if (jumpmap3.containsKey(filledpixels + 2 * column)) {
								// System.out.println("filledpixels:" +
								// filledpixels + ",res[i][j]:" + res[i][j] +
								// ",map.get(res[i][j]):" + map.get(res[i][j]));
								map.put(res[i][j],
										map.get(res[i][j])
												+ jumpmap3.get(filledpixels + 2
														* column));
							}

						} else {
							Set<Integer> set = map.keySet();
							int key = set.iterator().next();
							int len = map.get(key);
							sb.append(key + " " + len + "\n");
							map.clear();
							map.put(res[i][j], 1);
							filledpixels++;
							if (jumpmap3.containsKey(filledpixels + 2 * column)) {
								// System.out.println("filledpixels:" +
								// filledpixels + ",res[i][j]:" + res[i][j] +
								// ",map.get(res[i][j]):" + map.get(res[i][j]));
								map.put(res[i][j],
										map.get(res[i][j])
												+ jumpmap3.get(filledpixels + 2
														* column));
							}
						}
					} else {
						map.put(res[i][j], 1 + map.get(res[i][j]));
						filledpixels++;
						if (jumpmap3.containsKey(filledpixels + 2 * column)) {
							// System.out.println("filledpixels:" + filledpixels
							// + ",res[i][j]:" + res[i][j] +
							// ",map.get(res[i][j]):" + map.get(res[i][j]));
							map.put(res[i][j],
									map.get(res[i][j])
											+ jumpmap3.get(filledpixels + 2
													* column));
						}
					}

				}
			}
			Set<Integer> set = map.keySet();
			int key = set.iterator().next();
			int len = map.get(key);
			sb.append(key + " " + len + "\n");
			map.clear();
			sb.append("0 0\n");

			// 压缩edges矩阵并输出

			try {
				s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sb.append("0\n");
		System.out.println(sb.toString());
	}
}
