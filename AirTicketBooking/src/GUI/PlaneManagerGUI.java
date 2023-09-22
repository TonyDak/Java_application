package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
import BUS.BillBUS;
import BUS.PlaneBUS;
import BUS.TicketBUS;
import DTO.Airplane;
import DTO.Bill;
import DTO.Plane;
import DTO.Ticket;

public class PlaneManagerGUI extends JPanel {

    // private JComboBox<String> employeeComboBox, customercombBox;
    private JComboBox<String> airplane_idBox;
    private JTable planeTable;

    private AirplaneBUS airplaneBUS;
    private PlaneBUS planeBUS;

    public PlaneManagerGUI() throws ClassNotFoundException, SQLException {
        // JFrame frame = new JFrame();
        setSize(900, 500);
        setVisible(true);

        airplaneBUS = new AirplaneBUS();
        ArrayList<Airplane> list_Airplanes = airplaneBUS.getList_Airplanes();
        planeBUS = new PlaneBUS();
        ArrayList<Plane> list_Planes = planeBUS.getList_Planes();

        // set Wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel(new GridBagLayout());

        Font titleFont = new Font("Segoe UI", 3, 24);
        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin máy bay",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
    
       

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
        JLabel labelBillEm = new JLabel();
        JLabel labelBillInfo = new JLabel();
        labelBillEm.setText("QUẢN LÝ MÁY BAY");

        // set font for label
        labelBillEm.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // Set positions of labels
        labelBillEm.setHorizontalAlignment(JLabel.CENTER);
        labelBillInfo.setHorizontalAlignment(JLabel.LEFT);

        // create panel and set border
        panelCenter.setBorder(nameBorder);

        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelBillEm);
        panelTop.add(labelBillInfo);

        // create label and jtextfiled in panelCenter
        JLabel plane_id = new JLabel("Mã máy bay:");
        plane_id.setFont(fontLabel);
        JLabel plane_model = new JLabel("Tên máy bay:");
        plane_model.setFont(fontLabel);
        JLabel airplane_id = new JLabel("Mã hãng máy bay:");
        airplane_id.setFont(fontLabel);
        JLabel eco_seats = new JLabel("Số lượng ghế ECO:");
        eco_seats.setFont(fontLabel);
        JLabel busi_seats = new JLabel("Số lượng ghế Busi:");
        busi_seats.setFont(fontLabel);

        airplane_idBox = new JComboBox<>();
        // create combobox for panelBot
        airplane_idBox = new JComboBox<>();
        airplane_idBox.setPreferredSize(new Dimension(500, 20));
        airplane_idBox.addItem("--Mã hãng--");
        for (Airplane a : list_Airplanes) {
            airplane_idBox.addItem(a.getAirplane_id()+" - "+a.getName());
        }

