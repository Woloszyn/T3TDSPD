package domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cluster {
    private String category;

    public Cluster(String category) {
        this.category = category;
    }

    private List<Process> processList = new CopyOnWriteArrayList<>();

    public List<Process> getProccessList() {
        return processList;
    }

    public void setProccessList(List<Process> processList) {
        this.processList = processList;
    }

    public void addProccess(Process process) {
        processList.add(process);
    }


    public void startLeaderElection() {
        for (Process process : processList) {
            process.startElection(this);
        }
    }

}
