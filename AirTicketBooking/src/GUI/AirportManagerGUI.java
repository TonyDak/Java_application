package GUI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.AirportBUS;
import DTO.Airport;

public class AirportManagerGUI extends JPanel {
    private JButton searchButton;
    private JTable AirplaneTable;

    private AirportBUS airportBUS;

    public AirportManagerGUI() throws ClassNotFoundException, SQLException {
        setSize(800, 460);
        setVisible(true);

        airportBUS = new AirportBUS();
        ArrayList<Airport> list_Airports = airportBUS.getList_Airports();
        //ArrayList<
        // set Wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel(new GridBagLayout());

        panelTop.setPreferredSize(new Dimension(1100, 60));
        panelBot.setPreferredSize(new Dimension(1100, 300));
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
        JLabel labelAirplaneEm = new JLabel();
        JLabel labelAirplaneInfo = new JLabel();
        labelAirplaneEm.setText("QUẢN LÝ SÂN BAY");

        // set font for label
        labelAirplaneEm.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // Set positions of labels
        labelAirplaneEm.setHorizontalAlignment(JLabel.CENTER);
        labelAirplaneInfo.setHorizontalAlignment(JLabel.LEFT);


        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelAirplaneEm);
        panelTop.add(labelAirplaneInfo);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        // create label and jtextfiled in panelCenter
        JLabel airport_id = new JLabel("Mã sân bay:");
        airport_id.setFont(fontLabel);
        JLabel name = new JLabel("Tên sân bay:");
        name.setFont(fontLabel);
        JLabel city = new JLabel("Thành phố");
        city.setFont(fontLabel);
        JLabel country = new JLabel("Quốc gia:");
        country.setFont(fontLabel);


