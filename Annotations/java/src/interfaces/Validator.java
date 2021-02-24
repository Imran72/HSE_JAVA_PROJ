/**
 * @author <a href="mailto:iatimkanov@edu.hse.ru"> Imran Timkanov</a>
 */

package interfaces;

import java.util.Set;

public interface Validator {
    Set<ValidationError> validate(Object object) throws Exception;
}
