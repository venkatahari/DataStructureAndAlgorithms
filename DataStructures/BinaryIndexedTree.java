import java.util.Arrays;

/***
 * Binary Indexed Tree or Fenwick Tree - Data Structure implementation
 * @author VenkataHari Shankar
 */
public class BinaryIndexedTree {

    static final long serialVersionUID = 1L;
    private int[] tree;

    public BinaryIndexedTree(int[] arg){
        int length = arg.length;
        tree = Arrays.copyOf(arg, length);
        for(int i=1;i<length;i++){
            int parentIndex = i + (i & -i);
            if(parentIndex < length)
                tree[parentIndex] +=tree[i];
        }
    }

    public int sum(int index){
        int sum = 0;
        while(index > 0){
            sum +=tree[index];
            index -= (index & -index);
        }
        return sum;
    }

    public void add(int index, int value){
        while(index < tree.length){
            tree[index] +=value;
            index += (index & -index);
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int item:tree){
            sb.append(item).append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] arg){
        int[] input = {0,3,2,-1,6,5,4,-3,3,7,2,3};
        BinaryIndexedTree bit = new BinaryIndexedTree(input);
        System.out.println(bit);
        System.out.println(bit.sum(7));
        bit.add(3,3);
        System.out.println(bit);
        System.out.println(bit.sum(7));
    }
}
