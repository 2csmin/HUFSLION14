package com.example.assignment.service;

import com.example.assignment.domain.Assignment;
import com.example.assignment.dto.AssignmentCreateRequest;
import com.example.assignment.dto.AssignmentResponse;
import com.example.assignment.dto.AssignmentUpdateRequest;
import com.example.assignment.repository.AssignmentRepository;
import com.example.global.exception.AssignmentNotFoundException;
import com.example.global.exception.MemberNotFoundException;
import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public AssignmentResponse createAssignment(Long memberId, AssignmentCreateRequest request) {
        Member member = findMember(memberId);

        Assignment assignment = new Assignment(
                request.getTitle(),
                request.getDescription(),
                member
        );

        Assignment savedAssignment = assignmentRepository.save(assignment);

        return AssignmentResponse.from(savedAssignment);
    }

    public List<AssignmentResponse> findAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    public List<AssignmentResponse> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    public AssignmentResponse findById(Long id) {
        Assignment assignment = findAssignment(id);
        return AssignmentResponse.from(assignment);
    }

    public List<AssignmentResponse> searchAssignments(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    @Transactional
    public AssignmentResponse updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = findAssignment(id);

        assignment.updateInfo(request.getTitle(), request.getDescription());

        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public void deleteAssignment(Long id) {
        Assignment assignment = findAssignment(id);
        assignmentRepository.delete(assignment);
    }

    private Assignment findAssignment(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException(
                        "해당 과제를 찾을 수 없습니다. id: " + id
                ));
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(
                        "해당 멤버를 찾을 수 없습니다. id: " + memberId
                ));
    }

}
