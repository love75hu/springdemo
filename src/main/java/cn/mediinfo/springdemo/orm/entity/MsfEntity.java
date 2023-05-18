package cn.mediinfo.springdemo.orm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public abstract class MsfEntity<T> implements IdEntity<T>, ModifyRecord, SoftDelete {
    private int zuoFeiBZ;

    private String chuangJianRID;

    private String chuangJianRXM;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date chuangJianSJ;

    private String xiuGaiRID;

    private String xiuGaiRXM;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date xiuGaiSJ;

    @Override
    public String getChuangJianRID() {
        return this.chuangJianRID;
    }

    @Override
    public void setChuangJianRID(String chuangJianRID) {
        this.chuangJianRID = chuangJianRID;
    }

    @Override
    public String getChuangJianRXM() {
        return chuangJianRXM;
    }

    @Override
    public void setChuangJianRXM(String chuangJianRXM) {
        this.chuangJianRXM = chuangJianRXM;
    }

    @Override
    public Date getChuangJianSJ() {
        return this.chuangJianSJ;
    }

    @Override
    public void setChuangJianSJ(Date chuangJianSJ) {
        this.chuangJianSJ = chuangJianSJ;
    }

    @Override
    public String getXiuGaiRID() {
        return this.xiuGaiRID;
    }

    @Override
    public void setXiuGaiRID(String xiuGaiRID) {
        this.xiuGaiRID = xiuGaiRID;
    }

    @Override
    public String getXiuGaiRXM() {
        return this.xiuGaiRXM;
    }

    @Override
    public void setXiuGaiRXM(String xiuGaiRXM) {
        this.xiuGaiRXM = xiuGaiRXM;
    }

    @Override
    public Date getXiuGaiSJ() {
        return this.xiuGaiSJ;
    }

    @Override
    public void setXiuGaiSJ(Date xiuGaiSJ) {
        this.xiuGaiSJ = xiuGaiSJ;
    }

    @Override
    public int getZuoFeiBZ() {
        return zuoFeiBZ;
    }

    @Override
    public void SetZuoFeiBZ(int zuoFeiBZ) {
        this.zuoFeiBZ = zuoFeiBZ;
    }
}
