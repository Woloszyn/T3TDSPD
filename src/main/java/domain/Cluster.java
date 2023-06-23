package domain;

import java.util.List;

public class Cluster {

    private static Cluster instance;

    public static Cluster getInstance() {
        if (instance == null) {
            instance = new Cluster();
        }
        return instance;
    }

    private List<Process> processList;

    public List<Process> getProccessList() {
        return processList;
    }

    public void setProccessList(List<Process> processList) {
        this.processList = processList;
    }

    public void addProccess(Process process) {
        processList.add(process);
    }

}
