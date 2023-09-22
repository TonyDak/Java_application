package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ServiceDAO;
import DTO.ServiceDTO;

public class ServiceBUS {
    private ArrayList<ServiceDTO> list_ServiceDTOs;
    private ServiceDAO serviceDAO;
    
    public ServiceBUS() throws ClassNotFoundException, SQLException {
        list_ServiceDTOs = new ArrayList<>();
        serviceDAO = new ServiceDAO();
        list_ServiceDTOs = serviceDAO.docDB();
    }
    
    public ArrayList<ServiceDTO> getList_ServiceDTOs() {
        return list_ServiceDTOs;
    }
    
    public void setList_ServiceDTOs(ArrayList<ServiceDTO> list_ServiceDTOs) {
        this.list_ServiceDTOs = list_ServiceDTOs;
    }
    
    
    public boolean checkService(String id){
        for (ServiceDTO ServiceDTO : list_ServiceDTOs) {
            if(ServiceDTO.getDescription().equals(id)){
                return false;
            }
        }
        return true;
    }
    public void add(ServiceDTO a) throws ClassNotFoundException, SQLException {
        list_ServiceDTOs.add(a);
        ServiceDAO dao = new ServiceDAO();
        dao.add(a);
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        for (ServiceDTO n : list_ServiceDTOs) {
            if (n.getDescription().equals(id)) {
                list_ServiceDTOs.remove(n);
                ServiceDAO dao = new ServiceDAO();
                dao.delete(id);
                return;
            }
        }
    }

    public void set(ServiceDTO a) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_ServiceDTOs.size(); i++) {
            if (list_ServiceDTOs.get(i).getDescription().equals(a.getDescription())) {
                list_ServiceDTOs.set(i, a);
                ServiceDAO dao = new ServiceDAO();
                dao.set(a);
                return;
            }

        }
    }
    public String CreateType2PT(){
        
        ArrayList<ServiceDTO> serviceDTOs = new ArrayList<>();
        for(ServiceDTO serviceDTO : list_ServiceDTOs){
            if(serviceDTO.getDescription().startsWith("ECO")){
                serviceDTOs.add(serviceDTO);
            }
        }
        if(serviceDTOs == null || serviceDTOs.size() == 0){
            return "ECO001";
        }
        ServiceDTO ServiceDTO = new ServiceDTO();
        ServiceDTO = serviceDTOs.get(serviceDTOs.size()-1);
        String id = ServiceDTO.getDescription();
        String numberPart = id.substring(3); // lấy phần số sau chuỗi "KH"
        int number = Integer.parseInt(numberPart); // chuyển phần số thành kiểu int
        number++; // tăng giá trị số lên 1 đơn vị
        return String.format("ECO%03d", number);
    }
    public String CreateServiceDTOTG(){
        
        ArrayList<ServiceDTO> serviceDTOs = new ArrayList<>();
        for(ServiceDTO serviceDTO : list_ServiceDTOs){
            if(serviceDTO.getDescription().startsWith("BUS")){
                serviceDTOs.add(serviceDTO);
            }
        }
        if(serviceDTOs == null || serviceDTOs.size() == 0){
            return "BUS001";
        }
        ServiceDTO ServiceDTO = new ServiceDTO();
        ServiceDTO = serviceDTOs.get(serviceDTOs.size()-1);
        String id = ServiceDTO.getDescription();
        String numberPart = id.substring(3); // lấy phần số sau chuỗi "KH"
        int number = Integer.parseInt(numberPart); // chuyển phần số thành kiểu int
        number++; // tăng giá trị số lên 1 đơn vị
        return String.format("BUS%03d", number);
    }
    public ServiceDAO getServiceDAO() {
        return serviceDAO;
    }

    public void setServiceDAO(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }
}
