package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import BUS.BillBUS;
import BUS.ChairBUS;
import BUS.CustomerBUS;
import BUS.DetailBillBUS;
import BUS.FlightBUS;
import BUS.FlightScheduleBUS;
import BUS.PlaneBUS;
import BUS.RoleFuncBUS2;
import BUS.RouteBUS;
import BUS.ServiceBUS;
import BUS.TicketBUS;
import BUS.TypeBUS;
import DTO.Bill;
import DTO.Chair;
import DTO.Customer;
import DTO.Flight;
import DTO.FlightSchedule;
import DTO.Plane;
import DTO.RoleFunc;
import DTO.Route;
import DTO.ServiceDTO;
import DTO.Ticket;
import DTO.Type;

public class TicketGUI extends JPanel {

    private JPanel ticketManager;
    private JPanel typeManager;
    private JTable ticketTable, tickettypeTable;
    
    private TicketBUS ticketBUS;
    private TypeBUS typeBUS;
    private CustomerBUS customerBUS;
    private FlightBUS flightBUS;
    private FlightScheduleBUS flightScheduleBUS;
    private PlaneBUS planeBUS;
    private ChairBUS chairBUS;
    private DetailBillBUS detailBillBUS;
    private BillBUS billBUS;

    List<JButton> ecoButtons, busButtons, selectedButtons;
    String saveType, saveFlight;
    JButton saveButton;
    JTextField ticketField,flightField ,cusField,chairField;
    JComboBox<String> typeField ;

    private ArrayList<JCheckBox> list_checkBox;
    private RoleFuncBUS2 roleFuncBUS2;

    private ServiceGUI serviceGUI;
    private TypeGUI typeGUI;

