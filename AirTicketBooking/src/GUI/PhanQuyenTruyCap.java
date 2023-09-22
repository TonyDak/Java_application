package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.FuntionBUS;
import BUS.RoleFuncBUS;
import BUS.RolesBUS;
import DTO.Funtions;
import DTO.RoleFunc;
import DTO.Roles;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class PhanQuyenTruyCap extends JPanel {
    private JComboBox<String> positionComboBox;
    // private JRadioButton maleButton, femaleButton;
    private JTextField searchBar;
    private JButton searchButton;
    private JTable employeeTable;
    private JCheckBox[] checkBox;

    private FuntionBUS funtionBUS;
    private RolesBUS rolesBUS;
    private RoleFuncBUS roleFuncBUS;

    public PhanQuyenTruyCap() throws ClassNotFoundException, SQLException {
        initComponent();
    }

    public void initComponent() throws ClassNotFoundException, SQLException {
        // JFrame frame = new JFrame();
        
        setSize(800, 460);
        setVisible(true);

        // set Wrapper
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        funtionBUS = new FuntionBUS();
        ArrayList<Funtions> list_Funtions = funtionBUS.getList_Funtions();
        rolesBUS = new RolesBUS();
        ArrayList<Roles> list_Roles = rolesBUS.getList_Roles();
        roleFuncBUS = new RoleFuncBUS();
        ArrayList<RoleFunc> list_RoleFuncs = roleFuncBUS.getList_RoleFunc();

        // create panel
        JPanel panelTop = new JPanel();
        JPanel panelBot = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelCenter = new JPanel(new GridBagLayout());
        // Create an additional panel
        JPanel additionalPanelLeft = new JPanel(new GridLayout(3, 6));
        JPanel additionalPanelRight = new JPanel(new GridLayout(3, 6));
        // additionalPanelLeft.setBackground(Color.BLUE);
        // additionalPanelRight.setBackground(Color.CYAN);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        // set size for panel
        panelTop.setPreferredSize(new Dimension(1100, 60));
        panelBot.setPreferredSize(new Dimension(1100, 200));
        panelCenter.setPreferredSize(new Dimension(900, 500));
        panelRight.setPreferredSize(new Dimension(200, 250));
        // additionalPanelLeft.setPreferredSize(new Dimension(650, 100));

        // add panel into wrapper
        wrapperPanel.add(panelTop, BorderLayout.NORTH);
        wrapperPanel.add(panelBot, BorderLayout.SOUTH);
        wrapperPanel.add(panelCenter, BorderLayout.CENTER);
        wrapperPanel.add(panelRight, BorderLayout.EAST);

        // add wrapper into frame
        add(wrapperPanel);

        // set label for panelTop
        JLabel labelEmMa = new JLabel();
        JLabel labelEmIn = new JLabel();
        labelEmMa.setText("PHÂN QUYỀN LINH ĐỘNG");

        // set font for label
        labelEmMa.setFont(new Font("Segoe UI", 3, 24));

        // Set positions of labels
        labelEmMa.setHorizontalAlignment(JLabel.CENTER);
        labelEmIn.setHorizontalAlignment(JLabel.LEFT);

        // create label and textfield
        JTextField employeeJTextField = new JTextField();
        employeeJTextField.setEditable(false);
        employeeJTextField.setText(rolesBUS.Createrole());
        employeeJTextField.setFont(fontTextField);
        JTextField usernameField = new JTextField();
        usernameField.setFont(fontTextField);
        JTextField passwordField = new JPasswordField();
        passwordField.setFont(fontTextField);

        // add label for panelTop
        panelTop.setLayout(new GridLayout(2, 1));
        panelTop.add(labelEmMa);
        panelTop.add(labelEmIn);

        // create label and jtextfiled in panelCenter
        JLabel employee_id = new JLabel("Mã quyền:");
        employee_id.setFont(fontLabel);
        JLabel user_name = new JLabel("Tên quyền:");
        user_name.setFont(fontLabel);
        JLabel pass = new JLabel("Mô tả:");
        pass.setFont(fontLabel);

        // set size for text fields
        employeeJTextField.setPreferredSize(new Dimension(500, 20));
        usernameField.setPreferredSize(new Dimension(500, 20));
        passwordField.setPreferredSize(new Dimension(500, 40)); // Increase the height to 40

        // create border
        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY), "Phân quyền",
                        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                        titleFont, Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // add border to panelCenter
        panelCenter.setBorder(nameBorder);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);


        // add label and text field into panelCenter
        panelCenter.setLayout(new GridLayout(10, 5, 5, 5));
        panelCenter.add(employee_id);
        panelCenter.add(employeeJTextField);
        panelCenter.add(user_name);
        panelCenter.add(usernameField);
        panelCenter.add(pass);
        panelCenter.add(passwordField);



        // Add the additional panel to panelCenter
        panelCenter.add(additionalPanelLeft);
        panelCenter.add(additionalPanelRight);
        // Add 6 JCheckBox components to the additional panel left
        String[] checkboxNames = new String[list_Funtions.size()];
        for(int i = 0; i < list_Funtions.size();i++){
            checkboxNames[i] = list_Funtions.get(i).getName();
        }
        checkBox = new JCheckBox[checkboxNames.length];
        for (int i = 0; i < checkboxNames.length; i++) {
            JCheckBox checkBoxn = new JCheckBox(checkboxNames[i]);
            
            checkBoxn.setHorizontalTextPosition(SwingConstants.RIGHT); // Align the text to the right
            checkBoxn.setHorizontalAlignment(SwingConstants.LEFT); // Align the checkbox to the left
            // additionalPanelLeft.add(checkBox);
            checkBox[i] = checkBoxn;
            panelCenter.add(checkBoxn);
        }
        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(employeeJTextField.getText().isEmpty()==true||usernameField.getText().isEmpty()==true
                ){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin cần thêm");
                }else{
                    if(rolesBUS.checkRoleID(employeeJTextField.getText())==false){
                        JOptionPane.showMessageDialog(null, "Mã quyền đã tồn tại");
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận thêm quyềnquyền!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            Roles roles = new Roles();
                            roles.setRoles_id(employeeJTextField.getText());
                            roles.setName(usernameField.getText());
                            roles.setDescription(passwordField.getText());
                            try {
                                rolesBUS.add(roles);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            for (JCheckBox cb : checkBox) {
                                if (cb.isSelected()==true) {
                                    for (Funtions funtions : list_Funtions) {
                                        if(funtions.getName().equals(cb.getText())){
                                            RoleFunc roleFunc = new RoleFunc();
                                            roleFunc.setRoleId(employeeJTextField.getText());
                                            roleFunc.setFunctionId(funtions.getFuntion_id());
                                            try {
                                                roleFuncBUS.add(roleFunc);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                            
                
                                String[] columnNames = { "Ma Quyen", "Ten Quyen", "Mo ta" };
                                // JTable employeeTable = new JTable(tableModel);
                                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                                for (Roles r : list_Roles) {
                                    tableModel.addRow(new Object[]{r.getRoles_id(),r.getName(),r.getDescription()});
                                }
                                employeeTable.setModel(tableModel);
                                employeeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                                employeeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                                employeeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                            
                            
                        
                        }else{
                            // Xử lý khi nhấn No
                        }
                    }
                }
            }
        });
        JButton delButton = new JButton("Xóa");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(employeeJTextField.getText().isEmpty()==true||usernameField.getText().isEmpty()==true
                ){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin cần thêm");
                }else{
                    if(rolesBUS.checkRoleID(employeeJTextField.getText())==true){
                        JOptionPane.showMessageDialog(null, "Mã quyền không tồn tại");
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận xóa quuyền!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            try {
                                roleFuncBUS.deleteallRole(employeeJTextField.getText());

                                rolesBUS.delete(employeeJTextField.getText());

                                String[] columnNames = { "Ma Quyen", "Ten Quyen", "Mo ta" };
                                // JTable employeeTable = new JTable(tableModel);
                                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                                for (Roles r : list_Roles) {
                                    tableModel.addRow(new Object[]{r.getRoles_id(),r.getName(),r.getDescription()});
                                }
                                employeeTable.setModel(tableModel);
                                employeeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                                employeeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                                employeeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }else{
                            // Xử lý khi nhấn No
                        }
                    }
                }
            }
        });
        JButton editButton = new JButton("Sửa");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(employeeJTextField.getText().isEmpty()==true||usernameField.getText().isEmpty()==true
                ){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin cần thêm");
                }else{
                    if(rolesBUS.checkRoleID(employeeJTextField.getText())==true){
                        JOptionPane.showMessageDialog(null, "Mã quyền không tồn tại");
                    }else{
                        int option = JOptionPane.showConfirmDialog(null, "Xác nhận sửa quyền!!", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION){
                            // Xử lý khi nhấn Yes
                            Roles roles = new Roles();
                            roles.setRoles_id(employeeJTextField.getText());
                            roles.setName(usernameField.getText());
                            roles.setDescription(passwordField.getText());
                            try {
                                roleFuncBUS.deleteallRole(employeeJTextField.getText());
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                             for (JCheckBox cb : checkBox) {
                                if (cb.isSelected()==true) {
                                    for (Funtions funtions : list_Funtions) {
                                        if(funtions.getName().equals(cb.getText())){
                                            RoleFunc roleFunc = new RoleFunc();
                                            roleFunc.setRoleId(employeeJTextField.getText());
                                            roleFunc.setFunctionId(funtions.getFuntion_id());
                                            try {
                                                roleFuncBUS.add(roleFunc);
                                            } catch (ClassNotFoundException | SQLException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                            try {
                                rolesBUS.set(roles);
                                String[] columnNames = { "Ma Quyen", "Ten Quyen", "Mo ta" };
                                // JTable employeeTable = new JTable(tableModel);
                                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                                for (Roles r : list_Roles) {
                                    tableModel.addRow(new Object[]{r.getRoles_id(),r.getName(),r.getDescription()});
                                }
                                employeeTable.setModel(tableModel);
                                employeeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                                employeeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                                employeeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                            } catch (ClassNotFoundException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
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
        gbc.insets = new Insets(5, 5, 5, 5);
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

        // create combobox for panelBot
        String[] positions = { "--Vị trí--", "Phi công", "Tiếp viên", "Bán vé" };
        positionComboBox = new JComboBox<>(positions);

        // create JtextField for panelBot searchBar
        searchBar = new JTextField(20);

        // Create the search button
        searchButton = new JButton("Search");

        // add JTable for panelBot
        String[] columnNames = { "Ma Quyen", "Ten Quyen", "Mo ta" };
        // JTable employeeTable = new JTable(tableModel);
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Roles roles : list_Roles) {
            tableModel.addRow(new Object[]{roles.getRoles_id(),roles.getName(),roles.getDescription()});
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
        employeeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        employeeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        employeeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    for (JCheckBox cb : checkBox){
                        cb.setSelected(false);
                    }
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    // Lấy dữ liệu từ JTable

                    String id = target.getValueAt(row, 0).toString();
                    employeeJTextField.setText(id);
                    String name = target.getValueAt(row, 1).toString();
                    usernameField.setText(name);
                    String employeeid = (target.getValueAt(row, 2) == null)? "":target.getValueAt(row, 2).toString();
                    if(employeeid == ""){
                        passwordField.setText("");
                    }else{
                        passwordField.setText(employeeid);

                    }
                    for (RoleFunc roleFunc : list_RoleFuncs) {
                        if(roleFunc.getRoleId().equals(id)){
                            for (Funtions funtions : list_Funtions) {
                                if(roleFunc.getFunctionId().equals(funtions.getFuntion_id())){
                                    for (JCheckBox cb : checkBox) {
                                        if(cb.getText().equals(funtions.getName())){
                                            cb.setSelected(true);
                                        }
                                    }
                                }
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
        // panel.add(positionComboBox);
        // panel.add(maleButton);
        // panel.add(femaleButton);
        // panel.add(searchBar);
        // panel.add(searchButton);
        panelBot.add(panel, BorderLayout.NORTH);
        panelBot.add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        // pack and center frame
        
    }

    
}
