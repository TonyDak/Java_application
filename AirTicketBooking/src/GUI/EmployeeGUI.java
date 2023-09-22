package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BUS.EmployeeBUS;
import BUS.RolesBUS;
import DTO.Employee;
import DTO.Roles;
import DAO.RolesDAO;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EmployeeGUI extends JPanel {
    private JComboBox<String> positionComboBox;
    private JTextField usernameField, passwordField;
    // private JRadioButton maleButton, femaleButton;
    private JTextField searchBar;
    private JButton searchButton;
    private JTable employeeTable;
  
    private EmployeeBUS employeeBUS;
    private RolesDAO roledao;
    private RolesBUS rolesBUS;

    public EmployeeGUI() throws ClassNotFoundException, SQLException {
        // JFrame frame = new JFrame();
        setSize(900, 500);
        setVisible(true);
        
        rolesBUS = new RolesBUS();
        ArrayList<Roles> list_Roles = rolesBUS.getList_Roles();
        roledao = new RolesDAO();
        employeeBUS = new EmployeeBUS();
        ArrayList<Employee> list_Employees = employeeBUS.getList_Employees();

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
        panelCenter.setPreferredSize(new Dimension(900, 400));
        panelRight.setPreferredSize(new Dimension(200, 250));

        // add panel into wrapper
        wrapperPanel.add(panelTop, BorderLayout.NORTH);
        wrapperPanel.add(panelBot, BorderLayout.SOUTH);
        wrapperPanel.add(panelCenter, BorderLayout.CENTER);
        wrapperPanel.add(panelRight, BorderLayout.EAST);

        // add wrapper into frame
        //add(wrapperPanel);

        // set label for panelTop
        JLabel labelEmMa = new JLabel();
        JLabel labelEmIn = new JLabel();
        labelEmMa.setText("QUẢN LÝ NHÂN VIÊN");

        // set font for label
        labelEmMa.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // Set positions of labels
        labelEmMa.setHorizontalAlignment(JLabel.CENTER);
        labelEmIn.setHorizontalAlignment(JLabel.LEFT);

        // add label into panelTop
        // panelTop.add(labelEmMa);
        // panelTop.add(labelEmIn);

        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelEmMa);
        panelTop.add(labelEmIn);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        // create label and jtextfiled in panelCenter
        JLabel employee_id = new JLabel("Mã nhân viên:");
        employee_id.setFont(fontLabel);
        JLabel user_name = new JLabel("Tên đăng nhập:");
        user_name.setFont(fontLabel);
        JLabel pass = new JLabel("Mật khẩu:");
        pass.setFont(fontLabel);
        JLabel position = new JLabel("Chức vụ: ");
        position.setFont(fontLabel);
        JLabel surname = new JLabel("Họ:");
        surname.setFont(fontLabel);
        JLabel name = new JLabel("Tên:");
        name.setFont(fontLabel);
        JLabel gender = new JLabel("Giới tính:");
        gender.setFont(fontLabel);
        JLabel birth = new JLabel("Ngày sinh:");
        birth.setFont(fontLabel);
        JLabel email = new JLabel("Email:");
        email.setFont(fontLabel);
        JLabel phone = new JLabel("Số điện thoại:");
        phone.setFont(fontLabel);
        JLabel address = new JLabel("Địa chỉ:");
        address.setFont(fontLabel);

        usernameField = new JTextField();
        usernameField.setFont(fontTextField);
        passwordField = new JTextField();
        passwordField.setFont(fontTextField);

        // set size for text field
        usernameField.setPreferredSize(new Dimension(500, 20));
        passwordField.setPreferredSize(new Dimension(500, 20));

        // create border
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Thông tin nhân viên",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // add border to panelCenter
        panelCenter.setBorder(nameBorder);
        // add label and text field into panelCenter
        panelCenter.setLayout(new GridLayout(11, 2, 3, 3));
        panelCenter.add(employee_id);
        JTextField employeeIDField = new JTextField();
        employeeIDField.setText(employeeBUS.CreateMNV());
        employeeIDField.setFont(fontTextField);
        employeeIDField.setEditable(false);
        panelCenter.add(employeeIDField);
        panelCenter.add(user_name);
        panelCenter.add(usernameField);
        panelCenter.add(pass);
        panelCenter.add(passwordField);
        panelCenter.add(position);
        JComboBox<String> checkboxPanel = new JComboBox<>();
        for (Roles roles : list_Roles) {
            checkboxPanel.addItem(roles.getRoles_id()+"-"+roles.getName());
        }
        panelCenter.add(checkboxPanel);
         // Text field for position
        panelCenter.add(surname);
        JTextField surnameField = new JTextField(); 
        surnameField.setFont(fontTextField);
        panelCenter.add(surnameField);
        panelCenter.add(name);
        JTextField nameField = new JTextField();
        nameField.setFont(fontTextField);
        panelCenter.add(nameField);
        panelCenter.add(gender);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Create a panel to hold radio buttons
        JRadioButton maleRadioButton = new JRadioButton("Nam");
        maleRadioButton.setFont(fontTextField);
        JRadioButton femaleRadioButton = new JRadioButton("Nữ");
        femaleRadioButton.setFont(fontTextField);
        ButtonGroup genderButtonGroup = new ButtonGroup(); // Create a button group to group radio buttons
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        genderPanel.setLayout(new GridLayout(1, 2, 2, 2));
        panelCenter.add(genderPanel);
        panelCenter.add(birth);
        JDateChooser birthDate = new JDateChooser();
        birthDate.setDateFormatString("dd-MM-yyyy");
        birthDate.setLocale(new Locale("vi", "VN"));
        birthDate.setPreferredSize(new Dimension(130, 20));
        birthDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelCenter.add(birthDate);
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
        
        
         // add JTable for panelBot
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
        "Email", "Số điện thoại", "Địa chỉ" }, 0);
        for (Employee e : list_Employees) {
            tableModel.addRow(new Object[]{e.getEmployee_id(),e.getUser_name(),e.getPass(),roledao.nameroles(Integer.parseInt(e.getRole_id())),e.getSurname(),e.getName(),e.getGender(),e.getBirth(),e.getEmail(),e.getPhone(),e.getAddress()});
        }
        employeeTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        Font font = new Font("Arial", Font.PLAIN, 16);
        employeeTable.setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        employeeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);

        employeeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable
                   String id = target.getValueAt(row, 0).toString();
                   employeeIDField.setText(id);
                   String user =target.getValueAt(row, 1).toString();
                   usernameField.setText(user);
                   String pass =target.getValueAt(row, 2).toString();
                   passwordField.setText(pass);
                   String positi =target.getValueAt(row, 3).toString();
                   for (int i = 0; i < checkboxPanel.getItemCount(); i++) {
                        if(checkboxPanel.getItemAt(i).toString().startsWith(positi)){
                            checkboxPanel.setSelectedIndex(i);
                        }
                    }
                   String sn =target.getValueAt(row, 4).toString();
                   surnameField.setText(sn);
                   String n =target.getValueAt(row, 5).toString();
                   nameField.setText(n);
                   String gd =target.getValueAt(row, 6).toString();
                   if(gd.equals("Nam")){
                    maleRadioButton.setSelected(true);
                   }else{femaleRadioButton.setSelected(true);}
                   String bir =target.getValueAt(row, 7).toString();
                   try {
                    birthDate.setDate(Check.String_Date(bir));
                    } catch (ParseException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                   String em =target.getValueAt(row, 8).toString();
                   emailField.setText(em);
                   String ph =target.getValueAt(row, 9).toString();
                   phonField.setText(ph);
                   String ad =target.getValueAt(row, 10).toString();
                    addressField.setText(ad);
                    
                }
            }
        });

        // create buttons for panelRight
        JButton delButton = new JButton("Xóa");
        
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String gender;
                if(maleRadioButton.isSelected() == true){
                    gender = "Nam";
                }else if(femaleRadioButton.isSelected() == true){
                    gender = "Nu";
                }else{
                    gender = null;
                }
                if(employeeIDField.getText().isEmpty() == true || usernameField.getText().isEmpty() == true || passwordField.getText().isEmpty() == true 
                || /*positionField.getSelectedItem().toString().equals("--Chọn chức vụ--") ||*/  surnameField.getText().isEmpty() == true ||
                nameField.getText().isEmpty() == true ||   gender.isEmpty() == true ||
                Check.Date_String(birthDate.getDate()).isEmpty() == true||emailField.getText().isEmpty() ==true||
                phonField.getText().isEmpty()==true || addressField.getText(). isEmpty() == true
                || Check.isValidEmail(emailField.getText()) == false
                || Check.isValidPhoneNumber(phonField.getText()) == false){
                    JOptionPane.showMessageDialog(EmployeeGUI.this, "Chưa nhập thông tin nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
                    if (Check.isValidEmail(emailField.getText()) == false) {
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Error Email", "Error", JOptionPane.ERROR_MESSAGE);
                        emailField.requestFocus();
                    }
                    if (Check.isValidPhoneNumber(phonField.getText()) == false) {
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Error phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        phonField.requestFocus();
                    }
                }else{
                    if(employeeBUS.checkEmloyeeID(employeeIDField.getText())==true){
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Mã nhân viên không khớp!!", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa nhân viên!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                employeeBUS.delete(employeeIDField.getText());
                                DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                        "Email", "Số điện thoại", "Địa chỉ" }, 0);
                        for (Employee etb : list_Employees) {
                            if(etb.getGender().equals("Nam")){
                                tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                            }
                        }
                        employeeTable.setModel(tableModel1);
                        for(int i = 0; i <= 10; i++){
                            employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } else {
                            // Xử lý khi nhấn No hoặc đóng hộp thoại
                        }
                    }
                }

            }
        });

        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String gender;
                if(maleRadioButton.isSelected() == true){
                    gender = "Nam";
                }else if(femaleRadioButton.isSelected() == true){
                    gender = "Nu";
                }else{
                    gender = null;
                }
                if(employeeIDField.getText().isEmpty() == true || usernameField.getText().isEmpty() == true || passwordField.getText().isEmpty() == true 
                || /*positionField.getSelectedItem().toString().equals("--Chọn chức vụ--") ||*/ surnameField.getText().isEmpty() == true ||
                nameField.getText().isEmpty() == true ||   gender.isEmpty() == true ||
                Check.Date_String(birthDate.getDate()).isEmpty() == true||emailField.getText().isEmpty() ==true||
                phonField.getText().isEmpty()==true || addressField.getText(). isEmpty() == true
                || Check.isValidEmail(emailField.getText()) == false
                || Check.isValidPhoneNumber(phonField.getText()) == false){
                    JOptionPane.showMessageDialog(EmployeeGUI.this, "Chưa nhập thông tin nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
                    if (Check.isValidEmail(emailField.getText()) == false) {
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Error Email", "Error", JOptionPane.ERROR_MESSAGE);
                        emailField.requestFocus();
                    }
                    if (Check.isValidPhoneNumber(phonField.getText()) == false) {
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Error phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        phonField.requestFocus();
                    }
                }else{
                    boolean checkEmployee = false;
                    for (Employee employee : list_Employees) {
                        if(employee.getEmployee_id().equals(employeeIDField.getText())){
                            checkEmployee = true;
                        }
                    }
                    if(checkEmployee == true){
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Trùng mã nhân viên chỉ có thể sửa!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm nhân viên?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                int index = checkboxPanel.getSelectedItem().toString().indexOf("-");
                                Employee addEmployee = new Employee(employeeIDField.getText(),usernameField.getText(),passwordField.getText(),checkboxPanel.getSelectedItem().toString().substring(0, index),surnameField.getText()
                                ,nameField.getText(),gender,Check.Date_String(birthDate.getDate()),emailField.getText()
                                ,phonField.getText(),addressField.getText());
                                employeeBUS.add(addEmployee);
                                DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                        "Email", "Số điện thoại", "Địa chỉ" }, 0);
                        for (Employee etb : list_Employees) {
                            if(etb.getGender().equals("Nam")){
                                tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                            }
                        }
                        employeeTable.setModel(tableModel1);
                        for(int i = 0; i <= 10; i++){
                            employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
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
                String gender;
                if(maleRadioButton.isSelected() == true){
                    gender = "Nam";
                }else if(femaleRadioButton.isSelected() == true){
                    gender = "Nu";
                }else{
                    gender = null;
                }
                if(employeeIDField.getText().isEmpty() == true || usernameField.getText().isEmpty() == true || passwordField.getText().isEmpty() == true 
                || /*positionField.getSelectedItem().toString().equals("--Chọn chức vụ--") ||*/  surnameField.getText().isEmpty() == true ||
                nameField.getText().isEmpty() == true ||   gender.isEmpty() == true ||
                Check.Date_String(birthDate.getDate()).isEmpty() == true||emailField.getText().isEmpty() ==true||
                phonField.getText().isEmpty()==true || addressField.getText(). isEmpty() == true
                || Check.isValidEmail(emailField.getText()) == false
                || Check.isValidPhoneNumber(phonField.getText()) == false){
                    JOptionPane.showMessageDialog(EmployeeGUI.this, "Chưa nhập thông tin nhân viên", "Error", JOptionPane.ERROR_MESSAGE);
                    if (Check.isValidEmail(emailField.getText()) == false) {
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Error Email", "Error", JOptionPane.ERROR_MESSAGE);
                        emailField.requestFocus();
                    }
                    if (Check.isValidPhoneNumber(phonField.getText()) == false) {
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Error phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        phonField.requestFocus();
                    }
                }else{
                    if(employeeBUS.checkEmloyeeID(employeeIDField.getText())==true){
                        JOptionPane.showMessageDialog(EmployeeGUI.this, "Mã nhân viên không khớp!!", "Error", JOptionPane.ERROR_MESSAGE);

                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa nhân viên?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                int index = checkboxPanel.getSelectedItem().toString().indexOf("-");
                                Employee addEmployee = new Employee(employeeIDField.getText(),usernameField.getText(),passwordField.getText(),checkboxPanel.getSelectedItem().toString().substring(0, index),surnameField.getText()
                                ,nameField.getText(),gender,Check.Date_String(birthDate.getDate()),emailField.getText()
                                ,phonField.getText(),addressField.getText());
                                employeeBUS.set(addEmployee);
                                DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                        "Email", "Số điện thoại", "Địa chỉ" }, 0);
                        for (Employee etb : list_Employees) {
                            if(etb.getGender().equals("Nam")){
                                tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                            }
                        }
                        employeeTable.setModel(tableModel1);
                        for(int i = 0; i <= 10; i++){
                            employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
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
        gbc.weightx = 0.5;

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
        String[] positions = { "--Chọn tiêu chí--", "Mã nhân viên", "Họ", "Tên", "Chức vụ"};
        positionComboBox = new JComboBox<>(positions);

        // create radio gender button for panelBot
        JRadioButton maleButton = new JRadioButton("Nam");
        JRadioButton femaleButton = new JRadioButton("Nữ");
        ButtonGroup ButtonGroup = new ButtonGroup(); // Create a button group to group radio buttons
        ButtonGroup.add(maleButton);
        ButtonGroup.add(femaleButton);

        // create JtextField for panelBot searchBar
        searchBar = new JTextField(20);

        // Create the search button
        searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(positionComboBox.getSelectedItem().toString().equals("--Chọn tiêu chí--")){
                    if(maleButton.isSelected() == true){
                        DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                        "Email", "Số điện thoại", "Địa chỉ" }, 0);
                        for (Employee etb : list_Employees) {
                            if(etb.getGender().equals("Nam")){
                                tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                            }
                        }
                        employeeTable.setModel(tableModel1);
                        for(int i = 0; i <= 10; i++){
                            employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }
                    if(femaleButton.isSelected() == true){
                        DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                        "Email", "Số điện thoại", "Địa chỉ" }, 0);
                        for (Employee etb : list_Employees) {
                            if(etb.getGender().equals("Nu")){
                                tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                            }
                        }
                        employeeTable.setModel(tableModel1);
                        for(int i = 0; i <= 10; i++){
                            employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        }
                    }

                }
                if(positionComboBox.getSelectedItem().toString().equals("Mã nhân viên")){
                    
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        if(maleButton.isSelected() == true){
                            DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                            "Email", "Số điện thoại", "Địa chỉ" }, 0);
                            for (Employee etb : list_Employees) {
                                if(etb.getGender().equals("Nam")&&etb.getEmployee_id().equals(searchBar.getText())){
                                    tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                                }
                            }
                            employeeTable.setModel(tableModel1);
                            for(int i = 0; i <= 10; i++){
                                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }
                        if(femaleButton.isSelected() == true){
                            DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                            "Email", "Số điện thoại", "Địa chỉ" }, 0);
                            for (Employee etb : list_Employees) {
                                if(etb.getGender().equals("Nu")&&etb.getEmployee_id().equals(searchBar.getText())){
                                    try {
                                        tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),roledao.nameroles(Integer.parseInt(etb.getRole_id())),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                                    } catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            employeeTable.setModel(tableModel1);
                            for(int i = 0; i <= 10; i++){
                                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }
                    }
                    
                }
                if(positionComboBox.getSelectedItem().toString().equals("Họ")){
                   
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        if(maleButton.isSelected() == true){
                            DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                            "Email", "Số điện thoại", "Địa chỉ" }, 0);
                            for (Employee etb : list_Employees) {
                                if(etb.getGender().equals("Nam")&&etb.getSurname().equals(searchBar.getText())){
                                    try {
                                        tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),roledao.nameroles(Integer.parseInt(etb.getRole_id())),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                                    } catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            employeeTable.setModel(tableModel1);
                            for(int i = 0; i <= 10; i++){
                                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }
                        if(femaleButton.isSelected() == true){
                            DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                            "Email", "Số điện thoại", "Địa chỉ" }, 0);
                            for (Employee etb : list_Employees) {
                                if(etb.getGender().equals("Nu")&&etb.getSurname().equals(searchBar.getText())){
                                    try {
                                        tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),roledao.nameroles(Integer.parseInt(etb.getRole_id())),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                                    } catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            employeeTable.setModel(tableModel1);
                            for(int i = 0; i <= 10; i++){
                                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }
                    }
                    
                }   
                if(positionComboBox.getSelectedItem().toString().equals("Tên")){
                    
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        
                        if(maleButton.isSelected() == true){
                            DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                            "Email", "Số điện thoại", "Địa chỉ" }, 0);
                            for (Employee etb : list_Employees) {
                                if(etb.getGender().equals("Nam")&&etb.getName().equals(searchBar.getText())){
                                    tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                                }
                            }
                            employeeTable.setModel(tableModel1);
                            for(int i = 0; i <= 10; i++){
                                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }
                        if(femaleButton.isSelected() == true){
                            DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                            "Email", "Số điện thoại", "Địa chỉ" }, 0);
                            for (Employee etb : list_Employees) {
                                if(etb.getGender().equals("Nu")&&etb.getName().equals(searchBar.getText())){
                                    tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                                }
                            }
                            employeeTable.setModel(tableModel1);
                            for(int i = 0; i <= 10; i++){
                                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }
                    }
                    
                }  
                if(positionComboBox.getSelectedItem().toString().equals("Chức vụ")){
                    
                    if(searchBar.getText().isEmpty() == true){
                        JOptionPane.showMessageDialog(null, "Chưa nhập thông tin tìm kiếm!!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                       
                        if(maleButton.isSelected() == true){
                            DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                            "Email", "Số điện thoại", "Địa chỉ" }, 0);
                            for (Employee etb : list_Employees) {
                                if(etb.getGender().equals("Nam")/*&&etb.getPosition().equals(searchBar.getText())*/){
                                    tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                                }
                            }
                            employeeTable.setModel(tableModel1);
                            for(int i = 0; i <= 10; i++){
                                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }
                        if(femaleButton.isSelected() == true){
                            DefaultTableModel tableModel1 = new DefaultTableModel(new Object[]{ "Mã nhân viên", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Họ", "Tên", "Giới tính", "Ngày sinh",
                            "Email", "Số điện thoại", "Địa chỉ" }, 0);
                            for (Employee etb : list_Employees) {
                                if(etb.getGender().equals("Nu")/*&&etb.getPosition().equals(searchBar.getText())*/){
                                    tableModel1.addRow(new Object[]{etb.getEmployee_id(),etb.getUser_name(),etb.getPass(),etb.getRole_id(),etb.getSurname(),etb.getName(),etb.getGender(),etb.getBirth(),etb.getEmail(),etb.getPhone(),etb.getAddress()});
                                }
                            }
                            employeeTable.setModel(tableModel1);
                            for(int i = 0; i <= 10; i++){
                                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                            }
                        }
                    }
                    
                }   
            }
        });

        // add to panelBot
        // panelBot = new JPanel(new FlowLayout());
        JPanel panel = new JPanel(new FlowLayout());
        panelBot.setLayout(new BorderLayout());
        panel.add(positionComboBox);
        panel.add(maleButton);
        panel.add(femaleButton);
        panel.add(searchBar);
        panel.add(searchButton);
        panelBot.add(panel, BorderLayout.NORTH);
        // panelBot.add(panelBot, BorderLayout.SOUTH);
        panelBot.add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Nhân viên", wrapperPanel);
        PhanQuyenTruyCap phanQuyenTruyCap = new PhanQuyenTruyCap();
        tabbedPane.addTab("Phân quyền", phanQuyenTruyCap);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(tabbedPane, BorderLayout.CENTER);

        // add the wrapper panel to the frame
        add(wrapper, BorderLayout.CENTER);
    }
}
