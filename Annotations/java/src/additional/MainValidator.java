/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package additional;

import annotations.*;
import interfaces.ValidationError;
import interfaces.Validator;
import validators.*;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Класс, в котором осуществляется проверка все полей класса BookingForm
 * в соответсвии с их аннотациями
 */
public class MainValidator implements Validator {

    String path = "";

    /**
     * Проверка всех полей формы на соответствия условиям аннотаций
     *
     * @param object объект класса BookinForm
     * @return сет ошибок, выявленных при проверке объекта класса BookinForm
     * @throws ValidateException выбрасывается, если объект не является instance BookingForm
     */
    @Override
    public Set<ValidationError> validate(Object object) throws ValidateException {
        Set<ValidationError> ValidationErrorSet = new HashSet<>();
        Class<?> classObj = requireNonNull(object).getClass();
        if (classObj.isAnnotationPresent(Constrained.class)) {
            Field[] fields = classObj.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (field.get(object) != null) {
                        if (field.get(object) instanceof List) {
                            ValidationErrorSet.addAll(validateList(object, field));
                        } else {
                            ValidationErrorSet.addAll(validateElement(object, field));
                        }
                    }
                } catch (Exception exception) {
                    return ValidationErrorSet;
                }
            }

        }

        return ValidationErrorSet;
    }

    /**
     * Проверка всех элементов Листа: рассматривается случай, когда лист содержит @Constrained классы,
     * когда лист содержит примитивные классы
     *
     * @param object экземпляр класса
     * @param field  ссылка на поле класса
     * @return возвращает множество ошибок в формате ValidationError
     * @throws ValidateException      - возможное исключение1
     * @throws IllegalAccessException - возможное исключение2
     */
    public Set<ValidationError> validateList(Object object, Field field) throws ValidateException, IllegalAccessException {
        Set<ValidationError> ValidationErrorSet = new HashSet<>();
        // индекс элемента списка - важен для фиксации пути
        int index = 0;
        List<?> list;
        try {
            list = (List<?>) field.get(object);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return ValidationErrorSet;
        }

        ValidationErrorSet.addAll(validateElement(object, field));
        /** проход по всем элементам листа в целях проверки на соответствие */
        for (var el : list) {
            String tmpPath = path;
            path += field.getName();
            ValidationErrorSet.addAll(validateInList(object, field, index));
            path += "[" + index + "].";
            ValidationErrorSet.addAll(validate(el));
            path = tmpPath;
            index++;

        }

        return ValidationErrorSet;

    }


    /**
     * Валидация элемента как самостоятельного объекта
     *
     * @param object экземпляр класса
     * @param field  ссылка на поле класса
     * @return возвращает множество ошибок в формате ValidationError
     * @throws ValidateException      - возможное исключение1
     * @throws IllegalAccessException - возможное исключение2
     */
    public Set<ValidationError> validateElement(Object object, Field field) throws ValidateException, IllegalAccessException {
        Set<ValidationError> ValidationErrorSet = new HashSet<>();

        /** Изъятие всех аннотаций подлежацих проверке */
        AnnotatedType annotatedType = field.getAnnotatedType();
        /** Проверка на соответствие */
        ValidationErrorSet.addAll(validateAnnotation(field.get(object), annotatedType, field.getName()));

        return ValidationErrorSet;
    }

    /**
     * Валидация элемента как самостоятельного объекта внутри листа
     *
     * @param object экземпляр класса
     * @param field  ссылка на поле класса
     * @return возвращает множество ошибок в формате ValidationError
     * @throws ValidateException      - возможное исключение1
     * @throws IllegalAccessException - возможное исключение2
     */
    public Set<ValidationError> validateInList(Object object, Field field, int index) throws IllegalAccessException, ValidateException {
        Set<ValidationError> ValidationErrorSet = new HashSet<>();
        /** Изъятие всех аннотаций подлежацих проверке */
        AnnotatedType annotatedType = ((AnnotatedParameterizedType) field.getAnnotatedType()).getAnnotatedActualTypeArguments()[0];
        /** Проверка на соответствие */
        ValidationErrorSet.addAll(validateAnnotation(((List<?>) field.get(object)).get(index), annotatedType, "[" + index + "]"));

        return ValidationErrorSet;
    }

    /**
     * Валидация поступившего поля - проход по всем возможным аннотациям
     * В случае, когда annotation заключает в себе аннотацию, программа заходит в условие и проверяет на соответсвие значение
     * внутри поля
     *
     * @param obj        объект валидации
     * @param annotation мета-информация по аннотациям поля
     * @param name       путь
     * @return
     * @throws ValidateException      - возможное исключение1
     * @throws IllegalAccessException - возможное исключение2
     */
    public Set<ValidationError> validateAnnotation(Object obj, AnnotatedType annotation, String name) throws ValidateException, IllegalAccessException {
        Set<ValidationError> ValidationErrorSet = new HashSet<>();
        //  путь
        String _path = path + name;
        if (annotation.isAnnotationPresent(NotNull.class)) {
            NotNullValidator notNullValidator = new NotNullValidator(_path);
            ValidationErrorSet.addAll(notNullValidator.validate(obj));

            // если есть ошибки null -> выходим
            if (!ValidationErrorSet.isEmpty())
                return ValidationErrorSet;
        }

        if (annotation.isAnnotationPresent(Size.class)) {
            SizeValidator sizeValidator = new SizeValidator(_path, annotation.getAnnotation(Size.class).min(),
                    annotation.getAnnotation(Size.class).max());
            ValidationErrorSet.addAll(sizeValidator.validate(obj));
        }

        if (annotation.isAnnotationPresent(Positive.class)) {
            PositiveValidator positiveValidator = new PositiveValidator(_path);
            ValidationErrorSet.addAll(positiveValidator.validate(obj));
        }

        if (annotation.isAnnotationPresent(Negative.class)) {
            NegativeValidator negativeValidator = new NegativeValidator(_path);
            ValidationErrorSet.addAll(negativeValidator.validate(obj));
        }

        if (annotation.isAnnotationPresent(InRange.class)) {
            InRangeValidator inRangeValidator = new InRangeValidator(_path, annotation.getAnnotation(InRange.class).min(),
                    annotation.getAnnotation(InRange.class).max());
            ValidationErrorSet.addAll(inRangeValidator.validate(obj));
        }

        if (annotation.isAnnotationPresent(AnyOf.class)) {
            AnyOfValidator anyOfValidator = new AnyOfValidator(_path, annotation.getAnnotation(AnyOf.class).value());
            ValidationErrorSet.addAll(anyOfValidator.validate(obj));
        }

        if (annotation.isAnnotationPresent(NotBlank.class)) {
            NotBlankValidator notBlankValidator = new NotBlankValidator(_path);
            ValidationErrorSet.addAll(notBlankValidator.validate(obj));
        }


        return ValidationErrorSet;
    }

}
