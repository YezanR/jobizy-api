package yezan.training.jobizyapi.domain;

import lombok.Data;
import yezan.training.jobizyapi.validation.group.JobCreation;
import yezan.training.jobizyapi.validation.group.JobUpdate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Job {

    @Min(value = 1, groups = JobUpdate.class)
    private long id;

    @NotEmpty(groups = {JobCreation.class, JobUpdate.class})
    private String title;

    private final Map<String, SkillRequirement> skillRequirements = new HashMap<>();

    public Job() {

    }

    public Job(String title) {
        this.title = title;
    }

    public Job(long id, String title) {
        this(title);
        this.id = id;
    }

    public void addSkillRequirement(SkillRequirement skillRequirement) {
        Skill skill = skillRequirement.getSkill();

        if (skillRequirements.get(skill.getName()) != null) {
            throw new IllegalArgumentException("Duplicate skill, skill '" + skill + "' " +
                    "has already a defined requirement");
        }

        skillRequirements.put(skill.getName(), skillRequirement);
    }

    public void addAllSkillRequirement(Set<SkillRequirement> skillRequirements) {
        skillRequirements.forEach((this::addSkillRequirement));
    }

    public SkillRequirement getSkillRequirementBySkillName(String skillName) {
        return skillRequirements.get(skillName);
    }

    public Set<SkillRequirement> getSkillRequirements() {
        return new HashSet<>(skillRequirements.values());
    }
}
