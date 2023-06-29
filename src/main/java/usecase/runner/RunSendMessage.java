package usecase.runner;

import domain.Cluster;
import usecase.creator.CreateSendMessageCluster;

public class RunSendMessage {
        Cluster cluster;

        private static RunSendMessage instance;

        public static RunSendMessage getInstance() {
            if (instance == null) {
                instance = new RunSendMessage();
            }
            return instance;
        }

        public void execute() {
            if (this.cluster == null) {
                CreateSendMessageCluster createSendMessageCluster = new CreateSendMessageCluster();
                this.cluster = createSendMessageCluster.create();
            }
            ExecuteCoordinatorProcess executeCoordinatorProcess = new ExecuteCoordinatorProcess();
            executeCoordinatorProcess.execute(cluster);
        }

}
