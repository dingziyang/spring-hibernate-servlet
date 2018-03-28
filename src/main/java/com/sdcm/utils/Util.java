package com.sdcm.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static String StringJoin(String a, String b) {
		return a + b;
	}

	public static synchronized int StringToInt(String source) {
		try {
			return Integer.parseInt(source);
		} catch (NumberFormatException e) {
		}
		return -9999;
	}

	public static synchronized String IntToString(int source) {
		try {
			return Integer.valueOf(source).toString();
		} catch (NumberFormatException e) {
		}
		return "";
	}

	public static synchronized String formatDate(Date date, String partten) {
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat(partten);
		try {
			s = sdf.format(date);
		} catch (Exception e) {
			System.out.println("com.tom.util.Util.formatDate()发生异常,partten=" + partten);
		}
		return s;
	}

	public static synchronized String splitString(String s, int len) {
		String str = s;
		if ((s != null) && (s.length() > len)) {
			str = s.substring(0, len);
		}
		return str;
	}
	
	@SuppressWarnings("rawtypes")
	public static synchronized int SizeOfList(List list) {
		return list == null ? 0 : list.size();
	}

	public static String FormatBlankQuestions(String content, String input) {
		if ((content == null) || ("".equals(content))) {
			return "";
		}
		String result = "";
		Pattern p = Pattern.compile("\\[BlankArea.+?]");
		Matcher m = null;
		m = p.matcher(content);
		result = m.replaceAll(input);

		return result;
	}

	public static int dateDiff(Object o1, Object o2) {
		int i = 0;
		try {
			Timestamp sdate = (Timestamp) o1;
			Timestamp edate = (Timestamp) o2;
			long passed = edate.getTime() - sdate.getTime();
			int passed_min = (int) (passed / 60000L);
			i = 1 * passed_min;
		} catch (Exception e) {
			e.getMessage();
		}
		return i;
	}
	
	public static String dateDiffs(Object o1, Object o2) {
		try {
			Timestamp sdate = (Timestamp) o1;
			Timestamp edate = (Timestamp) o2;
			long passed = edate.getTime() - sdate.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("HH小时mm分钟SS秒");

			@SuppressWarnings("unused")
			int passed_min = (int) (passed / 60000L);

			Date date = new Date(passed);
			return sdf.format(date);
		} catch (Exception e) {
			e.getMessage();
		}
		return "0";
	}

	public static String readFileByLines(String fileName) throws Exception {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			throw new Exception("读取授权文件发生异常，文件可能不存在");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException localIOException1) {
				}
			}
		}
		return sb.toString();
	}

	public static boolean checksn(String sn) {
		boolean isok = false;

		return isok;
	}

	public static boolean isShowScore(String show_score) {
		if ((show_score == null) || (show_score.length() < 10)) {
			return true;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String now_date = sdf.format(new Date());
		show_score = show_score.replaceAll(" ", "").replaceAll("/", "").replaceAll(":", "");

		return now_date.compareTo(show_score) > 0;
	}

	public static String FUNCTION_FORMAT_PATH(String path) {
		String upath = path;
		upath = upath.replaceAll("\\\\", "\\\\\\\\");
		upath = upath.replaceAll("/", "//");

		if (upath.indexOf("/") > -1) {
			upath = upath + "//";
		} else {
			upath = upath + "\\\\";
		}
		return upath;
	}

	public static String isoToUTF8(String name) {
		String value = name;
		if (value == null || "".equals(value)) {
			return "";
		}
		// 处理编码问题
		try {
			value = new String(value.getBytes("iso-8859-1"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

}
