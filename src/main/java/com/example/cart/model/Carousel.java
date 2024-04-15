package com.example.cart.model;

import java.io.Serializable;
import javax.persistence.*;


@Table(name = "carousel")
public class Carousel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "jdbc")
    @Column(name="carousel_id")
    private Integer carouselId;

    @Column(name="image_path")
    private String imgPath;

    @Column(name="describes")
    private String describes;

    private static final long serialVersionUID = 1L;

    public Integer getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", carouselId=").append(carouselId);
        sb.append(", imgPath=").append(imgPath);
        sb.append(", describes=").append(describes);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}