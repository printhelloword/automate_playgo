package bot.higgs.Pojo.Request;

public class ValidateRequest {
    private Fields fields;

    private String productId;

    public ValidateRequest(Fields fields, String productId) {
        this.fields = fields;
        this.productId = productId;
    }

    public static ValidateRequest ofFieldsAndProductId(Fields fields, String product_id_free_fire) {
        return new ValidateRequest(fields, product_id_free_fire);
    }

    public String getProductId ()
    {
        return productId;
    }

    public void setProductId (String productId)
    {
        this.productId = productId;
    }

    public Fields getFields ()
    {
        return fields;
    }

    public void setFields (Fields fields)
    {
        this.fields = fields;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [productId = "+productId+", fields = "+fields+"]";
    }
}
