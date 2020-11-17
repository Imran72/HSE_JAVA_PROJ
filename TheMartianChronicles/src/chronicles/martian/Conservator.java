package chronicles.martian;

import java.util.List;

public class Conservator<T> implements IMartian<T> {

    final IMartian<T> parent;
    final T value; // genetic code
    final List<IMartian<T>> children;
    final List<IMartian<T>> descadants;

    public Conservator(Conservator<T> parent, T value, List<IMartian<T>> children) {
        this.parent = parent;
        this.value = value;
        this.children = children;
        descadants = ExpressDescadants();
    }

    public Conservator(Novator<T> novator) {
        this.parent = novator.parent;
        this.value = novator.value;
        this.children = novator.children;
        this.descadants = novator.ExpressDescadants();
    }

    @Override
    public String toString() {
        return String.format("ConservativeMartian (%s:%s)",
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
