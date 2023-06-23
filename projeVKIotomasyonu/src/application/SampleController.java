package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.List;
import java.util.ResourceBundle;


import com.VeritabaniUtil.VeritabaniUtil.VeritabaninUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;





public class SampleController {

	
	//VERÝTABANI BAÐLANTISI VE TANIMLAMALARI-------------------------------------------------------------------------------------------------------------------------------------------------------------- 1
		Connection baglanti=null;
		PreparedStatement sorguIfadesi=null;
		ResultSet getirilen=null;
		String sql;
		public SampleController() {	
			baglanti=VeritabaninUtil.Baglan();
		}
	//------------------------------------------------------------------------------------------------------------------------------
	
		  public void TabloKisilerDoldur(TableView<Kisi> tabloKISILER) {
		    	
			  sql = "select * from kisi";
		    	
		    	ObservableList<Kisi> kisiler = FXCollections.observableArrayList();
		    	try
		    	{
		    		sorguIfadesi=baglanti.prepareStatement(sql);
		        	getirilen = sorguIfadesi.executeQuery();
		        	while(getirilen.next())
		        	
		        		
		        		
		        		
		        	{
		        		kisiler.add(new Kisi(getirilen.getDate("tarihColumn"),
		        				getirilen.getString("telefonColumn").trim(),
		        				getirilen.getString("adColumn").trim(),
		        				getirilen.getInt("boyColumn"),
		        				getirilen.getString("durumColumn").trim(),
		        				getirilen.getInt("kiloColumn"),
		        				getirilen.getDouble("vkiColumn"),
		        				getirilen.getString("soyadColumn").trim()));
		        		
		        		
		        		 soyadColumn.setCellValueFactory(new PropertyValueFactory<>("soyadColumn"));
		        		tarihColumn.setCellValueFactory(new PropertyValueFactory<>("tarihColumn"));
		        	    telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefonColumn"));
		        	    adColumn.setCellValueFactory(new PropertyValueFactory<>("adColumn"));
		        	    boyColumn.setCellValueFactory(new PropertyValueFactory<>("boyColumn"));
		        	    durumColumn.setCellValueFactory(new PropertyValueFactory<>("durumColumn"));
		        	    kiloColumn.setCellValueFactory(new PropertyValueFactory<>("kiloColumn"));
		        	    vkiColumn.setCellValueFactory(new PropertyValueFactory<>("vkiColumn"));
		        	   
		        	
		        	
		        	tabloKISILER.setItems(kisiler);
		        	
		       
		    	}
		    	}
		    	catch (Exception e)
		    	{
		    		System.out.print(e.getMessage());
		    	}
		
		    	
		    	
		    	
		    	FilteredList<Kisi> filtre = new FilteredList<>(kisiler, b -> true);

		    	alanARA.textProperty().addListener((observable, oldValue, newValue) -> {
					filtre.setPredicate(employee -> {
										
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						
						String lowerCaseFilter = newValue.toLowerCase();
						
						if (employee.getAdColumn().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
							return true;
						} 
						else  
						return false;
					});
				});
				
				SortedList<Kisi> sortedData = new SortedList<>(filtre);
				

				sortedData.comparatorProperty().bind(tabloKISILER.comparatorProperty());
				
				tabloKISILER.setItems(sortedData);
		    	
		    	
		  }
		  
		    
		//-----------------------------------------------------------------------------------------------------------------------------------
		  
		  

		    @FXML
		    void dugmeEkleAksiyonu(ActionEvent event)throws SQLException {

		    	Date tarih = Date.valueOf(tarihGiris.getValue());
		        String telefon = telefonGiris.getText().trim();
		        String ad = adGiris.getText().trim();
		        int boy = Integer.parseInt(boyGiris.getText());
		        String durum = durumCikti.getText().trim();
		        int kilo = Integer.parseInt(agirlikGiris.getText());
		        double vki = Double.parseDouble(vkiCikti.getText());
		        String soyad = soyadGiris.getText().trim();
		        
		       
		        String sql = "INSERT INTO kisi (tarihColumn, telefonColumn, adColumn, boyColumn, durumColumn, kiloColumn, vkiColumn, soyadColumn) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		        PreparedStatement statement = baglanti.prepareStatement(sql);
		        statement.setDate(1, tarih);
		        statement.setString(2, telefon);
		        statement.setString(3, ad);
		        statement.setInt(4, boy);
		        statement.setString(5, durum);
		        statement.setInt(6, kilo);
		        statement.setDouble(7, vki);
		        statement.setString(8, soyad);
		        
		        int affectedRows = statement.executeUpdate();
		        
		        if (affectedRows > 0) {
		          
		            TabloKisilerDoldur(tabloKISILER);
		            
		         
		            butonlariTemizle();
		            
		            
		            Alert alert = new Alert(AlertType.INFORMATION);
		            alert.setTitle("Başarılı");
		            alert.setHeaderText(null);
		            alert.setContentText("Veri başarıyla eklendi.");
		            alert.showAndWait();
		        } else {
		            
		            Alert alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Hata");
		            alert.setHeaderText(null);
		            alert.setContentText("Veri eklenirken bir hata oluştu.");
		            alert.showAndWait();
		        }
		    }
		
		    
		    //-----------------------------------------------------------------------------------------------------------------------------------
		
		    
		    @FXML
		
