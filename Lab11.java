import java.util.Iterator;

public class Lab11 {
    public static void main(String[] args) {
        LList<Integer> myList = new LList<Integer>();
        myList.add(300);
        myList.add(500);
        myList.add(700);
        myList.add(900);
        Iterator<Integer> it = myList.iterator();
        System.out.println("hasNext() should be true: " + it.hasNext());
        System.out.println("next() should be 300: " + it.next());
        it.remove(); // removes 300
        System.out.println("next() should be 500: " + it.next());
        System.out.println("next() should be 700: " + it.next());
        System.out.println("next() should be 900: " + it.next());
        it.remove(); // removes 900
        System.out.println("getLength() should return 2: " + myList.getLength());      
        // list should be 500, 700
        System.out.println(myList.getEntry(0));
        System.out.println(myList.getEntry(1));
        System.out.println("hasNext() should be false: " + it.hasNext());
    }
}