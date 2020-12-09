package yezan.training.jobizyapi.service;

import yezan.training.jobizyapi.domain.Candidate;
import yezan.training.jobizyapi.repository.CandidateRepository;

public class CandidateCrudService {

    private final CandidateRepository candidateRepository;

    public CandidateCrudService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate findById(long id) {
        return candidateRepository.findById(id);
    }
}
