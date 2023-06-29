package usecase.runner;

import domain.Cluster;
import usecase.creator.CreateNewServerPromptCluster;

public class RunServerPrompt {

    private static RunServerPrompt instance;
    private Cluster cluster;

    public static RunServerPrompt getInstance() {
        if (instance == null) {
            instance = new RunServerPrompt();
        }
        return instance;
    }

    public void execute() {
        if (cluster == null) {
            CreateNewServerPromptCluster createNewServerPromptCluster = new CreateNewServerPromptCluster();
            cluster = createNewServerPromptCluster.create();
        }
        ExecuteCoordinatorProcess executeCoordinatorProcess = new ExecuteCoordinatorProcess();
        executeCoordinatorProcess.execute(cluster);
    }

}
