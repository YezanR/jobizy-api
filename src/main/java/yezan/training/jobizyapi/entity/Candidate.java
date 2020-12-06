package yezan.training.jobizyapi.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Candidate {

    private long id;

    private final List<Experience> experiences;

    public Candidate() {
        experiences = new ArrayList<>();
    }

    public void addExperience(Experience experience) {
        experiences.add(experience);
    }

    public boolean matchesJob(Job job) {
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

    public void addExperience(Skill skill, int monthsOfExperience) {
        experiences.add(new Experience(skill, monthsOfExperience));
    }

    public JobApplication applyForJob(Job job) {
        return new JobApplication(this, job);
    }

    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (!(otherObject instanceof Candidate)) {
            return false;
        }

        return getId() == ((Candidate) otherObject).getId();
    }
}
