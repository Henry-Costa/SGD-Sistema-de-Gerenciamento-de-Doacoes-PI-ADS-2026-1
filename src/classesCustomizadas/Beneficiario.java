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
}