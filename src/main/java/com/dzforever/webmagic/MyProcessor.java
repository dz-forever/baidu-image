package com.dzforever.webmagic;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().all());
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0‐9]{8}").all());

        System.out.println(page.getHtml().xpath("//* [@id=\"mainBox\"]/main/div[1]/div[1]/h1/text()").toString());
//        System.out.println(page.getHtml().xpath("//*[@id=\"nav\"]/div/div/ul/li[5]/a").toString());
        System.out.println("hello world");

//        System.out.println(page.getHtml().toString());
    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(300).setRetryTimes(3);
    }

    public static void main(String[] args) {
        Spider.create(new MyProcessor()).addUrl("http://blog.csdn.net/nav/ai").run();

    }
}
