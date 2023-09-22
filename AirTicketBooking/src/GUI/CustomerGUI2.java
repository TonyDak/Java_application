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

import BUS.CustomerBUS;
import DTO.Customer;

public class CustomerGUI2 extends JPanel {

    private JLabel text, cusid, icard;
    private JTextField textBar, cusbar, icardbar;
    private JButton searchButton;
    private JTable CustomerTable;

    private CustomerBUS customerBUS;

    public CustomerGUI2() throws ClassNotFoundException, SQLException {
        setSize(900, 500);
        setVisible(true);

        customerBUS = new CustomerBUS();
        ArrayList<Customer> list_Customers = customerBUS.getList_Customers();

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
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin khách hàng",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelCenter.setBorder(nameBorder);

        // set size for panel
        panelTop.setPreferredSize(new Dimension(1100, 60));
        panelBot.setPreferredSize(new Dimension(1100, 300));
        panelCenter.setPreferredSize(new Dimension(900, 350));
        panelRight.setPreferredSize(new Dimension(200, 250));

        // add panel into wrapper
        wrapperPanel.add(panelTop, BorderLayout.NORTH);
        wrapperPanel.add(panelBot, BorderLayout.SOUTH);
        wrapperPanel.add(panelCenter, BorderLayout.CENTER);
        wrapperPanel.add(panelRight, BorderLayout.EAST);

        // add wrapper into frame
        add(wrapperPanel);

        // set label for panelTop
        JLabel labelCustomerEm = new JLabel();
        JLabel labelCustomerInfo = new JLabel();
        labelCustomerEm.setText("QUẢN LÝ KHÁCH HÀNG");

        // set font for label
        labelCustomerEm.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // Set positions of labels
        labelCustomerEm.setHorizontalAlignment(JLabel.CENTER);
        labelCustomerInfo.setHorizontalAlignment(JLabel.LEFT);


        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelCustomerEm);
        panelTop.add(labelCustomerInfo);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        // create label and jtextfiled in panelCenter
        JLabel customer_id = new JLabel("Mã khách hàng:");
        customer_id.setFont(fontLabel);
        JLabel surname = new JLabel("Họ:");
        surname.setFont(fontLabel);
        JLabel name = new JLabel("Tên:");
        name.setFont(fontLabel);
        JLabel email = new JLabel("Email:");
        email.setFont(fontLabel);
        JLabel phone = new JLabel("Số điện thoại:");
        phone.setFont(fontLabel);
        JLabel address = new JLabel("Địa chỉ:");
        address.setFont(fontLabel);

