package networktest;

import static org.junit.Assert.*;
import network.DefaultShannonsModel;

import org.junit.Before;
import org.junit.Test;

/*
 * @author Ruchika Chona
 */

public class ShannonsTheoremTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShannonsTheorem() {
		DefaultShannonsModel model = new DefaultShannonsModel();
		assertEquals("The result is ", 26.63, model.maximumDataRate(4, 20), 0.1);
	}

}
