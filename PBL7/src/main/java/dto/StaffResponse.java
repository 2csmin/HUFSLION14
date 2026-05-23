package dto;

import domain.role.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StaffResponse {

    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName;
    private String position;

    public static StaffResponse from(Staff staff) {
        return new StaffResponse(
                staff.getName(),
                staff.getMajor(),
                staff.getGeneration(),
                staff.getPart(),
                staff.getRoleName(),
                staff.getPosition()
        );
    }
}
