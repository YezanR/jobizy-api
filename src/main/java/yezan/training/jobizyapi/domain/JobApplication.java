package yezan.training.jobizyapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobApplication {
    private Candidate candidate;
    private Job job;
}
