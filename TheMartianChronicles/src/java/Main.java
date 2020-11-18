import chronicles.martian.IMartian;
import chronicles.martian.Innovator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Innovator<String> n1 = new Innovator<String>(null, "Rahim", false);
        Innovator<String> n2 = new Innovator<String>(n1, "Alim", false);
        Innovator<String> n3 = new Innovator<String>(n1, "Alsu", false);
        n1.AddChild(n2);
        n1.AddChild(n3);
        Innovator<String> n4 = new Innovator<String>(n2, "Imran", false);
        Innovator<String> n5 = new Innovator<String>(n3, "Arslan", false);

        n2.AddChild(n4);
        n3.AddChild(n5);

        List<IMartian> list = new ArrayList<>();
        list.add(n1);

        System.out.println(n1.hasDescadantWithValue(n5.getValue()));

//        GenealogicalTree genealogicalTree = new GenealogicalTree(list);
//
//        System.out.println(genealogicalTree.getTree());
//
//        genealogicalTree = new GenealogicalTree(genealogicalTree.getTree());
//
//        System.out.println(genealogicalTree.getTree());
//
//        Conservator<String> c1 = new Conservator<>(n1, null);
//        list.add(c1);
//
//        genealogicalTree = new GenealogicalTree(list);
//        System.out.println(genealogicalTree.getTree());
    }
}
