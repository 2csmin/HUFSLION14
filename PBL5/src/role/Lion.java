package role;

import policy.LionPolicy;
import policy.Policy;

public class Lion extends Role {
    private int studentId;

    public Lion(String name, String major, int generation, String part, int studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    public Policy getPolicy() {
        return new LionPolicy();
    }

    @Override
    public String getRoleName() {
        return "아기사자";
    }

    @Override
    public void printInfo() {
        System.out.println("역할: 아기사자");
        System.out.println("이름: " + getName() + " | 전공: " + getMajor() + " | 기수: " + getGeneration() + " | 파트: " + getPart());
        System.out.println("학번: " + studentId);
        System.out.println("과제 제출 가능 여부: " + canSubmit());
    }

}
