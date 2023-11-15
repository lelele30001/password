package test;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Password {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String isContinue="Y";
            while(isContinue.equalsIgnoreCase("Y")){
                 System.out.println("请选择操作：");
                 System.out.println("1.加密");
                 System.out.println("2.解密");
                 System.out.println("3.判断密码强度");
                 System.out.println("4.密码生成");
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
                     case 3:
                         System.out.println("请输入要判断强度的密码：");
                         String checkPassword = scanner.nextLine();
                         System.out.println("此密码的强度为："+ checkPasswordStrength(checkPassword));
                         break;
                     case 4:
                         System.out.println("请输入要生成密码的长度：");
                         int length=scanner.nextInt();
                         scanner.nextLine();
                         System.out.println("生成的密码为："+generatePassword(length));
                         break;
                     default:
                         System.out.println("无效！请重新输入！");
                         break;
                }
                System.out.println("是否继续？（Y/N）:");
                isContinue=scanner.nextLine();
        }

        }
//        加密功能
        public static String encryptPassword(String password) {
            StringBuilder encryptedPassword = new StringBuilder();
            int length = password.length();

            for (int i = 0; i < length; i++) {
                char ch = password.charAt(i);
                int ascii = (int) ch + i + 1 + 3; // （1）加上位置和偏移值

                encryptedPassword.append((char) ascii);
            }

            char firstChar = encryptedPassword.charAt(0);
            char lastChar = encryptedPassword.charAt(length - 1);
            encryptedPassword.setCharAt(0, lastChar);
            encryptedPassword.setCharAt(length - 1, firstChar); // （2）调换第一位和最后一位
            StringBuilder reversedPassword = encryptedPassword.reverse(); // （3）反转字符串
            return reversedPassword.toString();
        }
//        解密功能
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
        //判断密码强度
        public static String checkPasswordStrength(String password) {
            if (password.length() < 8 || isOnlyOneType(password)) {
                return "弱强度";
            } else if (isHighStrength(password)) {
                return "高强度";
            } else if (isMiddleStrength(password)) {
                return "中强度";
            } else {
                return "弱强度";
            }
        }

    public static boolean isOnlyOneType(String password) {
        char[] chars = password.toCharArray();
        Set<Character> types = new HashSet<>();
        for (char c : chars) {
            types.add(c);
        }
        return types.size() == 1;
    }

    public static boolean isMiddleStrength(String password) {
        boolean hasDigit = false;
        boolean hasLetter = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }
        return hasDigit && hasLetter;
    }

    public static boolean isHighStrength(String password) {
        boolean hasDigit = false;
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
        }
        return hasDigit && hasLowerCase && hasUpperCase;
    }
    //密码生成
        public static String generatePassword(int length) {
            String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random = new Random();
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < length; i++) {
                password.append(characters.charAt(random.nextInt(characters.length())));
            }
            return password.toString();
        }
}
//


