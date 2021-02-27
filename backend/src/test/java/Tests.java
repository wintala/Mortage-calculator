import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static util.MathHelpers.*;
import util.Mortage;


public class Tests {
    @Test
    public void testPowerRaising()
    {
        assertEquals(32, raiseToPower(2, 5), 0.0001);
        assertEquals(0.0001, raiseToPower(0.1, 4), 0.0001);
        assertEquals(0, raiseToPower(0, 11), 0.00001);
        assertEquals(11.390625, raiseToPower(2.25, 3), 0.0001);
        assertEquals(1, raiseToPower(12, 0), 0.0001);
    }

    @Test
    public void testNthRoot()
    {
        assertEquals(3, nthRoot(81, 4, 0.0001), 0.0001);
        assertEquals(0.83255320, nthRoot(0.4, 5, 0.0001), 0.0001);
        assertEquals(0, nthRoot(0, 4, 0.00001), 0.0001);
        assertEquals(0.56234132, nthRoot(0.001, 12, 0.0001), 0.0001);
    }

    @Test
    public void testMortageClass()
    {
        Mortage m1 = new Mortage("Lisa", 5, 200000, 0.05);
        Mortage m2 = new Mortage("Gerald", 15, 500000, 0);

        assertEquals(3764.079674, m1.monthlyPayment, 0.001);
        assertEquals(0.004074123, m1.monthlyInterest, 0.00001);
        assertEquals(500000.0 / (15 * 12), m2.monthlyPayment, 0.001);
        assertEquals(0, m2.monthlyInterest, 0.00001);
    }
}
