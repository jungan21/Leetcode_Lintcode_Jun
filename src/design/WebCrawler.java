package design;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class WebCrawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> crawler(String url) {
		// Write your code here
		int thread_num = 7;
		//
		CrawlerThread.setFirstUrl(url, thread_num);

		CrawlerThread[] thread_pools = new CrawlerThread[thread_num];
		for (int i = 0; i < thread_num; ++i) {
			thread_pools[i] = new CrawlerThread();
			thread_pools[i].start();
		}
		// 一直要到 counter == 0, 这个while loop才会停止，然后接下来执行stop 操作
		while (CrawlerThread.getCounter() > 0) {
			;
		}

		for (int i = 0; i < thread_num; ++i) {
			// http://www.infoq.com/cn/articles/java-interrupt-mechanism
			thread_pools[i].interrupt();
			// thread_pools[i].stop();
		}

		List<String> results = CrawlerThread.getResults();
		return results;
	}
}

class CrawlerThread extends Thread {
	private static AtomicLong counter;
	private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	private static HashMap<String, Boolean> mp = new HashMap<String, Boolean>();
	private static List<String> results = new ArrayList<String>();

	public static void setFirstUrl(String url, int thread_num) {
		// counter的初始值 必须和thread_num相等
		// if thread_numw=7， you set counter to 8, will get time limit error
		// if thread_numw=7， you set counter to 6, will get wrong answer,
		// 会缺少一些答案，因为queue里还有url但是，thread都停止了
		counter = new AtomicLong(thread_num);

		try {
			queue.put(url);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}

	public static Long getCounter() {
		return counter.get();
	}

	public static List<String> getResults() {
		return results;
	}

	@Override
	public void run() {
		while (true) {
			String url = "";
			try {
				/**
				 * count--, count++, 如果queue里面没有url,
				 * 7个线程都会调用count--一次，最后把初始值为7的count减为0，
				 * 
				 * 在crawl 函数里，有判断，当count ==0, 意味着7个线程都没有从queen里取到url,
				 * 就条用thread.stop去终止每个线程
				 */
				counter.decrementAndGet();
				// Retrieves and removes the head of this queue, waiting if
				// necessary until an element becomes available.
				url = queue.take();
				counter.incrementAndGet();
			} catch (Exception e) {
				// e.printStackTrace();
				break;
			}

			String domain = "";
			try {
				URL netUrl = new URL(url);
				domain = netUrl.getHost();
			} catch (MalformedURLException e) {
				// e.printStackTrace();
			}
			if (!mp.containsKey(url) && domain.endsWith("wikipedia.org")) {
				mp.put(url, true);
				results.add(url);
				List<String> urls = HtmlHelper.parseUrls(url);
				for (String u : urls) {
					try {
						queue.put(u);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
				}
			}
		}
	}
}

class HtmlHelper {
	public static List<String> parseUrls(String url) {
		return null;
	}
}
