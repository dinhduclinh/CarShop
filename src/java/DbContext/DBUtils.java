/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbContext;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

    public static Connection makeConnection() throws Exception {
        Connection cn = null;
        String IP = "localhost";
        String port = "1433";
        String uid = "sa";
        String pwd = "123";
        String db = "Car";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + ":" + port
                + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }
}
