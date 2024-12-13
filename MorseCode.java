/*
This Java program, MorseCode, is a simple utility for translating between English text and Morse code. It provides the following functionalities:

Encoding Text to Morse Code
Converts a user-provided phrase (text) into its equivalent Morse code representation.

Decoding Morse Code to Text
Converts a user-provided Morse code sequence into its equivalent English text.

Example output below

Hello, this program allows you to translate text to morse code or translate morse code to text.
Please, select one of the below options:
*** Enter 't' for encoding text
*** Enter 'm' for decoding morse code
*** Enter 'e' to exit the program.
t
Please enter a phrase:
Hello World
MorseCode:
.... . .-.. .-.. ---     .-- --- .-. .-.. -..
Please, select one of the below options:
*** Enter 't' for encoding text
*** Enter 'm' for decoding morse code
*** Enter 'e' to exit the program.
m
Please enter a Morse code:
.... . .-.. .-.. ---    .-- --- .-. .-.. -..
Normal Text:
HELLO WORLD


*/



import java.util.Scanner;

public class MorseCode {
    private static final char[] letters = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '
    };
    private static final String[] morseCodes = {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
        ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "   " // Space represented as 3 spaces. So input 3 spaces to output space, but add 1 more before writing another morse code. So 4 total.
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option;

        System.out.println("Hello, this program allows you to translate text to morse code or translate morse code to text.");
        do {
            displayMenu();
            option = scanner.nextLine().toLowerCase();

            switch (option) {
                case "t":
                    System.out.println("Please enter a phrase:");
                    String phrase = scanner.nextLine().toUpperCase();
                    System.out.println("MorseCode:");
                    System.out.println(encodeToMorse(phrase));
                    break;
                case "m":
                    System.out.println("Please enter a Morse code:");
                    String morseCode = scanner.nextLine().trim();
                    System.out.println("Normal Text:");
                    System.out.println(decodeFromMorse(morseCode));
                    break;
                case "e":
                    System.out.println("Thanks for using this program!");
                    break;
                default:
                    System.out.println("***invalid option***\nPlease enter a valid option:");
            }
        } while (!option.equals("e"));

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Please, select one of the below options:");
        System.out.println("*** Enter 't' for encoding text");
        System.out.println("*** Enter 'm' for decoding morse code");
        System.out.println("*** Enter 'e' to exit the program.");
    }

    private static String encodeToMorse(String text) {
        StringBuilder morse = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = findLetterIndex(c);
            if (index != -1) {
                morse.append(morseCodes[index]).append(" ");
            }
        }
        return morse.toString().trim();
    }

    private static String decodeFromMorse(String morseCode) {
        StringBuilder text = new StringBuilder();
        String[] words = morseCode.split("   "); // Split by 3 spaces for words
        for (String word : words) {
            for (String letter : word.split(" ")) {
                int index = findMorseIndex(letter);
                if (index != -1) {
                    text.append(letters[index]);
                }
            }
            text.append(" ");
        }
        return text.toString().trim();
    }

    private static int findLetterIndex(char c) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private static int findMorseIndex(String morse) {
        for (int i = 0; i < morseCodes.length; i++) {
            if (morseCodes[i].equals(morse)) {
                return i;
            }
        }
        return -1;
    }
}
