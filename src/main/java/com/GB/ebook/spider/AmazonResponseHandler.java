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

public class AmazonResponseHandler implements ResponseHandler<List> {

	 public List handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		 //System.out.println("==AmazonResponseHandler===");
		 /*String htmlstr=EntityUtils.toString(response.getEntity());
         System.out.println(htmlstr);
         System.out.println(response.getStatusLine().getStatusCode());*/
         
         
         InputStream is = response.getEntity().getContent();
         Document doc = Jsoup.parse(is, "utf-8", "https://www.amazon.cn");
         Elements results=doc.getElementsByClass("s-result-item  celwidget ");
         //System.out.println(results.size());
         List<BookItem> arraylist=new ArrayList<BookItem>();
         int count=0;
         for(Element result:results){
        	 BookItem bookitem=new BookItem();
        	 String url=result.getElementsByClass("a-link-normal s-access-detail-page  a-text-normal").first().attr("href");
        	 String title=result.getElementsByClass("a-link-normal s-access-detail-page  a-text-normal").first().attr("title");
        	 Float price = Float.valueOf(result.getElementsByClass("a-size-base a-color-price s-price a-text-bold").first().text().substring(1));
        	 bookitem.setSite("亚马逊");
        	 bookitem.setUrl(url);
        	 bookitem.setPrice(price);
        	 bookitem.setTitle(title);
        	 arraylist.add(bookitem);
        	 count++;
        	 if(count>=2){
        		 break;
        	 }
         }

       /*
         for(int id=0;id<23;id++){
        	 Element li=doc.getElementById("result_"+id);
        	 li.
         }
         
         
         Elements items =doc.getElementById("s-results-list-atf").getElementsByAttributeValueStarting("id", "result_");
         System.out.println(items.size());
         List<BookItem> arraylist=new ArrayList<>();
         int count=0;
         for(Element item:items){
        	 
        	 BookItem bookitem = new BookItem();
        	 
        	 Float price = Float.valueOf(item.getElementsByClass("a-size-base a-color-price s-price a-text-bold").first().text().substring(1));
        	 String title=item.getElementsByClass("a-link-normal s-access-detail-page  a-text-normal").first().attr("title");
        	 String url=item.getElementsByClass("a-link-normal s-access-detail-page  a-text-normal").first().attr("href");
        	 
        	 bookitem.setSite("亚马逊");
        	 bookitem.setUrl(url);
        	 bookitem.setPrice(price);
        	 bookitem.setTitle(title);
        	 arraylist.add(bookitem);
        	 count++;
        	 if(count>=10){
        		 break;
        	 }
         }*/
        
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
