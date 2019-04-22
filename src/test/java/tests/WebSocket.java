package tests;

import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.springframework.http.HttpEntity;
import org.testng.annotations.Test;
import testData.EmailDTO;
import testData.RestClient;

import javax.websocket.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.server.ServerEndpoint;

import static org.testng.AssertJUnit.assertEquals;

@ServerEndpoint(value = "https://iqoption.com?api=getprofile")
public class WebSocket implements RestClient {

    private static Set<WebSocket> webSockets
            = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private Object wsPacket;

    @OnClose
    public void onClose(Session session) throws IOException {
    }

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText("You open session");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testWebSocket(Object wsPacket){
        EmailDTO emailDTO = new EmailDTO();
        HttpEntity<EmailDTO> request = new HttpEntity<>(emailDTO, setAuthHeadersAny("988ee8a2479083592cc9f189cde801c5"));
        Session session = (Session)wsPacket;
        onOpen(session);
        try {
            onClose(session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}