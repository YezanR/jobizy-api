package yezan.training.jobizyapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yezan.training.jobizyapi.domain.Candidate;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

}
