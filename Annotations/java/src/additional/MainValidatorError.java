/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package additional;

import interfaces.*;

/**
 * Класс ошибки  валидации
 * Каждая ошибка - это сообщение пользователю, условный путь, значение несоответствующее условиям аннотации
 */
public class MainValidatorError implements ValidationError{
    // сообщение пользователю
    private final String message;
    // условный путь
    private final String path;
    // значение несоответствующее условиям аннотации
    private final Object failedValue;

    public MainValidatorError(String message, String path, Object failedValue) {
        this.message = message;
        this.path = path;
        this.failedValue = failedValue;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getFailedValue() {
        return failedValue;
    }
}
