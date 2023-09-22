package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import BUS.CustomerBUS;
import DTO.Customer;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerGUI extends JPanel {
    private JTextField customerIdTextField, surnameTextField, nameTextField, emailTextField, phoneTextField, addressTextField;
    private CustomerBUS customerBUS;
    JTable resultTable;

    public CustomerGUI() throws ClassNotFoundException, SQLException {
        customerBUS = new CustomerBUS();
        ArrayList<Customer> customers = customerBUS.getList_Customers();
        setSize(500, 500);

        Font fontLabel = new Font("SansSerif", Font.CENTER_BASELINE, 18);
        Font fontTextField = new Font("Arial", Font.PLAIN, 16);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(600, 400));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10); // thêm khoảng cách 10px vào top, left, bottom, right

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        JLabel customerIdLabel = new JLabel("Mã Khách hàng:");
        customerIdLabel.setFont(fontLabel);
        panel.add(customerIdLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        JLabel surnameLabel = new JLabel("Họ:");
        surnameLabel.setFont(fontLabel);
        panel.add(surnameLabel, c);

        c.gridx = 0;
        c.gridy = 2;
        JLabel nameLabel = new JLabel("Tên:");
        nameLabel.setFont(fontLabel);
        panel.add(nameLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(fontLabel);
        panel.add(emailLabel, c);

        c.gridx = 0;
        c.gridy = 4;
        JLabel phoneLabel = new JLabel("Số điện thoại:");
        phoneLabel.setFont(fontLabel);
        panel.add(phoneLabel, c);

        c.gridx = 0;
        c.gridy = 5;
        JLabel addressLabel = new JLabel("Địa chỉ:");
        addressLabel.setFont(fontLabel);
        panel.add(addressLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        customerIdTextField = new JTextField();
        customerIdTextField.setFont(fontTextField);
        customerIdTextField.setPreferredSize(new Dimension(300, 30));
        panel.add(customerIdTextField, c);
        customerIdTextField.setText(customerBUS.CreateMKH());
        customerIdTextField.setEditable(false);

        c.gridx = 1;
        c.gridy = 1;
        surnameTextField = new JTextField();
        surnameTextField.setFont(fontTextField);
        surnameTextField.setPreferredSize(new Dimension(300, 30));
        panel.add(surnameTextField, c);

        c.gridx = 1;
        c.gridy = 2;
        nameTextField = new JTextField();
        nameTextField.setFont(fontTextField);
        nameTextField.setPreferredSize(new Dimension(300, 30));
        panel.add(nameTextField, c);

        c.gridx = 1;
        c.gridy = 3;
        emailTextField = new JTextField();
        emailTextField.setFont(fontTextField);
        emailTextField.setPreferredSize(new Dimension(300, 30));
        emailTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Check.validateEmail(emailTextField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Check.validateEmail(emailTextField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Check.validateEmail(emailTextField);
            }
        });
        panel.add(emailTextField, c);

        c.gridx = 1;
        c.gridy = 4;
        phoneTextField = new JTextField();
        phoneTextField.setFont(fontTextField);
        phoneTextField.setPreferredSize(new Dimension(300, 30));
        phoneTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Check.validatePhoneNumber(phoneTextField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Check.validatePhoneNumber(phoneTextField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                Check.validatePhoneNumber(phoneTextField);
            }
        });
        panel.add(phoneTextField, c);

        c.gridx = 1;
        c.gridy = 5;
        addressTextField = new JTextField();
        addressTextField.setFont(fontTextField);
        addressTextField.setPreferredSize(new Dimension(300, 30));
        panel.add(addressTextField, c);

        JButton showCustomersButton = new JButton("Danh sách Khách hàng");

        showCustomersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // code to show list of customers in new frame
                JFrame customerListFrame = new JFrame("Danh sách khách hàng");
                customerListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customerListFrame.setSize(1500, 700);

                DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã KH", "Họ", "Tên", "Email", "Phone", "Address"}, 0);
                for (Customer customer : customers) {
                    model.addRow(new Object[]{customer.getCustomer_id(), customer.getSurname(), customer.getName(),
                        customer.getEmail(), customer.getPhone(), customer.getAddress()});
                }
                resultTable = new JTable(model){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                        return false;
                    }
                };
                resultTable.setPreferredScrollableViewportSize(new Dimension(750, 500));
                resultTable.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(resultTable);
                customerListFrame.getContentPane().add(scrollPane);

                resultTable.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1) {
                            JTable target = (JTable)e.getSource();
                            int row = target.getSelectedRow();
                            // Lấy dữ liệu từ JTable
                            String id = target.getValueAt(row, 0).toString();
                            customerIdTextField.setText(id);
                            customerIdTextField.setEditable(false);
                            String surname = target.getValueAt(row, 1).toString();
                            surnameTextField.setText(surname);
                            surnameTextField.setEditable(false);
                            String name = target.getValueAt(row, 2).toString();
                            nameTextField.setText(name);
                            nameTextField.setEditable(false);
                            String email = target.getValueAt(row, 3).toString();
                            emailTextField.setText(email);
                            emailTextField.setEditable(false);
                            String phone = target.getValueAt(row, 4).toString();
                            phoneTextField.setText(phone);
                            phoneTextField.setEditable(false);
                            String address = target.getValueAt(row, 5).toString();
                            addressTextField.setText(address);
                            addressTextField.setEditable(false);
                            // Tạo JFrame mới để hiển thị chi tiết chuyến bay
                            
                        
                        }
                    }
                });
                customerListFrame.setLocationRelativeTo(null);
                customerListFrame.setVisible(true);


            }
        });

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 4;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(showCustomersButton, c);

        Font titleFont = new Font("Segoe UI", 3, 24);
        Border nameBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Thông tin khách hàng",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                titleFont, Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        panel.setBorder(nameBorder);
        add(panel);
        setVisible(true);
    }

    

    public JTable getResultTable() {
        return resultTable;
    }
    public void setResultTable(JTable resultTable) {
        this.resultTable = resultTable;
    }
    


    public void setCustomerIdTextField(JTextField customerIdTextField) {
        this.customerIdTextField = customerIdTextField;
    }



    public JTextField getSurnameTextField() {
        return surnameTextField;
    }



    public void setSurnameTextField(JTextField surnameTextField) {
        this.surnameTextField = surnameTextField;
    }



    public JTextField getNameTextField() {
        return nameTextField;
    }



    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }



    public JTextField getEmailTextField() {
        return emailTextField;
    }



    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }



    public JTextField getPhoneTextField() {
        return phoneTextField;
    }



    public void setPhoneTextField(JTextField phoneTextField) {
        this.phoneTextField = phoneTextField;
    }



    public JTextField getAddressTextField() {
        return addressTextField;
    }



    public void setAddressTextField(JTextField addressTextField) {
        this.addressTextField = addressTextField;
    }

    

    public JTextField getCustomerIdTextField() {
        return customerIdTextField;
    }
}