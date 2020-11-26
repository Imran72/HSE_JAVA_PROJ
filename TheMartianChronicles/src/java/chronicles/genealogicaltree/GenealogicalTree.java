/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package chronicles.genealogicaltree;

import chronicles.martian.Conservative;
import chronicles.martian.IMartian;
import chronicles.martian.Innovator;

import java.util.ArrayList;
import java.util.List;

public class GenealogicalTree {
    List<IMartian> martians; // коллекция марсиан для вывода
    String tree; // строковое представление марсиан


    /**
     * Геттер коллекции местных марсинан
     */
    public List<IMartian> getMartians() {
        return martians;
    }


    /**
     * Геттер строкового представление марсиан
     */
    public String getTree() {
        return tree;
    }


    /**
     * Конструктор, принимающий лист марсиан и преобразующий его в строковый вид
     */
    public GenealogicalTree(List<IMartian> list) {
        martians = list;
        tree = getTextTree(list, 0);
    }

    /**
     * Конструктор, принимающий строкое представление марсиан и преобразующий его в коллекцию марсиан
     */
    public GenealogicalTree(String tree) {
        this.tree = tree;
        martians = getMartians(null, tree.split("\n"), 0, 0);
    }

    /**
     * Конструктор, преобразования марсиан - новатор ->> консерватор
     */
    List<IMartian> getDiverseList(List<IMartian> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (((Innovator) list.get(i)).getTransitionForm())
                list.set(i, new Conservative((Innovator) list.get(i), null));
        }
        return list;
    }

    /**
     * Метод преобразования коллекции марсиан в строковый вид, работает рекурсивно
     */
    String getTextTree(List<IMartian> martians, int indent) {
        String tree = "";
        if (martians == null)
            return tree;
        for (IMartian martian : martians) {
            tree += " ".repeat(indent) + martian.toString() + '\n' + getTextTree(martian.getChildren(), indent + 4);
        }

        return tree;
    }

    /**
     * Метод преобразования строки-иерархии марсиан в коллекцию марсиан
     */
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
        list = getDiverseList(list);
        return list;
    }

    /**
     * Метод парсинга строки на основании которой инициализируется марсианини
     */
    Innovator getMartian(String a, IMartian parent) {
        a = a.trim();
        return new Innovator(parent, a.substring(a.indexOf(':') + 1, a.length() - 1), !a.startsWith("InnovatorMartian"));
    }
}
