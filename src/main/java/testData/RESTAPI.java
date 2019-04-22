package testData;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.http.entity.ContentType.*;
import static org.testng.Assert.*;

public class RESTAPI implements RestClient{
    private static final String URL = "https://auth.iqoption.com/api/v1.0/login";

    private static final String URL1 = "https://iqoption.com?api=register";

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
            ResponseEntity<EmailDTO> response = restTemplate.postForEntity(URL, request, EmailDTO.class);

            getLogger().info(response.getStatusCode().toString());
            boolean status200 = response.getStatusCode().is2xxSuccessful();
            assertTrue(is200Status && status200, "Status is not '200 OK'");
        } catch (HttpClientErrorException e) {
            getLogger().info(e.getMessage());
//            boolean status400 = response.getStatusCode().is4xxClientError();
            assertFalse(is200Status, "Status is not '401'");
        }

//        given().contentType(MediaType.APPLICATION_XML).body(APPLICATION_XML);
   }

    public void checkAllHeaders()  {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://auth.iqoption.com/api/v1.0/login");
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



}
