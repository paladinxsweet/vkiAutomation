package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.VeritabaniUtil.VeritabaniUtil.VeritabaninUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class loginController {
    //------------------------------------------------------------------------------------------------------------------------------
    Connection baglanti = null;
    PreparedStatement sorguIfadesi = null;
    ResultSet getirilen = null;
    String sql;
    private Runnable loginSuccessAction;

    public loginController() {
        baglanti = VeritabaninUtil.Baglan();
    }
    //------------------------------------------------------------------------------------------------------------------------------

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Text errorField;

    @FXML
    private Text cikis1;

    @FXML
    private Button girisButton;

    @FXML
    private TextField kullaniciAdi;

    @FXML
    private PasswordField kullaniciSifre;

    @FXML
    void cikis1Aksiyon(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void girisAksiyonu(ActionEvent event) {
        String username = kullaniciAdi.getText();
        String password = kullaniciSifre.getText();

        try {
            sql = "SELECT * FROM admingiris WHERE kullaniciAdi = ? AND sifre = ?";
            sorguIfadesi = baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1, username);
            sorguIfadesi.setString(2, password);
            getirilen = sorguIfadesi.executeQuery();

            if (getirilen.next()) {
                // User authentication successful
                errorField.setText("Giriş Başarılı!!");

                // Call the login success action if it is set
                if (loginSuccessAction != null) {
                    loginSuccessAction.run();
                }
            } else {
                // User authentication failed
                System.out.println("Kullanıcı adı veya parola Hatalı!!");
                errorField.setText("Kullanıcı adı veya parola Hatalı!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLoginSuccessAction(Runnable action) {
        this.loginSuccessAction = action;
    }
}
