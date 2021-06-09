package bot.higgs.Pojo.Response;

public class CreateResponse {
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

