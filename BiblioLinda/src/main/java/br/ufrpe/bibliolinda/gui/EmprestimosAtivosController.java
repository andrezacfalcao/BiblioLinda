package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
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

    public void initialize() {
        // Configura as colunas para receber os dados
        usuarioColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        livroColumn.setCellValueFactory(new PropertyValueFactory<>("livro"));

        // Carrega os empréstimos ativos do controlador
        List<Emprestimo> emprestimosAtivos = ControladorEmprestimo.getInstancia().obterEmprestimosAtivos();

        // Preenche a TableView com os empréstimos ativos
        tableView.getItems().addAll(emprestimosAtivos);
    }
}
