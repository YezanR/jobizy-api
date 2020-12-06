package yezan.training.jobizyapi.entity;

import lombok.Data;
import yezan.training.jobizyapi.exception.JobApplicationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Candidate {

    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;

    private final List<Experience> experiences = new ArrayList<>();

    public Candidate(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Candidate() {

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
        if (!matchesJob(job)) {
            throw new JobApplicationException("Job requirements not met");
        }

        return new JobApplication(this, job);
    }
}
