package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.RouteDAO;
import DTO.Route;

public class RouteBUS {
    private ArrayList<Route> list_Routes;
    private RouteDAO routeDAO;
    public RouteBUS() throws ClassNotFoundException, SQLException{
        list_Routes = new ArrayList<>();
        routeDAO = new RouteDAO();
        list_Routes = routeDAO.docDB();
    }
    public ArrayList<Route> getList_Routes() {
        return list_Routes;
    }
    public void setList_Routes(ArrayList<Route> list_Routes) {
        this.list_Routes = list_Routes;
    }
    public RouteDAO getRouteDAO() {
        return routeDAO;
    }
    public void setRouteDAO(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }
    public boolean checkRouteID(String id){
        for (Route route : list_Routes) {
            if(route.getRoute_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public boolean checkID(String id){
        Pattern pattern = Pattern.compile("[A-Z]{2}-[A-Z]{2}");
        Matcher matcher = pattern.matcher(id);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public void add(Route a) throws ClassNotFoundException, SQLException {
        list_Routes.add(a);
        RouteDAO dao = new RouteDAO();
        dao.add(a);
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        for (Route n : list_Routes) {
            if (n.getRoute_id().equals(id)) {
                list_Routes.remove(n);
                RouteDAO dao = new RouteDAO();
                dao.delete(id);
                return;
            }
        }
    }

    public void set(Route a) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Routes.size(); i++) {
            if (list_Routes.get(i).getRoute_id().equals(a.getRoute_id())) {
                list_Routes.set(i, a);
                RouteDAO dao = new RouteDAO();
                dao.set(a);
                return;
            }

        }
    }
}
