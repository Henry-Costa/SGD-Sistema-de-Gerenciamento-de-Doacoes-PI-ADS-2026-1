package telas;

import componentes.BotaoPadrao;
import componentes.CampoTextoPadrao;

import classesCustomizadas.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Tela de cadastro de um usuário no banco de dados
 */
public class TelaCadastroUsuario extends TelaBase {

    private CampoTextoPadrao campoUsuario;

    private JPasswordField campoSenha;

    private JCheckBox checkAdmin;
    
    private Usuario usuarioLogado;

    public TelaCadastroUsuario(Usuario usuarioLogado) {

        super("Cadastro de Usuário");
        this.usuarioLogado = usuarioLogado;
        
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBounds(250,100,400,380);

        painel.setBackground(PAINEL);

        JLabel titulo = new JLabel("Criar Usuário");

        titulo.setBounds(110, 20, 250, 40);

        titulo.setForeground(TEXTO);

        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblUsuario = new JLabel("Usuário");

        lblUsuario.setBounds(50, 90, 100, 20);

        lblUsuario.setForeground(TEXTO);

        campoUsuario = new CampoTextoPadrao();

        campoUsuario.setBounds(50, 115, 300, 35);

        JLabel lblSenha = new JLabel("Senha");

        lblSenha.setBounds(50, 160, 100, 20);

        lblSenha.setForeground(TEXTO);

        campoSenha = new JPasswordField();

        campoSenha.setBounds(50, 185, 300, 35);

        checkAdmin = new JCheckBox("Administrador");

        checkAdmin.setBounds(50, 230, 150, 30);

        checkAdmin.setBackground(PAINEL);

        checkAdmin.setForeground(TEXTO);

        BotaoPadrao btnCadastrar = new BotaoPadrao("Cadastrar");

        btnCadastrar.setBounds(120,270,150,35);

        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        
        BotaoPadrao btnVoltar = new BotaoPadrao("Voltar");

        btnVoltar.setBounds(120,315,150,35);

        btnVoltar.addActionListener(e -> {

            dispose();

            new TelaMenu(usuarioLogado)
                    .setVisible(true);
        });
        

        painel.add(titulo);

        painel.add(lblUsuario);

        painel.add(campoUsuario);

        painel.add(lblSenha);

        painel.add(campoSenha);

        painel.add(checkAdmin);

        painel.add(btnCadastrar);
        
        painel.add(btnVoltar);

        add(painel);
    }

    private void cadastrarUsuario() {

        String username = campoUsuario.getText();

        String senha = new String(campoSenha.getPassword());

        boolean admin = checkAdmin.isSelected();

        Usuario usuario = new Usuario(
                username,
                senha,
                admin
        );

        Usuario.cadastrarUsuario(usuario);

        JOptionPane.showMessageDialog(this,
                "Usuário cadastrado com sucesso!");

        dispose();

        new TelaMenu(usuarioLogado).setVisible(true);
    }
}