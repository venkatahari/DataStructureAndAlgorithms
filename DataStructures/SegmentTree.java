import java.util.Arrays;

/***
 * Segment Tree Data Structure implementation
 * @author VenkataHari Shankar
 */
public class SegmentTree {

    static final long serialVersionUID = 1L;
    private int[] input;
    private int size;
    private int[] segmentArray;

    public SegmentTree(int[] arg) {
        input = Arrays.copyOf(arg, arg.length);
        size = (int) (Math.pow(2, Math.ceil(Math.log(input.length) / Math.log(2))) * 2) - 1;
        segmentArray = new int[size];
        Arrays.fill(segmentArray, Integer.MAX_VALUE);
        constructSegmentTree(0, 0, input.length - 1);
    }

    private void printSegmentTree() {
        for (int item : segmentArray) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    private void constructSegmentTree(int position, int lower, int higher) {
        if (lower == higher) {
            segmentArray[position] = input[lower];
            return;
        }
        int middle = (lower + higher) / 2;
        constructSegmentTree((2 * position) + 1, lower, middle);
        constructSegmentTree((2 * position) + 2, middle + 1, higher);
        segmentArray[position] = Math.min(segmentArray[(2 * position) + 1], segmentArray[(2 * position) + 2]);
    }

    private int queryMin(int qlower, int qhigher, int lower, int higher, int position) {
        //Total Overlap
        if (qlower <= lower && qhigher >= higher) {
            return segmentArray[position];
        }
        //No Overlap
        if (qlower > higher || qhigher < lower) {
            return Integer.MAX_VALUE;
        }
        //Partial Overlap
        int middle = (lower + higher) / 2;
        return Math.min(
                queryMin(qlower, qhigher, lower, middle, (2 * position) + 1),
                queryMin(qlower, qhigher, middle + 1, higher, (2 * position) + 2)
        );
    }

    public int queryMin(int queryLower, int queryHigher) {
        return queryMin(queryLower, queryHigher, 0, input.length - 1, 0);
    }

    public static void main(String[] args) {
        SegmentTree sg = new SegmentTree(new int[]{-1, 2, 4, 0, 8, 1, 3});
        sg.printSegmentTree();
        System.out.println("Min b/w {4, 0, 8} :" + sg.queryMin(2, 4));
        System.out.println("Min b/w {8, 1, 3} :" + sg.queryMin(4, 6));
        System.out.println("Min among all :" + sg.queryMin(0, 6));
    }
}
