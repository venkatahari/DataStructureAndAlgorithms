import java.util.HashMap;
import java.util.Map;

/***
 * Trie Data structure implementation
 * @author VenkataHari Shankar
 */
public class Trie {

    Map<Character, Object> root = new HashMap<>();

    public void insert(char[] input){

        if(input == null || input.length == 0)
            throw new IllegalArgumentException();

        Map<Character, Object> current = root;
        for(char c:input){
            if (!current.containsKey(c)) {
                Map<Character, Object> temp = new HashMap<>();
                current.put(c, temp);
                current = temp;
            } else {
                Object temp = current.get(c);
                if(temp instanceof Map)
                    current = (Map<Character, Object>) temp;
            }
        }
        current.put('/',null);
    }

    public boolean search(char[] input){
        if(input == null || input.length == 0)
            throw new IllegalArgumentException();

        Map<Character, Object> current;
        current = root;
        for(char c:input){
            if (!current.containsKey(c)) {
                return false;
            } else {
                Object temp = current.get(c);
                if(temp instanceof Map)
                    current = (Map<Character, Object>) temp;
            }
        }
        return current.containsKey('/');
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Apple".toCharArray());
        trie.insert("Microsoft".toCharArray());
        trie.insert("Google".toCharArray());

        System.out.println(trie.search("Micro".toCharArray()));
        System.out.println(trie.search("Apple".toCharArray()));
        try {
            System.out.println(trie.search("".toCharArray()));
        }catch (Exception e){
            System.out.println("Exception Expected for illegal arg caught");
        }
    }
}
