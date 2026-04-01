import java.util.Scanner;

public class PBL_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = 0;

        while (true) {
            System.out.println("🦁저장할 아기사자 수를 5 이상 입력해주세요.");
            num = sc.nextInt();
            sc.nextLine();

            if (num >= 5) {
                break;
            } else {
                System.out.println("❗[오류] 5 이상 입력해주세요.");
            }
        }

        String[] lions = new String[num];

        System.out.println("✏️아기사자 이름을 입력해주세요.");
        for (int i = 0; i < num; i++) {
            lions[i] = sc.nextLine();
        }

        System.out.println("📋아기사자 명단을 최종적으로 출력합니다.");
        for (int i = 0; i < num; i++) {
            System.out.println("🦁" + (i + 1) + ". " + lions[i]);
        }

        sc.close();
    }
}
