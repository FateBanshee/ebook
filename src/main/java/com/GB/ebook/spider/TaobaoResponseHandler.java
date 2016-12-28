package com.GB.ebook.spider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.GB.ebook.entity.BookItem;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TaobaoResponseHandler implements ResponseHandler<List> {
	public static String getTextFromHtml(String html) {
		Document doc = Jsoup.parse(html);
		return doc.text();
	}

	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;

		while ((i = is.read()) != -1) {
			baos.write(i);
		}

		return baos.toString();
	}

	public List handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		// System.out.println("==TaobaoResponseHandler==");
		// String htmlstr=EntityUtils.toString(response.getEntity());
		// System.out.println(htmlstr);

		InputStream is = response.getEntity().getContent();
		String html = inputStream2String(is);
		int start = html.indexOf("g_page_config =");
		int end = html.indexOf("g_srp_loadCss()");
		String json_txt = html.substring(start + 15, end).trim();
		JSONObject json = JSONObject.parseObject(json_txt.substring(0, json_txt.length() - 1));
		JSONArray json_array = json.getJSONObject("mods").getJSONObject("itemlist").getJSONObject("data")
				.getJSONArray("auctions");

		List<BookItem> arraylist = new ArrayList<BookItem>();

		int count = 0;
		for (Object product : json_array) {
			Map<String, Object> map = new HashMap<String, Object>();

			JSONObject product_obj = JSONObject.parseObject(product.toString());
			map.put("site", "淘宝");
			String title = getTextFromHtml(product_obj.getString("title"));
			String url = getTextFromHtml(product_obj.getString("detail_url"));
			Float price = Float.valueOf(getTextFromHtml(product_obj.getString("view_price")));
			// map.put("nick", getTextFromHtml(product_obj.getString("nick")));
			// map.put("item_loc",
			// getTextFromHtml(product_obj.getString("item_loc")));

			BookItem bookitem = new BookItem();
			bookitem.setSite("淘宝");
			bookitem.setUrl(url);
			bookitem.setPrice(price);
			bookitem.setTitle(title);
			arraylist.add(bookitem);
			count++;
			if (count >= 2) {
				break;
			}

		}

		arraylist.sort(new Comparator<BookItem>() {
			public int compare(BookItem o1, BookItem o2) {
				// TODO Auto-generated method stub
				if (o1.getPrice() > o2.getPrice()) {
					return 1;
				} else if (o1.getPrice() < o2.getPrice()) {
					return -1;
				} else {
					return 0;
				}
			}

		});

		return arraylist;
	}

}
