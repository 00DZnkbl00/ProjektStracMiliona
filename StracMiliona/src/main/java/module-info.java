module pl.dawidzjava.stracmiliona {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.dawidzjava.stracmiliona to javafx.fxml;
    exports pl.dawidzjava.stracmiliona;
}