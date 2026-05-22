package classesCustomizadas;

import java.io.*;
/**
 * Classe responsável por representar a sessão de um usuário
 * e prover métodos de persistência via serialização.
 */

public class Login implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String username;
    public String senha;
    
    /**
     * Serializa o objeto atual para um arquivo no disco.
     * * @param caminho O destino onde o arquivo .dat será criado.
     * @return String contendo a mensagem de confirmação ou erro.
     */

    // Método para gravar o objeto (igual ao exemplo do prof)
    public String gravar(String caminho) {
        try {
            FileOutputStream fos = new FileOutputStream(caminho);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            return "Sessão salva com sucesso!";
        } catch (IOException e) {
            return "Erro ao salvar arquivo: " + e.getMessage();
        }
    }

    // Método para ler o objeto
    /**
     * Realiza a leitura (desserialização) de um objeto Login do disco.
     * * @param caminho O local onde o arquivo está armazenado.
     * @return O objeto Login recuperado ou null em caso de falha.
     */
    public static Login ler(String caminho) {
        try {
            FileInputStream fis = new FileInputStream(caminho);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Login l = (Login) ois.readObject();
            ois.close();
            return l;
        } catch (Exception e) {
            return null;
        }
    }
}
