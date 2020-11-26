/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package chronicles.martian;

import java.util.List;

public class Conservative<T> implements IMartian<T> {

    final IMartian<T> parent; // родитель
    final T value; // genetic code
    final List<IMartian<T>> children; // коллекция детей
    final List<IMartian<T>> descadants; // коллекция потомков

    // конструктор - реализация консерватора через объект класса Innovator
    public Conservative(Innovator<T> novator, IMartian parent) {
        this.parent = parent;
        this.value = novator.value;
        this.children = novator.children;
        if (getChildren() != null)
            MakeChildrenConservator(children);

        this.descadants = novator.ExpressDescadants();
    }

    /**
     * Рекурсивное преобразование детей новаторов в детей консерваторов
     */
    void MakeChildrenConservator(List<IMartian<T>> martians) {
        for (int i = 0; i < martians.size(); i++)
            martians.set(i, new Conservative((Innovator<T>) martians.get(i), this));
    }


    /**
     * Сеттер потомков с проверкой на противоречивость
     */
    @Override
    public String toString() {
        return String.format("ConservativeMartian (%s:%s)",
                ((Object) getValue()).getClass().getSimpleName(), getValue().toString());
    }

    /**
     * переопределенный метод ToString()
     */
    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }


    @Override
    public T getValue() {
        return value;
    }

    @Override
    public IMartian<T> getParent() {
        return parent;
    }

    @Override
    public List<IMartian<T>> getChildren() {
        return children.isEmpty() ? null : children;
    }

    @Override
    public List<IMartian<T>> getDescadants() {
        return descadants.isEmpty() ? null : descadants;
    }
}
