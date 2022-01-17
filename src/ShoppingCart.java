import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ShoppingCart{

    private Date purchaseDate;
    private Product [] products;
    private double totalCost;
    private Person person;




    public ShoppingCart(Person person,Product [] products,double totalCost,Date date){
        this.products=products;
        this.totalCost=totalCost;
        this.person=person;
        this.purchaseDate=date;
    }
    public Date getPurchaseDate(){
        return purchaseDate;
    }

    public Person getPerson(){
        return this.person;
    }

    public void setProducts(Product[] products){
        this.products = products;
    }
    public Product[] getProducts(){
        return this.products;
    }

    public void setTotalCost(double totalCost){
        this.totalCost=totalCost;
    }

    public double getTotalCost(){
        return totalCost;
    }
   public String toString(){
        String output= "your products: ";
        for (int i=0;i< products.length;i++){
            output=output+(products[i].getProductName())+"\n";
        }
       output=output+ "total price is: "+(totalCost);
        return output;
   }
}
