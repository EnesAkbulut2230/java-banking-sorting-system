import java.util.Random;

public class Generator {
    private static final String[] FIRST_NAMES = {
        "Mehmet","Burak","Kaan","Yusuf","Sinan","Yakup","Enes","Onur"
    };
    private static final String[] LAST_NAMES = {
        "Birben","Akbulut","Sonlu","Zor","Yıldırım","Çelebi","Balcı","Onur"
    };

    public static User[] generateUsers(int number) {
        User[] users = new User[number];
        Random rnd = new Random();

        for (int i = 0; i < number; i++) {
            String id = String.format("%09d", rnd.nextInt(1_000_000_000));

            String names    = FIRST_NAMES[rnd.nextInt(FIRST_NAMES.length)];
            String surnames = LAST_NAMES[rnd.nextInt(LAST_NAMES.length)];

            User user = new User(id, names, surnames);

            int acctCount = 2 + rnd.nextInt(9);
            for (int j = 0; j < acctCount; j++) {
                char currency = (char)('A' + rnd.nextInt(4));     
                double amount = rnd.nextDouble() * 1000;           
                user.addAccount(new Account(currency, amount));
            }

            users[i] = user;
        }
        return users;
    }
}
