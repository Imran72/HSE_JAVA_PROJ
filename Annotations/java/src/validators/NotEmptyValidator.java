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
 * Класс, в котором осуществляется проверка поля с аннотацией @NotEmpty.
 */
public class NotEmptyValidator implements Validator {

    /**
     * Путь до значения непрошедшего проверку
     */
    private final String path;

    public NotEmptyValidator(String path) {
        this.path = path;
    }

    /**
     * Проверка, аннотированого поля @NotEmpty
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
            StringBuilder message = new StringBuilder("must not be empty");
            MainValidatorError error = new MainValidatorError(message.toString(), path, object);
            if (object instanceof String) {
                String value = (String) object;
                if (value.isEmpty()) {
                    ValidationErrorSet.add(error);
                }

            } else if (object instanceof List) {
                List value = (List) object;
                if (value.isEmpty()) {
                    ValidationErrorSet.add(error);
                }
            } else if (object instanceof Set) {
                Set value = (Set) object;
                if (value.isEmpty()) {

                    ValidationErrorSet.add(error);
                }
            }else if (object instanceof Map) {
                Map value = (Map) object;
                if (value.isEmpty()) {
                    ValidationErrorSet.add(error);
                }
            }else {
                throw new ValidateException("An annotated object is not string, list, map or set.");
            }
        }

        return ValidationErrorSet;
    }
}
