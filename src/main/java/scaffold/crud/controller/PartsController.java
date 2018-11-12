package scaffold.crud.controller;

import enkan.data.HttpResponse;
import kotowari.component.TemplateEngine;
import kotowari.data.Validatable;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kawasima
 */
public class PartsController {
    @Inject
    private TemplateEngine templateEngine;

    public HttpResponse textfield() {
        Form obj = new Form();
        return templateEngine.render("parts/textfield", "$entity$", obj,
                "$Entity", "User",
                "$Field$", "Name",
                "$field$", "name");
    }

    public static class Form implements Validatable {
        public Map<String, Object> extensions = new HashMap<>();

        @SuppressWarnings("unchecked")
        @Override
        public <T> T getExtension(String name) {
            return (T) extensions.get(name);
        }

        @Override
        public <T> void setExtension(String name, T extension) {
            extensions.put(name, extension);
        }

        public String get$field$() {
            return "abc";
        }
    }
}
