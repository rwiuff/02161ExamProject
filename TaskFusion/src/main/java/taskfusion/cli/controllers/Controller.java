package taskfusion.cli.controllers;

import taskfusion.app.TaskFusion;

public abstract class Controller implements ControllerInterface {
    
    protected TaskFusion app;

    public Controller(TaskFusion taskFusion) {
        this.app = taskFusion;
    } 

}
