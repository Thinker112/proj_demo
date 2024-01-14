public class Main {
    public static void main(String[] args) {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            System.out.println("Windows");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            System.out.println("Unix/Linux/Mac");
        } else {
            System.out.println("Other");
        }
    }
}
