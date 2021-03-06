package home.controller;

import home.Main;
import home.dao.JDBCConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BaoCaoThongKeController {

    Main window = new Main();

    @FXML
    private GridPane paneDocGia;

    @FXML
    private Button btnDanhSachCuonSach;

    @FXML
    private Button btnTop3CuonSach;

    @FXML
    private Button btnMuonSachTheoTheLoai;

    @FXML
    private Button btnInPhieuPhat;

    Connection connection = JDBCConnection.getJDBCConnection();
    // tạo connection ở ngoài để mỗi lần click vào button xem report thì hệ thống
    // không tạo commit connection cũ và tạo connection mới nữa


    @FXML
    void danhSachCuonSachAction(ActionEvent event) throws JRException {
        try {
            connection.setAutoCommit(false); // tắt chế độ tự commit
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // set mức cô lập Serializable
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String dir = ".\\src\\home\\report\\TK1_SoLuongCuonSach.jrxml";
//        String pdf = ".\\src\\home\\report\\input\\SoLuongCuonSach.pdf";
        JasperDesign jd = JRXmlLoader.load(dir);
        JasperReport jr = JasperCompileManager.compileReport(dir);
        JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), connection);
        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }


    @FXML
    void muonSachTheoTheLoaiAction(ActionEvent event) throws JRException {
        Connection connection = JDBCConnection.getJDBCConnection();
        String dir = ".\\src\\home\\report\\TK2_MuonSachTheoTheLoai.jrxml";
//        String pdf = "D:\\IntelliJ\\iReport\\Data\\DemoTK1\\MuonSachTheoTheLoai2\\MuonSachTheoTheLoai2.pdf";
        JasperDesign jd = JRXmlLoader.load(dir);
        JasperReport jr = JasperCompileManager.compileReport(dir);
        JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), connection);
        JasperViewer jv = new JasperViewer(jp, false); // tắt report mà không tắt chương trình
        jv.setVisible(true);
    }

    @FXML
    void top3SachMuonNhieu(ActionEvent event) throws JRException {
        window.loadAnotherWindow("/home/fxml/ThangNamThongKeTop3.fxml", "Tháng năm cần thống kê");
        cancelAction(event);
    }

    @FXML
    void cancelAction(ActionEvent event) {
        Stage stage = (Stage) btnTop3CuonSach.getScene().getWindow();
        stage.close();
    }

    @FXML
    void openHomeWindow(ActionEvent event) {
        window.loadAnotherWindow("/home/fxml/MainGUI.fxml");
        cancelAction(event);
    }

}
