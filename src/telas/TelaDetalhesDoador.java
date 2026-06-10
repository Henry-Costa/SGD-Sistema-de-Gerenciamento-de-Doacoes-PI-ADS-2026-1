package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Doador;
import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaDetalhesDoador
        extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private Doador doador;

    private CampoTextoPadrao campoNome;

    private CampoTextoPadrao campoEmail;

    private CampoTextoPadrao campoTelefone;

    private CampoTextoPadrao campoDocumento;

    public TelaDetalhesDoador(
            Usuario usuario,
            Doador doador
    ) {

        super("Detalhes do Doador");

        this.usuario = usuario;

        this.doador = doador;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBackground(PAINEL);

        painel.setBounds(
                220,
                40,
                450,
                500
        );

        add(painel);

        JLabel titulo =
                new JLabel(
                        "Detalhes do Doador"
                );

        titulo.setBounds(
                70,
                20,
                320,
                40
        );

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        painel.add(titulo);

        adicionarCampo(
                painel,
                "Nome",
                90
        );

        campoNome =
                new CampoTextoPadrao();

        campoNome.setText(
                doador.getNome()
        );

        campoNome.setBounds(
                50,
                115,
                340,
                35
        );

        painel.add(campoNome);

        adicionarCampo(
                painel,
                "Email",
                160
        );

        campoEmail =
                new CampoTextoPadrao();

        campoEmail.setText(
                doador.getEmail()
        );

        campoEmail.setBounds(
                50,
                185,
                340,
                35
        );

        painel.add(campoEmail);

        adicionarCampo(
                painel,
                "Telefone",
                230
        );

        campoTelefone =
                new CampoTextoPadrao();

        campoTelefone.setText(
                doador.getTelefone()
        );

        campoTelefone.setBounds(
                50,
                255,
                340,
                35
        );

        painel.add(campoTelefone);

        adicionarCampo(
                painel,
                "Documento",
                300
        );

        campoDocumento =
                new CampoTextoPadrao();

        campoDocumento.setText(
                doador.getDocumento()
        );

        campoDocumento.setEditable(false);

        campoDocumento.setBounds(
                50,
                325,
                340,
                35
        );

        painel.add(campoDocumento);

        if(usuario.isAdministrador()) {

            BotaoPadrao btnSalvar =
                    new BotaoPadrao(
                            "Salvar"
                    );

            btnSalvar.setBounds(
                    50,
                    390,
                    150,
                    40
            );

            btnSalvar.addActionListener(
                    e -> salvar()
            );

            painel.add(btnSalvar);

            BotaoPadrao btnExcluir =
                    new BotaoPadrao(
                            "Excluir"
                    );

            btnExcluir.setBounds(
                    240,
                    390,
                    150,
                    40
            );

            btnExcluir.addActionListener(
                    e -> excluir()
            );

            painel.add(btnExcluir);
        }

        BotaoPadrao btnFechar =
                new BotaoPadrao(
                        "Fechar"
                );

        btnFechar.setBounds(
                145,
                440,
                150,
                40
        );

        btnFechar.addActionListener(
                e -> dispose()
        );

        painel.add(btnFechar);
    }

    private void adicionarCampo(
            JPanel painel,
            String texto,
            int y
    ) {

        JLabel label =
                new JLabel(texto);

        label.setBounds(
                50,
                y,
                200,
                20
        );

        label.setForeground(TEXTO);

        painel.add(label);
    }

    private void salvar() {

        try {

            doador.setNome(
                    campoNome.getText()
            );

            doador.setEmail(
                    campoEmail.getText()
            );

            doador.setTelefone(
                    campoTelefone.getText()
            );

            Doador.atualizarDoador(
                    doador
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Doador atualizado com sucesso!"
            );

        }
        catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void excluir() {

        int opcao =
                JOptionPane.showConfirmDialog(

                        this,

                        "Deseja realmente excluir este doador?",

                        "Confirmação",

                        JOptionPane.YES_NO_OPTION
                );

        if(opcao != JOptionPane.YES_OPTION) {

            return;
        }

        try {

            Doador.excluirDoador(
                    doador.getId()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Doador excluído."
            );

            dispose();

        }
        catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }
}