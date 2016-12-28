 package com.GB.ebook.spider;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.PrinterLocation;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpRequestFutureTask;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;

import com.GB.ebook.entity.BookItem;

public class SpiderUtils {
	public static FutureRequestExecutionService fres;
	public static CloseableHttpClient httpclient;
	static {

		ExecutorService es = Executors.newCachedThreadPool();
		try {

			es.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(3);
		ArrayList<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("accept-language", "zh-CN,zh;q=0.8"));
		headers.add(new BasicHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		headers.add(new BasicHeader("Upgrade-Insecure-Requests", "1"));
		headers.add(new BasicHeader("Accept-Encoding:", "gzip"));
		RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).setConnectTimeout(25200)
				.setConnectionRequestTimeout(25200).build();
		// HttpHost proxy = new HttpHost("67.205.157.63", 8080, "http");

		String ua = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36";
		httpclient = HttpClientBuilder.create().setUserAgent(ua).setDefaultRequestConfig(config)
				.setConnectionManager(cm).setDefaultHeaders(headers).build();
		fres = new FutureRequestExecutionService(httpclient, es);

	}
	public SpiderUtils()
	{
		
	}

	public static void print(Object o) {
		System.out.println(o.toString());
	}

	public static List<BookItem> getItemsFromTaobao(String key1, String... key2)
			throws InterruptedException, ExecutionException, ClientProtocolException, IOException {
		String url = "https://s.taobao.com/search?q=" + key1;
		for (String key : key2) {
			url = url + "+" + key;
		}
		HttpGet taobaopage = new HttpGet(url);
		HttpClientContext hc = new HttpClientContext();
		ResponseHandler<List> taobaoResponseHandler = new TaobaoResponseHandler();
		List<BookItem> bookItems = httpclient.execute(taobaopage, taobaoResponseHandler, hc);
		/*
		 * for (int i = 0; i < bookItems.size(); i++) { BookItem bookItem =
		 * bookItems.get(i); print(bookItem.getTitle());
		 * print(bookItem.getPrice()); }
		 */
		return bookItems;
	}

	public static List<BookItem> getItemsFromDangdang(String key1, String... key2)
			throws ClientProtocolException, IOException, InterruptedException, ExecutionException {
		String url = "http://search.dangdang.com/?key=" + key1;
		for (String key : key2) {
			url = url + "+" + key;
		}
		HttpGet dangdangpage = new HttpGet(url);
		ResponseHandler<List> responseHandler = new DangdangResponseHandler();
		HttpClientContext hc = new HttpClientContext();
		List<BookItem> bookItems = httpclient.execute(dangdangpage, responseHandler, hc);
		/*
		 * for (int i = 0; i < bookItems.size(); i++) { BookItem bookItem =
		 * bookItems.get(i); print(bookItem.getTitle());
		 * print(bookItem.getPrice()); }
		 */
		return bookItems;
	}

	public static List<BookItem> getItemsFromAmazon(String key1, String... key2)
			throws ClientProtocolException, IOException, InterruptedException, ExecutionException {
		String param = key1;
		for (String key : key2) {
			param = param + " " + key;
		}
		String url = "https://www.amazon.cn/s?field-keywords=" + URLEncoder.encode(param, "utf-8");
		HttpGet amazonpage = new HttpGet(url);
		ResponseHandler<List> responseHandler = new AmazonResponseHandler();
		HttpClientContext hc = new HttpClientContext();
		List<BookItem> bookItems = httpclient.execute(amazonpage, responseHandler, hc);
		/*
		 * for (int i = 0; i < bookItems.size(); i++) { BookItem bookItem =
		 * bookItems.get(i); print(bookItem.getTitle());
		 * print(bookItem.getPrice()); }
		 */
		return bookItems;
	}

