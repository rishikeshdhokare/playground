package callables;

import org.junit.Test;

import java.util.List;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;

public class CallablesTest {

    @Test
    public void shouldInvokeCallablesInParallel() throws Exception {
        long startTime = currentTimeMillis();
        List<String> output = new Callables().performMultipleTasks(100);
        System.out.println(currentTimeMillis() - startTime);
        assertEquals(100, output.size());
    }
}