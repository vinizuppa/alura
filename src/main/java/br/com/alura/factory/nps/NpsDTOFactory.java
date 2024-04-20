package br.com.alura.factory.nps;

import br.com.alura.dto.nps.NpsResponseDTO;
import br.com.alura.entity.courses.Courses;
import br.com.alura.enums.NpsEnum;

public class NpsDTOFactory {
    public static NpsResponseDTO createCourseEvaluationEntity(Integer npsScore, NpsEnum npsDescription, Courses course) {
        return NpsResponseDTO
                .builder()
                .courseId(course.getId())
                .courseCode(course.getCode())
                .npsScore(npsScore)
                .npsDescription(npsDescription)
                .build();
    }
}
