package cn.mediinfo.springdemo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "client", schema = "demo", catalog = "")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "enabled")
    private byte enabled;
    @Basic
    @Column(name = "clientid")
    private String clientid;
    @Basic
    @Column(name = "protocoltype")
    private String protocoltype;
    @Basic
    @Column(name = "requireclientsecret")
    private byte requireclientsecret;
    @Basic
    @Column(name = "clientname")
    private String clientname;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "clienturi")
    private String clienturi;
    @Basic
    @Column(name = "logouri")
    private String logouri;
    @Basic
    @Column(name = "requireconsent")
    private byte requireconsent;
    @Basic
    @Column(name = "allowrememberconsent")
    private byte allowrememberconsent;
    @Basic
    @Column(name = "alwaysincludeuserclaimsinidtoken")
    private byte alwaysincludeuserclaimsinidtoken;
    @Basic
    @Column(name = "requirepkce")
    private byte requirepkce;
    @Basic
    @Column(name = "allowplaintextpkce")
    private byte allowplaintextpkce;
    @Basic
    @Column(name = "requirerequestobject")
    private byte requirerequestobject;
    @Basic
    @Column(name = "allowaccesstokensviabrowser")
    private byte allowaccesstokensviabrowser;
    @Basic
    @Column(name = "frontchannellogouturi")
    private String frontchannellogouturi;
    @Basic
    @Column(name = "frontchannellogoutsessionrequired")
    private byte frontchannellogoutsessionrequired;
    @Basic
    @Column(name = "backchannellogouturi")
    private String backchannellogouturi;
    @Basic
    @Column(name = "backchannellogoutsessionrequired")
    private byte backchannellogoutsessionrequired;
    @Basic
    @Column(name = "allowofflineaccess")
    private byte allowofflineaccess;
    @Basic
    @Column(name = "identitytokenlifetime")
    private int identitytokenlifetime;
    @Basic
    @Column(name = "allowedidentitytokensigningalgorithms")
    private String allowedidentitytokensigningalgorithms;
    @Basic
    @Column(name = "accesstokenlifetime")
    private int accesstokenlifetime;
    @Basic
    @Column(name = "authorizationcodelifetime")
    private int authorizationcodelifetime;
    @Basic
    @Column(name = "consentlifetime")
    private Integer consentlifetime;
    @Basic
    @Column(name = "absoluterefreshtokenlifetime")
    private int absoluterefreshtokenlifetime;
    @Basic
    @Column(name = "slidingrefreshtokenlifetime")
    private int slidingrefreshtokenlifetime;
    @Basic
    @Column(name = "refreshtokenusage")
    private int refreshtokenusage;
    @Basic
    @Column(name = "updateaccesstokenclaimsonrefresh")
    private byte updateaccesstokenclaimsonrefresh;
    @Basic
    @Column(name = "refreshtokenexpiration")
    private int refreshtokenexpiration;
    @Basic
    @Column(name = "accesstokentype")
    private int accesstokentype;
    @Basic
    @Column(name = "enablelocallogin")
    private byte enablelocallogin;
    @Basic
    @Column(name = "includejwtid")
    private byte includejwtid;
    @Basic
    @Column(name = "alwayssendclientclaims")
    private byte alwayssendclientclaims;
    @Basic
    @Column(name = "clientclaimsprefix")
    private String clientclaimsprefix;
    @Basic
    @Column(name = "pairwisesubjectsalt")
    private String pairwisesubjectsalt;
    @Basic
    @Column(name = "userssolifetime")
    private Integer userssolifetime;
    @Basic
    @Column(name = "usercodetype")
    private String usercodetype;
    @Basic
    @Column(name = "devicecodelifetime")
    private int devicecodelifetime;
    @Basic
    @Column(name = "noneditable")
    private byte noneditable;

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

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getProtocoltype() {
        return protocoltype;
    }

    public void setProtocoltype(String protocoltype) {
        this.protocoltype = protocoltype;
    }

    public byte getRequireclientsecret() {
        return requireclientsecret;
    }

    public void setRequireclientsecret(byte requireclientsecret) {
        this.requireclientsecret = requireclientsecret;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClienturi() {
        return clienturi;
    }

    public void setClienturi(String clienturi) {
        this.clienturi = clienturi;
    }

    public String getLogouri() {
        return logouri;
    }

    public void setLogouri(String logouri) {
        this.logouri = logouri;
    }

    public byte getRequireconsent() {
        return requireconsent;
    }

    public void setRequireconsent(byte requireconsent) {
        this.requireconsent = requireconsent;
    }

    public byte getAllowrememberconsent() {
        return allowrememberconsent;
    }

    public void setAllowrememberconsent(byte allowrememberconsent) {
        this.allowrememberconsent = allowrememberconsent;
    }

    public byte getAlwaysincludeuserclaimsinidtoken() {
        return alwaysincludeuserclaimsinidtoken;
    }

    public void setAlwaysincludeuserclaimsinidtoken(byte alwaysincludeuserclaimsinidtoken) {
        this.alwaysincludeuserclaimsinidtoken = alwaysincludeuserclaimsinidtoken;
    }

    public byte getRequirepkce() {
        return requirepkce;
    }

    public void setRequirepkce(byte requirepkce) {
        this.requirepkce = requirepkce;
    }

    public byte getAllowplaintextpkce() {
        return allowplaintextpkce;
    }

    public void setAllowplaintextpkce(byte allowplaintextpkce) {
        this.allowplaintextpkce = allowplaintextpkce;
    }

    public byte getRequirerequestobject() {
        return requirerequestobject;
    }

    public void setRequirerequestobject(byte requirerequestobject) {
        this.requirerequestobject = requirerequestobject;
    }

    public byte getAllowaccesstokensviabrowser() {
        return allowaccesstokensviabrowser;
    }

    public void setAllowaccesstokensviabrowser(byte allowaccesstokensviabrowser) {
        this.allowaccesstokensviabrowser = allowaccesstokensviabrowser;
    }

    public String getFrontchannellogouturi() {
        return frontchannellogouturi;
    }

    public void setFrontchannellogouturi(String frontchannellogouturi) {
        this.frontchannellogouturi = frontchannellogouturi;
    }

    public byte getFrontchannellogoutsessionrequired() {
        return frontchannellogoutsessionrequired;
    }

    public void setFrontchannellogoutsessionrequired(byte frontchannellogoutsessionrequired) {
        this.frontchannellogoutsessionrequired = frontchannellogoutsessionrequired;
    }

    public String getBackchannellogouturi() {
        return backchannellogouturi;
    }

    public void setBackchannellogouturi(String backchannellogouturi) {
        this.backchannellogouturi = backchannellogouturi;
    }

    public byte getBackchannellogoutsessionrequired() {
        return backchannellogoutsessionrequired;
    }

    public void setBackchannellogoutsessionrequired(byte backchannellogoutsessionrequired) {
        this.backchannellogoutsessionrequired = backchannellogoutsessionrequired;
    }

    public byte getAllowofflineaccess() {
        return allowofflineaccess;
    }

    public void setAllowofflineaccess(byte allowofflineaccess) {
        this.allowofflineaccess = allowofflineaccess;
    }

    public int getIdentitytokenlifetime() {
        return identitytokenlifetime;
    }

    public void setIdentitytokenlifetime(int identitytokenlifetime) {
        this.identitytokenlifetime = identitytokenlifetime;
    }

    public String getAllowedidentitytokensigningalgorithms() {
        return allowedidentitytokensigningalgorithms;
    }

    public void setAllowedidentitytokensigningalgorithms(String allowedidentitytokensigningalgorithms) {
        this.allowedidentitytokensigningalgorithms = allowedidentitytokensigningalgorithms;
    }

    public int getAccesstokenlifetime() {
        return accesstokenlifetime;
    }

    public void setAccesstokenlifetime(int accesstokenlifetime) {
        this.accesstokenlifetime = accesstokenlifetime;
    }

    public int getAuthorizationcodelifetime() {
        return authorizationcodelifetime;
    }

    public void setAuthorizationcodelifetime(int authorizationcodelifetime) {
        this.authorizationcodelifetime = authorizationcodelifetime;
    }

    public Integer getConsentlifetime() {
        return consentlifetime;
    }

    public void setConsentlifetime(Integer consentlifetime) {
        this.consentlifetime = consentlifetime;
    }

    public int getAbsoluterefreshtokenlifetime() {
        return absoluterefreshtokenlifetime;
    }

    public void setAbsoluterefreshtokenlifetime(int absoluterefreshtokenlifetime) {
        this.absoluterefreshtokenlifetime = absoluterefreshtokenlifetime;
    }

    public int getSlidingrefreshtokenlifetime() {
        return slidingrefreshtokenlifetime;
    }

    public void setSlidingrefreshtokenlifetime(int slidingrefreshtokenlifetime) {
        this.slidingrefreshtokenlifetime = slidingrefreshtokenlifetime;
    }

    public int getRefreshtokenusage() {
        return refreshtokenusage;
    }

    public void setRefreshtokenusage(int refreshtokenusage) {
        this.refreshtokenusage = refreshtokenusage;
    }

    public byte getUpdateaccesstokenclaimsonrefresh() {
        return updateaccesstokenclaimsonrefresh;
    }

    public void setUpdateaccesstokenclaimsonrefresh(byte updateaccesstokenclaimsonrefresh) {
        this.updateaccesstokenclaimsonrefresh = updateaccesstokenclaimsonrefresh;
    }

    public int getRefreshtokenexpiration() {
        return refreshtokenexpiration;
    }

    public void setRefreshtokenexpiration(int refreshtokenexpiration) {
        this.refreshtokenexpiration = refreshtokenexpiration;
    }

    public int getAccesstokentype() {
        return accesstokentype;
    }

    public void setAccesstokentype(int accesstokentype) {
        this.accesstokentype = accesstokentype;
    }

    public byte getEnablelocallogin() {
        return enablelocallogin;
    }

    public void setEnablelocallogin(byte enablelocallogin) {
        this.enablelocallogin = enablelocallogin;
    }

    public byte getIncludejwtid() {
        return includejwtid;
    }

    public void setIncludejwtid(byte includejwtid) {
        this.includejwtid = includejwtid;
    }

    public byte getAlwayssendclientclaims() {
        return alwayssendclientclaims;
    }

    public void setAlwayssendclientclaims(byte alwayssendclientclaims) {
        this.alwayssendclientclaims = alwayssendclientclaims;
    }

    public String getClientclaimsprefix() {
        return clientclaimsprefix;
    }

    public void setClientclaimsprefix(String clientclaimsprefix) {
        this.clientclaimsprefix = clientclaimsprefix;
    }

    public String getPairwisesubjectsalt() {
        return pairwisesubjectsalt;
    }

    public void setPairwisesubjectsalt(String pairwisesubjectsalt) {
        this.pairwisesubjectsalt = pairwisesubjectsalt;
    }

    public Integer getUserssolifetime() {
        return userssolifetime;
    }

    public void setUserssolifetime(Integer userssolifetime) {
        this.userssolifetime = userssolifetime;
    }

    public String getUsercodetype() {
        return usercodetype;
    }

    public void setUsercodetype(String usercodetype) {
        this.usercodetype = usercodetype;
    }

    public int getDevicecodelifetime() {
        return devicecodelifetime;
    }

    public void setDevicecodelifetime(int devicecodelifetime) {
        this.devicecodelifetime = devicecodelifetime;
    }

    public byte getNoneditable() {
        return noneditable;
    }

    public void setNoneditable(byte noneditable) {
        this.noneditable = noneditable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return enabled == that.enabled && requireclientsecret == that.requireclientsecret && requireconsent == that.requireconsent && allowrememberconsent == that.allowrememberconsent && alwaysincludeuserclaimsinidtoken == that.alwaysincludeuserclaimsinidtoken && requirepkce == that.requirepkce && allowplaintextpkce == that.allowplaintextpkce && requirerequestobject == that.requirerequestobject && allowaccesstokensviabrowser == that.allowaccesstokensviabrowser && frontchannellogoutsessionrequired == that.frontchannellogoutsessionrequired && backchannellogoutsessionrequired == that.backchannellogoutsessionrequired && allowofflineaccess == that.allowofflineaccess && identitytokenlifetime == that.identitytokenlifetime && accesstokenlifetime == that.accesstokenlifetime && authorizationcodelifetime == that.authorizationcodelifetime && absoluterefreshtokenlifetime == that.absoluterefreshtokenlifetime && slidingrefreshtokenlifetime == that.slidingrefreshtokenlifetime && refreshtokenusage == that.refreshtokenusage && updateaccesstokenclaimsonrefresh == that.updateaccesstokenclaimsonrefresh && refreshtokenexpiration == that.refreshtokenexpiration && accesstokentype == that.accesstokentype && enablelocallogin == that.enablelocallogin && includejwtid == that.includejwtid && alwayssendclientclaims == that.alwayssendclientclaims && devicecodelifetime == that.devicecodelifetime && noneditable == that.noneditable && Objects.equals(id, that.id) && Objects.equals(clientid, that.clientid) && Objects.equals(protocoltype, that.protocoltype) && Objects.equals(clientname, that.clientname) && Objects.equals(description, that.description) && Objects.equals(clienturi, that.clienturi) && Objects.equals(logouri, that.logouri) && Objects.equals(frontchannellogouturi, that.frontchannellogouturi) && Objects.equals(backchannellogouturi, that.backchannellogouturi) && Objects.equals(allowedidentitytokensigningalgorithms, that.allowedidentitytokensigningalgorithms) && Objects.equals(consentlifetime, that.consentlifetime) && Objects.equals(clientclaimsprefix, that.clientclaimsprefix) && Objects.equals(pairwisesubjectsalt, that.pairwisesubjectsalt) && Objects.equals(userssolifetime, that.userssolifetime) && Objects.equals(usercodetype, that.usercodetype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enabled, clientid, protocoltype, requireclientsecret, clientname, description, clienturi, logouri, requireconsent, allowrememberconsent, alwaysincludeuserclaimsinidtoken, requirepkce, allowplaintextpkce, requirerequestobject, allowaccesstokensviabrowser, frontchannellogouturi, frontchannellogoutsessionrequired, backchannellogouturi, backchannellogoutsessionrequired, allowofflineaccess, identitytokenlifetime, allowedidentitytokensigningalgorithms, accesstokenlifetime, authorizationcodelifetime, consentlifetime, absoluterefreshtokenlifetime, slidingrefreshtokenlifetime, refreshtokenusage, updateaccesstokenclaimsonrefresh, refreshtokenexpiration, accesstokentype, enablelocallogin, includejwtid, alwayssendclientclaims, clientclaimsprefix, pairwisesubjectsalt, userssolifetime, usercodetype, devicecodelifetime, noneditable);
    }
}
