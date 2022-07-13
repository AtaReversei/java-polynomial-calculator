public class Term {
    // ---- fields ------------------------------------------------------------------
    private double coefficient;
    private int power;
    String strTerm;
    // ---- constructor -------------------------------------------------------------
    public Term(String term) {
        setCoefficient(term);
        setPower(term);
        setString(term);
    }
    public Term (double coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }
    // ---- set methods -------------------------------------------------------------
    private void setCoefficient(String term) {
        if (term.contains("x")) {
            String[] tempArr = term.split("x");
            String tempArrItem = tempArr[0];
            if (tempArrItem.equals("")) {
                coefficient = 1;
            } else if (tempArrItem.equals("+")) {
                coefficient = 1;
            } else if (tempArrItem.equals("-")) {
                coefficient = -1;
            } else {
                tempArrItem = tempArrItem.contains("+") ? tempArrItem.replace("+", "") : tempArrItem;
                coefficient = Double.parseDouble(tempArrItem);
            }
        } else {
                coefficient = Double.parseDouble(term);
        }
    }
    private void setPower(String term) {
        if (term.contains("x^")) {
            String[] tempArr = term.split("x\\^");
            String tempArrItem = tempArr[1];
            if (tempArrItem.equals("")) {
                power = 1;
            } else {
                power = Integer.parseInt(tempArrItem);
            }
        } else if (term.contains("x")) {
            power = 1;
        } else {
            power = 0;
        }
    }
    private void setString(String term) {
        String strCoefficient = null;
        if (coefficient > 0) {
            strCoefficient = "+" + String.format("%.1f", coefficient );
        } else {
            strCoefficient = String.format("%.1f",coefficient);
        }

        String strPower = null;
        if (power > 0) {
            strPower = "x^" + Integer.toString(power);
        } else if (power== 0) {
            strPower = "";
        }

        this.strTerm = " " + strCoefficient + strPower + " ";

    }
    // ---- get methods -------------------------------------------------------------
    public double getCoefficient() {
        return coefficient;
    }
    public int getPower() {
        return power;
    }
    public double getValue(double x) {
        return getPowerValue(x) * coefficient;
    }
    public double getPowerValue(double x) {
        double poweredX = x;
        if (power == 0) {
            return 1;
        } else {
            for (int i = 1; i < power; i++) {
                poweredX *= x;
            }
            return poweredX;
        }
    }
}
