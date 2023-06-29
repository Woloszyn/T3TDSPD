package usecase.creator;

import domain.Cluster;
import usecase.SendMessage;

public class CreateSendMessageCluster {

    public Cluster create() {
        CreateNewProcess createNewProcess = new CreateNewProcess();
        Cluster cluster = new Cluster("SenderMessage");
        for (int i = 0; i < 9; i++) {
            createNewProcess.execute(cluster, SendMessage::execute, i);
        }
        return cluster;
    }

}
