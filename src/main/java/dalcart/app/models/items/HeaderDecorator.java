package dalcart.app.models.items;

public abstract class HeaderDecorator implements HomeHeader {
    HomeHeader header;

    @Override
    public String decorate() {
        return header.decorate();
    }
}
