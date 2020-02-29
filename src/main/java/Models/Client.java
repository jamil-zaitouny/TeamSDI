package Models;

public class Client {
    public static final String className = "Clients";

    private String clientName;
    private int clientNumber;

    public Client(String clientName, int clientNumber){
        this.clientName = clientName;
        this.clientNumber = clientNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Client)
            return clientNumber == ((Client) obj).clientNumber;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Client Name: " + clientName + " Client Number: " + clientNumber ;
    }
}
