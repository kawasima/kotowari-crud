package scaffold.crud;

import enkan.middleware.MethodOverrideMiddleware;
import enkan.util.BeanBuilder;

/**
 * @author kawasima
 */
public class TestApplicationFactory {
    public void test() {
        BeanBuilder.builder(new MethodOverrideMiddleware())
                .set(MethodOverrideMiddleware::setGetterFunction, "").build();
    }
}
