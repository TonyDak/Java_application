package GUI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import BUS.AirportBUS;
import BUS.BillBUS;
import BUS.FlightBUS;
import BUS.FlightScheduleBUS;
import BUS.PlaneBUS;
import BUS.RouteBUS;
import BUS.TicketBUS;
import DTO.Airport;
import DTO.Bill;
import DTO.Flight;
import DTO.FlightSchedule;
import DTO.Plane;
import DTO.Route;
import DTO.Ticket;


public class FlightManagerGUI extends JPanel {

    private JTextField textBar, textfligt_id, textakeOffDay, textlandingDay ;
    // private JButton searchButton;
    private JTable FlightTable;
    JComboBox<String> positionComboBox;
    
    private FlightBUS flightBUS = new FlightBUS();
    private RouteBUS routeBUS=  new RouteBUS();
    private PlaneBUS planeBUS=   new PlaneBUS();
    private FlightScheduleBUS flightScheduleBUS=  new FlightScheduleBUS();
    ArrayList<Flight> list_Flights = flightBUS.getList_Flights();
    ArrayList<Route> list_Routes = routeBUS.getList_Routes();
    ArrayList<Plane> list_Planes = planeBUS.getList_Planes();
    ArrayList<FlightSchedule> list_FlightSchedules = flightScheduleBUS.getList_FlightSchedules();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();


    public FlightManagerGUI() throws ClassNotFoundException, SQLException {
        // JFrame frame = new JFrame();
        setSize(800, 460);
        setVisible(true);

        // flightBUS = new FlightBUS();
        // ArrayList<Flight> list_Flights = flightBUS.getList_Flights();

        // routeBUS = new RouteBUS();
        // ArrayList<Route> list_Routes = routeBUS.getList_Routes();
        // planeBUS = new PlaneBUS();
        // ArrayList<Plane> list_Planes = planeBUS.getList_Planes();
        // flightScheduleBUS = new FlightScheduleBUS();
        // ArrayList<FlightSchedule> list_FlightSchedules = flightScheduleBUS.getList_FlightSchedules();

        // set Wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel();

        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        // set size for panel
        panelTop.setPreferredSize(new Dimension(1100, 60));
        panelBot.setPreferredSize(new Dimension(1100, 350));
        panelCenter.setPreferredSize(new Dimension(900, 350));
        panelRight.setPreferredSize(new Dimension(200, 250));

        // add panel into wrapper
        wrapperPanel.add(panelTop, BorderLayout.NORTH);
        wrapperPanel.add(panelBot, BorderLayout.SOUTH);
        wrapperPanel.add(panelCenter, BorderLayout.CENTER);
        wrapperPanel.add(panelRight, BorderLayout.EAST);

        // add wrapper into frame
        add(wrapperPanel);

        // set label for panelTop
        JLabel labelFlightScheduleEm = new JLabel();
        JLabel labelFlightScheduleInfo = new JLabel();
        labelFlightScheduleEm.setText("QUẢN LÝ CHUYẾN BAY");

        // set font for label
        labelFlightScheduleEm.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // Set positions of labels
        labelFlightScheduleEm.setHorizontalAlignment(JLabel.CENTER);
        labelFlightScheduleInfo.setHorizontalAlignment(JLabel.LEFT);


        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelFlightScheduleEm);
        panelTop.add(labelFlightScheduleInfo);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        // create label and jtextfiled in panelCenter
        JLabel flight_id = new JLabel("Mã chuyến bay:");
        flight_id.setFont(fontLabel);
        JLabel route_id = new JLabel("Lộ trình:");
        route_id.setFont(fontLabel);
        JLabel plane_id = new JLabel("Máy bay:");
        plane_id.setFont(fontLabel);
        JLabel flightSchedule_id = new JLabel("Lịch bay: ");
        flightSchedule_id.setFont(fontLabel);
        JLabel takeOffDay = new JLabel("Thời gian đi:");
        takeOffDay.setFont(fontLabel);
        JLabel landingDay = new JLabel("Thời gian đến:");
        landingDay.setFont(fontLabel);
        JLabel timedep = new JLabel("Giờ cất cánh:");
        timedep.setFont(fontLabel);
        JLabel totalSeats = new JLabel("Tổng ghế:");
        totalSeats.setFont(fontLabel);
        JLabel Eco = new JLabel("Thường (đã đặt)");
        Eco.setFont(fontLabel);
        JLabel Bus = new JLabel("Thương gia (đã đặt)");
        Bus.setFont(fontLabel);
        JLabel price_eco = new JLabel("Giá tiền ECO (VND):");
        price_eco.setFont(fontLabel);
        JLabel price_bus = new JLabel("Giá tiền BUS (VND):");
        price_bus.setFont(fontLabel);
        // text
        textfligt_id = new JTextField(10);
        textfligt_id.setFont(fontTextField);
        textakeOffDay = new JTextField(10);
        textakeOffDay.setFont(fontTextField);
        textakeOffDay.setEditable(false);
        textlandingDay = new JTextField(10);
        textlandingDay.setFont(fontTextField);  
        textlandingDay.setEditable(false);
        JTextField timeTextField = new JTextField();
        timeTextField.setFont(fontTextField);
        timeTextField.setEditable(false);
        JTextField texttotalSeats = new JTextField();
        texttotalSeats.setFont(fontLabel);
        texttotalSeats.setEnabled(false);
        JTextField textBus = new JTextField();
        textBus.setFont(fontLabel);
        textBus.setEnabled(false);
        JTextField textEco = new JTextField();
        textEco.setFont(fontLabel);
        textEco.setEnabled(false);
        JTextField textprice_eco = new JTextField();
        textprice_eco.setFont(fontLabel);
        JTextField textprice_bus = new JTextField();
        textprice_bus.setFont(fontLabel);
       

