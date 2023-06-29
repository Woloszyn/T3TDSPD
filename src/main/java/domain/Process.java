package domain;

public class Process extends Thread {

    private int processId;
    private boolean isActive;
    private boolean isCoordinator;
    private final Runnable processRunnable;

    public Process(int processId, Runnable processRunnable) {
        this.processId = processId;
        this.setActive(true);
        this.setCoordinator(false);
        this.processRunnable = processRunnable;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getProcessId() {
        return processId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }

    public void setCoordinator(boolean coordinator) {
        isCoordinator = coordinator;
    }

    public void startElection(Cluster cluster) {
        if (!isActive) {
            return; // Ignore inactive processes
        }

        System.out.println("Process " + processId + " has started an election.");

        for (Process process : cluster.getProccessList()) {
            if (process.getProcessId() > processId) {
                // Send election message to higher process IDs
                System.out.println("Process " + processId + " sends election message to process " + process.getProcessId());
                if (!process.isActive()) {
                    // If the process is inactive, assume the role of coordinator
                    becomeCoordinator(cluster);
                    return;
                }
            }
        }

        // No higher process IDs found, become the coordinator
        becomeCoordinator(cluster);
    }

    private void becomeCoordinator(Cluster cluster) {
        isCoordinator = true;
        System.out.println("Process " + processId + " becomes the coordinator.");

        // Notify other processes about the new coordinator
        for (Process process : cluster.getProccessList()) {
            if (process.getProcessId() != processId) {
                process.setCoordinator(false);
                System.out.println("Process " + process.getProcessId() + " receives coordinator message from process " + processId);
            }
        }
    }

    public void run() {
        while (isActive) {
            System.out.println("Process " + processId + " is running");
            processRunnable.run();
        }
    }
}
