/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package forms;

import annotations.*;

import java.util.List;


/**
 * Класс, предназначенный для тестирования валидаторов аннотаций
 */
@Constrained
public class TestClass {
    @Size(min = 1, max = 2)
    @NotEmpty
    public List<String> list;
    @NotNull
    @AnyOf({"TV", "Kitchen"})
    public String name;

    @InRange(min = -10, max = 10)
    public int x;

    @Positive
    public int a;

    @Negative
    public int b;

    public TestClass(List<String> list, String name, int x, int a, int b) {
        this.a = a;
        this.b = b;
        this.list = list;
        this.name = name;
        this.x = x;
    }


}
