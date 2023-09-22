package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import BUS.EmployeeBUS;
import DTO.Employee;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panel;
    private EmployeeBUS list_Employees;

    public static String ROLE_ID;

    public Login() throws ClassNotFoundException, SQLException {
        list_Employees = new EmployeeBUS();
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create border for username field
        Font titleFont = new Font("Arial", Font.PLAIN, 14);
        Border usernameBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Tài khoản",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                titleFont, Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        Border passwordBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Mật khẩu",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                titleFont, Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        // Create JPanel
        panel = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/dai_ly_ve_may_bay.jpg");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, 400, 250, this);
            }
        };
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        

        usernameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        usernameField.setBorder(usernameBorder);
        usernameField.setForeground(Color.BLACK);
        panel.add(usernameField, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        passwordField.setBorder(passwordBorder);
        passwordField.setForeground(Color.BLACK);
        panel.add(passwordField, constraints);

        loginButton = new JButton("Đăng nhập");
        constraints.gridx = 1;
        constraints.gridy = 2;
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(loginButton, constraints);

        // Set preferred size of panel
        panel.setPreferredSize(new Dimension(400, 200));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Thực hiện logic đăng nhập ở đây
                if ((list_Employees.chekcLogin(username, password) ==  true)) {
                    dispose();
                    Employee employee = list_Employees.getEmployee(username, password);
                    ROLE_ID = employee.getRole_id();
                    try {
                        Manager manager = new Manager(employee);
                        manager.setEmployee(employee);
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);                }
            }
        });
        getRootPane().setDefaultButton(loginButton);
        // Add panel to JFrame and center it
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new Login();
    }
}

