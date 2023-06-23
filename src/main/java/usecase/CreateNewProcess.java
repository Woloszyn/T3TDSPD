package usecase;

import domain.Cluster;
import domain.Process;

public class CreateNewProcess {
    public void execute(Cluster cluster) {
        int newProcessId = cluster.getProccessList().size();
        Process newProcess = new Process(newProcessId);
        cluster.addProccess(newProcess);
    }
}
