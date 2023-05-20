package cn.mediinfo.springdemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ConfigurationProperties(prefix = "member")
public class MemberProperties {
    private String name;
    private int sex;
    private int age;
    private Date birthday;
    private String country;

    /**
     * @ConstructorBinding 指定要绑定的构造器方法
     * @param name
     * @param sex
     * @param age
     * @param country  @DefaultValue给默认值
     * @param birthday @DateTimeFormat对时间格式化
     */
    @ConstructorBinding
    public MemberProperties(String name, int sex, int age, @DefaultValue("杭州") String country, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date birthday) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.country=country;
    }


}
