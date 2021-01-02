package yezan.training.jobizyapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.repository.JobRepository;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Validated
public class JobCrudService {

    private final JobRepository jobRepository;

    public Job findById(long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("job with id " + id + " not found"));
    }

    public Job create(Job job) {
        return jobRepository.save(job);
    }
}
