package usecase;

import domain.Cluster;
import domain.Process;

public class GetCoordinator {
    public Process execute(Cluster cluster) {
        for (Process process : cluster.getProccessList()) {
            if (process.isActive()) {
                return process;
            }
        }

        return null;
    }
}
