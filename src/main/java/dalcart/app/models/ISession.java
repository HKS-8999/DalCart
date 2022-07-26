package dalcart.app.models;

import javax.servlet.http.HttpSession;

public interface ISession {

    boolean isAdminInSession(HttpSession session);

    boolean isUserInSession(HttpSession session);
}
