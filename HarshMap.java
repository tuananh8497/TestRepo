package abc;
import java.util.*;

public class HarshMap {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("number of magazine words: ");
        int m = in.nextInt();
        System.out.println("number of note words: ");
        int n = in.nextInt();
        String magazine[] = new String[m];
        System.out.println("input magazine words: ");
        for(int i=0; i < m; i++){
            magazine[i] = in.next();
        }
        String note[] = new String[n];
        System.out.println("input ransom note words: ");
        for(int i=0; i < n; i++){
            note[i] = in.next();
        }
        
        if (checkMagazine(magazine,note)) {
          System.out.println("Yes");
        } else {
          System.out.println("No");
        }
        in.close();
    }

    public static boolean checkMagazine(String[] magazine, String[] ransom){
      Map<String, Integer> magMap = new HashMap<String, Integer>();
      Map<String, Integer> ransomMap = new HashMap<String, Integer>();

      for(String word: magazine){
          if (!magMap.containsKey(word)){
            magMap.put(word, 0);
           } else {
          magMap.put(word, magMap.get(word) + 1);
           }
      }

      for(String word: ransom){
          if (!ransomMap.containsKey(word)) {
            ransomMap.put(word, 0);
          } else {
          ransomMap.put(word, ransomMap.get(word) + 1);
          }
      }

      for(String key: ransomMap.keySet()){
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