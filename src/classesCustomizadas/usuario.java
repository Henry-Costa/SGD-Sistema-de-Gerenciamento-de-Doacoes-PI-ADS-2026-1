package classesCustomizadas;

import services.BD;
import java.sql.SQLException;

/**
 * Classe responsável por representar um usuário do sistema.
 * A classe também realiza cadastro de usuários, autenticação de login e criptografia simples de dados.
 * Os dados de username e senha são armazenados de forma criptografada utilizando deslocamento de caracteres.
 */
public class Usuario {

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
     * Realiza o cadastro de um usuário no sistema no banco de dados
     * 
     * @param usuario Usuário que será cadastrado.
     */
    public static void cadastrarUsuario(Usuario usuario) {

        BD bd = new BD();

        try {

            if (bd.getConnection()) {

                String sql =
                        """
                        INSERT INTO sgd.usuario
                        (
                            username,
                            senha,
                            administrador
                        )
                        VALUES
                        (
                            ?, ?, ?
                        )
                        """;

                bd.st =
                        bd.con.prepareStatement(sql);

                bd.st.setString(
                        1,
                        usuario.username
                );

                bd.st.setString(
                        2,
                        usuario.senha
                );

                bd.st.setBoolean(
                        3,
                        usuario.administrador
                );

                bd.st.executeUpdate();
            }

        } catch(SQLException e) {

            throw new RuntimeException(
                    "Erro ao cadastrar usuário: "
                            + e.getMessage()
            );

        } finally {

            bd.close();
        }
    }

    /**
     * Realiza a autenticação de um usuário.
     * 
     * Os dados informados são descriptografados antes da comparação.
     * 
     * @param username Nome de usuário informado.
     * @param senha Senha informada.
     * 
     * @return Usuário autenticado ou null em caso de falha.
     */
    public static Usuario autenticar(
            String username,
            String senha
    ) {

        BD bd = new BD();

        try {

            if (bd.getConnection()) {

                String sql =
                        """
                        SELECT *
                        FROM sgd.usuario
                        WHERE username = ?
                        AND senha = ?
                        """;

                bd.st =
                        bd.con.prepareStatement(sql);

                bd.st.setString(
                        1,
                        criptografar(username)
                );

                bd.st.setString(
                        2,
                        criptografar(senha)
                );

                bd.rs =
                        bd.st.executeQuery();

                if(bd.rs.next()) {

                    return new Usuario(

                            descriptografar(
                                    bd.rs.getString(
                                            "username"
                                    )
                            ),

                            descriptografar(
                                    bd.rs.getString(
                                            "senha"
                                    )
                            ),

                            bd.rs.getBoolean(
                                    "administrador"
                            )
                    );
                }
            }

        } catch(SQLException e) {

            e.printStackTrace();

        } finally {

            bd.close();
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