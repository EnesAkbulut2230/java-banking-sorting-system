import java.util.Random;

public class Bank {
    private static final double[] rate = new double[4];

    static {
        Random random = new Random();
        for (int i = 0; i < rate.length; i++) {
            rate[i] = 0.2 + random.nextDouble() * (5.0 - 0.2);
        }
    }

    public static double getRate(char currency) {
    switch (currency) {
        case 'A': return 1.0;
        case 'B': return 2.0;
        case 'C': return 3.0;
        case 'D': return 4.0;
        default:
            throw new IllegalArgumentException(
                "Invalid currency type: " + currency
            );
    }
}


    public static void setRate(char currency, double newRate) {
    if (currency < 'A' || currency > 'D') {
        throw new IllegalArgumentException("Invalid currency type: " + currency);
    }
    if (newRate <= 0) {
        throw new IllegalArgumentException("Rate must be positive: " + newRate);
    }
    rate[currency - 'A'] = newRate;
}


    public static void printRates() {
        System.out.printf(
            "A: %.7f%nB: %.7f%nC: %.7f%nD: %.7f%n",
            rate[0], rate[1], rate[2], rate[3]
        );
    }
}
