package com.example.assignment.service;

import com.example.assignment.domain.Assignment;
import com.example.assignment.dto.AssignmentCreateRequest;
import com.example.assignment.dto.AssignmentResponse;
import com.example.assignment.dto.AssignmentUpdateRequest;
import com.example.assignment.repository.AssignmentRepository;
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
        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null) {
            return null;
        }

        Assignment assignment = new Assignment(
                request.getTitle(),
                request.getDescription(),
                member
        );

        Assignment savedAssignment = assignmentRepository.save(assignment);

        return AssignmentResponse.from(savedAssignment);
    }

    public List<AssignmentResponse> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
    }

    public AssignmentResponse findById(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);

        if (assignment == null) {
            return null;
        }

        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public AssignmentResponse updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);

        if (assignment == null) {
            return null;
        }

        assignment.updateInfo(request.getTitle(), request.getDescription());

        return AssignmentResponse.from(assignment);
    }

    @Transactional
    public boolean deleteAssignment(Long id) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);

        if (assignment == null) {
            return false;
        }

        assignmentRepository.delete(assignment);
        return true;
    }

}
