import java.util.Random;
import java.util.Stack;

public class StackQuestion {
  public static void main(String[] args) {
    Stack<Integer> stk = new Stack<>();
    Random r = new Random();

    // generate 10 random number between 0 and 100
    for (int i = 0; i < 10; i++) {
      int x = r.nextInt(100);
      stk.push(x);
    }

    System.out.println("Before sort: " + stk);
    Stack<Integer> sortedStack = sortStack(stk); // sortStack
    System.out.println("After sort: " + sortedStack);
  }

  public static Stack<Integer> sortStack(Stack<Integer> input) {
    Stack<Integer> tmpStack = new Stack<Integer>();
    while (!input.isEmpty()) {

      // pop the top the number into temporary stack
      // compare the top number of current input to the teporary number in temp Stack
      int tmpNumber = input.pop();
      while (!tmpStack.isEmpty() && tmpStack.peek() < tmpNumber) {
        input.push(tmpStack.pop());
      }
      tmpStack.push(tmpNumber);
    }
    // return the all the input number back to input Stack
    while (!tmpStack.empty()) {
      int tmpNum = tmpStack.pop();
      input.push(tmpNum);
    }
    return input;
  }
}
