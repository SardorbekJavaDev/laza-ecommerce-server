package uz.laza.ecommerce.exception;

/**
 * @author Sampson Alfred
 */

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
