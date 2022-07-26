package dalcart.app.models.items;

import java.util.Calendar;

public class HeaderSetter {
    public static String messageToDisplay() {
        int m = Calendar.getInstance().get(Calendar.MONTH);
        if (m >= 3 && m <= 5) {
            HomeHeader spring = new Spring();
            HomeHeader plants = new Plants(spring);
            return plants.decorate();
        }
        if (m >= 6 && m <= 8) {
            HomeHeader summer = new Summer();
            HomeHeader summerCloths = new SummerCloths(summer);
            return summerCloths.decorate();
        }
        if (m == 9 || m == 10) {
            HomeHeader fall = new Fall();
            HomeHeader stationary = new Stationary(fall);
            return stationary.decorate();
        }
        if (m == 11 || m <= 2) {
            HomeHeader winter = new Winter();
            HomeHeader warmCloths = new WarmCloths(winter);
            return warmCloths.decorate();
        } else {
            return " ";
        }
    }
}
