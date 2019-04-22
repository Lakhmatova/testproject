package tests;

import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.testng.annotations.Test;

import javax.net.ssl.SSLEngine;
import javax.websocket.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.server.ServerEndpoint;

import static javafx.beans.binding.Bindings.when;
import static org.testng.AssertJUnit.assertEquals;

@ServerEndpoint(value = "https://iqoption.com?api=getprofile")
public class WebSocket {

        private static Set<tests.WebSocket> webSockets
                = new CopyOnWriteArraySet<>();
        private static HashMap<String, String> users = new HashMap<>();

        @OnMessage
        public void onMessage(String message) {
            System.out.println(String.format("%s %s", "Received message: ", message));
        }

        @OnClose
        public void onClose(Session session) throws IOException {
        }

        @OnError
        public void onError(Session session, Throwable throwable) {
            // Do error handling here
        }

        @OnOpen
        public void onOpen(Session session) {
            try {
                session.getBasicRemote().sendText("Hello!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   // Session session = wsContainer.connectToServer(client, "https://iqoption.com?api=getprofile");

   
    @OnWebSocketConnect
    @Test
    public void testWebSocket() throws IOException {
//        final EchoEndpoint endpoint = new EchoEndpoint();
//        final Session session = request.getSession(true);
//        onOpen(session);
//        assertEquals(onMessage("hello"),session);
//         onClose(session);
    }

}