package usecase.creator;

import domain.Chat;
import domain.ChatMessage;
import domain.Cluster;
import domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateNewServerPromptCluster {

    public static void process() {
        System.out.println("Send some message to the server:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String content = bufferedReader.readLine();
            User user = new User();
            user.setUsername("Server");
            user.setId(1);
            Chat.getInstance().addMessage(new ChatMessage(user, content));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cluster create() {
        CreateNewProcess createNewProcess = new CreateNewProcess();
        Cluster cluster = new Cluster("ServerPrompt");
        for (int i = 0; i < cluster.getProccessList().size(); i++) {
            createNewProcess.execute(cluster, CreateNewServerPromptCluster::process, i);
        }
        return cluster;
    }

}