package com.example.member.service;

import com.example.global.exception.DuplicateMemberException;
import com.example.global.exception.MemberNotFoundException;
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

        checkDuplicateName(request.getName());

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

        checkDuplicateName(request.getName());

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
    public List<MemberResponse> findAllMembers(String part) {
        List<Member> members;

        if (part == null || part.isBlank()) {
            members = memberRepository.findAll();
        } else {
            members = memberRepository.findByPart(part);
        }

        return members.stream()
                .map(MemberResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberResponse findMemberById(Long id) {
        Member member = findMember(id);
        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse updateLion(Long id, LionUpdateRequest request) {
        Member member = findMember(id);

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
        Member member = findMember(id);

        member.updateInfo(
                request.getMajor(),
                request.getGeneration(),
                request.getPart()
        );

        member.updatePosition(request.getPosition());

        return MemberResponse.from(member);
    }

    @Transactional
    public void deleteMember(Long id) {

        Member member = findMember(id);

        memberRepository.delete(member);
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(
                        "해당 멤버를 찾을 수 없습니다. id: " + id
                ));
    }

    private void checkDuplicateName(String name) {
        if (memberRepository.existsByName(name)) {
            throw new DuplicateMemberException(
                    "이미 존재하는 이름입니다. name: " + name
            );
        }
    }

}