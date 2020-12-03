package yezan.training.jobizyapi.entity;

import lombok.Data;

import java.util.Set;

@Data
public class Job {
    private Set<SkillRequirement> skillRequirements;
}
