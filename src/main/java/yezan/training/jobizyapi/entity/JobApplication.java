package yezan.training.jobizyapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobApplication {
    private Candidate candidate;
    private Job job;
}
