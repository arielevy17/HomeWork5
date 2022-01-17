import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Shop {
    Calendar calendar= GregorianCalendar.getInstance();
    Date purchaseDate=Calendar.getInstance().getTime();
    Scanner scanner = new Scanner(System.in);
    private Person[] persons;
    private Product[] productsInTheShop;
    private ShoppingCart[] allShoppingCarts;

    public static final int EMPLOYEE = 1;
    public static final int CLIENT = 2;
    public static final int REGULAR_WORKER = 1;
    public static final int DIRECTOR = 2;
    public static final int BOARD_MEMBER = 3;
    public static final int CLUB_FRIEND = 1;
    public static final int NOT_CLUB_FRIEND = 2;
    public static final int LEGAL_PASSWORD = 6;
    public static final int END_THE_PURCHASE = -1;
    public static final int SHOPPING_CARD_ARRAY_LARGE = 1;
    public static final int SHOPPING_CARD_ARRAY_INDEX = 0;
    public static final double REGULAR_WORKER_REDUCTION=0.9;
    public static final double DIRECTOR_REDUCTION=0.8;
    public static final double BOARD_MEMBER_REDUCTION=0.7;

    public Shop() {
        this.persons = new Person[0];
        this.productsInTheShop = new Product[0];
        this.allShoppingCarts = new ShoppingCart[0];
    }

    public void creatPerson() {
        int userChoose;
        String name = "";
        do {
            System.out.println("enter your name: ");
            name = scanner.next();
        } while (!legalName(name));

        String lastName = "";
        do {
            System.out.println("enter your last name: ");
            lastName = scanner.next();
        } while (!legalName(lastName));

        String password = "";
        do {
            System.out.println("enter your password: ");
            password = scanner.next();
        } while (!legalPassword(password));

        String userName = "";
        do {
            System.out.println("enter your user name: ");
            userName = scanner.next();
        } while (userNameExist(userName));

        do {
            System.out.println("if you wont to creat a employee count press: 1" + "\n" + "if you wont to creat a client count press: 2");
            userChoose = scanner.nextInt();
        } while (userChoose > CLIENT || userChoose < EMPLOYEE);
        Person[] newArray = new Person[persons.length + 1];
        for (int i = 0; i < persons.length; i++) {
            newArray[i] = persons[i];
        }
        Person newPerson;
        if (userChoose == EMPLOYEE) {
            String rank = correctRank();
            newPerson = new Employee(name, lastName, userName, password, rank);
        } else {
            boolean clubCard;
            int userClubChoose;
            do {
                System.out.println("if you are a club friend, press: 1" + "\n" + "if not press: 2");
                userClubChoose = scanner.nextInt();
            } while (userClubChoose > NOT_CLUB_FRIEND || userClubChoose < CLUB_FRIEND);
            if (userClubChoose == CLUB_FRIEND) {
                clubCard = true;
            } else {
                clubCard = false;
            }
            newPerson = new Client(name, lastName, userName, password, clubCard);
        }
        newArray[persons.length] = newPerson;
        persons = newArray;
        System.out.println("new user successfully received!");
    }

    public boolean userNameExist(String userName) {
        boolean isItExist = false;
        for (int i = 0; i < persons.length; i++) {
            if (persons[i].getUserName().equals(userName)) {
                System.out.println("this user name is always exist!");
                return true;
            }
        }
        return isItExist;
    }

    public boolean legalName(String name) {
        boolean NameIsLegal = false;
        int counter = 0;
        for (int i = 0; i < name.length(); i++) {
            char currentChar = name.charAt(i);
            if (Character.isLetter(currentChar)) {
                counter++;
            }
        }
        if (counter == name.length()) {
            NameIsLegal = true;
        } else {
            System.out.println("the name is invalid, the name must contain only letters!");
        }
        return NameIsLegal;
    }

    public boolean legalPassword(String password) {
        boolean isItLegalPassword = false;
        if (password.length() >= LEGAL_PASSWORD) {
            isItLegalPassword = true;
        } else {
            System.out.println("Invalid password, password must be at least 6 characters long!");
        }
        return isItLegalPassword;
    }

    public String correctRank() {
        String employeeRank = "";
        int employeeCode;
        do {
            System.out.println("enter your worker code:" + "\n for regular worker press:  1" + "\n" + "for director press: 2" + "\n" + "for board member press: 3");
            employeeCode = scanner.nextInt();
        } while (employeeCode > BOARD_MEMBER || employeeCode < REGULAR_WORKER);
        if (employeeCode == REGULAR_WORKER) {
            employeeRank = "regular worker";
        } else if (employeeCode == DIRECTOR) {
            employeeRank = "director";
        } else if (employeeCode == BOARD_MEMBER) {
            employeeRank = "board member";
        }
        return employeeRank;
    }

    public Person login() {
        Person personDetails = null;
        String userName;
        String password;
        int kindOfPersonCode = 0;
        System.out.println("What type of account do you want to connect to?\n" +
                "If the account is a client press: 1\n" +
                "If the account or employee press: 2");
        kindOfPersonCode = scanner.nextInt();
        System.out.println("enter your user name:");
        userName = scanner.next();
        System.out.println("enter your password:");
        password = scanner.next();
        for (int i = 0; i < persons.length; i++) {
            if (persons[i].getUserName().equals(userName) && persons[i].getPassword().equals(password)) {
                if (persons[i] instanceof Client) {
                    if (((Client) persons[i]).getClubCard()) {
                        System.out.println("hello "+persons[i].getName() + " " + persons[i].getLastName() + " vip!");
                    } else {
                        System.out.println("hello "+persons[i].getName() + " " + persons[i].getLastName() + "!");
                    }
                } else if (persons[i] instanceof Employee) {
                    System.out.println("hello "+persons[i].getName() + " " + persons[i].getLastName() +" "+ ((Employee) persons[i]).getRank()+"!");
                }
                personDetails = persons[i];
            }
        }
        if (personDetails == null) {
            System.out.println("this user is not exist!");
        }
        return personDetails;
    }


    public ShoppingCart makePurchase(Person person) {
        Date date=Calendar.getInstance().getTime();
        ShoppingCart[] nowShoppingCart = new ShoppingCart[SHOPPING_CARD_ARRAY_LARGE];
        int userChooseProduct = 0;
        int amount = 0;
        int counter = 0;
        double totalPrice = 0;
        nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX] = new ShoppingCart(person,null, 0,date);
        do {
            do {
                System.out.println("Here is the list of products in stock," + "\n" +
                        "Please select the product number you want to purchase..");
                for (int i = 0; i < productsInTheShop.length; i++) {
                    if (productsInTheShop[i].getInStock()) {
                        System.out.println("product number: " + (i + 1) + " ----" + productsInTheShop[i].getProductName());
                    }
                }
                System.out.println("If you want to finish the purchase, press: -1");
                userChooseProduct = scanner.nextInt();
                userChooseProduct = userChooseProduct - 1;
                if (userChooseProduct==END_THE_PURCHASE-1){
                    break;
                }
            } while (userChooseProduct< 0  ||userChooseProduct>productsInTheShop.length);

            //  ווידוא כמות נכונה
          if (userChooseProduct!=END_THE_PURCHASE-1) {
              do {
                  System.out.println("How much of this product do you want to purchase?");
                  amount = scanner.nextInt();
              } while (amount < 1);
              //    מערך עזר שמקבל את המוצר כפול הכמות בבחירה הנוכחית
              Product[] productsCurrentSelection = new Product[amount];
              for (int i = 0; i < amount; i++) {
                  productsCurrentSelection[i] = productsInTheShop[userChooseProduct];
              }
              int indexLastProductsSelectionCounter = 0;
              Product[] lastProductsSelection = new Product[amount];
              if (nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].getProducts() != null) {
                  lastProductsSelection = new Product[(nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].getProducts().length) + amount];
                  for (int i = 0; i < nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].getProducts().length; i++) {
                      lastProductsSelection[i] = nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].getProducts()[i];
                      indexLastProductsSelectionCounter++;
                  }
              }

              //   עידכון עגלת הקניות הנוכחית
              nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX] = new ShoppingCart(person, productsCurrentSelection, totalPrice,date);

              for (int i = indexLastProductsSelectionCounter, a = 0; i < lastProductsSelection.length; i++, a++) {
                  lastProductsSelection[i] = nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].getProducts()[a];
              }

              nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX] = new ShoppingCart(person, lastProductsSelection, totalPrice,date);

              //  חישוב מחיר העגלה
              for (int i = 0; i < productsCurrentSelection.length; i++) {
                  totalPrice = totalPrice + productsCurrentSelection[i].getCost();
              }
              nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].setTotalCost(totalPrice);
              //  הדפסת עגלת הקניות הנוכחית
              System.out.println(nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX]);
              System.out.println();
          }
        } while (userChooseProduct != END_THE_PURCHASE-1);
        if (person instanceof Employee){
            if (((Employee) person).getRank().equals("regular worker")){
                totalPrice=(totalPrice*REGULAR_WORKER_REDUCTION);
                nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].setTotalCost(totalPrice);
            }
            else if (((Employee) person).getRank().equals("director")){
                totalPrice=(totalPrice*DIRECTOR_REDUCTION);
                nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].setTotalCost(totalPrice);
            }
            else if (((Employee) person).getRank().equals("board member")){
                totalPrice=(totalPrice*BOARD_MEMBER_REDUCTION);
                nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].setTotalCost(totalPrice);
            }
        }
        addPurchaseToArray(nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX]);
        System.out.println("The cost of the final shopping cart: "+nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX].getTotalCost());
        return nowShoppingCart[SHOPPING_CARD_ARRAY_INDEX];
    }


    //  העברת עגלת הקניות למערך כל עגלות הקניות
 public void addPurchaseToArray(ShoppingCart shoppingCarts){
        ShoppingCart [] auxiliaryArray =new ShoppingCart[this.allShoppingCarts.length+1];
        for (int i=0;i<allShoppingCarts.length;i++){
            auxiliaryArray[i]=allShoppingCarts[i];
        }
        auxiliaryArray[auxiliaryArray.length-1]=new ShoppingCart(shoppingCarts.getPerson(),shoppingCarts.getProducts(),shoppingCarts.getTotalCost(),shoppingCarts.getPurchaseDate());
       allShoppingCarts= auxiliaryArray;
    }

       //  מתודות לעובד

   public void printAllClients() {
       double totalCost=0;
       int purchaseCounter=0;
       Date lastPurchaseDate=GregorianCalendar.getInstance().getTime();
       int notSameDate=0;
       System.out.println("here all the shop client list:");
       for (int i = 0; i < persons.length; i++) {
           if (persons[i] instanceof Client) {  //  הדפסת עובד בפורמט המבוקש
               Client clients= (Client) persons[i];
               System.out.println(toString(clients));
           }
           else {   //  הדפסת עובד שהוא לקוח בפורמט המבוקש ללקוח
               int samePersonCounter = 0;
               for (int j = 0; j < allShoppingCarts.length; j++) {
                   if (persons[i].equals(allShoppingCarts[j].getPerson())) {
                       for (int a = j + 1; a < allShoppingCarts.length; a++) {  //  בודק שאין כפילות שם
                           if (allShoppingCarts[j].getPerson().equals(allShoppingCarts[a].getPerson())) {
                               samePersonCounter++;
                           }
                       }
                       if (samePersonCounter == 0) {
                           if (persons[i] instanceof Client){
                               Client clients = (Client) allShoppingCarts[j].getPerson();
                               System.out.println(toString(clients));
                           }
                           else {
                               System.out.println( allShoppingCarts[j].getPerson()+"\n club member:no");
                               for (int a=0;a<allShoppingCarts.length;a++){
                                   if (allShoppingCarts[a].getPerson().equals(allShoppingCarts[j].getPerson())){
                                       purchaseCounter++;
                                       totalCost=totalCost+allShoppingCarts[a].getTotalCost();
                                   }
                               }
                               System.out.println( "purchase number: "+purchaseCounter+"\n total costs: "+totalCost);
                               for (int a=allShoppingCarts.length-1;a>=0;a--){
                                   if (allShoppingCarts[a].getPerson().equals(allShoppingCarts[j].getPerson())){
                                       lastPurchaseDate=allShoppingCarts[a].getPurchaseDate();
                                       break;
                                   }
                                   else {
                                       notSameDate++;
                                   }
                               }
                               if (notSameDate != allShoppingCarts.length) {
                                   System.out.println("last purchase date: " + lastPurchaseDate);
                               }
                               else {
                                   System.out.println("last purchase date: not exist");
                               }
                           }
                       }
                       samePersonCounter = 0;
                   }
               }
           }

       }
   }

    public void printAllVipClients(){
        System.out.println("here all the shop vip client list:");
        for (int i=0;i<persons.length;i++){
            if (persons[i] instanceof Client && ((Client) persons[i]).getClubCard()){
                Client clients= (Client) persons[i];
                System.out.println(toString(clients));
            }
        }
    }

    public void printAllClientsHowBuy(){
        int notSameDate=0;
        double totalCost=0;
        int purchaseCounter=0;
        Date lastPurchaseDate=GregorianCalendar.getInstance().getTime();
        System.out.println("here all the shop clients how buy in the shop until now:");
        int samePersonCounter=0;
                for (int j=0;j<allShoppingCarts.length;j++){
                    for (int i=j+1;i<allShoppingCarts.length;i++){
                        if(allShoppingCarts[i].getPerson().equals(allShoppingCarts[j].getPerson())){
                            samePersonCounter++;
                        }
                    }
                     if (samePersonCounter==0){
                         if (allShoppingCarts[j].getPerson() instanceof  Client) {  //  הדפסת לקוח בפורמט המבוקש
                             Client clients = (Client) allShoppingCarts[j].getPerson();
                             System.out.println(toString(clients));
                             System.out.println();
                         }

                         else {   //  הדפסת עובד שהוא לקוח בפורמט המבוקש ללקוח
                             System.out.println( allShoppingCarts[j].getPerson()+"\n club member:no");
                             for (int i=0;i<allShoppingCarts.length;i++){
                                 if (allShoppingCarts[i].getPerson().equals(allShoppingCarts[j].getPerson())){
                                     purchaseCounter++;
                                     totalCost=totalCost+allShoppingCarts[i].getTotalCost();
                                 }
                             }
                             System.out.println( "purchase number: "+purchaseCounter+"\n total costs: "+totalCost);
                             for (int i=allShoppingCarts.length-1;i>=0;i--){
                                 if (allShoppingCarts[i].getPerson().equals(allShoppingCarts[j].getPerson())){
                                     lastPurchaseDate=allShoppingCarts[i].getPurchaseDate();
                                     break;
                                 }
                                 else {
                                     notSameDate++;
                                 }
                             }
                             if (notSameDate != allShoppingCarts.length) {
                                 System.out.println("last purchase date: " + lastPurchaseDate+"\n");
                             }
                             else {
                                 System.out.println("last purchase date: not exist"+"\n");
                             }
                         }
                     }
                     samePersonCounter=0;
                    }
                }

    public void printTheBiggestBuyer() {
        int notSameDate=0;
        double biggestCost = 0;
        double totalCostForPerson = 0;
        int indexBiggestBuyer = 0;
        double totalCost=0;
        int purchaseCounter=0;
        Date lastPurchaseDate=GregorianCalendar.getInstance().getTime();

        for (int i = 0; i < persons.length; i++) {
                for (int j = 0; j < allShoppingCarts.length; j++) {
                    if (allShoppingCarts[j].getPerson().equals(persons[i])) {
                    totalCostForPerson =totalCostForPerson+allShoppingCarts[j].getTotalCost();
                    }
                    }
            if (totalCostForPerson >biggestCost){
                biggestCost= totalCostForPerson;
                indexBiggestBuyer=i;
                }
            totalCostForPerson=0;
            }
        if (allShoppingCarts[indexBiggestBuyer].getPerson() instanceof  Client) {  //  הדפסת לקוח בפורמט המבוקש
            Client clients = (Client) allShoppingCarts[indexBiggestBuyer].getPerson();
            System.out.println("the biggest buyer in the shop is:\n" + toString(clients));
        }
        else {   //  הדפסת עובד שהוא לקוח בפורמט המבוקש ללקוח
            System.out.println( allShoppingCarts[indexBiggestBuyer].getPerson()+"\n club member:no");
            for (int i=0;i<allShoppingCarts.length;i++){
                if (allShoppingCarts[i].getPerson().equals(allShoppingCarts[indexBiggestBuyer].getPerson())){
                    purchaseCounter++;
                    totalCost=totalCost+allShoppingCarts[i].getTotalCost();
                }
            }
            System.out.println( "purchase number: "+purchaseCounter+"\n total costs: "+totalCost);
            for (int i=allShoppingCarts.length-1;i>=0;i--){
                if (allShoppingCarts[i].getPerson().equals(allShoppingCarts[indexBiggestBuyer].getPerson())){
                    lastPurchaseDate=allShoppingCarts[i].getPurchaseDate();
                    break;
                }
                else {
                    notSameDate++;
                }
            }
            if (notSameDate != allShoppingCarts.length) {
                System.out.println("last purchase date: " + lastPurchaseDate);
            }
            else {
                System.out.println("last purchase date: not exist");
            }
        }
    }

    public Product [] addProduct(){
        String productName="";
        int productCost=0;
        Product []  allcturyProductArray=new Product[productsInTheShop.length+1];
        for (int i=0;i<productsInTheShop.length;i++){
            allcturyProductArray[i]=productsInTheShop[i];
        }
        do {
            System.out.println("enter your product name: ");
            productName = scanner.next();
        } while (!legalName(productName));
        do {
            System.out.println("enter your product cost: ");
            productCost = scanner.nextInt();
        } while (productCost<1);
        allcturyProductArray[productsInTheShop.length]=new Product(productName,true,productCost);
        productsInTheShop=allcturyProductArray;
        System.out.println(productsInTheShop[productsInTheShop.length-1]);
        return productsInTheShop;
    }

    public Product [] changInStockStatus(){
        int newStatusCode=0;
        boolean newStatus;
        int userProductChoose=0;
        do {
            System.out.println("Select the product number for which you want to change the status:");
            for (int i = 0; i < productsInTheShop.length; i++) {
                System.out.println("product number:" + (i + 1) + "\n" + productsInTheShop[i].getProductName());
            }
            userProductChoose = scanner.nextInt();
        } while (userProductChoose<1 || userProductChoose>productsInTheShop.length);
        do {
            System.out.println("To what status would you like to change the product:" + "\n" +
                    "To enter stock press 1" + "\n" +
                    "To report a lack of press inventory 2");
            newStatusCode = scanner.nextInt();
        } while (newStatusCode<1 || newStatusCode>2);

        if (newStatusCode==1){
            newStatus=true;
        }
        else {
            newStatus=false;
        }
        productsInTheShop[userProductChoose-1].setInStock(newStatus);
        return productsInTheShop;
    }

    //  הדפסת לקוח בפורמט המבוקש
 public String toString(Client client){
        String dateNotExist=" last purchase date: not exist";
           Date lastPurchaseDate=GregorianCalendar.getInstance().getTime();
           int purchaseCounter=0;
            double totalCost=0;
            int noDateCounter=0;
            String output="full name: "+client.getName()+" "+client.getLastName();
            if (client.getClubCard()) {
                output = client.toString() + "\n did he friend club:";
                if (client.getClubCard()) {
                    output = output + " yes";
                } else {
                    output = output + " no";
                }
            }
         for (int i=0;i<allShoppingCarts.length;i++){
        if (allShoppingCarts[i].getPerson().equals(client)){
            purchaseCounter++;
        totalCost=totalCost+allShoppingCarts[i].getTotalCost();
        }
        }
        output=output+"\n purchase number: "+purchaseCounter+"\n total costs: "+totalCost;
        for (int i=allShoppingCarts.length-1;i>=0;i--){
            if (allShoppingCarts[i].getPerson().equals(client)){
                lastPurchaseDate=allShoppingCarts[i].getPurchaseDate();
                 break;
            }
            else {
                noDateCounter++;
            }
        }
        if (noDateCounter!=allShoppingCarts.length){
            output=output+"\n last purchase date: "+lastPurchaseDate;
            return output;
        }
        else {
            output=output+"\n"+dateNotExist;
        }

       return output;
     }
}

