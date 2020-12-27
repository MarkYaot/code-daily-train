package czm.exception;

public class ServerException extends BaseException {
    public ServerException(String errorMsg) {
        this.setErrorMsg(errorMsg);
    }
}
