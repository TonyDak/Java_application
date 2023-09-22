package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.RoleFunc;

public class RoleFuncDAO {
    public ArrayList<RoleFunc> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ROLEFUNC");
        ArrayList<RoleFunc> rolfuns = new ArrayList<>();
        while(rs.next()){
            RoleFunc rolefun = new RoleFunc();
            rolefun.setRoleId(rs.getString("role_id"));
            rolefun.setFunctionId(rs.getString("function_id"));
            rolfuns.add(rolefun);
        }
        connection.close();
        return rolfuns;
    }
    public void add(RoleFunc a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO ROLEFUNC VALUES (";
        sql += "'" + a.getRoleId() + "',";
        sql += "'" + a.getFunctionId() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }
    public void set(RoleFunc a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE ROLEFUNC SET " ;
        sql += "role_id'" + a.getRoleId() + "',";
        sql += "function_id'" + a.getFunctionId()+ "',";
        sql += " WHERE function_id = '" + a.getFunctionId() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }
    public ArrayList<String> getRoleId() throws ClassNotFoundException, SQLException {
        ArrayList<String> dsID = new ArrayList<>();
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        try {
            String qry = "select ";
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                Integer Roles_id = rs.getInt("role_id");
                String Name = rs.getString("name");
                String Description = rs.getString("description");

                dsID.add(Roles_id + " (" + Name + ")" + " (" + Description + ")");
        
                }
                
            rs.close();
             connection.close();;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
            }
            return dsID;
        }
        public ArrayList<String> getFunctionId() throws ClassNotFoundException, SQLException {
            ArrayList<String> dsID = new ArrayList<>();
            Class.forName(DatabaseInfo.driverName);
                    Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

            System.out.println("Connected to SQL Server" + connection.getCatalog());
            Statement stmt = connection.createStatement();
            try {
                String qry = "select ";
                ResultSet rs = stmt.executeQuery(qry);
                while (rs.next()) {
                    Integer Funtions_id = rs.getInt("function_id");
                    String Name = rs.getString("name");
                    String Description = rs.getString("description");
    
                    dsID.add(Funtions_id + " (" + Name + ")" + " (" + Description + ")");
                    }
                    
                rs.close();
                 connection.close();;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
                }
                return dsID;
            }
            public void delete(int role_id, int funtion_id) throws ClassNotFoundException, SQLException {
                Class.forName(DatabaseInfo.driverName);
                        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

                System.out.println("Connected to SQL Server" + connection.getCatalog());
                Statement stmt = connection.createStatement();
                String sql = " DELETE FROM ROLEFUNC WHERE role_id='" + role_id + "' and function_id='" + funtion_id +"'";
                System.out.println(sql);
                stmt.executeUpdate(sql);
        
            }
            public void deleteallRole(String role_id) throws ClassNotFoundException, SQLException {
                Class.forName(DatabaseInfo.driverName);
                        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

                System.out.println("Connected to SQL Server" + connection.getCatalog());
                Statement stmt = connection.createStatement();
                String sql = " DELETE FROM ROLEFUNC where role_id='" + role_id + "'";
                System.out.println(sql);
                stmt.executeUpdate(sql);
        
            }

            public String[] getFunctionsName(String  roleId) throws ClassNotFoundException, SQLException {
                Class.forName(DatabaseInfo.driverName);
                        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

                System.out.println("Connected to SQL Server" + connection.getCatalog());
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("select f.name as name \n" +
                        "from ROLES r join ROLEFUNC rf on rf.role_id = r.role_id\n" +
                        "join FUNTIONS f on rf.function_id = f.function_id\n" +
                        "where r.role_id = "+roleId);
                ArrayList<String> temp = new ArrayList<>();
                String[] functions;
                while(rs.next()){
                    String test = rs.getString("name");
                    temp.add(test);
                }
                functions = new String[temp.size()];
                for (int i=0; i< temp.size(); i++){
                    functions[i] = temp.get(i);
                }
                connection.close();
                return functions;
            }

            public ArrayList<RoleFunc> docDB2() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SERVICEFUNC");
        ArrayList<RoleFunc> rolfuns = new ArrayList<>();
        while(rs.next()){
            RoleFunc rolefun = new RoleFunc();
            rolefun.setRoleId(rs.getString("type_id"));
            rolefun.setFunctionId(rs.getString("description"));
            rolfuns.add(rolefun);
        }
        connection.close();
        return rolfuns;
    }
    public void add2(RoleFunc a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO SERVICEFUNC VALUES (";
        sql += "'" + a.getRoleId() + "',";
        sql += "'" + a.getFunctionId() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }
    public void set2(RoleFunc a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE SERVICEFUNC SET " ;
        sql += "type_id'" + a.getRoleId() + "',";
        sql += "description'" + a.getFunctionId()+ "',";
        sql += " WHERE description = '" + a.getFunctionId() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }
    public ArrayList<String> getRoleId2() throws ClassNotFoundException, SQLException {
        ArrayList<String> dsID = new ArrayList<>();
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        try {
            String qry = "select ";
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                Integer Roles_id = rs.getInt("type_id");
                String Name = rs.getString("type_name");
                String Description = rs.getString("type_price");

                dsID.add(Roles_id + " (" + Name + ")" + " (" + Description + ")");
        
                }
                
            rs.close();
             connection.close();;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
            }
            return dsID;
        }
        public ArrayList<String> getFunctionId2() throws ClassNotFoundException, SQLException {
            ArrayList<String> dsID = new ArrayList<>();
            Class.forName(DatabaseInfo.driverName);
                    Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

            System.out.println("Connected to SQL Server" + connection.getCatalog());
            Statement stmt = connection.createStatement();
            try {
                String qry = "select ";
                ResultSet rs = stmt.executeQuery(qry);
                while (rs.next()) {
                    Integer Funtions_id = rs.getInt("description");
                    String Name = rs.getString("name");
                    String Description = rs.getString("price");
    
                    dsID.add(Funtions_id + " (" + Name + ")" + " (" + Description + ")");
                    }
                    
                rs.close();
                 connection.close();;
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
                }
                return dsID;
            }
            public void delete2(int role_id, int funtion_id) throws ClassNotFoundException, SQLException {
                Class.forName(DatabaseInfo.driverName);
                        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

                System.out.println("Connected to SQL Server" + connection.getCatalog());
                Statement stmt = connection.createStatement();
                String sql = " DELETE FROM SERVICEFUNC WHERE type_id='" + role_id + "' and description='" + funtion_id +"'";
                System.out.println(sql);
                stmt.executeUpdate(sql);
        
            }
            public void deleteallRole2(String role_id) throws ClassNotFoundException, SQLException {
                Class.forName(DatabaseInfo.driverName);
                        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

                System.out.println("Connected to SQL Server" + connection.getCatalog());
                Statement stmt = connection.createStatement();
                String sql = " DELETE FROM SERVICEFUNC where type_id='" + role_id + "'";
                System.out.println(sql);
                stmt.executeUpdate(sql);
        
            }

            public String[] getFunctionsName2(String  roleId) throws ClassNotFoundException, SQLException {
                Class.forName(DatabaseInfo.driverName);
                        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

                System.out.println("Connected to SQL Server" + connection.getCatalog());
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("select f.name as name \n" +
                        "from SERVICEFUNC r join SERVICEFUNC rf on rf.type_id = r.type_id\n" +
                        "join SERVICE f on rf.description = f.description\n" +
                        "where r.type_id = "+roleId);
                ArrayList<String> temp = new ArrayList<>();
                String[] functions;
                while(rs.next()){
                    String test = rs.getString("name");
                    temp.add(test);
                }
                functions = new String[temp.size()];
                for (int i=0; i< temp.size(); i++){
                    functions[i] = temp.get(i);
                }
                connection.close();
                return functions;
            }
        }

    