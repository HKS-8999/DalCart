package dalcart.app.models.items;

public class Plants extends HeaderDecorator {
    public Plants(HomeHeader header) {
        this.header = header;
    }

    public String decorate() {
        return header.decorate() + "\n" + messageToAdd();
    }

    public String messageToAdd() {
        return "Grab your Plants...";
    }
}
