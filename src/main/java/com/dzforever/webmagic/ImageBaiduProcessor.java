package com.dzforever.webmagic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzforever.pojo.ImageSrc;
import com.dzforever.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.ArrayList;
import java.util.List;

public class ImageBaiduProcessor implements PageProcessor {

    private List<ImageSrc> imageSrcs = new ArrayList<>();
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
    @Autowired
    private IdWorker idWorker;

    @Override
    public void process(Page page) {
        JSONObject jsonObject = (JSONObject) JSONObject.parse(page.getRawText());
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            String url = (String) jsonArray.getJSONObject(i).get("thumbURL");
            String alt = (String) jsonArray.getJSONObject(i).get("fromPageTitleEnc");
            if (url != null) {
                ImageSrc imageSrc = new ImageSrc();
                imageSrc.setId(idWorker.nextId()+"");
                imageSrc.setImageStr(url);
                imageSrc.setImagealt(alt);
                imageSrcs.add(imageSrc);
            }
        }
//        //System.out.println(page.getRawText());
//        String context = page.getRawText();
//        String regexString = "\"objURL\":\"http://\\S*?.[a-z]+?\"";
//        Pattern compile = Pattern.compile(regexString);
//        Matcher matcher = compile.matcher(context);
////        System.out.println(matcher.find());
//        while (matcher.find()) {
//            String[] split = matcher.group().split("\"objURL\":\"");
//            String[] split1 = split[1].split("\"");
//            ImageSrc imageSrc = new ImageSrc();
//            imageSrc.setId(idWorker.nextId()+"");
//            imageSrc.setImageStr(split1[0]);
//            imageSrc.setImagealt();

//            System.out.println(matcher.group());
//
//        }
//        String context = page.getHtml().toString();
//        System.out.println(context);
//        JSONObject jsonObject = (JSONObject) JSONObject.parse(page.getRawText());
//        JSONArray data = (JSONArray) jsonObject.get("data");
//        for(int i=0;i<data.size();i++){
//            String url = (String) data.getJSONObject(i).get("thumbURL");
//            String name = (String) data.getJSONObject(i).get("fromPageTitleEnc");
//            if(url!=null){
//                System.out.println(url);
//                System.out.println(name);
//                url_list.add(url);
//                name_list.add(name);
//            }
//        }


    }

    public void newImageSrcs() {
        this.imageSrcs = new ArrayList<>();
    }
    public List<ImageSrc> getImageSrcs() {
        return this.imageSrcs;
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
//        Spider.create(new ImageBaiduProcessor())
//                .addUrl("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=index&fr=&hs=0&xthttps=111111&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=good&oq=good&rsp=-1")
//                .setScheduler(new QueueScheduler())
//                .run();
//        String key = "无敌";    //百度图片 关键词
////        DownloadPicture downloadPicture = new DownloadPicture();
//        ArrayList<String> nameList = new ArrayList<>();
//        ArrayList<String> urlList = new ArrayList<>();
//        for(int i=0;i<2;i++){   //控制爬取页数，一页30张图片
//            String url = "https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&queryWord="+key+"&word="+key+"&pn="+i*3+"0";
//            Spider.create(new ImageBaiduProcessor())
//                    .addUrl(url)
//                    .run();
//            System.out.println("这是输出-------------"+url);
//            urlList.addAll(urls);
//            nameList.addAll(names);
//        }
        Spider.create(new ImageBaiduProcessor())
                .addUrl("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&fm=result&pos=history&word=%E6%9D%83%E5%8A%9B%E7%9A%84%E6%B8%B8%E6%88%8F")
                .setScheduler(new QueueScheduler())
                .run();


    }
}
