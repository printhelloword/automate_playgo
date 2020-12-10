package bot.playgo.Entity;

public class Voucher {
    private String playerId;
    private String denomId;
    private String denomBonusId;

    public String getPlayerId() {
        return playerId;
    }

    public String getDenomId() {
        return denomId;
    }

    public String getDenomBonusId() {
        return denomBonusId;
    }

    public Voucher(String playerId, String denomId, String denomBonusId) {
        this.playerId = playerId;
        this.denomId = denomId;
        this.denomBonusId = denomBonusId;
    }

    public static Voucher withBonus(String playerId, String denomId, String denomBonusId){
        return new Voucher(playerId, denomId, denomBonusId);
    }

    public static Voucher withoutBonus(String playerId, String denomId){
        return new Voucher(playerId, denomId);
    }

    public void setDenomBonusId(String denomBonusId) {
        this.denomBonusId = denomBonusId;
    }

    public Voucher(String playerId, String denomId) {
        this.playerId = playerId;
        this.denomId = denomId;
    }
}


