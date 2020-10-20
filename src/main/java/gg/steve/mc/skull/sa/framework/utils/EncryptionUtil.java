package gg.steve.mc.skull.sa.framework.utils;

public class EncryptionUtil {

    public static String getEncrypted(String input) {
        StringBuilder builder = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '0':
                    builder.append("#");
                    break;
                case '1':
                    builder.append("$");
                    break;
                case '2':
                    builder.append("[");
                    break;
                case '3':
                    builder.append("W");
                    break;
                case '4':
                    builder.append("B");
                    break;
                case '5':
                    builder.append("}");
                    break;
                case '6':
                    builder.append("*");
                    break;
                case '7':
                    builder.append("?");
                    break;
                case '8':
                    builder.append("!");
                    break;
                case '9':
                    builder.append("b");
                    break;
                default:
                    builder.append(c);
                    break;
            }
        }
        return builder.toString();
    }

    public static String getDecrypted(String input) {
        StringBuilder builder = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '#':
                    builder.append("0");
                    break;
                case '$':
                    builder.append("1");
                    break;
                case '[':
                    builder.append("2");
                    break;
                case 'W':
                    builder.append("3");
                    break;
                case 'B':
                    builder.append("4");
                    break;
                case '}':
                    builder.append("5");
                    break;
                case '*':
                    builder.append("6");
                    break;
                case '?':
                    builder.append("7");
                    break;
                case '!':
                    builder.append("8");
                    break;
                case 'b':
                    builder.append("9");
                    break;
                default:
                    builder.append(c);
                    break;
            }
        }
        return builder.toString();
    }
}
