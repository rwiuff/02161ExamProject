package taskfusion.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.helpers.SingletonHelpers;

public class ProjectTest {
  @BeforeEach
  public void resetSingletons() {
    SingletonHelpers.resetSingletons();
  }

  @Test 
  public void testGenerateProjectNumber() { // Up for debate tomorrow

  }
}
