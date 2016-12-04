/**
 * Created by a-morenets on 04.12.2016.
 */
public class Box {
    private int value;

    public Box(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("*** equals() ***");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Box box = (Box) o;

        return value == box.value;
    }

    @Override
    public int hashCode() {
        System.out.println("### hashCode() ###");
        return value;
    }
}
