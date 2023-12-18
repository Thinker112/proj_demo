public class Main {
    public static void main(String[] args) {


        int i = 0;
        try {
            i = 1 / 0;
        } catch (Exception e) {
            System.out.println("e = " + e);
            return;
        }
        System.out.println("i = " + i);
    }
}
