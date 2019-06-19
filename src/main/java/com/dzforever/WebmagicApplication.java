package com.dzforever;

import com.dzforever.utils.IdWorker;
import com.dzforever.webmagic.ImageBaiduProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class WebmagicApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebmagicApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

    @Bean
    public ImageBaiduProcessor imageBaiduProcessor() {
        return new ImageBaiduProcessor();
    }
}
