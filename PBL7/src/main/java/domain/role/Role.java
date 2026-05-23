package domain.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Role {

    private String name;
    private String major;
    private int generation;
    private String part;

    public abstract String getRoleName();
}