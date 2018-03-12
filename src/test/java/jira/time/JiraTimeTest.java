package jira.time;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Unit test for simple JiraTime.
 */
@RunWith(Parameterized.class)
public class JiraTimeTest
    extends TestCase
{
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "0m", 0 },
                { "0h", 0 },
                { "0d", 0 },
                { "0w", 0 },
                { "1h", 60 },
                { "4h", 240 },
                { "1d", 480 },
                { "1d4h30m", 750 },
                { "2w3d12h30m", 6990 },
                { "2w3d25h90m", 7830 },
                { "0j", -1 },
                { "0 m", -1 },
                { "0h 0m", -1 },
                { "0m0h", -1 },
                { "2w 3h 25d 90m", -1 },
        });
    }

    @Parameterized.Parameter
    public String expression;

    @Parameterized.Parameter(1)
    public int expected;

    @org.junit.Test
    public void test() {
        if (expected == -1) {
            testException();
        } else {
            testResult();
        }
    }

    public void testResult() {
        int result = new JiraTime().calculate(expression);
        assertEquals(expected, result);
    }

    public void testException() {
        try {
            new JiraTime().calculate(expression);
            fail();
        } catch(IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
