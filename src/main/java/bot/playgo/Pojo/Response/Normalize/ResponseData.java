package bot.playgo.Pojo.Response.Normalize;

public class ResponseData {
    private String msisdnMinLength;

    private String isMsisdnValid;

    private String originalMsisdn;

    private String msisdnMaxLength;

    private String normalizedMsisdn;

    private String operatorId;

    public String getMsisdnMinLength ()
    {
        return msisdnMinLength;
    }

    public void setMsisdnMinLength (String msisdnMinLength)
    {
        this.msisdnMinLength = msisdnMinLength;
    }

    public String getIsMsisdnValid ()
    {
        return isMsisdnValid;
    }

    public void setIsMsisdnValid (String isMsisdnValid)
    {
        this.isMsisdnValid = isMsisdnValid;
    }

    public String getOriginalMsisdn ()
    {
        return originalMsisdn;
    }

    public void setOriginalMsisdn (String originalMsisdn)
    {
        this.originalMsisdn = originalMsisdn;
    }

    public String getMsisdnMaxLength ()
    {
        return msisdnMaxLength;
    }

    public void setMsisdnMaxLength (String msisdnMaxLength)
    {
        this.msisdnMaxLength = msisdnMaxLength;
    }

    public String getNormalizedMsisdn ()
    {
        return normalizedMsisdn;
    }

    public void setNormalizedMsisdn (String normalizedMsisdn)
    {
        this.normalizedMsisdn = normalizedMsisdn;
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
        return "ClassPojo [msisdnMinLength = "+msisdnMinLength+", isMsisdnValid = "+isMsisdnValid+", originalMsisdn = "+originalMsisdn+", msisdnMaxLength = "+msisdnMaxLength+", normalizedMsisdn = "+normalizedMsisdn+", operatorId = "+operatorId+"]";
    }
}
