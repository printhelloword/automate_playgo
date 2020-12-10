package bot.playgo.Entity;

public class Request {
    private String trxId;
    private String denom;
    private String playerId;

    public Request() {
    }

    public Request(String playerId, String denom, String trxId) {
        this.playerId = playerId;
        this.denom = denom;
        this.trxId = trxId;
    }

    public static Request ofParams(String playerId, String denom, String trxId) {
        return new Request(playerId, denom, trxId);
    }

    public String getTrxId() {
        return trxId;
    }

    public String getDenom() {
        return denom;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String mergedRequestSeparatedBySlash(){
        return playerId+ "/" +denom+ "/" +trxId;
    }
}
