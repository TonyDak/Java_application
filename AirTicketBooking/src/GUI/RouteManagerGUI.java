package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.AirportBUS;
import BUS.BillBUS;
import BUS.RouteBUS;
import BUS.TicketBUS;
import DTO.Bill;
import DTO.Route;
import DTO.Ticket;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class RouteManagerGUI extends JPanel {
    private JComboBox<String> positionComboBox;
    // private JComboBox<String> employeeComboBox, customercombBox;
    private JTextField searchBar;
    private JButton searchButton;
    private JTable routeTable;

    private RouteBUS routeBUS;
    private AirportBUS airportBUS;

    public RouteManagerGUI() throws ClassNotFoundException, SQLException {
        setSize(900, 600);
        setVisible(true);

        routeBUS = new RouteBUS();
        ArrayList<Route> list_Routes = routeBUS.getList_Routes();
        airportBUS = new AirportBUS();

        // set Wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel(new GridBagLayout());

        // set size for panel
        panelTop.setPreferredSize(new Dimension(1100, 60));
        panelBot.setPreferredSize(new Dimension(1100, 300));
        panelCenter.setPreferredSize(new Dimension(900, 150));
        panelRight.setPreferredSize(new Dimension(200, 200));

        // add panel into wrapper
        wrapperPanel.add(panelTop, BorderLayout.NORTH);
        wrapperPanel.add(panelBot, BorderLayout.SOUTH);
        wrapperPanel.add(panelCenter, BorderLayout.CENTER);
        wrapperPanel.add(panelRight, BorderLayout.EAST);

        // add wrapper into frame
        add(wrapperPanel);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin lộ trình",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // add border to panelCenter
        panelCenter.setBorder(nameBorder);

        // set label for panelTop
        JLabel labelRouteMa = new JLabel();
        JLabel labelRouteIn = new JLabel();
        labelRouteMa.setText("QUẢN LÝ LỘ TRÌNH");
        // labelRouteIn.setText("THÔNG TIN HÓA ĐƠN");

        // set font for label
        labelRouteMa.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // Set positions of labels
        labelRouteMa.setHorizontalAlignment(JLabel.CENTER);
        labelRouteIn.setHorizontalAlignment(JLabel.LEFT);


        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelRouteMa);
        panelTop.add(labelRouteIn);

        // create label and jtextfiled in panelCenter
        JLabel route_id = new JLabel("Mã lộ trình:");
        route_id.setFont(fontLabel);
        JLabel origin = new JLabel("Điểm đi:");
        origin.setFont(fontLabel);
        JLabel destination = new JLabel("Điểm đến:");
        destination.setFont(fontLabel);

        String[] cities = airportBUS.listCity();
        ArrayList<String> cityList = new ArrayList<>(Arrays.asList(cities));

        // add label and text field into panelCenter
        panelCenter.setLayout(new GridLayout(3, 2, 5, 5));
        panelCenter.add(route_id);
        JTextField routrIDField = new JTextField();
        routrIDField.setFont(fontTextField);
        routrIDField.setEditable(false);;
        panelCenter.add(routrIDField);
        panelCenter.add(origin);
        JComboBox<String> originField = new JComboBox<>(cities);
        originField.setFont(fontTextField);
        panelCenter.add(originField);
        panelCenter.add(destination);
        JComboBox<String> destinationField = new JComboBox<>(cityList.toArray(new String[0]));
        destinationField.setFont(fontTextField);
        panelCenter.add(destinationField);

        

        originField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedCity = (String) e.getItem();
                    ArrayList<String> remainingCities = new ArrayList<>(cityList);
                    remainingCities.remove(selectedCity);
                    destinationField.removeAllItems();
                    for (String city : remainingCities) {
                        destinationField.addItem(city);
                    }
                }
            }
        });

        // add JTable for panelBot
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình", "Điểm đi", "Điểm đến"}, 0);
        for (Route route : list_Routes) {
            tableModel.addRow(new Object[]{route.getRoute_id(),route.getOrigin(),route.getDestination()});
        }
        routeTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        Font font = new Font("Arial", Font.PLAIN, 16);
        routeTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        routeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        routeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        routeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        routeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                   String id = target.getValueAt(row, 0).toString();
                   routrIDField.setText(id);
                   String diemdi = target.getValueAt(row, 1).toString();
                   for (int i = 0; i < originField.getItemCount(); i++) {
                       if(originField.getItemAt(i).toString().substring(4).equals(diemdi)){
                           originField.setSelectedIndex(i);
                       }
                   }                    
                   String diemden = target.getValueAt(row, 2).toString();
                   for (int i = 0; i < destinationField.getItemCount(); i++) {
                       if(destinationField.getItemAt(i).toString().substring(4).equals(diemden)){
                           destinationField.setSelectedIndex(i);
                       }
                   }

                }
            }
        });
        // create buttons for panelRight
        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(originField.getSelectedItem().toString().isEmpty()==true == true
                || destinationField.getSelectedItem().toString().isEmpty()==true == true
                || routeBUS.checkRouteID(routrIDField.getText()) == false){
                    if( originField.getSelectedItem().toString().isEmpty() == true
                    || destinationField.getSelectedItem().toString().isEmpty() == true){
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần thêm", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(routeBUS.checkRouteID(routrIDField.getText()) == false){
                        JOptionPane.showMessageDialog(null,"Trùng mã lộ trình", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm lộ trình!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        // Xử lý khi nhấn Yes
                        try {
                            Route route = new Route(originField.getSelectedItem().toString().substring(0, 3)+"-"+destinationField.getSelectedItem().toString().substring(0, 3), originField.getSelectedItem().toString().substring(4), destinationField.getSelectedItem().toString().substring(4));
                            routeBUS.add(route);
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình", "Điểm đi", "Điểm đến"}, 0);
                    
                        for (Route route : list_Routes) {
                                tableModel.addRow(new Object[]{route.getRoute_id(),route.getOrigin(),route.getDestination()});
                            }    
                            routeTable.setModel(tableModel);
                            for(int i = 0; i <= 2; i++){
                                routeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                    } else {
                        // Xử lý khi nhấn No hoặc đóng hộp thoại
                    }
                }
            }
        });
        JButton delButton = new JButton("Xóa");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(routrIDField.getText().isEmpty() == true || originField.getSelectedItem().toString().isEmpty() == true
                || destinationField.getSelectedItem().toString().isEmpty() == true
                ){
                    
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần thêm", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(routeBUS.checkRouteID(routrIDField.getText()) == true){
                        JOptionPane.showMessageDialog(null,"Mã lộ trình sai định dạng", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa lộ trình!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        // Xử lý khi nhấn Yes
                        try {
                            routeBUS.delete(routrIDField.getText());
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
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình", "Điểm đi", "Điểm đến"}, 0);
                    
                        for (Route route : list_Routes) {
                                tableModel.addRow(new Object[]{route.getRoute_id(),route.getOrigin(),route.getDestination()});
                            }    
                            routeTable.setModel(tableModel);
                            for(int i = 0; i <= 2; i++){
                                routeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
                if(originField.getSelectedItem().toString().isEmpty() == true
                || destinationField.getSelectedItem().toString().isEmpty() == true
                ){
                    
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần thêm", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    if(routeBUS.checkRouteID(routrIDField.getText()) == true){
                        JOptionPane.showMessageDialog(null,"Mã lộ trình sai định dạng", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa lộ trình!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        // Xử lý khi nhấn Yes
                        try {
                            Route route = new Route(originField.getSelectedItem().toString().substring(0, 3)+"-"+destinationField.getSelectedItem().toString().substring(0, 3), originField.getSelectedItem().toString().substring(4), destinationField.getSelectedItem().toString().substring(4));
                            routeBUS.set(route);
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình", "Điểm đi", "Điểm đến"}, 0);
                    
                        for (Route route : list_Routes) {
                                tableModel.addRow(new Object[]{route.getRoute_id(),route.getOrigin(),route.getDestination()});
                            }    
                            routeTable.setModel(tableModel);
                            for(int i = 0; i <= 2; i++){
                                routeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
        gbc.weighty = 1;

        // add buttons to panelRight

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRight.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRight.add(delButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRight.add(editButton, gbc);

        // create combobox for panelBot
        String[] positions = { "--Chọn tiêu chí--","Mã lộ trình","Điểm đi","Điểm đến"};
        positionComboBox = new JComboBox<>(positions);

        // create JtextField for panelBot searchBar
        searchBar = new JTextField(20);

        // Create the search button
        searchButton = new JButton("Tìm kiếm");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình", "Điểm đi", "Điểm đến"}, 0);
                    
                    for (Route route : list_Routes) {
                            tableModel.addRow(new Object[]{route.getRoute_id(),route.getOrigin(),route.getDestination()});
                        }    
                        routeTable.setModel(tableModel);
                        for(int i = 0; i <= 2; i++){
                            routeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    
                }
                if(positionComboBox.getSelectedItem().toString().equals("Mã lộ trình")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình", "Điểm đi", "Điểm đến"}, 0);
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{   
                    for (Route route : list_Routes) {
                            if(route.getRoute_id().startsWith(searchBar.getText())){
                                tableModel.addRow(new Object[]{route.getRoute_id(),route.getOrigin(),route.getDestination()});

                            }
                        }    
                        routeTable.setModel(tableModel);
                        for(int i = 0; i <= 2; i++){
                            routeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Điểm đi")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình", "Điểm đi", "Điểm đến"}, 0);
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{    
                    for (Route route : list_Routes) {
                            if(route.getOrigin().startsWith(searchBar.getText())){
                                tableModel.addRow(new Object[]{route.getRoute_id(),route.getOrigin(),route.getDestination()});

                            }
                        }    
                        routeTable.setModel(tableModel);
                        for(int i = 0; i <= 2; i++){
                            routeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Điểm đến")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã lộ trình", "Điểm đi", "Điểm đến"}, 0);
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{    
                    for (Route route : list_Routes) {
                            if(route.getDestination().startsWith(searchBar.getText())){
                                tableModel.addRow(new Object[]{route.getRoute_id(),route.getOrigin(),route.getDestination()});

                            }
                        }    
                        routeTable.setModel(tableModel);
                        for(int i = 0; i <= 2; i++){
                            routeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                }
            }
        });

        // create searchLabel add to panelBot
        JLabel searchLabel = new JLabel("Tìm kiếm");
        // add to panelBot
        // panelBot = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());
        panel.add(searchLabel);
        panel.add(positionComboBox);
        panel.add(searchBar);
        panel.add(searchButton);
        panelBot.add(panel, BorderLayout.NORTH);
        // panelBot.add(panelBot, BorderLayout.SOUTH);
        panelBot.add(new JScrollPane(routeTable), BorderLayout.CENTER);

    }
}
