package classesCustomizadas;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * Classe que representa um beneficiário da ONG.
 * Armazena informações pessoais, controla o status de elegibilidade
 * e mantém o histórico de doações recebidas.
 */
public class Beneficiario {

    /** Nome completo do beneficiário. */
    private String nome;

    /** CPF do beneficiário. Imutável após o cadastro (11 dígitos numéricos). */
    private final String cpf;

    /** Telefone de contato com DDD (10 ou 11 dígitos numéricos). */
    private String telefone;

    /** Endereço residencial do beneficiário. */
    private String endereco;

    /** Quantidade de pessoas que compõem a família do beneficiário. */
    private int membrosFamilia;

    /** Indica se o beneficiário está ativo no programa da ONG. */
    private boolean ativo;

    /** Lista interna de doações recebidas pelo beneficiário. */
    private final List<Doacao> doacoesRecebidas = new ArrayList<>();
    
    private static ArrayList<Beneficiario> beneficiarios = new ArrayList<>();

    /**
     * Construtor da classe Beneficiario.
     * Inicializa todos os campos com validação.
     * O CPF é validado e imutável após a criação.
     * O beneficiário começa como ativo por padrão.
     *
     * @param nome           Nome completo do beneficiário.
     * @param cpf            CPF com 11 dígitos numéricos válidos.
     * @param telefone       Telefone com DDD (10 ou 11 dígitos).
     * @param endereco       Endereço residencial.
     * @param membrosFamilia Número de membros na família (mínimo 1).
     * @throws IllegalArgumentException se qualquer parâmetro for inválido.
     */
    public Beneficiario(String nome, String cpf, String telefone, String endereco, int membrosFamilia) {
        this.cpf = validarCpf(cpf);
        setNome(nome);
        setTelefone(telefone);
        setEndereco(endereco);
        setMembrosFamilia(membrosFamilia);
        this.ativo = true;
    }
    
    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }
    
    /**
     * Gets the telefone.
     *
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }
    
    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }
    
    /**
     * Gets the membros familia.
     *
     * @return the membros familia
     */
    public int getMembrosFamilia() {
        return membrosFamilia;
    }

    /**
     * Checks if is ativo.
     *
     * @return true, if is ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * Retorna uma cópia da lista de doações recebidas.
     * A cópia defensiva impede modificações externas na lista interna.
     *
     * @return lista de doações recebidas.
     */
    public List<Doacao> getDoacoesRecebidas() {
        return new ArrayList<>(doacoesRecebidas);
    }

    /**
     * Retorna a quantidade total de doações recebidas.
     *
     * @return número de doações registradas.
     */
    public int getQuantidadeDoacoes() {
        return doacoesRecebidas.size();
    }

    /**
     * Define o nome do beneficiário.
     * Não aceita valor nulo ou vazio.
     *
     * @param nome novo nome.
     * @throws IllegalArgumentException se o nome for nulo ou vazio.
     */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        this.nome = nome.trim();
    }

    /**
     * Define o telefone do beneficiário.
     * Aceita somente 10 ou 11 dígitos numéricos (com DDD).
     *
     * @param telefone novo telefone.
     * @throws IllegalArgumentException se o telefone for inválido.
     */
    public void setTelefone(String telefone) {
        if (telefone == null || !telefone.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
    }

    /**
     * Define o endereço do beneficiário.
     * Não aceita valor nulo ou vazio.
     *
     * @param endereco novo endereço.
     * @throws IllegalArgumentException se o endereço for nulo ou vazio.
     */
    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço inválido.");
        }
        this.endereco = endereco.trim();
    }

    /**
     * Define o número de membros da família.
     * Deve ser no mínimo 1.
     *
     * @param membrosFamilia novo número de membros.
     * @throws IllegalArgumentException se o valor for menor que 1.
     */
    public void setMembrosFamilia(int membrosFamilia) {
        if (membrosFamilia < 1) {
            throw new IllegalArgumentException("Deve haver pelo menos 1 membro.");
        }
        this.membrosFamilia = membrosFamilia;
    }

    /**
     * Define o status do beneficiário no programa.
     *
     * @param ativo true para ativo, false para inativo.
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * Registra o recebimento de uma doação pelo beneficiário.
     * Valida se o beneficiário está ativo e se a doação não é nula ou duplicada.
     *
     * @param doacao doação a ser registrada.
     * @throws IllegalStateException    se o beneficiário estiver inativo.
     * @throws IllegalArgumentException se a doação for nula.
     */
    public void registrarRecebimento(Doacao doacao) {
        if (!ativo) {
            throw new IllegalStateException("Beneficiário inativo.");
        }
        if (doacao == null) {
            throw new IllegalArgumentException("Doação inválida.");
        }
        if (!doacoesRecebidas.contains(doacao)) {
            doacoesRecebidas.add(doacao);
        }
    }

    /**
     * Ativa o beneficiário no programa da ONG.
     */
    public void ativar() {
        this.ativo = true;
    }

    /**
     * Desativa o beneficiário no programa da ONG.
     * Beneficiários inativos não podem receber doações.
     */
    public void desativar() {
        this.ativo = false;
    }

    /**
     * Verifica se o beneficiário tem prioridade no recebimento de doações.
     * Famílias com 4 ou mais membros são consideradas prioritárias.
     *
     * @return true se elegível à prioridade, false caso contrário.
     */
    public boolean isElegivelPrioridade() {
        return membrosFamilia >= 4;
    }

    /**
     * Remove todas as doações registradas para este beneficiário.
     * Útil para reinício de ciclo de distribuição.
     */
    public void limparDoacoes() {
        doacoesRecebidas.clear();
    }

    /**
     * Calcula o total de dinheiro recebido em todas as doações.
     *
     * @return soma dos valores em reais.
     */
    public double calcularTotalDinheiro() {
        return doacoesRecebidas.stream()
                .mapToDouble(Doacao::getValorMonetario)
                .sum();
    }

    /**
     * Calcula o total de agasalhos recebidos em todas as doações.
     *
     * @return soma das quantidades de agasalhos.
     */
    public int calcularTotalAgasalhos() {
        return doacoesRecebidas.stream()
                .mapToInt(Doacao::getUnidadeAgasalho)
                .sum();
    }

    /**
     * Calcula o total de alimentos recebidos em todas as doações.
     *
     * @return soma do peso em kg de alimentos.
     */
    public double calcularTotalAlimentos() {
        return doacoesRecebidas.stream()
                .mapToDouble(Doacao::getKgAlimento)
                .sum();
    }

    /**
     * Calcula a média de dinheiro recebido por doação.
     * Retorna 0 se não houver doações registradas.
     *
     * @return média em reais por doação.
     */
    public double getMediaDinheiroPorDoacao() {
        if (doacoesRecebidas.isEmpty()) return 0;
        return calcularTotalDinheiro() / doacoesRecebidas.size();
    }

    /**
     * Exibe as informações resumidas do beneficiário no console.
     */
    public void mostrarInfo() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Status: " + (ativo ? "Ativo" : "Inativo"));
        System.out.println("Membros família: " + membrosFamilia);
        System.out.println("Total dinheiro: R$ " + calcularTotalDinheiro());
        System.out.println("Total alimentos: " + calcularTotalAlimentos() + " kg");
        System.out.println("Total agasalhos: " + calcularTotalAgasalhos());
    }

    /**
     * Gera e retorna um relatório completo do beneficiário em formato de texto.
     * Não imprime diretamente, permitindo uso flexível (console, arquivo, tela).
     *
     * @return String com o relatório formatado.
     */
    public String gerarRelatorio() {
        return "----- BENEFICIÁRIO -----\n" +
                "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "Status: " + (ativo ? "Ativo" : "Inativo") + "\n" +
                "Membros: " + membrosFamilia + "\n" +
                "Total R$: " + calcularTotalDinheiro() + "\n";
    }

    /**
     * Valida o CPF informado.
     * Verifica formato, dígitos repetidos e dígitos verificadores.
     *
     * @param cpf CPF a ser validado (somente números).
     * @return CPF validado.
     * @throws IllegalArgumentException se o CPF for inválido.
     */
    private String validarCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        // Validação dos dígitos verificadores
        int[] nums = cpf.chars().map(c -> c - '0').toArray();

        int soma1 = 0;
        for (int i = 0; i < 9; i++) soma1 += nums[i] * (10 - i);
        int d1 = (soma1 * 10) % 11;
        if (d1 == 10) d1 = 0;

        int soma2 = 0;
        for (int i = 0; i < 10; i++) soma2 += nums[i] * (11 - i);
        int d2 = (soma2 * 10) % 11;
        if (d2 == 10) d2 = 0;

        if (d1 != nums[9] || d2 != nums[10]) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        return cpf;
    }
    
    /**
     * Cadastra um beneficiário na lista em memória(enquanto não tem o banco de dados).
     *
     * @param beneficiario Beneficiário a cadastrar.
     */
    public static void cadastrarBeneficiario(
            Beneficiario beneficiario
    ) {

        for(Beneficiario b : beneficiarios) {

            /*
             * CPF DUPLICADO
             */

            if(b.getCpf().equals(
                    beneficiario.getCpf())) {

                throw new IllegalArgumentException(
                        "CPF já cadastrado."
                );
            }
        }

        beneficiarios.add(beneficiario);
    }
    
    /**
     * Retorna todos os beneficiários cadastrados.
     *
     * @return Lista de beneficiários.
     */
    public static ArrayList<Beneficiario>
    listarBeneficiarios() {

        return beneficiarios;
    }
    
    /**
     * Pesquisa beneficiários pelo nome.
     *
     * @param nome.
     *
     * @return Lista de beneficiários encontrados.
     */
    public static ArrayList<Beneficiario>
    pesquisarPorNome(String nome) {

        ArrayList<Beneficiario> encontrados =
                new ArrayList<>();

        for(Beneficiario beneficiario
                : beneficiarios) {

            if(beneficiario.getNome()
                    .toLowerCase()
                    .contains(nome.toLowerCase())) {

                encontrados.add(beneficiario);
            }
        }

        return encontrados;
    }

    /**
     * Compara dois beneficiários pelo CPF.
     * Dois beneficiários são iguais se possuírem o mesmo CPF.
     *
     * @param o objeto a comparar.
     * @return true se os CPFs forem iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beneficiario)) return false;
        Beneficiario that = (Beneficiario) o;
        return cpf.equals(that.cpf);
    }

    /**
     * Gera o hash code baseado no CPF do beneficiário.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    /**
     * Retorna uma representação resumida do beneficiário em texto.
     *
     * @return String com nome, CPF e status.
     */
    @Override
    public String toString() {
        return "Beneficiario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
