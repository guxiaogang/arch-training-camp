package camp.week3;

public abstract class Widget extends Component {

    public Widget(String name) {
        super(name);
    }

    @Override
    protected void print() {
        System.out.println("print ".concat(this.getClass().getSimpleName()).concat("(").concat(getName()).concat(")"));
    }
}
