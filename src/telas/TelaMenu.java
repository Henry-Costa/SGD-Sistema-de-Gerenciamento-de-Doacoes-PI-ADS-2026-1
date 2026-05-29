package telas;

import componentes.BotaoPadrao;

import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Tela principal do sistema SGD.
 * 
 * A tela apresenta as funcionalidades disponíveis
 * para o usuário autenticado.
 * 
 * Usuários administradores possuem acesso
 * ao cadastro de novos usuários.
 */
public class TelaMenu extends TelaBase {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    public TelaMenu(Usuario usuario) {

        super("SGD - Menu Principal");

        this.usuario = usuario;

        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JLabel titulo = new JLabel("SGD");

        titulo.setBounds(30, 20, 300, 50);

        titulo.setForeground(TEXTO);

        titulo.setFont(new Font("Arial", Font.BOLD, 36));

        add(titulo);

        JLabel subtitulo = new JLabel(
                "Sistema Gerenciador de Doações"
        );

        subtitulo.setBounds(30, 70, 400, 30);

        subtitulo.setForeground(Color.LIGHT_GRAY);

        subtitulo.setFont(new Font("Arial", Font.PLAIN, 18));

        add(subtitulo);

        JLabel usuarioLogado = new JLabel(
                "Usuário: " + usuario.getUsername()
        );

        usuarioLogado.setBounds(30, 130, 300, 30);

        usuarioLogado.setForeground(TEXTO);

        usuarioLogado.setFont(new Font("Arial", Font.PLAIN, 18));

        add(usuarioLogado);

        String tipoUsuario = usuario.isAdministrador()
                ? "Administrador"
                : "Usuário comum";

        JLabel cargo = new JLabel(
                "Permissão: " + tipoUsuario
        );

        cargo.setBounds(30, 165, 300, 30);

        cargo.setForeground(TEXTO);

        cargo.setFont(new Font("Arial", Font.PLAIN, 18));

        add(cargo);

        
        
        /*
         * BOTÃO DOADORES
         */

        BotaoPadrao btnDoadores =
                new BotaoPadrao("Doadores");

        btnDoadores.setBounds(30,250,220,40);

        btnDoadores.addActionListener(e -> {

            dispose();

            new TelaDoadores(usuario).setVisible(true);

        });

        add(btnDoadores);
        
        
        /*
         * BOTÃO BENEFICIÁRIOS
         */


        BotaoPadrao btnBeneficiarios =
   	        new BotaoPadrao("Beneficiários");

        btnBeneficiarios.setBounds(30,310,220,40);

       btnBeneficiarios.addActionListener(e -> {

            dispose();

            new TelaBeneficiarios(usuario).setVisible(true);

        });

        add(btnBeneficiarios);
        
        
        /*
         * BOTÃO CAMPANHAS
         */

        if(usuario.isAdministrador()) {

        	BotaoPadrao btnCampanhas =
        	        new BotaoPadrao("Campanhas");

        	btnCampanhas.setBounds(30,370,220,40);

        	btnCampanhas.addActionListener(e -> {

                dispose();

                new TelaCampanhas(usuario)
                        .setVisible(true);

            });

            add(btnCampanhas);
        }

        /*
         * BOTÃO CADASTRAR USUÁRIO
         */

        if(usuario.isAdministrador()) {

            BotaoPadrao btnCadastrarUsuario =
                    new BotaoPadrao("Cadastrar Usuário");

            btnCadastrarUsuario.setBounds(30,430,220,40);

            btnCadastrarUsuario.addActionListener(e -> {

                dispose();

                new TelaCadastroUsuario(usuario)
                        .setVisible(true);

            });

            add(btnCadastrarUsuario);
        }

        /*
         * BOTÃO DESLOGAR
         */

        BotaoPadrao btnDeslogar =
                new BotaoPadrao("Deslogar");

        btnDeslogar.setBounds(30,490,220,40);

        btnDeslogar.addActionListener(e -> {

            dispose();

            new TelaLogin().setVisible(true);

        });

        add(btnDeslogar);
    }
}