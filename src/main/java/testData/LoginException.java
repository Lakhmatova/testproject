package testData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LoginException extends RuntimeException {
    private Logger getLogger() {
        return LoggerFactory.getLogger(LoginException.class);
    }

    public LoginException() {
        getLogger().error("Failed login to the mShop");
    }

    public LoginException(String message) {
        super(message);
    }

}
