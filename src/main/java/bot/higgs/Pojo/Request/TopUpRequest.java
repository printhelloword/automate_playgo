package bot.higgs.Pojo.Request;

public class TopUpRequest {
    private String productId;

    private String validationToken;

    private String denominationId;

    private String subId;

    private String pin;

    private String userIdentifier;

    private String bonusId;

    public String getSubId ()
    {
        return subId;
    }

    public TopUpRequest(String validationToken, String productId, String pin, String denominationId, String userIdentifier) {
        this.validationToken = validationToken;
        this.productId = productId;
        this.pin = pin;
        this.denominationId = denominationId;
        this.userIdentifier = userIdentifier;
        this.subId="";
    }

    public static TopUpRequest withoutBonus(String validationToken, String productId, String pin, String denominationId, String userIdentifier){
        return new TopUpRequest(validationToken, productId, pin, denominationId, userIdentifier);
    }

    public void setSubId (String subId)
    {
        this.subId = subId;
    }

    public String getValidationToken ()
    {
        return validationToken;
    }

    public void setValidationToken (String validationToken)
    {
        this.validationToken = validationToken;
    }

    public String getProductId ()
    {
        return productId;
    }

    public void setProductId (String productId)
    {
        this.productId = productId;
    }

    public String getPin ()
    {
        return pin;
    }

    public void setPin (String pin)
    {
        this.pin = pin;
    }

    public String getDenominationId ()
    {
        return denominationId;
    }

    public void setDenominationId (String denominationId)
    {
        this.denominationId = denominationId;
    }

    public String getUserIdentifier ()
    {
        return userIdentifier;
    }

    public void setUserIdentifier (String userIdentifier)
    {
        this.userIdentifier = userIdentifier;
    }

    public String getBonusId ()
    {
        return bonusId;
    }

    public void setBonusId (String bonusId)
    {
        this.bonusId = bonusId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [subId = "+subId+", validationToken = "+validationToken+", productId = "+productId+", pin = "+pin+", denominationId = "+denominationId+", userIdentifier = "+userIdentifier+", bonusId = "+bonusId+"]";
    }
}

