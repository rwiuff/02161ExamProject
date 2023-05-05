package taskfusion.facades;

import java.util.List;

import taskfusion.exceptions.InvalidPropertyException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.viewModels.RegularActivityViewModel;

public interface RegularActivityFacadeInterface {
   
    public void createRegularActivity(String title, String startWeek, String endWeek) throws OperationNotAllowedException, InvalidPropertyException, NotFoundException;
    public boolean hasRegularActivity(String title, String startWeek, String endWeek) throws NotFoundException;
    public List<RegularActivityViewModel> getRegularActivities() throws NotFoundException;
    public RegularActivityViewModel getRegularActivityById(int id)
            throws NotFoundException, OperationNotAllowedException;
}