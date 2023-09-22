package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BUS.BillBUS;
import BUS.ChairBUS;
import BUS.CustomerBUS;
import BUS.DetailBillBUS;
import BUS.EmployeeBUS;
import BUS.FlightBUS;
import BUS.TicketBUS;
import BUS.TypeBUS;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import DTO.Bill;
import DTO.Customer;
import DTO.DetailBill;
import DTO.Employee;
import DTO.Flight;
import DTO.Ticket;
import DTO.Type;

public class BillGUI extends JPanel {
    private JComboBox<String> positionComboBox;
    // private JComboBox<String> employeeComboBox, customercombBox;
    private JComboBox<String> employeeBox, customerBox;
    private JTextField searchBar;
    private JButton searchButton, detailButton;
    private JTable BillTable;

    private Employee employee;
    private BillBUS billBUS;
    private EmployeeBUS employeeBUS;
    private CustomerBUS customerBUS;
    private TypeBUS typeBUS;

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    public Employee getEmployee(){
        return employee;
    }

    public BillGUI(Employee employee) throws ClassNotFoundException, SQLException {
        billBUS = new BillBUS();
        ArrayList<Bill> bills = billBUS.getList_Bills();

        setSize(800, 460);
        setVisible(true);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);
        // set Wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel(new GridBagLayout());

        // set size for panel
        panelTop.setPreferredSize(new Dimension(1100, 60));
        panelBot.setPreferredSize(new Dimension(1100, 400));
        panelCenter.setPreferredSize(new Dimension(900, 250));
        panelRight.setPreferredSize(new Dimension(200, 250));

        // add panel into wrapper
        wrapperPanel.add(panelTop, BorderLayout.NORTH);
        wrapperPanel.add(panelBot, BorderLayout.SOUTH);
        wrapperPanel.add(panelCenter, BorderLayout.CENTER);
        wrapperPanel.add(panelRight, BorderLayout.EAST);

        // add wrapper into frame
        add(wrapperPanel);

        // set label for panelTop
        JLabel labelBillEm = new JLabel();
        JLabel labelBillInfo = new JLabel();
        labelBillEm.setText("QUẢN LÝ HÓA ĐƠN");
        // labelBillInfo.setText("THÔNG TIN HÓA ĐƠN");

        // set font for label
        labelBillEm.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // Set positions of labels
        labelBillEm.setHorizontalAlignment(JLabel.CENTER);
        labelBillInfo.setHorizontalAlignment(JLabel.LEFT);

