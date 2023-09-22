package GUI;

import javax.swing.*;

import DTO.Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import BUS.RoleFuncBUS;

public class Manager extends JFrame{
    private Employee employee;
    private String ROLE_ID= Login.ROLE_ID;

    private JPanel menuPanel;
    private RoleFuncBUS roleFuncBUS =new RoleFuncBUS();

    JPanel pannelNorth;

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    public Employee getEmployee(){
        return employee;
    }
    public Manager(Employee employee) throws ClassNotFoundException, SQLException {
        initialize(employee);
        
    }

    private void initialize(Employee employee) throws ClassNotFoundException, SQLException{
        setResizable(false);
        setTitle("Airline Management System");
        setSize(1500, 950);
        setLayout(new BorderLayout());
        Font titleFont = new Font("Arial", Font.PLAIN, 14);
        String[] functions = roleFuncBUS.funtionname(ROLE_ID);
        // Tạo panel chứa các chức năng
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(200, menuPanel.getHeight()));
        menuPanel.setBackground(Color.LIGHT_GRAY);

        JPanel panel = new JPanel(new GridLayout(functions.length, 1, 5, 20));
        panel.setBackground(Color.LIGHT_GRAY);
        for (String function : functions) {
            JButton btnFunction = new JButton(function);
            btnFunction.setPreferredSize(new Dimension(170, 40));
            btnFunction.setFont(titleFont);
            btnFunction.setForeground(Color.BLACK);
            btnFunction.setBackground(Color.WHITE);
            btnFunction.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnFunction.setHorizontalAlignment(SwingConstants.LEFT);
            if(function.equals("BÁN VÉ")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/icons8_ticket_purchase_50px_1.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("CHUYẾN BAY")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/Paper Plane_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("LỘ TRÌNH")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/world_map_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("LỊCH TRÌNH")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/Google Calendar_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("VÉ & LOẠI VÉ")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/ticket_50px_yellow.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("MÁY BAY")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/airplane_mode_on_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("SÂN BAY")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/icons8_airport_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("HÃNG")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/MiroTools_icons8_airplane_tail_fin_50px_8.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("NHÂN VIÊN")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/icons8_staff_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("KHÁCH HÀNG")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/person_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("HÓA ĐƠN")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/invoice_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            if(function.equals("THỐNG KÊ")){
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/combo_chart_50px.png"); // Đường dẫn tới tệp hình ảnh biểu tượng
                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                // Đặt biểu tượng đã thay đổi kích thước cho nút
                btnFunction.setIcon(scaledIcon);
            }
            btnFunction.addActionListener(e -> {
                try {
                    handleFunctionButtonClick(function);
                } catch (ClassNotFoundException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }  
            });
            panel.add(btnFunction);
        }

        // Tạo JToggleButton để hiển thị/ẩn panel
        pannelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pannelNorth.setBackground(new Color(173,216,230));

        JToggleButton toggleButton = new JToggleButton();
        ImageIcon icon = new ImageIcon("D:/code/TicketBooking/AirTicketBooking/src/Image/menu_48px.png");
        toggleButton.setBackground(new Color(173,216,230));
        toggleButton.setIcon(icon);
        toggleButton.setPreferredSize(new Dimension(48, 48));
        toggleButton.addActionListener(e -> {
            boolean visible = menuPanel.isVisible();
            menuPanel.setVisible(!visible);
        });

        JLabel helloLabel = new JLabel();
        helloLabel.setForeground(Color.BLACK);
        helloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        helloLabel.setText("Xin Chào "+employee.getEmployee_id()+ " - " +employee.getSurname() +" "+employee.getName());

        JButton logoutButton = new JButton("Đăng xuất");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(Color.BLACK);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Xử lý khi nhấn Yes
                    dispose();
                    try {
                        new Login();
                    } catch (ClassNotFoundException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else {
                    // Xử lý khi nhấn No hoặc đóng hộp thoại
                }
            }
        });

        pannelNorth.add(toggleButton);
        pannelNorth.add(helloLabel);
        pannelNorth.add(logoutButton);
        pannelNorth.setName("pannelNorth");
        add(pannelNorth, BorderLayout.NORTH );
        menuPanel.setName("menuPanel");
        menuPanel.add(panel);
        add(menuPanel, BorderLayout.WEST);
        JPanel backgroundPanel = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("D:/code/DoAnJava/TicketBooking/AirTicketBooking/src/Image/cac-hang-ve-cua-vietjet-2_auto_x2.jpg");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, 1284, 853, null);
            }
        };
        backgroundPanel.setName("bakground");
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        //getContentPane().add(new TicketFilterForm(), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Xử lý khi người dùng click vào nút chức năng
    private void handleFunctionButtonClick(String function) throws ClassNotFoundException, SQLException {
        // TODO: Xử lý khi người dùng click vào nút chức năng
        if(function.equals("BÁN VÉ")){
            System.out.println("Dang focus");
            boolean banVeGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof BanVeGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    banVeGUIExists = true;
                    BanVeGUI banVeGUI = new BanVeGUI(employee);
                    banVeGUI.setName("banVeGUI");
                    getContentPane().add(banVeGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!banVeGUIExists) { // Nếu không có BanVeGUI trong getContentPane() thì thêm mới
                BanVeGUI banVeGUI = new BanVeGUI(employee);
                banVeGUI.setName("banVeGUI");
                getContentPane().add(banVeGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("HÓA ĐƠN")){
            System.out.println("Dang focus");
            boolean BillGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof BillGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    BillGUIExists = true;
                    BillGUI billGUI = new BillGUI(employee);
                    billGUI.setName("billGUI");
                    getContentPane().add(billGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!BillGUIExists) { // Nếu không có BillGUI trong getContentPane() thì thêm mới
                BillGUI billGUI = new BillGUI(employee);
                billGUI.setName("billGUI");
                getContentPane().add(billGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("NHÂN VIÊN")){
            System.out.println("Dang focus");
            boolean EmployeeGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof EmployeeGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    EmployeeGUIExists = true;
                    EmployeeGUI EmployeeGUI = new EmployeeGUI();
                    EmployeeGUI.setName("EmployeeGUI");
                    getContentPane().add(EmployeeGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!EmployeeGUIExists) { // Nếu không có EmployeeGUI trong getContentPane() thì thêm mới
                EmployeeGUI EmployeeGUI = new EmployeeGUI();
                EmployeeGUI.setName("EmployeeGUI");
                getContentPane().add(EmployeeGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("MÁY BAY")){
            System.out.println("Dang focus");
            boolean PlaneManagerGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof PlaneManagerGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    PlaneManagerGUIExists = true;
                    PlaneManagerGUI PlaneManagerGUI = new PlaneManagerGUI();
                    PlaneManagerGUI.setName("PlaneManagerGUI");
                    getContentPane().add(PlaneManagerGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!PlaneManagerGUIExists) { // Nếu không có PlaneManagerGUI trong getContentPane() thì thêm mới
                PlaneManagerGUI PlaneManagerGUI = new PlaneManagerGUI();
                PlaneManagerGUI.setName("PlaneManagerGUI");
                getContentPane().add(PlaneManagerGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("LỘ TRÌNH")){
            System.out.println("Dang focus");
            boolean RouteManagerGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof RouteManagerGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    RouteManagerGUIExists = true;
                    RouteManagerGUI RouteManagerGUI = new RouteManagerGUI();
                    RouteManagerGUI.setName("RouteManagerGUI");
                    getContentPane().add(RouteManagerGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!RouteManagerGUIExists) { // Nếu không có RouteManagerGUI trong getContentPane() thì thêm mới
                RouteManagerGUI RouteManagerGUI = new RouteManagerGUI();
                RouteManagerGUI.setName("RouteManagerGUI");
                getContentPane().add(RouteManagerGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("KHÁCH HÀNG")){
            System.out.println("Dang focus");
            boolean CustomerGUI2Exists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof CustomerGUI2) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    CustomerGUI2Exists = true;
                    CustomerGUI2 CustomerGUI2 = new CustomerGUI2();
                    CustomerGUI2.setName("CustomerGUI2");
                    getContentPane().add(CustomerGUI2, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!CustomerGUI2Exists) { // Nếu không có CustomerGUI2 trong getContentPane() thì thêm mới
                CustomerGUI2 CustomerGUI2 = new CustomerGUI2();
                CustomerGUI2.setName("CustomerGUI2");
                getContentPane().add(CustomerGUI2, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("HÃNG")){
            System.out.println("Dang focus");
            boolean QuanLyHangHangKhongExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof QuanLyHangHangKhong) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    QuanLyHangHangKhongExists = true;
                    QuanLyHangHangKhong QuanLyHangHangKhong = new QuanLyHangHangKhong();
                    QuanLyHangHangKhong.setName("QuanLyHangHangKhong");
                    getContentPane().add(QuanLyHangHangKhong, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!QuanLyHangHangKhongExists) { // Nếu không có QuanLyHangHangKhong trong getContentPane() thì thêm mới
                QuanLyHangHangKhong QuanLyHangHangKhong = new QuanLyHangHangKhong();
                QuanLyHangHangKhong.setName("QuanLyHangHangKhong");
                getContentPane().add(QuanLyHangHangKhong, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("VÉ & LOẠI VÉ")){
            System.out.println("Dang focus");
            boolean TicketGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof TicketGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    TicketGUIExists = true;
                    TicketGUI TicketGUI = new TicketGUI();
                    TicketGUI.setName("TicketGUI");
                    getContentPane().add(TicketGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!TicketGUIExists) { // Nếu không có TicketGUI trong getContentPane() thì thêm mới
                TicketGUI TicketGUI = new TicketGUI();
                TicketGUI.setName("TicketGUI");
                getContentPane().add(TicketGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("LỊCH TRÌNH")){
            System.out.println("Dang focus");
            boolean FlightScheduleGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof FlightScheduleGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    FlightScheduleGUIExists = true;
                    FlightScheduleGUI FlightScheduleGUI = new FlightScheduleGUI();
                    FlightScheduleGUI.setName("FlightScheduleGUI");
                    getContentPane().add(FlightScheduleGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!FlightScheduleGUIExists) { // Nếu không có FlightScheduleGUI trong getContentPane() thì thêm mới
                FlightScheduleGUI FlightScheduleGUI = new FlightScheduleGUI();
                FlightScheduleGUI.setName("FlightScheduleGUI");
                getContentPane().add(FlightScheduleGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("CHUYẾN BAY")){
            System.out.println("Dang focus");
            boolean FlightManagerGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof FlightManagerGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    FlightManagerGUIExists = true;
                    FlightManagerGUI FlightManagerGUI = new FlightManagerGUI();
                    FlightManagerGUI.setName("FlightManagerGUI");
                    getContentPane().add(FlightManagerGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!FlightManagerGUIExists) { // Nếu không có FlightManagerGUI trong getContentPane() thì thêm mới
                FlightManagerGUI FlightManagerGUI = new FlightManagerGUI();
                FlightManagerGUI.setName("FlightManagerGUI");
                getContentPane().add(FlightManagerGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("SÂN BAY")){
            System.out.println("Dang focus");
            boolean AirportManagerGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof AirportManagerGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    AirportManagerGUIExists = true;
                    AirportManagerGUI AirportManagerGUI = new AirportManagerGUI();
                    AirportManagerGUI.setName("AirportManagerGUI");
                    getContentPane().add(AirportManagerGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!AirportManagerGUIExists) { // Nếu không có AirportManagerGUI trong getContentPane() thì thêm mới
                AirportManagerGUI AirportManagerGUI = new AirportManagerGUI();
                AirportManagerGUI.setName("AirportManagerGUI");
                getContentPane().add(AirportManagerGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        if(function.equals("THỐNG KÊ")){
            System.out.println("Dang focus");
            boolean ThongKeGUIExists = false;
            for (Component component : getContentPane().getComponents()) {
                if (component instanceof ThongKeGUI) { // Kiểm tra nếu component là TicketFilterForm
                    getContentPane().remove(component); // Remove component đó
                    ThongKeGUIExists = true;
                    ThongKeGUI ThongKeGUI = new ThongKeGUI();
                    ThongKeGUI.setName("ThongKeGUI");
                    getContentPane().add(ThongKeGUI, BorderLayout.CENTER); // Thêm component mới
                    revalidate(); // Cập nhật container
                    repaint(); // Vẽ lại nội dung
                    break; // Thoát khỏi vòng for sau khi đã tìm thấy và thực hiện thay đổi
                }else{
                    if (component.getName().equals("pannelNorth") || component.getName().equals("menuPanel")) {
                        // Không xóa các panel này
                    } else {
                        getContentPane().remove(component); // Xóa component khác
                    }
                }
            }
            if (!ThongKeGUIExists) { // Nếu không có ThongKeGUI trong getContentPane() thì thêm mới
                ThongKeGUI ThongKeGUI = new ThongKeGUI();
                ThongKeGUI.setName("ThongKeGUI");
                getContentPane().add(ThongKeGUI, BorderLayout.CENTER);
                revalidate(); // Cập nhật container
                repaint(); // Vẽ lại nội dung
            }
        }
        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // new Manager();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
