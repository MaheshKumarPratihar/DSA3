import java.util.Arrays;

public class Utility {
    static void print1d(int[] a) {
        Arrays.stream(a).forEach(el -> System.out.print(el + " "));
    }
}
