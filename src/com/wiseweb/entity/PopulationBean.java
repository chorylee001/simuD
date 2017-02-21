package com.wiseweb.entity;

import java.util.Date;

/**
 * Created by Chory on 2017/2/17 0017.
 * 人口属性基础信息实体类
 */
public class PopulationBean {

    private Integer id;
    private String name;
    private String passengerType;
    private String certificateType;
    private String certificateCode;
    private Integer userId;
    private String userName;
    private String sex;
    private String country;
    private String birthday;
    private String nation;
    private String phoneNumber;
    private String emailAddress;
    private String postCode;
    private String address;
    private String wnId;
    private Date registerTime;

    public PopulationBean() {
    }

    public PopulationBean(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWnId() {
        return wnId;
    }

    public void setWnId(String wnId) {
        this.wnId = wnId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * PopulationBean population = new PopulationBean();
     population.setId(rs.getInt("ID"));
     population.setName(rs.getString("name"));
     population.setPassengerType(rs.getString("PASSENGER_TYPE"));
     population.setCertificateType(rs.getString("CRETIFICATE_TYPE"));
     population.setCertificateCode(rs.getString("CERTIFICATE_CODE"));
     population.setUserId(rs.getInt("user_id"));
     population.setUserName(rs.getString("user_name"));
     population.setSex(rs.getInt("sex"));
     population.setCountry(rs.getString("country"));
     population.setBirthday(rs.getString("birthday"));
     population.setNation(rs.getString("nation"));
     population.setPhoneNumber(rs.getString("phonenumber"));
     population.setEmailAddress(rs.getString("email_address"));
     population.setPostCode(rs.getString("postcode"));
     population.setAddress(rs.getString("address"));
     population.setWnId(rs.getString("wn_id"));
     population.setRegisterTime(rs.getDate("register_time"));
     */
}
