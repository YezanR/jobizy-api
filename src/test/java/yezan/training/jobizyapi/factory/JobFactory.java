package yezan.training.jobizyapi.factory;

import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.domain.Skill;
import yezan.training.jobizyapi.domain.SkillRequirement;

import java.util.ArrayList;
import java.util.List;

public class JobFactory {

    private JobFactory() {

    }

    public static Job createWithSkillRequirements(String title, SkillRequirement... skillRequirements) {
        Job job = new Job(title);

        for (SkillRequirement skillRequirement : skillRequirements) {
            job.addSkillRequirement(skillRequirement);
        }

        return job;
    }

    public static Job createWithSkillRequirements(long id, String title, SkillRequirement... skillRequirements) {
        Job job = createWithSkillRequirements(title, skillRequirements);
        job.setId(id);
        return job;
    }

    public static List<Job> generateMany() {
        ArrayList<Job> jobs = new ArrayList<>();

        jobs.add(new Job("IT Manager"));
        jobs.add(new Job("Java developer"));
        jobs.add(new Job("Angular tech lead"));

        return jobs;
    }
}