		    void tabloClick(MouseEvent event) {
		    	Kisi k = new Kisi();
		    	k = (Kisi) tabloKISILER.getItems().get(tabloKISILER.getSelectionModel().getSelectedIndex());
		   // 	tarihColumn.setText(k.getTarihColumn().toLocalDate());
		    	telefonColumn.setText(k.getTelefonColumn());
		    	adColumn.setText(k.getAdColumn());
		    	boyColumn.setText(""+k.getBoyColumn());
		    	durumColumn.setText(k.getDurumColumn());
		    	kiloColumn.setText(""+k.getKiloColumn());
		    	vkiColumn.setText(""+k.getVkiColumn());
		    	soyadColumn.setText(k.getSoyadColumn());
		   
		    }
		    
			//SİL BUTONUNU YAZDIRAN -----------------------------------------------------------------------------------------------------------------------------------
		    @FXML
		    void dugmeSilAksiyonu(ActionEvent event) throws SQLException {
		        Kisi seciliKisi = tabloKISILER.getSelectionModel().getSelectedItem();
		        if (seciliKisi != null) {
		            String soyad = seciliKisi.getSoyadColumn();

		            String sql = "DELETE FROM kisi WHERE soyadColumn = ?";
		            PreparedStatement statement = baglanti.prepareStatement(sql);
		            statement.setString(1, soyad);

		            int affectedRows = statement.executeUpdate();

		            if (affectedRows > 0) {
		                TabloKisilerDoldur(tabloKISILER);
		                butonlariTemizle();

		                Alert alert = new Alert(AlertType.INFORMATION);
		                alert.setTitle("Başarılı");
		                alert.setHeaderText(null);
		                alert.setContentText("Veri başarıyla silindi.");
		                alert.showAndWait();
		            } else {
		                Alert alert = new Alert(AlertType.ERROR);
		                alert.setTitle("Hata");
		                alert.setHeaderText(null);
		                alert.setContentText("Veri silinirken bir hata oluştu.");
		                alert.showAndWait();
		            }
		        } else {
		            Alert alert = new Alert(AlertType.WARNING);
		            alert.setTitle("Uyarı");
		            alert.setHeaderText(null);
		            alert.setContentText("Lütfen silinecek bir kişi seçin.");
		            alert.showAndWait();
		        }
		    }


		  //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		    public void butonlariTemizle() {
		    	
		   
		 
		    	    tarihGiris.setValue(LocalDate.of(2022, 01, 01));
		    	    telefonGiris.clear();
		    	    adGiris.clear();
		    	    boyGiris.clear();
		    	    durumCikti.clear();
		    	    agirlikGiris.clear();
		    	    vkiCikti.clear();
		    	    soyadGiris.clear();
		    	}

		    @FXML
		    void dugmeYeniAksiyonu(ActionEvent event) {
		    	butonlariTemizle();
		    	
		    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	  @FXML
	 private Button dugmeEKLE;

	 @FXML
	 private Button dugmeSIL;

	 @FXML
	 private Button dugmeYENI;
		    
	@FXML
    private ResourceBundle resources;
    
    @FXML
    private TableColumn<Kisi, String> tarihColumn;

    @FXML
    private TableColumn<Kisi, String> telefonColumn;

    @FXML
    private TableColumn<Kisi, String> adColumn;
    
    @FXML
    private TableColumn<Kisi, Integer> boyColumn;

    @FXML
    private TableColumn<Kisi, String> durumColumn;

    @FXML
    private TableColumn<Kisi, Integer> kiloColumn;

    @FXML
    private TableColumn<Kisi, Double> vkiColumn;
    
    @FXML
    private TableColumn<Kisi, String> soyadColumn;
    

    @FXML
    private TableView<Kisi> tabloKISILER;

    @FXML
    private URL location;
    
    @FXML
    private TextField alanARA;

    @FXML
    private TextField adGiris;

    @FXML
    private TextField agirlikGiris; 

    @FXML
    private TextField boyGiris; 

    @FXML
    private Button buttonHesapla; 

    @FXML
    private Text cikis;

    @FXML
    private TextField durumCikti; 

    @FXML
    private TextField soyadGiris;

    @FXML
    private DatePicker tarihGiris;

    @FXML
    private TextField telefonGiris;

    @FXML
    private TextField vkiCikti; 
    

   

    @FXML
    void cikis(MouseEvent event) {
System.exit(0);
    }

    @FXML
    void hesapla(ActionEvent event) {
try {
	Double agirlikDeger=Double.parseDouble(agirlikGiris.getText());
	Double uzunlukDeger=Double.parseDouble(boyGiris.getText());
	Double vkiDeger=agirlikDeger/((uzunlukDeger/100)*(uzunlukDeger/100));
  
    
	setResult(vkiDeger);
}
catch(Exception e){
	
}
    }
    void setResult(Double vkiDeger )
    {
    	vkiCikti.setText(vkiDeger.toString());
    	if(vkiDeger <= 18.5 ) {
    		durumCikti.setText("ZAYIF");
    	}
    	else if(18.6 <= vkiDeger && vkiDeger <= 24.9) {
    	   durumCikti.setText("NORMAL KİLO");   
    	}
    	else if(25 <= vkiDeger && vkiDeger <= 29.9)
    	{
    		durumCikti.setText("KİLOLU");
    	}
    	
    	else {
    		
    	durumCikti.setText("OBEZİTE");
    	}
    	
    }
    @FXML
    void initialize() {
    	TabloKisilerDoldur(tabloKISILER);

    }

}
