package br.com.alura.service.nps;

import br.com.alura.dto.nps.NpsResponseDTO;
import br.com.alura.entity.courseEvaluation.CourseEvaluation;
import br.com.alura.entity.courses.Courses;
import br.com.alura.enums.NpsEnum;
import br.com.alura.factory.nps.NpsDTOFactory;
import br.com.alura.service.courseEvaluation.CourseEvaluationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NpsService {
    private final CourseEvaluationService courseEvaluationService;

    public NpsService(CourseEvaluationService courseEvaluationService) {
        this.courseEvaluationService = courseEvaluationService;
    }

    public List<NpsResponseDTO> calculateNpsOfCourses() {
        var courseEvaluationList = courseEvaluationService.getAllCourseEvaluationsForNpsCalculator();

        List<Courses> coursesList = this.extractCourses(courseEvaluationList);
        List<NpsResponseDTO> npsResponseDTOList = new ArrayList<>();

        coursesList.forEach(course -> {
            var allEvaluationsForThisCourse = this.extractAllCourseEvaluations(courseEvaluationList, course.getId());
            var promoterEvaluations = this.extractPromoterEvaluations(allEvaluationsForThisCourse);
            var detractorEvaluations = this.extractDetractorEvaluations(allEvaluationsForThisCourse);

            Double npsScore = this.calculateNpsScore(promoterEvaluations, detractorEvaluations, allEvaluationsForThisCourse);
            var npsDescription = this.verifyDescriptionOfNpsClassification(Math.toIntExact(npsScore.intValue()));

            npsResponseDTOList.add(NpsDTOFactory.createCourseEvaluationEntity(Math.toIntExact(npsScore.intValue()), npsDescription, course));
        });

        return npsResponseDTOList;
    }

    private List<Courses> extractCourses(List<CourseEvaluation> courseEvaluationList) {
        return courseEvaluationList.stream().map(CourseEvaluation::getCourses).distinct().collect(Collectors.toList());
    }

    private List<CourseEvaluation> extractAllCourseEvaluations(List<CourseEvaluation> courseEvaluationList, Integer courseId) {
        return courseEvaluationList.stream()
                .filter(courseEvaluation -> courseEvaluation.getCourses().getId() == courseId)
                .collect(Collectors.toList());
    }

    private List<Integer> extractPromoterEvaluations(List<CourseEvaluation> allEvaluationsForCourse) {
        return allEvaluationsForCourse.stream()
                .filter(courseEvaluation -> courseEvaluation.getScore() >= 9)
                .map(CourseEvaluation::getScore).collect(Collectors.toList());
    }

    private List<Integer> extractDetractorEvaluations(List<CourseEvaluation> allEvaluationsForCourse) {
        return allEvaluationsForCourse.stream()
                .filter(courseEvaluation -> courseEvaluation.getScore() <= 6)
                .map(CourseEvaluation::getScore).collect(Collectors.toList());
    }

    private Double calculateNpsScore(List<Integer> promoterEvaluations, List<Integer> detractorEvaluations, List<CourseEvaluation> allCourseEvaluations) {
        var calc = ( (promoterEvaluations.size() - detractorEvaluations.size()) /  (double) allCourseEvaluations.size() ) * 100;
        return calc;
    }

    private NpsEnum verifyDescriptionOfNpsClassification(Integer nps) {
        if (nps >= 75) {
            return NpsEnum.GREAT;
        } else if (nps >= 50) {
            return NpsEnum.VERY_GOOD;
        } else if (nps >= 0) {
            return NpsEnum.REASONABLE;
        } else {
            return NpsEnum.BAD;
        }
    }
}
