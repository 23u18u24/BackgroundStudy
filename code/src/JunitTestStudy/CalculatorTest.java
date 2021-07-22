package JunitTestStudy;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    @Before
    public void init() {
        System.out.println("这是before");
    }
    @Test
    public void addTest() {
        Calculator c = new Calculator();
        Assert.assertEquals(c.add(1, 2), 3);
    }

    @After
    public void close() {
        System.out.println("这是after");
    }
}
