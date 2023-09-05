import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterBoxed {

    public static boolean containsAllLetters(String sb, String puzzle_grid) {
        char[] ch = puzzle_grid.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (sb.indexOf(ch[i]) == -1) {
                return false;
            }
        }
        return true;
    }

    public static void getWordCombo(ArrayList<String> word_map, String puzzle_grid) {
        for (int i = 0; i < word_map.size(); i++) {
            String s1 = word_map.get(i);
            char[] ch_array1 = s1.toCharArray();
            char ch1 = ch_array1[ch_array1.length - 1];
            for (int j = 0; j < word_map.size(); j++) {
                String s2 = word_map.get(j);
                if (!s1.equals(s2)) {
                    char[] ch_array2 = s2.toCharArray();
                    char ch2 = ch_array2[0];
                    StringBuilder sb = new StringBuilder();
                    sb.append(s1);
                    sb.append(s2);
                    String s = sb.toString();
                    if (ch1 == ch2 && containsAllLetters(s, puzzle_grid)) {
                        System.out.println(s1 + " " + s2);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Please input the puzzle grid:");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        List<String> puzzle_grid = Arrays.asList(s.split(","));
        ArrayList<String> word_map = WordList.initWordList(puzzle_grid);
        ArrayList<String> filtered_word_map = WordList.filterWordList(puzzle_grid, word_map);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(puzzle_grid.get(i));
        }
        getWordCombo(filtered_word_map, sb.toString().toLowerCase());
    }
}