        // add label and text field into panelCenter
        panelCenter.setLayout(new GridLayout(5, 2, 5, 5));
        panelCenter.add(plane_id);
        JTextField planeIDField = new JTextField();
        planeIDField.setFont(fontTextField);
        panelCenter.add(planeIDField);
        panelCenter.add(plane_model);
        JTextField planemodelField = new JTextField();
        planemodelField.setFont(fontTextField);
        panelCenter.add(planemodelField);
        panelCenter.add(airplane_id);
        airplane_idBox.setFont(fontTextField);
        panelCenter.add(airplane_idBox);
        panelCenter.add(eco_seats);
        JTextField ecoField = new JTextField();
        ecoField.setFont(fontTextField);
        ecoField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Check.validateNumber(ecoField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Check.validateNumber(ecoField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Check.validateNumber(ecoField);
            }
        });
        panelCenter.add(ecoField);
        panelCenter.add(busi_seats);
        JTextField busField = new JTextField();
        busField.setFont(fontTextField);
        busField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Check.validateNumber(busField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Check.validateNumber(busField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Check.validateNumber(busField);
            }
        });
        panelCenter.add(busField);

        // add JTable for panelBot
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã máy bay", "Mã hãng", "Model", "Eco seats", "Bus seats"}, 0);
        for (Plane plane : list_Planes) {
            tableModel.addRow(new Object[]{plane. getPlane_id(),plane.getAirplane_id(),plane.getPlane_model(),plane.getEco_seats(),plane.getBusi_seats()});
        }
        planeTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        Font font = new Font("Arial", Font.PLAIN, 16);
        planeTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        planeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        planeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        planeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        planeTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        planeTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        planeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                   String id = target.getValueAt(row, 0).toString();
                   planeIDField.setText(id);
                   String airplane_id = target.getValueAt(row, 1).toString();
                   for (int i = 0; i < airplane_idBox.getItemCount(); i++) {
                        if(airplane_idBox.getItemAt(i).toString().startsWith(airplane_id)){
                            airplane_idBox.setSelectedIndex(i);
                        }
                    }
                   String plane_model = target.getValueAt(row, 2).toString();
                   planemodelField.setText(plane_model);
                   String eco_seats = target.getValueAt(row, 3).toString();
                   ecoField.setText(eco_seats);
                   String bus_seats = target.getValueAt(row, 4).toString();
                   busField.setText(bus_seats);
                }
            }
        });
        // create buttons for panelRight
        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(planeIDField.getText().isEmpty() == true || airplane_idBox.getSelectedItem().toString().equals("--Mã hãng--")
                || planemodelField.getText().isEmpty() == true || ecoField.getText().isEmpty() == true||
                busField.getText().isEmpty() == true ||planeBUS.checkPlaneID(planeIDField.getText()) == false){
                    if(planeIDField.getText().isEmpty() == true || airplane_idBox.getSelectedItem().toString().equals("--Mã hãng--")
                    || planemodelField.getText().isEmpty() == true || ecoField.getText().isEmpty() == true||
                    busField.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần thêm", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                    
                    if(planeBUS.checkPlaneID(planeIDField.getText()) == false){
                        JOptionPane.showMessageDialog(null,"Trùng mã máy bay", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                    
                }else{
                    if(Check.isNumber(busField.getText()) == false
                    ||Check.isNumber(ecoField.getText()) == false){
                        if(Check.isNumber(ecoField.getText()) == false){
                            JOptionPane.showMessageDialog(null,"Error nhập ghế ECO", "Error", JOptionPane.ERROR_MESSAGE);
    
                        }if(Check.isNumber(busField.getText()) == false){
                            JOptionPane.showMessageDialog(null,"Error nhập ghế BUS", "Error", JOptionPane.ERROR_MESSAGE);
    
                        }
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm máy bay!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                int index = airplane_idBox.getSelectedItem().toString().indexOf("-");
                                Plane plane = new Plane(planeIDField.getText(), planemodelField.getText(), Integer.parseInt(ecoField.getText()), Integer.parseInt(busField.getText()), airplane_idBox.getSelectedItem().toString().substring(0, index));
                                planeBUS.add(plane);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã máy bay", "Mã hãng", "Model", "Eco seats", "Bus seats"}, 0);
                            for (Plane plane : list_Planes) {
                                tableModel.addRow(new Object[]{plane. getPlane_id(),plane.getAirplane_id(),plane.getPlane_model(),plane.getEco_seats(),plane.getBusi_seats()});
                            }    
                            planeTable.setModel(tableModel);
                            for(int i = 0; i <= 4; i++){
                                planeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
                if(planeIDField.getText().isEmpty() == true || airplane_idBox.getSelectedItem().toString().equals("--Mã hãng--")
                || planemodelField.getText().isEmpty() == true || ecoField.getText().isEmpty() == true||
                busField.getText().isEmpty() == true ){
                    
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần thêm", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    if(planeBUS.checkPlaneID(planeIDField.getText()) == true){
                        JOptionPane.showMessageDialog(null,"Sai mã máy bay", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                    if(Check.isNumber(busField.getText()) == false
                    ||Check.isNumber(ecoField.getText()) == false){
                        if(Check.isNumber(ecoField.getText()) == false){
                            JOptionPane.showMessageDialog(null,"Error nhập ghế ECO", "Error", JOptionPane.ERROR_MESSAGE);
    
                        }if(Check.isNumber(busField.getText()) == false){
                            JOptionPane.showMessageDialog(null,"Error nhập ghế BUS", "Error", JOptionPane.ERROR_MESSAGE);
    
                        }
                    }else{
                    int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa máy bay!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        // Xử lý khi nhấn Yes
                        try {
                            planeBUS.delete(planeIDField.getText());
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
                        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã máy bay", "Mã hãng", "Model", "Eco seats", "Bus seats"}, 0);
                        for (Plane plane : list_Planes) {
                            tableModel.addRow(new Object[]{plane. getPlane_id(),plane.getAirplane_id(),plane.getPlane_model(),plane.getEco_seats(),plane.getBusi_seats()});
                        }    
                        planeTable.setModel(tableModel);
                        for(int i = 0; i <= 4; i++){
                            planeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    } else {
                        // Xử lý khi nhấn No hoặc đóng hộp thoại
                    }
                }
                }
                }
            }
        });
        JButton editButton = new JButton("Sửa");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(planeIDField.getText().isEmpty() == true || airplane_idBox.getSelectedItem().toString().equals("--Mã hãng--")
                || planemodelField.getText().isEmpty() == true || ecoField.getText().isEmpty() == true||
                busField.getText().isEmpty() == true && Check.isNumber(busField.getText()) == false
                ||Check.isNumber(ecoField.getText()) == false){
                   
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin cần thêm", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    if(planeBUS.checkPlaneID(planeIDField.getText()) == true){
                        JOptionPane.showMessageDialog(null,"Sai mã máy bay", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        if(Check.isNumber(busField.getText()) == false
                        ||Check.isNumber(ecoField.getText()) == false){
                            if(Check.isNumber(ecoField.getText()) == false){
                                JOptionPane.showMessageDialog(null,"Error nhập ghế ECO", "Error", JOptionPane.ERROR_MESSAGE);
        
                            }if(Check.isNumber(busField.getText()) == false){
                                JOptionPane.showMessageDialog(null,"Error nhập ghế BUS", "Error", JOptionPane.ERROR_MESSAGE);
        
                            }
                        }else{
                            int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa thông tin máy bay!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION){
                                // Xử lý khi nhấn Yes
                                try {
                                    int index = airplane_idBox.getSelectedItem().toString().indexOf("-");
                                    Plane plane = new Plane(planeIDField.getText(), planemodelField.getText(), Integer.parseInt(ecoField.getText()), Integer.parseInt(busField.getText()), airplane_idBox.getSelectedItem().toString().substring(0, index));
                                    planeBUS.set(plane);
                                } catch (ClassNotFoundException | SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã máy bay", "Mã hãng", "Model", "Eco seats", "Bus seats"}, 0);
                        for (Plane plane : list_Planes) {
                            tableModel.addRow(new Object[]{plane. getPlane_id(),plane.getAirplane_id(),plane.getPlane_model(),plane.getEco_seats(),plane.getBusi_seats()});
                        }    
                        planeTable.setModel(tableModel);
                        for(int i = 0; i <= 4; i++){
                            planeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                            } else {
                                // Xử lý khi nhấn No hoặc đóng hộp thoại
                            }
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

        // JButton eraseButton = new JButton("Làm rỗng");
        // JButton exitButton = new JButton("Thoát");

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

        // create combobox for panelBot

        // create JtextField for panelBot textBar,ecoBar,busiBar
        String[] positions = { "--Chọn tiêu chí--", "Mã máy bay", "Mã hãng", "Model" };
        JComboBox<String> positionComboBox = new JComboBox<>(positions);

        // create JtextField for panelBot searchBar
        JTextField searchBar = new JTextField(20);

        // Create the search button
        JButton searchButton = new JButton("Tìm kiếm ");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã máy bay", "Mã hãng", "Model", "Eco seats", "Bus seats"}, 0);
                        for (Plane plane : list_Planes) {
                            tableModel.addRow(new Object[]{plane. getPlane_id(),plane.getAirplane_id(),plane.getPlane_model(),plane.getEco_seats(),plane.getBusi_seats()});
                        }    
                        planeTable.setModel(tableModel);
                        for(int i = 0; i <= 4; i++){
                            planeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Mã máy bay")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã máy bay", "Mã hãng", "Model", "Eco seats", "Bus seats"}, 0);
                        for (Plane plane : list_Planes) {
                            if(plane.getPlane_id().startsWith(searchBar.getText())){
                                tableModel.addRow(new Object[]{plane. getPlane_id(),plane.getAirplane_id(),plane.getPlane_model(),plane.getEco_seats(),plane.getBusi_seats()});
                            }
                        }    
                        planeTable.setModel(tableModel);
                        for(int i = 0; i <= 4; i++){
                            planeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Mã hãng")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã máy bay", "Mã hãng", "Model", "Eco seats", "Bus seats"}, 0);
                        for (Plane plane : list_Planes) {
                            if(plane.getAirplane_id().startsWith(searchBar.getText())){
                                tableModel.addRow(new Object[]{plane. getPlane_id(),plane.getAirplane_id(),plane.getPlane_model(),plane.getEco_seats(),plane.getBusi_seats()});
                            }
                        }    
                        planeTable.setModel(tableModel);
                        for(int i = 0; i <= 4; i++){
                            planeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                }
                if(positionComboBox.getSelectedItem().toString().equals("Model")){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Mã máy bay", "Mã hãng", "Model", "Eco seats", "Bus seats"}, 0);
                        for (Plane plane : list_Planes) {
                            if(plane.getPlane_model().startsWith(searchBar.getText())){
                                tableModel.addRow(new Object[]{plane. getPlane_id(),plane.getAirplane_id(),plane.getPlane_model(),plane.getEco_seats(),plane.getBusi_seats()});
                            }
                        }    
                        planeTable.setModel(tableModel);
                        for(int i = 0; i <= 4; i++){
                            planeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                }
            }
        });

        // add to panelBot
        // panelBot = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());

        panel.add(positionComboBox);
        panel.add(searchBar);
        panel.add(searchButton);

        panelBot.add(panel, BorderLayout.NORTH);
        // panelBot.add(panelBot, BorderLayout.SOUTH);
        panelBot.add(new JScrollPane(planeTable), BorderLayout.CENTER);


    }
}
