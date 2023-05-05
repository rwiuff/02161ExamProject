package taskfusion.facades;

import java.io.IOException;
import java.net.URISyntaxException;
import taskfusion.exceptions.NotFoundException;
import taskfusion.exceptions.OperationNotAllowedException;
import taskfusion.viewModels.ReportViewModel;

public interface ReportFacadeInterface {
    
    public ReportViewModel generateProjectRaport(String projectNumber) throws NotFoundException, OperationNotAllowedException;
    public void saveReport(String projectNumber, String reportDate, String saveDirectory) throws NotFoundException, IOException, URISyntaxException;

}
