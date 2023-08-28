package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
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
    private TextField livroTextField;
    @FXML
    private Label excecaoNenhumLivroSelecionado;
    @FXML
    private Button removerLivro;
    @FXML
    private Button adicionarLivro;
    @FXML
    private Button editarLivro;
    private Livro livroSelecionado;

    @FXML
    private final ObservableList<Livro> items = FXCollections.observableArrayList();

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
    private void onBuscarDoGerenciarLivrosClick() {
        String termoBusca = livroTextField.getText().toLowerCase();

        ObservableList<Livro> livrosEncontrados = FXCollections.observableArrayList();

        for (Livro livro : items) {
            if (livro.getNomeLivro().toLowerCase().contains(termoBusca) ||
                    livro.getNomeAutor().toLowerCase().contains(termoBusca) ||
                    livro.getCategoriaLivro().toString().toLowerCase().contains(termoBusca)) {
                livrosEncontrados.add(livro);
            }
        }
        livrosDisponiveis.setItems(livrosEncontrados);
    }

    @FXML
    void onRemoverLivroClick(ActionEvent event) {
        ControladorLivro controladorLivro = ControladorLivro.getInstancia();
        Livro livroSelecionado = livrosDisponiveis.getSelectionModel().getSelectedItem();

        if (livroSelecionado != null) {
            //ControladorLivro controladorLivro = ControladorLivro.getInstancia();

            try {
                controladorLivro.removerLivro(livroSelecionado);
                // Atualize a TableView ou realize outras ações necessárias após a remoção
            } catch (ObjetoInvalidoException | ParametroInvalidoException e) {
                e.printStackTrace(); // Trate o erro de acordo com suas necessidades
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-ADMIN-GerenciarLivros.fxml"));
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
        ControladorLivro controladorLivro = ControladorLivro.getInstancia();
        List<Livro> livrosDisp = controladorLivro.listarLivros();
        items.addAll(livrosDisp);

        colNome.setCellValueFactory(new PropertyValueFactory<>("nomeLivro"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaLivro"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("nomeAutor"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoDeLancamento"));
        colCopias.setCellValueFactory(new PropertyValueFactory<>("totalDeCopias"));

        livrosDisponiveis.setItems(items);

        livrosDisponiveis.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            excecaoNenhumLivroSelecionado.setText("");
        });
    }

    @FXML
    void onadicionarLivroClick (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-ADMIN-adicionar-livros.fxml"));
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
    void onEditarLivroClick (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-ADMIN-EditarLivros.fxml"));
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
