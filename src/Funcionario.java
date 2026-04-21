import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;


public class Funcionario extends Pessoa {

    BigDecimal salario;
    String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        String salarioFormatado = nf.format(salario).replace("R$", "").trim();

        return String.format("Nome: %s | Data Nasc: %s | Salário: %s | Função: %s", nome, dataNascimento.format(df), salarioFormatado, funcao);
    }

}
