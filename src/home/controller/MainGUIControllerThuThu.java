package home.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainGUIControllerThuThu implements Initializable {

    Main window = new Main();

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnDocGia;

    @FXML
    private Button btnTuaSach;

    @FXML
    private Button btnCuonSach;

    @FXML
    private Button btnPhieuMuon;

    @FXML
    private Button btnPhieuTra;

    @FXML
    private Button btnPhieuPhat;

    @FXML
    private Label lblNguoiDung;

    @FXML
    private Button btnLogout;

    @FXML
    private FontAwesomeIconView btnClose;

    @FXML
    void closeWindowAction(MouseEvent event) {
        if (event.getSource() == btnClose) {
            System.exit(0);
        }
    }

    @FXML
    void handleClickAction(ActionEvent event) {
        if (event.getSource() == btnDocGia) {
            loadUI("/home/fxml/DocGiaDanhSach.fxml");
        } else if (event.getSource() == btnTuaSach) {
            loadUI("/home/fxml/TuaSachDanhSach.fxml");
        } else if (event.getSource() == btnCuonSach) {
            loadUI("/home/fxml/CuonSachDanhSach.fxml");
        } else if (event.getSource() == btnPhieuMuon) {
            loadUI("/home/fxml/PhieuMuonSachDanhSach.fxml");
        } else if (event.getSource() == btnPhieuTra) {
            loadUI("/home/fxml/PhieuTraSachDanhSach.fxml");
        } else if (event.getSource() == btnPhieuPhat) {
            loadUI("/home/fxml/PhieuPhatDanhSach.fxml");
        } else if (event.getSource() == btnLogout) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Bạn muốn đăng xuất chứ?");
            Optional<ButtonType> response = alert.showAndWait();
            if (response.get() == ButtonType.CANCEL) {
                return;
            }
            window.loadAnotherWindow("/home/fxml/DangNhap.fxml", "Phần mềm quản lí thư viện - Đăng nhập");
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.close();
        } else if (event.getSource() == btnHome || event.getSource() == null) {
            loadUI(("/home/fxml/Intro.fxml"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String nguoiDung = DangNhapController.getNguoiDung();
        lblNguoiDung.setText(nguoiDung);
        loadUI(("/home/fxml/DocGiaDanhSach.fxml"));
    }


    public void loadUI(String nameUI) {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource(nameUI));
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
