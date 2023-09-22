package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import java.sql.SQLException;
import java.util.ArrayList;

import BUS.ServiceBUS;
import DTO.ServiceDTO;



public class ServiceGUI extends JPanel{
    private ServiceBUS serviceBUS;
    private JTable ServiceTable;
    private File selectedFile;


    public ServiceGUI() throws ClassNotFoundException, SQLException{
        this.setSize(900, 500);
        this.setVisible(true);
        serviceBUS = new ServiceBUS();
        ArrayList<ServiceDTO> list_ServiceDTOs = serviceBUS.getList_ServiceDTOs();

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel(new GridBagLayout());
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin dịch vụ",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelCenter.setBorder(nameBorder);

        // set size for panel
        panelTop.setPreferredSize(new Dimension(1100, 60));
        panelBot.setPreferredSize(new Dimension(1100, 350));
        panelCenter.setPreferredSize(new Dimension(900, 370));
        panelRight.setPreferredSize(new Dimension(200, 250));

        // add panel into wrapper
        wrapperPanel.add(panelTop, BorderLayout.NORTH);
        wrapperPanel.add(panelBot, BorderLayout.SOUTH);
        wrapperPanel.add(panelCenter, BorderLayout.CENTER);
        wrapperPanel.add(panelRight, BorderLayout.EAST);

        // add wrapper into frame
        add(wrapperPanel);

        // set label for panelTop
        JLabel labelServiceEm = new JLabel();
        JLabel labelServiceInfo = new JLabel();
        labelServiceEm.setText("QUẢN LÝ DỊCH VỤ");

        // set font for label
        labelServiceEm.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // Set positions of labels
        labelServiceEm.setHorizontalAlignment(JLabel.CENTER);
        labelServiceInfo.setHorizontalAlignment(JLabel.LEFT);


        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelServiceEm);
        panelTop.add(labelServiceInfo);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        JLabel name = new JLabel("Tên dịch vụ:");
        name.setFont(fontLabel);
        JLabel des = new JLabel("Mã dịch vụ:");
        des.setFont(fontLabel);
        JLabel price = new JLabel("Giá Tiền:");
        price.setFont(fontLabel);

        // add label and text field into panelCenter
        panelCenter.setLayout(new GridLayout(2, 1,5,5));

        JPanel panelField = new JPanel(new GridLayout(4, 2,5,5));
        panelField.add(name);
        JTextField nameField = new JTextField(); 
        nameField.setFont(fontTextField);
        panelField.add(nameField);
        panelField.add(des);
        JComboBox<String> desField = new JComboBox<>();
        desField.setFont(fontTextField);
        desField.addItem(serviceBUS.CreateType2PT());
        desField.addItem(serviceBUS.CreateServiceDTOTG());
        

        panelField.add(desField);
        panelField.add(price);
        JTextField priceField = new JTextField();
        priceField.setFont(fontTextField);
        panelField.add(priceField);

        JPanel panelFile = new JPanel(new GridLayout(1, 2,5,5));
        JLabel imagField = new JLabel();
        JLabel Field = new JLabel();
        imagField.setFont(fontTextField);


