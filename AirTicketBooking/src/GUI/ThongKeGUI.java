package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.BillBUS;
import BUS.DetailBillBUS;
import BUS.TicketBUS;
import DTO.Bill;
import DTO.DetailBill;
import DTO.Ticket;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.*;
import java.awt.Font;

public class ThongKeGUI extends JPanel {
    private JComboBox<String> yearBox2, yearBox3, yBox;
    private JButton exportBtn;
    private JPanel profitManager, employeeManager;
    private JTable dashTable , employeeTable;

    private BillBUS billBUS;
    private DetailBillBUS detailBillBUS;
    private TicketBUS ticketBUS;
    private int eco,bus,slhd, sumeco,sumbus,sumslhd;
    private double totalprice, sumtotalprice;

    private void showSaveFileDialog(JTable table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn vị trí và tên file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("File Excel (*.xlsx)", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            exportToExcel(filePath, table);
        }
    }
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontName("Times New Roman"); 
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    private void exportToExcel(String filePath, JTable table) {
        try (// Tạo workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Tạo một trang mới
            XSSFSheet sheet = workbook.createSheet("Data");

            // Lấy mô hình dữ liệu từ JTable
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Ghi tiêu đề vào dòng đầu tiên
            XSSFRow headerRow = sheet.createRow(0);
            CellStyle cellStyle = createStyleForHeader(sheet);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(model.getColumnName(i));
            }

            // Ghi dữ liệu vào từng dòng
            for (int row = 0; row < model.getRowCount(); row++) {
                XSSFRow excelRow = sheet.createRow(row + 1);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    XSSFCell cell = excelRow.createCell(col);
                    Object value = model.getValueAt(row, col);
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }
            // Lưu workbook vào file Excel
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xuất file Excel!");
            }
        } catch (HeadlessException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ThongKeGUI() throws ClassNotFoundException, SQLException {
        // setResizable(false);
        setSize(900, 500);
        setLayout(new BorderLayout());

        billBUS = new BillBUS();
        ArrayList<Bill> list_Bills = billBUS.getList_Bills();
        detailBillBUS = new DetailBillBUS();
        ArrayList<DetailBill> list_DetailBills = detailBillBUS.getList_DetailBills();
        ticketBUS = new TicketBUS();
        ArrayList<Ticket> list_Tickets =  ticketBUS.getList_Tickets();
        // employeeBUS = new EmployeeBUS();
        // ArrayList<Employee> list_Employees = employeeBUS.getList_Employees();

        profitManager = new JPanel(new BorderLayout());
        JPanel panelticketTop = new JPanel();
        JPanel panelticketCenter = new JPanel();

        panelticketTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelticketCenter.setLayout(new BorderLayout());

        
        JLabel yearLabelBot = new JLabel("Năm");
        panelticketTop.add(yearLabelBot);

        yearBox2 = new JComboBox<>();
        yearBox2.addItem("--Chọn năm--");
        String year = "";
        for (Bill bill : list_Bills) {
            if(year.equals(bill.getBill_date().substring(6))){
                continue;
            }else{
                year = bill.getBill_date().substring(6);
                yearBox2.addItem(year);
            }
        }
        panelticketTop.add(yearBox2);

        exportBtn = new JButton("Export");
        panelticketTop.add(exportBtn);

        panelticketCenter.add(panelticketTop, BorderLayout.NORTH);
        DefaultTableModel dashs = new DefaultTableModel(new Object[]{"Tháng", "SL hóa đơn", "SL vé", "Vé Eco", "Vé Bus", "Tổng thu"}, 0);
        dashTable = new JTable(dashs){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        dashTable.setRowHeight(30);
        Font font = new Font("Arial", 2, 16);
        dashTable.setFont((java.awt.Font) font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        yearBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedOption = (String) e.getItem();
                    DefaultTableModel dashs2 = new DefaultTableModel(new Object[]{"Tháng", "SL hóa đơn", "SL vé", "Vé Eco", "Vé Bus", "Tổng thu"}, 0);
                    sumeco=0;
                    sumbus=0;
                    sumtotalprice=0;
                    sumslhd=0;
                    for(int i = 1; i <= 12 ; i++){
                        slhd=0;totalprice=0;
                        eco=0;bus=0;
                        for (Bill bill : list_Bills) {
                            
                            if(Integer.parseInt(bill.getBill_date().substring(3,5)) == i && selectedOption.equals(bill.getBill_date().substring(6))){
                                for (DetailBill detailBill : list_DetailBills) {
                                    if(detailBill.getBill_id().equals(bill.getBill_id())){
                                        for (Ticket ticket : list_Tickets) {
                                            if(ticket.getTicket_id().equals(detailBill.getType_id())){
                                                if(ticket.getType_id().startsWith("ECO")){
                                                    eco += detailBill.getAmount();
                                                }
                                                if(ticket.getType_id().startsWith("BUS")){
                                                    bus += detailBill.getAmount();
                                                }
                                            }
                                        }
                                    }
                                }
                                slhd++;
                                totalprice += bill.getTotal_price();
                                
                            }
                        }
                        sumeco+=eco;
                        sumbus+=bus;
                        sumtotalprice+=totalprice;
                        sumslhd+=slhd;
                        dashs2.addRow(new Object[]{i,slhd,eco+bus,eco,bus,totalprice});
                    }
                    dashs2.addRow(new Object[]{"Tổng cộng",sumslhd,sumeco+sumbus,sumeco,sumbus,sumtotalprice});
                    dashTable.setModel(dashs2);
                    for (int k = 0; k <= 5; k++) {
                        dashTable.getColumnModel().getColumn(k).setCellRenderer(centerRenderer);
                    }
                }
            }
        });
        exportBtn.addActionListener(e -> {
            try {
                showSaveFileDialog(dashTable);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        panelticketCenter.add(new JScrollPane(dashTable), BorderLayout.CENTER);
        dashTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
        profitManager.add(panelticketTop, BorderLayout.NORTH);
        profitManager.add(panelticketCenter, BorderLayout.CENTER);
        //////////////////////
        // employeeManager
        employeeManager = new JPanel(new BorderLayout());
        JPanel employeeTop = new JPanel();
        JPanel employeeCenter = new JPanel();
        JLabel yearLabel = new JLabel("Năm");
        yBox = new JComboBox<>();
        yBox.addItem("--Chọn năm--");
        String year4 = "";
        for (Bill bill : list_Bills) {
            if(year4.equals(bill.getBill_date().substring(6))){
                continue;
            }else{
                year4 = bill.getBill_date().substring(6);
                yBox.addItem(year4);
            }
        }
        employeeTop.add(yearLabel);
        employeeTop.add(yBox);
        JLabel yearLabelBot2 = new JLabel("Mã nhân viên: ");
        employeeTop.add(yearLabelBot2);

        yearBox3 = new JComboBox<>();
        yearBox3.addItem("--Chọn nhân viên--");
        String year2 = "";
        for (Bill bill : list_Bills) {
            if(year.equals(bill.getEmployee_id())){
                continue;
            }else{
                year2 = bill.getEmployee_id();
                yearBox3.addItem(year2);
            }
        }
        employeeTop.add(yearBox3);
        JButton exportBtn2 = new JButton("Export");
        employeeTop.add(exportBtn2);
       

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Tháng", "SL hóa đơn", "Tổng thu"}, 0);
        employeeTable = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Trả về giá trị false để không cho phép sửa thông tin trên JTable
                return false;
            }
        };
        employeeTable.setRowHeight(31);
        employeeTable.setFont((java.awt.Font) font);
        yearBox3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    sumtotalprice=0;
                    sumslhd=0;
                    if(yBox.getSelectedItem().toString().equals("--Chọn năm--")){
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn năm");

                    }else{
                        String selectedOption = (String) e.getItem();
                        DefaultTableModel model = new DefaultTableModel(new Object[]{"Tháng", "SL hóa đơn", "Tổng thu"}, 0);
                        for(int i = 1; i <= 12 ; i++){
                            slhd=0;totalprice=0;
                            for (Bill bill : list_Bills) {
                                if(Integer.parseInt(bill.getBill_date().substring(3,5)) == i && selectedOption.equals(bill.getEmployee_id())&& yBox.getSelectedItem().toString().equals(bill.getBill_date().substring(6))){
                                    slhd++;
                                    totalprice += bill.getTotal_price();
                                    
                                }
                               
                                
                            }
                            sumslhd+=slhd;
                            sumtotalprice+=totalprice;
                            model.addRow(new Object[]{i,slhd,totalprice});
                            
                        }
                        model.addRow(new Object[]{"Tổng cộng",sumslhd,sumtotalprice});
                            employeeTable.setModel(model);
                            for (int k = 0; k <= 2; k++) {
                                employeeTable.getColumnModel().getColumn(k).setCellRenderer(centerRenderer);
                            }
                    }
                    
                }
            }
        });
        exportBtn2.addActionListener(e -> {
            try {
                showSaveFileDialog(employeeTable);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        employeeCenter.add(new JScrollPane(employeeTable));
        employeeManager.add(employeeTop, BorderLayout.NORTH);
        employeeManager.add(employeeCenter, BorderLayout.CENTER);

        /// tab
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Doanh thu", profitManager);
        tabbedPane.addTab("Nhân viên bán hàng", employeeManager);
        // create the wrapper panel and add the tabbed pane to it
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(tabbedPane, BorderLayout.CENTER);
        // add the wrapper panel to the frame
        add(wrapper, BorderLayout.CENTER);
        // add(profitManager);
        // pack and center frame
        
    }
}
