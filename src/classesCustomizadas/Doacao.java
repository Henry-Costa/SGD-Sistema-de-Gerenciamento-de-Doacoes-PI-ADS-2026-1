package classesCustomizadas;

import java.util.ArrayList;
import java.sql.*;
import services.BD;

/**
 * Classe responsável por representar
 * uma doação no sistema.
 *
 * A doação conecta:
 * doador,
 * beneficiário
 * e campanha.
 */
public class Doacao {
	
	/** ID da doação no banco de dados */
    private int id;

    /**
     * Lista em memória de doações.
     */
    private static ArrayList<Doacao> doacoes =
            new ArrayList<>();

    /**
     * Doador responsável.
     */
    private Doador doador;

    /**
     * Beneficiário da doação.
     */
    private Beneficiario beneficiario;

    /**
     * Campanha relacionada.
     */
    private Campanha campanha;

    /**
     * Valor monetário.
     */
    private float valorMonetario;

    /**
     * Quilos de alimento.
     */
    private double kgAlimento;

    /**
     * Quantidade de agasalhos.
     */
    private int unidadeAgasalho;

    /**
     * Tipo da doação.
     */
    private String tipo;

    /**
     * Cria uma doação monetária.
     *
     * @param doador Doador.
     * @param beneficiario Beneficiário.
     * @param campanha Campanha.
     * @param valorMonetario Valor.
     */
    public Doacao(
            Doador doador,
            Beneficiario beneficiario,
            Campanha campanha,
            float valorMonetario
    ) {

        validarObjetos(
                doador,
                beneficiario,
                campanha
        );

        if(valorMonetario <= 0) {

            throw new IllegalArgumentException(
                    "Valor monetário inválido."
            );
        }

        if(!campanha.isAceitaDinheiro()) {

            throw new IllegalArgumentException(
                    "Campanha não aceita dinheiro."
            );
        }

        this.doador = doador;

        this.beneficiario = beneficiario;

        this.campanha = campanha;

        this.valorMonetario = valorMonetario;

        this.tipo = "DINHEIRO";
    }

    /**
     * Cria uma doação de alimentos.
     *
     * @param doador Doador.
     * @param beneficiario Beneficiário.
     * @param campanha Campanha.
     * @param kgAlimento Quilos.
     */
    public Doacao(
            Doador doador,
            Beneficiario beneficiario,
            Campanha campanha,
            double kgAlimento
    ) {

        validarObjetos(
                doador,
                beneficiario,
                campanha
        );

        if(kgAlimento <= 0) {

            throw new IllegalArgumentException(
                    "Quantidade inválida."
            );
        }

        if(!campanha.isAceitaAlimento()) {

            throw new IllegalArgumentException(
                    "Campanha não aceita alimentos."
            );
        }

        this.doador = doador;

        this.beneficiario = beneficiario;

        this.campanha = campanha;

        this.kgAlimento = kgAlimento;

        this.tipo = "ALIMENTO";
    }

    /**
     * Cria uma doação de agasalhos.
     *
     * @param doador Doador.
     * @param beneficiario Beneficiário.
     * @param campanha Campanha.
     * @param unidadeAgasalho Quantidade.
     */
    public Doacao(
            Doador doador,
            Beneficiario beneficiario,
            Campanha campanha,
            int unidadeAgasalho
    ) {

        validarObjetos(
                doador,
                beneficiario,
                campanha
        );

        if(unidadeAgasalho <= 0) {

            throw new IllegalArgumentException(
                    "Quantidade inválida."
            );
        }

        if(!campanha.isAceitaAgasalho()) {

            throw new IllegalArgumentException(
                    "Campanha não aceita agasalhos."
            );
        }

        this.doador = doador;

        this.beneficiario = beneficiario;

        this.campanha = campanha;

        this.unidadeAgasalho =
                unidadeAgasalho;

        this.tipo = "AGASALHO";
    }

    /**
     * Construtor privado para usar no listar doações sem precisar passar os parâmetros.
     */
    private Doacao() {
    }
    
    /**
     * Valida objetos da doação.
     *
     * @param doador Doador.
     * @param beneficiario Beneficiário.
     * @param campanha Campanha.
     */
    private void validarObjetos(
            Doador doador,
            Beneficiario beneficiario,
            Campanha campanha
    ) {

        if(doador == null) {

            throw new IllegalArgumentException(
                    "Doador inválido."
            );
        }

        if(beneficiario == null) {

            throw new IllegalArgumentException(
                    "Beneficiário inválido."
            );
        }

        if(campanha == null) {

            throw new IllegalArgumentException(
                    "Campanha inválida."
            );
        }
    }

    /**
     * Cadastra uma doação.
     *
     * @param doacao Doação cadastrada.
     */
    public static void cadastrarDoacao(
            Doacao doacao
    ) {

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                "INSERT INTO sgd.doacao (" +
                "id_doador," +
                "id_beneficiario," +
                "id_campanha," +
                "tipo_doacao," +
                "valor_monetario," +
                "kg_alimento," +
                "unidade_agasalho" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?)";

            bd.st = bd.con.prepareStatement(sql);

            bd.st.setInt(
                1,
                doacao.getDoador().getId()
            );

            bd.st.setInt(
                2,
                doacao.getBeneficiario().getId()
            );

            bd.st.setInt(
                3,
                doacao.getCampanha().getId()
            );

            bd.st.setString(
                4,
                doacao.getTipo()
            );
            
            if(doacao.getTipo().equals("DINHEIRO")) {

                bd.st.setDouble(
                    5,
                    doacao.getValorMonetario()
                );

                bd.st.setNull(
                    6,
                    java.sql.Types.NUMERIC
                );

                bd.st.setNull(
                    7,
                    java.sql.Types.INTEGER
                );
            }
            
