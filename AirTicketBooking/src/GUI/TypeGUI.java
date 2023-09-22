package GUI;

import java.sql.SQLException;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.RoleFuncBUS2;
import BUS.ServiceBUS;
import BUS.TypeBUS;
import DTO.RoleFunc;
import DTO.ServiceDTO;
import DTO.Type;

public class TypeGUI extends JPanel {
    private JPanel typeManager;
    private JTable tickettypeTable;
    private ArrayList<JCheckBox> list_checkBox;
    private RoleFuncBUS2 roleFuncBUS2;
    private TypeBUS typeBUS;

    public TypeGUI() throws ClassNotFoundException, SQLException{
        // create the type panel
        ServiceBUS serviceBUS = new ServiceBUS();
        ArrayList<ServiceDTO> list_ServiceDTOs = serviceBUS.getList_ServiceDTOs();
        roleFuncBUS2 = new RoleFuncBUS2();
        ArrayList<RoleFunc> list_RoleFuncs = roleFuncBUS2.getList_RoleFunc();
        typeBUS = new TypeBUS();
        ArrayList<DTO.Type> list_Types = typeBUS.getList_Types();

        Font titleFont = new Font("Segoe UI", 3, 24);
        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);
        Font font = new Font("Arial", Font.PLAIN, 16);

