package dalcart.app.models.items;

public class Stationary extends HeaderDecorator {
    public Stationary(HomeHeader header) {
        this.header = header;
    }

    public String decorate() {
        return header.decorate() + "\n" + messageToAdd();
    }

    public String messageToAdd() {
        return "Grab your Stationary..";
    }
}