            else if(doacao.getTipo().equals("ALIMENTO")) {

                bd.st.setNull(
                    5,
                    java.sql.Types.NUMERIC
                );

                bd.st.setDouble(
                    6,
                    doacao.getKgAlimento()
                );

                bd.st.setNull(
                    7,
                    java.sql.Types.INTEGER
                );
            }
            
            else {

                bd.st.setNull(
                    5,
                    java.sql.Types.NUMERIC
                );

                bd.st.setNull(
                    6,
                    java.sql.Types.NUMERIC
                );

                bd.st.setInt(
                    7,
                    doacao.getUnidadeAgasalho()
                );
            }
            bd.st.executeUpdate();
        }catch(SQLException e) {

            throw new IllegalArgumentException(
                    "Erro ao cadastrar campanha: "
                            + e.getMessage()
            );

        } finally {

            bd.close();
        }
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
     * Lista todas as doações.
     *
     * @return Lista de doações.
     */
    public static ArrayList<Doacao> listarDoacoes() {

        ArrayList<Doacao> lista =
                new ArrayList<>();

        BD bd = new BD();

        try {

            bd.getConnection();

            String sql =
                "SELECT " +
                "d.id_doacao, " +
                "d.tipo_doacao, " +
                "d.valor_monetario, " +
                "d.kg_alimento, " +
                "d.unidade_agasalho, " +

                "doa.id_doador, " +
                "doa.nome AS nome_doador, " +
                "doa.email, " +
                "doa.telefone AS telefone_doador, " +
                "doa.documento, " +

                "b.id_beneficiario, " +
                "b.nome AS nome_beneficiario, " +
                "b.cpf, " +
                "b.telefone AS telefone_beneficiario, " +
                "b.endereco, " +
                "b.membros_familia, " +
                "b.ativo, " +

                "c.id_campanha, " +
                "c.nome_campanha, " +
                "c.aceita_dinheiro, " +
                "c.aceita_agasalho, " +
                "c.aceita_alimento " +

                "FROM sgd.doacao d " +

                "INNER JOIN sgd.doador doa " +
                "ON doa.id_doador = d.id_doador " +

                "INNER JOIN sgd.beneficiario b " +
                "ON b.id_beneficiario = d.id_beneficiario " +

                "INNER JOIN sgd.campanha c " +
                "ON c.id_campanha = d.id_campanha " +

                "ORDER BY d.id_doacao";

            bd.st = bd.con.prepareStatement(sql);

            bd.rs = bd.st.executeQuery();

            while (bd.rs.next()) {

                Doador doador =
                    new Doador(
                        bd.rs.getString("nome_doador"),
                        bd.rs.getString("email"),
                        bd.rs.getString("telefone_doador"),
                        bd.rs.getString("documento")
                    );

                doador.setId(
                    bd.rs.getInt("id_doador")
                );

                Beneficiario beneficiario =
                    new Beneficiario(
                        bd.rs.getString("nome_beneficiario"),
                        bd.rs.getString("cpf"),
                        bd.rs.getString("telefone_beneficiario"),
                        bd.rs.getString("endereco"),
                        bd.rs.getInt("membros_familia")
                    );

                beneficiario.setId(
                    bd.rs.getInt("id_beneficiario")
                );

                beneficiario.setAtivo(
                    bd.rs.getBoolean("ativo")
                );

                Campanha campanha =
                    new Campanha(
                        bd.rs.getString("nome_campanha"),
                        bd.rs.getBoolean("aceita_dinheiro"),
                        bd.rs.getBoolean("aceita_agasalho"),
                        bd.rs.getBoolean("aceita_alimento")
                    );

                campanha.setId(
                    bd.rs.getInt("id_campanha")
                );

                Doacao doacao;

                String tipo =
                    bd.rs.getString("tipo_doacao");

                if(tipo.equals("DINHEIRO")) {

                    doacao =
                        new Doacao(
                            doador,
                            beneficiario,
                            campanha,
                            bd.rs.getFloat(
                                "valor_monetario"
                            )
                        );
                }
                else if(tipo.equals("ALIMENTO")) {

                    doacao =
                        new Doacao(
                            doador,
                            beneficiario,
                            campanha,
                            bd.rs.getDouble(
                                "kg_alimento"
                            )
                        );
                }
                else {

                    doacao =
                        new Doacao(
                            doador,
                            beneficiario,
                            campanha,
                            bd.rs.getInt(
                                "unidade_agasalho"
                            )
                        );
                }

                doacao.setId(
                    bd.rs.getInt("id_doacao")
                );

                lista.add(doacao);
            }

        }
        catch(SQLException e) {

            e.printStackTrace();
        }
        finally {

            bd.close();
        }

        return lista;
    }

    /**
     * Retorna o tipo da doação.
     *
     * @return Tipo.
     */
    public String getTipo() {

        return tipo;
    }

    public float getValorMonetario() {

        return valorMonetario;
    }

    public double getKgAlimento() {

        return kgAlimento;
    }

    public int getUnidadeAgasalho() {

        return unidadeAgasalho;
    }

    public Doador getDoador() {

        return doador;
    }

    public Beneficiario getBeneficiario() {

        return beneficiario;
    }

    public Campanha getCampanha() {

        return campanha;
    }

    /**
     * Exibe informações no console.
     */
    public void mostrarInfo() {

        System.out.println(
                toString()
        );
    }

    @Override
    public String toString() {

        return tipo
                + " - "
                + doador.getNome();
    }
}