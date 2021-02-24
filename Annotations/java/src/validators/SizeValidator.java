/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package validators;

import additional.MainValidatorError;
import additional.ValidateException;
import interfaces.ValidationError;
import interfaces.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Класс, в котором осуществляется проверка поля с аннотацией @Size.
 */
public class SizeValidator implements Validator {

    /**
     * Путь до значения непрошедшего проверку
     */
    private final String path;
    /**
     * Минимальное значение размера, заданного аннотацией @Size
     */
    private final int min;
    /**
     * Максимальное значение размера, заданного аннотацией @Size
     */
    private final int max;

    public SizeValidator(String path, int min, int max) {
        this.path = path;
        this.min = min;
        this.max = max;

    }

    /**
     * Проверка, аннотированого поля @Size
     *
     * @param object поле класса для проверки
     * @return сет ошибок, выявленных при проверке поля класса
     * @throws ValidateException выбрасывается, если аннотация
     *                           применена к несоответствующему типу
     */
    @Override
    public Set<ValidationError> validate(Object object) throws ValidateException {
        Set<ValidationError> ValidationErrorSet = new HashSet<>();

        if (object != null) {
            StringBuilder message = new StringBuilder(String.format("size must be in range %d and %d", min, max));
            MainValidatorError error = new MainValidatorError(message.toString(), path, object);
            if (object instanceof String) {
                String value = (String) object;
                if (value.length() < min || value.length() > max) {
                    ValidationErrorSet.add(error);
                }

            } else if (object instanceof List) {
                List value = (List) object;
                if (value.size() < min || value.size() > max) {
                    ValidationErrorSet.add(error);
                }
            } else if (object instanceof Set) {
                Set value = (Set) object;
                if (value.size() < min || value.size() > max) {

                    ValidationErrorSet.add(error);
                }
            } else if (object instanceof Map) {
                Map value = (Map) object;
                if (value.size() < min || value.size() > max) {
                    ValidationErrorSet.add(error);
                }
            } else {
                throw new ValidateException("An annotated object is not string, list, map or set.");
            }
        }

        return ValidationErrorSet;
    }
}
