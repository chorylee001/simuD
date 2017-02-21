package com.wiseweb.entity;

/**
 * Created by Chory on 2017/2/19 0019.
 * 站次基本信息表
 */
public class DepotBean {

    /**
     * UNIQ()/ROW KEY	STRING	唯一ID
     TRAIN_NAME	STRING	列车车次
     TRAIN_TYPE	INT	车次类型
     STATION_NAME	STRING	车站名称
     STATION_NO	INT	车站顺序
     ARRIVAL_TIME	STRING	到达时间
     DEPARTURE_TIME	STRING	出发时间
     TASK_TIME	STRING	所用时间
     DISTANCE	DOUBLE	距离
     */
    private Integer id;
    private String trainName;
    private Integer trainType;
    private String stationName;
    private Integer stationNum;
    private String arrivalTime;
    private String departureTime;
    private String taskTime;
    private Double distance;

    public DepotBean() {
    }

    public DepotBean(Integer id, String trainName, Integer trainType) {
        this.id = id;
        this.trainName = trainName;
        this.trainType = trainType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Integer getTrainType() {
        return trainType;
    }

    public void setTrainType(Integer trainType) {
        this.trainType = trainType;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getStationNum() {
        return stationNum;
    }

    public void setStationNum(Integer stationNum) {
        this.stationNum = stationNum;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public static Integer[] getSeatLevel(Integer trainType){

        if(trainType == null){
            return null;
        }
        //1普快2空调普快3普客4空调普客5城际高速6动车组7高速动车8空调快速9快速10空调特快11直达特快12普慢
        switch (trainType){
            case 1:
                return new Integer[]{6,7,9,10};
            case 2:return new Integer[]{6,7,9,10};
            case 3:return new Integer[]{6,7,9,10};
            case 4:return new Integer[]{6,7,9,10};
            case 5:return new Integer[]{2,3,4,10};
            case 6:return new Integer[]{1,2,3,4};
            case 7:return new Integer[]{1,2,3,4};
            case 8:return new Integer[]{6,7,9,10};
            case 9:return new Integer[]{6,7,8,9,10};
            case 10:return new Integer[]{8,9,10};
            case 11:return new Integer[]{5,6,7,9,10};
            case 12:return new Integer[]{6,7,9,10};
            default:
                return null;
        }
    }
}
