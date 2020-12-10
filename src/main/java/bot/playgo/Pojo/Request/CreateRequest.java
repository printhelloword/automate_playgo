package bot.playgo.Pojo.Request;

public class CreateRequest {
    private String productId;

    private String userIdentifier;

    private String denominationId;

    private String validationToken;

    private String bonusId;

    public CreateRequest(String productId, String userIdentifier, String denominationId, String validationToken) {
        this.productId=productId;
        this.userIdentifier=userIdentifier;
        this.denominationId=denominationId;
        this.validationToken=validationToken;
    }

    public static CreateRequest withoutBonus(String productId, String userIdentifier, String denominationId, String validationToken) {
        return new CreateRequest(productId, userIdentifier, denominationId, validationToken);
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

    public String getUserIdentifier ()
    {
        return userIdentifier;
    }

    public void setUserIdentifier (String userIdentifier)
    {
        this.userIdentifier = userIdentifier;
    }

    public String getDenominationId ()
    {
        return denominationId;
    }

    public void setDenominationId (String denominationId)
    {
        this.denominationId = denominationId;
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
        return "ClassPojo [validationToken = "+validationToken+", productId = "+productId+", userIdentifier = "+userIdentifier+", denominationId = "+denominationId+", bonusId = "+bonusId+"]";
    }

}
