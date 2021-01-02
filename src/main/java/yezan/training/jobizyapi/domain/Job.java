package yezan.training.jobizyapi.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Job {

    @NotEmpty
    private String title;

    private final Map<String, SkillRequirement> skillRequirements = new HashMap<>();

    public Job() {

    }

    public Job(String title) {
        this.title = title;
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
