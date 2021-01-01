package yezan.training.jobizyapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yezan.training.jobizyapi.domain.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

}
