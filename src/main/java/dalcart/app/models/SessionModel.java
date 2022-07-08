//package dalcart.app.service;
//
//import dalcart.app.Repository.ISessionGenerator;
//import dalcart.app.Repository.IUserPersistence;
//import dalcart.app.models.User;
//
//import java.sql.SQLException;
//
//public class SessionService {
//    private IUserPersistence iUserPersistence;
//
//    public boolean isSessionGenerated(User user, ISessionGenerator sessionGenerator){
//        try {
//            sessionGenerator.saveSession(user);
//            return true;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
