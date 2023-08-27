package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
import javafx.beans.property.SimpleBooleanProperty;
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
import java.time.LocalDate;
import java.util.List;

public class TelaADMINGerenciarEmprestimosController {
    @FXML
    private Button voltarTelaInicialAdmin1;
    @FXML
    private TableView<Emprestimo> emprestimos;
    @FXML
    private TableColumn<Emprestimo, Usuario> usuarioColumn;
    @FXML
    private TableColumn<Emprestimo, Livro> livroColumn;
    @FXML
    private TableColumn<Emprestimo, LocalDate> dataEmprestimoColumn;
    @FXML
    private TableColumn<Emprestimo, LocalDate> dataDevolucaoColumn;
    @FXML
    private TableColumn<Emprestimo, Boolean> emprestadoColumn;
    @FXML
    private TableColumn<Emprestimo, LocalDate> dataLimiteColumn;

    @FXML
    private final ObservableList<Emprestimo> items = FXCollections.observableArrayList();

    @FXML
    void onvoltarTelaInicialAdmin1Click (ActionEvent event) {
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
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        List<Emprestimo> emprestimoGeral = controladorEmprestimo.listarEmprestimos();
        items.addAll(emprestimoGeral);

        // Configura as colunas para receber os dados
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        livroColumn.setCellValueFactory(new PropertyValueFactory<>("livro"));
        emprestadoColumn.setCellValueFactory(new PropertyValueFactory<>("emprestimoAtivoBoo"));
        dataDevolucaoColumn.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        dataEmprestimoColumn.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        dataLimiteColumn.setCellValueFactory(new PropertyValueFactory<>("dataLimite"));

        emprestimos.setItems(items);
    }


}
