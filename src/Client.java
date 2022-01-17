public class Client extends Person{
    private boolean clubCard;

    public Client(String name, String lastName,String userName,String password,boolean clubCard){
        super(name,lastName,userName,password);
        this.clubCard=clubCard;
    }

    public void  setClubCard(boolean clubCard){
        this.clubCard=clubCard;
    }
    public boolean getClubCard(){
        return clubCard;
    }

    public String toString(){
        String output=super.toString();
        return output;
    }
}
