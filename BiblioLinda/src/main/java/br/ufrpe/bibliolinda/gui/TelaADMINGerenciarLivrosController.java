package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
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

public class TelaADMINGerenciarLivrosController {
    @FXML
    private Button voltarTelaInicialAdmin;
    @FXML
    private TableView<Livro> livrosDisponiveis;
    @FXML
    private TableColumn<Livro, String> colNome;
    @FXML
    private TableColumn<Livro, Categoria> colCategoria;
    @FXML
    private TableColumn<Livro, String> colAutor;
    @FXML
    private TableColumn<Livro, Integer> colAno;
    @FXML
    private TableColumn<Livro, Integer> colCopias;
    @FXML
    private Button buscarDoGerenciarLivros;
    @FXML
    private Button BuscarLivrosdaTavleViewButton;
    @FXML
    private TextField BuscarLivrosdaTavleViewTextField;
    @FXML
    private Label ExcecaoNenhumLivroSelecionado;

    @FXML
    private final ObservableList<Livro> items = FXCollections.observableArrayList();
    @FXML
    void onVoltarTelaInicialAdminClick (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-principal-ADMIN.fxml"));
            Parent secondScreenParent = loader.load();

            Scene secondScreenScene = new Scene(secondScreenParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(secondScreenScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void initialize() throws ParametroInvalidoException {
        ControladorLivro controladorLivro = ControladorLivro.getInstancia();
        List<Livro> livrosDisp = controladorLivro.livrosDisponiveis();
        items.addAll(livrosDisp);

        colNome.setCellValueFactory(new PropertyValueFactory<>("nomeLivro"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaLivro"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("nomeAutor"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoDeLancamento"));
        colCopias.setCellValueFactory(new PropertyValueFactory<>("totalDeCopias"));

        livrosDisponiveis.setItems(items);

        livrosDisponiveis.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ExcecaoNenhumLivroSelecionado.setText("");
        });
    }

}
