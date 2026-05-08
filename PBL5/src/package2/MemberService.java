package package2;

import role.Role;

import java.util.List;

public class MemberService {

    // 인터페이스에 의존
    private final MemberRepository repository;

    // 생성자 주입
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // 저장
    public boolean save(Role member) {

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
