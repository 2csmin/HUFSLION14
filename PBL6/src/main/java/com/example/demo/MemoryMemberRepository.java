package com.example.demo;

import org.springframework.stereotype.Repository;
import role.Role;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private List<Role> members = new ArrayList<>();

    @Override
    public void save(Role member) {
        members.add(member);
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
