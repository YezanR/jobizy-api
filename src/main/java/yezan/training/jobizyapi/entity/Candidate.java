package yezan.training.jobizyapi.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Candidate {

    private final List<Experience> experiences;

    public Candidate() {
        experiences = new ArrayList<>();
    }

    public void addExperience(Experience experience) {
        experiences.add(experience);
    }

    public boolean matches(Job job) {
        for (SkillRequirement requirement: job.getSkillRequirements()) {
            boolean requirementFulfilled = false;
            for (Experience experience: experiences) {
                if (experience.matchesSkillRequirement(requirement)) {
                    requirementFulfilled = true;
                    break;
                }
            }

            if (!requirementFulfilled) {
                return false;
            }
        }

        return true;
    }
}
