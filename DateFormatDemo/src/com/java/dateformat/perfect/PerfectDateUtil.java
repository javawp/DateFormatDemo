package com.java.dateformat.perfect;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PerfectDateUtil {

	private static final String date_format1 = "yyyy-MM-dd";
	private static final String date_format2 = "yyyy/MM/dd";
	private static final String date_format3 = "yyyy.MM.dd";

	private static final ThreadLocal<DateFormat> threadLocal1 = new ThreadLocal<DateFormat>();
	private static final ThreadLocal<DateFormat> threadLocal2 = new ThreadLocal<DateFormat>();
	private static final ThreadLocal<DateFormat> threadLocal3 = new ThreadLocal<DateFormat>();

	public static DateFormat getDateFormat1() {
		DateFormat df = threadLocal1.get();
		if (df == null) {
			df = new SimpleDateFormat(date_format1);
			System.err.println("我是date_format1,新创建");
			threadLocal1.set(df);
		}
		return df;
	}

	public static DateFormat getDateFormat2() {
		DateFormat df = threadLocal2.get();
		if (df == null) {
			df = new SimpleDateFormat(date_format2);
			System.err.println("我是date_format2,新创建");
			threadLocal2.set(df);
		}
		return df;
	}

	public static DateFormat getDateFormat3() {
		DateFormat df = threadLocal3.get();
		if (df == null) {
			df = new SimpleDateFormat(date_format3);
			System.err.println("我是date_format3,新创建");
			threadLocal3.set(df);
		}
		return df;
	}

	public static class TestDateFormat1 extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					this.join(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					System.out.println(this.getName() + ":" + getDateFormat1().parse("2013-05-24"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class TestDateFormat2 extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					this.join(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					System.out.println(this.getName() + ":" + getDateFormat2().parse("2013/05/24"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class TestDateFormat3 extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					this.join(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					System.out.println(this.getName() + ":" + getDateFormat3().parse("2013.05.24"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			new TestDateFormat1().start();
			new TestDateFormat2().start();
			new TestDateFormat3().start();
		}

	}
}
