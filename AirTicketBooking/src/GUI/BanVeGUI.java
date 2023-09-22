package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BUS.AirportBUS;
import BUS.BillBUS;
import BUS.ChairBUS;
import BUS.CustomerBUS;
import BUS.DetailBillBUS;
import BUS.FlightBUS;
import BUS.FlightScheduleBUS;
import BUS.PlaneBUS;
import BUS.RouteBUS;
import BUS.TicketBUS;
import BUS.TypeBUS;
import DTO.Airport;
import DTO.Bill;
import DTO.Chair;
import DTO.Customer;
import DTO.DetailBill;
import DTO.Employee;
import DTO.Flight;
import DTO.FlightSchedule;
import DTO.Plane;
import DTO.Route;
import DTO.Ticket;
import DTO.Type;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.List;

public class BanVeGUI extends JPanel{
    private CustomerGUI customerGUI;

    private JTable resultTable;
    private FlightBUS flightBUS = new FlightBUS();
    private FlightScheduleBUS flightScheduleBUS= new FlightScheduleBUS();
    private PlaneBUS planeBUS = new PlaneBUS();

    private JPanel infoPanel, quantityPanel;
    private JLabel departureLabel, arrivalLabel, takeOffTimeLabel, landingTimeLabel, ecoField, busField;
    private JTextField flightIDField, departureField, arrivalField, takeOffTimeField, landingTimeField, textBar;
    private JLabel ecoSeatsLabel, busSeatsLabel;
    private JButton ecoPlusButton, ecoMinusButton, busPlusButton, busMinusButton, bookButton;
    private JComboBox<String> typeEco, typeBus;
    
    private TicketBUS ticketBUS;
    private TypeBUS typeBUS;
    private BillBUS billBUS;
    private CustomerBUS customerBUS;
    private DetailBillBUS detailBillBUS;
    private Employee employee;
    private RouteBUS routeBUS=  new RouteBUS();
    JComboBox<String> positionComboBox;
    private TypeGUI typeGUI;


    List<JButton> ecoButtons, busButtons, selectedButtons;

    ArrayList<Flight> flights = flightBUS.getList_Flights();
    ArrayList<Route> list_Routes = routeBUS.getList_Routes();
    ArrayList<Plane> planes = planeBUS.getList_Planes();

    ArrayList<FlightSchedule> flightSchedules = flightScheduleBUS.getList_FlightSchedules();

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();


    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    public Employee getEmployee(){
        return employee;
    }

