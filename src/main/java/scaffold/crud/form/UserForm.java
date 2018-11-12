package scaffold.crud.form;

import java.util.Objects;

/**
 * @author kawasima
 */
public class UserForm extends FormBase {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForm userForm = (UserForm) o;
        return Objects.equals(name, userForm.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "name='" + name + '\'' +
                '}';
    }
}
