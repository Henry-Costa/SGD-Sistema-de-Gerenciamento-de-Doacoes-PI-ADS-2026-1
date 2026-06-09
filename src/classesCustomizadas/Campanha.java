package classesCustomizadas;
import java.util.ArrayList;
import java.util.List;
import services.BD;
import java.sql.SQLException;

/**
 * Representa uma campanha de doações.
 * Uma campanha pode aceitar diferentes tipos de doações:
 * dinheiro, agasalhos e alimentos.
 */
public class Campanha {
	/** ID da campanha no banco de dados */
    private int id;

    /**
     * O nome da campanha
     */
    private String nome;
    /**
     * Se a campanha aceita doações com valor monetário
     */
    private boolean aceitaDinheiro;
    /**
     * Se a campanha aceita doações de agasalho
     */
    private boolean aceitaAgasalho;
    /**
     * Se a campanha aceita doações de kgs alimento
     */
    private boolean aceitaAlimento;

    /**
     * Doações listadas na campanha
     */
    private List<Doacao> doacoes = new ArrayList<>();
    

    /**
     * Construtor da campanha.
     *
     * @param nome Nome da campanha
     * @param aceitaDinheiro Se aceita doações em dinheiro
     * @param aceitaAgasalho Se aceita agasalhos
     * @param aceitaAlimento Se aceita alimentos
     */
    public Campanha(String nome, boolean aceitaDinheiro, boolean aceitaAgasalho, boolean aceitaAlimento) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da campanha não pode ser vazio.");
        }

        if (!aceitaDinheiro && !aceitaAgasalho && !aceitaAlimento) {
            throw new IllegalArgumentException("Campanha deve aceitar pelo menos um tipo de doação.");
        }

        this.nome = nome;
        this.aceitaDinheiro = aceitaDinheiro;
        this.aceitaAgasalho = aceitaAgasalho;
        this.aceitaAlimento = aceitaAlimento;
    }
    
    /**
     * Gets the id.
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Define o id do doador.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Define o nome da campanha.
     *
     * @param nome Nome da campanha
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o nome da campanha.
     *
     * @return nome da campanha
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define se a campanha aceita doações em dinheiro.
     *
     * @param aceitaDinheiro true se aceita, false caso contrário
     */
    public void setAceitaDinheiro(boolean aceitaDinheiro) {
        this.aceitaDinheiro = aceitaDinheiro;
    }

    /**
     * Verifica se a campanha aceita doações em dinheiro.
     *
     * @return true se aceita dinheiro
     */
    public boolean isAceitaDinheiro() {
        return aceitaDinheiro;
    }

    /**
     * Define se a campanha aceita doações de agasalhos.
     *
     * @param aceitaAgasalho true se aceita
     */
    public void setAceitaAgasalho(boolean aceitaAgasalho) {
        this.aceitaAgasalho = aceitaAgasalho;
    }

    /**
     * Verifica se a campanha aceita agasalhos.
     *
     * @return true se aceita agasalhos
     */
    public boolean isAceitaAgasalho() {
        return aceitaAgasalho;
    }

    /**
     * Define se a campanha aceita doações de alimentos.
     *
     * @param aceitaAlimento true se aceita
     */
    public void setAceitaAlimento(boolean aceitaAlimento) {
        this.aceitaAlimento = aceitaAlimento;
    }

    /**
     * Verifica se a campanha aceita alimentos.
     *
     * @return true se aceita alimentos
     */
    public boolean isAceitaAlimento() {
        return aceitaAlimento;
    }
    
    /**
     * Busca a quantidade de doações relacionadas a campanha atual
     * @return quantidade de doações da campanha
     */
    public int getQuantidadeDoacoes() {

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                "SELECT COUNT(*) " +
                "FROM sgd.doacao " +
                "WHERE id_campanha = ?";

            bd.st =
                bd.con.prepareStatement(sql);

            bd.st.setInt(1, id);

            bd.rs = bd.st.executeQuery();

            if(bd.rs.next()) {

                return bd.rs.getInt(1);
            }

        } catch(SQLException e) {

            e.printStackTrace();

        } finally {

            bd.close();
        }

        return 0;
    }

    
    
    /**
     * Adiciona uma doação à campanha se o tipo da doação for válido.
     *
     * @param doacao objeto Doacao a ser adicionado
     */
    public void adicionarDoacao(Doacao doacao) {
        if (doacao == null) {
            throw new IllegalArgumentException("Doação não pode ser nula.");
        }

        boolean temTipoValido = false;

        if (doacao.getValorMonetario() > 0) {
            if (!aceitaDinheiro) {
                throw new IllegalArgumentException("Campanha não aceita doação em dinheiro.");
            }
            temTipoValido = true;
        }

        if (doacao.getUnidadeAgasalho() > 0) {
            if (!aceitaAgasalho) {
                throw new IllegalArgumentException("Campanha não aceita doação de agasalhos.");
            }
            temTipoValido = true;
        }

        if (doacao.getKgAlimento() > 0) {
            if (!aceitaAlimento) {
                throw new IllegalArgumentException("Campanha não aceita doação de alimentos.");
            }
            temTipoValido = true;
        }

        if (!temTipoValido) {
            throw new IllegalArgumentException("Doação não possui nenhum tipo válido.");
        }

        doacoes.add(doacao);
    }

    /**
     * Calcula o total de dinheiro arrecadado.
     *
     * @return soma total em dinheiro
     */
    public double getTotalDinheiro() {

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                "SELECT COALESCE(SUM(valor_monetario),0) " +
                "FROM sgd.doacao " +
                "WHERE id_campanha = ?";

            bd.st =
                bd.con.prepareStatement(sql);

            bd.st.setInt(1, id);

            bd.rs = bd.st.executeQuery();

            if(bd.rs.next()) {

                return bd.rs.getDouble(1);
            }

        } catch(SQLException e) {

            e.printStackTrace();

        } finally {

            bd.close();
        }

        return 0;
    }

    /**
     * Calcula o total de agasalhos arrecadados.
     *
     * @return quantidade total de agasalhos
     */
    public int getTotalAgasalhos() {
    	BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                "SELECT COALESCE(SUM(unidade_agasalho),0) " +
                "FROM sgd.doacao " +
                "WHERE id_campanha = ?";

            bd.st =
                bd.con.prepareStatement(sql);

            bd.st.setInt(1, id);

            bd.rs = bd.st.executeQuery();

            if(bd.rs.next()) {

                return bd.rs.getInt(1);
            }

        } catch(SQLException e) {

            e.printStackTrace();

        } finally {

            bd.close();
        }

        return 0;
    }

    /**
     * Calcula o total de alimentos (em kg).
     *
     * @return total em quilogramas
     */
    public double getTotalAlimentosKg() {
    	BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                "SELECT COALESCE(SUM(kg_alimento),0) " +
                "FROM sgd.doacao " +
                "WHERE id_campanha = ?";

            bd.st =
                bd.con.prepareStatement(sql);

            bd.st.setInt(1, id);

            bd.rs = bd.st.executeQuery();

            if(bd.rs.next()) {

                return bd.rs.getDouble(1);
            }

        } catch(SQLException e) {

            e.printStackTrace();

        } finally {

            bd.close();
        }

        return 0;
    }

    /**
     * Busca uma campanha pelo nome exato.
     *
     * @param nome Nome da campanha.
     * @return Campanha encontrada ou null.
     */
    public static Campanha buscarPorNomeExato(
            String nome
    ) {

        for(Campanha campanha
                : listarCampanhas()) {

            if(campanha.getNome()
                    .equals(nome)) {

                return campanha;
            }
        }

        return null;
    }
    
    /**
     * Cadastra uma campanha no banco de dados.
     *
     * @param campanha Campanha cadastrada.
     */
    public static void cadastrarCampanha(
            Campanha campanha
    ) {

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                    "INSERT INTO sgd.campanha " +
                    "(nome_campanha, aceita_dinheiro, aceita_agasalho, aceita_alimento) " +
                    "VALUES (?, ?, ?, ?)";

            bd.st = bd.con.prepareStatement(sql);

            bd.st.setString(
                    1,
                    campanha.getNome()
            );

            bd.st.setBoolean(
                    2,
                    campanha.isAceitaDinheiro()
            );

            bd.st.setBoolean(
                    3,
                    campanha.isAceitaAgasalho()
            );

            bd.st.setBoolean(
                    4,
                    campanha.isAceitaAlimento()
            );

            bd.st.executeUpdate();

        } catch(SQLException e) {

            throw new IllegalArgumentException(
                    "Erro ao cadastrar campanha: "
                            + e.getMessage()
            );

        } finally {

            bd.close();
        }
    }
    
    /**
     * Retorna todas as campanhas cadastradas.
     *
     * @return Lista de campanhas.
     */
    public static ArrayList<Campanha>
    listarCampanhas() {

        ArrayList<Campanha> lista =
                new ArrayList<>();

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                    "SELECT * " +
                    "FROM sgd.campanha " +
                    "WHERE ativa = true " +
                    "ORDER BY nome_campanha";

            bd.st = bd.con.prepareStatement(sql);

            bd.rs = bd.st.executeQuery();

            while(bd.rs.next()) {

                Campanha campanha =
                        new Campanha(

                                bd.rs.getString(
                                        "nome_campanha"
                                ),

                                bd.rs.getBoolean(
                                        "aceita_dinheiro"
                                ),

                                bd.rs.getBoolean(
                                        "aceita_agasalho"
                                ),

                                bd.rs.getBoolean(
                                        "aceita_alimento"
                                )
                        );
                
		                campanha.setId(
		                	    bd.rs.getInt("id_campanha")
		                	);

                lista.add(campanha);
            }

        } catch(SQLException e) {

            e.printStackTrace();

        } finally {

            bd.close();
        }

        return lista;
    }
    
    /**
     * Pesquisa campanhas pelo nome.
     *
     * @param nome Nome pesquisado.
     *
     * @return Lista encontrada.
     */
    public static ArrayList<Campanha>
    pesquisarPorNome(String nome) {

        ArrayList<Campanha> lista =
                new ArrayList<>();

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                    "SELECT * " +
                    "FROM sgd.campanha " +
                    "WHERE LOWER(nome_campanha) LIKE LOWER(?) " +
                    "AND ativa = true";

            bd.st = bd.con.prepareStatement(sql);

            bd.st.setString(
                    1,
                    "%" + nome + "%"
            );

            bd.rs = bd.st.executeQuery();

            while(bd.rs.next()) {

                lista.add(

                        new Campanha(

                                bd.rs.getString(
                                        "nome_campanha"
                                ),

                                bd.rs.getBoolean(
                                        "aceita_dinheiro"
                                ),

                                bd.rs.getBoolean(
                                        "aceita_agasalho"
                                ),

                                bd.rs.getBoolean(
                                        "aceita_alimento"
                                )
                        )
                );
            }

        } catch(SQLException e) {

            e.printStackTrace();

        } finally {

            bd.close();
        }

        return lista;
    }
    
    /**
     * Retorna um resumo das informações da campanha.
     *
     * @return string com os dados da campanha
     */
    /**
     * Exibe as informações da campanha no terminal.
     */
    public void mostrarInfo() {
        System.out.println("Nome: " + nome);

        System.out.println("\nTipos de doação aceitos:");
        System.out.println("Dinheiro: " + (aceitaDinheiro ? "Sim" : "Não"));
        System.out.println("Agasalho: " + (aceitaAgasalho ? "Sim" : "Não"));
        System.out.println("Alimento: " + (aceitaAlimento ? "Sim" : "Não"));

        System.out.println("\nTotais arrecadados:");
        System.out.println("Total dinheiro: R$ " + getTotalDinheiro());
        System.out.println("Total agasalhos: " + getTotalAgasalhos());
        System.out.println("Total alimentos: " + getTotalAlimentosKg() + " kg");
    }
    
    @Override
    public String toString() {

        return nome;
    }
}