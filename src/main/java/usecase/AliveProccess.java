package usecase;

import domain.Cluster;
import domain.Process;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AliveProccess {

    public List<Process> execute(Cluster cluster) {
        List<Process> aliveProcesses = new CopyOnWriteArrayList<>();

        for (Process process : cluster.getProccessList()) {
            if (process.isActive()) {
                aliveProcesses.add(process);
            }
        }

        return aliveProcesses;
    }

}
