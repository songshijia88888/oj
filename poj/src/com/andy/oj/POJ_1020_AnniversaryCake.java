package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class POJ_1020_AnniversaryCake {
	public static void main(String[] args) throws IOException {
		/*
		 * 4 8 1 1 1 1 1 3 1 1 SquareCutter s = new SquareCutter(4); if
		 * (s.cutSquare(3)) { s.showRectangle(); } ; for (int i = 0; i < 7; i++)
		 * { if (s.cutSquare(1)) { s.showRectangle(); } ; }
		 * 
		 * 5 6 3 3 2 1 1 1 s = new SquareCutter(5); if (s.cutSquare(3)) {
		 * s.showRectangle(); } if (s.cutSquare(3)) { s.showRectangle(); } else
		 * { System.out.println("不得行！"); }
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int cases = Integer.parseInt(s);
		int count = 0;
		StringBuffer sb = new StringBuffer();

		while (count < cases) {
			s = br.readLine();
			count++;
			String[] ss = s.split(" ");
			int n = Integer.valueOf(ss[0]);
			SquareCutter cake = new SquareCutter(n);
			int i = 2;
			for (i = 2; i < ss.length; i++) {
				n = Integer.valueOf(ss[i]);
				if (cake.cutSquare(n)) {
					continue;
				} else {
					sb.append("HUTUTU!");
					break;
				}
			}
			if (i == ss.length) {
				if (cake.isEmpty()) {

					sb.append("KHOOOOB!");
				}

				else {
					sb.append("HUTUTU!");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}

// 方形蛋糕切割器
class SquareCutter {
	List<Rectangle> rectangle = new LinkedList<Rectangle>();

	public void showRectangle() {
		System.out.println(rectangle);
	}

	public boolean isEmpty() {
		if (rectangle.size() == 0) {
			return true;
		}

		return false;
	}

	public SquareCutter(int n) {
		Rectangle s = new Rectangle(n, n);
		rectangle.add(s);
		Collections.sort(rectangle);
	}

	public boolean cutSquare(int n) {
		/*
		 * 1.正方形上剪下一个正方形后，剩下一个正方形+两个长方形 2.长方形上剪下一个正方形后，剩下两个长方形
		 * 3.边长为n的正方形中剪下一个边长为m的正方形，显然m < n， 剩下一个边长为n - m的正方形，两个边长为n - m和m的长方形
		 * 4.边长为n和m的长方形中剪下一个边长为k的正方形，显然k < m && k < n， 剩下一个边长为k和m -
		 * k的长方形，一个连长为m和n - k的长方形
		 * 
		 * 应该从所有长方形和所有正方形中边长刚刚大于等于待切正方形的那个图形里面切 最后应该刚刚好切完
		 */
		Rectangle tar = new Rectangle(n, n);
		Rectangle src = null;
		for (int i = 0; i < rectangle.size(); i++) {
			if (rectangle.get(i).compareTo(tar) >= 0) {
				src = rectangle.remove(i);
				int min = src.getMin();
				int max = src.getMax();
				if (min - n != 0) {
					rectangle.add(new Rectangle(min - n, n));
				}
				if (max - n != 0) {
					rectangle.add(new Rectangle(max - n, min));
				}
				Collections.sort(rectangle);

				return true;
			}
		}

		return false;
	}
}

// 方形
class Rectangle implements Comparable<Rectangle> {
	private int n;
	private int m;

	public int getMin() {
		if (n <= m) {
			return n;
		} else {
			return m;
		}
	}

	public int getMax() {
		if (n <= m) {
			return m;
		} else {
			return n;
		}
	}

	public Rectangle(int n, int m) {
		this.n = n;
		this.m = m;
	}

	public int compareTo(Rectangle tar) {
		if (this.getMin() > tar.getMin()) {
			return 1;
		} else if (this.getMin() == tar.getMin()) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return getMax() + "*" + getMin() + " ";
	}
}