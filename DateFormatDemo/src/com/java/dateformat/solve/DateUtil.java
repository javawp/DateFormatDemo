package com.java.dateformat.solve;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 1.需要的时候创建新实例： 说明：在需要用到SimpleDateFormat
 * 的地方新建一个实例，不管什么时候，将有线程安全问题的对象由共享变为局部私有都能避免多线程问题，不过也加重了创建对象的负担。在一般情况下，
 * 这样其实对性能影响比不是很明显的。
 */
public class DateUtil {

	public static String formatDate(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static Date parse(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(strDate);
	}
}
