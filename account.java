package bankapptrue;

public class account {
    private String name;
    private String password;
    private int age;
    private int accNumber;
    private double moneyC;
    private double moneyT;
    private double moneyS;
    private String interestC="0";
    private String interestT="0";
    private String interestS="0";

    public account() {
    }

    public account(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String getInterestC() {
        return interestC;
    }

    public void setInterestC(String interestC) {
        this.interestC = interestC;
    }

    public String getInterestT() {
        return interestT;
    }

    public void setInterestT(String interestT) {
        this.interestT = interestT;
    }

    public String getInterestS() {
        return interestS;
    }

    public void setInterestS(String interestS) {
        this.interestS = interestS;
    }

    public double getMoneyC() {
        return moneyC;
    }

    public void setMoneyC(double moneyC) {
        this.moneyC = moneyC;
    }

    public double getMoneyT() {
        return moneyT;
    }

    public void setMoneyT(double moneyT) {
        this.moneyT = moneyT;
    }

    public double getMoneyS() {
        return moneyS;
    }

    public void setMoneyS(double moneyS) {
        this.moneyS = moneyS;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "account" +'\n'+
                "Name:" + this.name + '\n'+
                "Age:" + this.age +'\n'+
                "Number:" + this.accNumber ;
    }

    public String toString(account ac){
        return "Type"+"                      "+"   interest"+'\n'+
                "Current Account" + "  "+this.moneyC+ "    "+this.interestC+'\n'+
                "Time Account" +"     "+this.moneyT+"    "+this.interestT+'\n'+
                "Saving Account" +"  "+this.moneyS+"    "+this.interestS;
    }
}
//Alt+insert 快捷键