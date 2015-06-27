package com.andy.oj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MayaCalendar {
	/*
	 * 					Maya Calendar 
	 * Time Limit: 1000MS 		Memory Limit: 10000K 
	 * Total Submissions: 70182 Accepted: 21600 
	 * 
	 * Description:
	 * During his last sabbatical, professor M. A. Ya made a surprising
	 * discovery about the old Maya calendar. From an old knotted message,
	 * professor discovered that the Maya civilization used a 365 day long year,
	 * called Haab, which had 19 months. Each of the first 18 months was 20 days
	 * long, and the names of the months were pop, no, zip, zotz, tzec, xul,
	 * yoxkin, mol, chen, yax, zac, ceh, mac, kankin, muan, pax, koyab, cumhu.
	 * Instead of having names, the days of the months were denoted by numbers
	 * starting from 0 to 19. The last month of Haab was called uayet and had 5
	 * days denoted by numbers 0, 1, 2, 3, 4. The Maya believed that this month
	 * was unlucky, the court of justice was not in session, the trade stopped,
	 * people did not even sweep the floor.
	 * For religious purposes, the Maya used another calendar in which the year
	 * was called Tzolkin (holly year). The year was divided into thirteen
	 * periods, each 20 days long. Each day was denoted by a pair consisting of
	 * a number and the name of the day. They used 20 names: imix, ik, akbal,
	 * kan, chicchan, cimi, manik, lamat, muluk, ok, chuen, eb, ben, ix, mem,
	 * cib, caban, eznab, canac, ahau and 13 numbers; both in cycles.
	 * Notice that each day has an unambiguous description. For example, at the
	 * beginning of the year the days were described as follows:
	 * 1 imix, 2 ik, 3 akbal, 4 kan, 5 chicchan, 6 cimi, 7 manik, 8 lamat, 9
	 * muluk, 10 ok, 11 chuen, 12 eb, 13 ben, 1 ix, 2 mem, 3 cib, 4 caban, 5
	 * eznab, 6 canac, 7 ahau, and again in the next period 8 imix, 9 ik, 10
	 * akbal . . .
	 * Years (both Haab and Tzolkin) were denoted by numbers 0, 1, : : : , where
	 * the number 0 was the beginning of the world. Thus, the first day was:
	 * Haab: 0. pop 0
	 * Tzolkin: 1 imix 0 
	 * Help professor M. A. Ya and write a program for him to
	 * convert the dates from the Haab calendar to the Tzolkin calendar. 
	 * 
	 * Input:
	 * The date in Haab is given in the following format: 
	 * NumberOfTheDay. Month Year
	 * The first line of the input file contains the number of the input dates
	 * in the file. The next n lines contain n dates in the Haab calendar
	 * format, each in separate line. The year is smaller then 5000. 
	 * 
	 * Output:
	 * The date in Tzolkin should be in the following format: 
	 * Number NameOfTheDay Year
	 * The first line of the output file contains the number of the output
	 * dates. In the next n lines, there are dates in the Tzolkin calendar
	 * format, in the order corresponding to the input dates. 
	 * 
	 * Sample Input:
	 * 3 
	 * 10. zac 0 
	 * 0. pop 0 
	 * 10. zac 1995 
	 * 
	 * Sample Output:
	 * 3 
	 * 3 chuen 0 
	 * 1 imix 0 
	 * 9 cimi 2801 
	 * 
	 * Source:
	 * Central Europe 1995
	 */
	private static Map<String, Integer> haabmonth = new HashMap<String, Integer>();
	static {
		haabmonth.put("pop", 0);
		haabmonth.put("no", 1);
		haabmonth.put("zip", 2);
		haabmonth.put("zotz", 3);
		haabmonth.put("tzec", 4);
		haabmonth.put("xul", 5);
		haabmonth.put("yoxkin", 6);
		haabmonth.put("mol", 7);
		haabmonth.put("chen", 8);
		haabmonth.put("yax", 9);
		haabmonth.put("zac", 10);
		haabmonth.put("ceh", 11);
		haabmonth.put("mac", 12);
		haabmonth.put("kankin", 13);
		haabmonth.put("muan", 14);
		haabmonth.put("pax", 15);
		haabmonth.put("koyab", 16);
		haabmonth.put("cumhu", 17);
		haabmonth.put("uayet", 18);
	}

	private static Map<Integer, String> tzolkinday = new HashMap<Integer, String>();
	private static String[] tzolkindayname = new String[] { "imix", "ik",
			"akbal", "kan", "chicchan", "cimi", "manik", "lamat", "muluk",
			"ok", "chuen", "eb", "ben", "ix", "mem", "cib", "caban", "eznab",
			"canac", "ahau" };
	static {
		int days = 0;
		while (days < 260) {
			tzolkinday.put(days, "" + (days % 13 + 1) + " "
					+ tzolkindayname[days % 20]);
			days++;
		}

	}

	public static int getDaysFromHaab(String s) {
		int days = 0;
		String[] ss = s.split(" ");
		days = days + Integer.parseInt(ss[0].replace(".", ""));
		int month = haabmonth.get(ss[1]);
		days = days + month * 20;
		days = days + Integer.parseInt(ss[2]) * 365;

		return days;
	}

	public static String reverseDaysToTzolkin(int days) {
		int year = days / 260;
		days = days % 260;

		String day = tzolkinday.get(days);
		return day + " " + year;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		int count = Integer.parseInt(s);
		int i = 0;
		int days = 0;
		String tzolkin = "";
		StringBuffer sb = new StringBuffer();
		sb.append(count + "\n");
		while (i < count) {
			s = br.readLine();
			days = getDaysFromHaab(s);
			tzolkin = reverseDaysToTzolkin(days);
			i++;
			sb.append(tzolkin + "\n");
		}
		System.out.println(sb.toString());
	}
}
