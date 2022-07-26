package dalcart.app.models.items;

public class WarmCloths extends HeaderDecorator {
    public WarmCloths(HomeHeader header) {
        this.header = header;
    }

    public String decorate() {
        return header.decorate() + "\n" + messageToAdd();
    }

    public String messageToAdd() {
        return "Grab your Sweaters...";
    }
}
