import exceptions.AccountBlockedException;

/**
 * Created by ermakov on 25.11.2016.
 */
public class Main {
    public static void main(String[] args) throws AccountBlockedException {
        try {
            throw new AccountBlockedException();
        } catch (AccountBlockedException e) {
            System.out.println(e.getMessage());
        }

        throw new AccountBlockedException();
    }
}
