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

    private JCheckBox chkAtiva;

    private JCheckBox chkDinheiro;

    private JCheckBox chkAgasalho;

    private JCheckBox chkAlimento;
    
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
        
        if(usuario.isAdministrador()) {

            chkAtiva = new JCheckBox("Campanha Ativa");

            chkAtiva.setBounds(
                    30,
                    105,
                    150,
                    25
            );

            chkAtiva.setSelected(
                    campanha.isAtiva()
            );

            chkAtiva.setBackground(PAINEL);

            chkAtiva.setForeground(TEXTO);

            add(chkAtiva);


            chkDinheiro = new JCheckBox("Aceita Dinheiro");

            chkDinheiro.setBounds(
                    190,
                    105,
                    150,
                    25
            );

            chkDinheiro.setSelected(
                    campanha.isAceitaDinheiro()
            );

            chkDinheiro.setBackground(PAINEL);

            chkDinheiro.setForeground(TEXTO);

            add(chkDinheiro);


            chkAgasalho = new JCheckBox("Aceita Agasalho");

            chkAgasalho.setBounds(
                    360,
                    105,
                    150,
                    25
            );

            chkAgasalho.setSelected(
                    campanha.isAceitaAgasalho()
            );

            chkAgasalho.setBackground(PAINEL);

            chkAgasalho.setForeground(TEXTO);

            add(chkAgasalho);


            chkAlimento = new JCheckBox("Aceita Alimento");

            chkAlimento.setBounds(
                    530,
                    105,
                    150,
                    25
            );

            chkAlimento.setSelected(
                    campanha.isAceitaAlimento()
            );

            chkAlimento.setBackground(PAINEL);

            chkAlimento.setForeground(TEXTO);

            add(chkAlimento);
        }

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
                150,
                820,
                290
        );

        add(scroll);

        if(usuario.isAdministrador()) {

            BotaoPadrao btnSalvar =
                    new BotaoPadrao(
                            "Salvar Alterações"
                    );

            btnSalvar.setBounds(
                    120,
                    470,
                    180,
                    40
            );

            btnSalvar.addActionListener(
                    e -> salvarAlteracoes()
            );

            add(btnSalvar);
        }
        
        BotaoPadrao btnFechar =
                new BotaoPadrao(
                        "Fechar"
                );

        btnFechar.setBounds(
                520,
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
    
    private void salvarAlteracoes() {

        try {

            campanha.setAtiva(
                    chkAtiva.isSelected()
            );

            campanha.setAceitaDinheiro(
                    chkDinheiro.isSelected()
            );

            campanha.setAceitaAgasalho(
                    chkAgasalho.isSelected()
            );

            campanha.setAceitaAlimento(
                    chkAlimento.isSelected()
            );

            campanha.atualizarCampanha();

            JOptionPane.showMessageDialog(
                    this,
                    "Campanha atualizada com sucesso!"
            );
        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }
}