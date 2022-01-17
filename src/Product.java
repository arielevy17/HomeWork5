public class Product {
    private String productName;
    private boolean inStock;
    private int cost;

    public Product(String product,boolean inStock,int cost){
        this.productName =product;
        this.inStock=inStock;
        this.cost=cost;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getProductName(){
        return productName;
    }

    public void setInStock(boolean inStock){
        this.inStock=inStock;
    }
    public boolean getInStock(){
        return inStock;
    }

    public void setCost(int cost){
        this.cost=cost;
    }
    public int getCost(){
        return cost;
    }

    public String toString(){
        String output= getProductName();
        return output;
    }
}
