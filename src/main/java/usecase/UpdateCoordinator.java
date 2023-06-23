package usecase;

import domain.Cluster;
import domain.Process;

public class UpdateCoordinator {

    public void execute(Cluster cluster, int newCoordinatorId) {
        for (Process process : cluster.getProccessList()) {
            process.setActive(process.getId() == newCoordinatorId);
        }
    }

}
