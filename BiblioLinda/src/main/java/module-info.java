module br.ufrpe.bibliolinda {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens br.ufrpe.bibliolinda to javafx.fxml;
    exports br.ufrpe.bibliolinda;
}