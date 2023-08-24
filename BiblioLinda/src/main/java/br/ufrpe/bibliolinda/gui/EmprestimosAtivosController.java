package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EmprestimosAtivosController {

    @FXML
    private TableView<Emprestimo> tableView;
    @FXML
    private TableColumn<Emprestimo, String> usuarioColumn;
    @FXML
    private TableColumn<Emprestimo, String> livroColumn;
    @FXML
    private final ObservableList<Emprestimo> items = FXCollections.observableArrayList();

    public void initialize() {
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        List<Emprestimo> emprestimosAtivos = controladorEmprestimo.obterEmprestimosAtivos();
        items.addAll(emprestimosAtivos);

        // Configura as colunas para receber os dados
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        livroColumn.setCellValueFactory(new PropertyValueFactory<>("livro"));

        // Preenche a TableView com os empréstimos ativos
        tableView.setItems(items);
    }
}
