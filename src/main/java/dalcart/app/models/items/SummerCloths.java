package dalcart.app.models.items;

public class SummerCloths extends HeaderDecorator {
    public SummerCloths(HomeHeader header) {
        this.header = header;
    }

    public String decorate() {
        return header.decorate() + "\n" + messageToAdd();
    }

    public String messageToAdd() {
        return "Grab your Shorts, Pants and Dresses...";
    }
}
