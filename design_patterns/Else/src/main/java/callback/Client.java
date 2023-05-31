package callback;

public class Client {
    public static void main(String[] args) {
        var task = new SimpleTask();

        task.executeWith(() -> System.out.println("done now. "));
    }
}
