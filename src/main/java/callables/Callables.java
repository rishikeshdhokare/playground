package callables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.newFixedThreadPool;

class Callables {

    List<String> performMultipleTasks(int count) throws Exception {
        List<Callable<String>> tasks = prepareTasks(count);
        ExecutorService executorService = newFixedThreadPool(count);
        List<Future<String>> futures = executorService.invokeAll(tasks);
        return buildOutput(count, futures);
    }

    private List<String> buildOutput(int count, List<Future<String>> futures) throws Exception {
        List<String> output = new ArrayList<>(count);
        for (Future<String> future: futures) {
            output.add(future.get());
        }
        return output;
    }

    private List<Callable<String>> prepareTasks(int count) {
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tasks.add(this::task);
        }
        return tasks;
    }

    private String task() throws InterruptedException {
        Thread.sleep(2000);
        return new Random().toString();
    }
}
