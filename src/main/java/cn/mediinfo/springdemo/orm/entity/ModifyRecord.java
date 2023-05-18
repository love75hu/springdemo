package cn.mediinfo.springdemo.orm.entity;

import java.util.Date;

/**
 * 审计基类
 */
public interface ModifyRecord  {
    String getChuangJianRID();

    void setChuangJianRID(String chuangJianRID);

    String getChuangJianRXM();

    void setChuangJianRXM(String chuangJianRXM);

    Date getChuangJianSJ();

    void setChuangJianSJ(Date chuangJianSJ);

    String getXiuGaiRID();

    void setXiuGaiRID(String xiuGaiRID);

    String getXiuGaiRXM();

    void setXiuGaiRXM(String xiuGaiRXM);

    Date getXiuGaiSJ();

    void setXiuGaiSJ(Date xiuGaiSJ);
}
