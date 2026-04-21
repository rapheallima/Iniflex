import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // 3.1 - Inserir funcionários
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"), new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"), new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"), new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"), new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"), new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"), new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"), new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"), new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"), new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")));

        // 3.2 - Remover João
        funcionarios.removeIf(f -> f.nome.equals("João"));

        // 3.3 - Imprimir (O toString() da classe Funcionario já trata a formatação)
        System.out.println("--- Lista de Funcionários ---");
        funcionarios.forEach(System.out::println);

        // 3.4 - Aumento de 10%
        funcionarios.forEach(f -> f.salario = f.salario.multiply(new BigDecimal("1.10")));

        // 3.5 - Agrupar por função (MAP)
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream().collect(Collectors.groupingBy(f -> f.funcao));

        // 3.6 - Imprimir agrupados
        System.out.println("\n--- Agrupados por Função ---");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("  " + f.nome));
        });

        // 3.8 - Aniversariantes mês 10 e 12
        System.out.println("\n--- Aniversariantes Outubro (10) e Dezembro (12) ---");
        funcionarios.stream().filter(f -> f.dataNascimento.getMonthValue() == 10 || f.dataNascimento.getMonthValue() == 12).forEach(System.out::println);

        // 3.9 - Maior idade
        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(f -> f.dataNascimento));
        int idade = Period.between(maisVelho.dataNascimento, LocalDate.now()).getYears();
        System.out.println("\n--- Funcionário com maior idade ---");
        System.out.println("Nome: " + maisVelho.nome + " | Idade: " + idade);

        // 3.10 - Ordem alfabética
        System.out.println("\n--- Ordem Alfabética ---");
        funcionarios.stream().sorted(Comparator.comparing(f -> f.nome)).forEach(System.out::println);

        // 3.11 - Total dos salários
        BigDecimal totalSalarios = funcionarios.stream().map(f -> f.salario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalSalarios));

        // 3.12 - Salários mínimos (R$ 1212.00)
        System.out.println("\n--- Qtd de Salários Mínimos ---");
        BigDecimal minimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.salario.divide(minimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.nome + " ganha " + qtd + " salários mínimos.");
        });
    }
}
