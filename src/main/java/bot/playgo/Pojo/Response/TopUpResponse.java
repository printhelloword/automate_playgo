package bot.playgo.Pojo.Response;

public class TopUpResponse {
    private String resultCode;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "ClassPojo [resultCode = " + resultCode + "]";
    }

}
