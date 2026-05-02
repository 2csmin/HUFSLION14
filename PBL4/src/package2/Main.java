package package2;

import role.Role;
import role.Lion;
import role.Staff;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static List<Role> members = new ArrayList<>();
    static Map<String, List<Role>> partMap = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("멤버 관리 시스템");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 파트별 조회");
            System.out.println("5. 종료");
            System.out.println("선택: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerMember();
                    break;
                case 2:
                    printAllMembers();
                    break;
                case 3:
                    searchMemberByName();
                    break;
                case 4:
                    searchMemberByPart();
                    break;
                case 5:
                    System.out.println("종료합니다");
                    return;
                default:
                    System.out.println("잘못된 접근입니다.");
            }
        }
    }

    static void registerMember() {
        System.out.println("멤버 등록");

        System.out.println("역할 선택 (1: 아기사자, 2: 운영진): ");
        int roleType = sc.nextInt();
        sc.nextLine();

        System.out.println("이름: ");
        String name;

        while (true) {
            name = sc.nextLine();

            if (isDuplicateName(name)) {
                System.out.println("등록 실패: 이미 존재하는 이름입니다.");
            } else {
                break;
            }
        }

        System.out.println("전공: ");
        String major = sc.nextLine();

        System.out.println("기수: ");
        int generation = sc.nextInt();
        sc.nextLine();

        System.out.println("파트: ");
        String part = sc.nextLine();

        Role member;

        if (roleType == 1) {
            System.out.println("학번: ");
            int studentId = sc.nextInt();
            sc.nextLine();

            member = new Lion(name, major, generation, part, studentId);
        } else if (roleType == 2) {
            System.out.println("직책: ");
            String position = sc.nextLine();

            member = new Staff(name, major, generation, part, position);
        } else {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        members.add(member);

        if (!partMap.containsKey(part)) {
            partMap.put(part, new ArrayList<>());
        }

        partMap.get(part).add(member);

        System.out.println("등록 완료: " + name);
    }

    public static boolean isDuplicateName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void printAllMembers() {
        System.out.println("전체 멤버 목록");

        for (int i = 0; i < members.size(); i++) {
            Role member = members.get(i);

            System.out.println((i+1) + ". [" + member.getRoleName() + "] " + member.getName() + " - " + member.getGeneration() + "기");
        }
        System.out.println("총 " + members.size() + "명");
    }

    public static void searchMemberByName() {
        System.out.println("이름으로 검색");

        System.out.println("검색할 이름: ");
        String name = sc.nextLine();

        for (Role member : members) {
            if (member.getName().equals(name)) {
                System.out.println("[검색 결과]");
                member.printInfo();
                return;
            }
        }

        System.out.println("검색 결과가 없습니다.");
    }

    public static void searchMemberByPart() {
        System.out.println("파트별 조회");
        System.out.println("등록된 파트: " + partMap.keySet());
        System.out.println("조회할 파트: ");
        String part = sc.nextLine();

        List<Role> partMembers = partMap.get(part);

        if (partMembers == null) {
            System.out.println("해당 파트가 없습니다.");
            return;
        }

        System.out.println("[" + part + " 파트 멤버]");

        for (int i = 0; i < partMembers.size(); i++) {
            Role member = partMembers.get(i);

            System.out.println((i+1) + ". " + member.getName() + "(" + member.getRoleName() + ") " + member.getGeneration() + "기");
        }
    }
}
