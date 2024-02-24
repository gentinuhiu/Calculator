import java.util.*;

class Equation{
	public static double calculate(String line){
		List<Double> numbers = new ArrayList<>();
		List<Character> signs = new ArrayList<>();
		int index = 0;

		for(int i = 0; i < line.length(); i++){
			char c = line.charAt(i);
			if(!Character.isDigit(c)){
				if(((i == 0) && (c == '+' || c == '-')) || (c == '.'))
					continue;

				if(Character.isDigit(line.charAt(i - 1))){
					if(index > 0 && line.charAt(index - 1) == '-') {
						index++;
					}

					numbers.add(Double.parseDouble(line.substring(index, i)));
					signs.add(c);
				}

				index = i;
				if(c == '*' || c == '/')
					index++;
			}
		}
		if(index > 0 && line.charAt(index - 1) == '-')
			index++;

		numbers.add(Double.parseDouble(line.substring(index)));
		mul_div(numbers, signs);
		double result = numbers.stream().mapToDouble(Double::doubleValue).sum();

		return result;
	}
	private static void mul_div(List<Double> numbers, List<Character> signs){
		int i = 0;
		while(i < signs.size()){
			Character c = signs.get(i);
			if(c == '*' || c == '/'){
				Double x = numbers.get(i);
				Double y = numbers.get(i + 1);
				Operation operation = OperationSelector.createOperation(c);
				Double result = operation.apply(x, y);

				numbers.set(i + 1, result);
				numbers.remove(i);
				signs.remove(i);
			}
			else{
				i++;
			}
		}
	}
}