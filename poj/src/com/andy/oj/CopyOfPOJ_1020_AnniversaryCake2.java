package com.andy.oj;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CopyOfPOJ_1020_AnniversaryCake2 {
	public static void main(String[] args) throws IOException {
		// 22 14 4 1 6 7 9 1 7 3 10 8 1 6 5 4

		SquareCutter2 s = new SquareCutter2(22);
		if (s.cutSquare(4)) {
			s.showRectangle();
		}
		if (s.cutSquare(6)) {
			s.showRectangle();
		}
		if (s.cutSquare(7)) {
			s.showRectangle();
		}
		if (s.cutSquare(9)) {
			s.showRectangle();
		}
		if (s.cutSquare(7)) {
			s.showRectangle();
		}
		if (s.cutSquare(3)) {
			s.showRectangle();
		}
		if (s.cutSquare(10)) {
			s.showRectangle();
		}
		if (s.cutSquare(8)) {
			s.showRectangle();
		}
		if (s.cutSquare(6)) {
			s.showRectangle();
		}
		if (s.cutSquare(5)) {
			s.showRectangle();
		}
		if (s.cutSquare(4)) {
			s.showRectangle();
		}
		for (int i = 0; i < 3; i++) {
			if (s.cutSquare(1)) {
				s.showRectangle();
			}
		}
		s.showRectangle();

	}

}

// ���ε����и���
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
		 * 1.�������ϼ���һ�������κ�ʣ��һ��������+���������� 2.�������ϼ���һ�������κ�ʣ������������
		 * 3.�߳�Ϊn���������м���һ���߳�Ϊm�������Σ���Ȼm < n�� ʣ��һ���߳�Ϊn - m�������Σ������߳�Ϊn - m��m�ĳ�����
		 * 4.�߳�Ϊn��m�ĳ������м���һ���߳�Ϊk�������Σ���Ȼk < m && k < n�� ʣ��һ���߳�Ϊk��m -
		 * k�ĳ����Σ�һ������Ϊm��n - k�ĳ�����
		 * 
		 * Ӧ�ô����г����κ������������б߳��ոմ��ڵ��ڴ��������ε��Ǹ�ͼ�������� ���Ӧ�øոպ�����
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

// ����
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