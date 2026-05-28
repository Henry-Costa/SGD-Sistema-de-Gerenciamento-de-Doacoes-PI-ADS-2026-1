package classesCustomizadas;

import java.util.ArrayList;

/**
 * Classe responsável por representar um usuário do sistema.
 * A classe também realiza cadastro de usuários, autenticação de login e criptografia simples de dados.
 * Os dados de username e senha são armazenados de forma criptografada utilizando deslocamento de caracteres.
 */
public class Usuario {

    /**
     * Lista responsável por armazenar os usuários cadastrados
     * temporariamente em memória.
     */
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    /**
     * Nome de usuário criptografado.
     */
    private String username;

    /**
     * Senha criptografada.
     */
    private String senha;

    /**
     * Define se o usuário possui permissões administrativas.
     */
    private boolean administrador;

    /**
     * Construtor da classe Usuario.
     * 
     * Os dados recebidos são criptografados antes do armazenamento.
     * 
     * @param username Nome do usuário.
     * @param senha Senha do usuário.
     * @param administrador Define se o usuário é administrador.
     */
    public Usuario(String username,
                   String senha,
                   boolean administrador) {

        this.username = criptografar(username);

        this.senha = criptografar(senha);

        this.administrador = administrador;
    }

    /**
     * Verifica se o usuário possui permissões administrativas.
     * 
     * @return true caso seja administrador.
     */
    public boolean isAdministrador() {

        return administrador;
    }

    /**
     * Retorna o nome do usuário descriptografado.
     * 
     * @return Username descriptografado.
     */
    public String getUsername() {

        return descriptografar(username);
    }

    /**
     * Realiza o cadastro de um usuário no sistema.
     * 
     * O usuário é armazenado temporariamente em memória.
     * 
     * @param usuario Usuário que será cadastrado.
     */
    public static void cadastrarUsuario(Usuario usuario) {

        usuarios.add(usuario);
    }

    /**
     * Realiza a autenticação de um usuário.
     * 
     * Os dados informados são criptografados antes da comparação.
     * 
     * @param username Nome de usuário informado.
     * @param senha Senha informada.
     * 
     * @return Usuário autenticado ou null em caso de falha.
     */
    public static Usuario autenticar(String username,
                                     String senha) {

        String usernameCripto = criptografar(username);

        String senhaCripto = criptografar(senha);

        for (Usuario usuario : usuarios) {

            if (usuario.username.equals(usernameCripto)
                    && usuario.senha.equals(senhaCripto)) {

                return usuario;
            }
        }

        return null;
    }

    /**
     * Realiza uma criptografia simples utilizando deslocamento
     * de caracteres.
     * 
     * Cada caractere é deslocado 5 posições na tabela Unicode.
     * 
     * @param texto Texto original.
     * 
     * @return Texto criptografado.
     */
    private static String criptografar(String texto) {

        StringBuilder resultado = new StringBuilder();

        for (char caractere : texto.toCharArray()) {

            resultado.append((char)(caractere + 5));
        }

        return resultado.toString();
    }

    /**
     * Realiza a descriptografia de um texto previamente criptografado.
     * 
     * @param texto Texto criptografado.
     * 
     * @return Texto descriptografado.
     */
    private static String descriptografar(String texto) {

        StringBuilder resultado = new StringBuilder();

        for (char caractere : texto.toCharArray()) {

            resultado.append((char)(caractere - 5));
        }

        return resultado.toString();
    }
}