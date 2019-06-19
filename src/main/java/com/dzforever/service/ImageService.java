package com.dzforever.service;

import com.dzforever.dao.ImageDao;
import com.dzforever.entity.PageResult;
import com.dzforever.pojo.ImageSrc;
import com.dzforever.pojo.QueryObeject;
import com.dzforever.utils.IdWorker;
import com.dzforever.webmagic.ImageBaiduProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.List;

@Service
@Transactional
public class ImageService {
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
//    @Autowired
//    private ImageProcessor imageProcessor;
    @Autowired
    private ImageBaiduProcessor imageBaiduProcessor;

//    @CachePut(value = "",key = "")
    public PageResult<ImageSrc> findAllPage(int page, int size, int pn, QueryObeject queryObeject) {
//        ImageProcessor imageProcessor = new ImageProcessor();
        String queryString = queryObeject.getQueryString();
        Spider.create(imageBaiduProcessor)
                .addUrl("https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=" +queryString+
                        "&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=0&hd=&latest=&copyright=&word=" +queryString+
                        "&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&expermode=&force=&pn="+pn*3+"0")
//                .addPipeline(new FilePipeline("D:/"+queryString))
                .setScheduler(new QueueScheduler())
                .run();
        PageResult<ImageSrc> pageResult = new PageResult<>();
        List<ImageSrc> imageSrc = imageBaiduProcessor.getImageSrcs();
        imageBaiduProcessor.newImageSrcs();
        pageResult.setTotal((long)(imageSrc.size()));
        int start = (page-1)*size;
        int end = page*size;
        if (end > imageSrc.size()) {
            end = imageSrc.size();

        }
        List<ImageSrc> rowsSrc = imageSrc.subList(start, end);
        pageResult.setRows(rowsSrc);
        return pageResult;
    }
}
