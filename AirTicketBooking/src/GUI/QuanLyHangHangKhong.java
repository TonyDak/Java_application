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

import BUS.AirplaneBUS;
import DTO.Airplane;

public class QuanLyHangHangKhong extends JPanel {

    // private JComboBox<String> employeeComboBox, customercombBox;
    private JLabel text, apid;
    private JTextField textBar, apbar, namebar;
    private JButton searchButton;
    private JTable AirplaneTable;

    private AirplaneBUS airplaneBUS;

    public QuanLyHangHangKhong() throws ClassNotFoundException, SQLException{
        setSize(800, 460);
        setVisible(true);
        airplaneBUS = new AirplaneBUS();
        ArrayList<Airplane> list_Airplanes = airplaneBUS.getList_Airplanes();

        // set Wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel(new GridBagLayout());
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Thông tin hãng hàng không",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                titleFont, Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        panelCenter.setBorder(nameBorder);


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

        // set label for panelTop
        JLabel labelAirplaneEm = new JLabel();
        JLabel labelAirplaneInfo = new JLabel();
        labelAirplaneEm.setText("QUẢN LÝ HÃNG HÀNG KHÔNG");

        // set font for label
        labelAirplaneEm.setFont(new Font("Times New Roman", Font.BOLD, 24));
        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);


