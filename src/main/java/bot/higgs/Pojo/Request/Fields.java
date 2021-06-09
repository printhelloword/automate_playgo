package bot.higgs.Pojo.Request;

public class Fields {
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Fields(String userid) {
        this.userid = userid;
    }

    public static Fields ofUserId(String userid) {
        return new Fields(userid);
    }

    @Override
    public String toString() {
        return "Fields{" +
                "userid='" + userid + '\'' +
                '}';
    }

}
