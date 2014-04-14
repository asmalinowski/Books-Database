import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BooksDatabase {
    
    
    static final String DATABASE_URL = ("jdbc:mysql://localhost:3306/books?" +
"user=asm&password=school");
        
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try 
        {
            connection = DriverManager.getConnection(DATABASE_URL);
            
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery( "SELECT LastName, FirstName, Copyright, Titles.ISBN, Title "
     + "FROM Authors INNER JOIN AuthorISBN "
     + "ON Authors.AuthorID=AuthorISBN.AuthorID "
     + "INNER JOIN Titles "
     + "ON AuthorISBN.ISBN=Titles.ISBN "
     + "WHERE LastName = 'dietel' " );             
            
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("Authors Table of Books Database:\n");
            
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf( "%-8s\t", metaData.getColumnName(i));
            System.out.println();
            
            while ( resultSet.next())
            {
                for ( int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                System.out.println();
            }
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex)
        {
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch (Exception exception)
            {
            }
            }
        }
            }
