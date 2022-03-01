package cn.zhgliu.ezdp.user.vo;

public class UserInfo {

    private String userId;

    private String userName;

    private String email;

    private String mobilePhone;

    public String getUserId() {
        return userId;
    }

    public UserInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserInfo setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public UserInfo setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }
}
