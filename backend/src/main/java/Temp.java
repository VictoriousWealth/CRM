import java.lang.reflect.Array;
import java.util.Arrays;

public class Temp {

    public static int pointer = 0;

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(batonPass(4, 5)));
        System.out.println(Arrays.toString(batonPass(5, 3)));
        System.out.println(Arrays.toString(batonPass(3, 6)));
    }

    private static int[] batonPass(int numberOfFriends, int time) {
        int t = 0;
        int[] lastBatonPass = new int[2];
        while (t != time) {
            t++;
            move(numberOfFriends);
            int friendWithBaton = converted(pointer, numberOfFriends) + 1;
            System.out.printf("Current time is %s and the baton is with friend %s.\n", t, friendWithBaton);
            lastBatonPass[0] = lastBatonPass[1];
            lastBatonPass[1] = friendWithBaton;
        }
        pointer = 0;
        return lastBatonPass;
    }

    private static int converted(int pointer, int length) {
        // -1 -> length-1-1;
        // -2 -> length-1-2;
        // and so on
        if (pointer<0) return length-1+pointer;
        else return pointer;
    }

    private static void move(int length) {
        if (pointer == length-1) pointer = -1;
        else if (pointer == -(length-2)) pointer = 0;
        else if (pointer >= 0) pointer ++;
        else pointer --;
    }
}
