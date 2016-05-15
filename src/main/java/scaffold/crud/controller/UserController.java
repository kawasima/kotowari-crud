package scaffold.crud.controller;

import enkan.collection.Parameters;
import enkan.component.BeansConverter;
import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import kotowari.component.TemplateEngine;
import scaffold.crud.dao.UserDao;
import scaffold.crud.entity.User;
import scaffold.crud.form.UserForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static kotowari.routing.UrlRewriter.redirect;

/**
 * @author kawasima
 */
public class UserController {
    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private DomaProvider daoProvider;

    @Inject
    private BeansConverter beans;

    public HttpResponse index() {
        UserDao userDao = daoProvider.getDao(UserDao.class);
        List<User> users = userDao.selectAll();
        return templateEngine.render("user/list",
                "users", users);
    }

    public HttpResponse show(Parameters params) {
        UserDao userDao = daoProvider.getDao(UserDao.class);
        User user = userDao.selectById(params.getLong("id"));
        return templateEngine.render("user/show", "user", user);
    }

    public HttpResponse newForm() {
        return templateEngine.render("user/new",
                "user", new UserForm());
    }

    @Transactional
    public HttpResponse create(UserForm form) {
        if (form.hasErrors()) {
            return templateEngine.render("user/new", "user", form);
        }
        UserDao userDao = daoProvider.getDao(UserDao.class);
        User user = beans.createFrom(form, User.class);
        userDao.insert(user);
        return redirect(getClass(), "index", SEE_OTHER);
    }

    public HttpResponse edit(Parameters params) {
        UserDao userDao = daoProvider.getDao(UserDao.class);
        User user = userDao.selectById(params.getLong("id"));
        UserForm form = beans.createFrom(user, UserForm.class);
        return templateEngine.render("user/edit",
                "id", params.getLong("id"),
                "user", form);
    }

    @Transactional
    public HttpResponse update(Parameters params, UserForm form) {
        if (form.hasErrors()) {
            return templateEngine.render("user/edit",
                    "id", params.getLong("id"),
                    "user", form);
        }
        UserDao userDao = daoProvider.getDao(UserDao.class);
        User user = userDao.selectById(params.getLong("id"));
        beans.copy(form, user);
        userDao.update(user);
        return redirect(getClass(), "show?id=" + user.getId(), SEE_OTHER);
    }

    @Transactional
    public HttpResponse delete(Parameters params) {
        UserDao userDao = daoProvider.getDao(UserDao.class);
        User user = userDao.selectById(params.getLong("id"));
        userDao.delete(user);

        return redirect(getClass(), "index", SEE_OTHER);
    }
}
