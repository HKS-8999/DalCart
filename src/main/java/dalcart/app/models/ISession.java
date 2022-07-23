package dalcart.app.models;

import javax.servlet.http.HttpSession;

public interface ISession {

    public boolean isAdminInSession(HttpSession session);

    public boolean isUserInSession(HttpSession session);
}