    private void showSaveFileDialog() throws ClassNotFoundException, SQLException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn vị trí và tên file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("File Excel (*.xlsx)", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            exportToExcel(filePath);
        }
    }
    private void exportToExcel(String filePath) throws ClassNotFoundException, SQLException {
        if(ticketField.getText().isEmpty()==true||flightField.getText().isEmpty()==true||
        cusField.getText().isEmpty()==true||chairField.getText().isEmpty()==true||
        typeField.getSelectedItem().toString().isEmpty()==true){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin trước khi xuất excel");
        }else{
            try (// Tạo workbook mới
                XSSFWorkbook workbook = new XSSFWorkbook()) {
                // Tạo một trang mới
                XSSFSheet sheet = workbook.createSheet("Data");

                // Ghi tiêu đề vào dòng đầu tiên
                XSSFRow headerRow = sheet.createRow(0);
                Cell cell2 = headerRow.createCell(3);
                cell2.setCellValue("VÉ TICKET");


                XSSFRow excelRowid = sheet.createRow(2);
                XSSFCell cellid = excelRowid.createCell(1);
                XSSFCell cellidtext = excelRowid.createCell(2);
                cellid.setCellValue("MÃ VÉ ");
                cellidtext.setCellValue(ticketField.getText());

                flightBUS = new FlightBUS();
                ArrayList<Flight> flights= flightBUS.getList_Flights();
                RouteBUS routeBUS = new RouteBUS();
                ArrayList<Route> routes = routeBUS.getList_Routes();
                XSSFRow exceltype = sheet.createRow(4);

                for (Flight flight : flights) {
                    if(flight.getFlight_id().equals(flightField.getText())){
                        for (Route route : routes) {
                            if(flight.getRoute_id().equals(route.getRoute_id())){
                                XSSFCell cellroute = exceltype.createCell(1);
                                XSSFCell cellidroutetxet = exceltype.createCell(2);
                                cellroute.setCellValue("LỘ TRÌNH ");
                                cellidroutetxet.setCellValue(route.getOrigin()+" - "+route.getDestination());
                            }
                        }
                    }
                }
                
                XSSFCell cellkh = excelRowid.createCell(4);
                XSSFCell cellkhtext = excelRowid.createCell(5);
                cellkh.setCellValue("KHÁCH HÀNG ");
                cellkhtext.setCellValue(cusField.getText());
                sheet.autoSizeColumn(4);

                XSSFRow excelRowsg = sheet.createRow(3);
                XSSFCell cellsg = excelRowsg.createCell(4);
                XSSFCell cellsgtext = excelRowsg.createCell(5);
                cellsg.setCellValue("SỐ GHẾ ");
                cellsgtext.setCellValue(chairField.getText());

               
                XSSFCell celltype = exceltype.createCell(4);
                XSSFCell cellkhtypet = exceltype.createCell(5);
                celltype.setCellValue("LOẠI VÉ ");
                cellkhtypet.setCellValue(typeField.getSelectedItem().toString());

                for (int i = 0; i < sheet.getLastRowNum(); i++) {
                    sheet.autoSizeColumn(i);
                }
                

                // Lưu workbook vào file Excel
                try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                    workbook.write(outputStream);
                    JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xuất file Excel!");
                }
            } catch (HeadlessException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public TicketGUI() throws ClassNotFoundException, SQLException {

        setSize(900, 500);
        setVisible(true);
        //setLayout(new BorderLayout());
        serviceGUI = new ServiceGUI();
        typeGUI = new TypeGUI();

        Font titleFont = new Font("Segoe UI", 3, 24);
        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        // create the panels
        ticketManager = new JPanel(new BorderLayout());
        JPanel panelticketTop = new JPanel();
        JPanel panelticketBot = new JPanel();
        JPanel panelticketRight = new JPanel();
        JPanel panelticketCenter = new JPanel(new GridBagLayout());

        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin vé máy bay",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelticketTop.setPreferredSize(new Dimension(1100, 60));
        panelticketBot.setPreferredSize(new Dimension(1100, 300));
        panelticketCenter.setPreferredSize(new Dimension(900, 300));
        panelticketRight.setPreferredSize(new Dimension(200, 250));

        ticketManager.add(panelticketTop, BorderLayout.NORTH);
        ticketManager.add(panelticketBot, BorderLayout.SOUTH);
        ticketManager.add(panelticketCenter, BorderLayout.CENTER);
        ticketManager.add(panelticketRight, BorderLayout.EAST);

        JLabel labelBillEm = new JLabel();
        JLabel labelBillInfo = new JLabel();
        labelBillEm.setText("QUẢN LÝ VÉ MÁY BAY");

        // set font for label
        labelBillEm.setFont(new Font("Times New Roman", Font.BOLD, 24));

        labelBillEm.setHorizontalAlignment(JLabel.CENTER);
        labelBillInfo.setHorizontalAlignment(JLabel.LEFT);

        // create panel and set border
        panelticketCenter.setBorder(nameBorder);
        panelticketTop.setLayout(new GridLayout(2, 1));
        panelticketTop.add(labelBillEm);
        panelticketTop.add(labelBillInfo);

        JLabel ticket_id = new JLabel("Mã vé");
        ticket_id.setFont(fontLabel);
        ticketField = new JTextField();
        ticketField.setFont(fontTextField);
        ticketField.setEditable(false);

        JLabel flight_id = new JLabel("Mã chuyến bay ");
        JPanel flightPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        flight_id.setFont(fontLabel);
        flightField = new JTextField();
        flightField.setFont(fontTextField);
        flightField.setEditable(false);
        JButton showFlightsButton = new JButton("Danh sách Chuyến bay");
        showFlightsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // code to show list of customers in new frame
                JFrame customerListFrame = new JFrame("Danh sách khách hàng");
                customerListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customerListFrame.setSize(900, 700);
                try {
                    flightBUS = new FlightBUS();
                    flightScheduleBUS = new FlightScheduleBUS();
                    planeBUS = new PlaneBUS();
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                ArrayList<Flight> flights = flightBUS.getList_Flights();
                ArrayList<FlightSchedule> flightSchedules = flightScheduleBUS.getList_FlightSchedules();
                ArrayList<Plane> planes = planeBUS.getList_Planes();

                DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã CB", "Mã LT", "Thời gian đi", "Thời gian đến", "Sân bay cất cánh", "Sân bay hạ cánh", "Số ghế Eco đã đặt", "Số ghế BUS đã đặt"}, 0);
                for (Flight flight : flights) {
                    for (FlightSchedule flightSchedule : flightSchedules) {
                        for (Plane plane : planes) {
                            if(flight.getPlane_id().equals(plane.getPlane_id())){
                                if(flight.getFlightSchedule_id().equals(flightSchedule.getFlight_schedule_id())){
                                    model.addRow(new Object[]{flight.getFlight_id(), flight.getRoute_id(),
                                        flightSchedule.getDepartureDate(), flightSchedule.getArrivalDate(), flightSchedule.getDepartureAirport(), flightSchedule.getArrivalAirport(),flight.getBookedECOSeats()+"/"+plane.getEco_seats(), 
                                        flight.getBookedBUSSeats()+"/"+plane.getBusi_seats()});
                                }
                            }
                        }
                        
                    }
                }
                JTable resultTable = new JTable(model){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                        return false;
                    }
                };
                resultTable.setPreferredScrollableViewportSize(new Dimension(750, 500));
                resultTable.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(resultTable);
                customerListFrame.getContentPane().add(scrollPane);

                resultTable.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1) {
                            JTable target = (JTable)e.getSource();
                            int row = target.getSelectedRow();
                            // Lấy dữ liệu từ JTable
                            String id = target.getValueAt(row, 0).toString();
                            flightField.setText(id);
                            // Tạo JFrame mới để hiển thị chi tiết chuyến bay
                        }
                    }
                });
                customerListFrame.setLocationRelativeTo(null);
                customerListFrame.setVisible(true);


            }
        });
        showFlightsButton.setBackground(Color.WHITE);
        flightPanel.add(flightField);
        flightPanel.add(showFlightsButton);

        JPanel cusPanel=new JPanel(new GridLayout(1,2,5,5));
        JLabel customer_id = new JLabel("Mã khách hàng");
        customer_id.setFont(fontLabel);
        cusField = new JTextField();
        cusField.setFont(fontTextField);
        cusField.setEditable(false);
        JButton showCustomersButton = new JButton("Danh sách Khách hàng");
        showCustomersButton.setBackground(Color.WHITE);
        showCustomersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // code to show list of customers in new frame
                JFrame customerListFrame = new JFrame("Danh sách khách hàng");
                customerListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customerListFrame.setSize(800, 700);
                try {
                    customerBUS = new CustomerBUS();
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                ArrayList<Customer> customers = customerBUS.getList_Customers();

                DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã KH", "Họ", "Tên", "Email", "Phone", "Address"}, 0);
                for (Customer customer : customers) {
                    model.addRow(new Object[]{customer.getCustomer_id(), customer.getSurname(), customer.getName(),
                        customer.getEmail(), customer.getPhone(), customer.getAddress()});
                }
                JTable resultTable = new JTable(model){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                        return false;
                    }
                };
                resultTable.setPreferredScrollableViewportSize(new Dimension(750, 500));
                resultTable.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(resultTable);
                customerListFrame.getContentPane().add(scrollPane);

                resultTable.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1) {
                            JTable target = (JTable)e.getSource();
                            int row = target.getSelectedRow();
                            // Lấy dữ liệu từ JTable
                            String id = target.getValueAt(row, 0).toString();
                            cusField.setText(id);
                            // Tạo JFrame mới để hiển thị chi tiết chuyến bay
                        }
                    }
                });
                customerListFrame.setLocationRelativeTo(null);
                customerListFrame.setVisible(true);


            }
        });
        cusPanel.add(cusField);
        cusPanel.add(showCustomersButton);


        typeBUS = new TypeBUS();
        ArrayList<DTO.Type> list_Types = typeBUS.getList_Types();

        JLabel type_id = new JLabel("Mã loại vé");
        type_id.setFont(fontLabel);
        typeField = new JComboBox<>();
        typeField.setFont(fontTextField);
        for (DTO.Type type : list_Types) {
            typeField.addItem(type.getType_id() +" - "+ type.getType_price());
        }

        JPanel chairPanel=new JPanel(new GridLayout(1,2,5,5));
        JLabel chair = new JLabel("Số ghế");
        chair.setFont(fontLabel);
        chairField = new JTextField();
        chairField.setFont(fontTextField);
        chairField.setEditable(false);
        JButton showChairsButton = new JButton("Chọn ghế");
        showChairsButton.setBackground(Color.WHITE);
        
        showChairsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (DTO.Type type : list_Types) {
                    if(typeField.getSelectedItem().toString().substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Phổ thông")){
                        if(flightField.getText().isEmpty() == true){
                            JOptionPane.showMessageDialog(TicketGUI.this, "Chưa chọn chuyến bay", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            JFrame listChaiFrameECO = new JFrame("Chọn ghê ECO");
                            listChaiFrameECO.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            listChaiFrameECO.setSize(500, 600);
                            listChaiFrameECO.setLocationRelativeTo(null);

                            try {
                                flightBUS = new FlightBUS();
                                planeBUS = new PlaneBUS();
                                chairBUS = new ChairBUS();
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            ArrayList<Flight> flights = flightBUS.getList_Flights();
                            ArrayList<Plane> planes = planeBUS.getList_Planes();
                            ArrayList<Chair> list_Chairs = chairBUS.getList_Chairs();
                            
                            ecoButtons = new ArrayList<>();
                            JButton chairButton = new JButton(chairField.getText());
                            ecoButtons.add(chairButton);

                            for (Plane plane : planes) {
                                for (Flight flight : flights) {
                                    if(flight.getFlight_id().equals(flightField.getText())){
                                        if(plane.getPlane_id().equals(flight.getPlane_id())){
                                            JPanel panel = new JPanel(new GridLayout(plane.getEco_seats()/6, 6));
                                            selectedButtons = new ArrayList<>();
                                            selectedButtons = ecoButtons;
                                            for (int i = 1; i <= plane.getEco_seats(); i++) {
                                                JButton button = new JButton(Integer.toString(i));
                                                for (Chair chair : list_Chairs) {
                                                    if(chair.getFlight_id().equals(flightField.getText()) && chair.getType_chair().equals("ECO") &&
                                                    chair.getChair_number().equals(Integer.toString(i)) && chair.getStatus() == 1){
                                                        button.setEnabled(false);
                                                        if(chair.getChair_number().equals(chairField.getText())){
                                                            button.setEnabled(true);
                                                        }
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
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (selectedButtons.get(0).getText().equals(button.getText())) {
                                                            // Chọn lại nút button và đặt lại màu mặc định
                                                            button.setBackground(Color.WHITE);
                                                            ecoButtons = new ArrayList<>();
                                                        } else {
                                                            if(ecoButtons.size() == 1){
                                                                int option = JOptionPane.showConfirmDialog(listChaiFrameECO, "Bạn có xác nhận ghế đã chọn", "Xác nhận", JOptionPane.YES_NO_OPTION);
                                                                if (option == JOptionPane.YES_OPTION) {
                                                                    // Xử lý khi nhấn Yes
                                                                    ecoButtons = selectedButtons;
                                                                    try {
                                                                        chairBUS.set_chair(ticketField.getText().substring(2, 7), "ECO", chairButton.getText());
                                                                    } catch (ClassNotFoundException | SQLException e1) {
                                                                        // TODO Auto-generated catch block
                                                                        e1.printStackTrace();
                                                                    }
                                                                    chairField.setText(ecoButtons.get(0).getText());
                                                                    
                                                                    listChaiFrameECO.dispose();
                                                                } else {
                                                                    // Xử lý khi nhấn No hoặc đóng hộp thoại
                                                                }
                                                            }else{
                                                                // Chọn nút button và đặt màu khác với màu mặc định
                                                                button.setBackground(Color.GREEN);
                                                                selectedButtons.set(0,button);
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
                            
                            listChaiFrameECO.setLocationRelativeTo(null);
                            listChaiFrameECO.setVisible(true);
                            
                        }
                    }
                    if(typeField.getSelectedItem().toString().substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Thương gia")){
                        if(flightField.getText().isEmpty() == true){
                            JOptionPane.showMessageDialog(TicketGUI.this, "Chưa chọn chuyến bay", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            JFrame listChaiFrameBUS = new JFrame("Chọn ghê BUS");
                            listChaiFrameBUS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            listChaiFrameBUS.setSize(500, 600);
                            listChaiFrameBUS.setLocationRelativeTo(null);

                            try {
                                flightBUS = new FlightBUS();
                                planeBUS = new PlaneBUS();
                                chairBUS = new ChairBUS();
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            ArrayList<Flight> flights = flightBUS.getList_Flights();
                            ArrayList<Plane> planes = planeBUS.getList_Planes();
                            ArrayList<Chair> list_Chairs = chairBUS.getList_Chairs();
                            
                            busButtons = new ArrayList<>();
                            JButton chairButton = new JButton(chairField.getText());
                            busButtons.add(chairButton);

                            for (Plane plane : planes) {
                                for (Flight flight : flights) {
                                    if(flight.getFlight_id().equals(flightField.getText())){
                                        if(plane.getPlane_id().equals(flight.getPlane_id())){
                                            JPanel panel = new JPanel(new GridLayout(plane.getBusi_seats()/6, 6));
                                            selectedButtons = new ArrayList<>();
                                            selectedButtons = busButtons;
                                            for (int i = 1; i <= plane.getBusi_seats(); i++) {
                                                JButton button = new JButton(Integer.toString(i));
                                                for (Chair chair : list_Chairs) {
                                                    if(chair.getFlight_id().equals(flightField.getText()) && chair.getType_chair().equals("BUS") &&
                                                    chair.getChair_number().equals(Integer.toString(i)) && chair.getStatus() == 1){
                                                        button.setEnabled(false);
                                                        if(chair.getChair_number().equals(chairField.getText())){
                                                            button.setEnabled(true);
                                                        }
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
                                                    public void actionPerformed(ActionEvent e) {
                                                        if (selectedButtons.get(0).getText().equals(button.getText())) {
                                                            // Chọn lại nút button và đặt lại màu mặc định
                                                            button.setBackground(Color.WHITE);
                                                            busButtons = new ArrayList<>();
                                                        } else {
                                                            if(busButtons.size() == 1){
                                                                int option = JOptionPane.showConfirmDialog(listChaiFrameBUS, "Bạn có xác nhận ghế đã chọn", "Xác nhận", JOptionPane.YES_NO_OPTION);
                                                                if (option == JOptionPane.YES_OPTION) {
                                                                    // Xử lý khi nhấn Yes
                                                                    busButtons = selectedButtons;
                                                                    try {
                                                                        chairBUS.set_chair(ticketField.getText().substring(2, 7), "BUS", chairButton.getText());
                                                                    } catch (ClassNotFoundException | SQLException e1) {
                                                                        // TODO Auto-generated catch block
                                                                        e1.printStackTrace();
                                                                    }

                                                                    chairField.setText(busButtons.get(0).getText());   

                                                                    listChaiFrameBUS.dispose();
                                                                } else {
                                                                    // Xử lý khi nhấn No hoặc đóng hộp thoại
                                                                }
                                                            }else{
                                                                // Chọn nút button và đặt màu khác với màu mặc định
                                                                button.setBackground(Color.GREEN);
                                                                selectedButtons.set(0,button);
                                                                busButtons = selectedButtons;
                                                            } 
                                                        }
                                                    }
                                                });
                                            panel.add(button);
                                            listChaiFrameBUS.add(panel);
                                        }
                                    }
                                }
                            }
                            
                        }
                            
                            listChaiFrameBUS.setLocationRelativeTo(null);
                            listChaiFrameBUS.setVisible(true);
                            
                        }
                    }
                }
            }
        });
        chairPanel.add(chairField);
        chairPanel.add(showChairsButton);

        JLabel bill_id = new JLabel("Mã hóa đơn");
        bill_id.setFont(fontLabel);
        JTextField billField = new JTextField();
        billField.setFont(fontTextField);
        billField.setEditable(false);
        // set size of the field
        // add labels and text fields to ticketManager panel
        panelticketCenter.setLayout(new GridLayout(6, 2, 5, 5));
        panelticketCenter.add(ticket_id);
        panelticketCenter.add(ticketField);
        panelticketCenter.add(flight_id);
        panelticketCenter.add(flightPanel);
        panelticketCenter.add(customer_id);
        panelticketCenter.add(cusPanel);
        panelticketCenter.add(type_id);
        panelticketCenter.add(typeField);
        panelticketCenter.add(chair);
        panelticketCenter.add(chairPanel);
        panelticketCenter.add(bill_id);
        panelticketCenter.add(billField);

        ticketBUS = new TicketBUS();
        ArrayList<DTO.Ticket> list_Tickets = ticketBUS.getList_Tickets();
        // add JTable for panelBot
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã vé", "Mã chuyến bay", "Mã khách hàng", "Loại vé", "Số ghế","Mã hóa đơn"}, 0);
        for (DTO.Ticket ticket : list_Tickets) {
            tableModel.addRow(new Object[]{ticket.getTicket_id(),ticket.getFlight_id(),ticket.getCustomer_id(),ticket.getType_id(),ticket.getChair_number(),ticket.getBill_id()});
        }
        ticketTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        ticketTable.setRowHeight(30);
        Font font = new Font("Arial", Font.PLAIN, 16);
        ticketTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ticketTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ticketTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        ticketTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        ticketTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        ticketTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        ticketTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
        ticketTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable

                    String id = target.getValueAt(row, 0).toString();
                    ticketField.setText(id);
                    String idflight = target.getValueAt(row, 1).toString();
                    saveFlight = idflight;
                    flightField.setText(idflight);
                    String idcus = target.getValueAt(row, 2).toString();
                    cusField.setText(idcus);
                    String  idtype = target.getValueAt(row, 3).toString();
                    saveType = idtype.substring(0, 5);
                    for (int i = 0; i < typeField.getItemCount(); i++) {
                        if(typeField.getItemAt(i).toString().startsWith(idtype)){
                            typeField.setSelectedIndex(i);
                        }
                    }
                    String numberchair = target.getValueAt(row, 4).toString();
                    saveButton = new JButton(numberchair);
                    chairField.setText(numberchair);
                    String idbill = target.getValueAt(row, 5).toString();
                    billField.setText(idbill);
                    // Tạo JFrame mới để hiển thị chi tiết chuyến bay
                }
            }
        });

        JButton editButton = new JButton("Sửa");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa vé!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        chairBUS = new ChairBUS();
                        flightBUS = new FlightBUS();
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    ArrayList<Flight> flights = flightBUS.getList_Flights();
                    for (DTO.Type type : list_Types) {
                        if(saveType.substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Phổ thông")){
                            Ticket ticket = new Ticket(ticketBUS.CreateTickerID(flightField.getText()), flightField.getText(), cusField.getText(),
                            typeField.getSelectedItem().toString().substring(0, 5), chairField.getText(), billField.getText());
                            try {
                                chairBUS.set_chair(ticketField.getText().substring(2, 7), "ECO", saveButton.getText());
                                ticketBUS.add(ticket);
                                ticketBUS.delete(ticketField.getText());
                                for (Flight flight : flights) { 
                                    if(flight.getFlight_id().equals(saveFlight)){
                                        int num = Integer.parseInt(flight.getBookedECOSeats())-1;
                                        flight.setBookedECOSeats(Integer.toString(num));
                                        try {
                                            flightBUS.set(flight);
                                        } catch (ClassNotFoundException | SQLException e1) {
                                            // TODO Auto-generated catch block
                                            e1.printStackTrace();
                                        }
                                    }
                                }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            try {
                                if(typeField.getSelectedItem().toString().substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Phổ thông")){
                                    chairBUS.set_chair_1(ticket.getFlight_id(), "ECO", ticket.getChair_number());
                                    for (Flight flight : flights) {
                                        if(flight.getFlight_id().equals(ticket.getFlight_id())){
                                            int num = Integer.parseInt(flight.getBookedECOSeats())+1;
                                            flight.setBookedECOSeats(Integer.toString(num));
                                            try {
                                                flightBUS.set(flight);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                }else{
                                    chairBUS.set_chair_1(ticket.getFlight_id(), "BUS", ticket.getChair_number());
                                    for (Flight flight : flights) {
                                        if(flight.getFlight_id().equals(ticket.getFlight_id())){
                                            int num = Integer.parseInt(flight.getBookedBUSSeats())+1;
                                            flight.setBookedBUSSeats(Integer.toString(num));
                                            try {
                                                flightBUS.set(flight);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    
                                    } 
                                }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                        if(saveType.substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Thương gia")){
                            Ticket ticket = new Ticket(ticketBUS.CreateTickerID(flightField.getText()), flightField.getText(), cusField.getText(),
                            typeField.getSelectedItem().toString().substring(0, 5), chairField.getText(), billField.getText());
                            try {
                                chairBUS.set_chair(ticketField.getText().substring(2, 7), "BUS", saveButton.getText());
                                ticketBUS.add(ticket);
                                ticketBUS.delete(ticketField.getText());
                                for (Flight flight : flights) {
                                    if(flight.getFlight_id().equals(saveFlight)){
                                        int num = Integer.parseInt(flight.getBookedBUSSeats())-1;
                                        flight.setBookedBUSSeats(Integer.toString(num));
                                        try {
                                            flightBUS.set(flight);
                                        } catch (ClassNotFoundException | SQLException e1) {
                                            // TODO Auto-generated catch block
                                            e1.printStackTrace();
                                        }
                                    }
                                    
                                }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            try {
                                if(typeField.getSelectedItem().toString().substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Thương gia")){
                                    chairBUS.set_chair_1(ticket.getFlight_id(), "BUS", ticket.getChair_number());
                                    for (Flight flight : flights) {                              
                                        if(flight.getFlight_id().equals(ticket.getFlight_id())){
                                            int num = Integer.parseInt(flight.getBookedBUSSeats())+1;
                                            flight.setBookedBUSSeats(Integer.toString(num));
                                            try {
                                                flightBUS.set(flight);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                        
                                    }
                                }else{
                                    chairBUS.set_chair_1(ticket.getFlight_id(), "ECO", ticket.getChair_number());
                                    for (Flight flight : flights) {
                                        if(flight.getFlight_id().equals(ticket.getFlight_id())){
                                            int num = Integer.parseInt(flight.getBookedECOSeats())+1;
                                            flight.setBookedECOSeats(Integer.toString(num));
                                            try {
                                                flightBUS.set(flight);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    }
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã vé", "Mã chuyến bay", "Mã khách hàng", "Loại vé", "Số ghế","Mã hóa đơn"}, 0);
                    for (DTO.Ticket ticket : list_Tickets) {
                        tableModel.addRow(new Object[]{ticket.getTicket_id(),ticket.getFlight_id(),ticket.getCustomer_id(),ticket.getType_id(),ticket.getChair_number(),ticket.getBill_id()});
                    }
                    ticketTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        ticketTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }else{

                }
                

            }
        });

        JButton delButton = new JButton("Xóa");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa vé!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        chairBUS = new ChairBUS();
                        flightBUS = new FlightBUS();
                        detailBillBUS = new DetailBillBUS();
                        billBUS = new BillBUS();
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        ticketBUS.delete(ticketField.getText());
                        detailBillBUS.delete2(billField.getText(), ticketField.getText()); 
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        for (DTO.Type type : list_Types) {
                            if(saveType.substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Phổ thông")){
                                chairBUS.set_chair(ticketField.getText().substring(2, 7), "ECO", saveButton.getText());               
                            }
                            if(saveType.substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Thương gia")){
                                chairBUS.set_chair(ticketField.getText().substring(2, 7), "BUS", saveButton.getText());               

                            }
                        }
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    ArrayList<Bill> list_Bills = billBUS.getList_Bills();
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
                    ArrayList<Flight> list_Flights = flightBUS.getList_Flights();
                    for (int i = 0; i < list_Flights.size(); i++) {
                        for (DTO.Type type : list_Types) {
                            if(list_Flights.get(i).getFlight_id().equals(ticketField.getText().substring(2, 7))&&saveType.substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Phổ thông")){
                                int num = Integer.parseInt(list_Flights.get(i).getBookedECOSeats())-1;
                                list_Flights.get(i).setBookedECOSeats(Integer.toString(num));
                                try {
                                    flightBUS.set(list_Flights.get(i));
                                } catch (ClassNotFoundException | SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                            if(list_Flights.get(i).getFlight_id().equals(ticketField.getText().substring(2, 7))&&saveType.substring(0, 5).equals(type.getType_id())&&type.getType_name().equals("Thương gia")){
                                int num = Integer.parseInt(list_Flights.get(i).getBookedBUSSeats())-1;
                                list_Flights.get(i).setBookedBUSSeats(Integer.toString(num));
                                try {
                                    flightBUS.set(list_Flights.get(i));
                                } catch (ClassNotFoundException | SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã vé", "Mã chuyến bay", "Mã khách hàng", "Loại vé", "Số ghế","Mã hóa đơn"}, 0);
                    for (DTO.Ticket ticket : list_Tickets) {
                        tableModel.addRow(new Object[]{ticket.getTicket_id(),ticket.getFlight_id(),ticket.getCustomer_id(),ticket.getType_id(),ticket.getChair_number(),ticket.getBill_id()});
                    }
                    ticketTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        ticketTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }else{

                }
            }
        });

        
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

        panelticketRight.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelticketRight.add(delButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelticketRight.add(editButton, gbc);

        String[] positions = { "--Chọn tiêu chí--", "Mã vé", "Mã chuyến bay", "Mã khách hàng", "Mã hóa đơn" };
        JComboBox<String> positionComboBox = new JComboBox<>(positions);

        // create JtextField for panelBot searchBar
        JTextField searchBar = new JTextField(20);

        // Create the search button
        JButton searchButton = new JButton("Tìm kiếm ");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã vé", "Mã chuyến bay", "Mã khách hàng", "Loại vé", "Số ghế","Mã hóa đơn"}, 0);
                    for (Ticket ticket : list_Tickets) {
                        tableModel.addRow(new Object[]{ticket.getTicket_id(),ticket.getFlight_id(),ticket.getCustomer_id(),ticket.getType_id(),ticket.getChair_number(),ticket.getBill_id()});
                    }
                    ticketTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        ticketTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Mã vé")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã vé", "Mã chuyến bay", "Mã khách hàng", "Loại vé", "Số ghế","Mã hóa đơn"}, 0);
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{   
                        for (Ticket ticket : list_Tickets) {
                            if(ticket.getTicket_id().equals(searchBar.getText())){
                                tableModel.addRow(new Object[]{ticket.getTicket_id(),ticket.getFlight_id(),ticket.getCustomer_id(),ticket.getType_id(),ticket.getChair_number(),ticket.getBill_id()});
                            }
                        }
                        ticketTable.setModel(tableModel);
                        for(int i = 0; i <= 5; i++){
                            ticketTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Mã chuyến bay")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã vé", "Mã chuyến bay", "Mã khách hàng", "Loại vé", "Số ghế","Mã hóa đơn"}, 0);
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{   
                        for (Ticket ticket : list_Tickets) {
                            if(ticket.getFlight_id().equals(searchBar.getText())){
                                tableModel.addRow(new Object[]{ticket.getTicket_id(),ticket.getFlight_id(),ticket.getCustomer_id(),ticket.getType_id(),ticket.getChair_number(),ticket.getBill_id()});
                            }
                        }
                        ticketTable.setModel(tableModel);
                        for(int i = 0; i <= 5; i++){
                            ticketTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Mã khách hàng")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã vé", "Mã chuyến bay", "Mã khách hàng", "Loại vé", "Số ghế","Mã hóa đơn"}, 0);
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{   
                        for (Ticket ticket : list_Tickets) {
                            if(ticket.getCustomer_id().equals(searchBar.getText())){
                                tableModel.addRow(new Object[]{ticket.getTicket_id(),ticket.getFlight_id(),ticket.getCustomer_id(),ticket.getType_id(),ticket.getChair_number(),ticket.getBill_id()});
                            }
                        }
                        ticketTable.setModel(tableModel);
                        for(int i = 0; i <= 5; i++){
                            ticketTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Mã hóa đơn")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã vé", "Mã chuyến bay", "Mã khách hàng", "Loại vé", "Số ghế","Mã hóa đơn"}, 0);
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{   
                        for (Ticket ticket : list_Tickets) {
                            if(ticket.getBill_id().equals(searchBar.getText())){
                                tableModel.addRow(new Object[]{ticket.getTicket_id(),ticket.getFlight_id(),ticket.getCustomer_id(),ticket.getType_id(),ticket.getChair_number(),ticket.getBill_id()});
                            }
                        }
                        ticketTable.setModel(tableModel);
                        for(int i = 0; i <= 5; i++){
                            ticketTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
            }
        });

        JPanel panel = new JPanel(new FlowLayout());
        panelticketBot.setLayout(new BorderLayout());

        panel.add(positionComboBox);
        panel.add(searchBar);
        panel.add(searchButton);
        JButton exportBtn = new JButton("Export");
        panel.add(exportBtn);
        exportBtn.addActionListener(e -> {
            try {
                showSaveFileDialog();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        panelticketBot.add(panel, BorderLayout.NORTH);
        panelticketBot.add(new JScrollPane(ticketTable));
////////////////////////////////////////////////////////////////////////////////////////////
        

        // create the tabbed pane and add the panels to it
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Ticket Manager", ticketManager);
        tabbedPane.addTab("Type Manager", typeGUI);
        tabbedPane. addTab("Service", serviceGUI);

        // create the wrapper panel and add the tabbed pane to it
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(tabbedPane, BorderLayout.CENTER);

        // add the wrapper panel to the frame
        add(wrapper, BorderLayout.CENTER);
    }
}
