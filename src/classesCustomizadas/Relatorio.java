package classesCustomizadas;

import services.BD;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Relatorio {

    public static DefaultTableModel gerarRelatorio(
            String sql
    ) {

        DefaultTableModel modelo =
                new DefaultTableModel();

        BD bd = new BD();

        try {

            bd.getConnection();

            bd.st =
                    bd.con.prepareStatement(
                            sql
                    );

            bd.rs =
                    bd.st.executeQuery();

            ResultSetMetaData meta =
                    bd.rs.getMetaData();

            int quantidadeColunas =
                    meta.getColumnCount();

            for (
                    int i = 1;
                    i <= quantidadeColunas;
                    i++
            ) {

                modelo.addColumn(
                        meta.getColumnLabel(i)
                );
            }

            while (bd.rs.next()) {

                Object[] linha =
                        new Object[
                                quantidadeColunas
                        ];

                for (
                        int i = 1;
                        i <= quantidadeColunas;
                        i++
                ) {

                    linha[i - 1] =
                            bd.rs.getObject(i);
                }

                modelo.addRow(
                        linha
                );
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    e
            );

        } finally {

            bd.close();
        }

        return modelo;
    }

    public static DefaultTableModel listarDoacoes() {

        String sql =
                """
                SELECT
                    doa.id_doacao,
                    d.nome AS doador,
                    b.nome AS beneficiario,
                    c.nome_campanha,
                    doa.tipo_doacao,
                    doa.valor_monetario,
                    doa.kg_alimento,
                    doa.unidade_agasalho,
                    doa.data_doacao
                FROM sgd.doacao doa
                INNER JOIN sgd.doador d
                    ON d.id_doador = doa.id_doador
                INNER JOIN sgd.beneficiario b
                    ON b.id_beneficiario = doa.id_beneficiario
                INNER JOIN sgd.campanha c
                    ON c.id_campanha = doa.id_campanha
                ORDER BY doa.data_doacao DESC
                """;

        return gerarRelatorio(
                sql
        );
    }

    public static DefaultTableModel totalPorCampanha() {

        String sql =
                """
                SELECT
                    c.nome_campanha,
                    COUNT(doa.id_doacao) AS total_doacoes,
                    COALESCE(SUM(doa.valor_monetario),0) AS dinheiro,
                    COALESCE(SUM(doa.kg_alimento),0) AS alimentos,
                    COALESCE(SUM(doa.unidade_agasalho),0) AS agasalhos
                FROM sgd.campanha c
                LEFT JOIN sgd.doacao doa
                    ON doa.id_campanha = c.id_campanha
                GROUP BY c.nome_campanha
                ORDER BY total_doacoes DESC
                """;

        return gerarRelatorio(
                sql
        );
    }

    public static DefaultTableModel totalPorDoador() {

        String sql =
                """
                SELECT
                    d.nome,
                    d.email,
                    COUNT(doa.id_doacao) AS total_doacoes,
                    COALESCE(SUM(doa.valor_monetario),0) AS dinheiro,
                    COALESCE(SUM(doa.kg_alimento),0) AS alimentos,
                    COALESCE(SUM(doa.unidade_agasalho),0) AS agasalhos
                FROM sgd.doador d
                LEFT JOIN sgd.doacao doa
                    ON doa.id_doador = d.id_doador
                GROUP BY d.nome, d.email
                ORDER BY total_doacoes DESC
                """;

        return gerarRelatorio(
                sql
        );
    }

    public static DefaultTableModel totalPorBeneficiario() {

        String sql =
                """
                SELECT
                    b.nome,
                    b.cpf,
                    COUNT(doa.id_doacao) AS total_recebido,
                    COALESCE(SUM(doa.valor_monetario),0) AS dinheiro,
                    COALESCE(SUM(doa.kg_alimento),0) AS alimentos,
                    COALESCE(SUM(doa.unidade_agasalho),0) AS agasalhos
                FROM sgd.beneficiario b
                LEFT JOIN sgd.doacao doa
                    ON doa.id_beneficiario = b.id_beneficiario
                GROUP BY b.nome, b.cpf
                ORDER BY total_recebido DESC
                """;

        return gerarRelatorio(
                sql
        );
    }

    public static DefaultTableModel campanhasAtivas() {

        String sql =
                """
                SELECT
                    id_campanha,
                    nome_campanha,
                    aceita_dinheiro,
                    aceita_alimento,
                    aceita_agasalho
                FROM sgd.campanha
                WHERE ativa = TRUE
                ORDER BY nome_campanha
                """;

        return gerarRelatorio(
                sql
        );
    }

    public static DefaultTableModel campanhasSemDoacoes() {

        String sql =
                """
                SELECT
                    c.id_campanha,
                    c.nome_campanha
                FROM sgd.campanha c
                LEFT JOIN sgd.doacao doa
                    ON doa.id_campanha = c.id_campanha
                WHERE doa.id_doacao IS NULL
                ORDER BY c.nome_campanha
                """;

        return gerarRelatorio(
                sql
        );
    }

    public static DefaultTableModel beneficiariosAtendidos() {

        String sql =
                """
                SELECT
                    b.nome,
                    b.cpf,
                    COUNT(doa.id_doacao) AS total_recebido
                FROM sgd.beneficiario b
                INNER JOIN sgd.doacao doa
                    ON doa.id_beneficiario = b.id_beneficiario
                WHERE b.ativo = TRUE
                GROUP BY b.nome, b.cpf
                ORDER BY total_recebido DESC
                """;

        return gerarRelatorio(
                sql
        );
    }
}