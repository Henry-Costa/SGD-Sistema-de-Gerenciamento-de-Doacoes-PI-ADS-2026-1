package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Campanha;
import classesCustomizadas.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Tela responsável pelo gerenciamento
 * de campanhas.
 */
public class TelaCampanhas extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private JTable tabela;

    private DefaultTableModel modeloTabela;

    private CampoTextoPadrao campoPesquisa;

    public TelaCampanhas(Usuario usuario) {

        super("SGD - Campanhas");

        this.usuario = usuario;

        inicializarComponentes();

        carregarTabela(
                Campanha.listarCampanhas()
        );
    }

    @Override
    protected void inicializarComponentes() {

        JLabel titulo =
                new JLabel("Campanhas");

        titulo.setBounds(30, 20, 300, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(
                new Font("Arial",
                        Font.BOLD,
                        30)
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
                new BotaoPadrao("Pesquisar");

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
                new DefaultTableModel();

        modeloTabela.addColumn("Nome");

        modeloTabela.addColumn("Doações");

        modeloTabela.addColumn("Aceita Dinheiro");

        modeloTabela.addColumn("Aceita Agasalho");

        modeloTabela.addColumn("Aceita Alimento");

        modeloTabela.addColumn("Total R$");

        modeloTabela.addColumn("Total Agasalhos");

        modeloTabela.addColumn("Total Alimentos (Kg)");

        tabela = new JTable(modeloTabela) {

            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int column) {

                switch(column) {

                    case 2:
                    case 3:
                    case 4:
                        return Boolean.class;

                    default:
                        return Object.class;
                }
            }

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };
        
        tabela.getColumnModel()
        .getColumn(0)
        .setPreferredWidth(220);

        tabela.getColumnModel()
        .getColumn(1)
        .setPreferredWidth(60);

  		tabela.getColumnModel()
        .getColumn(2)
        .setPreferredWidth(90);

  		tabela.getColumnModel()
        .getColumn(3)
        .setPreferredWidth(90);

  		tabela.getColumnModel()
        .getColumn(4)
        .setPreferredWidth(90);

  		tabela.getColumnModel()
        .getColumn(5)
        .setPreferredWidth(120);

  		tabela.getColumnModel()
        .getColumn(6)
        .setPreferredWidth(120);

  		tabela.getColumnModel()
        .getColumn(7)
        .setPreferredWidth(140);

        JScrollPane scroll =
                new JScrollPane(tabela);

        scroll.setBounds(
                30,
                140,
                820,
                300
        );

        add(scroll);
        
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

                                String nomeCampanha =
                                        tabela.getValueAt(
                                                linha,
                                                0
                                        ).toString();

                                Campanha campanha =
                                        Campanha
                                                .buscarPorNomeExato(
                                                        nomeCampanha
                                                );

                                new TelaDetalhesCampanha(
                                        usuario,
                                        campanha
                                ).setVisible(true);
                            }
                        }
                    }
                }
        );

        /*
         * BOTÃO CADASTRAR
         */

        if(usuario.isAdministrador()) {
        	BotaoPadrao btnCadastrar =
                new BotaoPadrao(
                        "Cadastrar Campanha"
	                );
	
	        btnCadastrar.setBounds(
	                30,
	                470,
	                240,
	                40
	        );
	
	        btnCadastrar.addActionListener(e -> {
	
	            dispose();
	
	            new TelaCadastroCampanha(usuario)
	                    .setVisible(true);
	
	        });
	
	        add(btnCadastrar);
        }

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
     * Carrega campanhas na tabela.
     *
     * @param campanhas Lista carregada.
     */
    private void carregarTabela(
            ArrayList<Campanha> campanhas
    ) {

        modeloTabela.setRowCount(0);

        for(Campanha campanha : campanhas) {

            modeloTabela.addRow(new Object[] {

                campanha.getNome(),

                campanha.getQuantidadeDoacoes(),

                campanha.isAceitaDinheiro(),

                campanha.isAceitaAgasalho(),

                campanha.isAceitaAlimento(),

                String.format(
                        "R$ %.2f",
                        campanha.getTotalDinheiro()
                ),

                campanha.getTotalAgasalhos(),

                campanha.getTotalAlimentosKg()
            });
        }
    }

    /**
     * Pesquisa campanhas.
     */
    private void pesquisar() {

        carregarTabela(

                Campanha.pesquisarPorNome(
                        campoPesquisa.getText()
                )
        );
    }
}