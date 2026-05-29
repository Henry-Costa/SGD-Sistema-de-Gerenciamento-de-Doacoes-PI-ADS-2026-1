package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Campanha;
import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Tela responsável pelo cadastro
 * de campanhas.
 */
public class TelaCadastroCampanha
        extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private CampoTextoPadrao campoNome;

    private JCheckBox checkDinheiro;

    private JCheckBox checkAgasalho;

    private JCheckBox checkAlimento;

    public TelaCadastroCampanha(
            Usuario usuario
    ) {

        super("SGD - Cadastro Campanha");

        this.usuario = usuario;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBackground(PAINEL);

        painel.setBounds(220, 60, 450, 430);

        add(painel);

        JLabel titulo = new JLabel(
                "Cadastrar Campanha"
        );

        titulo.setBounds(70, 20, 320, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28)
        );

        painel.add(titulo);

        /*
         * NOME
         */

        JLabel labelNome =
                new JLabel("Nome");

        labelNome.setBounds(50, 100, 200, 20);

        labelNome.setForeground(TEXTO);

        painel.add(labelNome);

        campoNome =
                new CampoTextoPadrao();

        campoNome.setBounds(
                50,
                125,
                340,
                35
        );

        painel.add(campoNome);

        /*
         * CHECKBOXES
         */

        checkDinheiro =
                new JCheckBox("Aceita Dinheiro");

        checkDinheiro.setBounds(
                50,
                190,
                200,
                30
        );

        estilizarCheck(checkDinheiro);

        painel.add(checkDinheiro);

        checkAgasalho =
                new JCheckBox("Aceita Agasalho");

        checkAgasalho.setBounds(
                50,
                230,
                200,
                30
        );

        estilizarCheck(checkAgasalho);

        painel.add(checkAgasalho);

        checkAlimento =
                new JCheckBox("Aceita Alimento");

        checkAlimento.setBounds(
                50,
                270,
                200,
                30
        );

        estilizarCheck(checkAlimento);

        painel.add(checkAlimento);

        /*
         * BOTÃO CADASTRAR
         */

        BotaoPadrao btnCadastrar =
                new BotaoPadrao("Cadastrar");

        btnCadastrar.setBounds(
                120,
                330,
                180,
                40
        );

        btnCadastrar.addActionListener(
                e -> cadastrar()
        );

        painel.add(btnCadastrar);
        
        /*
         * BOTÃO VOLTAR
         */

        BotaoPadrao btnVoltar =
                new BotaoPadrao("Voltar");

        btnVoltar.setBounds(
                120,
                380,
                180,
                40
        );

        btnVoltar.addActionListener(e -> {

            dispose();

            new TelaCampanhas(usuario)
                    .setVisible(true);

        });

        painel.add(btnVoltar);
    }

    /**
     * Estiliza checkbox.
     *
     * @param check checkbox estilizado.
     */
    private void estilizarCheck(
            JCheckBox check
    ) {

        check.setBackground(PAINEL);

        check.setForeground(TEXTO);

        check.setFocusPainted(false);

        check.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        16)
        );
    }

    /**
     * Realiza cadastro da campanha.
     */
    private void cadastrar() {

        try {

            Campanha campanha =
                    new Campanha(

                            campoNome.getText(),

                            checkDinheiro.isSelected(),

                            checkAgasalho.isSelected(),

                            checkAlimento.isSelected()
                    );

            Campanha.cadastrarCampanha(
                    campanha
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Campanha cadastrada!"
            );

            dispose();

            new TelaCampanhas(usuario)
                    .setVisible(true);

        }
        catch(Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }
}