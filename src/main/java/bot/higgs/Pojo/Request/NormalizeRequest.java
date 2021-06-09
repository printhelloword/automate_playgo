package bot.higgs.Pojo.Request;

public class NormalizeRequest {

    private String msisdn;

    private String operatorId;

    public NormalizeRequest(String msisdn, String default_operator_id) {
        this.msisdn=msisdn;
        this.operatorId=default_operator_id;
    }

    public static NormalizeRequest ofMsisdnAndOperatorId(String msisdn, String default_operator_id) {
        return new NormalizeRequest(msisdn, default_operator_id);
    }

    public String getMsisdn ()
    {
        return msisdn;
    }

    public void setMsisdn (String msisdn)
    {
        this.msisdn = msisdn;
    }

    public String getOperatorId ()
    {
        return operatorId;
    }

    public void setOperatorId (String operatorId)
    {
        this.operatorId = operatorId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [msisdn = "+msisdn+", operatorId = "+operatorId+"]";
    }

}
