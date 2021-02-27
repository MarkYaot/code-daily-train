package czm;

import java.util.concurrent.Callable;

public class ComputeTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
