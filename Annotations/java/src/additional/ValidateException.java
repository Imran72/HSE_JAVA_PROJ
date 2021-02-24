/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package additional;

/**
 * Кастомное исключение, что выбрасывается при неверном применении аннотаций к типу
 */
public class ValidateException extends Exception {
    public ValidateException(String errorMessage) {
        super(errorMessage);
    }
}

