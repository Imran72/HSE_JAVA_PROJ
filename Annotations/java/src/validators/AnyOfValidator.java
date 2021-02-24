package validators;

import additional.MainValidatorError;
import additional.ValidateException;
import interfaces.ValidationError;
import interfaces.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Класс, в котором осуществляется проверка поля с аннотацией @AnyOf.
 */
public class AnyOfValidator implements Validator {

    /**
     * Путь до значения непрошедшего проверку
     */
    private final String path;
    private final String[] values;

    public AnyOfValidator(String path, String[] values) {
        this.path = path;
        this.values = values;
    }


    /**
     * Проверка, аннотированого поля @AnyOf
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
                if (!Arrays.asList(values).contains(value)) {
                    StringBuilder message = new StringBuilder("must be one of ");
                    for (String val : values) {
                        message.append("'").append(val).append("' ");
                    }
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
