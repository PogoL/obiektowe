package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Stadium extends BaseEntity {
    private int capacity;
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Stadium(UUID id, String name, LocalDateTime creationTime, int capacity) {
        super(id, name, creationTime);
        this.capacity = capacity;
    }

    @Override
    public String toString() { return getName() + " (capacity:  "+ getCapacity() + ")"; }
}
