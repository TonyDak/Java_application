package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BUS.AirportBUS;
import BUS.BillBUS;
import BUS.FlightScheduleBUS;
import BUS.TicketBUS;
import DTO.Bill;
import DTO.FlightSchedule;
import DTO.Ticket;

public class FlightScheduleGUI extends JPanel {


    private JLabel text, fsid, daylb, dayarr;
    private JTextField textBar, fsbar;
    private JButton searchButton;
    private JTable FlightScheduleTable;

    private FlightScheduleBUS flightScheduleBUS;
    private AirportBUS airportBUS;

    public FlightScheduleGUI() throws ClassNotFoundException, SQLException {
        // JFrame frame = new JFrame();
        setSize(800, 460);
        setVisible(true);
        flightScheduleBUS=new FlightScheduleBUS();
        ArrayList<FlightSchedule> list_FlightSchedules = flightScheduleBUS.getList_FlightSchedules();
        airportBUS = new AirportBUS();
        // set Wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel();

        // set size for panel
        panelTop.setPreferredSize(new Dimension(1100, 60));
        panelBot.setPreferredSize(new Dimension(1100, 300));
        panelCenter.setPreferredSize(new Dimension(900, 300));
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
        labelFlightScheduleEm.setText("QUẢN LÝ LỊCH TRÌNH");

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
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);
        // create label and jtextfiled in panelCenter
        JLabel flightSchedule_id = new JLabel("Mã lộ trình:");
        flightSchedule_id.setFont(fontLabel);
        JLabel departureAirport = new JLabel("Nơi đi:");
        departureAirport.setFont(fontLabel);
        JLabel arrivalAirport = new JLabel("Nơi đến:");
        arrivalAirport.setFont(fontLabel);
        JLabel weekdays = new JLabel("Ngày đi: ");
        weekdays.setFont(fontLabel);
        JLabel time = new JLabel("Ngày đến:");
        time.setFont(fontLabel);
        JLabel time2 = new JLabel("Giờ, phút");
        time2.setFont(fontLabel);
    
        JComboBox<String> hour = new JComboBox<>();
        hour.addItem("00");
        hour.addItem("01");
        hour.addItem("02");
        hour.addItem("03");
        hour.addItem("04");
        hour.addItem("05");
        hour.addItem("06");
        hour.addItem("07");
        hour.addItem("08");
        hour.addItem("09");
        for (int i = 10; i <= 23; i++) {
            hour.addItem(""+i);
        }
        JComboBox<String> minute = new JComboBox<>();
        minute.addItem("00");
        minute.addItem("01");
        minute.addItem("02");
        minute.addItem("03");
        minute.addItem("04");
        minute.addItem("05");
        minute.addItem("06");
        minute.addItem("07");
        minute.addItem("08");
        minute.addItem("09");
        for (int i = 10; i <= 59; i++) {
            minute.addItem(""+i);
        }
    
        
        JDateChooser departureTime = new JDateChooser();
        departureTime.setDateFormatString("dd-MM-yyyy");
        departureTime.setLocale(new Locale("vi", "VN"));
        departureTime.setPreferredSize(new Dimension(130, 20));
        departureTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        departureTime.setFont(fontTextField);
        JDateChooser arrivalTime = new JDateChooser();
        arrivalTime.setDateFormatString("dd-MM-yyyy");
        arrivalTime.setLocale(new Locale("vi", "VN"));
        arrivalTime.setPreferredSize(new Dimension(130, 20));
        arrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        arrivalTime.setFont(fontTextField);

       
        // add label and text field into panelCenter
        JPanel time3 = new JPanel();
        JTextField fscid = new JTextField();

        String[] cities = airportBUS.listCity();
        List<String> cityList = new ArrayList<>(Arrays.asList(cities));

        fscid.setFont(fontTextField);
        JComboBox<String> dpa = new JComboBox<>(cities);
        dpa.setFont(fontTextField);

