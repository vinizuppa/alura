package br.com.alura.dto.nps;

import br.com.alura.enums.NpsEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NpsResponseDTO {
    private Integer courseId;
    private String courseCode;
    private Integer npsScore;
    private NpsEnum npsDescription;
}
