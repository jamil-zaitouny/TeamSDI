package Model;

import java.util.Optional;

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
        Optional<Object> objectOptional = Optional.ofNullable(obj).filter(v -> v instanceof Client);
        return objectOptional
                .filter(v->((Client) v).getId().equals(this.getId()))
                .isPresent();
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