        JComboBox<String> arap = new JComboBox<>(cityList.toArray(new String[0]));
        arap.setFont(fontTextField);
        dpa.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedCity = (String) e.getItem();
                    List<String> remainingCities = new ArrayList<>(cityList);
                    remainingCities.remove(selectedCity);
                    arap.removeAllItems();
                    for (String city : remainingCities) {
                        arap.addItem(city);
                    }
                }
            }
        });

        panelCenter.setLayout(new GridLayout(6, 2,5,5));
        panelCenter.add(flightSchedule_id);
        panelCenter.add(fscid);
        panelCenter.add(departureAirport);
        panelCenter.add(dpa);
        panelCenter.add(arrivalAirport);
        panelCenter.add(arap);
        panelCenter.add(weekdays);
        panelCenter.add(departureTime);
        panelCenter.add(time);
        panelCenter.add(arrivalTime);
        panelCenter.add(time2);
        time3.setLayout(new GridLayout(1,2));
        panelCenter.add(time3);
        time3.add(hour);
        time3.add(minute);

        // add JTable for panelBot
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình","Nơi đi", "Nơi đến", "Ngày đi", "Ngày đến","Giờ lên máy bay"}, 0);
        for (FlightSchedule flightSchedule : list_FlightSchedules) {
            tableModel.addRow(new Object[]{flightSchedule.getFlight_schedule_id(), flightSchedule.getDepartureAirport(),flightSchedule.getArrivalAirport(),flightSchedule.getDepartureDate(),flightSchedule.getArrivalDate(),flightSchedule.getBoartime()});
        }
        FlightScheduleTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        Font font = new Font("Arial", Font.PLAIN, 16);
        FlightScheduleTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        FlightScheduleTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        FlightScheduleTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        FlightScheduleTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        FlightScheduleTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        FlightScheduleTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        FlightScheduleTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        // create buttons for panelRight
        FlightScheduleTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                    String id = target.getValueAt(row, 0).toString();
                    fscid.setText(id);
                    String diemdi = target.getValueAt(row, 1).toString();
                    for (int i = 0; i < dpa.getItemCount(); i++) {
                        if(dpa.getItemAt(i).toString().startsWith(diemdi)){
                            dpa.setSelectedIndex(i);
                        }
                    }                    
                    String diemden = target.getValueAt(row, 2).toString();
                    for (int i = 0; i < arap.getItemCount(); i++) {
                        if(arap.getItemAt(i).toString().startsWith(diemden)){
                            arap.setSelectedIndex(i);
                        }
                    }                    
                    String thu = target.getValueAt(row, 3).toString();
                    try {
                        departureTime.setDate(Check.String_Date(thu));
                    } catch (ParseException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    String time = target.getValueAt(row, 4).toString();
                    try {
                        arrivalTime.setDate(Check.String_Date(time));
                    } catch (ParseException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    String timeboar = target.getValueAt(row, 5).toString();
                    String[] parts = timeboar.split(":");
                    for (int i = 0; i < hour.getItemCount(); i++) {
                        if(hour.getItemAt(i).toString().startsWith(parts[0])){
                            hour.setSelectedIndex(i);
                        }
                    }
                    for (int i = 0; i < minute.getItemCount(); i++) {
                        if(minute.getItemAt(i).toString().startsWith(parts[1])){
                            minute.setSelectedIndex(i);
                        }
                    }
                }
            }
        });


        JButton addButton = new JButton("Thêm");
        JButton delButton = new JButton("Xóa");
        JButton editButton = new JButton("Sửa");
        // JButton eraseButton = new JButton("Làm rỗng");
        // JButton exitButton = new JButton("Thoát");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fscid.getText().isEmpty() == true || dpa.getSelectedItem().toString().isEmpty()==true
                || arap.getSelectedItem().toString().isEmpty()==true || Check.Date_String(departureTime.getDate()).isEmpty() == true ||
                Check.Date_String(arrivalTime.getDate()).isEmpty() ==true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(flightScheduleBUS.checkID(fscid.getText())==false){
                        JOptionPane.showMessageDialog(null, "Trùng mã cần thêm", "Error", JOptionPane.ERROR_MESSAGE);
    
                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                FlightSchedule flightSchedule = new FlightSchedule(fscid.getText(), dpa.getSelectedItem().toString().substring(0, 3), arap.getSelectedItem().toString().substring(0, 3), Check.Date_String(departureTime.getDate()),Check.Date_String(arrivalTime.getDate()),hour.getSelectedItem().toString()+":"+minute.getSelectedItem().toString());
                                flightScheduleBUS.add(flightSchedule);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình","Nơi đi", "Nơi đến", "Ngày đi", "Ngày đến","Giờ lên máy bay"}, 0);
                            for (FlightSchedule flightSchedule : list_FlightSchedules) {
                                tableModel.addRow(new Object[]{flightSchedule.getFlight_schedule_id(), flightSchedule.getDepartureAirport(),flightSchedule.getArrivalAirport(),flightSchedule.getDepartureDate(),flightSchedule.getArrivalDate(),flightSchedule.getBoartime()});
                            }
                            FlightScheduleTable.setModel(tableModel);
                            for(int i = 0; i <= 5; i++){
                                FlightScheduleTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }else{
                            // Xử lý khi nhấn No
                        }
                    }
                }
            }
        });
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Get the new data and add it to the table
            if(fscid.getText().isEmpty() == true || dpa.getSelectedItem().toString().isEmpty()==true
                || arap.getSelectedItem().toString().isEmpty()==true || Check.Date_String(departureTime.getDate()).isEmpty() == true ||
                Check.Date_String(arrivalTime.getDate()).isEmpty() ==true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(flightScheduleBUS.checkID(fscid.getText())==true){
                        JOptionPane.showMessageDialog(null, "Sai mã cần xóa", "Error", JOptionPane.ERROR_MESSAGE);
    
                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                flightScheduleBUS.delete(fscid.getText());
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
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình","Nơi đi", "Nơi đến", "Ngày đi", "Ngày đến","Giờ lên máy bay"}, 0);
                            for (FlightSchedule flightSchedule : list_FlightSchedules) {
                                tableModel.addRow(new Object[]{flightSchedule.getFlight_schedule_id(), flightSchedule.getDepartureAirport(),flightSchedule.getArrivalAirport(),flightSchedule.getDepartureDate(),flightSchedule.getArrivalDate(),flightSchedule.getBoartime()});
                            }
                            FlightScheduleTable.setModel(tableModel);
                            for(int i = 0; i <= 5; i++){
                                FlightScheduleTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }else{
                            // Xử lý khi nhấn No
                        }
                    }
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Get the new data and add it to the table
            if(fscid.getText().isEmpty() == true || dpa.getSelectedItem().toString().isEmpty()==true
                || arap.getSelectedItem().toString().isEmpty()==true || Check.Date_String(departureTime.getDate()).isEmpty() == true ||
                Check.Date_String(arrivalTime.getDate()).isEmpty() ==true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(flightScheduleBUS.checkID(fscid.getText())==true){
                        JOptionPane.showMessageDialog(null, "Sai mã cần xóa", "Error", JOptionPane.ERROR_MESSAGE);
    
                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                FlightSchedule flightSchedule = new FlightSchedule(fscid.getText(), dpa.getSelectedItem().toString().substring(0, 3), arap.getSelectedItem().toString().substring(0, 3), Check.Date_String(departureTime.getDate()),Check.Date_String(arrivalTime.getDate()),hour.getSelectedItem().toString()+":"+minute.getSelectedItem().toString());
                                flightScheduleBUS.set(flightSchedule);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình","Nơi đi", "Nơi đến", "Ngày đi", "Ngày đến","Giờ lên máy bay"}, 0);
                            for (FlightSchedule flightSchedule : list_FlightSchedules) {
                                tableModel.addRow(new Object[]{flightSchedule.getFlight_schedule_id(), flightSchedule.getDepartureAirport(),flightSchedule.getArrivalAirport(),flightSchedule.getDepartureDate(),flightSchedule.getArrivalDate(),flightSchedule.getBoartime()});
                            }
                            FlightScheduleTable.setModel(tableModel);
                            for(int i = 0; i <= 5; i++){
                                FlightScheduleTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }else{
                            // Xử lý khi nhấn No
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
        gbc.weightx = 0.5;
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
        text = new JLabel("Nơi đi");
        textBar = new JTextField(15);

        fsid = new JLabel("Nơi đến");
        fsbar = new JTextField(10);

        daylb = new JLabel("Ngày đi");
        JDateChooser dbar = new JDateChooser();
        dbar.setDateFormatString("dd-MM-yyyy");
        dbar.setLocale(new Locale("vi", "VN"));
        dbar.setPreferredSize(new Dimension(130, 20));
        dbar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dbar.setFont(fontTextField);

        dayarr = new JLabel("Ngày đến");
        JDateChooser abar = new JDateChooser();
        abar.setDateFormatString("dd-MM-yyyy");
        abar.setLocale(new Locale("vi", "VN"));
        abar.setPreferredSize(new Dimension(130, 20));
        abar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        abar.setFont(fontTextField);

        // Create the search button
        searchButton = new JButton("Tìm kiếm");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textBar.getText().isEmpty() == true && fsbar.getText().isEmpty() == true &&
                Check.Date_String(dbar.getDate()).isEmpty() == true && Check.Date_String(dbar.getDate()).isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình","Nơi đi", "Nơi đến", "Ngày đi", "Ngày đến","Giờ lên máy bay"}, 0);
                    for (FlightSchedule flightSchedule : list_FlightSchedules) {
                        tableModel.addRow(new Object[]{flightSchedule.getFlight_schedule_id(), flightSchedule.getDepartureAirport(),flightSchedule.getArrivalAirport(),flightSchedule.getDepartureDate(),flightSchedule.getArrivalDate(),flightSchedule.getBoartime()});
                    }
                    FlightScheduleTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        FlightScheduleTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true || fsbar.getText().isEmpty() != true ||
                Check.Date_String(dbar.getDate()).isEmpty() != true || Check.Date_String(dbar.getDate()).isEmpty() != true){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình","Nơi đi", "Nơi đến", "Ngày đi", "Ngày đến","Giờ lên máy bay"}, 0);
                    for (FlightSchedule flightSchedule : list_FlightSchedules) {
                        if(flightSchedule.getDepartureAirport().startsWith(textBar.getText())||flightSchedule.getArrivalAirport().startsWith(fsbar.getText())||flightSchedule.getDepartureDate().equals(Check.Date_String(dbar.getDate()))||flightSchedule.getArrivalDate().equals(Check.Date_String(abar.getDate())))
                        tableModel.addRow(new Object[]{flightSchedule.getFlight_schedule_id(), flightSchedule.getDepartureAirport(),flightSchedule.getArrivalAirport(),flightSchedule.getDepartureDate(),flightSchedule.getArrivalDate(),flightSchedule.getBoartime()});
                    }
                    FlightScheduleTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        FlightScheduleTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
            }
        });
       
        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());

        panel.add(text);
        panel.add(textBar);
        panel.add(fsid);
        panel.add(fsbar);
        panel.add(daylb);
        panel.add(dbar);
        panel.add(dayarr);
        panel.add(abar);
        panel.add(searchButton);

        panelBot.add(panel, BorderLayout.NORTH);
        // panelBot.add(panelBot, BorderLayout.SOUTH);
        panelBot.add(new JScrollPane(FlightScheduleTable), BorderLayout.CENTER);

        // pack and center frame
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Thông tin lịch trình",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                titleFont, Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        panelCenter.setBorder(nameBorder);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new FlightScheduleGUI();
    }
}

