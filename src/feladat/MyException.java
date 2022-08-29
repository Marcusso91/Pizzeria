package feladat;

/* i creating custom exception class for the special exceptions */
public class MyException extends Exception {

    private String hibauzenet;

    public MyException(String hibauzenet) {

        this.setHibauzenet(hibauzenet);
    }

    public String getHibauzenet() {
        return hibauzenet;
    }

    public void setHibauzenet(String hibauzenet) {
        this.hibauzenet = hibauzenet;
    }

    @Override
    public String toString() {

        return this.getHibauzenet();
    }

}
