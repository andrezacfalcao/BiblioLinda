package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.TipoDeUsuario;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TelaADMINGerenciarUsuariosController {
    @FXML
    private Button voltarTelaInicialAdmin;
    @FXML
    private TableView<Usuario> tableView;
    @FXML
    private TableColumn<Usuario, String> colNome;
    @FXML

    private TableColumn<Usuario, TipoDeUsuario> colTipo;
    @FXML
    private TableColumn<Usuario, String> colLogin;
    @FXML
    private TableColumn<Usuario, Integer> colId;
    @FXML
    private TableColumn<Usuario, String> colSenha;
    @FXML
    private Button buscarUsuario;
    @FXML
    private TextField usuarioTextField;
    @FXML
    private Label excecaoNenhumLivroSelecionado;
    @FXML
    private Button removerUsuario;


    @FXML
    private final ObservableList<Usuario> items = FXCollections.observableArrayList();

    @FXML
    void onVoltarTelaInicialAdminClick (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-ADMIN-principal.fxml"));
            Parent secondScreenParent = loader.load();

            Scene secondScreenScene = new Scene(secondScreenParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(secondScreenScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onBuscarDoGerenciarUsuarioClick() {
        String termoBusca = usuarioTextField.getText().toLowerCase();

        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

        for (Usuario usuario : items) {
            if (usuario.getNome().toLowerCase().contains(termoBusca) ||
                    usuario.getLogin().toLowerCase().contains(termoBusca) ||
                    String.valueOf(usuario.getId()).contains(termoBusca) ||
                    usuario.getTipo().toString().toLowerCase().contains(termoBusca)) {
                usuarios.add(usuario);
            }
        }
        tableView.setItems(usuarios);
    }

    @FXML
    void onRemoverUsuarioClick(ActionEvent event) {
        ControladorUsuario controladorUsuario = ControladorUsuario.getInstancia();
        Usuario usuarioSelecionado = tableView.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado != null) {
            //ControladorLivro controladorLivro = ControladorLivro.getInstancia();

            try {
                controladorUsuario.removerUsuario(usuarioSelecionado);
                // Atualize a TableView ou realize outras ações necessárias após a remoção
            } catch (ObjetoInvalidoException e) {
                e.printStackTrace(); // Trate o erro de acordo com suas necessidades
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-ADMIN-GerenciarUsuarios.fxml"));
                Parent secondScreenParent = loader.load();

                Scene secondScreenScene = new Scene(secondScreenParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(secondScreenScene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void initialize() throws ParametroInvalidoException {
        ControladorUsuario controladorcontroladorUsuario = ControladorUsuario.getInstancia();
        List<Usuario> usuarioList = controladorcontroladorUsuario.listarUsuarios();
        items.addAll(usuarioList);

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        tableView.setItems(items);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            excecaoNenhumLivroSelecionado.setText("");
        });
    }


}
