package cn.mediinfo.springdemo.orm.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class StringMsfEntity extends MsfEntity<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
