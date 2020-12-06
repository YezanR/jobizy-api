package yezan.training.jobizyapi.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Job {
    private long id;
    private final Set<SkillRequirement> skillRequirements = new HashSet<>();

    public void addSkillRequirement(SkillRequirement skillRequirement) {
        skillRequirements.add(skillRequirement);
    }

    public void addAllSkillRequirement(Set<SkillRequirement> skillRequirements) {
        this.skillRequirements.addAll(skillRequirements);
    }

    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (!(otherObject instanceof Job)) {
            return false;
        }

        return getId() == ((Job) otherObject).getId();
    }
}
