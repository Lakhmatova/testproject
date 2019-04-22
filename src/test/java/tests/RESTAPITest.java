package tests;

import okhttp3.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import testData.EmailDTO;
import testData.RegistrationDTO;
import testData.RESTAPI;
import testData.RegistrationDTO;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnOpen;
import java.io.IOException;

public class RESTAPITest extends BaseTest {


    private RESTAPI restapi = new RESTAPI();
    private static final int TIMEOUT = 5000; //seconds

    @Test
    public void checkLoginService() {
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
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setEmail("ttesttest651@gmail.com");
        registrationDTO.setPassword("qazwsx098");
        registrationDTO.setFirst_name("Test");
        registrationDTO.setLast_name("test345");
        registrationDTO.setTz("Europe/Moscow");
        restapi.postRequestToRegistartion(registrationDTO, "ssid path", true);
        registrationDTO.setEmail("test@gmail.com");
        registrationDTO.setPassword("12345qwerty");
        registrationDTO.setFirst_name("Test");
        registrationDTO.setLast_name("Testest");
        registrationDTO.setTz("Europe/Milan");
        restapi.postRequestToRegistartion(registrationDTO, "ru_RU", true);
        registrationDTO.setEmail("test@gmail.com");
        registrationDTO.setPassword("12345q");
        registrationDTO.setFirst_name("Test");
        registrationDTO.setLast_name("Testest");
        registrationDTO.setTz("Europe/Milan");
        restapi.postRequestToRegistartion(registrationDTO, "ru_RU", true);
        registrationDTO.setEmail("test@gmail.com");
        registrationDTO.setPassword("12345q");
        registrationDTO.setFirst_name("");
        registrationDTO.setLast_name("Testest");
        registrationDTO.setTz("Europe/Milan");
        restapi.postRequestToRegistartion(registrationDTO, "ru_RU", true);
        restapi.getProfile("lang");
    }

}
