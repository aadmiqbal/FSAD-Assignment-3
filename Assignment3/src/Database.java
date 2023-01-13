import java.sql.*;

public class Database {

    //This method executes a query and returns the number of albums for the artist with name artistName
    public int getTitles(String artistName) {
       int titleNum = 0;
        //Implement this method
        try {
            Class.forName("org.postgresql.Driver");
            String sql = "SELECT COUNT(*) FROM album INNER JOIN artist ON album.artistid" +
                    " = artist.artistid WHERE artist.name = ?;";
            Connection con = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.clearParameters();
            preparedStatement.setString(1, artistName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            titleNum = resultSet.getInt(1);
            resultSet.close();
            preparedStatement.close();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //Prevent SQL injections!
        return titleNum;
    }

    // This method establishes a DB connection & returns a boolean status
    public boolean establishDBConnection() {
        //Implement this method
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(Credentials.URL,Credentials.USERNAME,Credentials.PASSWORD);
            con.isValid(5);
        }catch (Exception e){
            e.printStackTrace();
            return false;}
        //5 sec timeout
        return true;
    }
}