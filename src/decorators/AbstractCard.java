package decorators;

import java.util.Calendar;

public abstract class AbstractCard implements CardInterface{
    protected String numberCard;
    protected int pin;
    protected volatile int money;
    protected Calendar howLongBlockedCard;
    protected int countIncorrectPin;

}
