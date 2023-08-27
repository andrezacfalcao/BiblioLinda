package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Emprestimo;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.dados.RepositorioEmprestimo;
import br.ufrpe.bibliolinda.exception.ParametroInvalidoException;
import br.ufrpe.bibliolinda.negocio.ControladorEmprestimo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaHistoricoLeituraClienteController {

    @FXML
    private TableView<Emprestimo> tableView;
    @FXML
    private TableColumn<Emprestimo, String> tituloColumn;
    @FXML
    private TableColumn<Emprestimo, String> autorColumn;
    @FXML
    private TableColumn<Emprestimo, String> dataEmprestimoColumn;
    @FXML
    private Button voltarTelaInicioCliente;
    private ObservableList<Emprestimo> emprestimos = FXCollections.observableArrayList();

    public void initialize() {
        ControladorEmprestimo controladorEmprestimo = ControladorEmprestimo.getInstancia();
        // Configure as células de valor das colunas
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("livro.titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("livro.autor"));
        dataEmprestimoColumn.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));

        // Simule o carregamento de empréstimos do usuário
        // Adicione os empréstimos à lista observável
        // Defina a lista observável como o item da TableView
        tableView.setItems(emprestimos);
    }

    // Simulação: Busca os empréstimos do usuário a partir de algum lugar
    RepositorioEmprestimo repositorioEmprestimo = RepositorioEmprestimo.getInstancia();
    public List<Emprestimo> listarEmprestimosPorCliente(Usuario usuario) throws ParametroInvalidoException {
        List<Emprestimo> resultado = new ArrayList<>(); // ajeitar pq nao pode ter essa funcao aqui. como faço p chamar so o nome?

        if (usuario != null) {
            for (Emprestimo emprestimo : repositorioEmprestimo.listarEmprestimos())
                if (emprestimo.getUsuario().equals(usuario))
                    resultado.add(emprestimo);

            return resultado;
        } else {
            throw new ParametroInvalidoException("O usuário não pode ser nulo!");
        }
    }

    @FXML
    void onvoltarTelaInicioClienteClick (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tela-principal-CLIENTE.fxml"));
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