        // add label and text field into panelCenter
        JTextField apidbar = new JTextField();
        apidbar.setFont(fontTextField);
        JTextField nameapbar = new JTextField();
        nameapbar.setFont(fontTextField);
        JTextField citybar = new JTextField();
        citybar.setFont(fontTextField);
        JTextField countrybar = new JTextField();
        countrybar.setFont(fontTextField);
        panelCenter.setLayout(new GridLayout(4, 2));
        panelCenter.add(airport_id);
        panelCenter.add(apidbar);
        panelCenter.add(name);
        panelCenter.add(nameapbar);
        panelCenter.add(city);
        panelCenter.add(citybar);
        panelCenter.add(country);
        panelCenter.add(countrybar);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
        for (Airport airport : list_Airports) {
            tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});

        }
        AirplaneTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        Font font = new Font("Arial", Font.PLAIN, 16);
        AirplaneTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        AirplaneTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        AirplaneTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        AirplaneTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        AirplaneTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        AirplaneTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                    String id = target.getValueAt(row, 0).toString();
                    apidbar.setText(id);
                    String namea = target.getValueAt(row, 1).toString();
                    nameapbar.setText(namea);
                    String citya = target.getValueAt(row, 2).toString();
                    citybar.setText(citya);
                    String countrya = target.getValueAt(row, 3).toString();
                    countrybar.setText(countrya);

                }
            }
        });

        JButton addButton = new JButton("Thêm");
        JButton delButton = new JButton("Xóa");
        JButton editButton = new JButton("Sửa");

        // Create a JButton for adding new data

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Get the new data and add it to the table
            if(apidbar.getText().isEmpty()==true || nameapbar.getText().isEmpty()==true 
            || countrybar.getText().isEmpty()==true || citybar.getText().isEmpty()==true){
                JOptionPane.showMessageDialog(null, "Chưa nhập thông tin", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                if(airportBUS.checkID(apidbar.getText())==false){
                    JOptionPane.showMessageDialog(null, "Trùng mã cần thêm", "Error", JOptionPane.ERROR_MESSAGE);

                }else{
                int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        // Xử lý khi nhấn Yes
                        try {
                            Airport airport = new Airport(apidbar.getText(), nameapbar.getText(), citybar.getText(), countrybar.getText());
                            airportBUS.add(airport);
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
                        for (Airport airport : list_Airports) {
                            tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});

                        }
                        AirplaneTable.setModel(tableModel);
                        AirplaneTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                    }else{
                        // Xử lý khi nhấn No
                    }
                }
            }
            }
        });
     
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(apidbar.getText().isEmpty()==true || nameapbar.getText().isEmpty()==true 
                || countrybar.getText().isEmpty()==true || citybar.getText().isEmpty()==true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(airportBUS.checkID(apidbar.getText())==true){
                        JOptionPane.showMessageDialog(null, "Sai mã cần xóa", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                Airport airport = new Airport(apidbar.getText(), nameapbar.getText(), citybar.getText(), countrybar.getText());
                                airportBUS.set(airport);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
                        for (Airport airport : list_Airports) {
                            tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});

                        }
                        AirplaneTable.setModel(tableModel);
                        AirplaneTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                        }else{
                            // Xử lý khi nhấn No
                        }
                    }
                }
            }
        });

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(apidbar.getText().isEmpty()==true || nameapbar.getText().isEmpty()==true 
                || countrybar.getText().isEmpty()==true || citybar.getText().isEmpty()==true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(airportBUS.checkID(apidbar.getText())==true){
                        JOptionPane.showMessageDialog(null, "Sai mã cần xóa", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                airportBUS.delete(apidbar.getText());
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
                        for (Airport airport : list_Airports) {
                            tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});

                        }
                        AirplaneTable.setModel(tableModel);
                        AirplaneTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                        AirplaneTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
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

        panelRight.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRight.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRight.add(delButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRight.add(editButton, gbc);


        String[] positions = { "--Chọn tiêu chí--", "Mã sân bay", "Tên sân bay", "Tên thành phố", "Quốc gia"};
        JComboBox<String> positionComboBox = new JComboBox<>(positions);
        JTextField searchBar = new JTextField(20);


        searchButton = new JButton("Tìm kiếm");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
                    for (Airport airport : list_Airports) {
                        tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});
            
                    }
                    AirplaneTable.setModel(tableModel);
                    for (int i = 0; i <= 3; i++) {
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

                    }
                        
                }
                   
               if(positionComboBox.getSelectedItem().toString().equals("Mã sân bay")){
                if(searchBar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
                    for (Airport airport : list_Airports) {
                        if(searchBar.getText().equals(airport.getAirport_id()))
                        tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});
                    }
                    AirplaneTable.setModel(tableModel);
                    for (int i = 0; i <= 3; i++) {
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

                    }
                    
                }
               }
               if(positionComboBox.getSelectedItem().toString().equals("Tên sân bay")){
                if(searchBar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
                    for (Airport airport : list_Airports) {
                        if(searchBar.getText().equals(airport.getName()))
                        tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});
            
                    }
                    AirplaneTable.setModel(tableModel);
                    for (int i = 0; i <= 3; i++) {
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

                    }
                    
                }
               }
               if(positionComboBox.getSelectedItem().toString().equals("Tên thành phố")){
                if(searchBar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
                    for (Airport airport : list_Airports) {
                        if(searchBar.getText().equals(airport.getCity()))
                        tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});
            
                    }
                    AirplaneTable.setModel(tableModel);
                    for (int i = 0; i <= 3; i++) {
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

                    }
                    
                }
               }
               if(positionComboBox.getSelectedItem().toString().equals("Quốc gia")){
                if(searchBar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã sân bay","Tên sân bay","Thành phố","Quốc gia"}, 0);
                    for (Airport airport : list_Airports) {
                        if(searchBar.getText().equals(airport.getCountry()))
                        tableModel.addRow(new Object[]{airport.getAirport_id(),airport.getName(),airport.getCity(),airport.getCountry()});
            
                    }
                    AirplaneTable.setModel(tableModel);
                    for (int i = 0; i <= 3; i++) {
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

                    }
                    
                }
               }
            }
        });


        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());

        panel.add(positionComboBox);
        panel.add(searchBar);
        panel.add(searchButton);

        panelBot.add(panel, BorderLayout.NORTH);
        panelBot.add(new JScrollPane(AirplaneTable), BorderLayout.CENTER);

        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin sân bay",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        panelCenter.setBorder(nameBorder);
    }
}