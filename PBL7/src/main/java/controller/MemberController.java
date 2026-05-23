package controller;

import dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(
            @RequestBody LionCreateRequest request
    ) {
        LionResponse response = memberService.createLion(request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(
            @RequestBody StaffCreateRequest request
    ) {
        StaffResponse response = memberService.createStaff(request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> findMember(
            @PathVariable String name
    ) {
        Object response = memberService.findMember(name);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(
            @PathVariable String name,
            @RequestBody LionUpdateRequest request
    ) {
        LionResponse response = memberService.updateLion(name, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(
            @PathVariable String name,
            @RequestBody StaffUpdateRequest request
    ) {
        StaffResponse response = memberService.updateStaff(name, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(
            @PathVariable String name
    ) {
        boolean deleted = memberService.deleteMember(name);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
