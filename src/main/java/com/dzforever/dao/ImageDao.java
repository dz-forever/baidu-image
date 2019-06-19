package com.dzforever.dao;

import com.dzforever.pojo.ImageSrc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageDao extends JpaRepository<ImageSrc,String >, JpaSpecificationExecutor {
}
