package camp.week3;

import java.util.ArrayList;
import java.util.List;

public abstract class Container extends Component {
    private List<Component> components = new ArrayList<>();

    public Container(String name) {
        super(name);
    }

    public void add(Component component) {
        components.add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    protected void print() {
        System.out.println("print ".concat(this.getClass().getSimpleName()).concat("(").concat(getName()).concat(")"));
        for (Component component : components) {
            component.print();
        }
    }
}
