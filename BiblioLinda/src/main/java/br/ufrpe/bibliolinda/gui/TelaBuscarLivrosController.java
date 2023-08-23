package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.exception.CamposVaziosException;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
import br.ufrpe.bibliolinda.negocio.ControladorSessao;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TelaBuscarLivrosController {
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
    private Button BuscarLivrosdaTavleViewButton;
    @FXML
    private TextField BuscarLivrosdaTavleViewTextField;
    @FXML
    private Button SolicitarEmprestimoButton;
    @FXML
    private Label ExcecaoNenhumLivroSelecionado;
    @FXML
    private AnchorPane capas;
    @FXML
    private final ObservableList<Livro> items = FXCollections.observableArrayList();
    ControladorSessao sessao = ControladorSessao.getInstancia();

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

    @FXML
    private void OnBuscarLivrosdaTavleViewButtonClick() {
        String termoBusca = BuscarLivrosdaTavleViewTextField.getText().toLowerCase();

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
    private void selecionarLivro() {
        Livro livroSelecionado = livrosDisponiveis.getSelectionModel().getSelectedItem();

        if (livroSelecionado != null) {
            ExcecaoNenhumLivroSelecionado.setText(""); // Limpa a mensagem de erro
        } else {
            try {
                throw new CamposVaziosException("Nenhum livro foi selecionado para solicitar empréstimo");
            } catch (CamposVaziosException e) {
                ExcecaoNenhumLivroSelecionado.setTextFill(Color.RED);
                ExcecaoNenhumLivroSelecionado.setText(e.getMessage());
                return;
            }
        }
    }

    @FXML
    private void OnSolicitarEmprestimoClick(ActionEvent event) {
        Livro livroSelecionado = livrosDisponiveis.getSelectionModel().getSelectedItem();

        if (livroSelecionado != null) {
            sessao.setLivroTemp(livroSelecionado);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("solicitacao-de-emprestimo.fxml"));
                Parent secondScreenParent = loader.load();

                Scene secondScreenScene = new Scene(secondScreenParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(secondScreenScene);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new CamposVaziosException("Nenhum livro foi selecionado para solicitar empréstimo");
            } catch (CamposVaziosException e) {
                ExcecaoNenhumLivroSelecionado.setTextFill(Color.RED);
                ExcecaoNenhumLivroSelecionado.setText(e.getMessage());
                return;
            }
        }
    }



}
