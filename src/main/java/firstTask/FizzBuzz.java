package firstTask;

import java.util.stream.IntStream;

public class FizzBuzz {
    private int[] array = new int[100];

    public static void main(String[] args) {
        FizzBuzz fb = new FizzBuzz();
        fb.printFizzBuzzOrNum();
    }

    public FizzBuzz() {
        array = IntStream.rangeClosed(1, 100).toArray();
    }

    public void printFizzBuzzOrNum() {
        for (int num : array) {
            if (num % 3 == 0 && num % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (num % 3 == 0) {
                System.out.println("Fizz");
            } else if (num % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(num);
            }
        }
    }
}
