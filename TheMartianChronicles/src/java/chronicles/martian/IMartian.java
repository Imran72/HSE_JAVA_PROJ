/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package chronicles.martian;

import java.util.ArrayList;
import java.util.List;


/**
 * Интерфейс, который наследуют марсиане-новаторы и марсиане-консерваторы
 */
public interface IMartian<T> {

    /**
     * Возвращаем геном марсианина - обобщенно
     */
    T getValue();

    /**
     * булеан имения детей или же нет
     */
    boolean hasChildren();

    /**
     * Геттер родителя
     */
    IMartian<T> getParent();

    /**
     * Гетер детей марсианина
     */
    List<IMartian<T>> getChildren();

    /**
     * Геттер потомков
     */
    List<IMartian<T>> getDescadants();

    /**
     * дефолтный метод, возвращающий булеан - есть ли ребенок с таким геномом или же нет
     */

    default boolean hasChildWithValue(T value) {
        List<IMartian<T>> children = this.getChildren();
        if (children == null)
            return false;
        for (IMartian<T> child : children) {
            if (child.getValue().equals(value))
                return true;
        }
        return false;
    }


    /**
     * дефолтный метод, возвращающий булеан - есть ли потомок с таким геномом или же нет
     */
    default boolean hasDescadantWithValue(T value) {
        List<IMartian<T>> children = this.getChildren();
        for (IMartian<T> child : children) {
            if ((child.getChildren() != null && child.hasDescadantWithValue(value)))
                return true;
            else if ((child.getChildren() == null && child.getValue().equals(value)))
                return true;
        }
        return false;
    }

    /**
     * дефолтный метод интерактивного подсчета всех потомков
     */
    default List<IMartian<T>> ExpressDescadants() {
        List<IMartian<T>> children = this.getChildren();
        List<IMartian<T>> localDescadants = new ArrayList<>();
        if (children == null)
            return localDescadants;
        for (IMartian<T> child : children) {
            if (child.getChildren() != null)
                localDescadants.addAll((child.ExpressDescadants()));
            localDescadants.add(child);
        }
        return localDescadants;
    }


}
