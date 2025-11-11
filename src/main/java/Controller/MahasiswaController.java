package Controller;

import Model.Mahasiswa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MahasiswaController implements Initializable {
    
    @FXML private TextField txtNim;
    @FXML private TextField txtNama;
    @FXML private TextField txtAttributes;
    
    @FXML private TableView<Mahasiswa> tvMahasiswa;
    @FXML private TableColumn<Mahasiswa, String> colNim;
    @FXML private TableColumn<Mahasiswa, String> colNama;
    @FXML private TableColumn<Mahasiswa, String> colAttributes;
    
    private ObservableList<Mahasiswa> dataMahasiswa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataMahasiswa = FXCollections.observableArrayList();
      
        dataMahasiswa.add(new Mahasiswa("123001", "Randy", "main bola"));
        dataMahasiswa.add(new Mahasiswa("123002", "Asep", "main badminton"));
        
        colNim.setCellValueFactory(new PropertyValueFactory<>("nim"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAttributes.setCellValueFactory(new PropertyValueFactory<>("aktivitas"));
    
        tvMahasiswa.setItems(dataMahasiswa);

        tvMahasiswa.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showMahasiswaDetails(newValue));
    } 
    
    private void showMahasiswaDetails(Mahasiswa mhs) {
        if (mhs != null) {
            txtNim.setText(mhs.getNim());
            txtNama.setText(mhs.getNama());
            txtAttributes.setText(mhs.getAktivitas());
        } else {
            txtNim.setText("");
            txtNama.setText("");
            txtAttributes.setText("");
        }
    }
    
    @FXML
    private void handleAddMahasiswa(ActionEvent event) {
        String nim = txtNim.getText();
        String nama = txtNama.getText();
        String aktivitas = txtAttributes.getText(); 

        
        if (nim.isEmpty() || nama.isEmpty() || aktivitas.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "NIM, Nama, dan Aktivitas tidak boleh kosong.");
            return;
        }

        
        Mahasiswa baru = new Mahasiswa(nim, nama, aktivitas);
        dataMahasiswa.add(baru);
         clearFields(event); 
        
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Mahasiswa berhasil ditambahkan.");
    }
    
    @FXML
    private void handleEditMahasiswa(ActionEvent event) {
        Mahasiswa selectedMhs = tvMahasiswa.getSelectionModel().getSelectedItem();

        if (selectedMhs != null) {
            String aktivitas = txtAttributes.getText(); 
            
            
            selectedMhs.setNim(txtNim.getText()); 
            selectedMhs.setNama(txtNama.getText());
            selectedMhs.setAktivitas(aktivitas); 

            tvMahasiswa.refresh();
            
            
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Mahasiswa berhasil diubah.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih Mahasiswa yang ingin diubah.");
        }
    }

  @FXML
    private void handleDeleteMahasiswa(ActionEvent event) {
    Mahasiswa selectedMhs = tvMahasiswa.getSelectionModel().getSelectedItem();
    if (selectedMhs != null) {
        dataMahasiswa.remove(selectedMhs);
        
        clearFields(event);
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data Mahasiswa berhasil dihapus.");
    } else {
        showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih Mahasiswa yang ingin dihapus.");
    }
}

@FXML
private void clearFields(ActionEvent event) {
    txtNim.clear();
    txtNama.clear();
    txtAttributes.clear();
    tvMahasiswa.getSelectionModel().clearSelection();
}
    
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}