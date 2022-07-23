package dalcart.app.models;

import javax.servlet.http.HttpSession;

public interface ISecurity {

    public boolean isUserRoleAdmin(HttpSession session);

    public boolean isUserRoleUser(HttpSession session);
}
