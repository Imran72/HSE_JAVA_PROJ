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
 * Класс, в котором осуществляется проверка поля с аннотацией @NotNull.
 */
public class NotNullValidator implements Validator {
    /**
     * Путь до значения непрошедшего проверку
     */
    private final String path;

    public NotNullValidator(String path) {
        this.path = path;
    }

    /**
     * Проверка, аннотированого поля @NotNull
     *
     * @param object поле класса для проверки
     * @return сет ошибок, выявленных при проверке поля класса
     * @throws ValidateException выбрасывается, если аннотация
     *                           применена к несоответствующему типу
     */
    @Override
    public Set<ValidationError> validate(Object object) {
        Set<ValidationError> ValidationErrorSet = new HashSet<>();
        if (object == null) {
            StringBuilder message = new StringBuilder("must not be null");
            MainValidatorError error = new MainValidatorError(message.toString(), path, object);
            ValidationErrorSet.add(error);
        }

        return ValidationErrorSet;
    }
}
