package czm.exception;

public class ClientException extends Exception {
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ClientException(String errorMsg) {
        this.setErrorMsg(errorMsg);
    }
}
