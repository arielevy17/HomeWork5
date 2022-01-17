import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        final int CREAT_ACCOUNT = 1;
        final int LOGIN = 2;
        final int END_THE_PROGRAM = 3;
        final int PRINT_ALL_CLIENTS=1;
        final int PRINT_ALL_VIP_CLIENTS=2;
        final int PRINT_ALL_CLIENT_HOW_BUY=3;
        final int PRINT_BIGGEST_BUYER=4;
        final int ADD_PRODUCT=5;
        final int CHANGE_IN_STOCK_STATUS=6;
        final int MAKE_A_PURCHASE=7;
        final int END_EMPLOYEE_MENU=8;


        Shop shop = new Shop();
        Scanner scanner = new Scanner(System.in);
        int userChoose = 0;

        while (userChoose != END_THE_PROGRAM) {
            do {
                System.out.println();
                System.out.println("Welcome to the digital system of the supermarket !!\n" +
                        "Please select the action you want to perform:\n" +
                        "To create a new account, press: 1\n" +
                        "To log in to an existing account, press: 2\n" +
                        "To exit Press: 3");
                userChoose = scanner.nextInt();

                switch (userChoose) {
                    case CREAT_ACCOUNT:
                        if (userChoose==CREAT_ACCOUNT) {
                            shop.creatPerson();
                        }
                    case LOGIN:
                        if (userChoose==LOGIN) {
                            Person theUserNow=shop.login();
                            if (theUserNow==null) {
                                break;
                            } else {
                                if (theUserNow instanceof Client){
                                    shop.makePurchase(theUserNow);
                                }
                                else if (theUserNow instanceof Employee){
                                    int employeeChoose=0;
                                    while (employeeChoose!= END_EMPLOYEE_MENU) {
                                        do {
                                            System.out.println("Select the action you want to perform:\n" +
                                                    "To print a list of all customers, press: 1\n" +
                                                    "To print all club members Press: 2\n" +
                                                    "To print the customers who have purchased at least once, press: 3\n" +
                                                    "To print the customer who purchased the most, press: 4\n" +
                                                    "To add a new product, tap: 5\n" +
                                                    "To change the status of the product, press: 6\n" +
                                                    "To make a purchase, tap: 7\n" +
                                                    "To log out, tap: 8");
                                            employeeChoose = scanner.nextInt();
                                            switch (employeeChoose) {
                                                case PRINT_ALL_CLIENTS:
                                                    if (employeeChoose == PRINT_ALL_CLIENTS) {
                                                        shop.printAllClients();
                                                        System.out.println();
                                                    }
                                                case PRINT_ALL_VIP_CLIENTS:
                                                    if (employeeChoose == PRINT_ALL_VIP_CLIENTS) {
                                                        shop.printAllVipClients();
                                                        System.out.println();
                                                    }
                                                case PRINT_ALL_CLIENT_HOW_BUY:
                                                    if (employeeChoose == PRINT_ALL_CLIENT_HOW_BUY) {
                                                        shop.printAllClientsHowBuy();
                                                        System.out.println();
                                                    }
                                                case PRINT_BIGGEST_BUYER:
                                                    if (employeeChoose == PRINT_BIGGEST_BUYER) {
                                                        shop.printTheBiggestBuyer();
                                                        System.out.println();
                                                    }
                                                case ADD_PRODUCT:
                                                    if (employeeChoose == ADD_PRODUCT) {
                                                        shop.addProduct();
                                                        System.out.println();
                                                    }
                                                case CHANGE_IN_STOCK_STATUS:
                                                    if (employeeChoose == CHANGE_IN_STOCK_STATUS) {
                                                        shop.changInStockStatus();
                                                        System.out.println();
                                                    }
                                                case MAKE_A_PURCHASE:
                                                    if (employeeChoose == MAKE_A_PURCHASE) {
                                                        shop.makePurchase(theUserNow);
                                                        System.out.println();
                                                    }
                                                case END_EMPLOYEE_MENU:
                                                    if (employeeChoose == END_EMPLOYEE_MENU) {
                                                        break;
                                                    }
                                            }
                                        } while (employeeChoose > END_EMPLOYEE_MENU || employeeChoose < PRINT_ALL_CLIENTS);
                                    }
                                }
                            }
                        }

                    case END_THE_PROGRAM:
                        System.out.println("See you soon!");
                        break;
                }
            } while (userChoose < CREAT_ACCOUNT || userChoose > END_THE_PROGRAM);
        }
    }
}
