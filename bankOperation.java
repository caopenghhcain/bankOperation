package bankapptrue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class bankOperation {
    private ArrayList<account> list = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    public static final String RESET ="\u001B[0m";
    public static final String RED ="\u001B[31m";
    public static final String GREEN ="\u001B[32m";
    public static final String YELLOW ="\u001B[33m";
    public static final String BLUE ="\u001B[34m";

    public void mainMenu() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.flush();//不知道是啥
            System.out.println(BLUE+"The initial page------------"+RESET);
            System.out.println(GREEN+"1）Register"+RESET);
            System.out.println(GREEN+"2）Log in"+RESET);
            System.out.println(GREEN+"0）Return"+RESET);
            System.out.println(YELLOW+"Please enter your operational requirements."+RESET);
            String i = input.next();
            switch (i) {
                case "1":
                    okInput();
                    break;
                case "2":
                    account ac=new account();
                    ac= logIn();
                    menu(ac);
                    break;
                case "0":
                    return;
                default:
                    System.out.println(RED+"Your input in incorrect,please re-enter！！"+RESET);
            }
        }
    }//The initial page

    public account logIn() {
        if(list.isEmpty()){
            System.out.println(BLUE+"Please register an account first！！"+RESET);
            return null;
        }
        while (true) {
            System.out.print(BLUE+"Please enter your username ： "+RESET);
            String name = input.next();
            System.out.print(BLUE+"Please enter your password： "+RESET);
            String password = input.next();
            boolean very=verify();
            while (very) {
                for (int i = 0; i < list.size(); i++) {
                    account ac=list.get(i);
                    if(Objects.equals(ac.getName(), name)&&Objects.equals(ac.getPassword(),password)){
                        System.out.println(RED+"Login Successful!"+RESET);
                        return ac;
                    }
                    if(i==list.size()-1){
                        very=false;
                    }
                }
            }
            System.out.println(RED+"The name or password is wrong,please try again"+RESET);
        }
    }//The login interface 2

    public void menu(account ac)  {
        while (true){
            System.out.println(" ");
            System.out.println(BLUE+"operating system page------------"+RESET);
            System.out.println(GREEN+"1）Personal information"+RESET);
            System.out.println(GREEN+"2）Deposit"+RESET);
            System.out.println(GREEN+"3）Withdraw money"+RESET);
            System.out.println(GREEN+"4）Transfer accounts"+RESET);
            System.out.println(GREEN+"0）Return"+RESET);
            System.out.println(YELLOW+"Please enter your operational requirements"+RESET);
            String i=input.next();
            switch (i) {
                case "1":
                    System.out.println(ac.toString());
                    moneyNum(ac);
                    interestNum(ac);
                    moneyDetail(ac);
                    break;
                case "2":
                    depositMenu(ac);
                    break;
                case "3":
                    drawMoneyMenu(ac);
                    break;
                case "4":
                    transferAccount(ac);
                    break;
                case "0":
                    return;
                default:
                    System.out.println(RED+"Your input is incorrect,please enter again！！"+RESET);
            }
        }
    }//The operating system page 2

    public void transferAccount(account ac){
        if(list.size()<=1){
            System.out.println(RED+"There are no more than two accounts in the system. Please register and try again"+RESET);
        }else {
            if(authentication(ac)){
                System.out.println(ac.toString(ac));
                moneyNum(ac);
                interestNum(ac);
                transfer(ac);
            }else {
                System.out.println(RED+"Your password is wrong！"+RESET);
            }
        }
    }//The main of transfer 2.3

    public void transfer(account ac){
        System.out.println(YELLOW+"Are you sure you want to transfer the money？yes/no"+RESET);
        String result =input.next();
        char a=result.charAt(0);
        a=Character.toLowerCase(a);
        if(Objects.equals(a,'y')){
            System.out.println(BLUE+"Please enter the recipient of your transfer:"+RESET);
            String object=input.next();
            account objectA=invocation(object);
            System.out.println(BLUE+"Please enter the amount you want to tr transfer："+RESET);
            double number=input.nextDouble();
            if(number<=ac.getMoneyC()+ac.getMoneyS()){
                if(authentication(ac)){
                    if(number<=ac.getMoneyC()){
                        System.out.println(RED+"Transfer successful！"+RESET);
                        ac.setMoneyC(ac.getMoneyC()-number);
                        objectA.setMoneyC(objectA.getMoneyC()+number);
                        currentAccountInterest(ac);
                        currentAccountInterest(objectA);
                        savingAccountInterest(ac);
                        savingAccountInterest(objectA);
                        moneyDetail(ac);
                    }else {
                        System.out.println(RED+"Transfer successful！"+RESET);
                        ac.setMoneyS(ac.getMoneyS()+ac.getMoneyC()-number);
                        ac.setMoneyC(0.0);
                        objectA.setMoneyC(objectA.getMoneyC()+number);
                        currentAccountInterest(ac);
                        currentAccountInterest(objectA);
                        savingAccountInterest(ac);
                        savingAccountInterest(objectA);
                        moneyDetail(ac);
                    }
                }else {
                    System.out.println(RED+"Your password is wrong"+RESET);
                }
            }else {
                System.out.println(BLUE+"Insufficient Balance"+RESET);
            }
        }
    }//The method of transfer 2.3.1

    public account invocation(String object){
        boolean very=verify();
        while (very) {
            for (int i = 0; i < list.size(); i++) {
                account ac=list.get(i);
                if(Objects.equals(ac.getName(), object)){
                    System.out.println(BLUE+"Do you want to transfer money to "+ac.getName()+"？yes/no"+RESET);
                    String name=input.next();
                    char a=name.charAt(0);
                    a=Character.toLowerCase(a);
                    if(Objects.equals(a,'y')){
                        return ac;
                    }
                }
                if(i==list.size()-1){
                    very=false;

                }
            }
        }return null;
    } //Confirm the transfer recipient 2.3.2

    public void drawMoneyMenu(account ac){
        while (true) {
            System.out.println(" ");
            System.out.println(BLUE+"Are you sure you want to withdraw money"+RESET);
            System.out.println(GREEN+"1）Continue"+RESET);
            System.out.println(GREEN+"0）Return"+RESET);
            System.out.println(YELLOW+"Please enter your operational requirement."+RESET);
            String i = input.next();
            switch (i) {
                case "1":
                    drawMoney(ac);
                    break;
                case "0":
                    return;
                default:
                    System.out.println(RED+"Your input in incorrect,please enter again！！"+RESET);
            }
        }
    }//The menu of draw money 2.2

    public void drawMoney(account ac){
        if(authentication(ac)){
            System.out.println(YELLOW+"Please enter the amount you want to withdraw："+RESET);
            double number=input.nextDouble();
            if(number<=ac.getMoneyC()+ac.getMoneyS()){
                int i=0;
                while(true){
                    if(authentication(ac)){
                        if(number<=ac.getMoneyC()){
                            System.out.println(RED+"Withdrawal Successful！"+RESET);
                            ac.setMoneyC(ac.getMoneyC()-number);
                            currentAccountInterest(ac);
                            System.out.println(BLUE+"Balance of current account:"+RESET+ac.getMoneyC());
                            moneyDetail(ac);
                            return;
                        }else {
                            System.out.println(RED+"Withdrawal Successful！"+RESET);
                            ac.setMoneyS(ac.getMoneyS()+ac.getMoneyC()-number);
                            ac.setMoneyC(0.0);
                            currentAccountInterest(ac);
                            System.out.println(BLUE+"Balance of current account:"+RESET+ac.getMoneyC());
                            savingAccountInterest(ac);
                            System.out.println(BLUE+"Balance of saving account:"+RESET+ac.getMoneyS());
                            moneyDetail(ac);
                            return;
                        }
                    }
                    i++;
                    if (i>3) {
                        System.out.println(RED+"Multiple errors,try again later"+RESET);
                        return;
                    }

                }
            }else {
                if(number>ac.getMoneyC()+ac.getMoneyS()+ac.getMoneyT()){
                    System.out.println(BLUE+"Insufficient Balance"+RESET);
                }else {
                    System.out.println(RED+"The money inside cannot be withdrawn online, and withdrawing it offline from the bank will result in a loss of 20% of the principal"+RESET);
                }
            }
        }
    }//The method of draw money 2.2.1

    public void depositMenu(account ac){
        while (true) {
            System.out.println(" ");
            System.out.println(BLUE+"Deposit method"+RESET);
            System.out.println(GREEN+"1）Specific information"+RESET);
            System.out.println(GREEN+"2）Current Account"+RESET);
            System.out.println(GREEN+"3）Time Account"+RESET);
            System.out.println(GREEN+"4）Saving Account"+RESET);
            System.out.println(GREEN+"0）Return"+RESET);
            System.out.println(BLUE+"Please enter your operational requirement."+RESET);
            String i = input.next();
            switch (i) {
                case "1":
                    depositInformation();
                    break;
                case "2":
                    currentAccount(ac);
                    break;
                case "3":
                    ac.setMoneyT(timeAccount(ac));
                    break;
                case "4":
                    ac.setMoneyS(savingAccount(ac));
                    break;
                case "0":
                    return;
                default:
                    System.out.println(RED+"Your input is incorrect,please enter again！！"+RESET);
            }
        }
    }//The deposit main page 2.1

    public void moneyDetail(account ac){
        System.out.println(YELLOW+"Do you want to view the detailed bill ?yes/no"+RESET);
        String result =input.next();
        char a=result.charAt(0);
        a=Character.toLowerCase(a);
        if(Objects.equals(a,'y')){
            System.out.println(ac.toString(ac));
            moneyNum(ac);
            interestNum(ac);
        }
    }//View property details 2.1.1

    public  void currentAccount(account ac){
        System.out.println(BLUE+"Your current account:"+RESET+ac.getMoneyC());
        if (authentication(ac)){
            System.out.println(BLUE+"The amount you want to transfer："+RESET);
            double transfer=input.nextDouble();
            ac.setMoneyC(transfer+ac.getMoneyC());
            currentAccountInterest(ac);
            System.out.println(BLUE+"daily earning of current account:"+RESET+ac.getInterestC());
            if(ac.getMoneyC()>=6000){
                System.out.println(RED+"You are suitable to transfer some of current account to saving account in order to generate more profits."+RESET);
                System.out.println(YELLOW+"Whether to transfer or not ？yes/no"+RESET);
                String result =input.next();
                char a=result.charAt(0);
                a=Character.toLowerCase(a);
                if(Objects.equals(a,'y')){
                    int i=0;
                    boolean very=true;
                    while(very){
                        System.out.println(BLUE+"The amount you want to transfer："+RESET);
                        double move=input.nextDouble();
                        if(move<=ac.getMoneyC()){
                            ac.setMoneyS(move+ac.getMoneyS());
                            ac.setMoneyC(ac.getMoneyC()-move);
                            savingAccountInterest(ac);
                            System.out.println(BLUE+"Interest of saving account:"+RESET+ac.getInterestS());
                            moneyDetail(ac);
                            very=false;
                        }else{
                            System.out.println(RED+"Insufficient Balance"+RESET);
                            i++;
                            if(i>=3){
                                break;
                            }
                        }
                    }
                }
            }
        }else{
            System.out.println(RED+"Your password is incorrect.Please try again later！"+RESET);
        }
    }//The method of current account 2.1.2

    public void currentAccountInterest(account ac){
        double data=ac.getMoneyC();
        ac.setInterestC(String.format("%.2f",data*0.002/365));
    }//Change the current account interest 0.2

    public void savingAccountInterest(account ac){
        DecimalFormat df=new DecimalFormat("0.00");
        double randomDouble=0.5+Math.random();
        ac.setInterestS(df.format(ac.getMoneyS()*0.01*randomDouble/365));
    }//Change the saving account interest 0.2

    public  double timeAccount(account ac){
        System.out.println(BLUE+"Your time account:"+RESET+ac.getMoneyT());
        System.out.println(BLUE+"The money in Time account usually cannot be withdrawn before maturity and will be automatically transferred to another account after maturity."+RESET);
        if (authentication(ac)){
           while (true){
               System.out.println(BLUE+"please choose your time type"+RESET);
               System.out.println(GREEN+"1)short-time"+RESET);
               System.out.println(GREEN+"2)long-time"+RESET);
               System.out.println(GREEN+"0)Return"+RESET);
               System.out.println(YELLOW+"please choose:"+RESET);
               String i=input.next();
               switch (i){
                   case "1":
                       shortTime(ac);
                       break;
                   case "2":
                       longTime(ac);
                       break;
                   case "0":
                       return ac.getMoneyT() ;
                   default:
                       System.out.println(RED+"Your enter is wrong"+RESET);
               }
           }
        }else{
            System.out.println(RED+"Your password is incorrect.Please try again later！"+RESET);
        }
        return ac.getMoneyT() ;
    }//The method of Time account 2.1.3

    public void shortTime(account ac) {
        System.out.println(BLUE+"The amount you want to transfer："+RESET);
        double transfer = input.nextDouble();
        ac.setMoneyT(transfer + ac.getMoneyT());
        double data=ac.getMoneyT();
        String str =Double.toString((double) Math.round(data*0.011*100.0)/100.0);
        ac.setInterestT(str);
        System.out.println(BLUE+"Expected earnings in one year:"+RESET + ac.getInterestT());
    }//The detail of short-time account 2.1.4

    public void longTime(account ac) {
        System.out.println(BLUE+"The amount you want to transfer："+RESET);
        double transfer = input.nextDouble();
        ac.setMoneyT(transfer + ac.getMoneyT());
        if(Double.parseDouble(ac.getInterestT())<=10000){
            DecimalFormat df = new DecimalFormat("0.00");
            ac.setInterestT(df.format(ac.getMoneyT() * 0.0155* 5));
        }else {
            DecimalFormat df = new DecimalFormat("0.00");
            ac.setInterestT(df.format(55+ac.getMoneyT() * 0.0145 * 5));
        }
        System.out.println(BLUE+"Expected earnings in five year:"+RESET + ac.getInterestT());
    }//The detail of long-time account 2.1.5

    public  double  savingAccount(account ac){
        System.out.println(BLUE+"Your saving account:"+RESET+ac.getMoneyS());
        if (authentication(ac)){
            System.out.println(BLUE+"The amount you want to transfer："+RESET);
            double transfer=input.nextDouble();
            ac.setMoneyS(transfer+ac.getMoneyS());
            DecimalFormat df=new DecimalFormat("0.00");
            double randomDouble=0.5+Math.random();
            ac.setInterestS(df.format(ac.getMoneyS()*0.01*randomDouble/365));
            System.out.println(BLUE+"The daily interest of saving account:"+RESET+ac.getInterestS());
        }else{
            System.out.println(RED+"Your password is incorrect.Please try again later！"+RESET);
        }
        return ac.getMoneyS() ;
    }//The method of Saving account 2.1.6

    public  boolean authentication(account ac){
        System.out.println(BLUE+"Please enter your password to verify your identity:"+RESET);
        String password=input.next();
        return Objects.equals(password, ac.getPassword());
    }//The method of password authentication 2.1.7

    public void moneyNum(account ac){
        double num=0;
        num=ac.getMoneyT()+ac.getMoneyC()+ac.getMoneyS();
        System.out.println(BLUE+"The total is:"+RESET+"  "+num);
    }//The method of Asset calculation 2.1.8

    public void interestNum(account ac){
        double num=0;
        double value1=Double.parseDouble(ac.getInterestT());
        double value2=Double.parseDouble(ac.getInterestC());
        double value3=Double.parseDouble(ac.getInterestS());
        num=value2+value3;
        System.out.println(BLUE+"The total daily interest is:"+RESET+"  "+num);
        System.out.println(BLUE+"The time account interest:"+RESET+value1);
    }//The method of Interest calculation 2.1.9

    public void depositInformation(){
        System.out.println(BLUE+"Current Account:Suitable for daily trading"+RESET);
        System.out.println(BLUE+"                Interest rate:0.2%"+RESET);
        System.out.println(BLUE+"Time Account: For short-time idle funds"+RESET);
        System.out.println(BLUE+"              Interest rate:1 year  ---2% "+"\n"+"              5 year---3.7%"+RESET);
        System.out.println(BLUE+"Saving Account:For small-scale storage(maybe under 8000"+RESET);
        System.out.println(BLUE+"               Interest rate:0.5% to 1.5%"+RESET);
    }//Display information about deposit categories 2.1.10

    public void okInput() {
        account newAccount = new account();
        newAccount.setName(name());
        newAccount.setPassword(password());
        newAccount.setAge(age());
        boolean very=verify();
        if(very){
            list.add(newAccount);
            newAccount.setAccNumber(32128100+list.size());
            System.out.println("successfully registered");
            System.out.println("-------------------------");
        }
    }//The method of registering an account 1

    public String name() {
        System.out.print(BLUE+"Please enter your name："+RESET);
        while (true) {
            String name = input.next();
            if (name.length() < 13) {
                return name;

            } else {
                System.out.println(RED+"The letters of your name<=12"+RESET);
                System.out.println(YELLOW+"Please enter your name again："+RESET);
            }
        }
    }//The limitation of account name 1.1

    public int age() {
        while (true) {
            System.out.print(BLUE+"Please enter your age："+RESET);
            int age = input.nextInt();
            if (age > 1 && age <= 100) {
                return age;
            } else {
                System.out.println(RED+"Maybe the age of you from 1~100"+RESET);
                System.out.println(YELLOW+"Please enter your age again："+RESET);
            }
        }
    }//The limitation of account age 1.2

    private String password() {
        while (true) {
            System.out.print(BLUE+"Please enter your password ："+RESET);
            String password = input.next();
            System.out.print(BLUE+"Please enter your password again ："+RESET);
            String okPassword = input.next();
            if (Objects.equals(password, okPassword)) {
                return okPassword;
            }else {
                System.out.println(RED+"The password you entered twice is different ,please re-enter！"+RESET);
            }
        }
    }//The limitation of password 1.3

    public boolean verify(){
        boolean result=true;
        int i=0;
        while (true) {
            String ma=verification(6);
            System.out.print(BLUE+"This is Verification code  "+RESET);
            System.out.println(ma);
            System.out.print(BLUE+"Please enter ： "+RESET);
            String again= input.next();
            if(Objects.equals(again, ma)){
                System.out.println(RED+"Authenticate Successful"+RESET);
                break;
            }else{
                System.out.println(RED+"Authentication Failed"+RESET);
                i++;
                if(i==3) {
                    System.out.println(RED+"Failed multiple times ,please try again later"+RESET);
                    System.out.println(YELLOW+"---------------------------"+RESET);
                    return false;
                }
            }
        }
        return result;
    }//The main method of verification 0.1

    public static String verification(int a){
        String code="";
        Random r=new Random();
        for (int i = 0; i < a; i++) {
            int type=r.nextInt(3);
            switch (type){
                case 0:
                    code+=r.nextInt(10);
                    break;
                case 1:
                    char ch1= (char) (r.nextInt(26)+65);
                    code+=ch1;
                    break;
                case 2:
                    char ch2= (char) (r.nextInt(26)+97);
                    code+=ch2;
                    break;
            }
        }
        return  code;
    }//The method of Verification code generation 0.1
}
