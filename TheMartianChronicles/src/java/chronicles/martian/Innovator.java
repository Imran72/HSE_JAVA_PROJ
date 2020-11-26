/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package chronicles.martian;

import java.util.ArrayList;
import java.util.List;

public class Innovator<T> implements IMartian<T> {

    boolean transitionForm; // форма переходная/текущая
    IMartian<T> parent; // родитель
    T value; // genetic code
    List<IMartian<T>> children = new ArrayList<>();  // дети
    List<IMartian<T>> descadants = new ArrayList<>();  //  потомки

    // конструктор
    public Innovator(IMartian<T> parent, T value, boolean form) {
        this.parent = parent;
        this.value = value;
        transitionForm = form;
    }

    /**
     * Сеттер генома
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Сеттер родителя
     * установить родителя возможно только в том случае, если это не противоречит здравому смылсу
     */
    public void setParent(Innovator<T> parent) {
        if (this.equals(parent))
            throw new RuntimeException("Невозможно быть родителем самому себе!");
        if (descadants.contains(parent))
            throw new RuntimeException("Потомок не может быть родителем. Это противоестественно!");
        this.parent = parent;
        System.out.println(parent + " теперь вы родитель " + this);
        System.out.println(this + " теперь вы ребенок " + parent);
    }


    /**
     * Сеттер потомков с проверкой на противоречивость
     */
    public void setDescadants(List<IMartian<T>> descadants) {

        if (checkParent(descadants, this))
            throw new RuntimeException("Вы пытаетесь сделать детьми текущего марсианина множество, вклющающее родителя текущего марсианина!");
        this.children = descadants;
        for (IMartian child: children)
            ((Innovator) child).setParent(this);
        this.descadants = ExpressDescadants();
    }

    /**
     * Проверка: нет ли во всем перечне представленных марсиан родителя проверямого марсианина
     */
    boolean checkParent(List<IMartian<T>> descadants, IMartian<T> martian) {
        for (IMartian<T> child : children) {
            if (martian.getParent() == martian)
                return true;
            if (child.hasChildren())
                return checkParent(child.getChildren(), martian);
        }
        return false;
    }

    /**
     * Метод добавление ребенка марсианину с учетом естественности - защита от циклов
     */
    public boolean AddChild(Innovator<T> child) {
        if (child.descadants.contains(this))
            return false;
        if (children.contains(child))
            return true;
        children.add(child);
        child.setParent(this);
        descadants = ExpressDescadants();
        return true;
    }

    /**
     * Удаление ребенка
     */
    public boolean DeleteChild(Innovator<T> child) {
        if (!children.contains(child))
            return false;
        children.remove(child);
        return true;
    }

    /**
     * переопределенный метод ToString()
     */
    @Override
    public String toString() {
        return String.format("InnovatorMartian (%s:%s)",
                ((Object) getValue()).getClass().getSimpleName(), getValue().toString());
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
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
        return descadants.isEmpty() ? null : ExpressDescadants();
    }

    public boolean getTransitionForm() {
        return transitionForm;
    }
}
