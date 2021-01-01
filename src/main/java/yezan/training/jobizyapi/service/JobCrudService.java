package yezan.training.jobizyapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.repository.JobRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class JobCrudService {

    private final JobRepository jobRepository;

    public Job findById(long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("job with id " + id + " not found"));
    }
}
