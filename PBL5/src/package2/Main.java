package package2;

import role.Lion;
import role.Role;
import role.Staff;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static MemberService memberService;

    public static void main(String[] args) {

        MemberRepository repository = chooseRepository();

        memberService = new MemberService(repository);

        while (true) {
            System.out.println("===== 멋사 멤버 관리 시스템 (Step2 : DI 적용) =====");
            System.out.println("1. 멤버 등록 ");
            System.out.println("2. 전체 멤버 조회 ");
            System.out.println("3. 이름으로 검색 ");
            System.out.println("4. 종료 ");
            System.out.println("선택: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    save();
                    break;
                case 2:
                    showAll();
                    break;
                case 3:
                    findByName();
                    break;
                case 4:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 입력");
            }
        }

    }

    private static MemberRepository chooseRepository() {
        System.out.println("저장소 선택");
        System.out.println("1. MemoryMemberRepository");
        System.out.println("2. MockMemberRepository");
        System.out.println("선택: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            return new MemoryMemberRepository();
        }

        return new MockMemberRepository();

    }

    // 등록
    private static void save() {

        System.out.println("역할 선택 (1: 아기사자, 2: 운영진): ");
        int roleChoice = sc.nextInt();
        sc.nextLine();

        System.out.println("정보 입력");
        System.out.println("이름: ");
        String name = sc.nextLine();

        System.out.println("전공: ");
        String major = sc.nextLine();

        System.out.println("기수: ");
        int generation = sc.nextInt();
        sc.nextLine();

        System.out.println("파트: ");
        String part = sc.nextLine();

        Role member;

        if (roleChoice == 1) {
            System.out.println("학번: ");
            int studentId = sc.nextInt();
            sc.nextLine();

            member = new Lion(
                    name, major, generation, part, studentId
            );
        } else {
            System.out.println("직책: ");
            String position = sc.nextLine();

            member = new Staff(name, major, generation, part, position);
        }

        boolean result = memberService.save(member);

        if (result) {
            System.out.println("등록 완료");
        } else {
            System.out.println("중복 이름 존재");
        }
    }

    // 전체 조회
    private static void showAll() {

        List<Role> members = memberService.showAll();

        if (members.isEmpty()) {
            System.out.println("등록된 멤버 없음");
            return;
        }

        System.out.println();
        System.out.println("===== 전체 조회 =====");

        for (Role member: members) {
            member.printInfo();
            System.out.println("------------");
        }
    }

    // 이름 검색
    private static void findByName() {
        System.out.println("검색할 이름: ");
        String name = sc.nextLine();

        Role member = memberService.findByName(name);

        if (member == null) {
            System.out.println("검색 결과 없음");
        } else {
            System.out.println("===== 검색 결과 =====");
            member.printInfo();
        }
    }
}
