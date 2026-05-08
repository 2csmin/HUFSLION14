package package2;

import role.Role;

import java.util.List;

public interface MemberRepository {

    void save(Role member);

    boolean existName(String name);

    Role findByName(String name);

    List <Role> showAll();

}
