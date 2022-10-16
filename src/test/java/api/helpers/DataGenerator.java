package api.helpers;

import java.util.Random;

public class DataGenerator {

    /**
     * Метод генерации случайной строки разного регистра с английскими буквами
     * @param length - длина строки
     * @return строка
     */
    public static String generateEnglishLetters(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i = 0; i < length; i++) {
            char letter = alphabet[random.nextInt(alphabet.length)];
            stringBuffer = stringBuffer.append(letter);
        }
        return stringBuffer.toString();
    }
}
