package Client.TCP;

import Common.Communication.Message;
import Common.Exceptions.ConnectionException;

import java.io.IOException;
import java.net.Socket;

public class TCPClient {
    public TCPClient() {
    }

    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(Message.HOST, Message.PORT);
             var is = socket.getInputStream();
             var os = socket.getOutputStream()
        ) {
            request.writeTo(os);

            //System.out.println("sendAndReceive - received response: ");
            Message response = new Message();
            response.readFrom(is);
            //System.out.println(response);

            return response;
        } catch (IOException e) {
            throw new ConnectionException("error connection to server " + e.getMessage(), e);
        }
    }
}
