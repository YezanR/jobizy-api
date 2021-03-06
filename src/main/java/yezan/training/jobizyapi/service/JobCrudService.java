package yezan.training.jobizyapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.domain.SkillRequirement;
import yezan.training.jobizyapi.repository.JobRepository;
import yezan.training.jobizyapi.repository.SkillRequirementRepository;
import yezan.training.jobizyapi.validation.group.JobCreation;
import yezan.training.jobizyapi.validation.group.JobUpdate;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class JobCrudService {

    private final JobRepository jobRepository;
    private final SkillRequirementRepository skillRequirementRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public Job findById(long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("job with id " + id + " not found"));
    }

    public Job create(Job job) {
        validateJob(job, JobCreation.class);
        return jobRepository.save(job);
    }

    public Job update(Job job) {
        validateJob(job, JobUpdate.class);
        return jobRepository.save(job);
    }

    private void validateJob(Job job, Class<?>... groupClasses) {
        Set<ConstraintViolation<Job>> violations = validator.validate(job, groupClasses);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public void delete(Job job) {
        List<Long> relatedSkillRequirementIds = job.getSkillRequirements().stream()
                .map(SkillRequirement::getId)
                .collect(Collectors.toList());
        skillRequirementRepository.deleteByIdIn(relatedSkillRequirementIds);

        jobRepository.delete(job);
    }
}
