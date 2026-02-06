public class Account {
    private char currency;
    private double amount;

    public Account(char currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public double toCommon() {
        return amount * Bank.getRate(currency);
    }

    public char getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}
