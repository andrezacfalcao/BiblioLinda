package br.ufrpe.bibliolinda.gui;

import br.ufrpe.bibliolinda.beans.Categoria;
import br.ufrpe.bibliolinda.beans.Livro;
import br.ufrpe.bibliolinda.beans.TipoDeUsuario;
import br.ufrpe.bibliolinda.beans.Usuario;
import br.ufrpe.bibliolinda.exception.CamposVaziosException;
import br.ufrpe.bibliolinda.exception.ObjetoInvalidoException;
import br.ufrpe.bibliolinda.exception.ObjetoJaExisteException;
import br.ufrpe.bibliolinda.negocio.ControladorLivro;
import br.ufrpe.bibliolinda.negocio.ControladorUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class TelaADMINAdiconarLivroController {

    @FXML
    private Button adicionar;
    @FXML
    private Button levarTelaLogin;
    @FXML
    private Label excecaoVazio;
    @FXML
    private Label excecaoVazio1;
    @FXML
    private Label excecaoVazio2;
    @FXML
    private Label excecaoVazio3;
    @FXML
    private TextField nome;
    @FXML
    private TextField autor;
    @FXML
    private TextField ano;
    @FXML
    private TextField copias;
    @FXML
    ComboBox<Categoria> categoria = new ComboBox<>();

    @FXML
    private void limparMensagemErro() {
        excecaoVazio1.setText("");
        excecaoVazio.setText("");
        excecaoVazio2.setText("");
        excecaoVazio3.setText("");
    }

    public void onAdicionarClick(ActionEvent event) throws IOException, ObjetoJaExisteException, ObjetoInvalidoException, CamposVaziosException {

        try {
            // Cadastrar novo usuario
            ControladorLivro controladorLivro = ControladorLivro.getInstancia();
            String nomeLivro = nome.getText();
            String nomeAutor = autor.getText();
            String anoDeLancamento = ano.getText();
            String totalDeCopias = copias.getText();
            Categoria categoriaLivro = categoria.getValue();

            // Verifica se os Campos da Interface estão vazios
            if (nomeLivro.isEmpty() || nomeAutor.isEmpty() || anoDeLancamento.isEmpty() || categoria == null) {
                try {

                    throw new CamposVaziosException("Nenhum campo pode estar vazio");
                } catch (CamposVaziosException e) {
                    excecaoVazio.setTextFill(Color.RED);
                    excecaoVazio.setText(e.getMessage());
                    return;
                }
            }
            int anoDeLancament = Integer.parseInt(anoDeLancamento);
            int numeroDeCopia = Integer.parseInt(totalDeCopias);

            Livro novoLivro = new Livro(nomeLivro,categoriaLivro , nomeAutor, anoDeLancament, numeroDeCopia);
            controladorLivro.adicionarLivro(novoLivro);

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

        catch (ObjetoJaExisteException e) {
            try {
                throw new ObjetoJaExisteException("Livro já existe");
            } catch (ObjetoJaExisteException f) {
                excecaoVazio3.setTextFill(Color.RED);
                excecaoVazio3.setText(f.getMessage());
                return;
            }
        } catch (ObjetoInvalidoException e) {
            try {
                throw new CamposVaziosException("Preencha corretamente");
            } catch (CamposVaziosException f) {
                excecaoVazio2.setTextFill(Color.RED);
                excecaoVazio2.setText(f.getMessage());
                return;
            }
        }
    }

   @FXML
    private void initialize() {
        categoria.getItems().addAll(Arrays.asList(Categoria.values()));
    }




}