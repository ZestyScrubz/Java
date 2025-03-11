import java.util.Scanner;
import javax.xml.transform.Templates;

public class Postfix {

	private ArrayStack<String> expStack;
	private PostfixEvaluator evaluator;
	
	public Postfix () {
		expStack =  new ArrayStack<>();
		evaluator = new PostfixEvaluator();
		
	}

	public void run () {
		String expression, action = "e";
		int result;

		try {
			Scanner in = new Scanner(System.in);
      
			do {
				if (action.equals("e")) {
					System.out.print("Enter a valid postfix expression:  ");
					expression = in.nextLine();

					result = evaluator.evaluate(expression.trim());
					System.out.println("The result is " + result);
	
					expStack.push(expression.trim());
					
				
				} else if (action.equals("r")) {

                    // Show recent expressions
                    int size = expStack.size();
                    if (size >= 3) {
                        showRecent(3); // Show the last 3 expressions
                    } else {
                        showRecent(size); // Show the available expressions
                    }

				}

				System.out.println("\nWhat do you want to do?");
				System.out.println("\tType 'e' if you want to evaluate another postfix expression.");
				System.out.println("\tType 'r' if you want to see the recent expressions.");
				System.out.println("\tType any other key to quit.");
				action = in.nextLine();
				System.out.println();
			} while (action.equalsIgnoreCase("e") || action.equalsIgnoreCase("r"));
    	} catch (Exception IOException) {
    		System.out.println("Input exception reported");
    	}

	}
	
	private void showRecent (int numToShow) {

		ArrayStack<String> tmp = new ArrayStack<String>();

		System.out.println("Recent Expressions:");
		
		for (int i = 0; i < numToShow; i++) {
			String expr = expStack.pop();
			int result = evaluator.evaluate(expr);
			System.out.println("\t" + expr + " = " + result);
			tmp.push(expr);
		}
		
		while (!tmp.isEmpty()) {
			expStack.push(tmp.pop());
		}

	}
	
	
	public static void main (String[] args) {
		Postfix pf = new Postfix();
		pf.run();
	}
}
