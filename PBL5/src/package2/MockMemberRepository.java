package package2;

import role.Role;
import role.Lion;
import role.Staff;

import java.util.ArrayList;
import java.util.List;

public class MockMemberRepository implements MemberRepository {

    private List<Role> members = new ArrayList<>();

    public MockMemberRepository() {

        members.add(new Lion("김사자", "컴퓨터공학과", 14, "백엔드", 20202020));

        members.add(new Staff("이사자", "컴퓨터공학과", 13, "프론트엔드", "대표"));

    }

    @Override
    public void save(Role member) {
        // members.add(member);
        System.out.println("목데이터");
    }

    @Override
    public boolean existName(String name) {
        for (Role member:members) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Role findByName(String name) {
        for (Role member:members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Role> showAll() {
        return members;
    }
}
