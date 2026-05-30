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

        modeloTabela.addColumn("Dinheiro");

        modeloTabela.addColumn("Agasalho");

        modeloTabela.addColumn("Alimento");

        tabela = new JTable(modeloTabela);

        JScrollPane scroll =
                new JScrollPane(tabela);

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

                    campanha.isAceitaDinheiro()
                            ? "Sim"
                            : "Não",

                    campanha.isAceitaAgasalho()
                            ? "Sim"
                            : "Não",

                    campanha.isAceitaAlimento()
                            ? "Sim"
                            : "Não"
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