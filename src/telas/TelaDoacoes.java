package telas;

import componentes.BotaoPadrao;

import classesCustomizadas.Doacao;
import classesCustomizadas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

/**
 * Tela responsável pelo gerenciamento
 * de doações.
 */
public class TelaDoacoes extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private JTable tabela;

    private DefaultTableModel modeloTabela;

    public TelaDoacoes(Usuario usuario) {

        super("SGD - Doações");

        this.usuario = usuario;

        inicializarComponentes();

        carregarTabela();
    }

    @Override
    protected void inicializarComponentes() {

        JLabel titulo =
                new JLabel("Doações");

        titulo.setBounds(30, 20, 300, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font("Arial",
                        Font.BOLD,
                        30)
        );

        add(titulo);

        /*
         * TABELA
         */

        modeloTabela =
                new DefaultTableModel();

        modeloTabela.addColumn("Doador");

        modeloTabela.addColumn("Beneficiário");

        modeloTabela.addColumn("Campanha");

        modeloTabela.addColumn("Tipo");

        modeloTabela.addColumn("Valor");

        tabela = new JTable(modeloTabela);

        JScrollPane scroll =
                new JScrollPane(tabela);

        scroll.setBounds(
                30,
                100,
                820,
                340
        );

        add(scroll);

        /*
         * BOTÃO CADASTRAR
         */

        BotaoPadrao btnCadastrar =
                new BotaoPadrao(
                        "Cadastrar Doação"
                );

        btnCadastrar.setBounds(
                30,
                470,
                240,
                40
        );

        btnCadastrar.addActionListener(e -> {

            dispose();

            new TelaCadastroDoacao(usuario)
                    .setVisible(true);

        });

        add(btnCadastrar);

        /*
         * BOTÃO VOLTAR
         */

        BotaoPadrao btnVoltar =
                new BotaoPadrao("Voltar");

        btnVoltar.setBounds(
                290,
                470,
                180,
                40
        );

        btnVoltar.addActionListener(e -> {

            dispose();

            new TelaMenu(usuario)
                    .setVisible(true);

        });

        add(btnVoltar);
    }

    /**
     * Carrega tabela de doações.
     */
    private void carregarTabela() {

        modeloTabela.setRowCount(0);

        for(Doacao doacao
                : Doacao.listarDoacoes()) {

            String valor = "";

            switch(doacao.getTipo()) {

                case "DINHEIRO":

                    valor = "R$ "
                            + doacao.getValorMonetario();

                    break;

                case "ALIMENTO":

                    valor = doacao.getKgAlimento()
                            + " Kg";

                    break;

                case "AGASALHO":

                    valor = doacao.getUnidadeAgasalho()
                            + " unidades";

                    break;
            }

            modeloTabela.addRow(new Object[] {

                    doacao.getDoador()
                            .getNome(),

                    doacao.getBeneficiario()
                            .getNome(),

                    doacao.getCampanha()
                            .getNome(),

                    doacao.getTipo(),

                    valor
            });
        }
    }
}