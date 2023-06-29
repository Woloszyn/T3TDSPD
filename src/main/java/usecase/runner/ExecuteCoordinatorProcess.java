package usecase.runner;

import domain.Cluster;
import domain.Process;

public class ExecuteCoordinatorProcess {

    public void execute(Cluster cluster) {
        GetCoordinator getCoordinator = new GetCoordinator();
        Process process = null;
        do {
            if (process != null && !process.isActive()) {
                cluster.startLeaderElection();
            } else if (process == null) {
                cluster.startLeaderElection();
            }
            process = getCoordinator.execute(cluster);
            if (process != null) {
//                System.out.println("Coordinator is " + process.getProcessId());
                if (!process.isAlive()) {
                    process.start();
                }
                try {
                    Thread.sleep(60000);
                    process.setActive(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (process != null);
        System.out.println("No coordinator found");
    }

}
