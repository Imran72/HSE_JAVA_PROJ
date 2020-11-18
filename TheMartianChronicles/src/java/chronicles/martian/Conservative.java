package chronicles.martian;

import java.util.List;

public class Conservative<T> implements IMartian<T> {

    final IMartian<T> parent;
    final T value; // genetic code
    final List<IMartian<T>> children;
    final List<IMartian<T>> descadants;

    public Conservative(IMartian<T> parent, T value, List<IMartian<T>> children) {
        this.parent = parent;
        this.value = value;
        this.children = children;
        descadants = ExpressDescadants();
    }

    public Conservative(Innovator<T> novator, IMartian parent) {
        this.parent = parent;
        this.value = novator.value;
        this.children = novator.children;
        MakeChildrenConservator(children);
        this.descadants = novator.ExpressDescadants();
    }

    void MakeChildrenConservator(List<IMartian<T>> martians) {
        for(int i = 0; i < martians.size();i++)
            martians.set(i, new Conservative((Innovator<T>) martians.get(i), this));
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
        return children.isEmpty() ? null : children;
    }

    @Override
    public List<IMartian<T>> getDescadants() {
        return descadants.isEmpty() ? null : descadants;
    }
}
