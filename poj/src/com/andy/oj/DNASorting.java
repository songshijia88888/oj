package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DNASorting {
	/*
	 * 					DNA Sorting 
	 * Time Limit: 1000MS 		Memory Limit: 10000K 
	 * Total Submissions: 88772 Accepted: 35667 
	 * 
	 * Description:
	 * One measure of ``unsortedness'' in a sequence is the number of pairs of
	 * entries that are out of order with respect to each other. For instance,
	 * in the letter sequence ``DAABEC'', this measure is 5, since D is greater
	 * than four letters to its right and E is greater than one letter to its
	 * right. This measure is called the number of inversions in the sequence.
	 * The sequence ``AACEDGG'' has only one inversion (E and D)---it is nearly
	 * sorted---while the sequence ``ZWQM'' has 6 inversions (it is as unsorted
	 * as can be---exactly the reverse of sorted).
	 * You are responsible for cataloguing a sequence of DNA strings (sequences
	 * containing only the four letters A, C, G, and T). However, you want to
	 * catalog them, not in alphabetical order, but rather in order of
	 * ``sortedness'', from ``most sorted'' to ``least sorted''. All the strings
	 * are of the same length. 
	 * 
	 * Input:
	 * The first line contains two integers: a positive integer n (0 < n <= 50)
	 * giving the length of the strings; and a positive integer m (0 < m <= 100)
	 * giving the number of strings. These are followed by m lines, each
	 * containing a string of length n.
	 * 
	 * Output:
	 * Output the list of input strings, arranged from ``most sorted'' to
	 * ``least sorted''. Since two strings can be equally sorted, then output
	 * them according to the orginal order. 
	 * 
	 * Sample Input:
	 * 10 6 
	 * AACATGAAGG 
	 * TTTTGGCCAA 
	 * TTTGGCCAAA 
	 * GATCAGATTT 
	 * CCCGGGGGGA 
	 * ATCGATGCAT
	 * 
	 * Sample Output:
	 * CCCGGGGGGA 
	 * AACATGAAGG 
	 * GATCAGATTT 
	 * ATCGATGCAT 
	 * TTTTGGCCAA 
	 * TTTGGCCAAA 
	 * 
	 * Source:
	 * East Central North America 1998
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int m = Integer.parseInt(s.split(" ")[1]);
		int n = Integer.parseInt(s.split(" ")[0]);
		String[] ss = new String[m];
		int[] sc = new int[m];
		int count = 0;
		String sf = "";
		String st = "";
		while (count < m) {
			s = br.readLine();
			ss[count] = s;
			for (int i = 0; i < n - 1; i++) {
				sf = s.substring(i, i + 1);
				for (int j = i + 1; j < n; j++) {
					if (j + 1 < n) {
						st = s.substring(j, j + 1);
					} else {
						st = s.substring(j);
					}

					if (sf.compareTo(st) > 0) {
						sc[count] = sc[count] + 1;
					}
				}
			}
			count++;
		}
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < m; i++) {
			list.add(sc[i]);
		}
		Collections.sort(list);
		while (!list.isEmpty()) {
			int most = list.remove(0);
			for (int i = 0; i < m; i++) {
				if (sc[i] == most) {
					System.out.println(ss[i]);
					sc[i] = -1;
					/*
					 * without the operation 'sc[i] = -1;', when there are
					 * strings have the same inversions, those strings will be
					 * output repeatly.
					 */
				}
			}
		}

	}

}
