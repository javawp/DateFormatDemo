package com.java.dateformat.test;

import java.text.ParseException;

import com.java.dateformat.simple.DateUtil2;

/**
 * 当然，这个方法的确很不错，在大部分的时间里面都会工作得很好。但当你在生产环境中使用一段时间之后，你就会发现这么一个事实：它不是线程安全的。
 * 在正常的测试情况之下，都没有问题，但一旦在生产环境中一定负载情况下时，这个问题就出来了。他会出现各种不同的情况，比如转化的时间不正确，比如报错，
 * 比如线程被挂死等等。我们看下面的测试用例，那事实说话
 */
public class DateUtilTest2 {

	/**
	 * 说明：Thread-1和Thread-0报java.lang.NumberFormatException: multiple
	 * points错误，直接挂死，没起来；Thread-2 虽然没有挂死，但输出的时间是有错误的，比如我们输入的时间是：2013-05-24
	 * 06:02:20 ，但是会输出：Mon May 24 06:02:20 CST 2021 这样的灵异事件。
	 * 
	 * 作为一个专业程序员，我们当然都知道，相比于共享一个变量的开销要比每次创建一个新变量要小很多。上面的优化过的静态的SimpleDateFormat版
	 * ，之所在并发情况下回出现各种灵异错误，是因为SimpleDateFormat和DateFormat类不是线程安全的。我们之所以忽视线程安全的问题，
	 * 是因为从SimpleDateFormat和DateFormat类提供给我们的接口上来看，实在让人看不出它与线程安全有何相干。
	 * 只是在JDK文档的最下面有如下说明：
	 * 
	 * SimpleDateFormat中的日期格式不是同步的。推荐（建议）为每个线程创建独立的格式实例。如果多个线程同时访问一个格式，
	 * 则它必须保持外部同步。
	 * 
	 * JDK原始文档如下:
	 * Synchronization:
	 * Date formats are not synchronized.
	 * It is recommended to create separate format instances for each thread.
	 * If multiple threads access a format concurrently, it must be synchronized externally.
	 *
	 */

	public static class TestSimpleDateFormatThreadSafe extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					this.join(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					System.out.println(this.getName() + ":" + DateUtil2.parse("2013-05-24 06:02:20"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new TestSimpleDateFormatThreadSafe().start();
		}

	}
}