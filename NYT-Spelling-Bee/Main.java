import java.util.*;
import java.io.*;
class Main {
  public static void main(String[] args) throws IOException{
    Scanner sc = new Scanner(new File("dictionary.txt"));
    List<String> dictionaryy = new ArrayList<String>();
    while(sc.hasNext())
    {
      dictionaryy.add(sc.next());
    }
    SpellingBee s = new SpellingBee(dictionaryy);
    s.intro();
  }
}