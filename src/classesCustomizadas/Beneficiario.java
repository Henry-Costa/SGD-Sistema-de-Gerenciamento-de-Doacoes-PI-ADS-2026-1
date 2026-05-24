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
    /** CPF do beneficiário, utilizado como identificador único. */
    private final String cpf;
    /** Número de telefone para contato. */
    private String telefone;
    /** Endereço residencial do beneficiário. */
    private String endereco;
    /** Número de membros na família do beneficiário, utilizado para avaliação de elegibilidade. */
    private int membrosFamilia;
    /** Lista de doações recebidas pelo beneficiário, armazenando o valor e a data de cada doação. */
    private List<Doacao> historicoDoacoes = new ArrayList<>();
    /** Status de elegibilidade do beneficiário, indicando se ele está ativo e apto a receber doações. */
    private boolean ativo;

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
     * Gets the membrosFamilia.
     *
     * @return the membrosFamilia
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

}