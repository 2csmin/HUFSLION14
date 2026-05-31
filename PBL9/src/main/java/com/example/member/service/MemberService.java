package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.domain.RoleType;
import com.example.member.dto.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse createLion(LionCreateRequest request) {

        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                RoleType.LION,
                request.getStudentId(),
                null
        );

        Member savedMember = memberRepository.save(member);
        return MemberResponse.from(savedMember);
    }

    @Transactional
    public MemberResponse createStaff(StaffCreateRequest request) {

        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                RoleType.STAFF,
                null,
                request.getPosition()
        );

        Member savedMember = memberRepository.save(member);
        return MemberResponse.from(savedMember);
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberResponse findMemberById(Long id) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return null;
        }

        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse updateLion(Long id, LionUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return null;
        }

        member.updateInfo(
                request.getMajor(),
                request.getGeneration(),
                request.getPart()
        );

        member.updateStudentId(request.getStudentId());

        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse updateStaff(Long id, StaffUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return null;
        }

        member.updateInfo(
                request.getMajor(),
                request.getGeneration(),
                request.getPart()
        );

        member.updatePosition(request.getPosition());

        return MemberResponse.from(member);
    }

    @Transactional
    public boolean deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            return false;
        }

        memberRepository.deleteById(id);
        return true;
    }

}