        JFileChooser fileChooser = new JFileChooser();
        // Định cấu hình JFileChooser
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "gif"));
        fileChooser.setDialogTitle("Select an image file");

        // Tạo một đối tượng JButton
        JButton buttonfile = new JButton("Choose a file");

        // Thêm sự kiện nhấn chuột cho JButton
        buttonfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị hộp thoại
                int result = fileChooser.showDialog(null, "Open");

                // Xử lý kết quả
                if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn đến file đã chọn
            selectedFile = fileChooser.getSelectedFile();

            // Tạo một đối tượng ImageIcon từ đường dẫn đến file hình ảnh
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image scaledImage = imageIcon.getImage().getScaledInstance(420, 250, Image.SCALE_SMOOTH);

            // Set kích thước cho ImageIcon
            imageIcon.setImage(scaledImage);
            // Sử dụng phương thức setIcon() của Jlabel để đặt hình ảnh cho Jlabel
            imagField.setIcon(imageIcon);
        }
            }
        });

        panelField.add(buttonfile);
        panelFile.add(Field);
        panelFile.add(imagField);

        panelCenter.add(panelField);
        panelCenter.add(panelFile);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Tên dịch vụ",  "Mã dịch vụ", "Giá tiền",
        "IMG" }, 0);
        for (ServiceDTO cus : list_ServiceDTOs) {
            tableModel.addRow(new Object[]{cus.getName(),cus.getDescription(),cus.getPrice(),new ImageIcon(cus.getImage())});
        }
        ServiceTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        Font font = new Font("Arial", Font.PLAIN, 16);
        ServiceTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ServiceTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ServiceTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        ServiceTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        ServiceTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        ServiceTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                   String id = target.getValueAt(row, 0).toString();
                   nameField.setText(id);
                   String sn =target.getValueAt(row, 1).toString();
                   desField.addItem(sn);
                   
                   for (int i = 0; i < desField.getItemCount(); i++) {
                        if(desField.getItemAt(i).toString().equals(sn)){
                            desField.setSelectedIndex(i);
                        }
                    }
                    for (int i = 0; i < desField.getItemCount(); i++) {
                        // Kiểm tra xem text của item hiện tại có trùng với text của các item trước đó hay không
                        for (int j = 0; j < i; j++) {
                            if (desField.getItemAt(i).equals(desField.getItemAt(j))) {
                                // Xóa item hiện tại khỏi JdesField
                                desField.removeItemAt(i);
                                break;
                            }
                        }
                    }
                   String n =target.getValueAt(row, 2).toString();
                   priceField.setText(n);
                   String em =target.getValueAt(row, 3).toString();
                   ImageIcon image = new ImageIcon(em);
                   Image scaledImage = image.getImage().getScaledInstance(420, 250, Image.SCALE_SMOOTH);

                    // Set kích thước cho image
                    image.setImage(scaledImage);
                   imagField.setIcon(image);
                    
                }
            }
        });

        //////
        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(nameField.getText().isEmpty() == true  || desField.getSelectedItem().toString().isEmpty() == true ||
                priceField.getText().isEmpty() == true ){
                    JOptionPane.showMessageDialog(ServiceGUI.this, "Chưa nhập thông tin dịch vụ", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    if(serviceBUS.checkService(desField.getSelectedItem().toString())==false){
                        JOptionPane.showMessageDialog(ServiceGUI.this, "Trùng mã dịch vụ", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm dịch vụ!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                ServiceDTO customer = new ServiceDTO(nameField.getText(), desField.getSelectedItem().toString(), Double.parseDouble(priceField.getText()),selectedFile.getAbsolutePath());
                                serviceBUS.add(customer);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Tên dịch vụ",  "Mã dịch vụ", "Giá tiền",
        "IMG" }, 0);
                    for (ServiceDTO cus : list_ServiceDTOs) {
            tableModel.addRow(new Object[]{cus.getName(),cus.getDescription(),cus.getPrice(),cus.getImage()});

                    }
                    ServiceTable.setModel(tableModel);
                    for(int i = 0; i <= 3; i++){
                        ServiceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                    desField.removeAllItems();

                    desField.addItem(serviceBUS.CreateType2PT());
                    desField.addItem(serviceBUS.CreateServiceDTOTG());
                    //Lấy tập hợp các item không có phần tử trùng lặp
                    for (int i = 0; i < desField.getItemCount(); i++) {
                        // Kiểm tra xem text của item hiện tại có trùng với text của các item trước đó hay không
                        for (int j = 0; j < i; j++) {
                            if (desField.getItemAt(i).equals(desField.getItemAt(j))) {
                                // Xóa item hiện tại khỏi JdesField
                                desField.removeItemAt(i);
                                break;
                            }
                        }
                    }
                }

            }
        });
        
        JButton delButton = new JButton("Xóa");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(nameField.getText().isEmpty() == true  || desField.getSelectedItem().toString().isEmpty() == true ||
                priceField.getText().isEmpty() == true ){
                    JOptionPane.showMessageDialog(ServiceGUI.this, "Chưa nhập thông tin dịch vụ", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    if(serviceBUS.checkService(desField.getSelectedItem().toString())==true){
                        JOptionPane.showMessageDialog(ServiceGUI.this, "Mã dịch vụ không khớp", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa dịch vụ!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                serviceBUS.delete(desField.getSelectedItem().toString());
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Tên dịch vụ",  "Mã dịch vụ", "Giá tiền",
        "IMG" }, 0);
                    for (ServiceDTO cus : list_ServiceDTOs) {
            tableModel.addRow(new Object[]{cus.getName(),cus.getDescription(),cus.getPrice(),cus.getImage()});

                    }
                    ServiceTable.setModel(tableModel);
                    for(int i = 0; i <= 3; i++){
                        ServiceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                    for (int i = 0; i < desField.getItemCount(); i++) {
                        // Kiểm tra xem text của item hiện tại có trùng với text của các item trước đó hay không
                        for (int j = 0; j < i; j++) {
                            if (desField.getItemAt(i).equals(desField.getItemAt(j))) {
                                // Xóa item hiện tại khỏi JdesField
                                desField.removeItemAt(i);
                                break;
                            }
                        }
                    }
                }

            }
        });
        JButton editButton = new JButton("Sửa");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(nameField.getText().isEmpty() == true  || desField.getSelectedItem().toString().isEmpty() == true ||
                priceField.getText().isEmpty() == true ){
                    JOptionPane.showMessageDialog(ServiceGUI.this, "Chưa nhập thông tin dịch vụ", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }else{
                    if(serviceBUS.checkService(desField.getSelectedItem().toString())==true){
                        JOptionPane.showMessageDialog(ServiceGUI.this, "Mã dịch vụ không khớp", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa dịch vụ!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                serviceBUS.delete(desField.getSelectedItem().toString());
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Tên dịch vụ",  "Mã dịch vụ", "Giá tiền",
        "IMG" }, 0);
                    for (ServiceDTO cus : list_ServiceDTOs) {
            tableModel.addRow(new Object[]{cus.getName(),cus.getDescription(),cus.getPrice(),cus.getImage()});

                    }
                    ServiceTable.setModel(tableModel);
                    for(int i = 0; i <= 3; i++){
                        ServiceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                    for (int i = 0; i < desField.getItemCount(); i++) {
                        // Kiểm tra xem text của item hiện tại có trùng với text của các item trước đó hay không
                        for (int j = 0; j < i; j++) {
                            if (desField.getItemAt(i).equals(desField.getItemAt(j))) {
                                // Xóa item hiện tại khỏi JdesField
                                desField.removeItemAt(i);
                                break;
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

        // set layout for panelRight
        panelRight.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRight.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRight.add(delButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRight.add(editButton, gbc);


        panelBot.add(new JScrollPane(ServiceTable), BorderLayout.CENTER);
    }

}
