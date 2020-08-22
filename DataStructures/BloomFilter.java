import java.util.BitSet;

/***
 * Probabilistic data structures - Bloomfilter
 * 1. Less Memory 2. Paralleliazble 3. Constant Query Time
 * False Positive Possible, But False Negative not possible
 * @author VenkataHari Shankar
 */
public class BloomFilter {

    private int sizeOfBitSet;
    private int numOfHashFn;
    private BitSet bitSet;

    public BloomFilter(int sizeOfBitSet, int numOfHashFn){
        this.sizeOfBitSet = sizeOfBitSet;
        this.numOfHashFn = numOfHashFn;
        bitSet = new BitSet(sizeOfBitSet);
    }

    public void add(String key){
        for(int fnNo=0;fnNo<numOfHashFn;fnNo++){
            int index = (int)hashCode(fnNo, key)%sizeOfBitSet;
            bitSet.set(index);
        }
    }

    public boolean check(String key){
        for(int fnNo=0;fnNo<numOfHashFn;fnNo++){
            int index = (int)hashCode(fnNo, key)%sizeOfBitSet;
            if(!bitSet.get(index))
                return false;
        }
        return true;
    }

    private static int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    public long hashCode(int functionNumber, String input){
        long hash = primeNumbers[functionNumber];
        for (int i = 0; i < input.length(); i++) {
            hash = hash*31 + input.charAt(i);
        }
        return hash;
    }

}

class BloomFilterTestMain{
    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(1000,3);
        bloomFilter.add("Apple");
        bloomFilter.add("Ball");
        bloomFilter.add("Cat");
        bloomFilter.add("Dog");
        System.out.println("Call Exist "+bloomFilter.check("Call"));
        System.out.println("Dot Exist  "+bloomFilter.check("Dot"));
        System.out.println("Ball Exist "+bloomFilter.check("Ball"));
        System.out.println("Cat Exist  "+bloomFilter.check("Cat"));
    }
}
