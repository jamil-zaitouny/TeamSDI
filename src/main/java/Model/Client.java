package Model;

public class Client extends BaseEntity<Integer>{
    private String name;
    public static String className = "Clients";

    public Client(int id, String name){
        super(id);
        this.name = name;
    }

    public Client(Client copyClient){
        super(copyClient.getId());
        this.name = copyClient.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Client){
            return ((Client) obj).getId() == this.getId();
        }
        return false;
    }

    @Override
    public String toString() {
        return "ClientID: " + this.getId() + "\n"
                +"ClientName" + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
