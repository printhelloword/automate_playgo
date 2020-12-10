package bot.playgo.Pojo.Response;

public class ValidateResponse {
    private String validationToken;

    private String username;

    public String getValidationToken ()
    {
        return validationToken;
    }

    public void setValidationToken (String validationToken)
    {
        this.validationToken = validationToken;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [validationToken = "+validationToken+", username = "+username+"]";
    }

}
