public abstract class Person {
    private String name;
    private String lastName;
    private String userName;
    private String password;

    public Person(String name,String lastName,String userName,String password){
        this.name=name;
        this.lastName=lastName;
        this.userName=userName;
        this.password=password;
    }

    public void  setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

    public void  setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getLastName(){
        return this.lastName;
    }

    public void  setUserName(String userName){
        this.userName=userName;
    }
    public String getUserName(){
        return this.userName;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }

    public String toString(){
        String output="full name :"+name+" "+lastName;
        return output;
    }
}
