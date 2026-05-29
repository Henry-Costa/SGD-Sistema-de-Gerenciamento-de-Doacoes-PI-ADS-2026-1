package classesCustomizadas;

import java.util.ArrayList;

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

        doacoes.add(doacao);

        doacao.getCampanha()
                .adicionarDoacao(doacao);

        doacao.getBeneficiario()
                .registrarRecebimento(doacao);
    }

    /**
     * Lista todas as doações.
     *
     * @return Lista de doações.
     */
    public static ArrayList<Doacao>
listarDoacoes() {

        return doacoes;
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