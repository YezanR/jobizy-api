package yezan.training.jobizyapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.repository.JobRepository;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Validated
public class JobCrudService {

    private final JobRepository jobRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public Job findById(long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("job with id " + id + " not found"));
    }

    public Job create(Job job) {
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return jobRepository.save(job);
    }
}
