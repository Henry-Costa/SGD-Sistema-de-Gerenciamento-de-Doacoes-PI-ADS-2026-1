package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.*;

import javax.swing.*;
import java.awt.*;

/**
 * Tela responsável pelo cadastro
 * de doações.
 */
public class TelaCadastroDoacao
        extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private JComboBox<Doador> comboDoador;

    private JComboBox<Beneficiario> comboBeneficiario;

    private JComboBox<Campanha> comboCampanha;

    private JComboBox<String> comboTipo;

    private CampoTextoPadrao campoValor;

    private JLabel labelValor;

    public TelaCadastroDoacao(
            Usuario usuario
    ) {

        super("SGD - Cadastro Doação");

        this.usuario = usuario;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBackground(PAINEL);

        painel.setBounds(220, 20, 450, 560);

        add(painel);

        JLabel titulo = new JLabel(
                "Cadastrar Doação"
        );

        titulo.setBounds(80, 20, 320, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28)
        );

        painel.add(titulo);

        /*
         * DOADOR
         */

        comboDoador =
                new JComboBox<>();

        for(Doador d
                : Doador.listarDoadores()) {

            comboDoador.addItem(d);
        }

        adicionarCampo(
                painel,
                "Doador",
                comboDoador,
                90
        );

        /*
         * BENEFICIÁRIO
         */

        comboBeneficiario =
                new JComboBox<>();

        for(Beneficiario b
                : Beneficiario
                .listarBeneficiarios()) {

            comboBeneficiario.addItem(b);
        }

        adicionarCampo(
                painel,
                "Beneficiário",
                comboBeneficiario,
                160
        );

        /*
         * CAMPANHA
         */

        comboCampanha =
                new JComboBox<>();

        for(Campanha c
                : Campanha.listarCampanhas()) {

            comboCampanha.addItem(c);
        }

        adicionarCampo(
                painel,
                "Campanha",
                comboCampanha,
                230
        );

        /*
         * TIPO
         */

        comboTipo =
                new JComboBox<>();

        comboTipo.addItem("DINHEIRO");

        comboTipo.addItem("ALIMENTO");

        comboTipo.addItem("AGASALHO");
        
        comboTipo.addActionListener(e -> {

            String tipo =
                    (String)
                            comboTipo
                                    .getSelectedItem();

            switch(tipo) {

                case "DINHEIRO":

                    labelValor.setText("R$");

                    break;

                case "ALIMENTO":

                    labelValor.setText("Kgs");

                    break;

                case "AGASALHO":

                    labelValor.setText("Quantidade");

                    break;
            }
        });

        adicionarCampo(
                painel,
                "Tipo",
                comboTipo,
                300
        );

        /*
         * QUANTIDADE
         */

        labelValor =
                new JLabel("R$");

        labelValor.setBounds(
                50,
                370,
                200,
                20
        );

        labelValor.setForeground(TEXTO);

        painel.add(labelValor);

        campoValor =
                new CampoTextoPadrao();

        campoValor.setBounds(
                50,
                395,
                340,
                35
        );

        painel.add(campoValor);

        /*
         * BOTÃO CADASTRAR
         */

        BotaoPadrao btnCadastrar =
                new BotaoPadrao("Cadastrar");

        btnCadastrar.setBounds(
                120,
                445,
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
                495,
                180,
                40
        );

        btnVoltar.addActionListener(e -> {

            dispose();

            new TelaDoacoes(usuario)
                    .setVisible(true);

        });

        painel.add(btnVoltar);
    }


    private void adicionarCampo(
            JPanel painel,
            String texto,
            JComboBox<?> combo,
            int y
    ) {

        JLabel label = new JLabel(texto);

        label.setBounds(50, y, 200, 20);

        label.setForeground(TEXTO);

        painel.add(label);

        combo.setBounds(50, y + 25, 340, 35);

        painel.add(combo);
    }

    /**
     * Realiza cadastro da doação.
     */
    private void cadastrar() {

        try {

            Doador doador =
                    (Doador)
                            comboDoador
                                    .getSelectedItem();

            Beneficiario beneficiario =
                    (Beneficiario)
                            comboBeneficiario
                                    .getSelectedItem();

            Campanha campanha =
                    (Campanha)
                            comboCampanha
                                    .getSelectedItem();

            String tipo =
                    (String)
                            comboTipo
                                    .getSelectedItem();

            Doacao doacao = null;

            switch(tipo) {

                case "DINHEIRO":

                    doacao = new Doacao(

                            doador,

                            beneficiario,

                            campanha,

                            Float.parseFloat(
                                    campoValor.getText()
                            )
                    );

                    break;

                case "ALIMENTO":

                    doacao = new Doacao(

                            doador,

                            beneficiario,

                            campanha,

                            Double.parseDouble(
                                    campoValor.getText()
                            )
                    );

                    break;

                case "AGASALHO":

                    doacao = new Doacao(

                            doador,

                            beneficiario,

                            campanha,

                            Integer.parseInt(
                                    campoValor.getText()
                            )
                    );

                    break;
            }

            Doacao.cadastrarDoacao(doacao);

            JOptionPane.showMessageDialog(
                    this,
                    "Doação cadastrada!"
            );

            dispose();

            new TelaDoacoes(usuario)
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