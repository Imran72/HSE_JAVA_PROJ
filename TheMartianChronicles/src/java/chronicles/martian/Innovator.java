package chronicles.martian;

import java.util.ArrayList;
import java.util.List;

public class Innovator<T> implements IMartian<T> {

    boolean transitionForm;
    IMartian<T> parent;
    T value; // genetic code
    List<IMartian<T>> children = new ArrayList<>();
    List<IMartian<T>> descadants = new ArrayList<>();

    public Innovator(IMartian<T> parent, T value, boolean form) {
        this.parent = parent;
        this.value = value;
        transitionForm = form;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setParent(Innovator<T> parent) {
        if (this.equals(parent))
            throw new RuntimeException("Невозможно быть родителем самому себе!");
        if (descadants.contains(parent))
            throw new RuntimeException("Потомок не может быть родителем. Это противоестественно!");
        this.parent = parent;
    }

    public void setDescadants(List<IMartian<T>> descadants) {
        this.descadants = descadants;
    }

    public boolean AddChild(Innovator<T> child) {
        if (child.descadants.contains(this))
            return false;
        if (children.contains(child))
            return true;
        children.add(child);
        return true;
    }

    public boolean DeleteChild(Innovator<T> child) {
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
        return children.isEmpty() ? null : children;
    }

    @Override
    public List<IMartian<T>> getDescadants() {
        return descadants.isEmpty() ? null : descadants;
    }

    public boolean getTransitionForm()
    {
        return transitionForm;
    }
}
