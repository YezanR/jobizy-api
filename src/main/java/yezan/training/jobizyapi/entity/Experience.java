package yezan.training.jobizyapi.entity;

import lombok.Data;

@Data
public class Experience {
    private final Skill skill;
    private final int monthsOfExperience;

    public boolean matchesSkillRequirement(SkillRequirement requirement) {
        return skill.equals(requirement.getSkill()) &&
                monthsOfExperience >= requirement.getMonthsOfExperience();
    }
}
