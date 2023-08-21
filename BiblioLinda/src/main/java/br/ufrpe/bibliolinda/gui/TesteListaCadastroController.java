package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.negocio.ControladorUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TesteListaCadastroController {

    @FXML
    private ListView<Usuario> usuarioListView;

    public void initialize() {
        ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();
        usuarioListView.getItems().addAll(controladorUsuario.listarUsuarios());
    }
}
