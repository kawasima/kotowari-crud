package scaffold.crud;

import enkan.Application;
import enkan.application.WebApplication;
import enkan.config.ApplicationFactory;
import enkan.endpoint.ResourceEndpoint;
import enkan.middleware.*;
import enkan.middleware.devel.StacktraceMiddleware;
import enkan.middleware.doma2.DomaTransactionMiddleware;
import enkan.middleware.metrics.MetricsMiddleware;
import enkan.predicate.NonePredicate;
import enkan.system.inject.ComponentInjector;
import kotowari.middleware.*;
import kotowari.routing.Routes;
import scaffold.crud.controller.PartsController;
import scaffold.crud.controller.UserController;

import static enkan.util.BeanBuilder.*;

/**
 * @author kawasima
 */
public class MyApplicationFactory implements ApplicationFactory {
    @Override
    public Application create(ComponentInjector injector) {
        WebApplication app = new WebApplication();

        // Routing
        Routes routes = Routes.define(r -> {
            r.resource(UserController.class);
            r.get("/parts/textfield").to(PartsController.class, "textfield");
        }).compile();

        // Enkan
        app.use(new DefaultCharsetMiddleware<>());
        app.use(new MetricsMiddleware<>());
        app.use(new NonePredicate<>(), new ServiceUnavailableMiddleware<>(new ResourceEndpoint("/public/html/503.html")));
        app.use(new StacktraceMiddleware<>());
        app.use(new TraceMiddleware<>());
        app.use(new ContentTypeMiddleware<>());
        app.use(new ParamsMiddleware<>());
        app.use(new MultipartParamsMiddleware<>());
        app.use(builder(new MethodOverrideMiddleware<>())
                .set(MethodOverrideMiddleware::setGetterFunction, "_method")
                .build());
        app.use(new NormalizationMiddleware<>());
        app.use(new NestedParamsMiddleware<>());
        app.use(new CookiesMiddleware<>());
        app.use(new SessionMiddleware<>());
        // Kotowari
        app.use(new ResourceMiddleware<>());
        app.use(new RenderTemplateMiddleware<>());
        app.use(new RoutingMiddleware<>(routes));
        app.use(new DomaTransactionMiddleware<>());
        app.use(new FormMiddleware<>());
        app.use(new ValidateBodyMiddleware<>());
        app.use(new ControllerInvokerMiddleware<>(injector));

        return app;
    }
}
