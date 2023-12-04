module pl.dawidzjava.stracmiliona {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens pl.dawidzjava.stracmiliona to javafx.fxml;
    exports pl.dawidzjava.stracmiliona;
}