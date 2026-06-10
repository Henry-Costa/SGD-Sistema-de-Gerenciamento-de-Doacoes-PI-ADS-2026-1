package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class TelaUsuarios extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioLogado;

    private JTable tabela;

    private DefaultTableModel modeloTabela;

    private CampoTextoPadrao campoPesquisa;

    public TelaUsuarios(
            Usuario usuarioLogado
    ) {

        super("SGD - Usuários");

        this.usuarioLogado =
                usuarioLogado;

        inicializarComponentes();

        carregarTabela(
                Usuario.listarUsuarios()
        );
    }

    @Override
    protected void inicializarComponentes() {

        JLabel titulo =
                new JLabel(
                        "Usuários"
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
         * PESQUISA
         */

        campoPesquisa =
                new CampoTextoPadrao();

        campoPesquisa.setBounds(
                30,
                80,
                300,
                35
        );

        add(campoPesquisa);

        BotaoPadrao btnPesquisar =
                new BotaoPadrao(
                        "Pesquisar"
                );

        btnPesquisar.setBounds(
                350,
                80,
                150,
                35
        );

        btnPesquisar.addActionListener(
                e -> pesquisar()
        );

        add(btnPesquisar);

        /*
         * TABELA
         */

        modeloTabela =
                new DefaultTableModel() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        modeloTabela.addColumn(
                "Usuário"
        );

        modeloTabela.addColumn(
                "Administrador"
        );

        tabela =
                new JTable(
                        modeloTabela
                );

        tabela.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        if(e.getClickCount() == 2) {

                            int linha =
                                    tabela.getSelectedRow();

                            if(linha >= 0) {

                                String username =
                                        tabela.getValueAt(
                                                linha,
                                                0
                                        ).toString();

                                Usuario usuario =
                                        Usuario.buscarPorUsername(
                                                username
                                        );

                                if(usuario != null) {

                                    new TelaDetalhesUsuario(

                                            usuarioLogado,

                                            usuario

                                    ).setVisible(true);
                                }
                            }
                        }
                    }
                }
        );

        JScrollPane scroll =
                new JScrollPane(
                        tabela
                );

        scroll.setBounds(
                30,
                140,
                820,
                300
        );

        add(scroll);

        /*
         * BOTÃO CADASTRAR
         */

        BotaoPadrao btnCadastrar =
                new BotaoPadrao(
                        "Cadastrar Usuário"
                );

        btnCadastrar.setBounds(
                30,
                470,
                220,
                40
        );

        btnCadastrar.addActionListener(
                e -> {

                    dispose();

                    new TelaCadastroUsuario(
                            usuarioLogado
                    ).setVisible(true);
                }
        );

        add(btnCadastrar);

        /*
         * BOTÃO VOLTAR
         */

        BotaoPadrao btnVoltar =
                new BotaoPadrao(
                        "Voltar"
                );

        btnVoltar.setBounds(
                270,
                470,
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

    private void carregarTabela(
            ArrayList<Usuario> usuarios
    ) {

        modeloTabela.setRowCount(
                0
        );

        for(
                Usuario usuario
                : usuarios
        ) {

            modeloTabela.addRow(
                    new Object[] {

                            usuario.getUsername(),

                            usuario.isAdministrador()
                                    ? "Sim"
                                    : "Não"
                    }
            );
        }
    }

    private void pesquisar() {

        carregarTabela(

                Usuario.pesquisarPorUsername(
                        campoPesquisa.getText()
                )
        );
    }
}