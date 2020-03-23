package Repository.DBRepository;

import Model.Exceptions.ValidatorException;
import Model.Purchase;
import Repository.SortRepository.Sort;
import Repository.SortRepository.SortingRepository;

import java.io.IOException;
import java.util.Optional;

public class PurchaseDBRepository implements SortingRepository<Integer, Purchase>
{
    @Override
    public Iterable<Purchase> findAll(Sort sort) {
        return null;
    }

    @Override
    public Optional<Purchase> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Iterable<Purchase> findAll() {
        return null;
    }

    @Override
    public Optional<Purchase> add(Purchase entity) throws ValidatorException, IOException {
        return Optional.empty();
    }

    @Override
    public Optional<Purchase> delete(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Purchase> update(Purchase entity) throws ValidatorException {
        return Optional.empty();
    }
}
