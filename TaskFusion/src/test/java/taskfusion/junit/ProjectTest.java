package taskfusion.junit;

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
