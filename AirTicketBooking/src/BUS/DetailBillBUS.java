package BUS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import DAO.DetailBillDAO;
import DAO.FlightDAO;
import DTO.Bill;
import DTO.DetailBill;
import DTO.Flight;
import DTO.Ticket;
import DTO.Type;

public class DetailBillBUS {
    private ArrayList<DetailBill> list_DetailBills;
    private DetailBillDAO detailBillDAO;
    private BillBUS billBUS = new BillBUS();
    private ArrayList<Bill> list_Bills = new ArrayList<>();
    private TicketBUS ticketBUS = new TicketBUS();
    private FlightBUS flightBUS = new FlightBUS();
    private FlightDAO flightDAO = new FlightDAO();
    private TypeBUS typeBUS = new TypeBUS();
    private ArrayList<Type> list_Types = new ArrayList<>();

    public DetailBillBUS() throws ClassNotFoundException, SQLException{
        list_DetailBills = new ArrayList<>();
        detailBillDAO = new DetailBillDAO();
        list_DetailBills = detailBillDAO.docDB();
    }
    public ArrayList<DetailBill> getList_DetailBills() {
        return list_DetailBills;
    }
    public void setList_DetailBills(ArrayList<DetailBill> list_DetailBills) {
        this.list_DetailBills = list_DetailBills;
    }
    public DetailBillDAO getDetailBillDAO() {
        return detailBillDAO;
    }
    public void setDetailBillDAO(DetailBillDAO detailBillDAO) {
        this.detailBillDAO = detailBillDAO;
    }
    public void add(DetailBill a) throws ClassNotFoundException, SQLException {
        list_DetailBills.add(a);
        detailBillDAO.add(a);
    }

    public void delete2(String invoiceID, String typeID) throws ClassNotFoundException, SQLException {
        for (DetailBill n :list_DetailBills) {
            if (n.getBill_id().equals(invoiceID) && n.getType_id().equals(typeID)) {
               list_DetailBills.remove(n);
                detailBillDAO.delete2(invoiceID, typeID);
                return;
            }
        }
    }
    public void delete(String invoiceID) throws ClassNotFoundException, SQLException {
        for (DetailBill n :list_DetailBills) {
            if (n.getBill_id().equals(invoiceID)) {
               list_DetailBills.remove(n);
                detailBillDAO.delete(invoiceID);
                return;
            }
        }
    }

    public void set(DetailBill s) throws ClassNotFoundException, SQLException {
        for (int i = 0; i <list_DetailBills.size(); i++) {
            if (list_DetailBills.get(i).getType_id().equals(s.getType_id()) &&list_DetailBills.get(i).getBill_id().equals(s.getBill_id())) {
               list_DetailBills.set(i, s);
                detailBillDAO.set(s);
                return;
            }
        }

    }
    public boolean checkTypeAndInvoiceID(String invoiceID, String typeID) {
        getList_DetailBills();
        for (DetailBill iDetail :list_DetailBills) {
            if (iDetail.getType_id().equals(typeID) && iDetail.getBill_id().equals(invoiceID)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkInvoiceID(String id) {
        list_Bills = billBUS.getList_Bills();
        for (Bill sb : list_Bills) {
            if (sb.getBill_id().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTypeId(String typeID) {
        list_Types = typeBUS.getList_Types();

        for (Type type : list_Types) {
            if (type.getType_id().equals(typeID)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<DetailBill> searchIDetailDTOsByInvoiceId(String invoiceID) {
        ArrayList<DetailBill> iDsearchList = new ArrayList<>();
        for (DetailBill tk :list_DetailBills) {
            if (tk.getBill_id().toUpperCase().contains(invoiceID.toUpperCase())) {
                iDsearchList.add(tk);
            }
        }
        return iDsearchList;
    }

    public boolean updateAmountFlight(String invoiceId, String typeId, int amount) throws ClassNotFoundException, SQLException {
        ArrayList<Ticket> tkList = ticketBUS.getList_Tickets();
        System.out.println("amount = " + amount);
        ArrayList<Flight> cbList = flightBUS.getList_Flights();

        for (Ticket tk : tkList) {
            if (tk.getBill_id().equals(invoiceId) && tk.getType_id().equals(typeId)) {
                String flightId = tk.getFlight_id();
                for (Flight cb : cbList) {
                    if (cb.getFlight_id().equals(flightId)) {
                        if (typeId.equals("ECO")) {
                            int currAmount = Integer.parseInt(cb.getBookedECOSeats().split("/")[0]);
                            currAmount += amount;
                            if (currAmount > Integer.parseInt(cb.getBookedECOSeats().split("/")[1])) {
                                return false;
                            } else {
                                String seat = String.valueOf(currAmount) + "/" + cb.getBookedECOSeats().split("/")[1];
                                flightDAO.updateAmountECO(flightId, seat);
                                return true;
                            }
                        } else if (typeId.equals("BUS")) {
                            int currAmount = Integer.parseInt(cb.getBookedBUSSeats().split("/")[0]);
                            currAmount += amount;
                            if (currAmount > Integer.parseInt(cb.getBookedBUSSeats().split("/")[1])) {
                                return false;
                            } else {
                                String seat = String.valueOf(currAmount) + "/" + cb.getBookedBUSSeats().split("/")[1];
                                flightDAO.updateAmountBUS(flightId, seat);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public int getOldAmount(String invoiceId, String typeId) {
        for (DetailBill iD :list_DetailBills) {
            if (iD.getBill_id().equals(invoiceId) && iD.getType_id().equals(typeId)) {
                return iD.getAmount();
            }
        }
        return 0;
    }

    public int countTicketByTypeNInvoiceId(String invoiceid, String typeid) {
        ArrayList<Ticket> tkList = ticketBUS.getList_Tickets();

        int count = 0;
        for (Ticket tk : tkList) {
            if (tk.getBill_id().equals(invoiceid) && tk.getType_id().equals(typeid)) {
                count += 1;
            }
        }
        return count;
    }

    public ArrayList<DetailBill> searchByMonthAndYear(String month, String year) {
        
        list_Bills = billBUS.getList_Bills();

        ArrayList<DetailBill> iDsearchList = new ArrayList<>();

        for (Bill hd : list_Bills) {
            String hdYear = hd.getBill_date().split("-")[0];
            String hdMonth = hd.getBill_date().split("-")[1];
            if (hdYear.equals(year) && hdMonth.equals(month)) {
                String maHd = hd.getBill_id();
                for (DetailBill iD :list_DetailBills) {
                    if (iD.getBill_id().equals(maHd)) {
                        iDsearchList.add(iD);
                    }
                }
            }
        }
        return iDsearchList;
    }

    public static Comparator<DetailBill> priceComparatorAsc = (DetailBill i1, DetailBill i2) -> {
        double price1 = i1.getTotal_price();
        double price2 = i2.getTotal_price();

        // For ascending order
        return (int) (price1 - price2);
    };

    public static Comparator<DetailBill> priceComparatordesc = (DetailBill i1, DetailBill i2) -> {
        double price1 = i1.getTotal_price();
        double price2 = i2.getTotal_price();

        // For descending order
        return (int) (price2 - price1);
    };
    
    public long sotien(int sl, String typeId) {
    
        list_Types = typeBUS.getList_Types();
        int price = 1;
        for(Type typ : list_Types) {
            if (typ.getType_id().equals(typeId))
            {
                price = (int) typ.getType_price();
            }
        }
        return price*sl;
    }
}
