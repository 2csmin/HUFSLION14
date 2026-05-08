package package1;

import role.Role;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private List<Role> members = new ArrayList<>();

    // 저장
    public void save(Role member) {
        members.add(member);
    }

    // 이름 중복 확인
    public boolean existName(String name) {
        for (Role member:members) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // 이름 검색
    public Role findByName(String name) {
        for (Role member:members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    // 전체 조회
    public List<Role> showAll() {
        return members;
    }
}
