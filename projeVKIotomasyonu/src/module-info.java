module projeGorkem {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
	
	opens application to javafx.graphics, javafx.fxml, javafx.base, java.sql, javafx.controls ;
}
