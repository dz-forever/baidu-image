package com.dzforever.webmagic;

import com.dzforever.pojo.ImageSrc;
import com.dzforever.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class ImageProcessor implements PageProcessor {
    private List<ImageSrc> imageSrcs = new ArrayList<>();
    @Autowired
    private IdWorker idWorker;
    public void process(Page page) {
        String context = page.getHtml().xpath("//*[@id=\"content\"]/div/div[2]/div/div[2]").toString();
        String regexString = "src=\"https://cdn.pixabay.com/photo/[0-9]{4}/[0-9]{2}/[0-9]{2}/[0-9]+/[0-9]+/[a-z]+-[0-9]{7}__340\\.[a-z]{3}\"\\s+alt=\"[\\s\\S]*?\"";
        Pattern compile = Pattern.compile(regexString);
        Matcher matcher = compile.matcher(context);
 //       System.out.println(matcher.find());
        while (matcher.find()) {
           // System.out.println(matcher.toString());
//            System.out.println(matcher.group());
            String strAll = matcher.group();
            String[] split = strAll.split("src=\"");
            String[] split1 = split[1].split("\" alt=\"");
            String[] split2 = split1[1].split("\"");
//            System.out.println(split1[0]);
//            System.out.println(split2[0]);

            ImageSrc imageSrc = new ImageSrc();
            imageSrc.setId(idWorker.nextId()+"");
            imageSrc.setImageStr(split1[0]);
            imageSrc.setImagealt(split1[1]);
            imageSrcs.add(imageSrc);
//            System.out.println(split[1]);
        }
//        if (matcher.find()) {
//            System.out.println(matcher.find());
//        }
       // System.out.println(context);

//        page.putField("图片",context);

    }

    public List<ImageSrc> getImageSrc() {
        return this.imageSrcs;
    }
    @Override
    public Site getSite() {
        return Site.me().setSleepTime(300).setRetryTimes(3);
    }


    public static void main(String[] args) {
        // String url = "%E4%B8%AD%E5%9B%BD%E4%BA%BA";
//        String name = "中国人";
//        String url = "";
//        try {
//            name = URLDecoder.decode(url, "utf-8");
//            url = URLEncoder.encode(name, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(url);
//        System.out.println();
        Spider.create(new ImageProcessor())
                .addUrl("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&sf=1&fmq=&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&fm=index&pos=history&word=无敌")
               // .addPipeline(new FilePipeline("D:/image"))
                .setScheduler(new QueueScheduler())
                .run();

    }
}
