import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CPRNumberTest {
    CPRNumber cprNumber;

    @Before
    public void setUp() throws Exception {
        cprNumber = new CPRNumber();
    }

    @Test
    public void setCpr() {
        cprNumber.setCpr();
    }
}