package chronicles.martian;

import java.util.ArrayList;
import java.util.List;

public interface IMartian<T> {

    T getValue();

    IMartian<T> getParent();

    List<IMartian<T>> getChildren();

    List<IMartian<T>> getDescadants();

    default boolean hasChildWithValue(T value) {
        List<IMartian<T>> children = this.getChildren();
        for (IMartian<T> child : children) {
            if (((Novator<T>) child).value.equals(value))
                return true;
        }
        return false;
    }

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

    default List<IMartian<T>> ExpressDescadants() {
        List<IMartian<T>> children = this.getChildren();
        List<IMartian<T>> localDescadants = new ArrayList<>();
        for (IMartian<T> child : children) {
            if (child.getChildren() != null)
                localDescadants.addAll((child.ExpressDescadants()));
            else
                localDescadants.add(child);
        }
        return localDescadants;
    }


}
