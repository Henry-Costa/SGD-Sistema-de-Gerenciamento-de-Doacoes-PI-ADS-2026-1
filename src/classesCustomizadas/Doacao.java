package classesCustomizadas;

// TODO: Auto-generated Javadoc
/**
 * The Class Doacao.
 */
public class Doacao {

	/** O doador. */
	Doador doador;
	
	/** O beneficiario. */
	Beneficiario beneficiario;
	
	/** A campanha. */
	Campanha campanha;
    
    /** O valor monetario da doação. */
    private float valorMonetario;
    
    /** A quantidade de alimento em kgs. */
    private double kgAlimento;
    
    /** A quantidade de agasalhos em unidade. */
    private int unidadeAgasalho;

    /**
     * Cria uma doação com valor monetário.
     *
     * @param doador the doador
     * @param beneficiario the beneficiario
     * @param campanha the campanha
     * @param valorMonetario the valor monetario
     */
    public Doacao(Doador doador, Beneficiario beneficiario, Campanha campanha, float valorMonetario) {
        this.doador = doador;
        this.beneficiario = beneficiario;
        this.campanha = campanha;
        this.valorMonetario = valorMonetario;
    }
    
    /**
     * Cria uma doação com kgs de alimento.
     *
     * @param doador the doador
     * @param beneficiario the beneficiario
     * @param campanha the campanha
     * @param kgAlimento the kg alimento
     */
    public Doacao(Doador doador, Beneficiario beneficiario, Campanha campanha, double kgAlimento) {
    	this.doador = doador;
        this.beneficiario = beneficiario;
        this.campanha = campanha;
        this.kgAlimento = kgAlimento;
    }

	/**
	 * Cria uma doação com unidades de agasalho.
	 *
	 * @param doador the doador
	 * @param beneficiario the beneficiario
	 * @param campanha the campanha
	 * @param unidadeAgasalho the unidade agasalho
	 */
	public Doacao(Doador doador, Beneficiario beneficiario, Campanha campanha, int unidadeAgasalho) {
		this.doador = doador;
        this.beneficiario = beneficiario;
        this.campanha = campanha;
        this.unidadeAgasalho = unidadeAgasalho;
	}

	/**
	 * Gets the valor monetario.
	 *
	 * @return the valor monetario
	 */
	public float getValorMonetario() {
		return valorMonetario;
	}

	/**
	 * Sets the valor monetario.
	 *
	 * @param valorMonetario the new valor monetario
	 */
	public void setValorMonetario(float valorMonetario) {
		this.valorMonetario = valorMonetario;
	}

	/**
	 * Gets the kg alimento.
	 *
	 * @return the kg alimento
	 */
	public double getKgAlimento() {
		return kgAlimento;
	}

	/**
	 * Sets the kg alimento.
	 *
	 * @param kgAlimento the new kg alimento
	 */
	public void setKgAlimento(double kgAlimento) {
		this.kgAlimento = kgAlimento;
	}

	/**
	 * Gets the unidade agasalho.
	 *
	 * @return the unidade agasalho
	 */
	public int getUnidadeAgasalho() {
		return unidadeAgasalho;
	}

	/**
	 * Sets the unidade agasalho.
	 *
	 * @param unidadeAgasalho the new unidade agasalho
	 */
	public void setUnidadeAgasalho(int unidadeAgasalho) {
		this.unidadeAgasalho = unidadeAgasalho;
	}

	/**
	 * Gets the doador.
	 *
	 * @return the doador
	 */
	public Doador getDoador() {
		return doador;
	}

	/**
	 * Gets the beneficiario.
	 *
	 * @return the beneficiario
	 */
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	/**
	 * Gets the campanha.
	 *
	 * @return the campanha
	 */
	public Campanha getCampanha() {
		return campanha;
	}

    /**
     * Mostrar info.
     */
    public void mostrarInfo() {
    	System.out.println("Doador: " + doador.getNome());
    	System.out.println("Beneficiário: "+ beneficiario.getNome());
    	System.out.println("Campanha: " + campanha.getNome());
    	System.out.println("Valor monetário: R$" + valorMonetario);
    	System.out.println("Alimento: " + kgAlimento + "Kg(s)");
    	System.out.println("Agasalhos: " + unidadeAgasalho);
    }
}