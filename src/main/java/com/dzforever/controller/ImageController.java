package com.dzforever.controller;

import com.dzforever.entity.PageResult;
import com.dzforever.entity.Result;
import com.dzforever.entity.StateCode;
import com.dzforever.pojo.ImageSrc;
import com.dzforever.pojo.QueryObeject;
import com.dzforever.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/{page}/{size}/{pn}",method = RequestMethod.GET)
    public Result findAllPage(@PathVariable("page") int page,
                              @PathVariable("size") int size,
                              @PathVariable("pn") int pn,
                              QueryObeject queryObject) {
        PageResult<ImageSrc> pageResult = imageService.findAllPage(page,size,pn,queryObject);
        return new Result(true, StateCode.OK, "查询成功",pageResult);
    }
}
