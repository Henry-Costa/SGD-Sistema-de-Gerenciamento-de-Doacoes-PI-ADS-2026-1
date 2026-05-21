package classesCustomizadas;

// TODO: Auto-generated Javadoc
/**
 * Classe que representa um doador.
 * Armazena as informações básicas de uma pessoa doadora:
 * nome, e-mail, telefone e documento.
 */
public class Doador {

    /** Nome do doador. */
    private String nome;

    /** E-mail do doador. */
    private String email;

    /** Telefone do doador. */
    private String telefone;

    /** Documento do doador (CPF, RG, etc.) */
    private String documento;

    /**
     * Construtor da classe Doador.
     *
     * @param nome Nome do doador
     * @param email E-mail do doador
     * @param telefone Telefone do doador
     * @param documento Documento de identificação
     */
    public Doador(String nome, String email, String telefone, String documento) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.documento = documento;
    }

    /**
     * Exibe todas as informações do doador no console.
     */
    public void mostrarInfo() {
        System.out.println("Nome: " + nome);
        System.out.println("E-mail: " + email);
        System.out.println("Telefone: " + telefone);
        System.out.println("CNPJ/CPF: " + documento);
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
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Gets the documento.
     *
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Sets the documento.
     *
     * @param documento the new documento
     */
    public void setDocumento(String documento) {
        this.documento = validarCpfOuCnpj(documento);
    }
    
    /**
     * Verifica se o registro do documento é um cpf ou cnpj.
     *
     * @param documento registro do documento recebido
     * @return the string
     */
    private String validarCpfOuCnpj(String documento) {
        if (documento == null) {
            throw new IllegalArgumentException("Documento inválido.");
        }

        // Remove tudo que não for número
        documento = documento.replaceAll("\\D", "");

        if (documento.length() == 11) {
            return validarCPF(documento);
        } else if (documento.length() == 14) {
            return validarCNPJ(documento);
        } else {
            throw new IllegalArgumentException("Documento deve ser CPF ou CNPJ válido.");
        }
    }
    
    /**
     * Valida se o cpf é válido.
     *
     * @param cpf the cpf
     * @return the string
     */
    private String validarCPF(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }

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
     * Valida se o cnpj é válido.
     *
     * @param cnpj the cnpj
     * @return the string
     */
    private String validarCNPJ(String cnpj) {
        if (!cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma1 = 0;
        for (int i = 0; i < 12; i++) {
            soma1 += (cnpj.charAt(i) - '0') * pesos1[i];
        }
        int d1 = soma1 % 11;
        d1 = (d1 < 2) ? 0 : 11 - d1;

        int soma2 = 0;
        for (int i = 0; i < 13; i++) {
            soma2 += (cnpj.charAt(i) - '0') * pesos2[i];
        }
        int d2 = soma2 % 11;
        d2 = (d2 < 2) ? 0 : 11 - d2;

        if (d1 != (cnpj.charAt(12) - '0') || d2 != (cnpj.charAt(13) - '0')) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        return cnpj;
    }
}