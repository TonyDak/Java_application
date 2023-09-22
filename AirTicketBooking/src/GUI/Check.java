package GUI;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Check {

    public static String Date_String(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
    public static Date String_Date(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(date);
    }
    public static boolean isValidEmail(String email) {
        // Kiểm tra độ dài của email
        if (email.length() > 320) {
            return false;
        }
        // Sử dụng regular expression để kiểm tra định dạng email
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(String phone) {
        // Kiểm tra độ dài chuỗi nhập vào có đúng 10 kí tự không
        if (phone.length() != 10) {
            return false;
        }
        // Kiểm tra các kí tự trong chuỗi nhập vào có phải là số hay không
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static void validateEmail(JTextField emailTextField) {
        String email = emailTextField.getText();
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        boolean isValid = matcher.matches();
        emailTextField.setBorder(BorderFactory.createLineBorder(isValid ? Color.BLACK : Color.RED));
    }
    public static void validatePhoneNumber(JTextField phoneTextField) {
        String phoneNumber = phoneTextField.getText();
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean isValid = matcher.matches();
        phoneTextField.setBorder(BorderFactory.createLineBorder(isValid ? Color.BLACK : Color.RED));
    }
    public static void validateNumber(JTextField phoneTextField) {
        String phoneNumber = phoneTextField.getText();
        Pattern pattern = Pattern.compile("^\\d+");
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean isValid = matcher.matches();
        phoneTextField.setBorder(BorderFactory.createLineBorder(isValid ? Color.BLACK : Color.RED));
    }
}