        // add label and text field into panelCenter
        panelCenter.setLayout(new GridLayout(6, 2,5,5));
        panelCenter.add(customer_id);
        JTextField customerIDField = new JTextField();
        customerIDField.setFont(fontTextField);
        customerIDField.setEditable(false);
        customerIDField.setText(customerBUS.CreateMKH());
        panelCenter.add(customerIDField);
        panelCenter.add(surname);
        JTextField surnameField = new JTextField(); 
        surnameField.setFont(fontTextField);
        panelCenter.add(surnameField);
        panelCenter.add(name);
        JTextField nameField = new JTextField();
        nameField.setFont(fontTextField);
        panelCenter.add(nameField);
        panelCenter.add(email);
        JTextField emailField  = new JTextField();
        emailField.setFont(fontTextField);
         emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Check.validateEmail(emailField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Check.validateEmail(emailField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Check.validateEmail(emailField);
            }
        });
        panelCenter.add(emailField);
        panelCenter.add(phone);
        JTextField phonField = new JTextField();
        phonField.setFont(fontTextField);
        phonField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Check.validatePhoneNumber(phonField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Check.validatePhoneNumber(phonField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Check.validatePhoneNumber(phonField);
            }
        });
        panelCenter.add(phonField);
        panelCenter.add(address);
        JTextField addressField = new JTextField();
        addressField.setFont(fontTextField);
        panelCenter.add(addressField);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
        "Email", "Số điện thoại", "Địa chỉ" }, 0);
        for (Customer cus : list_Customers) {
            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});
        }
        CustomerTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        Font font = new Font("Arial", Font.PLAIN, 16);
        CustomerTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        CustomerTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        CustomerTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        CustomerTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        CustomerTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        CustomerTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        CustomerTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        CustomerTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                   String id = target.getValueAt(row, 0).toString();
                   customerIDField.setText(id);
                   String sn =target.getValueAt(row, 1).toString();
                   surnameField.setText(sn);
                   String n =target.getValueAt(row, 2).toString();
                   nameField.setText(n);
                   String em =target.getValueAt(row, 3).toString();
                   emailField.setText(em);
                   String ph =target.getValueAt(row, 4).toString();
                   phonField.setText(ph);
                   String ad =target.getValueAt(row, 5).toString();
                    addressField.setText(ad);
                    
                }
            }
        });

        // create buttons for panelRight
        ImageIcon iconadd = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/new-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImageadd = iconadd.getImage();
        Image scaledImageadd= originalImageadd.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIconadd = new ImageIcon(scaledImageadd);

        ImageIcon iconedit = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/edit-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImageedit = iconedit.getImage();
        Image scaledImageedit= originalImageedit.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIconedit = new ImageIcon(scaledImageedit);

        ImageIcon icondel = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/delete-folder.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
        Image originalImagedel = icondel.getImage();
        Image scaledImagedel= originalImagedel.getScaledInstance(35, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcondel = new ImageIcon(scaledImagedel);

        JButton addButton = new JButton("Thêm");
        addButton.setIcon(scaledIconadd);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(customerIDField.getText().isEmpty() == true  || surnameField.getText().isEmpty() == true ||
                nameField.getText().isEmpty() == true || emailField.getText().isEmpty() ==true||
                phonField.getText().isEmpty()==true || addressField.getText(). isEmpty() == true
                || Check.isValidEmail(emailField.getText()) == false
                || Check.isValidPhoneNumber(phonField.getText()) == false){
                    JOptionPane.showMessageDialog(CustomerGUI2.this, "Chưa nhập thông tin khách hàng", "Error", JOptionPane.ERROR_MESSAGE);
                    if (Check.isValidEmail(emailField.getText()) == false) {
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Error Email", "Error", JOptionPane.ERROR_MESSAGE);
                        emailField.requestFocus();
                    }
                    if (Check.isValidPhoneNumber(phonField.getText()) == false) {
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Error phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        phonField.requestFocus();
                    }
                }else{
                    if(customerBUS.checkCustomerID(customerIDField.getText())==false){
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Trùng mã khách hàng", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm khách hàng!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                Customer customer = new Customer(customerIDField.getText(), surnameField.getText(), nameField.getText(), emailField.getText(), phonField.getText(), addressField.getText());
                                customerBUS.add(customer);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                }

            }
        });
        
        JButton delButton = new JButton("Xóa");
        delButton.setIcon(scaledIcondel);
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(customerIDField.getText().isEmpty() == true  || surnameField.getText().isEmpty() == true ||
                nameField.getText().isEmpty() == true || emailField.getText().isEmpty() ==true||
                phonField.getText().isEmpty()==true || addressField.getText(). isEmpty() == true
                || Check.isValidEmail(emailField.getText()) == false
                || Check.isValidPhoneNumber(phonField.getText()) == false){
                    JOptionPane.showMessageDialog(CustomerGUI2.this, "Chưa nhập thông tin nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
                    if (Check.isValidEmail(emailField.getText()) == false) {
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Error Email", "Error", JOptionPane.ERROR_MESSAGE);
                        emailField.requestFocus();
                    }
                    if (Check.isValidPhoneNumber(phonField.getText()) == false) {
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Error phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        phonField.requestFocus();
                    }
                }else{
                    if(customerBUS.checkCustomerID(customerIDField.getText())==true){
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Mã khách hàng không khớp", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa nhân viên!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                customerBUS.delete(customerIDField.getText());
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                }

            }
        });
        JButton editButton = new JButton("Sửa");
        editButton.setIcon(scaledIconedit);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(customerIDField.getText().isEmpty() == true  || surnameField.getText().isEmpty() == true ||
                nameField.getText().isEmpty() == true || emailField.getText().isEmpty() ==true||
                phonField.getText().isEmpty()==true || addressField.getText(). isEmpty() == true
                || Check.isValidEmail(emailField.getText()) == false
                || Check.isValidPhoneNumber(phonField.getText()) == false){
                    JOptionPane.showMessageDialog(CustomerGUI2.this, "Chưa nhập thông tin khách hàng", "Error", JOptionPane.ERROR_MESSAGE);
                    if (Check.isValidEmail(emailField.getText()) == false) {
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Error Email", "Error", JOptionPane.ERROR_MESSAGE);
                        emailField.requestFocus();
                    }
                    if (Check.isValidPhoneNumber(phonField.getText()) == false) {
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Error phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        phonField.requestFocus();
                    }
                }else{
                    if(customerBUS.checkCustomerID(customerIDField.getText())==true){
                        JOptionPane.showMessageDialog(CustomerGUI2.this, "Mã khách hàng không khớp", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa khách hàng!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                Customer customer = new Customer(customerIDField.getText(), surnameField.getText(), nameField.getText(), emailField.getText(), phonField.getText(), addressField.getText());
                                customerBUS.set(customer);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
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
        gbc.gridy = 1;
        panelRight.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRight.add(delButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRight.add(editButton, gbc);

        // create JtextField for panelBot textBar,cusbar,icardbar
        text = new JLabel("Mã khách hàng");
        textBar = new JTextField(15);

        cusid = new JLabel("Họ");
        cusbar = new JTextField(10);

        icard = new JLabel("Tên");
        icardbar = new JTextField(10);

        // Create the search button
        searchButton = new JButton("Tìm kiếm");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textBar.getText().isEmpty() == true && cusbar.getText().isEmpty() == true &&
                icardbar.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(CustomerGUI2.this, "Vui lòng nhập thông tin tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true && cusbar.getText().isEmpty() == true &&
                icardbar.getText().isEmpty() == true){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                        if(cus.getCustomer_id().startsWith(textBar.getText())){
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                        }
                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() == true && cusbar.getText().isEmpty() != true &&
                icardbar.getText().isEmpty() == true){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                        if(cus.getSurname().startsWith(cusbar.getText())){
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                        }
                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() == true && cusbar.getText().isEmpty() == true &&
                icardbar.getText().isEmpty() != true){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                        if(cus.getName().startsWith(icardbar.getText())){
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                        }
                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true && cusbar.getText().isEmpty() != true &&
                icardbar.getText().isEmpty() == true){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                        if(cus.getCustomer_id().startsWith(textBar.getText()) && cus.getSurname().startsWith(cusbar.getText()) ){
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                        }
                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true && cusbar.getText().isEmpty() == true &&
                icardbar.getText().isEmpty() != true){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                        if(cus.getCustomer_id().startsWith(textBar.getText()) &&cus.getName().startsWith(icardbar.getText())){
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                        }
                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() == true && cusbar.getText().isEmpty() != true &&
                icardbar.getText().isEmpty() != true){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                        if(cus.getName().startsWith(icardbar.getText())&& cus.getSurname().startsWith(cusbar.getText())){
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                        }
                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
                if(textBar.getText().isEmpty() != true && cusbar.getText().isEmpty() != true &&
                icardbar.getText().isEmpty() != true){
                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã khách hàng",  "Họ", "Tên",
                    "Email", "Số điện thoại", "Địa chỉ" }, 0);
                    for (Customer cus : list_Customers) {
                        if(cus.getName().startsWith(icardbar.getText())&&cus.getCustomer_id().startsWith(textBar.getText()) && cus.getSurname().startsWith(cusbar.getText())){
                            tableModel.addRow(new Object[]{cus.getCustomer_id(),cus.getSurname(),cus.getName(),cus.getEmail(),cus.getPhone(),cus.getAddress()});

                        }
                    }
                    CustomerTable.setModel(tableModel);
                    for(int i = 0; i <= 5; i++){
                        CustomerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    }
                }
            }
        });

        // add to panelBot
        // panelBot = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());

        panel.add(text);
        panel.add(textBar);
        panel.add(cusid);
        panel.add(cusbar);
        panel.add(icard);
        panel.add(icardbar);
        panel.add(searchButton);

        panelBot.add(panel, BorderLayout.NORTH);
        // panelBot.add(panelBot, BorderLayout.SOUTH);
        panelBot.add(new JScrollPane(CustomerTable), BorderLayout.CENTER);

        // pack and center frame
        // pack();
        // setLocationRelativeTo(null);
        
    }
}
