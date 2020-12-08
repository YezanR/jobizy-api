package yezan.training.jobizyapi.domain;

import lombok.Data;
import lombok.NonNull;
import yezan.training.jobizyapi.exception.JobApplicationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Candidate {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private LocalDate birthDate;

    private final List<Experience> experiences = new ArrayList<>();

    public Candidate(String firstName, String lastName, String email, LocalDate birthDate) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setBirthDate(birthDate);
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
