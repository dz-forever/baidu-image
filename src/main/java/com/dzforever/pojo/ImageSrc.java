package com.dzforever.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "imageSrc")
public class ImageSrc implements Serializable {
    @Id
    private String id;
    private String imageStr;
    private String imagealt;

    public ImageSrc() {
    }

    public ImageSrc(String id, String imageStr) {
        this.id = id;
        this.imageStr = imageStr;
    }

    public ImageSrc(String id, String imageStr, String imagealt) {
        this.id = id;
        this.imageStr = imageStr;
        this.imagealt = imagealt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

    public String getImagealt() {
        return imagealt;
    }

    public void setImagealt(String imagealt) {
        this.imagealt = imagealt;
    }
}
