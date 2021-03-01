package yezan.training.jobizyapi.repository;

import org.springframework.data.repository.CrudRepository;
import yezan.training.jobizyapi.domain.SkillRequirement;

import java.util.Collection;

public interface SkillRequirementRepository extends CrudRepository<SkillRequirement, Long> {
    void deleteByIdIn(Collection<Long> ids);
}
