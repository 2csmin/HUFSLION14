package role;

import policy.Policy;
import policy.StaffPolicy;

public class Staff extends Role {
    private String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    @Override
    public Policy getPolicy() {
        return new StaffPolicy();
    }

    @Override
    public void printInfo() {
        System.out.println("역할: 운영진");
        System.out.println("이름: " + getName() + " | 전공: " + getMajor() + " | 기수: " + getGeneration() + " | 파트: " + getPart());
        System.out.println("직책: " + position);
    }
}
