package telas;

import componentes.BotaoPadrao;

import classesCustomizadas.Campanha;
import classesCustomizadas.Doacao;
import classesCustomizadas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class TelaDetalhesCampanha
        extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private Campanha campanha;

    private JTable tabela;

    private DefaultTableModel modelo;

    public TelaDetalhesCampanha(
            Usuario usuario,
            Campanha campanha
    ) {

        super(
                "Detalhes da Campanha"
        );

        this.usuario = usuario;

        this.campanha = campanha;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JLabel titulo =
                new JLabel(
                        campanha.getNome()
                );

        titulo.setBounds(
                30,
                20,
                500,
                40
        );

        titulo.setForeground(
                TEXTO
        );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        add(titulo);

        JLabel resumo =
                new JLabel(

                        "Doações: "
                        + campanha.getQuantidadeDoacoes()

                        + " | Dinheiro: R$ "

                        + String.format(
                                "%.2f",
                                campanha.getTotalDinheiro()
                        )

                        + " | Agasalhos: "

                        + campanha.getTotalAgasalhos()

                        + " | Alimentos: "

                        + campanha.getTotalAlimentosKg()

                        + " Kg"
                );

        resumo.setBounds(
                30,
                70,
                800,
                30
        );

        resumo.setForeground(
                TEXTO
        );

        add(resumo);

        modelo =
                new DefaultTableModel();

        modelo.addColumn(
                "Tipo"
        );

        modelo.addColumn(
                "Doador"
        );

        modelo.addColumn(
                "Beneficiário"
        );

        modelo.addColumn(
                "Valor"
        );

        tabela =
                new JTable(
                        modelo
                );

        carregarTabela();

        JScrollPane scroll =
                new JScrollPane(
                        tabela
                );

        scroll.setBounds(
                30,
                120,
                820,
                320
        );

        add(scroll);

        BotaoPadrao btnFechar =
                new BotaoPadrao(
                        "Fechar"
                );

        btnFechar.setBounds(
                330,
                470,
                180,
                40
        );

        btnFechar.addActionListener(
                e -> dispose()
        );

        add(btnFechar);
    }

    private void carregarTabela() {

        modelo.setRowCount(
                0
        );

        for(
                Doacao doacao
                : Doacao.listarPorCampanha(
                        campanha.getId()
                )
        ) {

            String valor = "";

            switch(
                    doacao.getTipo()
            ) {

                case "DINHEIRO":

                    valor =
                            "R$ "
                            + String.format(
                                    "%.2f",
                                    doacao.getValorMonetario()
                            );

                    break;

                case "ALIMENTO":

                    valor =
                            doacao.getKgAlimento()
                            + " Kg";

                    break;

                case "AGASALHO":

                    valor =
                            String.valueOf(
                                    doacao.getUnidadeAgasalho()
                            );

                    break;
            }

            modelo.addRow(
                    new Object[] {

                            doacao.getTipo(),

                            doacao.getDoador()
                                    .getNome(),

                            doacao.getBeneficiario()
                                    .getNome(),

                            valor
                    }
            );
        }
    }
}