        typeManager = new JPanel(new BorderLayout());
        JPanel paneltypeTop = new JPanel();
        JPanel paneltypeBot = new JPanel();
        JPanel paneltypeRight = new JPanel();
        JPanel paneltypeCenter = new JPanel(new GridBagLayout());
        Border nameBorder2 = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.GRAY), "Thông tin loại vé",
                    TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                    titleFont, Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5));

       paneltypeTop.setPreferredSize(new Dimension(1100, 60));
       paneltypeBot.setPreferredSize(new Dimension(1100, 300));
       paneltypeCenter.setPreferredSize(new Dimension(900, 250));
       paneltypeRight.setPreferredSize(new Dimension(200, 250));

        typeManager.add(paneltypeTop, BorderLayout.NORTH);
        typeManager.add(paneltypeBot, BorderLayout.SOUTH);
        typeManager.add(paneltypeCenter, BorderLayout.CENTER);
        typeManager.add(paneltypeRight, BorderLayout.EAST);

        add(typeManager);

        JLabel labelBillEm2 = new JLabel();
        JLabel labelBillInfo2 = new JLabel();
        labelBillEm2.setText("QUẢN LÝ LOẠI VÉ MÁY BAY");

        // set font for label
        labelBillEm2.setFont(new Font("Times New Roman", Font.BOLD, 24));

        labelBillEm2.setHorizontalAlignment(JLabel.CENTER);
        labelBillInfo2.setHorizontalAlignment(JLabel.LEFT);

        // create panel and set border
        paneltypeCenter.setBorder(nameBorder2);
        paneltypeTop.setLayout(new GridLayout(2, 1));
        paneltypeTop.add(labelBillEm2);
        paneltypeTop.add(labelBillInfo2);

        JLabel type_id2 = new JLabel("Mã kiểu");
        type_id2.setFont(fontLabel);
        JTextField typeField2 = new JTextField();
        typeField2.setFont(fontTextField);
        //typeField2.setText(typeBUS.CreateType());
        typeField2.setEditable(false);


        JLabel type_name = new JLabel("Tên kiểu ");
        type_name.setFont(fontLabel);
        JComboBox<String> nameBox = new JComboBox<>();
        nameBox.setFont(fontTextField);
        nameBox.addItem("Thương gia");
        nameBox.addItem("Phổ thông");

        // nameBox.addItemListener(new ItemListener() {
        //     @Override
        //     public void itemStateChanged(ItemEvent e) {
        //         // Nếu người dùng chọn 1 item trong JComboBox
        //         if (e.getStateChange() == ItemEvent.SELECTED) {
        //             // Lấy giá trị của item được chọn
        //             String selectedValue = nameBox.getSelectedItem().toString();
        
        //             // Xử lý sự kiện
        //            if(selectedValue == "Thương gia"){
        //             typeField2.setText(typeBUS.CreateTypeTG());
        //            }
        //            if(selectedValue == "Phổ thông"){
        //             typeField2.setText(typeBUS.CreateTypePT());
        //            }
        //         }
        //     }
        // });
        

        JLabel waitting_room = new JLabel("Dịch vụ");
        waitting_room.setFont(fontLabel);
        JButton waitField = new JButton("Chọn dịch vụ");
        waitField.setBackground(Color.white);

        JLabel type_price = new JLabel("Giá");
        type_price.setFont(fontLabel);
        JTextField priceField = new JTextField();
        priceField.setFont(fontTextField);
        priceField.setEditable(false);

        list_checkBox = new ArrayList<>();
        Border nameBorder3 = BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.GRAY), "",
                            TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                            titleFont, Color.BLACK),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5));
        waitField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame selectService = new JFrame("Service");
                selectService.setLayout(new BorderLayout());
                JLabel labelEm2 = new JLabel();
                JPanel panelService2 = new JPanel();
                labelEm2.setText("Dịch vụ Airplane");
                panelService2.setBackground(new Color(173,216,230));

                // set font for label
                labelEm2.setFont(new Font("Times New Roman", Font.BOLD, 24));
                labelEm2.setHorizontalAlignment(JLabel.CENTER);
                panelService2.add(labelEm2);
                selectService.add(panelService2, BorderLayout.NORTH);
                selectService.setSize(850, 900);
                selectService.setLocationRelativeTo(null);

                JPanel panelService = new JPanel(new GridLayout(3, 2, 5, 5));
                
                for(int i = 0; i < list_ServiceDTOs.size(); i++){
                    if(nameBox.getSelectedItem().toString().equals("Phổ thông") && list_ServiceDTOs.get(i).getDescription().startsWith("ECO")){
                        JCheckBox checkBoxn = new JCheckBox(list_ServiceDTOs.get(i).getDescription() +"-"+list_ServiceDTOs.get(i).getName()+"-"+list_ServiceDTOs.get(i).getPrice());
                        ImageIcon image = new ImageIcon(list_ServiceDTOs.get(i).getImage());
                        Image scaledImage = image.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        checkBoxn.setHorizontalTextPosition(SwingConstants.RIGHT); // Align the text to the right
                        checkBoxn.setHorizontalAlignment(SwingConstants.LEFT);
                        // Set kích thước cho image
                        JPanel paneltemp = new JPanel();
                        image.setImage(scaledImage);
                        checkBoxn.setIcon(image);
                        paneltemp.setBorder(nameBorder3);
                        list_checkBox.add(checkBoxn);
                        paneltemp.add(checkBoxn);
                        panelService.add(paneltemp);
                        checkBoxn.addItemListener(new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                // Nếu JCheckBox được tích vào
                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                    // Thay đổi màu text thành xanh lá
                                    checkBoxn.setForeground(Color.GREEN);
                                    checkBoxn.setSelected(true);
                                }else {
                                    // Thay đổi màu text về ban đầu
                                    checkBoxn.setForeground(Color.BLACK);
                                    checkBoxn.setSelected(false);
                                }
                            }
                        });
                        if(typeField2.getText().isEmpty() == false){
                            for (RoleFunc roleFunc : list_RoleFuncs) {
                                if(roleFunc.getRoleId().equals(typeField2.getText())){
                                    for (ServiceDTO funtions : list_ServiceDTOs) {
                                        if(roleFunc.getFunctionId().equals(funtions.getDescription())){
                                            for (JCheckBox cb : list_checkBox) {
                                                String[] parts = cb.getText().split("-");
                                                if(parts[0].equals(funtions.getDescription())){
                                                    cb.setSelected(true);
                                                    cb.setForeground(Color.GREEN);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        
                        }
                    }
                    if(nameBox.getSelectedItem().toString().equals("Thương gia") && list_ServiceDTOs.get(i).getDescription().startsWith("BUS")){
                        JCheckBox checkBoxn = new JCheckBox(list_ServiceDTOs.get(i).getDescription() +"-"+list_ServiceDTOs.get(i).getName()+"-"+list_ServiceDTOs.get(i).getPrice());
                        ImageIcon image = new ImageIcon(list_ServiceDTOs.get(i).getImage());
                        Image scaledImage = image.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        checkBoxn.setHorizontalTextPosition(SwingConstants.RIGHT); // Align the text to the right
                        checkBoxn.setHorizontalAlignment(SwingConstants.LEFT);
                        // Set kích thước cho image
                        JPanel paneltemp = new JPanel();
                        image.setImage(scaledImage);
                        checkBoxn.setIcon(image);
                        list_checkBox.add(checkBoxn);
                        paneltemp.setBorder(nameBorder3);
                        paneltemp.add(checkBoxn);
                        panelService.add(paneltemp);
                        checkBoxn.addItemListener(new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                // Nếu JCheckBox được tích vào
                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                    // Thay đổi màu text thành xanh lá
                                    checkBoxn.setForeground(Color.GREEN);
                                    checkBoxn.setSelected(true);
                                }else {
                                    // Thay đổi màu text về ban đầu
                                    checkBoxn.setForeground(Color.BLACK);
                                    checkBoxn.setSelected(false);
                                }
                            }
                        });
                        if(typeField2.getText().isEmpty() == false){
                            for (RoleFunc roleFunc : list_RoleFuncs) {
                                if(roleFunc.getRoleId().equals(typeField2.getText())){
                                    for (ServiceDTO funtions : list_ServiceDTOs) {
                                        if(roleFunc.getFunctionId().equals(funtions.getDescription())){
                                            for (JCheckBox cb : list_checkBox) {
                                                String[] parts = cb.getText().split("-");
                                                if(parts[0].equals(funtions.getDescription())){
                                                    cb.setSelected(true);
                                                    cb.setForeground(Color.GREEN);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                JButton confirmService = new JButton("OK");
                confirmService.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            int sumPrice = 0;
                            for(int i = 0; i < list_checkBox.size(); i++){
                                if(list_checkBox.get(i).isSelected()==true && list_checkBox.get(i) != null){
                                    String[] parts = list_checkBox.get(i).getText().split("-");
                                    // String index[] = parts[2].split(".");
                                    // String subStr = index[0];
                                    sumPrice += Double.parseDouble(parts[2]);
                                }
                            }
                        priceField.setText(Integer.toString(sumPrice));
                        }else{
                        }
                    }
                });
                
                selectService.add(new JScrollPane(panelService), BorderLayout.CENTER);
                selectService.add(confirmService, BorderLayout.SOUTH);
                selectService.setVisible(true);
            }
        });

        


        // add labels and text fields to ticketManager panel
        paneltypeCenter.setLayout(new GridLayout(5, 2, 5, 5));
        paneltypeCenter.add(type_id2);
        paneltypeCenter.add(typeField2);
        paneltypeCenter.add(type_name);
        paneltypeCenter.add(nameBox);
        paneltypeCenter.add(waitting_room);
        paneltypeCenter.add(waitField);
        paneltypeCenter.add(type_price);
        paneltypeCenter.add(priceField);

        // JTable ticketTable = new JTable(tableModel);
        DefaultTableModel tableModel2 = new DefaultTableModel(new Object[]{"Mã loại vé", "Tên loại vé", "Giá"}, 0);
        for (Type type3 : list_Types) {
            tableModel2.addRow(new Object[]{type3.getType_id(),type3.getType_name(),type3.getType_price()});
        }
        
        tickettypeTable = new JTable(tableModel2){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        tickettypeTable.setRowHeight(30);
        tickettypeTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tickettypeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tickettypeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tickettypeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tickettypeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    for (JCheckBox cb : list_checkBox){
                        cb.setSelected(false);
                    }
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable


                    String id = target.getValueAt(row, 0).toString();
                    typeField2.setText(id);
                    String name = target.getValueAt(row, 1).toString();
                    for (int i = 0; i < nameBox.getItemCount(); i++) {
                        if(nameBox.getItemAt(i).toString().startsWith(name)){
                            nameBox.setSelectedIndex(i);
                        }
                    }
                    String  price = target.getValueAt(row, 2).toString();
                        priceField.setText(price);
                    // Tạo JFrame mới để hiển thị chi tiết chuyến bay
                    for (RoleFunc roleFunc : list_RoleFuncs) {
                        if(roleFunc.getRoleId().equals(id)){
                            for (ServiceDTO funtions : list_ServiceDTOs) {
                                if(roleFunc.getFunctionId().equals(funtions.getDescription())){
                                    for (JCheckBox cb : list_checkBox) {
                                        String[] parts = cb.getText().split("-");
                                        if(parts[0].equals(funtions.getDescription())){
                                            cb.setSelected(true);
                                            cb.setForeground(Color.GREEN);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        JButton addButton2 = new JButton("Thêm");
        addButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm loại vé!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if(priceField.getText().isEmpty() == true || Check.isNumber(priceField.getText()) == false){
                        if(priceField.getText().isEmpty() == true){
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá vé !");
                        }
                        if(Check.isNumber(priceField.getText()) == false){
                            JOptionPane.showMessageDialog(null, "Giá vé phải là số !");
                        }
                    }else{
                        String type_id = typeBUS.CreateType();
                        Type type = new Type(type_id, nameBox.getSelectedItem().toString(),Float.parseFloat(priceField.getText()));
                        try {
                            typeBUS.add2(type);
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        for (JCheckBox cb : list_checkBox) {
                                if (cb.isSelected()==true) {
                                    for (ServiceDTO funtions : list_ServiceDTOs) {
                                        String[] parts = cb.getText().split("-");
                                        if(funtions.getDescription().equals(parts[0])){
                                            RoleFunc roleFunc = new RoleFunc();
                                            roleFunc.setRoleId(type_id);
                                            roleFunc.setFunctionId(funtions.getDescription());
                                            try {
                                                roleFuncBUS2.add(roleFunc);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                    }
                
                DefaultTableModel tableModel2 = new DefaultTableModel(new Object[]{"Mã loại vé", "Tên loại vé", "Giá"}, 0);
                    for (Type type3 : list_Types) {
                        tableModel2.addRow(new Object[]{type3.getType_id(),type3.getType_name(),type3.getType_price()});
                    }
                    tickettypeTable.setModel(tableModel2);
                    for(int i = 0; i <= 2; i++){
                        tickettypeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
            }else{}
            }
        });
        JButton delButton2 = new JButton("Xóa");
        delButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa loại vé!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                if(typeField2.getText().isEmpty()==true){
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ !");
                }else{
                
                    try {
                        roleFuncBUS2.deleteallRole(typeField2.getText());
                        typeBUS.delete(typeField2.getText());
                        
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                }
                DefaultTableModel tableModel2 = new DefaultTableModel(new Object[]{"Mã loại vé", "Tên loại vé", "Giá"}, 0);
                    for (Type type3 : list_Types) {
                        tableModel2.addRow(new Object[]{type3.getType_id(),type3.getType_name(),type3.getType_price()});
                    }
                    tickettypeTable.setModel(tableModel2);
                    for(int i = 0; i <= 2; i++){
                        tickettypeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
            }else{

            }
            }
        });
        JButton editButton2 = new JButton("Sửa");
        editButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int option = JOptionPane.showConfirmDialog(null, "Xác nhận loại sửa vé!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                //if(nameBox.getSelectedItem().toString().equals("Phổ thông")){
                    if( priceField.getText().isEmpty() == true || Check.isNumber(priceField.getText()) == false){
                        
                        if(priceField.getText().isEmpty() == true){
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá vé !");
                        }
                        if(Check.isNumber(priceField.getText()) == false){
                            JOptionPane.showMessageDialog(null, "Giá vé phải là số !");
                        }
                    }else{
                        Type type = new Type(typeField2.getText(), nameBox.getSelectedItem().toString()  ,Float.parseFloat(priceField.getText()));
                        for (JCheckBox cb : list_checkBox) {
                                if (cb.isSelected()==true) {
                                    for (ServiceDTO funtions : list_ServiceDTOs) {
                                        String[] parts = cb.getText().split("-");
                                        if(funtions.getDescription().equals(parts[0])){
                                            RoleFunc roleFunc = new RoleFunc();
                                            roleFunc.setRoleId(typeField2.getText());
                                            roleFunc.setFunctionId(funtions.getDescription());
                                            try {
                                                roleFuncBUS2.add(roleFunc);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        try {
                            typeBUS.set(type);
                        } catch (ClassNotFoundException | SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                DefaultTableModel tableModel2 = new DefaultTableModel(new Object[]{"Mã loại vé", "Tên loại vé", "Giá"}, 0);
                    for (Type type3 : list_Types) {
                        tableModel2.addRow(new Object[]{type3.getType_id(),type3.getType_name(),type3.getType_price()});
                    }
                    tickettypeTable.setModel(tableModel2);
                    for(int i = 0; i <= 2; i++){
                        tickettypeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
            }else{}
            }
        });

        ImageIcon iconadd = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/new-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImageadd = iconadd.getImage();
        Image scaledImageadd= originalImageadd.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIconadd = new ImageIcon(scaledImageadd);
        addButton2.setIcon(scaledIconadd);
        
        ImageIcon iconedit = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/edit-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImageedit = iconedit.getImage();
        Image scaledImageedit= originalImageedit.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIconedit = new ImageIcon(scaledImageedit);
        editButton2.setIcon(scaledIconedit);

        ImageIcon icondel = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/delete-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImagedel = icondel.getImage();
        Image scaledImagedel= originalImagedel.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcondel = new ImageIcon(scaledImagedel);
        delButton2.setIcon(scaledIcondel);

        paneltypeRight.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(10, 10, 10, 10);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1;

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        paneltypeRight.add(addButton2, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        paneltypeRight.add(delButton2, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 3;
        paneltypeRight.add(editButton2, gbc2);

        String[] positions2 = { "--Chọn tiêu chí--", "Mã loại vé", "Tên loại vé" };
        JComboBox<String> positionComboBox2 = new JComboBox<>(positions2);

        // create JtextField for panelBot searchBar
        JTextField searchBar2 = new JTextField(20);

        // Create the search button
        JButton searchButton2 = new JButton("Tìm kiếm ");
        searchButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(positionComboBox2.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
                    DefaultTableModel tableModel2 = new DefaultTableModel(new Object[]{"Mã loại vé", "Tên loại vé", "Phòng chờ", "Giá"}, 0);
                    for (Type type3 : list_Types) {
                        tableModel2.addRow(new Object[]{type3.getType_id(),type3.getType_name(),type3.getType_price()});
                    }
                    tickettypeTable.setModel(tableModel2);
                    for(int i = 0; i <= 5; i++){
                        tickettypeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(positionComboBox2.getSelectedItem().toString().equals("Mã loại vé")){
                    DefaultTableModel tableModel2 = new DefaultTableModel(new Object[]{"Mã loại vé", "Tên loại vé", "Phòng chờ", "Giá"}, 0);
                    for (Type type3 : list_Types) {
                        if(type3.getType_id().startsWith(searchBar2.getText())){
                            tableModel2.addRow(new Object[]{type3.getType_id(),type3.getType_name(),type3.getType_price()});

                        }
                    }
                    tickettypeTable.setModel(tableModel2);
                    for(int i = 0; i <= 5; i++){
                        tickettypeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(positionComboBox2.getSelectedItem().toString().equals("Tên loại vé")){
                    DefaultTableModel tableModel2 = new DefaultTableModel(new Object[]{"Mã loại vé", "Tên loại vé", "Phòng chờ", "Giá"}, 0);
                    for (Type type3 : list_Types) {
                        if(type3.getType_name().startsWith(searchBar2.getText())){
                            tableModel2.addRow(new Object[]{type3.getType_id(),type3.getType_name(),type3.getType_price()});

                        }
                    }
                    tickettypeTable.setModel(tableModel2);
                    for(int i = 0; i <= 5; i++){
                        tickettypeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
            }
        });

        JPanel panel2 = new JPanel(new FlowLayout());
        paneltypeBot.setLayout(new BorderLayout());

        panel2.add(positionComboBox2);
        panel2.add(searchBar2);
        panel2.add(searchButton2);

        paneltypeBot.add(panel2, BorderLayout.NORTH);

        paneltypeBot.add(new JScrollPane(tickettypeTable), BorderLayout.CENTER);
        
    }
}
