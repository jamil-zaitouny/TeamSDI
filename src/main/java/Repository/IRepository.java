package Repository;

import Models.Exceptions.ValidatorException;

import java.util.List;

public interface IRepository<TElem> {
    //TODO create ElementAlreadyExistsException and ValidatorException
    /**
     * Adds an element to the repository
     * @param elem adds the element to the Repository
     * @throws IllegalArgumentException
     *              If element is null
     * @throws ValidatorException
     *              If the element is not valid
     **/

    void add(TElem elem);

    /**
     * Updates the element at the given index with the new element elem
     * @param index - index of the element to update
     * @param elem - the new element
     * @throws IndexOutOfBoundsException
     *              If the index is out of bounds
     */
    void set(int index, TElem elem);
    /**
     * Removes an element from the Repository
     * @param elem - The element to be removed from the Repository
     * @return - True if the element has been removed successfully, false otherwise
     * @throws IllegalArgumentException
     *              If the element provided is null
     */
    boolean delete(TElem elem);

    /**
     * Returns all the elements
     * @return A Collection implementing List that contains all the elements in the Repository
     */
    List<TElem> getList();
}
