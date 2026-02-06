import java.util.ArrayList;

public class User {
    private String id;
    private String name;
    private String surname;
    private ArrayList<Account> accounts;

    public User(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account acct) {
        accounts.add(acct);
    }

    public double getTotalAmountInCommonCurrency() {
        double sum = 0;
        for (Account a : accounts) {
            sum += a.toCommon();
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s Total Amount: %.4f",
                id, name, surname, getTotalAmountInCommonCurrency());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
