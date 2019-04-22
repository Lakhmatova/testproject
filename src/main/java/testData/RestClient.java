package testData;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public interface RestClient{
    default HttpHeaders setAuthHeadersAny(String name ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", name);
        return headers;
    }
}
