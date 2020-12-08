package yezan.training.jobizyapi.factory;

import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.domain.SkillRequirement;

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
