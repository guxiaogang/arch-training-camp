package camp.week3;

public abstract class Component {

    private String name;

    public Component(String name) {
        this.name = name;
    }

    protected abstract void print();

    public String getName() {
        return name;
    }

    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }
}
