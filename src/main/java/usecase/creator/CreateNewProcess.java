package usecase.creator;

import domain.Cluster;
import domain.Process;

public class CreateNewProcess {

    public void execute(Cluster cluster, Runnable runnable, int idProcess) {
        Process process = new Process(idProcess, runnable);
        cluster.addProccess(process);
    }

}
