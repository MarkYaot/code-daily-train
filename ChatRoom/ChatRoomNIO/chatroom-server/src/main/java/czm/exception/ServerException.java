package czm.exception;

public class ServerException extends Exception {
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ServerException(String errorMsg) {
        this.setErrorMsg(errorMsg);
    }
}
