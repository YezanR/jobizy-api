package yezan.training.jobizyapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yezan.training.jobizyapi.domain.Candidate;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobMatcher {

    private final JobRepository jobRepository;

    public List<Job> findAllMatchingJobs(Candidate candidate) {
        List<Job> matchingJobs = new ArrayList<>();
        List<Job> allJobs = jobRepository.findAll();

        allJobs.forEach(job -> {
            if (candidate.matchesJob(job)) {
                matchingJobs.add(job);
            }
        });

        return matchingJobs;
    }
}
