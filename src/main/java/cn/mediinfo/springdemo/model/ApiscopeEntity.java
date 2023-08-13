package cn.mediinfo.springdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Objects;

@Entity
@Table(name = "apiscope", schema = "demo", catalog = "")
public class ApiscopeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "enabled")
    private byte enabled;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "displayname")
    private String displayname;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "required")
    private byte required;
    @Basic
    @Column(name = "emphasize")
    private byte emphasize;
    @Basic
    @Column(name = "showindiscoverydocument")
    private byte showindiscoverydocument;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getRequired() {
        return required;
    }

    public void setRequired(byte required) {
        this.required = required;
    }

    public byte getEmphasize() {
        return emphasize;
    }

    public void setEmphasize(byte emphasize) {
        this.emphasize = emphasize;
    }

    public byte getShowindiscoverydocument() {
        return showindiscoverydocument;
    }

    public void setShowindiscoverydocument(byte showindiscoverydocument) {
        this.showindiscoverydocument = showindiscoverydocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiscopeEntity that = (ApiscopeEntity) o;
        return enabled == that.enabled && required == that.required && emphasize == that.emphasize && showindiscoverydocument == that.showindiscoverydocument && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(displayname, that.displayname) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enabled, name, displayname, description, required, emphasize, showindiscoverydocument);
    }
}
