public class Main {

    public static void main(String[] args) {
        String StringOne = "- 5 x^3 + 4  x^2 - 3 x + 1x^0";
        String StringTwo = "+2 x ^ 2 -x+ 8";

        Polynomial polynomialOne = new Polynomial(StringOne);
        Polynomial polynomialTwo  = new Polynomial(StringTwo);

        System.out.println("Polynomial #1");
        polynomialOne.print();
        System.out.println("Polynomial #2");
        polynomialTwo.print();

        System.out.println("\nValue of P#1 for x = -3.2");
        polynomialOne.value(-3.2);
        System.out.println("Value of P#2 for x = 0");
        polynomialTwo.value(0);

        System.out.println("\nAddition of P#1 and P#2");
        polynomialOne.add(polynomialTwo).print();
        System.out.println("\nAddition of P#1 and P#1");
        polynomialOne.add(polynomialOne).print();
        System.out.println(" == P#1 * 2");
        polynomialOne.multiply(new Polynomial("2")).print();

        System.out.println("\nSubtract of P#1 from P#2");
        polynomialTwo.subtract(polynomialOne).print();
        System.out.println("Subtract of P#2 from p#1");
        polynomialOne.subtract(polynomialTwo).print();
        System.out.println("\nSubtract of P#1 from P#1");
        polynomialOne.subtract(polynomialOne).print();
        System.out.println("== P#1 * 0");
        polynomialOne.multiply(new Polynomial("0")).print();

        System.out.println("\nMultiplication of P#1 by P#2");
        polynomialOne.multiply(polynomialTwo).print();

        System.out.println("\nDivision of P#1 by P#2");
        polynomialOne.divide(polynomialTwo).print();
        System.out.println("\nDivision of P#2 by -5.4");
        polynomialTwo.divide(new Polynomial("5.4")).print();

    }
}
