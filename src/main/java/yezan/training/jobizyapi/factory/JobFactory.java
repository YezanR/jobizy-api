package yezan.training.jobizyapi.factory;

import yezan.training.jobizyapi.entity.Job;
import yezan.training.jobizyapi.entity.SkillRequirement;

import java.util.Set;

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
}
