package yezan.training.jobizyapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezan.training.jobizyapi.domain.Candidate;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.service.CandidateCrudService;
import yezan.training.jobizyapi.service.JobCrudService;
import yezan.training.jobizyapi.service.JobMatcher;
import java.util.List;

@RestController
@RequestMapping("jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobMatcher jobMatcher;
    private final CandidateCrudService candidateCrudService;
    private final JobCrudService jobCrudService;

    @GetMapping("matchingCandidate/{candidateId}")
    public ResponseEntity<List<Job>> findAllMatchingJobs(@PathVariable long candidateId) {
        Candidate candidate = candidateCrudService.findById(candidateId);
        List<Job> jobs = jobMatcher.findAllMatchingJobs(candidate);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("{jobId}/apply/{candidateId}")
    public ResponseEntity<String> applyForJob(@PathVariable long jobId, @PathVariable long candidateId) {
        Candidate candidate = candidateCrudService.findById(candidateId);
        Job job = jobCrudService.findById(jobId);
        candidate.applyForJob(job);
        return ResponseEntity.ok("Thank you for applying to '" + job.getTitle() + "'");
    }
}
