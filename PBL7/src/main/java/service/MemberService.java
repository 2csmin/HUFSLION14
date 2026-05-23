package service;

import dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import domain.role.Lion;
import repository.MemberRepository;
import domain.role.Role;
import domain.role.Staff;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public LionResponse createLion(LionCreateRequest request) {

        Lion lion = new Lion(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );

        if (memberRepository.existsByName(request.getName())) {
            return null;
        }

        memberRepository.save(lion);

        return LionResponse.from(lion);
    }

    public StaffResponse createStaff(StaffCreateRequest request) {

        Staff staff = new Staff(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );

        if (memberRepository.existsByName(request.getName())) {
            return null;
        }

        memberRepository.save(staff);

        return StaffResponse.from(staff);
    }

    public LionResponse updateLion(String name, LionUpdateRequest request) {
        Role member = memberRepository.findByName(name);

        if (!(member instanceof Lion)) {
            return null;
        }

        Lion updatedLion = new Lion(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );

        memberRepository.updateByName(name, updatedLion);

        return LionResponse.from(updatedLion);
    }

    public StaffResponse updateStaff(String name, StaffUpdateRequest request) {
        Role member = memberRepository.findByName(name);

        if (!(member instanceof Staff)) {
            return null;
        }

        Staff updatedStaff = new Staff(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );

        memberRepository.updateByName(name, updatedStaff);

        return StaffResponse.from(updatedStaff);
    }

    public boolean deleteMember(String name) {
        return memberRepository.deleteByName(name);
    }

    public Object findMember(String name) {
        Role member = memberRepository.findByName(name);

        if (member == null) {
            return null;
        }

        if (member instanceof Lion lion) {
            return LionResponse.from(lion);
        }

        if (member instanceof Staff staff) {
            return StaffResponse.from(staff);
        }

        return null;
    }

}
