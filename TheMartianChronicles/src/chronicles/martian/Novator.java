package chronicles.martian;

import java.util.ArrayList;
import java.util.List;

public class Novator<T> implements IMartian<T> {

    IMartian<T> parent;
    T value; // genetic code
    List<IMartian<T>> children = new ArrayList<>();
    List<IMartian<T>> descadants = new ArrayList<>();

    void setValue(T value) {
        this.value = value;
    }

    void setValue(Novator<T> parent) {
        if (this.equals(parent))
            throw new RuntimeException("Невозможно быть родителем самому себе!");
        if (descadants.contains(parent))
            throw new RuntimeException("Потомок не может быть родителем. Это противоестественно!");
        this.parent = parent;
    }

    void setDescadants(List<IMartian<T>> descadants) {
        this.descadants = descadants;
    }

    boolean AddChild(Novator<T> child) {
        if (child.descadants.contains(this))
            return false;
        if (children.contains(child))
            return true;
        children.add(child);
        return true;
    }

    boolean DeleteChild(Novator<T> child) {
        if (!children.contains(child))
            return false;
        children.remove(child);
        return true;
    }

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
    public IMartian<T> getParent() {
        return parent;
    }

    @Override
    public List<IMartian<T>> getChildren() {
        return children;
    }

    @Override
    public List<IMartian<T>> getDescadants() {
        return descadants;
    }
}
