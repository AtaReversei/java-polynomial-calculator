import java.util.ArrayList;

// 5x^2 + x + 3
public class Polynomial {
    // ---- fields ------------------------------------------------------------------
    private ArrayList<Term> terms = new ArrayList<Term>();
    // ---- constructor -------------------------------------------------------------
    public Polynomial(String expansion) {
        String spaceFormatted = deleteSpace(expansion);
        populateTerms(spaceFormatted);
        terms = cleanAnswer(terms);
        terms = zeroCheck(terms);
        sortArray();
    }
    private Polynomial() {}
    // ---- public methods ----------------------------------------------------------
    public void print() {
        String strPolynomial = "";

        if (this.terms == null) {
            System.out.println("There Is No Value To Print. Terms ArrayList Is Null.");
            return;
        }

        if (this.terms.size() == 1 && this.terms.get(0).getCoefficient() == 0) {
            System.out.println("0.0");
            return;
        }

        for (Term term : terms) {
            strPolynomial = strPolynomial + term.strTerm;
        }
        if (strPolynomial.substring(1, 2).equals("+")) {
            System.out.println(strPolynomial.substring(2));
        } else {
            System.out.println(strPolynomial.substring(1));
        }
    }
    public double value(double x) {
        double value = 0;
        for(Term term : terms){
            value += term.getValue(x);
        }
        System.out.println(value);
        return value;
    }
    public Polynomial add(Polynomial polynomialTwo){
        Polynomial resPolynomial = new Polynomial();
        ArrayList<Term> tempArr = new ArrayList<Term>();
        tempArr.addAll(terms);
        tempArr.addAll(polynomialTwo.terms);
        tempArr = cleanAnswer(tempArr);
        resPolynomial.terms.addAll(tempArr);
        resPolynomial.terms = zeroCheck(resPolynomial.terms);
        resPolynomial.sortArray();
        return resPolynomial;
    }
    public Polynomial subtract(Polynomial polynomialTwo) {
        Polynomial minusOne = new Polynomial("-1");
        Polynomial changedSign = polynomialTwo.multiply(minusOne);
        return this.add(changedSign);

    }
    public Polynomial multiply(Polynomial polynomialTwo){
        Polynomial resPolynomial = new Polynomial();
        ArrayList<Term> tempArr = new ArrayList<Term>();
        for(Term term : terms) {
            for (Term termTwo : polynomialTwo.terms) {
                double coefficient = term.getCoefficient() * termTwo.getCoefficient();
                int power = term.getPower() + termTwo.getPower();
                String strTerm = Double.toString(coefficient) + "x^" + Integer.toString(power);
                Term tempTerm = new Term(strTerm);
                tempArr.add(tempTerm);
            }
        }
        tempArr = cleanAnswer(tempArr);
        tempArr = zeroCheck(tempArr);
        resPolynomial.terms.addAll(tempArr);
        resPolynomial.sortArray();
        return resPolynomial;
    }
    public Polynomial divide(Polynomial polynomialTwo){
        if (polynomialTwo.terms.get(0).getCoefficient() == 0) {
            System.out.println("Can Not Divide By Zero");
            Polynomial resPolynomial = new Polynomial();
            resPolynomial.terms = null;
            return resPolynomial;
        }

        Polynomial dividend = new Polynomial();
        dividend.terms = new ArrayList<Term>(terms);

        Polynomial divisor = new Polynomial();
        divisor.terms = new ArrayList<Term>(polynomialTwo.terms);

        ArrayList<Term> quotient = new ArrayList<Term>();


        int divisorPower = divisor.terms.get(0).getPower();
        double divisorCoefficient = divisor.terms.get(0).getCoefficient();

        for(int i = (dividend.terms.get(0).getPower() - divisorPower); i >= 0; i--) {
            int multiplyPower = dividend.terms.get(0).getPower() - divisorPower;
            double multiplyCoefficient = dividend.terms.get(0).getCoefficient() / divisorCoefficient;

            String tempStr = Double.toString(multiplyCoefficient) + "x^" + Integer.toString(multiplyPower);
            Term tempTerm = new Term(tempStr);

            // Term tempTerm = new Term(multiplyCoefficient, multiplyPower);
            quotient.add(tempTerm);
            Polynomial tempClass = new Polynomial(tempStr);
            dividend = dividend.subtract(divisor.multiply(tempClass));

            for (int j = 0; j < dividend.terms.size(); j++) {
                if (Math.abs(dividend.terms.get(j).getCoefficient()) < 0.1) {
                    dividend.terms.remove(j);
                }
            }

            // (divisor.multiply(tempClass)).print();
            // dividend.print();

        }
        Polynomial resPolynomial = new Polynomial();
        resPolynomial.terms.addAll(quotient);
        return resPolynomial;
    }
    // ---- string methods ----------------------------------------------------------
    private String deleteSpace(String expansion) {
        return expansion.replace(" ", "");
    }
    private void sortArray() {
        if(terms.size() > 1) {
            return;
        }
        int i = 0;
        while (i < terms.size() - 1) {
            int j = i + 1;
            while (j < terms.size()) {
                if (terms.get(i).getPower() < terms.get(j).getPower()) {
                    Term temp = terms.get(i);
                    terms.set(i, terms.get(j));
                    terms.set(j, temp);
                }
                j++;
            }
            i++;
        }
    }
    // ---- term array methods ------------------------------------------------------
    private void populateTerms(String expansion) {
        String[] tempTerms = expansion.split("((?=\\u002B|-))");
        for (String term : tempTerms) {
            Term tempTerm = new Term(term);
            terms.add(tempTerm);
        }
    }
    private ArrayList<Term> cleanAnswer(ArrayList<Term> terms) {
        ArrayList<Term> tempArr = new ArrayList<Term>();
        while(terms.size() != 0) {
            double coefficient = terms.get(0).getCoefficient();
            int power = terms.get(0).getPower();
            for(int i = terms.size() - 1; i > 0; i--) {
                if(terms.get(i).getPower() == power) {
                    coefficient += terms.get(i).getCoefficient();
                    terms.remove(i);
                }
            }
            if (coefficient != 0) {
                String strTerm = Double.toString(coefficient) + "x^" + Integer.toString(power);
                Term tempTerm = new Term(strTerm);
                tempArr.add(tempTerm);
            }
            terms.remove(0);
        }
        return tempArr;
    }
    private ArrayList<Term> zeroCheck(ArrayList<Term> resTerms) {
        if (resTerms.size() == 0) {
            Term zero = new Term(0, 0);
            resTerms.add(zero);
        }
        return resTerms;


    }
}