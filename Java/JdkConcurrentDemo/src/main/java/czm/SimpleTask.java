package czm;

public class SimpleTask implements Runnable{
    @Override
    public void run() {
        System.out.println("hello world");
    }
}
