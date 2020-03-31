package Common.Communication;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Message {
    public static final int PORT = 1234;
    public static final String HOST = "localhost";

    private String header;
    private Serializable body;

    public Message() {
    }

    public Message(String header, Serializable body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void writeTo(OutputStream os) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(os);
        //
        // out.writeObject((header + System.lineSeparator() + body + System.lineSeparator()).getBytes());
        ArrayList<Serializable> array = new ArrayList<>();
        array.add(header);
        array.add(body);
        out.writeObject(array);
    }

    public void readFrom(InputStream is) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(is);
        ArrayList<Serializable> params = (ArrayList<Serializable>) in.readObject();
        header = (String) params.get(0);
        body = (Serializable) params.get(1);


    }

    @Override
    public String toString() {
        return "Common.Communication.Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
