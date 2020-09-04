import java.util.*;

/***
 * Trie Data structure implementation
 * with Get All String with Given input prefix
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

    public void insert(String input){
        if(input == null || input.length() == 0)
            throw new IllegalArgumentException();
        insert(input.toCharArray());
    }

    public boolean search(String input){
        if(input == null || input.length() == 0)
            throw new IllegalArgumentException();
        return search(input.toCharArray());
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

    public List<String> getAllStringWithPrefix(String prefix){

        if(prefix == null || prefix.length() == 0)
            throw new IllegalArgumentException();
        List<String> result = new ArrayList<>();
        List<Character> tillNow = new ArrayList<>();
        Map<Character, Object> current;
        current = root;
        for(char c:prefix.toCharArray()){
            if (!current.containsKey(c)) {
                return result;
            } else {
                Object temp = current.get(c);
                tillNow.add(c);
                if(temp instanceof Map)
                    current = (Map<Character, Object>) temp;
            }
        }
        recursiveCollect(current,tillNow,result);
        return result;
    }

    private void recursiveCollect(Map<Character, Object> current, List<Character> tillNowStr, List<String> result) {
        for(Map.Entry<Character, Object> entry:current.entrySet()){
            if(entry.getKey()=='/'){
                result.add(tillNowStr.toString());
            }else{
                tillNowStr.add(entry.getKey());
                recursiveCollect((Map<Character, Object>) entry.getValue(),tillNowStr, result);
                tillNowStr.remove(tillNowStr.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Apple");
        trie.insert("Microsoft");
        trie.insert("Google");
        trie.insert("GoLang");
        trie.insert("Go-on");


        System.out.println(trie.search("Micro".toCharArray()));
        System.out.println(trie.search("Apple".toCharArray()));
        try {
            System.out.println(trie.search("".toCharArray()));
        }catch (Exception e){
            System.out.println("Exception Expected for illegal arg caught");
        }

        System.out.println(trie.getAllStringWithPrefix("Go"));
    }
}
