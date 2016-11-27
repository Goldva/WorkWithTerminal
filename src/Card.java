import exceptions.IncorrectPinException;
import exceptions.InsufficientFundsOnTheCardException;

public class Card {
    private String pin;
    private int money;
    private String numberCard;

    public Card(String numberCard, String pin) {
        this.pin = pin;
        this.money = 0;
        this.numberCard = numberCard;
//        createNewPin();
    }

    public void putMoney(int money){
        this.money += money;
        System.out.println(String.format("Начислено %d рублей", money));
    }

    public void shootMoney(String pin, int money) throws InsufficientFundsOnTheCardException, IncorrectPinException {
        checkPin(pin);
        if (this.money < money)
            throw new InsufficientFundsOnTheCardException();
        this.money -= money;
        System.out.println(String.format("Выдано %d рублей", money));
    }

    public void checkPin(String pin) throws IncorrectPinException {
        if (!this.pin.equals(pin))
            throw new IncorrectPinException();

//        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            System.out.println("Введите ваш пин-код состоящий из 4 цифр");
//            if (!pin.equals(br.readLine()))
//                throw new IncorrectPinException();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void newPin(String oldPin, String newPin) throws IncorrectPinException {
        checkPin(oldPin);
        this.pin = newPin;
}

//    private void createNewPin() {
//        Pattern pattern = Pattern.compile("[0-9]{4}");
//        Matcher matcher;
//        String newPin = "";
//        while (newPin.isEmpty()) {
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//                System.out.println("Введите новый пин-код состоящий из 4 цифр");
//                newPin = br.readLine();
//                matcher = pattern.matcher(newPin);
//                if (!matcher.matches())
//                    throw new IncorrectPinException();
//                pin = newPin;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (IncorrectPinException e) {
//                newPin = "";
//                System.out.println(e.getMessage());
//            }
//        }
//    }

    public String getNumberCard() {
        return numberCard;
    }
}
