import java.util.Scanner;


public class Employee extends Person{
    Scanner scanner=new Scanner(System.in);

    private String rank;

    public Employee(String name, String lastName,String userName,String password,String rank){
        super(name,lastName,userName,password);
        this.rank=rank;
    }

    public void setRank(String rank){
        this.rank=rank;
    }
    public String getRank(){
        return rank;
    }

    public String toString(){
        String output=super.toString();
        return output;
    }
}
