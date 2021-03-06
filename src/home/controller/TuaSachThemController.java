package home.controller;

import home.Main;
import home.dao.TuaSachDao;
import home.model.TuaSach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TuaSachThemController implements Initializable {

    TuaSachDao tuaSachDao = new TuaSachDao();
    Main window = new Main();

    ObservableList<String> TheLoaiList = FXCollections.observableArrayList("Giáo trình",
            "Tài liệu tham khảo", "Luận văn", "Văn học nghệ thuật", "Khác", "Khoa học công nghệ – Kinh tế", "Văn hóa xã hội – Lịch sử");
    ObservableList<String> NXBList = FXCollections.observableArrayList("ĐHQG TPHCM", "Trẻ",
            "Giáo dục", "ĐHQG Hà Nội", "Tổng hợp TPHCM", "Kim Đồng",
            "Nước Ngoài", "Chính trị Quốc gia", "Văn hóa - thông tin");

    @FXML
    private TextField tfTenTuaSach;

    @FXML
    private ComboBox<String> comboboxTheLoai;

    @FXML
    private TextField tfTacGia;

    @FXML
    private ComboBox<String> comboboxNXB;


    @FXML
    private Button btnThemTuaSach;

    @FXML
    private Button btnCancel;

    @FXML
    private Spinner<Integer> soLuongSpinner;

    @FXML
    void cancelAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        window.loadAnotherWindow("/home/fxml/TuaSachDanhSach.fxml");
    }


    @FXML
    void themTuaSachAction(ActionEvent event) {
        String tenSach = tfTenTuaSach.getText();
        String theLoai = comboboxTheLoai.getValue();
        String tacGia = tfTacGia.getText();
        String nxb = comboboxNXB.getValue();
        int soLuong = soLuongSpinner.getValue();


        if (tenSach.isEmpty() || theLoai.isEmpty() || tacGia.isEmpty() || nxb.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Bạn phải nhập đầy đủ thông tin!!!");
            alert.showAndWait();
            return;
        }

        TuaSach tuaSach = new TuaSach(tenSach, theLoai, tacGia, nxb, soLuong);
        boolean flag = tuaSachDao.themTuaSach(tuaSach);
        if (flag) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Thêm tựa sách thành công");
            alert.showAndWait();
            cancelAction(event);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Không thêm được tựa sách");
            alert.showAndWait();
            cancelAction(event);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboboxTheLoai.setItems(TheLoaiList);
        comboboxTheLoai.getSelectionModel().selectFirst();
        comboboxNXB.setItems(NXBList);
        comboboxNXB.getSelectionModel().selectFirst();
        soLuongSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20));
        soLuongSpinner.getValueFactory().setValue(0);
        soLuongSpinner.setDisable(true);

    }

}
