package classesCustomizadas;

import services.BD;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável por representar um usuário do sistema.
 * A classe também realiza cadastro de usuários, autenticação de login e criptografia simples de dados.
 * Os dados de username e senha são armazenados de forma criptografada utilizando deslocamento de caracteres.
 */
public class Usuario {

	/**
	 * ID do usuário no banco de dados
	 */
	private int id;
	
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
     * Construtor privado utilizado para recriar objetos vindos do banco.
     */
    private Usuario() {
    }

    /**
     * Cria um usuário a partir de dados já armazenados no banco.
     * Os valores NÃO são criptografados novamente.
     */
    public static Usuario fromDatabase(
            String username,
            String senha,
            boolean administrador
    ) {

        Usuario usuario = new Usuario();

        usuario.username = username;
        usuario.senha = senha;
        usuario.administrador = administrador;

        return usuario;
    }

    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public void setAdministrador(boolean administrador) {
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
     * Busca um usuário por username para a tela de detalhes do usuário
     * @param username Username a ser procurado
     * @return Usuário com o username especifícado
     */
    public static Usuario buscarPorUsername(
            String username
    ) {

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                "SELECT * " +
                "FROM sgd.usuario " +
                "WHERE username = ?";

            bd.st =
                bd.con.prepareStatement(sql);

            bd.st.setString(
                1,
                criptografar(username)
            );

            bd.rs =
                bd.st.executeQuery();

            if(bd.rs.next()) {

            	Usuario usuario =
            		    Usuario.fromDatabase(
            		        bd.rs.getString("username"),
            		        bd.rs.getString("senha"),
            		        bd.rs.getBoolean("administrador")
            		    );

                usuario.setId(
                    bd.rs.getInt(
                        "id_usuario"
                    )
                );

                return usuario;
            }

            return null;

        } catch(SQLException e) {

            throw new RuntimeException(
                e
            );

        } finally {

            bd.close();
        }
    }
    
    /**
     * Pesquisa um usuário por username para a tela de listar usuários
     * @param texto
     * @return
     */
    public static ArrayList<Usuario>
    pesquisarPorUsername(
            String texto
    ) {

        ArrayList<Usuario> usuarios =
                new ArrayList<>();

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                    "SELECT * " +
                    "FROM sgd.usuario " +
                    "WHERE LOWER(username) LIKE ? " +
                    "ORDER BY username";

            bd.st =
                    bd.con.prepareStatement(sql);

            bd.st.setString(
            	    1,
            	    "%" +
            	    criptografar(texto.toLowerCase()) +
            	    "%"
            	);

            bd.rs =
                    bd.st.executeQuery();

            while(bd.rs.next()) {

            	Usuario usuario =
            		    Usuario.fromDatabase(
            		        bd.rs.getString("username"),
            		        bd.rs.getString("senha"),
            		        bd.rs.getBoolean("administrador")
            		    );

                usuario.setId(

                        bd.rs.getInt(
                                "id_usuario"
                        )
                );

                usuarios.add(usuario);
            }

        } catch(SQLException e) {

            throw new RuntimeException(
                    e
            );

        } finally {

            bd.close();
        }

        return usuarios;
    }
    
    /**
     * Atualiza os dados de um usuário no banco de dados
     */
    public void atualizar() {

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                    "UPDATE sgd.usuario " +
                    "SET administrador = ? " +
                    "WHERE id_usuario = ?";

            bd.st =
                    bd.con.prepareStatement(sql);

            bd.st.setBoolean(
                    1,
                    administrador
            );

            bd.st.setInt(
                    2,
                    id
            );

            bd.st.executeUpdate();

        } catch(SQLException e) {

            throw new RuntimeException(
                    e
            );

        } finally {

            bd.close();
        }
    }
    
    /**
     * Exclui o acesso de um usuário ao sistema reitrando ele do banco de dados
     * @param id ID do usuário a ser excluído
     */
    public static void excluirUsuario(
            int id
    ) {

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                "DELETE FROM sgd.usuario " +
                "WHERE id_usuario = ?";

            bd.st =
                bd.con.prepareStatement(sql);

            bd.st.setInt(
                1,
                id
            );

            bd.st.executeUpdate();

        } catch(SQLException e) {

            throw new RuntimeException(
                e
            );

        } finally {

            bd.close();
        }
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
     * Lista os usuários presentes no banco de dados
     * @return Lista de usuários
     */
    public static ArrayList<Usuario>
    listarUsuarios() {

        ArrayList<Usuario> usuarios =
                new ArrayList<>();

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                    "SELECT * " +
                    "FROM sgd.usuario " +
                    "ORDER BY username";

            bd.st =
                    bd.con.prepareStatement(sql);

            bd.rs =
                    bd.st.executeQuery();

            while(bd.rs.next()) {

            	Usuario usuario =
            		    Usuario.fromDatabase(
            		        bd.rs.getString("username"),
            		        bd.rs.getString("senha"),
            		        bd.rs.getBoolean("administrador")
            		    );

                usuario.setId(

                        bd.rs.getInt(
                                "id_usuario"
                        )
                );

                usuarios.add(usuario);
            }

        } catch(SQLException e) {

            throw new RuntimeException(
                    e
            );

        } finally {

            bd.close();
        }

        return usuarios;
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

                	Usuario usuario =
                	        Usuario.fromDatabase(
                	                bd.rs.getString("username"),
                	                bd.rs.getString("senha"),
                	                bd.rs.getBoolean("administrador")
                	        );

                	usuario.setId(
                	        bd.rs.getInt("id_usuario")
                	);

                	return usuario;
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
    
    @Override
    public String toString() {

        return getUsername();
    }
}