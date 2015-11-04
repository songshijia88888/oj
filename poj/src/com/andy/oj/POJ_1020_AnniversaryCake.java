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
		 * { System.out.println("�����У�"); }
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

// ���ε����и���
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