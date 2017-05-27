package pl.edu.agh.student.mprezes.lvoteandroid.service;

import java.util.Map;

import pl.edu.agh.student.mprezes.lvoteandroid.model.context.ApplicationContext;
import pl.edu.agh.student.mprezes.lvoteandroid.model.context.ConnectionContext;

/**
 * Singleton responsible for providing contexts in any place it is needed.
 *
 * @author Krystian Majewski
 * @since 27.05.2017
 */

public class ContextProvider {

    private static final ContextProvider INSTANCE = new ContextProvider();

    private ApplicationContext applicationContext;
    private ConnectionContext connectionContext;

    private ContextProvider() {

    }

    private static ContextProvider getInstance() {
        return INSTANCE;
    }

    public static ConnectionContext getConnectionContext() {
        return getInstance().connectionContext;
    }

    public static void setConnectionContext(ConnectionContext context) {
        getInstance().connectionContext = context;
    }

    public static Map<String, ?> getHeadersMap() {
        return getConnectionContext().getHeadersMap();
    }

}
