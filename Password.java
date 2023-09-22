package test;
import java.util.Scanner;
public class Password {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String isContinue="Y";
            while(isContinue.equalsIgnoreCase("Y")){
            System.out.println("请选择功能：");
            System.out.println("1.加密");
            System.out.println("2.解密");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("请输入要加密的字符串：");
                    String input = scanner.nextLine();
                    String encryptedPassword = encryptPassword(input);
                    System.out.println("加密后的密码：" + encryptedPassword);
                    break;
                case 2:
                    System.out.println("请输入要解密的字符串：");
                    String encryptedInput = scanner.nextLine();
                    String decryptedPassword = decryptPassword(encryptedInput);
                    System.out.println("解密后的密码：" + decryptedPassword);
                    break;
                default:
                    System.out.println("无效的选项！请重新输入！");
                    break;
            }
                System.out.println("是否继续？（Y/N）:");
                isContinue=scanner.nextLine();
        }

        }
        public static String encryptPassword(String password) {
            StringBuilder encryptedPassword = new StringBuilder();
            int length = password.length();

            for (int i = 0; i < length; i++) {
                char ch = password.charAt(i);
                int ascii = (int) ch + i + 1 + 3; // 加上位置和偏移值

                encryptedPassword.append((char) ascii);
            }


            char firstChar = encryptedPassword.charAt(0);
            char lastChar = encryptedPassword.charAt(length - 1);
            encryptedPassword.setCharAt(0, lastChar);
            encryptedPassword.setCharAt(length - 1, firstChar); // 调换第一位和最后一位
            StringBuilder reversedPassword = encryptedPassword.reverse(); // 反转字符串
            return reversedPassword.toString();
        }

        public static String decryptPassword(String encryptedPassword) {
            StringBuilder reversedPassword = new StringBuilder(encryptedPassword);

            int length = reversedPassword.length();
            char firstChar = reversedPassword.charAt(0);
            char lastChar = reversedPassword.charAt(length - 1);
            reversedPassword.setCharAt(0, lastChar);
            reversedPassword.setCharAt(length - 1, firstChar); // 恢复第一位和最后一位

            StringBuilder decryptedPassword = reversedPassword.reverse(); // 反转字符串

            StringBuilder originalPassword = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int ascii = (int) decryptedPassword.charAt(i) - i - 1 - 3; // 减去位置和偏移值
                originalPassword.append((char) ascii);
            }

            return originalPassword.toString();
        }

}
