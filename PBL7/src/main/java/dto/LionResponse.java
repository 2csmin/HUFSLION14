package dto;

import domain.role.Lion;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LionResponse {

    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String studentId;

    public static LionResponse from(Lion lion) {
        return new LionResponse(
                lion.getName(),
                lion.getMajor(),
                lion.getGeneration(),
                lion.getPart(),
                lion.getRoleName(),
                lion.getStudentId()
        );
    }
}
