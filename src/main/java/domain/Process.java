package domain;

public class Process {

    private int id;
    private boolean isActive;

    public Process(int id) {
        this.id = id;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
