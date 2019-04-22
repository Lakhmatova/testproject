package tests;
import okhttp3.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import testData.EmailDTO;
import testData.RESTAPI;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnOpen;
import java.io.IOException;

public class RESTAPITest extends BaseTest{


    private RESTAPI restapi = new RESTAPI();
    private static final int TIMEOUT = 5000; //seconds
    @Test
    public void checkLoginService()  {

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail("ttesttest651@gmail.com");
        emailDTO.setPassword("qazwsx098");
        restapi.postRequestToEmailService(emailDTO, "lang", true);
        restapi.checkAllHeaders();
        emailDTO.setEmail("ttesttest651@yandex.ru");
        emailDTO.setPassword("qazwsx098");
        restapi.postRequestToEmailService(emailDTO, "lang", false);
        emailDTO.setEmail("ttesttest651");
        emailDTO.setPassword("qazwsx098");
        restapi.postRequestToEmailService(emailDTO, "lang", false);
    }
@Test
    public void checkAPIRegister() {


    }

}
