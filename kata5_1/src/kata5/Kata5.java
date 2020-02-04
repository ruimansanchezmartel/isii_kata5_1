
package kata5;


public class Kata5 {


    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:\\Users\\Usuario\\Documents\\NetBeansProjects\\DB_SQLite\\kata5.db";
        DataBase db = new DataBase(url);
        
        db.open();
        
        db.selectPeople();
        
        People people = new People("Javiera", "Mena", "PROD");
        db.insertPeople(people);
        
        System.out.println("*********");
        db.selectPeople();
        
        db.close();        
    }
    
}
