package com.GB.ebook.spider;

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

public class DangdangResponseHandler implements ResponseHandler<List> {

	 
     public List handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		 //System.out.println("==DangdangResponseHandler===");
		 /*String htmlstr=EntityUtils.toString(response.getEntity());
         System.out.println(htmlstr);
         System.out.println(response.getStatusLine().getStatusCode());*/
         
         
         InputStream is = response.getEntity().getContent();
         Document doc = Jsoup.parse(is, "gbk", "http://search.dangdang.com");
         Elements items =doc.getElementById("search_nature_rg").getElementsByTag("li");
         List<BookItem> arraylist=new ArrayList<BookItem>();
         int count=0;
         for(Element item:items){
        	 String title=item.getElementsByClass("pic").first().attr("title");
        	 String url=item.getElementsByClass("pic").first().attr("href");
        	 Float price=Float.valueOf(item.getElementsByClass("price").first().getElementsByClass("search_now_price").text().substring(1));
        	 BookItem bookitem = new BookItem();
        	 bookitem.setSite("当当");
        	 bookitem.setUrl(url);
        	 bookitem.setPrice(price);
        	 bookitem.setTitle(title);
        	 
   
        	 //map.put("detail", item.getElementsByClass("detail").first().text());
 
        	 arraylist.add(bookitem);
        	 count++;
        	 if(count>=2){
        		 break;
        	 }
         }
        
         arraylist.sort(new Comparator<BookItem>() {
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
         
         return arraylist;
     }

}
