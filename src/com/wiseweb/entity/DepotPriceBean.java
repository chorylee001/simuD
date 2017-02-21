package com.wiseweb.entity;

/**
 * Created by Chory on 2017/2/19 0019.
 * 站次价格表
 */
public class DepotPriceBean {

    private Integer id;
    private Integer depotId;
    private Integer seatLevel;
    private Integer bunk;
    private Double price;

    public DepotPriceBean() {
    }

    public DepotPriceBean(Integer id, Integer depotId, Integer seatLevel, Double price) {
        this.id = id;
        this.depotId = depotId;
        this.seatLevel = seatLevel;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepotId() {
        return depotId;
    }

    public void setDepotId(Integer depotId) {
        this.depotId = depotId;
    }

    public Integer getSeatLevel() {
        return seatLevel;
    }

    public void setSeatLevel(Integer seatLevel) {
        this.seatLevel = seatLevel;
    }

    public Integer getBunk() {
        return bunk;
    }

    public void setBunk(Integer bunk) {
        this.bunk = bunk;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
