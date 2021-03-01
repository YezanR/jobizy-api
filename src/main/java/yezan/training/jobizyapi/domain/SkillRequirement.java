package yezan.training.jobizyapi.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SkillRequirement {
    private long id;

    private final Skill skill;
    private int monthsOfExperience;

    public SkillRequirement(Skill skill, int monthsOfExperience) {
        this.skill = skill;
        this.monthsOfExperience = monthsOfExperience;
    }

    public SkillRequirement(long id, Skill skill, int monthsOfExperience) {
        this(skill, monthsOfExperience);
        this.id = id;
    }
}
