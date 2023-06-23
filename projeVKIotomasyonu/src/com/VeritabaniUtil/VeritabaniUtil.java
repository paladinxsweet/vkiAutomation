package com.VeritabaniUtil;

import java.sql.Connection;
import java.sql.DriverManager;


public class VeritabaniUtil {
	public class VeritabaninUtil {
		static Connection conn=null;


		public static Connection Baglan() {
			
			
			try {
				//"jdbc:mysql://serverIPadress/db_ismi",kullanici","sifre"
				conn=DriverManager.getConnection("jdbc:mysql://localhost/birinic","root","mysql");
				System.out.println("Baðlantý Baþarýlý...");
				return conn;
				
				
			}catch(Exception e)
			{
				System.out.print("Baðlantý Baþarýsýz!!!");
				System.out.println(e.getMessage().toString());
				return null;
			}
		}


}
}
