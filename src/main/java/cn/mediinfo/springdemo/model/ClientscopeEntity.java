package cn.mediinfo.springdemo.model;

import jakarta.persistence.*;

import java.util.Objects;

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
