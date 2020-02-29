package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Repository<TElem> implements IRepository<TElem> {
    private ArrayList<TElem> elements;

    /**
     * The copy-constructor for repository
     * @param copyRepository - The Repository that we're copying
     *
     */

    public Repository(Repository<TElem> copyRepository){
        this.elements = (ArrayList<TElem>) copyRepository.getList();
    }

    /**
     *  Constructor initializing the repository's list
     */
    public Repository(){
        elements = new ArrayList<>();
    }

    @Override
    public void add(TElem elem) {
        elements.add(elem);
    }

    @Override
    public void set(int index, TElem elem) {
        elements.set(index, elem);
    }

    @Override
    public boolean delete(TElem elem) {
        return elements.remove(elem);
    }

    @Override
    public List<TElem> getList() {
        return elements;
    }
    //TODO Would having a toString here be ok? or should we just add that to the controller
    @Override
    public String toString() {
        return elements
                .stream()
                .map(Object::toString)
                .reduce("", (accumulator, current)->accumulator + current + "\n");
    }
}
