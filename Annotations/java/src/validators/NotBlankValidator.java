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
 * Класс, в котором осуществляется проверка поля с аннотацией @NotBlank.
 */
public class NotBlankValidator implements Validator {

    /**
     * Путь до значения непрошедшего проверку
     */
    private final String path;

    public NotBlankValidator(String path) {
        this.path = path;
    }


    /**
     * Проверка, аннотированого поля @NotBlank
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
            if (object instanceof String) {
                String value = (String) object;
                if (value.isBlank()) {
                    StringBuilder message = new StringBuilder("must not be blank");
                    MainValidatorError error = new MainValidatorError(message.toString(), path, object);
                    ValidationErrorSet.add(error);
                }

            } else {
                throw new ValidateException("An annotated object is not a string.");
            }
        }

        return ValidationErrorSet;
    }
}
