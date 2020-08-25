package com.kaikai.doubanspider.PageProcessor;

import com.kaikai.doubanspider.Pipeline.FileOutputPipeline;
import com.kaikai.doubanspider.config.propertiesConfig;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/** 
* @author 作者 kaikai: 
* @version 创建时间：2020年8月18日 下午5:17:07 
* @Description 类说明 爬取豆瓣租房小组上的租房信息
*/
public class doubanPageProcessor implements PageProcessor {

	private static Spider myspider=null;
	//当前页面
	private long index=1;
	public static final String URL_LIST = "https://www.douban.com/group/gz_rent/discussion\\?start=\\d+";
    public static final String URL_POST = "https://www.douban.com/group/topic/\\w+/";
    private Site site = Site
            .me()
            .setDomain("www.douban.com")
            .setSleepTime(propertiesConfig.SleepTime)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {        
        if (page.getUrl().regex(URL_LIST).match()) {
        	if(propertiesConfig.TargetIndex>=index++) {
        		System.out.println("正在采集第 "+(index-1)+" 页!");
        		page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
            	page.addTargetRequests(page.getHtml().css(".next").links().all());
        	}else {
        		myspider.stop();
        		System.out.println("---采集完成 !---");
        	}
        } else {
        	page.putField("帖子标题", page.getHtml().css(".article h1","text")); 
        	page.putField("帖子副标题", page.getHtml().css(".tablecc","text")); 
        	page.putField("发布时间", page.getHtml().css(".create-time","text")); 
        	page.putField("发布者", page.getHtml().css(".from > a:nth-child(1)").links().toString());
        	page.putField("帖子链接", page.getUrl());
        	int i=1;
        	while(true) {
        		if(page.getHtml().css(".rich-content > p:nth-child("+i+")","text").toString()==null) {
        			break;
        		}else {
        			page.putField("内容"+i, page.getHtml().css(".rich-content > p:nth-child("+i+")","text")); 
        		} 
        		i++;
        	}
        }      
    }
    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
        myspider = Spider.create(new doubanPageProcessor());
        myspider.addUrl("https://www.douban.com/group/"+propertiesConfig.GroupCode+"/discussion?start=0")
		        //开启5个线程抓取
		        .thread(propertiesConfig.ThreadPoolSize)
		        //添加pipeline
		        //.addPipeline(new ConsolePipeline())
		        .addPipeline(new FileOutputPipeline())
		        //启动爬虫
		        .run();
    }
}
