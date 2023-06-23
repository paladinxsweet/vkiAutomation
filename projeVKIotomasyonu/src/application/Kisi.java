package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.VeritabaniUtil.VeritabaniUtil.VeritabaninUtil;

public class Kisi {
	
	
	Connection baglanti=null;
	PreparedStatement sorguIfadesi=null;
	ResultSet getirilen=null;
	String sql;
	public void SampleController() {	
		baglanti=VeritabaninUtil.Baglan();
	}

	 public Kisi(Date tarihColumn, String telefonColumn, String adColumn, int boyColumn, String durumColumn,
			int kiloColumn, double vkiColumn, String soyadColumn) {
		super();
		this.tarihColumn = tarihColumn;
		this.telefonColumn = telefonColumn;
		this.adColumn = adColumn;
		this.boyColumn = boyColumn;
		this.durumColumn = durumColumn;
		this.kiloColumn = kiloColumn;
		this.vkiColumn = vkiColumn;
		this.soyadColumn = soyadColumn;
	}
	 public Kisi() {
	        
	    }
	private Date tarihColumn;
	    private String telefonColumn;
	    private String adColumn;
	    private int boyColumn;
	    private String durumColumn;
	    private int kiloColumn;
	    private double vkiColumn;
	    private String soyadColumn;
		public Date getTarihColumn() {
			return tarihColumn;
		}
		public void setTarihColumn(Date tarihColumn) {
			this.tarihColumn = tarihColumn;
		}
		public String getTelefonColumn() {
			return telefonColumn;
		}
		public void setTelefonColumn(String telefonColumn) {
			this.telefonColumn = telefonColumn;
		}
		public String getAdColumn() {
			return adColumn;
		}
		public void setAdColumn(String adColumn) {
			this.adColumn = adColumn;
		}
		public int getBoyColumn() {
			return boyColumn;
		}
		public void setBoyColumn(int boyColumn) {
			this.boyColumn = boyColumn;
		}
		public String getDurumColumn() {
			return durumColumn;
		}
		public void setDurumColumn(String durumColumn) {
			this.durumColumn = durumColumn;
		}
		public int getKiloColumn() {
			return kiloColumn;
		}
		public void setKiloColumn(int kiloColumn) {
			this.kiloColumn = kiloColumn;
		}
		public double getVkiColumn() {
			return vkiColumn;
		}
		public void setVkiColumn(double vkiColumn) {
			this.vkiColumn = vkiColumn;
		}
		public String getSoyadColumn() {
			return soyadColumn;
		}
		public void setSoyadColumn(String soyadColumn) {
			this.soyadColumn = soyadColumn;
		}

}
