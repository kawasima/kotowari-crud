package scaffold.crud.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kawasima
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserForm extends FormBase {
    private String name;
}
