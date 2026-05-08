package package1;

import role.Role;

import java.util.List;

public class MemberService {

    // 직접 생성
    private MemberRepository repository = new MemberRepository();

    // 등록
    public boolean register(Role member) {

        if (repository.existName(member.getName())) {
            return false;
        }

        repository.save(member);
        return true;
    }

    // 이름 검색
    public Role findByName(String name) {
        return repository.findByName(name);
    }

    // 전체 조회
    public List<Role> showAll() {
        return repository.showAll();
    }
}