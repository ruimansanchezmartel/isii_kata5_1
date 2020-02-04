
package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


class DataBase {
    private final String url;
    private Connection connection = null;

    public DataBase(String url) {
        this.url = url;
    }

    void open() {
        try {
            this.connection = DriverManager.getConnection(this.url);
            System.out.println("Base de datos abierta");
        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR DataBase::open (SQLException): " + ex);
        }
    }

    void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
                System.out.println("Base de datos cerrada");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR DataBase::close (SQLException): " + ex);
        }
    }

    void selectPeople() {
        String select = "select * from PEOPLE";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            
            System.out.println("ID\tNAME\tAPELLIDO\tDEPARTAMENTO");
            
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + " \t "
                        + resultSet.getString("NAME") + " \t "
                        + resultSet.getString("APELLIDO") + " \t " 
                        + resultSet.getString("DEPARTAMENTO") + " \t "                        
                        );
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR DataBase::select (SQLException): " + ex);
        }
    }

    void insertPeople(People people) {
        String sql = "insert into PEOPLE (NAME, APELLIDO, DEPARTAMENTO) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, people.getName());
            preparedStatement.setString(2, people.getApellido());
            preparedStatement.setString(3, people.getDepartamento());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR DataBase::insertPeople (SQLException): " + ex);
        }
    }
    
}
