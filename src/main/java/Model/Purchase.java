package Model;

import java.util.Optional;

public class Purchase extends BaseEntity<Integer>
{
    private String bookId;
    private int clientId;

    public Purchase(Integer id, String bookId, int clientId) {
        super(id);
        this.bookId = bookId;
        this.clientId = clientId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Book:" +bookId+ "\n"+
                "Client=" + clientId;
    }

    @Override
    public boolean equals(Object obj) {
        Optional<Object> objectOptional = Optional.ofNullable(obj).filter(v -> v instanceof Purchase);
        return objectOptional
                .filter(v->((Purchase) v).getId().equals(this.getId()))
                .isPresent();
    }
}
