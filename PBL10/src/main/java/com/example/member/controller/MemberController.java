package com.example.member.controller;

import com.example.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(
            @RequestBody LionCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createLion(request));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(
            @RequestBody StaffCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createStaff(request));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAllMembers(
            @RequestParam(required = false) String part
    ) {
        return ResponseEntity.ok(memberService.findAllMembers(part));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findMemberById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(memberService.findMemberById(id));
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(
            @PathVariable Long id,
            @RequestBody LionUpdateRequest request
    ) {
        return ResponseEntity.ok(memberService.updateLion(id, request));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(
            @PathVariable Long id,
            @RequestBody StaffUpdateRequest request
    ) {
        return ResponseEntity.ok(memberService.updateStaff(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(
            @PathVariable Long id
    ) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

}
