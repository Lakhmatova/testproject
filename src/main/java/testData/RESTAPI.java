package testData;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import static org.testng.Assert.*;

public class RESTAPI implements RestClient{

    private static final String URL_Login = "https://auth.iqoption.com/api/v1.0/login";
    private static final String URL_Register = "https://iqoption.com/api/register";
    //https://iqoption.com?api=register
    private static final String URL_Getprofile = "https://iqoption.com/api/getprofile";


    private RestTemplate restTemplate = new RestTemplate();

    private Logger getLogger() {
        return LoggerFactory.getLogger(RESTAPI.class);
    }

    public void postRequestToEmailService(EmailDTO emailDTO,String header, boolean is200Status) {
        HttpEntity<EmailDTO> request = new HttpEntity<>(emailDTO, setAuthHeadersAny(header));
        postRequestToEmailService(request, is200Status);
    }

    private void postRequestToEmailService(HttpEntity request, boolean is200Status) {
        try {
            ResponseEntity<EmailDTO> response = restTemplate.postForEntity(URL_Login, request, EmailDTO.class);

            getLogger().info(response.getStatusCode().toString());
            boolean status200 = response.getStatusCode().is2xxSuccessful();
            assertTrue(is200Status && status200, "Status is not '200 OK'");
        } catch (HttpClientErrorException e) {
            getLogger().info(e.getMessage());
        }
   }

    public void checkAllHeaders()  {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL_Login);
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            System.out.println("Key : " + header.getName()
                    + " ,Value : " + header.getValue());
        }
    }

    public void postRequestToRegistartion(RegistrationDTO registrationDTO,String header, boolean is200Status) {
        HttpEntity<RegistrationDTO> request = new HttpEntity<>(registrationDTO, setAuthHeadersAny(header));
        postRequestToRegistration(request, is200Status);
    }
    private void postRequestToRegistration(HttpEntity request, boolean is200Status) {
        try {
            ResponseEntity<RegistrationDTO> response = restTemplate.postForEntity(URL_Register, request, RegistrationDTO.class);
            getLogger().info(response.getStatusCode().toString());
            boolean status200 = response.getStatusCode().is2xxSuccessful();
            assertTrue(is200Status && status200, "Status is not '200 OK'");
        } catch (HttpClientErrorException e) {
            getLogger().info(e.getMessage());
        }
    }
    public JsonNode getProfile(String header) {
        HttpEntity<String> entity = new HttpEntity<>(setAuthHeadersAny(header));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_Getprofile);
//                .queryParam("Cookie", "ssid");
        getLogger().info("\n REQUEST: " + builder.toUriString() + "\n");
        ResponseEntity<JsonNode> response = restTemplate.exchange(builder.build().toString(),
                HttpMethod.GET, entity, JsonNode.class);
        response.getHeaders();
//        getLogger().info("\n HEADERS: " + getProfile(header) + "\n");
        if (response.getStatusCode().is2xxSuccessful()) {
            getLogger().info("Successful search");
        }
        return response.getBody();

    }
}
