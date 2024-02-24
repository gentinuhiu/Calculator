import java.io.*;

class Calculator{
	public static double calculate(String line){
		int index = 0;

		for(int i = 0; i < line.length(); i++){
			char c = line.charAt(i);
			if(c == '('){
				index = i;
			}
			else if(c == ')'){
				String equationLine = line.substring(index, i + 1);
				Double result = Equation.calculate(equationLine.substring(1, equationLine.length() - 1));
				line = line.replace(equationLine, String.format("%.3f", result));

				i = 0;
				index = 0;
			}
		}
		return Equation.calculate(line);
	}
}

public class CalculatorTest{
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(Calculator.calculate(bufferedReader.readLine()));
	}
}