        JComboBox<String> route_idcb = new JComboBox<>();
        route_idcb.addItem("--Chọn lộ trình--");
        route_idcb.setFont(fontTextField);
        for (Route route : list_Routes) {
            route_idcb.addItem(route.getRoute_id()+"("+route.getOrigin()+"-"+route.getDestination()+")");
        }
        JComboBox<String> plane_idcb = new JComboBox<>();
        plane_idcb.addItem("--Chọn máy bay--");
        plane_idcb.setFont(fontTextField);
        for (Plane plane : list_Planes) {
            plane_idcb.addItem(plane.getPlane_id());
        }
        plane_idcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = plane_idcb.getSelectedItem().toString();
                    for (Plane plane : list_Planes) {
                        if(plane.getPlane_id().equals(selectedOption)){
                            int total = plane.getEco_seats() +plane.getBusi_seats();
                            texttotalSeats.setText(Integer.toString(total));
                            // textEco.setText(flight.getBookedECOSeats()+"/"+Integer.toString(plane.getEco_seats()));
                            // textBus.setText(flight.getBookedBUSSeats()+"/"+Integer.toString(plane.getBusi_seats()));
                        }
                    }
                }
            }
        });
        JComboBox<String> flightSchedule_idcb = new JComboBox<>();
        flightSchedule_idcb.addItem("--Chọn lịch trình--");
        flightSchedule_idcb.setFont(fontTextField);
        for (FlightSchedule flightSchedule : list_FlightSchedules) {
            flightSchedule_idcb.addItem(flightSchedule.getFlight_schedule_id());
        }
        flightSchedule_idcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) flightSchedule_idcb.getSelectedItem();
                    for (FlightSchedule flightSchedule : list_FlightSchedules) {
                        if(flightSchedule.getFlight_schedule_id().equals(selectedOption)){
                            textakeOffDay.setText(flightSchedule.getDepartureDate());
                            textlandingDay.setText(flightSchedule.getArrivalDate());
                            timeTextField.setText(flightSchedule.getBoartime());
                        }
                    }
                    
                    
                    for (Flight f : list_Flights){
                        if(f.getFlightSchedule_id().equals(selectedOption)){
                            for (int i = 0; i < plane_idcb.getItemCount(); i++) {
                                if(plane_idcb.getItemAt(i).toString().equals(f.getPlane_id())){
                                    plane_idcb.remove(i);;
                                }
                            }
                    }
                }
                        
                    
                }
            }
        });
        AirportBUS airportBUS = new AirportBUS();

        route_idcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) e.getItem();
                    ArrayList<Airport> list_Airports = airportBUS.getList_Airports();
                    flightSchedule_idcb.removeAllItems();
                    for (Route route : list_Routes) {
                        if (selectedOption.equals(route.getRoute_id()+"("+route.getOrigin()+"-"+route.getDestination()+")")) {
                            for (FlightSchedule flightSchedule : list_FlightSchedules) {
                                boolean check1 = false;
                                boolean check2 = false;
                                for (Airport airport : list_Airports) {
                                    if(flightSchedule.getDepartureAirport().equals(airport.getAirport_id())&&route.getOrigin().equals(airport.getCity())) check1=true;
                                    if(flightSchedule.getArrivalAirport().equals(airport.getAirport_id())&&route.getDestination().equals(airport.getCity())) check2=true;
                                }
                                if(check1 ==true && check2==true){
                                    flightSchedule_idcb.addItem(flightSchedule.getFlight_schedule_id());
                                }
                            }
                        }else{
                            flightSchedule_idcb.addItem("--Chọn lịch trình--");

                        }
                    }
                }
            }
        });
        // add label and text field into panelCenter
        panelCenter.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setPreferredSize(new Dimension(600,HEIGHT));
        panel1.setLayout(new GridLayout(9,2,5,5));

        panel2.setPreferredSize(new Dimension(200,HEIGHT));
        panel2.setLayout(new GridLayout(7,2,5,5));
        panelCenter.add(panel1,BorderLayout.WEST);
        panelCenter.add(panel2,BorderLayout.EAST);

       
        panel1.add(flight_id);
        panel1.add(textfligt_id);

        panel1.add(route_id);
        panel1.add(route_idcb);

        panel1.add(plane_id);
        panel1.add(plane_idcb);

       
        panel1.add(flightSchedule_id);
        panel1.add(flightSchedule_idcb);

        panel1.add(takeOffDay);
        panel1.add(textakeOffDay);

        
        panel1.add(landingDay);
        panel1.add(textlandingDay);

        panel1.add(timedep);
        panel1.add(timeTextField);

        panel1.add(price_eco);
        panel1.add(textprice_eco);

        panel1.add(price_bus);
        panel1.add(textprice_bus);

        panel2.add(totalSeats);
        panel2.add(texttotalSeats);

        panel2.add(Eco);
        panel2.add(textEco);

        panel2.add(Bus);
        panel2.add(textBus);

        

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)", "Giá tiền ECO","Giá tiền BUS"}, 0);
        for (Flight flight : list_Flights) {
            for (FlightSchedule flightSchedule : list_FlightSchedules) {
                if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats(),flight.getPrice_eco(),flight.getPrice_bus()});
            }
        }
        FlightTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        
        FlightTable.setRowHeight(30);
        Font font = new Font("Arial", Font.PLAIN, 16);
        FlightTable.setFont(font);
       
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i <= 10; i++) {
            FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        FlightTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable

                    String id = target.getValueAt(row, 0).toString();
                    textfligt_id.setText(id);
                    String idroute = target.getValueAt(row, 1).toString();
                    for (int i = 0; i < route_idcb.getItemCount(); i++) {
                        if(route_idcb.getItemAt(i).toString().startsWith(idroute)){
                            route_idcb.setSelectedIndex(i);
                        }
                    }
                    String idplane = target.getValueAt(row, 2).toString();
                    for (int i = 0; i < plane_idcb.getItemCount(); i++) {
                        if(plane_idcb.getItemAt(i).toString().startsWith(idplane)){
                            plane_idcb.setSelectedIndex(i);
                        }
                    }
                    String idbus = target.getValueAt(row, 3).toString();
                    for (int i = 0; i < flightSchedule_idcb.getItemCount(); i++) {
                        if(flightSchedule_idcb.getItemAt(i).toString().startsWith(idbus)){
                            flightSchedule_idcb.setSelectedIndex(i);
                        }
                    }
                    String iddepart = target.getValueAt(row, 4).toString();
                    textakeOffDay.setText(iddepart);
                    String idreturn = target.getValueAt(row, 5).toString();
                    textlandingDay.setText(idreturn);
                    String seatTotal = target.getValueAt(row, 6).toString();
                    texttotalSeats.setText(seatTotal);
                    for (Flight flight : list_Flights) {
                        if(flight.getFlight_id().equals(id)){
                            for (Plane plane : list_Planes) {
                                textEco.setText(flight.getBookedECOSeats()+"/"+Integer.toString(plane.getEco_seats()));
                                textBus.setText(flight.getBookedBUSSeats()+"/"+Integer.toString(plane.getBusi_seats()));
                            }
                        }
                    }
                    String peco = target.getValueAt(row, 9).toString();
                    textprice_eco.setText(peco);
                    String pbus = target.getValueAt(row, 10).toString();
                    textprice_bus.setText(pbus);
                }
            }
        });

        // create buttons for panelRight
        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(textfligt_id.getText().isEmpty() == true || route_idcb.getSelectedItem().toString().equals("--Chọn lộ trình--") == true
                || plane_idcb.getSelectedItem().toString().equals("--Chọn máy bay--") || flightSchedule_idcb.getSelectedItem().toString().equals("--Chọn lịch trình--")
                || textprice_eco.getText().isEmpty() == true || textprice_bus.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin trước khi thêm");
                }else{
                    if(flightBUS.checkID(textfligt_id.getText())==false&&flightBUS.checkIDplane(plane_idcb.getSelectedItem().toString())==false&&flightBUS.checkIDflightschedule(flightSchedule_idcb.getSelectedItem().toString())==false){
                        if(flightBUS.checkID(textfligt_id.getText())==false){
                            JOptionPane.showMessageDialog(null, "Mã chuyến bay đã tồn tại");
                        }
                        if(flightBUS.checkIDplane(plane_idcb.getSelectedItem().toString())==false){
                            JOptionPane.showMessageDialog(null, "Mã máy bay bị trùng lịch");
                        }
                        if(flightBUS.checkIDflightschedule(flightSchedule_idcb.getSelectedItem().toString())==false){
                            JOptionPane.showMessageDialog(null, "Mã lịch trình bị trùng lịch");
                        }
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm chuyến bay!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            Flight flight2 = new Flight(textfligt_id.getText(), route_idcb.getSelectedItem().toString().substring(0, 7), plane_idcb.getSelectedItem().toString(), flightSchedule_idcb.getSelectedItem().toString(), Integer.parseInt(texttotalSeats.getText()), "0", "0",textprice_eco.getText(),textprice_bus.getText());
                            try {
                                flightBUS.add(flight2);
                                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)", "Giá tiền ECO","Giá tiền BUS"}, 0);
                                for (Flight flight : list_Flights) {
                                    for (FlightSchedule flightSchedule : list_FlightSchedules) {
                                        if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                                        tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats(),flight.getPrice_eco(),flight.getPrice_bus()});
                                    }
                                }
                                FlightTable.setModel(tableModel);
                                for (int i = 0; i <= 10; i++) {
                                    FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                                }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                }
            }
        });
        JButton delButton = new JButton("Xóa");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(textfligt_id.getText().isEmpty() == true || route_idcb.getSelectedItem().toString().equals("--Chọn lộ trình--") == true
                || plane_idcb.getSelectedItem().toString().equals("--Chọn máy bay--") || flightSchedule_idcb.getSelectedItem().toString().equals("--Chọn lịch trình--")
                || textprice_eco.getText().isEmpty() == true || textprice_bus.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin trước khi xóa");
                }else{
                    if(flightBUS.checkID(textfligt_id.getText())==true){
                        JOptionPane.showMessageDialog(null, "Mã chuyến bay không có");
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa chuyến bay!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                flightBUS.delete(textfligt_id.getText());
                                BillBUS billBUS = new BillBUS();
                                ArrayList<Bill> list_Bills = billBUS.getList_Bills();
                                TicketBUS ticketBUS = new TicketBUS();
                                ArrayList<Ticket> list_Tickets = ticketBUS.getList_Tickets();
                                for (int i = 0; i < list_Bills.size(); i++) {
                                    boolean check = false;
                                    for (Ticket ticket : list_Tickets) {
                                        if(list_Bills.get(i).getBill_id().equals(ticket.getBill_id())){
                                            check = true;
                                        }
                                    }
                                    if(check == false){
                                        try {
                                            billBUS.delete(list_Bills.get(i).getBill_id());
                                        } catch (ClassNotFoundException | SQLException e1) {
                                            // TODO Auto-generated catch block
                                            e1.printStackTrace();
                                        }
                                    }
                                }
                                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)", "Giá tiền ECO","Giá tiền BUS"}, 0);
                                for (Flight flight : list_Flights) {
                                    for (FlightSchedule flightSchedule : list_FlightSchedules) {
                                        if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                                        tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats(),flight.getPrice_eco(),flight.getPrice_bus()});
                                    }
                                }
                                FlightTable.setModel(tableModel);
                                for (int i = 0; i <= 10; i++) {
                                    FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                                }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                }
            }
        });
        JButton editButton = new JButton("Sửa");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(textfligt_id.getText().isEmpty() == true || route_idcb.getSelectedItem().toString().equals("--Chọn lộ trình--") == true
                || plane_idcb.getSelectedItem().toString().equals("--Chọn máy bay--") || flightSchedule_idcb.getSelectedItem().toString().equals("--Chọn lịch trình--")
                || textprice_eco.getText().isEmpty() == true || textprice_bus.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin trước khi thêm");
                }else{
                    if(flightBUS.checkID(textfligt_id.getText())==true){
                        JOptionPane.showMessageDialog(null, "Mã chuyến bay không có");
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm chuyến bay!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            Flight flight2 = new Flight(textfligt_id.getText(), route_idcb.getSelectedItem().toString().substring(0, 7), plane_idcb.getSelectedItem().toString(), flightSchedule_idcb.getSelectedItem().toString(), Integer.parseInt(texttotalSeats.getText()), "0", "0",textprice_eco.getText(),textprice_bus.getText());
                            try {
                                flightBUS.set(flight2);
                                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)", "Giá tiền ECO","Giá tiền BUS"}, 0);
                                for (Flight flight : list_Flights) {
                                    for (FlightSchedule flightSchedule : list_FlightSchedules) {
                                        if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                                        tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats(),flight.getPrice_eco(),flight.getPrice_bus()});
                                    }
                                }
                                FlightTable.setModel(tableModel);
                                for (int i = 0; i <= 10; i++) {
                                    FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                                }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                }
            }
        });

        ImageIcon iconadd = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/new-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImageadd = iconadd.getImage();
        Image scaledImageadd= originalImageadd.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIconadd = new ImageIcon(scaledImageadd);
        addButton.setIcon(scaledIconadd);
        
        ImageIcon iconedit = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/edit-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImageedit = iconedit.getImage();
        Image scaledImageedit= originalImageedit.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIconedit = new ImageIcon(scaledImageedit);
        editButton.setIcon(scaledIconedit);

        ImageIcon icondel = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/delete-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImagedel = icondel.getImage();
        Image scaledImagedel= originalImagedel.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcondel = new ImageIcon(scaledImagedel);
        delButton.setIcon(scaledIcondel);
        
        // set layout for panelRight
        panelRight.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // add buttons to panelRight

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRight.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRight.add(delButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRight.add(editButton, gbc);


        // create JtextField for panelBot textBar,fsbar,icardbar
        // searchButton = new JButton("Tìm kiếm");
        textBar = new JTextField(15);
        textBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performSearch();
            }
        });

        String tieuchi[]={"--Chọn tiêu chí--","Mã chuyến bay","Mã lộ trình", "Mã lịch trình","Mã máy bay"};
        positionComboBox = new JComboBox<>(tieuchi);

        // searchButton.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
        //             DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
        //             for (Flight flight : list_Flights) {
        //                 for (FlightSchedule flightSchedule : list_FlightSchedules) {
        //                     if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
        //                     tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
        //                 }
        //             }
        //             FlightTable.setModel(tableModel);
        //             for (int i = 0; i <= 8; i++) {
        //                 FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        //             }
                    
        //         }
        //         if(positionComboBox.getSelectedItem().toString().equals("Mã chuyến bay")){
        //             DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
        //             for (Flight flight : list_Flights) {
        //                 for (FlightSchedule flightSchedule : list_FlightSchedules) {
        //                     if(flight.getFlight_id().equals(textBar.getText()))
        //                     if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
        //                     tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
        //                 }
        //             }
        //             FlightTable.setModel(tableModel);
        //             for (int i = 0; i <= 8; i++) {
        //                 FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        //             }
                    
        //         }
        //         if(positionComboBox.getSelectedItem().toString().equals("Mã lộ trình")){
        //             DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
        //             for (Flight flight : list_Flights) {
        //                 for (FlightSchedule flightSchedule : list_FlightSchedules) {
        //                     if(flight.getRoute_id().equals(textBar.getText()))
        //                     if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
        //                     tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
        //                 }
        //             }
        //             FlightTable.setModel(tableModel);
        //             for (int i = 0; i <= 8; i++) {
        //                 FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        //             }
                    
        //         }
        //         if(positionComboBox.getSelectedItem().toString().equals("Mã lịch trình")){
        //             DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
        //             for (Flight flight : list_Flights) {
        //                 for (FlightSchedule flightSchedule : list_FlightSchedules) {
        //                     if(flight.getFlightSchedule_id().equals(textBar.getText()))
        //                     if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
        //                     tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
        //                 }
        //             }
        //             FlightTable.setModel(tableModel);
        //             for (int i = 0; i <= 8; i++) {
        //                 FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        //             }
                    
        //         }
        //         if(positionComboBox.getSelectedItem().toString().equals("Mã máy bay")){
        //             DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
        //             for (Flight flight : list_Flights) {
        //                 for (FlightSchedule flightSchedule : list_FlightSchedules) {
        //                     if(flight.getPlane_id().equals(textBar.getText()))
        //                     if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
        //                     tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
        //                 }
        //             }
        //             FlightTable.setModel(tableModel);
        //             for (int i = 0; i <= 8; i++) {
        //                 FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        //             }
                    
        //         }
        //     }
        // });

        // add to panelBot
        // panelBot = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());

        panel.add(positionComboBox);
        panel.add(textBar);
        // panel.add(searchButton);
        

        panelBot.add(panel, BorderLayout.NORTH);
        // panelBot.add(panelBot, BorderLayout.SOUTH);
        panelBot.add(new JScrollPane(FlightTable), BorderLayout.CENTER);

        // pack and center frame
    
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin chuyến bay",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        panelCenter.setBorder(nameBorder);
        
        // Border nameBorder2 = BorderFactory.createCompoundBorder(
        //         BorderFactory.createTitledBorder(
        //                 BorderFactory.createLineBorder(Color.GRAY), "",
        //                 TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
        //                 titleFont, Color.BLACK),
        //         BorderFactory.createEmptyBorder(5, 5, 5, 5)
        // );
        // panelRight.setBorder(nameBorder2);
    }
    private void performSearch() {
        if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
            for (Flight flight : list_Flights) {
                for (FlightSchedule flightSchedule : list_FlightSchedules) {
                    if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                    tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
                }
            }
            FlightTable.setModel(tableModel);
            for (int i = 0; i <= 8; i++) {
                FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
        if(positionComboBox.getSelectedItem().toString().equals("Mã chuyến bay")){
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
            for (Flight flight : list_Flights) {
                for (FlightSchedule flightSchedule : list_FlightSchedules) {
                    if(flight.getFlight_id().startsWith(textBar.getText()))
                    if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                    tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
                }
            }
            FlightTable.setModel(tableModel);
            for (int i = 0; i <= 8; i++) {
                FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
        if(positionComboBox.getSelectedItem().toString().equals("Mã lộ trình")){
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
            for (Flight flight : list_Flights) {
                for (FlightSchedule flightSchedule : list_FlightSchedules) {
                    if(flight.getRoute_id().startsWith(textBar.getText()))
                    if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                    tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
                }
            }
            FlightTable.setModel(tableModel);
            for (int i = 0; i <= 8; i++) {
                FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
        if(positionComboBox.getSelectedItem().toString().equals("Mã lịch trình")){
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
            for (Flight flight : list_Flights) {
                for (FlightSchedule flightSchedule : list_FlightSchedules) {
                    if(flight.getFlightSchedule_id().startsWith(textBar.getText()))
                    if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                    tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
                }
            }
            FlightTable.setModel(tableModel);
            for (int i = 0; i <= 8; i++) {
                FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
        if(positionComboBox.getSelectedItem().toString().equals("Mã máy bay")){
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã chuyến bay","Mã lộ trình","Mã máy bay","Mã lịch Bay","Thời gian đi", "Thời gian đến","Tổng ghế","Thường (đã đặt)","Thương gia (đã đặt)"}, 0);
            for (Flight flight : list_Flights) {
                for (FlightSchedule flightSchedule : list_FlightSchedules) {
                    if(flight.getPlane_id().startsWith(textBar.getText()))
                    if(flightSchedule.getFlight_schedule_id().equals(flight.getFlightSchedule_id()))
                    tableModel.addRow(new Object[]{flight.getFlight_id(),flight.getRoute_id(),flight.getPlane_id(),flight.getFlightSchedule_id(),flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(),flightSchedule.getArrivalDate(),flight.getTotalSeats(),flight.getBookedECOSeats(),flight.getBookedBUSSeats()});
                }
            }
            FlightTable.setModel(tableModel);
            for (int i = 0; i <= 8; i++) {
                FlightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
    }
}