        // create panel and set border
        panelCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelBillEm);
        panelTop.add(labelBillInfo);

        // create label and jtextfiled in panelCenter
        JLabel bill_id = new JLabel("Mã hóa đơn:");
        bill_id.setFont(fontLabel);
        JLabel customer_id = new JLabel("Khách hàng:");
        customer_id.setFont(fontLabel);
        JLabel employee_id = new JLabel("Nhân viên:");
        employee_id.setFont(fontLabel);
        JLabel total_price = new JLabel("Tổng tiền:");
        total_price.setFont(fontLabel);
        JLabel bill_date = new JLabel("Thời gian:");
        bill_date.setFont(fontLabel);

        
        customerBUS = new CustomerBUS();
        ArrayList<Customer> customers = customerBUS.getList_Customers();
        employeeBUS = new EmployeeBUS();
        ArrayList<Employee> employees = employeeBUS.getList_Employees();

        // add label and text field into panelCenter
        panelCenter.setLayout(new GridLayout(5, 2, 10, 10));
        panelCenter.add(bill_id);
        JTextField billField= new JTextField();
        billField.setEditable(false);
        billField.setFont(fontTextField);
        panelCenter.add(billField);

        employeeBox = new JComboBox<>();
        employeeBox.setFont(fontTextField);
        employeeBox.setPreferredSize(new Dimension(500, 20));
        employeeBox.addItem("--Chọn nhân viên lập hóa đơn--");
        panelCenter.add(employee_id);
        panelCenter.add(employeeBox);
        for (Employee e : employees) {
            employeeBox.addItem(e.getEmployee_id()+"-"+e.getSurname()+" "+e.getName());
        }
        
        customerBox = new JComboBox<>();
        customerBox.setFont(fontTextField);
        customerBox.setPreferredSize(new Dimension(500, 20));
        customerBox.addItem("--Chọn khách hàng lập hóa đơn--");
        panelCenter.add(customer_id);
        panelCenter.add(customerBox);
        for (Customer e : customers) {
            customerBox.addItem(e.getCustomer_id()+"-"+e.getSurname()+" "+e.getName());
        }

        panelCenter.add(total_price);
        JTextField totaField = new JTextField();
        totaField.setFont(fontTextField);
        totaField.setEditable(false);
        panelCenter.add(totaField);
        panelCenter.add(bill_date);
        JDateChooser billDate = new JDateChooser();
        billDate.setDateFormatString("dd-MM-yyyy");
        billDate.setLocale(new Locale("vi", "VN"));
        billDate.setPreferredSize(new Dimension(130, 20));
        billDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        billDate.setFont(fontTextField);
        panelCenter.add(billDate);

        // add JTable for panelBot
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Thời gian"}, 0);
        for (Bill bill : bills) {
            tableModel.addRow(new Object[]{bill.getBill_id(),bill.getEmployee_id(), bill.getCustomer_id(),bill.getTotal_price(),bill.getBill_date()});
        }

        BillTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        Font font = new Font("Arial", Font.PLAIN, 16);
        BillTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        BillTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        BillTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        BillTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        BillTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        BillTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        // create border
        BillTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                    String billid = target.getValueAt(row, 0).toString();
                    billField.setText(billid);
                    String employeeid = (target.getValueAt(row, 1) == null)? "":target.getValueAt(row, 1).toString();
                    if(employeeid == ""){
                        employeeBox.setSelectedIndex(0);
                    }else{
                        for (int i = 0; i < employeeBox.getItemCount(); i++) {
                            if(employeeBox.getItemAt(i).toString().startsWith(employeeid)){
                                employeeBox.setSelectedIndex(i);
                            }
                        }
                    }
                    String customerid = target.getValueAt(row, 2).toString();
                    for (int i = 0; i < customerBox.getItemCount(); i++) {
                        if(customerBox.getItemAt(i).toString().startsWith(customerid)){
                            customerBox.setSelectedIndex(i);
                        }
                    }
                    String totalprice = target.getValueAt(row, 3).toString();
                    totaField.setText(totalprice);
                    String date = target.getValueAt(row, 4).toString();
                    try {
                        billDate.setDate(Check.String_Date(date));
                        } catch (ParseException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    // Tạo JFrame mới để hiển thị chi tiết chuyến bay
                    
                
                }
            }
        });

        // create buttons for panelRight
        JButton delButton = new JButton("Xóa");
        JButton editButton = new JButton("Sửa");

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

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(billField.getText().isEmpty() == true || employeeBox.getSelectedItem().toString().isEmpty() ==true
                || customerBox.getSelectedItem().toString().isEmpty() == true || totaField.getText().isEmpty() == true
                ||  Check.Date_String(billDate.getDate()).isEmpty() == true){
                    JOptionPane.showMessageDialog(BillGUI.this, "Chưa nhập thông tin hóa đơn", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    int option = JOptionPane.showConfirmDialog(null, "Cảnh báo: Xóa hóa đơn sẽ xóa ticket nằm trong hóa đơn này!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Xử lý khi nhấn Yes
                        
                        try {
                            TicketBUS ticketBUS = new TicketBUS();
                            ArrayList<Ticket> tickets = ticketBUS.getList_Tickets();
                            DetailBillBUS detailBillBUS = new DetailBillBUS();
                            ChairBUS chairBUS = new ChairBUS();
                            //ArrayList<Chair> list_Chairs=chairBUS.getList_Chairs();
                            FlightBUS flightBUS = new FlightBUS();
                            ArrayList<Flight> flights = flightBUS.getList_Flights();

                            typeBUS = new TypeBUS();
                            ArrayList<Type> types = typeBUS.getList_Types();

                            String[] idticket = new String[tickets.size()];
                            int k = 0;
                            for (int i = 0; i < tickets.size(); i++) {
                                if(tickets.get(i).getBill_id().equals(billField.getText())){
                                    for (int j = 0; j < flights.size(); j++) {
                                        for(Type t : types){
                                            if(flights.get(j).getFlight_id().equals(tickets.get(i).getFlight_id())&&tickets.get(i).getType_id().equals(t.getType_id())&&t.getType_name().equals("Phổ thông")){
                                                int num = Integer.parseInt(flights.get(j).getBookedECOSeats())-1;
                                                flights.get(j).setBookedECOSeats(Integer.toString(num));
                                                try {
                                                    flightBUS.set(flights.get(j));
                                                    chairBUS.set_chair(flights.get(j).getFlight_id(), "ECO", tickets.get(i).getChair_number());
                                                    idticket[k++]=tickets.get(i).getTicket_id();
                                                } catch (ClassNotFoundException | SQLException e1) {
                                                    // TODO Auto-generated catch block
                                                    e1.printStackTrace();
                                                }
                                            }
                                            if(flights.get(j).getFlight_id().equals(tickets.get(i).getFlight_id())&&tickets.get(i).getType_id().equals(t.getType_id())&&t.getType_name().equals("Thương gia")){
                                                int num = Integer.parseInt(flights.get(j).getBookedBUSSeats())-1;
                                                flights.get(j).setBookedBUSSeats(Integer.toString(num));
                                                try {
                                                    flightBUS.set(flights.get(j));
                                                    chairBUS.set_chair(flights.get(j).getFlight_id(), "BUS", tickets.get(i).getChair_number());
                                                    idticket[k++]=tickets.get(i).getTicket_id();
                                                } catch (ClassNotFoundException | SQLException e1) {
                                                    // TODO Auto-generated catch block
                                                    e1.printStackTrace();
                                                }
                                            }
                                        }
                                        
                                    }
                                    
                                }
                            }
                            for(int i = 0; i < idticket.length ;i++){
                                System.out.println(idticket[i]);;
                                ticketBUS.delete(idticket[i]);
                            }
                            detailBillBUS.delete(billField.getText());
                            billBUS.delete(billField.getText());
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Thời gian"}, 0);
                        for (Bill bill : bills) {
                            tableModel.addRow(new Object[]{bill.getBill_id(),bill.getEmployee_id(), bill.getCustomer_id(),bill.getTotal_price(),bill.getBill_date()});
                        }
                        BillTable.setModel(tableModel);
                        for(int i = 0; i <= 4; i++){
                            BillTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    } else {
                        // Xử lý khi nhấn No hoặc đóng hộp thoại
                    }
                }
                
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(billField.getText().isEmpty() == true || employeeBox.getSelectedItem().toString().isEmpty() ==true
                || customerBox.getSelectedItem().toString().isEmpty() == true || totaField.getText().isEmpty() == true
                || Check.Date_String(billDate.getDate()).isEmpty() == true){
                    JOptionPane.showMessageDialog(BillGUI.this, "Chưa nhập thông tin hóa đơn", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa hóa đơn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Xử lý khi nhấn Yes
                        try {
                            Bill b = new Bill(billField.getText(),customerBox.getSelectedItem().toString().substring(0, 5), 
                            employeeBox.getSelectedItem().toString().substring(0, 5), Integer.parseInt(totaField.getText()), Check.Date_String(billDate.getDate()));
                            billBUS.set(b);
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Thời gian"}, 0);
                        for (Bill bill : bills) {
                            tableModel.addRow(new Object[]{bill.getBill_id(),bill.getEmployee_id(), bill.getCustomer_id(),bill.getTotal_price(),bill.getBill_date()});
                        }
                        BillTable.setModel(tableModel);
                        for(int i = 0; i <= 4; i++){
                            BillTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    } else {
                        // Xử lý khi nhấn No hoặc đóng hộp thoại
                    }
                }  
            }
        });

        // set layout for panelRight
        panelRight.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRight.add(delButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRight.add(editButton, gbc);

        // create combobox for panelBot
        String[] positions = { "--Chọn tiêu chí--", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng" };
        positionComboBox = new JComboBox<>(positions);

        // create JtextField for panelBot searchBar
        searchBar = new JTextField(20);

        // Create the search button
        searchButton = new JButton("Tìm kiếm ");
        detailButton = new JButton("Chi tiết");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Thời gian"}, 0);
                        for (Bill bill : bills){
                            tableModel.addRow(new Object[]{bill.getBill_id(),bill.getEmployee_id(), bill.getCustomer_id(),bill.getTotal_price(),bill.getBill_date()});
                        }
                        BillTable.setModel(tableModel);
                        BillTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                }
                   
               if(positionComboBox.getSelectedItem().toString().equals("Mã hóa đơn")){
                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Thời gian"}, 0);
                if(searchBar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    for (Bill bill : bills){
                        if(bill.getBill_id().startsWith(searchBar.getText())){
                            tableModel.addRow(new Object[]{bill.getBill_id(),bill.getEmployee_id(), bill.getCustomer_id(),bill.getTotal_price(),bill.getBill_date()});
                        }
                    }
                    BillTable.setModel(tableModel);
                    BillTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                }
               }
               if(positionComboBox.getSelectedItem().toString().equals("Mã nhân viên")){
                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Thời gian"}, 0);
                if(searchBar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    for (Bill bill : bills){
                        if(bill.getEmployee_id().startsWith(searchBar.getText())){
                            tableModel.addRow(new Object[]{bill.getBill_id(),bill.getEmployee_id(), bill.getCustomer_id(),bill.getTotal_price(),bill.getBill_date()});
                        }
                    }
                    BillTable.setModel(tableModel);
                    BillTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                }
               }
               if(positionComboBox.getSelectedItem().toString().equals("Mã khách hàng")){
                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Thời gian"}, 0);
                if(searchBar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    for (Bill bill : bills){
                        if(bill.getCustomer_id().startsWith(searchBar.getText())){
                            tableModel.addRow(new Object[]{bill.getBill_id(),bill.getEmployee_id(), bill.getCustomer_id(),bill.getTotal_price(),bill.getBill_date()});
                        }
                    }
                    BillTable.setModel(tableModel);
                    BillTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                        BillTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
                }
               }
            }
        });
        DetailBillBUS detailBillBUS = new DetailBillBUS();
        detailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(billField.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    JFrame detailBill = new JFrame("Chi tiết hóa đơn");
                    detailBill.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    detailBill.setSize(500, 500);
                    ArrayList<DetailBill> detailBills = detailBillBUS.getList_DetailBills();
                    DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã hóa đơn", "Loại vé", "Số lượng", "Tổng tiền"}, 0);
                    for (DetailBill d : detailBills){
                        if(d.getBill_id().equals(billField.getText())){
                            model.addRow(new Object[]{d.getBill_id(), d.getType_id(), d.getAmount(), d.getTotal_price()});
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
                    detailBill.getContentPane().add(scrollPane);
                    detailBill.setLocationRelativeTo(null);
                    detailBill.setVisible(true);
                }
            }
        });
        
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin hóa đơn",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // add border to panelCenter
        panelCenter.setBorder(nameBorder);

        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());
        panel.add(positionComboBox);
        panel.add(searchBar);
        panel.add(searchButton);
        panel.add(detailButton);
        panelBot.add(panel, BorderLayout.NORTH);
        // panelBot.add(panelBot, BorderLayout.SOUTH);
        panelBot.add(new JScrollPane(BillTable), BorderLayout.CENTER);

    }
}
