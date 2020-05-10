import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashTable_HashMap {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("number of magazine words: ");
    int m = in.nextInt();
    System.out.println("number of note words: ");
    int n = in.nextInt();

    String magazine[] = new String[m];
    System.out.println("input magazine words: ");
    for (int i = 0; i < m; i++) {
      magazine[i] = in.next();
    }

    String note[] = new String[n];
    System.out.println("input ransom note words: ");
    for (int i = 0; i < n; i++) {
      note[i] = in.next();
    }

    if (checkMagazine(magazine, note)) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
    }
    in.close();
  }

  public static boolean checkMagazine(String[] magazine, String[] ransom) {
    Map<String, Integer> magMap = new HashMap<String, Integer>();
    Map<String, Integer> ransomMap = new HashMap<String, Integer>();

    // count number of words occurence in magazine array
    // then store them in magMap(HashMap).
    for (String word : magazine) {
      if (!magMap.containsKey(word)) {
        magMap.put(word, 0);
      } else {
        magMap.put(word, magMap.get(word) + 1);
      }
    }

    // count number of words occurence in ransom array
    // then store them in ransomMap(HashMap).
    for (String word : ransom) {
      if (!ransomMap.containsKey(word)) {
        ransomMap.put(word, 0);
      } else {
        ransomMap.put(word, ransomMap.get(word) + 1);
      }
    }

    // if magMap not contain all the key required in ransomMap -> return false
    // if magMap not contain enough key required in needed for ransomMap -> return false
    // if it meet all requirement -> return true.
    for (String key : ransomMap.keySet()) {
      if (!magMap.containsKey(key)) {
        return false;
      }
      int needed = ransomMap.get(key);
      if (magMap.get(key) < needed) {
        return false;
      }
    }
    return true;
  }
}
