package yezan.training.jobizyapi.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class Skill {

    @NonNull
    private String name;

    public Skill(@NonNull String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
