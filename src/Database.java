import java.sql.*;

public class Database {
    private static Connection dbConnection;

    //This method executes a query and returns the number of albums for the artist with name artistName
    public int getTitles(String artistName) {
        int titleNum = 0;
        String SQL = "SELECT COUNT(a.name)"
                + " FROM artist a"
                + " INNER JOIN album b on b.artistid = a.artistid"
                + " WHERE a.name =?";


        try {
            PreparedStatement pstmt = dbConnection.prepareStatement(SQL);
            pstmt.setString(1, artistName);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            titleNum = Integer.parseInt(rs.getString(1));

            return titleNum;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //Implement this method
        //Prevent SQL injections!
        return titleNum;
    }

    // This method establishes a DB connection & returns a boolean status
    public boolean establishDBConnection() {

        //Implement this method
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
            return dbConnection.isValid(5);
        } catch (Exception e) {
            //5 sec timeout
            e.printStackTrace();
            return false;
        }
    }
}