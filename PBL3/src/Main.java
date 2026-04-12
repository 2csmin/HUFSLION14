import role.Lion;
import role.Role;
import role.Staff;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 아기사자 입력
        System.out.println("아기사자 정보 입력");

        System.out.println("이름: ");
        String name = sc.nextLine();

        System.out.println("전공: ");
        String major = sc.nextLine();

        System.out.println("기수: ");
        int generation = sc.nextInt();
        sc.nextLine();

        System.out.println("파트 (백엔드/프론트엔드/기획/디자인): ");
        String part = sc.nextLine();

        System.out.println("학번: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        Role lion = new Lion(name, major, generation, part, studentId);

        // 운영진 입력
        System.out.println("운영진 정보 입력");

        System.out.println("이름: ");
        String sName = sc.nextLine();

        System.out.println("전공: ");
        String sMajor = sc.nextLine();

        System.out.println("기수: ");
        int sGeneration = sc.nextInt();
        sc.nextLine();

        System.out.println("파트 (백엔드/프론트엔드/기획/디자인): ");
        String sPart = sc.nextLine();

        System.out.println("직책: ");
        String position = sc.nextLine();

        Role staff = new Staff(name, major, generation, part, position);

        // 출력
        System.out.println("결과 출력");
        lion.printInfo();
        System.out.println("과제 제출 가능 여부: " + (lion.canSubmit() ? "가능" : "불가능"));

        System.out.println();

        staff.printInfo();
        System.out.println("과제 제출 가능 여부: " + (staff.canSubmit() ? "가능" : "불가능"));

        sc.close();
    }
}