    public BanVeGUI(Employee employee) throws ClassNotFoundException, SQLException{
        setSize(1500, 900);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        customerGUI = new CustomerGUI();
        typeGUI = new TypeGUI();
        
        
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã CB", "Mã LT", "Thời gian đi", "Thời gian đến", "Sân bay cất cánh", "Sân bay hạ cánh", "Số ghế Eco đã đặt", "Số ghế BUS đã đặt"}, 0);
        for (Flight flight : flights) {
            for (FlightSchedule flightSchedule : flightSchedules) {
                for (Plane plane : planes) {
                    if(flight.getPlane_id().equals(plane.getPlane_id())){
                        if(flight.getFlightSchedule_id().equals(flightSchedule.getFlight_schedule_id())){
                            model.addRow(new Object[]{flight.getFlight_id(), flight.getRoute_id(),
                                flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(), flightSchedule.getArrivalDate(), flightSchedule.getDepartureAirport(), flightSchedule.getArrivalAirport(),flight.getBookedECOSeats()+"/"+plane.getEco_seats(), 
                                flight.getBookedBUSSeats()+"/"+plane.getBusi_seats()});
                        }
                    }
                }
                
            }
            
        }
        resultTable = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        resultTable.setRowHeight(30);
        Font font = new Font("Arial", Font.PLAIN, 16);
        resultTable.setFont(font);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        resultTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        resultTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        resultTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        resultTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        resultTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        resultTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        resultTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        resultTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setPreferredSize(new Dimension(1470, 300));


        JPanel ticketInfo = new JPanel();
        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        // Panel cho thông tin vé
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Thông tin vé",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                titleFont, Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        infoPanel = new JPanel();
        infoPanel.setBorder(nameBorder);
        infoPanel.setPreferredSize(new Dimension(600, 290));
        infoPanel.setLayout(new GridLayout(4, 2, 5, 5));

        flightIDField = new JTextField();
        flightIDField.setFont(fontTextField);

        departureLabel = new JLabel("Sân bay khởi hành:");
        departureLabel.setFont(fontLabel);
        infoPanel.add(departureLabel);
        departureField = new JTextField();
        departureField.setFont(fontTextField);
        departureField.setPreferredSize(new Dimension(300, 25));
        departureField.setEditable(false);
        infoPanel.add(departureField);

        arrivalLabel = new JLabel("Sân bay hạ cánh:");
        arrivalLabel.setFont(fontLabel);
        infoPanel.add(arrivalLabel);
        arrivalField = new JTextField();
        arrivalField.setFont(fontTextField);
        arrivalField.setPreferredSize(new Dimension(300, 25));
        arrivalField.setEditable(false);
        infoPanel.add(arrivalField);

        takeOffTimeLabel = new JLabel("Ngày khởi hành:");
        takeOffTimeLabel.setFont(fontLabel);
        infoPanel.add(takeOffTimeLabel);
        takeOffTimeField = new JTextField();
        takeOffTimeField.setFont(fontTextField);
        takeOffTimeField.setPreferredSize(new Dimension(300, 25));
        takeOffTimeField.setEditable(false);
        infoPanel.add(takeOffTimeField);

        landingTimeLabel = new JLabel("Ngày hạ cánh:");
        landingTimeLabel.setFont(fontLabel);
        infoPanel.add(landingTimeLabel);
        landingTimeField = new JTextField();
        landingTimeField.setFont(fontTextField);
        landingTimeField.setPreferredSize(new Dimension(300, 25));
        landingTimeField.setEditable(false);
        infoPanel.add(landingTimeField);

        
        
        resultTable.addMouseListener(new MouseAdapter() {
            AirportBUS airportBUS = new AirportBUS();
            ArrayList<Airport> list_Airports = airportBUS.getList_Airports();
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable

                    String id = target.getValueAt(row, 0).toString();
                    flightIDField.setText(id);
    
                    String departureAirport = target.getValueAt(row, 4).toString();
                    for (Airport airport : list_Airports) {
                        if(departureAirport.equals(airport.getAirport_id())){
                            departureField.setText(airport.getName());
                        }
                    }
                    String arrivalAirport = target.getValueAt(row, 5).toString();
                    for (Airport airport : list_Airports) {
                        if(arrivalAirport.equals(airport.getAirport_id())){
                            arrivalField.setText(airport.getName());
                        }
                    }
                    String  takeOffTime = target.getValueAt(row, 2).toString();
                    takeOffTimeField.setText(takeOffTime);
                    String landingTime = target.getValueAt(row, 3).toString();
                    landingTimeField.setText(landingTime);
                    // Tạo JFrame mới để hiển thị chi tiết chuyến bay
                    
                
                }
            }
        });
        typeBUS = new TypeBUS();
        ArrayList<Type> list_Types = typeBUS.getList_Types();

        // Panel cho số lượng vé
        quantityPanel = new JPanel();
        quantityPanel.setLayout(new GridBagLayout());
        quantityPanel.setPreferredSize(new Dimension(650, 80));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5); 

        ecoSeatsLabel = new JLabel("Phổ thông:");
        ecoSeatsLabel.setFont(fontLabel);
        c.gridx=0;
        c.gridy=0;
        quantityPanel.add(ecoSeatsLabel,c);

        ecoMinusButton = new JButton("-");
        ecoMinusButton.setBackground(Color.WHITE);
        ecoMinusButton.setPreferredSize(new Dimension(45, 25));
        c.gridx=1;
        c.gridy=0;
        quantityPanel.add(ecoMinusButton,c);

        ecoField = new JLabel("0");
        ecoField.setPreferredSize(new Dimension(20, 25));
        c.gridx=2;
        c.gridy=0;
        quantityPanel.add(ecoField,c);

        ecoPlusButton = new JButton("+");
        ecoPlusButton.setBackground(Color.WHITE);
        ecoPlusButton.setPreferredSize(new Dimension(45, 25));
        c.gridx=3;
        c.gridy=0;
        quantityPanel.add(ecoPlusButton,c);

        typeEco = new JComboBox<>();
        typeEco.setPreferredSize(new Dimension(200, 25));
        for (Type type : list_Types) {
            if(type.getType_name().startsWith("Phổ thông")){
                typeEco.addItem(type.getType_id() +" - "+ type.getType_price());
            }
        }
        c.gridx=4;
        c.gridy=0;
        c.gridwidth=3;
        typeEco.setFont(new Font("Tahoma", Font.PLAIN, 16));
        quantityPanel.add(typeEco,c);


        JButton chairEcoButton = new JButton("Chọn ghế");
        chairEcoButton.setPreferredSize(new Dimension(90, 25));
        c.gridx=8;
        c.gridy=0;
        quantityPanel.add(chairEcoButton,c);

        busSeatsLabel = new JLabel("Thương gia:");
        busSeatsLabel.setFont(fontLabel);
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=1;
        quantityPanel.add(busSeatsLabel,c);

        busMinusButton = new JButton("-");
        busMinusButton.setBackground(Color.WHITE);
        busMinusButton.setPreferredSize(new Dimension(45, 25));
        c.gridx=1;
        c.gridy=1;
        quantityPanel.add(busMinusButton,c);

        busField = new JLabel("0");
        busField.setPreferredSize(new Dimension(20, 25));
        c.gridx=2;
        c.gridy=1;
        quantityPanel.add(busField,c);

        busPlusButton = new JButton("+");
        busPlusButton.setBackground(Color.WHITE);
        busPlusButton.setPreferredSize(new Dimension(45, 25));
        c.gridx=3;
        c.gridy=1;
        quantityPanel.add(busPlusButton,c);

        typeBus = new JComboBox<>();
        typeBus.setPreferredSize(new Dimension(200, 25));
        for (Type type : list_Types) {
            if(type.getType_name().startsWith("Thương gia")){
                typeBus.addItem(type.getType_id() +" - "+ type.getType_price());
            }
        }
        c.gridx=4;
        c.gridy=1;
        c.gridwidth=3;
        typeBus.setFont(new Font("Tahoma", Font.PLAIN, 16));
        quantityPanel.add(typeBus,c);


        JButton chairBusButton = new JButton("Chọn ghế");
        chairBusButton.setPreferredSize(new Dimension(90, 25));
        c.gridx=8;
        c.gridy=1;
        quantityPanel.add(chairBusButton,c);


        // Thêm action listener cho các nút + và -
        ecoMinusButton.addActionListener(e -> {
            int quantity = Integer.parseInt(ecoField.getText());
            if (quantity > 0) {
                ecoField.setText(String.valueOf(quantity - 1));
                ecoButtons = new ArrayList<>();
            }
        });
        ecoPlusButton.addActionListener(e -> {
            int quantity = Integer.parseInt(ecoField.getText());
            ecoField.setText(String.valueOf(quantity + 1));
            ecoButtons = new ArrayList<>();
        });
        busMinusButton.addActionListener(e -> {
            int quantity = Integer.parseInt(busField.getText());
            if (quantity > 0) {
                busField.setText(String.valueOf(quantity - 1));
                busButtons = new ArrayList<>();
            }
        });
        busPlusButton.addActionListener(e -> {
            int quantity = Integer.parseInt(busField.getText());
            busField.setText(String.valueOf(quantity + 1));
            busButtons = new ArrayList<>();
        });

        ChairBUS chairBUS = new ChairBUS();
        ArrayList<Chair> list_Chairs =  chairBUS.getList_Chairs();

        ecoButtons = new ArrayList<>();

        chairEcoButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                if(flightIDField.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(BanVeGUI.this, "Chưa chọn chuyến bay", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(getEcoField().getText().equals("0")){
                        JOptionPane.showMessageDialog(BanVeGUI.this, "Chọn số lượng vé", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JFrame listChaiFrameECO = new JFrame("Chọn ghế ECO");
                        listChaiFrameECO.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        listChaiFrameECO.setSize(850, 900);
                        listChaiFrameECO.setLocationRelativeTo(null);
                    for (Plane plane : planes) {
                        for (Flight flight : flights) {
                            if(flight.getFlight_id().equals(flightIDField.getText())){
                                if(plane.getPlane_id().equals(flight.getPlane_id())){
                                    JPanel panel = new JPanel(new GridLayout(plane.getEco_seats()/6, 6));
                                    selectedButtons = new ArrayList<>();
                                    selectedButtons = ecoButtons;
                                    for (int i = 1; i <= plane.getEco_seats(); i++) {
                                        JButton button = new JButton(Integer.toString(i));
                                        ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/seat.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                                        Image originalImage = icon.getImage();
                                        Image scaledImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                                
                                        // Đặt biểu tượng đã thay đổi kích thước cho nút
                                        button.setIcon(scaledIcon);
                                        for (Chair chair : list_Chairs) {
                                            if(chair.getFlight_id().equals(flightIDField.getText()) && chair.getType_chair().equals("ECO") &&
                                            chair.getChair_number().equals(Integer.toString(i)) && chair.getStatus() == 1){
                                                button.setEnabled(false);
                                            }else{
                                                button.setBackground(Color.WHITE);
                                                for(int j = 0; j < selectedButtons.size(); j++){
                                                    if(selectedButtons.get(j).getText().equals(button.getText())){
                                                        button.setBackground(Color.GREEN);
                                                    }
                                                }
                                            }
                                        }                                                         
                                        button.addActionListener(new ActionListener() {
                                            String ecoSeat = getEcoField().getText();
                                            public void actionPerformed(ActionEvent e) {
                                                if (selectedButtons.contains(button)) {
                                                    // Chọn lại nút button và đặt lại màu mặc định
                                                    button.setBackground(Color.WHITE);
                                                    selectedButtons.remove(button);
                                                    ecoButtons = selectedButtons;
                                                 } else {
                                                    if(selectedButtons.size() == Integer.parseInt(ecoSeat)){
                                                        int option = JOptionPane.showConfirmDialog(listChaiFrameECO, "Bạn có xác nhận ghế đã chọn", "Xác nhận", JOptionPane.YES_NO_OPTION);
                                                        if (option == JOptionPane.YES_OPTION) {
                                                            // Xử lý khi nhấn Yes
                                                            ecoButtons = selectedButtons;
                                                            listChaiFrameECO.dispose();
                                                        } else {
                                                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                                                        }
                                                    }else{
                                                        // Chọn nút button và đặt màu khác với màu mặc định
                                                        button.setBackground(Color.GREEN);
                                                        selectedButtons.add(button);
                                                        ecoButtons = selectedButtons;
                                                    } 
                                                }
                                            }
                                        });
                                        panel.add(button);
                                        listChaiFrameECO.add(panel);
                                    }
                                }
                            }
                        }
                        
                    }
                    listChaiFrameECO.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            if(selectedButtons.size() == Integer.parseInt(getEcoField().getText())){
                                int option = JOptionPane.showConfirmDialog(listChaiFrameECO, "Bạn có xác nhận ghế đã chọn", "Xác nhận", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    // Xử lý khi nhấn Yes
                                    ecoButtons = selectedButtons;
                                    listChaiFrameECO.dispose();
                                    } else {
                                    // Xử lý khi nhấn No hoặc đóng hộp thoại
                                }
                            }
                            
                        }
                    });
                    listChaiFrameECO.setLocationRelativeTo(null);
                    listChaiFrameECO.setVisible(true);
                    }
                }
            }
        });

        busButtons = new ArrayList<>();

        chairBusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(flightIDField.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(BanVeGUI.this, "Chưa chọn chuyến bay", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(getBusField().getText().equals("0")){
                        JOptionPane.showMessageDialog(BanVeGUI.this, "Chọn số lượng vé", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JFrame listChairFrameBUS = new JFrame("Chọn ghê BUS");
                        listChairFrameBUS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        listChairFrameBUS.setSize(650, 700);
                        listChairFrameBUS.setLocationRelativeTo(null);
                    for (Plane plane : planes) {
                        for (Flight flight : flights) {
                            if(flight.getFlight_id().equals(flightIDField.getText())){
                                if(plane.getPlane_id().equals(flight.getPlane_id())){
                                    JPanel panel = new JPanel(new GridLayout(plane.getBusi_seats()/3, 3));
                                    selectedButtons = new ArrayList<>();
                                    selectedButtons = busButtons;
                                    for (int i = 1; i <= plane.getBusi_seats(); i++) {
                                        JButton button = new JButton(Integer.toString(i));
                                        ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/first-class.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                                        Image originalImage = icon.getImage();
                                        Image scaledImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                                
                                        // Đặt biểu tượng đã thay đổi kích thước cho nút
                                        button.setIcon(scaledIcon);
                                        for (Chair chair : list_Chairs) {
                                            if(chair.getFlight_id().equals(flightIDField.getText()) && chair.getType_chair().equals("BUS") && 
                                            chair.getChair_number().equals(Integer.toString(i)) && chair.getStatus() == 1){
                                                button.setEnabled(false);
                                            }else{
                                                button.setBackground(Color.WHITE);
                                                for(int j = 0; j < selectedButtons.size(); j++){
                                                    if(selectedButtons.get(j).getText().equals(button.getText())){
                                                        button.setBackground(Color.GREEN);
                                                    }
                                                }
                                            }
                                        }                                                         
                                        button.addActionListener(new ActionListener() {
                                            String busSeat = getBusField().getText();
                                            public void actionPerformed(ActionEvent e) {
                                                if (selectedButtons.contains(button)) {
                                                    // Chọn lại nút button và đặt lại màu mặc định
                                                    button.setBackground(Color.WHITE);
                                                    selectedButtons.remove(button);
                                                    busButtons = selectedButtons;
                                                 } else {
                                                    if(selectedButtons.size() == Integer.parseInt(busSeat)){
                                                        int option = JOptionPane.showConfirmDialog(listChairFrameBUS, "Bạn có xác nhận ghế đã chọn", "Xác nhận", JOptionPane.YES_NO_OPTION);
                                                        if (option == JOptionPane.YES_OPTION) {
                                                            // Xử lý khi nhấn Yes
                                                            busButtons = selectedButtons;
                                                            listChairFrameBUS.dispose();
                                                        } else {
                                                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                                                        }
                                                    }else{
                                                        // Chọn nút button và đặt màu khác với màu mặc định
                                                        button.setBackground(Color.GREEN);
                                                        selectedButtons.add(button);
                                                        busButtons = selectedButtons;
                                                    } 
                                                }
                                            }
                                        });
                                        panel.add(button);
                                        listChairFrameBUS.add(panel);
                                    }
                                }
                            }
                        }
                        
                    }
                    listChairFrameBUS.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            if(selectedButtons.size() == Integer.parseInt(getBusField().getText())){
                                int option = JOptionPane.showConfirmDialog(listChairFrameBUS, "Bạn có xác nhận ghế đã chọn", "Xác nhận", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    // Xử lý khi nhấn Yes
                                    busButtons = selectedButtons;
                                    listChairFrameBUS.dispose();
                                    } else {
                                    // Xử lý khi nhấn No hoặc đóng hộp thoại
                                }
                            }
                            
                            
                        }
                    });
                    listChairFrameBUS.setLocationRelativeTo(null);
                    listChairFrameBUS.setVisible(true);
                    }
                }
            }
        });

        // Nút đặt vé
        bookButton = new JButton("Đặt vé");
        ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/payment.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Đặt biểu tượng đã thay đổi kích thước cho nút
        bookButton.setIcon(scaledIcon);
        

        

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    customerBUS = new CustomerBUS();
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                ArrayList<Customer> list_Customers = customerBUS.getList_Customers();
                try {
                    billBUS = new BillBUS();
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    detailBillBUS = new DetailBillBUS();
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    ticketBUS = new TicketBUS();
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if(customerGUI.getCustomerIdTextField().getText().isEmpty() == true ||
                customerGUI.getSurnameTextField().getText().isEmpty() == true || customerGUI.getNameTextField().getText().isEmpty() == true ||
                customerGUI.getEmailTextField().getText().isEmpty() == true || customerGUI.getPhoneTextField().getText().isEmpty() == true ||
                customerGUI.getAddressTextField().getText().isEmpty() == true || Check.isValidEmail(customerGUI.getEmailTextField().getText()) == false
                || Check.isValidPhoneNumber(customerGUI.getPhoneTextField().getText()) == false){
                    JOptionPane.showMessageDialog(BanVeGUI.this, "Chưa nhập thông tin khác hàng", "Error", JOptionPane.ERROR_MESSAGE);
                    if (Check.isValidEmail(customerGUI.getEmailTextField().getText()) == false) {
                        JOptionPane.showMessageDialog(BanVeGUI.this, "Error Email", "Error", JOptionPane.ERROR_MESSAGE);
                        customerGUI.getEmailTextField().requestFocus();
                    }
                    if (Check.isValidPhoneNumber(customerGUI.getPhoneTextField().getText()) == false) {
                        JOptionPane.showMessageDialog(BanVeGUI.this, "Error phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        customerGUI.getPhoneTextField().requestFocus();
                    }
                }else{
                    if(flightIDField.getText().isEmpty() == true || ( Integer.parseInt(getEcoField().getText()) > 0 && ecoButtons.size() == 0 )
                    || ( Integer.parseInt(getBusField().getText()) > 0 && busButtons.size() == 0 )){
                        if(flightIDField.getText().isEmpty() == true){
                            JOptionPane.showMessageDialog(BanVeGUI.this, "Chưa chọn chuyến bay", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        if(( Integer.parseInt(getEcoField().getText()) > 0 && ecoButtons.size() == 0 )){
                            JOptionPane.showMessageDialog(BanVeGUI.this, "Chưa chọn ghế ECO", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        if(( Integer.parseInt(getBusField().getText()) > 0 && busButtons.size() == 0 )){
                            JOptionPane.showMessageDialog(BanVeGUI.this, "Chưa chọn ghế BUS", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận đặt vé!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            String flightID = flightIDField.getText();
                            // String departure = departureField.getText();
                            // String arrival = arrivalField.getText();
                            // String takeOffTime = takeOffTimeField.getText();
                            // String landingTime = landingTimeField.getText();
                            int ecoSeats = Integer.parseInt(ecoField.getText());
                            int busSeats = Integer.parseInt(busField.getText());
                            Flight flight_main = new Flight();
                            for (Flight flight : flights) {
                                if(flight.getFlight_id().equals(flightID)){
                                    int num = ecoSeats + Integer.parseInt(flight.getBookedECOSeats());
                                    int num2 = busSeats + Integer.parseInt(flight.getBookedBUSSeats());
                                    flight_main = flight;
                                    flight.setBookedECOSeats(Integer.toString(num));
                                    flight.setBookedBUSSeats(Integer.toString(num2));
                                    try {
                                        flightBUS.set(flight);
                                    } catch (ClassNotFoundException | SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
    
                            String ecotype = (String) typeEco.getSelectedItem();
                            String bustype = (String) typeBus.getSelectedItem();
    
                            Customer customer = new Customer(customerGUI.getCustomerIdTextField().getText(),
                            customerGUI.getSurnameTextField().getText(), customerGUI.getNameTextField().getText(),
                            customerGUI.getEmailTextField().getText(), customerGUI.getPhoneTextField().getText(),
                            customerGUI.getAddressTextField().getText());
                            boolean checkKH = false;
                            for (Customer kh : list_Customers) {
                                if(kh.getCustomer_id().equals(customer.getCustomer_id())){
                                    checkKH = true;
                                }
                            }
                            if(checkKH == false){
                                try {
                                    customerBUS.add(customer);
                                } catch (ClassNotFoundException | SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                            JDateChooser bill_date = new JDateChooser();
                            Calendar calendar = Calendar.getInstance();
                            bill_date.setDate(calendar.getTime());
                            bill_date.setDateFormatString("dd-MM-yyyy hh:mm");
                            bill_date.setLocale(new Locale("vi", "VN"));
                            java.util.Date billDate = bill_date.getDate();
                            String date = Check.Date_String(billDate);
                            Bill bill = new Bill(billBUS.CreateMHD(), customer.getCustomer_id(), employee.getEmployee_id(), 0, date);
    
                            Type type_eco = new Type();
                            Type type_bus = new Type();
                            for (Type type : list_Types) {
                                if(type.getType_id().equals(ecotype.substring(0, 5))){
                                    type_eco = type;
                                }
                                if(type.getType_id().equals(bustype.substring(0, 5))){
                                    type_bus = type;
                                }
                            }
                            // DetailBill detailBill_eco= new DetailBill();
                            // DetailBill detailBill_bus= new DetailBill();
                            // if(ecoSeats>0){
                            //     detailBill_eco.setBill_id(bill.getBill_id());
                            //     detailBill_eco.setAmount(ecoSeats);
                            //     detailBill_eco.setTotal_price(type_eco.getType_price()*ecoSeats);
                                
                            // }
                            // if(busSeats>0){
                            //     detailBill_bus.setBill_id(bill.getBill_id());
                            //     detailBill_bus.setAmount(busSeats);
                            //     detailBill_bus.setTotal_price(type_bus.getType_price()*busSeats);
    
                            // }
                            int sumeco = (int) ((type_eco.getType_price()+Integer.parseInt(flight_main.getPrice_eco()))*ecoSeats);
                            int sumbus = (int) ((type_bus.getType_price()+Integer.parseInt(flight_main.getPrice_bus()))*busSeats);
                            bill.setTotal_price(sumeco+sumbus);
                            try {
                                billBUS.add(bill);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }   
                            if(ecoSeats > 0){
                                for(int i = 0; i < ecoSeats; i++){
                                    Ticket ticket_eco = new Ticket(ticketBUS.CreateTickerID(flightID), flightID, customer.getCustomer_id(), type_eco.getType_id(), ecoButtons.get(i).getText(), bill.getBill_id());
                                    DetailBill detailBill_eco= new DetailBill(bill.getBill_id(),ticket_eco.getTicket_id(),1,type_eco.getType_price()+Double.parseDouble(flight_main.getPrice_eco()));
                                    for (Chair chair : list_Chairs) {
                                        if(chair.getFlight_id().equals(flightIDField.getText()) && chair.getType_chair().equals("ECO") &&
                                        chair.getChair_number().equals(ecoButtons.get(i).getText())){
                                            chair.setStatus(1);
                                            try {
                                                chairBUS.set(chair);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                    try {
                                        ticketBUS.add(ticket_eco);
                                        detailBillBUS.add(detailBill_eco);
                                    } catch (ClassNotFoundException | SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            
                            if(busSeats > 0){
                                for(int i = 0; i < busSeats; i++){
                                    Ticket ticket_bus = new Ticket(ticketBUS.CreateTickerID(flightID), flightID, customer.getCustomer_id(), type_bus.getType_id(), busButtons.get(i).getText(), bill.getBill_id());
                                    DetailBill detailBill_bus= new DetailBill(bill.getBill_id(),ticket_bus.getTicket_id(),1,type_bus.getType_price()+Double.parseDouble(flight_main.getPrice_bus()));
                                    for (Chair chair : list_Chairs) {
                                        if(chair.getFlight_id().equals(flightIDField.getText()) && chair.getType_chair().equals("BUS") &&
                                        chair.getChair_number().equals(busButtons.get(i).getText())){
                                            chair.setStatus(1);
                                            try {
                                                chairBUS.set(chair);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                    try {
                                        ticketBUS.add(ticket_bus);
                                        detailBillBUS.add(detailBill_bus);
                                    } catch (ClassNotFoundException | SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã CB", "Mã LT", "Thời gian đi", "Thời gian đến", "Sân bay cất cánh", "Sân bay hạ cánh", "Số ghế Eco đã đặt", "Số ghế BUS đã đặt"}, 0);
                            for (Flight flight : flights) {
                                for (FlightSchedule flightSchedule : flightSchedules) {
                                    for (Plane plane : planes) {
                                        if(flight.getPlane_id().equals(plane.getPlane_id())){
                                            if(flight.getFlightSchedule_id().equals(flightSchedule.getFlight_schedule_id())){
                                                model.addRow(new Object[]{flight.getFlight_id(), flight.getRoute_id(),
                                                    flightSchedule.getDepartureDate()+flightSchedule.getBoartime(), flightSchedule.getArrivalDate(), flightSchedule.getDepartureAirport(), flightSchedule.getArrivalAirport(),flight.getBookedECOSeats()+"/"+plane.getEco_seats(), 
                                                    flight.getBookedBUSSeats()+"/"+plane.getBusi_seats()});
                                            }
                                        }
                                    }
                                    
                                }
                                
                            }
                            resultTable.setModel(model);
                            for(int i = 0; i <= 7; i++){
                                resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }  
                    }
                } 
            }
        });
        textBar = new JTextField();
        textBar.setPreferredSize(new Dimension(180, 30));
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

        
       

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(650, 700));
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(650, 100));
        panel2.add(quantityPanel);

        JPanel panel4 = new JPanel(new FlowLayout());
        panel4.add(bookButton);
        JButton checkchair = new JButton("Check ghế");
        ImageIcon icon3 = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/ergonomic.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImage3 = icon3.getImage();
        Image scaledImage3= originalImage3.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
        checkchair.setIcon(scaledIcon3);

        JButton typeButton = new JButton("SERVICE");
        ImageIcon icon2 = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/flight-attendant.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImage2 = icon2.getImage();
        Image scaledImage2= originalImage2.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        typeButton.setIcon(scaledIcon2);

        panel4.add(typeButton);
        panel4.add(checkchair);

        //Service---------------------------------------
        typeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame service = new JFrame("SERVICE");
                service.setLayout(new BorderLayout());
                service.setSize(1200, 750);
                service.add(typeGUI, BorderLayout.CENTER);
                service.setLocationRelativeTo(null);
                service.setVisible(true);
                service.setResizable(false);
            }
        });

        //checkChair------------------------------------
        checkchair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFrame listChaiFrameECO = new JFrame("Check ghế");
                listChaiFrameECO.setLayout(new BorderLayout());
                    listChaiFrameECO.setSize(850, 1040);
                    listChaiFrameECO.setLocationRelativeTo(null);
                    for (Plane plane : planes) {
                        for (Flight flight : flights) {
                            if(flight.getFlight_id().equals(flightIDField.getText())){
                                if(plane.getPlane_id().equals(flight.getPlane_id())){
                                    JPanel panel = new JPanel(new GridLayout(plane.getEco_seats()/6, 6));
                                    for (int i = 1; i <= plane.getEco_seats(); i++) {
                                        JButton button = new JButton(Integer.toString(i));
                                        button.setPreferredSize(new Dimension(20, 20));
                                        button.setBackground(Color.WHITE);
                                        ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/icons8-armchair-32.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                                        Image originalImage = icon.getImage();
                                        Image scaledImage = originalImage.getScaledInstance(11, 11, Image.SCALE_SMOOTH);
                                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                                
                                        // Đặt biểu tượng đã thay đổi kích thước cho nút
                                        button.setIcon(scaledIcon);
                                        for (Chair chair : list_Chairs) {
                                            if(chair.getFlight_id().equals(flightIDField.getText()) && chair.getType_chair().equals("ECO") &&
                                            chair.getChair_number().equals(Integer.toString(i)) && chair.getStatus() == 1){
                                                button.setEnabled(false);
                                            }
                                        }                                                         
                                        panel.add(button);
                                        listChaiFrameECO.add(panel, BorderLayout.SOUTH);
                                    }
                                    JPanel panel2 = new JPanel(new GridLayout(plane.getBusi_seats()/3, 3));
                                    for (int i = 1; i <= plane.getBusi_seats(); i++) {
                                        JButton button = new JButton(Integer.toString(i));
                                        button.setPreferredSize(new Dimension(20, 20));
                                        button.setBackground(Color.WHITE);
                                        ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/icons8-armchair-32.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                                        Image originalImage = icon.getImage();
                                        Image scaledImage = originalImage.getScaledInstance(11, 11, Image.SCALE_SMOOTH);
                                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                                
                                        // Đặt biểu tượng đã thay đổi kích thước cho nút
                                        button.setIcon(scaledIcon);
                                        for (Chair chair : list_Chairs) {
                                            if(chair.getFlight_id().equals(flightIDField.getText()) && chair.getType_chair().equals("BUS") && 
                                            chair.getChair_number().equals(Integer.toString(i)) && chair.getStatus() == 1){
                                                button.setEnabled(false);
                                            }
                                        }
                                        panel2.add(button);
                                        listChaiFrameECO.add(panel2, BorderLayout.NORTH);
                                    }
                                }
                            }
                        }
                        
                    }
                JLabel row = new JLabel();
                row.setPreferredSize(new Dimension(getWidth(), 7));
                row.setBackground(Color.LIGHT_GRAY);
                listChaiFrameECO.add(row, BorderLayout.CENTER);
                listChaiFrameECO.setLocationRelativeTo(null);
                listChaiFrameECO.setVisible(true);
                listChaiFrameECO.setResizable(false);
            }
        });

        JLabel search = new JLabel("Tìm kiếm: ");
        String tieuchi[]={"--Chọn tiêu chí--","Mã chuyến bay","Mã lộ trình"};
        positionComboBox = new JComboBox<>(tieuchi);
        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.add(search);
        panel3.add(positionComboBox);
        panel3.add(textBar);
        
        // Thêm panel vào frame
        panel.add(infoPanel);
        panel.add(panel2);
        panel.add(panel4);
        panel.add(panel3);

        ticketInfo.add(panel);
        setVisible(true);
        
       

        add(customerGUI, BorderLayout.WEST);
        add(ticketInfo, BorderLayout.EAST);
        add(scrollPane, BorderLayout.SOUTH);
        setVisible(true);
        
        
    }
    private void performSearch() {
        if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã CB", "Mã LT", "Thời gian đi", "Thời gian đến", "Sân bay cất cánh", "Sân bay hạ cánh", "Số ghế Eco đã đặt", "Số ghế BUS đã đặt"}, 0);
            for (Flight flight : flights) {
                for (FlightSchedule flightSchedule : flightSchedules) {
                    for (Plane plane : planes) {
                        if(flight.getPlane_id().equals(plane.getPlane_id())){
                            if(flight.getFlightSchedule_id().equals(flightSchedule.getFlight_schedule_id())){
                                tableModel.addRow(new Object[]{flight.getFlight_id(), flight.getRoute_id(),
                                    flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(), flightSchedule.getArrivalDate(), flightSchedule.getDepartureAirport(), flightSchedule.getArrivalAirport(),flight.getBookedECOSeats()+"/"+plane.getEco_seats(), 
                                    flight.getBookedBUSSeats()+"/"+plane.getBusi_seats()});
                            }
                        }
                    }
                    
                }
                
            }
            resultTable.setModel(tableModel);
            for (int i = 0; i <= 7; i++) {
                resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
        if(positionComboBox.getSelectedItem().toString().equals("Mã chuyến bay")){
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã CB", "Mã LT", "Thời gian đi", "Thời gian đến", "Sân bay cất cánh", "Sân bay hạ cánh", "Số ghế Eco đã đặt", "Số ghế BUS đã đặt"}, 0);
            for (Flight flight : flights) {
                for (FlightSchedule flightSchedule : flightSchedules) {
                    for (Plane plane : planes) {
                        if(flight.getFlight_id().startsWith(textBar.getText()))
                        if(flight.getPlane_id().equals(plane.getPlane_id())){
                            if(flight.getFlightSchedule_id().equals(flightSchedule.getFlight_schedule_id())){
                                tableModel.addRow(new Object[]{flight.getFlight_id(), flight.getRoute_id(),
                                    flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(), flightSchedule.getArrivalDate(), flightSchedule.getDepartureAirport(), flightSchedule.getArrivalAirport(),flight.getBookedECOSeats()+"/"+plane.getEco_seats(), 
                                    flight.getBookedBUSSeats()+"/"+plane.getBusi_seats()});
                            }
                        }
                    }
                    
                }
                
            }
            resultTable.setModel(tableModel);
            for (int i = 0; i <= 7; i++) {
                resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
        if(positionComboBox.getSelectedItem().toString().equals("Mã lộ trình")){
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã CB", "Mã LT", "Thời gian đi", "Thời gian đến", "Sân bay cất cánh", "Sân bay hạ cánh", "Số ghế Eco đã đặt", "Số ghế BUS đã đặt"}, 0);
            for (Flight flight : flights) {
                for (FlightSchedule flightSchedule : flightSchedules) {
                    for (Plane plane : planes) {
                        if(flight.getRoute_id().startsWith(textBar.getText()))
                        if(flight.getPlane_id().equals(plane.getPlane_id())){
                            if(flight.getFlightSchedule_id().equals(flightSchedule.getFlight_schedule_id())){
                                tableModel.addRow(new Object[]{flight.getFlight_id(), flight.getRoute_id(),
                                    flightSchedule.getDepartureDate()+" "+flightSchedule.getBoartime(), flightSchedule.getArrivalDate(), flightSchedule.getDepartureAirport(), flightSchedule.getArrivalAirport(),flight.getBookedECOSeats()+"/"+plane.getEco_seats(), 
                                    flight.getBookedBUSSeats()+"/"+plane.getBusi_seats()});
                            }
                        }
                    }
                    
                }
                
            }
            resultTable.setModel(tableModel);
            for (int i = 0; i <= 7; i++) {
                resultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
    }
    public JLabel getEcoField() {
        return ecoField;
    }
    public void setEcoField(JLabel ecoField) {
        this.ecoField = ecoField;
    }
    public JLabel getBusField() {
        return busField;
    }
    public void setBusField(JLabel busField) {
        this.busField = busField;
    }
    
    
}
