package telas;

import componentes.BotaoPadrao;

import classesCustomizadas.Relatorio;
import classesCustomizadas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Tela de visualização de relatórios baseados no banco de dados
 */
public class TelaRelatorios extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioLogado;

    private JComboBox<String> comboRelatorios;

    private JTable tabela;

    public TelaRelatorios(
            Usuario usuarioLogado
    ) {

        super("SGD - Relatórios");

        this.usuarioLogado =
                usuarioLogado;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        /*
         * TÍTULO
         */

        JLabel titulo =
                new JLabel(
                        "Relatórios"
                );

        titulo.setBounds(
                30,
                20,
                300,
                40
        );

        titulo.setForeground(
                TEXTO
        );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        add(titulo);

        /*
         * COMBOBOX
         */

        comboRelatorios =
                new JComboBox<>();

        comboRelatorios.addItem(
                "Doações"
        );

        comboRelatorios.addItem(
                "Total por Campanha"
        );

        comboRelatorios.addItem(
                "Total por Doador"
        );

        comboRelatorios.addItem(
                "Total por Beneficiário"
        );

        comboRelatorios.addItem(
                "Campanhas Ativas"
        );

        comboRelatorios.addItem(
                "Campanhas sem Doações"
        );

        comboRelatorios.addItem(
                "Beneficiários Atendidos"
        );

        comboRelatorios.setBounds(
                30,
                80,
                300,
                35
        );

        add(comboRelatorios);

        /*
         * BOTÃO GERAR
         */

        BotaoPadrao btnGerar =
                new BotaoPadrao(
                        "Gerar"
                );

        btnGerar.setBounds(
                350,
                80,
                150,
                35
        );

        btnGerar.addActionListener(
                e -> gerarRelatorio()
        );

        add(btnGerar);

        /*
         * TABELA
         */

        tabela =
                new JTable(
                        new DefaultTableModel()
                );

        JScrollPane scroll =
                new JScrollPane(
                        tabela
                );

        scroll.setBounds(
                30,
                140,
                820,
                320
        );

        add(scroll);

        /*
         * BOTÃO VOLTAR
         */

        BotaoPadrao btnVoltar =
                new BotaoPadrao(
                        "Voltar"
                );

        btnVoltar.setBounds(
                30,
                490,
                180,
                40
        );

        btnVoltar.addActionListener(
                e -> {

                    dispose();

                    new TelaMenu(
                            usuarioLogado
                    ).setVisible(true);

                }
        );

        add(btnVoltar);
    }

    private void gerarRelatorio() {

        String opcao =
                comboRelatorios
                        .getSelectedItem()
                        .toString();

        switch(opcao) {

            case "Doações":

                tabela.setModel(
                        Relatorio.listarDoacoes()
                );

                break;

            case "Total por Campanha":

                tabela.setModel(
                        Relatorio.totalPorCampanha()
                );

                break;

            case "Total por Doador":

                tabela.setModel(
                        Relatorio.totalPorDoador()
                );

                break;

            case "Total por Beneficiário":

                tabela.setModel(
                        Relatorio.totalPorBeneficiario()
                );

                break;

            case "Campanhas Ativas":

                tabela.setModel(
                        Relatorio.campanhasAtivas()
                );

                break;

            case "Campanhas sem Doações":

                tabela.setModel(
                        Relatorio.campanhasSemDoacoes()
                );

                break;

            case "Beneficiários Atendidos":

                tabela.setModel(
                        Relatorio.beneficiariosAtendidos()
                );

                break;
        }
    }
}