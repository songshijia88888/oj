package com.andy.oj;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class POJ_1020_AnniversaryCake2 {
	public static void main(String[] args) throws IOException {
		// 22 14 4 1 6 7 9 1 7 3 10 8 1 6 5 4
		SquareCutter2 s = new SquareCutter2(22);
		s.cutSquare(1);
		s.showRectangle();
		s.cutSquare(1);
		s.showRectangle();
		s.cutSquare(1);
		s.showRectangle();
		s.cutSquare(3);
		s.showRectangle();
		s.cutSquare(4);
		s.showRectangle();
		s.cutSquare(4);
		s.showRectangle();
		s.cutSquare(5);
		s.showRectangle();
		s.cutSquare(6);
		s.showRectangle();
		s.cutSquare(6);
		s.showRectangle();
		s.cutSquare(7);
		s.showRectangle();
		s.cutSquare(7);
		s.showRectangle();
		s.cutSquare(8);
		s.showRectangle();
		s.cutSquare(9);
		s.showRectangle();
		s.cutSquare(10);
		s.showRectangle();
		s.showRectangle();
		s.showRectangle();
	}

}

// 方形蛋糕切割器
class SquareCutter2 {
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

	public SquareCutter2(int n) {
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
				System.out.println(n + "*" + n + ":cutted");
				return true;
			}
		}
		System.out.println(n + "*" + n + ":cannot be cutted");
		return false;
	}
}

// 方形
class Rectangle2 implements Comparable<Rectangle> {
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

	public Rectangle2(int n, int m) {
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