        // Set positions of labels
        labelAirplaneEm.setHorizontalAlignment(JLabel.CENTER);
        labelAirplaneInfo.setHorizontalAlignment(JLabel.LEFT);


        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelAirplaneEm);
        panelTop.add(labelAirplaneInfo);

        // create label and jtextfiled in panelCenter
        JLabel airplane_id = new JLabel("Mã hãng bay:");
        airplane_id.setFont(fontLabel);
        JLabel name = new JLabel("Tên hãng hàng không:");
        name.setFont(fontLabel);
        JLabel country = new JLabel("Quốc gia:");
        country.setFont(fontLabel);
        
        // add label and text field into panelCenter
        panelCenter.setLayout(new GridLayout(3, 2,5,5));
        panelCenter.add(airplane_id);
        JTextField airplaneIDField = new JTextField();
        airplaneIDField.setFont(fontTextField);
        panelCenter.add(airplaneIDField);
        panelCenter.add(name);
        JTextField nameField = new JTextField();
        nameField.setFont(fontTextField);
        panelCenter.add(nameField);
        panelCenter.add(country);
        JTextField countryField = new JTextField();
        countryField.setFont(fontTextField);
        panelCenter.add(countryField);

         // add JTable for panelBot
         DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
         for (Airplane airplane : list_Airplanes) {
            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
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
        AirplaneTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                   String id = target.getValueAt(row, 0).toString();
                   airplaneIDField.setText(id);
                   String origin = target.getValueAt(row, 1).toString();
                   nameField.setText(origin);
                   String destination = target.getValueAt(row, 2).toString();
                   countryField.setText(destination);

                }
            }
        });
        // create buttons for panelRight
        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(airplaneIDField.getText().isEmpty() == true || nameField.getText().isEmpty() == true
                || countryField.getText().isEmpty() == true
                ||airplaneBUS.checkAirplaneID(airplaneIDField.getText()) == false){
                    if(airplaneIDField.getText().isEmpty() == true || nameField.getText().isEmpty() == true
                    || countryField.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần thêm", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(airplaneBUS.checkAirplaneID(airplaneIDField.getText()) == false){
                        JOptionPane.showMessageDialog(null,"Trùng mã hàng không", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                    
                }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm hãng hàng không!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        // Xử lý khi nhấn Yes
                        try {
                            Airplane airplane = new Airplane(airplaneIDField.getText(),nameField.getText(),countryField.getText());
                            airplaneBUS.add(airplane);
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);

                        for (Airplane airplane : list_Airplanes) {
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                        AirplaneTable.setModel(tableModel);
                        for(int i = 0; i <= 2; i++){
                            AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
                if(airplaneIDField.getText().isEmpty() == true || nameField.getText().isEmpty() == true
                || countryField.getText().isEmpty() == true
                ){
                    if(airplaneIDField.getText().isEmpty() == true || nameField.getText().isEmpty() == true
                    || countryField.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần thêm", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }else{
                    if(airplaneBUS.checkAirplaneID(airplaneIDField.getText()) == true){
                        JOptionPane.showMessageDialog(null,"Sai mã hàng không", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa hãng hàng không!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        // Xử lý khi nhấn Yes
                        try {
                            airplaneBUS.delete(airplaneIDField.getText());
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);

                        for (Airplane airplane : list_Airplanes) {
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                        AirplaneTable.setModel(tableModel);
                        for(int i = 0; i <= 2; i++){
                            AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
                if(airplaneIDField.getText().isEmpty() == true || nameField.getText().isEmpty() == true
                || countryField.getText().isEmpty() == true
                ){
                    JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần sửa", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    if(airplaneBUS.checkAirplaneID(airplaneIDField.getText()) == false){
                            int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm hãng hàng không!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                Airplane airplane = new Airplane(airplaneIDField.getText(),nameField.getText(),countryField.getText());
                                airplaneBUS.set(airplane);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);

                            for (Airplane airplane : list_Airplanes) {
                                tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                            }
                            AirplaneTable.setModel(tableModel);
                            for(int i = 0; i <= 2; i++){
                                AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Sai mã hàng không", "Error", JOptionPane.ERROR_MESSAGE);

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
        gbc.gridy = 1;
        panelRight.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRight.add(delButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRight.add(editButton, gbc);

       

        // create combobox for panelBot

        // create JtextField for panelBot textBar,apbar,namebar
        text = new JLabel("Mã hãng hàng không:");
        textBar = new JTextField(15);

        apid = new JLabel("Tên hãng hàng không:");
        apbar = new JTextField(10);

        name = new JLabel("Quốc gia:");
        namebar = new JTextField(10);

        // Create the search button
        searchButton = new JButton("Tìm kiếm");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textBar.getText().isEmpty() == true && apbar.getText().isEmpty() == true &&
                namebar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(QuanLyHangHangKhong.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
                    for (Airplane airplane : list_Airplanes) {
                        tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                    }
                    AirplaneTable.setModel(tableModel);
                    for(int i = 0; i <= 2; i++){
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true && apbar.getText().isEmpty() == true &&
                namebar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(QuanLyHangHangKhong.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
                    for (Airplane airplane : list_Airplanes) {
                        if(airplane.getAirplane_id().startsWith(textBar.getText())){
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                    }
                    AirplaneTable.setModel(tableModel);
                    for(int i = 0; i <= 2; i++){
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() == true && apbar.getText().isEmpty() != true &&
                namebar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(QuanLyHangHangKhong.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
                    for (Airplane airplane : list_Airplanes) {
                        if(airplane.getName().startsWith(apbar.getText())){
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                    }
                    AirplaneTable.setModel(tableModel);
                    for(int i = 0; i <= 2; i++){
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() == true && apbar.getText().isEmpty() == true &&
                namebar.getText().isEmpty() != true){
                    JOptionPane.showMessageDialog(QuanLyHangHangKhong.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
                    for (Airplane airplane : list_Airplanes) {
                        if(airplane.getCountry().startsWith(namebar.getText())){
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                    }
                    AirplaneTable.setModel(tableModel);
                    for(int i = 0; i <= 2; i++){
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true && apbar.getText().isEmpty() != true &&
                namebar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(QuanLyHangHangKhong.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
                    for (Airplane airplane : list_Airplanes) {
                        if(airplane.getAirplane_id().startsWith(textBar.getText())&&airplane.getName().startsWith(apbar.getText())){
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                    }
                    AirplaneTable.setModel(tableModel);
                    for(int i = 0; i <= 2; i++){
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true && apbar.getText().isEmpty() == true &&
                namebar.getText().isEmpty() != true){
                    JOptionPane.showMessageDialog(QuanLyHangHangKhong.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
                    for (Airplane airplane : list_Airplanes) {
                        if(airplane.getAirplane_id().startsWith(textBar.getText())&&airplane.getCountry().startsWith(namebar.getText())){
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                    }
                    AirplaneTable.setModel(tableModel);
                    for(int i = 0; i <= 2; i++){
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() == true && apbar.getText().isEmpty() != true &&
                namebar.getText().isEmpty() != true){
                    JOptionPane.showMessageDialog(QuanLyHangHangKhong.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
                    for (Airplane airplane : list_Airplanes) {
                        if(airplane.getName().startsWith(apbar.getText())&&airplane.getCountry().startsWith(namebar.getText())){
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                    }
                    AirplaneTable.setModel(tableModel);
                    for(int i = 0; i <= 2; i++){
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true && apbar.getText().isEmpty() != true &&
                namebar.getText().isEmpty() != true){
                    JOptionPane.showMessageDialog(QuanLyHangHangKhong.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã hãng hàng không", "Tên hãng hàng không", "Quốc gia"}, 0);
                    for (Airplane airplane : list_Airplanes) {
                        if(airplane.getAirplane_id().startsWith(textBar.getText())&&airplane.getName().startsWith(apbar.getText())&&airplane.getCountry().startsWith(namebar.getText())){
                            tableModel.addRow(new Object[]{airplane.getAirplane_id(),airplane.getName(),airplane.getCountry()});
                        }
                    }
                    AirplaneTable.setModel(tableModel);
                    for(int i = 0; i <= 2; i++){
                        AirplaneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
            }
        });
       
        // panelBot = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());

        panel.add(text);
        panel.add(textBar);
        panel.add(apid);
        panel.add(apbar);
        panel.add(name);
        panel.add(namebar);
        panel.add(searchButton);

        panelBot.add(panel, BorderLayout.NORTH);
        // panelBot.add(panelBot, BorderLayout.SOUTH);
        panelBot.add(new JScrollPane(AirplaneTable), BorderLayout.CENTER);

        
    }
}
