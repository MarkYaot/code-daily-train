package czm.exception;

public class ClientException extends BaseException {
    public ClientException(String errorMsg) {
        this.setErrorMsg(errorMsg);
    }
}
