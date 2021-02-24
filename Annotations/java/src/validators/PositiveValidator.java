/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package validators;

import additional.MainValidatorError;
import additional.ValidateException;
import interfaces.ValidationError;
import interfaces.Validator;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс, в котором осуществляется проверка поля с аннотацией @Positive.
 */
public class PositiveValidator implements Validator {

    /**
     * Путь до значения непрошедшего проверку
     */
    private final String path;

    public PositiveValidator(String path) {
        this.path = path;
    }

    /**
     * Проверка, аннотированого поля @Positive
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
            if (object instanceof Number) {
                Number value = (Number) object;
                if (value.longValue() <= 0) {
                    StringBuilder message = new StringBuilder("must be positive");
                    MainValidatorError error = new MainValidatorError(message.toString(), path, object);
                    ValidationErrorSet.add(error);
                }

            } else {
                throw new ValidateException("An annotated object is not a number.");
            }
        }

        return ValidationErrorSet;
    }
}
