package bot.higgs.Pojo.Response.Normalize;

public class AdditionalFields {
    private String TIME_TAKEN;

    private String HOST;

    public String getTIME_TAKEN ()
    {
        return TIME_TAKEN;
    }

    public void setTIME_TAKEN (String TIME_TAKEN)
    {
        this.TIME_TAKEN = TIME_TAKEN;
    }

    public String getHOST ()
    {
        return HOST;
    }

    public void setHOST (String HOST)
    {
        this.HOST = HOST;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [TIME_TAKEN = "+TIME_TAKEN+", HOST = "+HOST+"]";
    }
}
