package com.hbnu.pojo;

import java.util.List;

public class User {
    private Integer id;
    private String name;
    private String password;
    private String stuCode;
    private String dormcode;
    private String sex;
    private String tel;
    private Integer dormBuildId;
    private Integer roleId;
    private Integer creatUserId;
    private Integer disabled;


    private List<DormBuild> dormBuilds;  //管理的宿舍楼

    public List<DormBuild> getDormBuilds() {
        return dormBuilds;
    }

    public void setDormBuilds(List<DormBuild> dormBuilds) {
        this.dormBuilds = dormBuilds;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getStuCode() {
        return stuCode;
    }

    public String getDormcode() {
        return dormcode;
    }

    public String getSex() {
        return sex;
    }

    public String getTel() {
        return tel;
    }

    public Integer getDormBuildId() {
        return dormBuildId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Integer getCreatUserId() {
        return creatUserId;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public void setDormcode(String dormcode) {
        this.dormcode = dormcode;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDormBuildId(Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setCreatUserId(Integer creatUserId) {
        this.creatUserId = creatUserId;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", stuCode='" + stuCode + '\'' +
                ", dormcode='" + dormcode + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", dormBuildI=" + dormBuildId +
                ", roleId=" + roleId +
                ", creatUserId=" + creatUserId +
                ", disabled=" + disabled +
                '}';
    }
}
