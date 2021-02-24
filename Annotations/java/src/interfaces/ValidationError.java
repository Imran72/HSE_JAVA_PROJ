/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package interfaces;

public interface ValidationError {
    String getMessage();

    String getPath();

    Object getFailedValue();
}
