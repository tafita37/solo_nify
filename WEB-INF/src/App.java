import bdd.BddObject;
import model.dent.Dent;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(BddObject.findById(null, Dent.class, "1", "postgres", "AnaTaf37", "nify"));
    }
}
