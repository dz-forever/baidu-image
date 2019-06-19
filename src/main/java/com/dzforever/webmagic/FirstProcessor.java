package com.dzforever.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;

public class FirstProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
//        System.out.println(page.getHtml());
        System.out.println("------------------------------------------------------------------------");
        page.addTargetRequests(page.getHtml().xpath("//*[@id=\"nav\"]/div/div/ul/li[5]/a").links().all());
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 _]+/article/details/[0-9]{8}").all());
        //System.out.println(page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1"));
        //System.out.println(page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/article"));
        page.putField("title",page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1").toString());
        page.putField("article",page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/article").toString());
    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(300).setRetryTimes(3);
    }

    public static void main(String[] args) {
        Spider.create(new FirstProcessor())
                .addUrl("https://www.csdn.net/")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new FilePipeline("D:/data"))
                .setScheduler(new QueueScheduler())
                .run();
    }
}
