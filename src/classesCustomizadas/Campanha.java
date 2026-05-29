package classesCustomizadas;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma campanha de doações.
 * Uma campanha pode aceitar diferentes tipos de doações:
 * dinheiro, agasalhos e alimentos.
 */
public class Campanha {

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
    
    private static ArrayList<Campanha> campanhas = new ArrayList<>();

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
        return doacoes.stream()
                .mapToDouble(Doacao::getValorMonetario)
                .sum();
    }

    /**
     * Calcula o total de agasalhos arrecadados.
     *
     * @return quantidade total de agasalhos
     */
    public int getTotalAgasalhos() {
        return doacoes.stream()
                .mapToInt(Doacao::getUnidadeAgasalho)
                .sum();
    }

    /**
     * Calcula o total de alimentos (em kg).
     *
     * @return total em quilogramas
     */
    public double getTotalAlimentosKg() {
        return doacoes.stream()
                .mapToDouble(Doacao::getKgAlimento)
                .sum();
    }

    
    /**
     * Cadastra uma campanha na lista em memória(Enquanto não tem banco de dados).
     *
     * @param campanha Campanha cadastrada.
     */
    public static void cadastrarCampanha(
            Campanha campanha
    ) {

        for(Campanha c : campanhas) {

            if(c.getNome().equalsIgnoreCase(
                    campanha.getNome())) {

                throw new IllegalArgumentException(
                        "Já existe uma campanha com esse nome."
                );
            }
        }

        campanhas.add(campanha);
    }
    
    /**
     * Retorna todas as campanhas cadastradas.
     *
     * @return Lista de campanhas.
     */
    public static ArrayList<Campanha>
    listarCampanhas() {

        return campanhas;
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

        ArrayList<Campanha> encontradas =
                new ArrayList<>();

        for(Campanha campanha : campanhas) {

            if(campanha.getNome()
                    .toLowerCase()
                    .contains(nome.toLowerCase())) {

                encontradas.add(campanha);
            }
        }

        return encontradas;
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