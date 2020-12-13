package yezan.training.jobizyapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yezan.training.jobizyapi.domain.Candidate;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.service.CandidateCrudService;
import yezan.training.jobizyapi.service.JobMatcher;

import java.util.List;

@RestController
@RequestMapping("jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobMatcher jobMatcher;

    private final CandidateCrudService candidateCrudService;

    @GetMapping("matchingCandidate/{candidateId}")
    public ResponseEntity<List<Job>> findAllMatchingJobs(@PathVariable long candidateId) {
        Candidate candidate = candidateCrudService.findById(candidateId);
        List<Job> jobs = jobMatcher.findAllMatchingJobs(candidate);
        return ResponseEntity.ok(jobs);
    }
}
