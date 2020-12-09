package yezan.training.jobizyapi.repository;

import org.springframework.stereotype.Repository;
import yezan.training.jobizyapi.domain.Job;

import java.util.List;

@Repository
public interface JobRepository {
    List<Job> findAll();
}
