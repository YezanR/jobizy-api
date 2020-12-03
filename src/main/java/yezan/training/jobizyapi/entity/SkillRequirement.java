package yezan.training.jobizyapi.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SkillRequirement {
    private final Skill skill;
    private int monthsOfExperience;

    public SkillRequirement(Skill skill, int monthsOfExperience) {
        this.skill = skill;
        this.monthsOfExperience = monthsOfExperience;
    }
}
