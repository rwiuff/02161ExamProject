package taskfusion.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.domain.RegularActivity;
import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.helpers.SingletonHelpers;
import taskfusion.viewModels.RegularActivityViewModel;

public class RegularActivityTest {

  @BeforeEach
  public void resetSingletons() {
    SingletonHelpers.resetSingletons();
  }


  @Test
  public void testRegularActivityViewModel() throws InvalidPropertyException {

    String title = "Ferie";
    String startWeek = "2301";
    String endWeek = "2302";

    RegularActivity model = new RegularActivity(title,startWeek,endWeek);
    RegularActivityViewModel viewModel = model.toViewModel();

    //Test for initial creation
    assertEquals(model.getId(), viewModel.id);
    assertEquals(title, viewModel.title);
    assertEquals(startWeek, viewModel.startWeek);
    assertEquals(endWeek, viewModel.endWeek);
  }

  @Test
  public void testRegularActivityListFromModels() throws InvalidPropertyException {

    List<RegularActivity> models = new ArrayList<>();
    
    models.add(new RegularActivity("Ferie", "2301", "2306"));
    models.add(new RegularActivity("Syg", "2109", "2110"));

    List<RegularActivityViewModel> viewModels = RegularActivityViewModel.listFromModels(models);
    assertEquals(models.size(), viewModels.size());
  }
  
}
