package com.java.dateformat.simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 你也许会说，OK，那我就创建一个静态的simpleDateFormat实例，然后放到一个DateUtil类（如下）中，在使用时直接使用这个实例进行操作，
 * 这样问题就解决了。改进后的代码如下：
 */
public class DateUtil2 {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDate(Date date) throws ParseException {
		return sdf.format(date);
	}

	public static Date parse(String strDate) throws ParseException {

		return sdf.parse(strDate);
	}
}