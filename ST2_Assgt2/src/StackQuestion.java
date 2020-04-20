import java.util.Random;
import java.util.Stack;

public class StackQuestion {
  public static void main(String[] args) {
    Stack stk = new Stack<>();
    Random r = new Random();

    for (int i = 0; i < 5; i++) {
      int x = r.nextInt(100);
      stk.push(x);
      System.out.println("loop #" + i + ": " + stk.peek());
    }
    System.out.println("Before sort: " + stk);
    sortStack(stk);
    System.out.println("After sort: " + stk);
  }

  // TODO Complete the sortStack method to sort the input stack
  public static Stack<Integer> sortStack(Stack<Integer> input) {

    Stack tmp = new Stack<>();
    if (tmp.empty()) {
      tmp.push(input.pop());
    }


    return input;
  }
}
