import java.util.Scanner;

public class Calc {
    static int operand1;
    static int operand2;
    static int calcResult;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("Введите два арабских или римских числа от одного до десяти, которые разделены аргументом операции и пробелами между собой");
        System.out.println("Пример: I + III или 8 / 2");

        userInput = scanner.nextLine();
        String [] userInputSplitted;
        userInputSplitted = userInput.split(" ");
        if (userInputSplitted.length>3){
            throw new IllegalArgumentException ("Формат математической операции не удовлетворяет требованию - два аргумента операции и один оператор (+, -, /, *)");
        }


        if (ifOperandRoman(userInputSplitted[0])){
            try{
                RomanNumerals roman1 = RomanNumerals.valueOf(userInputSplitted[0]);
                RomanNumerals roman2 = RomanNumerals.valueOf(userInputSplitted[2]);
                operand1 = roman1.getTranslation();
                operand2 = roman2.getTranslation();
                calcResult = calculate(operand1,operand2,userInputSplitted[1]);
                String calcResultRoman = convertNumToRoman(calcResult);
                System.out.println("Результат: "+ calcResultRoman);

            } catch (IllegalArgumentException e){
                System.out.println("Используются разные системы счисления. Ошибка: "+e);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Строка не является математической операцией "+ e);
            }

        }
        else if(ifOperandArabian(userInputSplitted[0])){


            try {
                operand1 = Integer.parseInt(userInputSplitted[0]);
                operand2 = Integer.parseInt(userInputSplitted[2]);
                calcResult = calculate(operand1, operand2, userInputSplitted[1]);
                System.out.println("Результат: "+ calcResult);

            }catch (IllegalArgumentException e){
                System.out.println("Используются разные системы счисления. Ошибка: "+e);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Строка не является математической операцией "+ e);
            }

        }
        else {
            System.out.println("Вы ввели некорректный аргумент операции");
            System.out.println("Требуется ввести выражение в формате: 1 + 2 или II + IV");
        }



    }

    public static int calculate(int op1, int op2, String op){
        int result = 0;
        switch (op){
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            case "/":
                try {
                    result = op1/op2;
                } catch (ArithmeticException e){
                    System.out.println("Exception: "+ e);
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException ("Введен некорректный аргумент операции");
        }
        return result;

    }

    static boolean ifOperandRoman(String operand1){
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        boolean check = false;
        for(String s: roman){
            if(s.equals(operand1)){
                check = true;
                break;
            }

        }
        return check;
    }
    static boolean ifOperandArabian(String operand1){
        String[] roman = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        boolean check = false;
        for(String s: roman){
            if(s.equals(operand1)){
                check = true;
                break;
            }

        }
        return check;
    }

     static String convertNumToRoman (int num) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String n;
        try{
            n = roman[num];
            return n;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Ошибка "+ e);
        }
        return n=null;
    }

}
