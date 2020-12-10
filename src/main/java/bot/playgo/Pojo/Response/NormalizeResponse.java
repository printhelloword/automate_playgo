package bot.playgo.Pojo.Response;

import bot.playgo.Pojo.Response.Normalize.AdditionalFields;
import bot.playgo.Pojo.Response.Normalize.ResponseData;

public class NormalizeResponse {
    private AdditionalFields additionalFields;

    private String inError;

    private String requestId;

    private ResponseData responseData;

    private String message;

    private String responseCode;

    public AdditionalFields getAdditionalFields ()
    {
        return additionalFields;
    }

    public void setAdditionalFields (AdditionalFields additionalFields)
    {
        this.additionalFields = additionalFields;
    }

    public String getInError ()
    {
        return inError;
    }

    public void setInError (String inError)
    {
        this.inError = inError;
    }

    public String getRequestId ()
    {
        return requestId;
    }

    public void setRequestId (String requestId)
    {
        this.requestId = requestId;
    }

    public ResponseData getResponseData ()
    {
        return responseData;
    }

    public void setResponseData (ResponseData responseData)
    {
        this.responseData = responseData;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getResponseCode ()
    {
        return responseCode;
    }

    public void setResponseCode (String responseCode)
    {
        this.responseCode = responseCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [additionalFields = "+additionalFields+", inError = "+inError+", requestId = "+requestId+", responseData = "+responseData+", message = "+message+", responseCode = "+responseCode+"]";
    }

}
