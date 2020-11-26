package chronicles.genealogicaltree;

import chronicles.martian.Conservative;
import chronicles.martian.IMartian;
import chronicles.martian.Innovator;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenealogicalTreeTest {

    @BeforeAll
    static void setUp() {
        System.out.println("GenealogicalTreeTest's testing is running...");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Conservative's testing have finished.");
    }

    @Test
    void getMartians() {
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

        GenealogicalTree genealogicalTree = new GenealogicalTree(list);

        GenealogicalTree genealogicalTree2 = new GenealogicalTree(genealogicalTree.getTree());

        genealogicalTree = new GenealogicalTree(genealogicalTree2.getMartians());

        String output = "InnovatorMartian (String:Rahim)\n" +
                "    InnovatorMartian (String:Alim)\n" +
                "        InnovatorMartian (String:Imran)\n" +
                "    InnovatorMartian (String:Alsu)\n" +
                "        InnovatorMartian (String:Arslan)\n";
        assertEquals(output, genealogicalTree.getTextTree(list, 0));

    }

    @Test
    void getTree() {
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

        GenealogicalTree genealogicalTree = new GenealogicalTree(list);

        String output = "InnovatorMartian (String:Rahim)\n" +
                "    InnovatorMartian (String:Alim)\n" +
                "        InnovatorMartian (String:Imran)\n" +
                "    InnovatorMartian (String:Alsu)\n" +
                "        InnovatorMartian (String:Arslan)\n";
        assertEquals(output, genealogicalTree.getTree());
    }

    @Test
    void getDiverseList() {
        Innovator<String> n1 = new Innovator<String>(null, "Rahim", false);
        Innovator<String> n2 = new Innovator<String>(n1, "Alim", false);
        Innovator<String> n3 = new Innovator<String>(n1, "Alsu", false);
        n1.AddChild(n2);
        n1.AddChild(n3);
        Innovator<String> n4 = new Innovator<String>(n2, "Imran", false);
        Innovator<String> n5 = new Innovator<String>(n3, "Arslan", false);

        n2.AddChild(n4);
        n3.AddChild(n5);

        Conservative conservative = new Conservative(n1, null);
        List<IMartian> list = new ArrayList<>();
        list.add(conservative);

        GenealogicalTree genealogicalTree = new GenealogicalTree(list);

        String output = "ConservativeMartian (String:Rahim)\n" +
                "    ConservativeMartian (String:Alim)\n" +
                "        ConservativeMartian (String:Imran)\n" +
                "    ConservativeMartian (String:Alsu)\n" +
                "        ConservativeMartian (String:Arslan)\n";

        assertEquals(output, genealogicalTree.getTree());
    }

    @Test
    void getTextTree() {
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

        GenealogicalTree genealogicalTree = new GenealogicalTree(list);

        String output = "InnovatorMartian (String:Rahim)\n" +
                "    InnovatorMartian (String:Alim)\n" +
                "        InnovatorMartian (String:Imran)\n" +
                "    InnovatorMartian (String:Alsu)\n" +
                "        InnovatorMartian (String:Arslan)\n";
        assertEquals(output, genealogicalTree.getTextTree(list, 0));
    }

    @Test
    void getMartian() {
        List<IMartian> list = new ArrayList<>();
        GenealogicalTree genealogicalTree = new GenealogicalTree(list);

        String text = "InnovatorMartian (String:Rahim)";
        Innovator martian = new Innovator(null, "Rahim", false);

        assertEquals(martian.toString(), genealogicalTree.getMartian(text, null).toString());
    }
}