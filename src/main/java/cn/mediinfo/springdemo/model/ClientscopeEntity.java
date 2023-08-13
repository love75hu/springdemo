package cn.mediinfo.springdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder //是 Lombok 库中的一个注解，用于简化 Java 类的构建过程。通过在类上添加 @Builder 注解，可以自动生成一个内部静态类 Builder，用于链式调用和构建对象。
@AllArgsConstructor //带参构造函数
@NoArgsConstructor  //无参构造函数
@Entity
@Table(name = "clientscope", schema = "demo", catalog = "")
public class ClientscopeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "scope")
    private String scope;
    @Basic
    @Column(name = "clientid")
    private String clientid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientscopeEntity that = (ClientscopeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(scope, that.scope) && Objects.equals(clientid, that.clientid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scope, clientid);
    }
}