	@Deprecated
	public static void getItemsFrom3Site(String key1, String... key2)
			throws ClientProtocolException, IOException, InterruptedException, ExecutionException {
		String azurl = "https://www.amazon.cn/s?&field-keywords=" + URLEncoder.encode(key1, "UTF-8");
		for (String key : key2) {
			azurl = azurl + "+" + URLEncoder.encode(key, "UTF-8");
		}
		// azurl=URLEncoder.encode(azurl, "utf-8");
		String ddurl = "http://search.dangdang.com/?key=" + key1;
		for (String key : key2) {
			ddurl = ddurl + "+" + key;
		}
		String tburl = "https://s.taobao.com/search?q=" + key1;
		for (String key : key2) {
			tburl = tburl + "+" + key;
		}

		HttpGet amazonpage = new HttpGet(azurl);
		HttpGet taobaopage = new HttpGet(tburl);
		HttpGet dangdpage = new HttpGet(ddurl);
		// HttpGet amazonpage=new
		// HttpGet("https://www.amazon.cn/s?&field-keywords=c%E8%AF%AD%E8%A8%80%E7%A8%8B%E5%BA%8F%E8%AE%BE%E8%AE%A1+%E6%B8%85%E5%8D%8E%E5%A4%A7%E5%AD%A6%E5%87%BA%E7%89%88%E7%A4%BE");
		ResponseHandler<List> amazonResponseHandler = new AmazonResponseHandler();
		ResponseHandler<List> dangdangResponseHandler = new DangdangResponseHandler();
		ResponseHandler<List> taobaoResponseHandler = new TaobaoResponseHandler();

		HttpClientContext azhc = new HttpClientContext();
		HttpClientContext tbhc = new HttpClientContext();
		HttpClientContext ddhc = new HttpClientContext();

		HttpRequestFutureTask<List> ddFutureTask = fres.execute(dangdpage, ddhc, dangdangResponseHandler);
		HttpRequestFutureTask<List> tbFutureTask = fres.execute(taobaopage, tbhc, taobaoResponseHandler);
		// HttpRequestFutureTask<List> amFutureTask =fres.execute(amazonpage,
		// azhc, amazonResponseHandler) ;
		// List azBookItems =amFutureTask.get();
		List ddBookItems = ddFutureTask.get();
		List tbBookItems = tbFutureTask.get();
		List totalbooks = new ArrayList<BookItem>();
		totalbooks.addAll(tbBookItems);
		// totalbooks.addAll(azBookItems);
		totalbooks.addAll(ddBookItems);

		// List<BookItem>bookItems= httpclient.execute(amazonpage,
		// responseHandler,hc);
		for (int i = 0; i < totalbooks.size(); i++) {
			BookItem bookItem = (BookItem) totalbooks.get(i);

			print(bookItem.getTitle());
			print(bookItem.getPrice());
		}
	}

	public static List<BookItem> getItemsFromAllSite(String key1, String... key2)
			throws ClientProtocolException, InterruptedException, ExecutionException, IOException {
		ArrayList<BookItem> total = new ArrayList<BookItem>();
		try{
			total.addAll(getItemsFromTaobao(key1, key2));
		}catch (Exception e) {
			e.printStackTrace();
		}
		try{
			total.addAll(getItemsFromDangdang(key1, key2));
		}catch (Exception e) {
			e.printStackTrace();
		}
		try{
			total.addAll(getItemsFromAmazon(key1, key2));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		total.sort(new Comparator<BookItem>() {
 			public int compare(BookItem o1, BookItem o2) {
 				// TODO Auto-generated method stub
 				if( o1.getPrice() > o2.getPrice()){
 					return 1;
 				}else if(o1.getPrice()<o2.getPrice()){
 					return -1;
 				}else {
						return 0;
					}
 			}
        
          });
      
		
		return total;
	}

//	public static void main(String[] args) throws ClientProtocolException,
//			InterruptedException, ExecutionException, IOException {
//		// getItemsFromTaobao("英语语法", "外语出版社");
//		// getItemsFromDangdang("英语语法", "外语出版社");
//		// getItemsFromAmazon("c语言程序设计", "清华大学出版社");
//		// getItemsFromAmazon("新概念英语", "新视野");
//		// getItemsFrom3Site("英语语法", "外语出版社");
//		// List<BookItem> bookItems = getItemsFromAllSite("spark", "出版社");
//		List<BookItem> bookItems = getItemsFromAllSite("计算机组成原理", "科学出版社");
//		// List<BookItem> bookItems = getItemsFromAllSite("英语四级单词");
//		for (int i = 0; i < bookItems.size(); i++) {
//			BookItem bookItem = bookItems.get(i);
//			print(bookItem.toString());
//			/*
//			 * print(bookItem.getPrice()); print(bookItem.getSite());
//			 * print(bookItem.getUrl());
//			 */
//		}
//	}

}
