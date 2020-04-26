package springjpa.Model.Validators;


import springjpa.Model.Exceptions.ValidatorException;

public interface IValidator<TElem>{
    /**
     * Validates an elements, an exception is thrown if the element is not valid
     * @param elem - element to be validated
     * @throws ValidatorException
     *               exception to be thrown if the element is not valid
     */
    void validate(TElem elem) throws ValidatorException;
}
