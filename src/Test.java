import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.*;

public class Test {

    /*
    Creates a regex for all the letters in the
     */
    public static char[] puzzleGridRegex1(List<String> puzzle_grid) {
        char[] array = new char[(puzzle_grid.size() - 1) * 4];
        for (int i = 0; i < puzzle_grid.size(); i++) {
            char[] ch = puzzle_grid.get(i).toCharArray();
            for (int j = 0; j < ch.length; j++) {
                array[j + i * (puzzle_grid.size() - 1)] = ch[j];
            }
        }
        return array;
    }

    /*
    Filters the words.txt file from given letters in puzzle grid
    HashMap key is word's letter sorted alphabetically, value is list of words that have that alphabetical order
     */
    public static ArrayList<String> initWordList(List<String> puzzle_grid) {
        ArrayList<String> word_map = new ArrayList<>();
        char[] array = puzzleGridRegex1(puzzle_grid);
        String regex = new String(array);
        Pattern p = Pattern.compile("[^" + regex + " ]", Pattern.CASE_INSENSITIVE);
        try {
            File file = new File("src/test.txt"); //TODO: change the file name
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                char[] ch = s.toCharArray();
                Matcher m = p.matcher(s);
                boolean b = m.find();
                Arrays.sort(ch);
                if (ch.length >= 3 && !b) {
                    word_map.add(s.toLowerCase());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
            e.printStackTrace();
        }
        return word_map;
    }

    public static String puzzleGridRegex2(String s, int i, int j) {
        char[] ch = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(ch[i]);
        sb.append(ch[j]);
        String regex = sb.toString().toLowerCase();
        return regex;
    }


    public static ArrayList<String> filterWordList(List<String> puzzle_grid,
                                                   ArrayList<String> word_map) {
        for (int n = 0; n < 4; n++) {
            for (int i = 0; i < puzzle_grid.size() - 1; i++) {
                for (int j = 0; j < puzzle_grid.size() - 1; j++) {
                    String regex = puzzleGridRegex2(puzzle_grid.get(n), i, j);
                    for (int k = word_map.size() - 1; k >= 0; k--) {
                        System.out.print(word_map.get(k));
                        boolean boo = word_map.get(k).contains(regex);
                        System.out.print(boo);
                        if (word_map.get(k).contains(regex)) {
                            word_map.remove(k);
                        }
                    }
                }
            }
        }
        return word_map;
    }
}
