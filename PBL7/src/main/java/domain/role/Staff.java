package domain.role;

import lombok.Getter;

@Getter
public class Staff extends Role {

    private String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    @Override
    public String getRoleName() {
        return "운영진";
    }
}