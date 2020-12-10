package bot.playgo.Entity;

public class RequestValidity {
    private boolean status;
    private String message;

    public RequestValidity(){

    }

    public RequestValidity(boolean b) {
        this.status = b;
    }

    public static RequestValidity ofFalseWithMessage(String message){
        return new RequestValidity(false, message);
    }

    public static RequestValidity ofTrue(){
        return new RequestValidity(true);
    }

    public RequestValidity(boolean status, String message) {
        this.status = status;
        this.message = message;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
