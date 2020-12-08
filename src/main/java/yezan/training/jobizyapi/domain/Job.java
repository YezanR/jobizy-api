package yezan.training.jobizyapi.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class Job {
    private String title;

    public Job() {

    }

    public Job(String title) {
        this.title = title;
    }

    private final Map<String, SkillRequirement> skillRequirements = new HashMap<>();

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
