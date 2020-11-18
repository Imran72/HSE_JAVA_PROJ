package chronicles.genealogicaltree;

import chronicles.martian.Conservative;
import chronicles.martian.IMartian;
import chronicles.martian.Innovator;

import java.util.ArrayList;
import java.util.List;

public class GenealogicalTree {
    List<IMartian> martians;
    String tree;

    public List<IMartian> getMartians() {
        return martians;
    }

    public String getTree() {
        return tree;
    }

    public GenealogicalTree(List<IMartian> list) {
        martians = list;
        tree = getTextTree(list, 0);
    }

    public GenealogicalTree(String tree) {
        this.tree = tree;
        martians = getMartians(null, tree.split("\n"), 0, 0);
    }

    List<IMartian> getDiverseList(List<IMartian> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (((Innovator) list.get(i)).getTransitionForm())
                list.set(i, new Conservative((Innovator) list.get(i), null));
        }
        return list;
    }

    String getTextTree(List<IMartian> martians, int indent) {
        String tree = "";
        for (IMartian martian : martians) {
            tree += " ".repeat(indent) + martian.toString() + '\n' + getTextTree(martian.getChildren(), indent + 4);
        }

        return tree;
    }

    List<IMartian> getMartians(IMartian parent, String[] arr, int indent, int index) {
        List<IMartian> list = new ArrayList<IMartian>();
        for (int i = index; i < arr.length; i++) {
            if (indent > (arr[i].length() - arr[i].trim().length()))
                break;
            if (arr[i].charAt(indent) != ' ')
                list.add(getMartian(arr[i], parent));
            else {
                List<IMartian> tmpList = getMartians(list.get(list.size() - 1), arr, indent + 4, i);
                for (IMartian child : tmpList) {
                    ((Innovator) list.get(list.size() - 1)).AddChild((Innovator) child);
                    i += child.ExpressDescadants().size() + 1;
                }
                i--;
            }

        }

        return list;
    }

    Innovator getMartian(String a, IMartian parent) {
        a = a.trim();
        return new Innovator(parent, a.substring(a.indexOf(':') + 1, a.length() - 1), a.startsWith("InnovatorMartian") ? false : true);
    }
}
