/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package br.unipar.sistemabancario;
import br.unipar.sistemabancario.model.ContaBancaria;
import javax.swing.JOptionPane;

/**
 *
 * @author vinid
 */
public class SistemaBancario {

     

/**
 *
 * @author vinid
 */


    public static void main(String[] args) {

        final int maximo_contas = 1000000;

        ContaBancaria[] contas = new ContaBancaria[maximo_contas];

        int numContas = 0;

        String input;

        boolean sair = false;
        //painel de opçoes 
        while (!sair) {
            input = JOptionPane.showInputDialog("===== Sistema Bancário ===== \n"
                    + "1. Criar conta\n"
                    + "2. Depositar\n"
                    + "3. Sacar\n"
                    + "4. Consultar saldo\n"
                    + "5. Ordenar contas\n"
                    + "6. Calcular Saldo do banco\n"
                    + "7. Calcular Saldo do negativo\n"
                    + "8. Sair\n"
                    + "Escolha uma opção: ");

            int opcao;

            opcao = Integer.parseInt(input);
            //executa as opçoes que o usuario escolher 
            switch (opcao) {
                //cria a conta 
                case 1:
                    if (numContas < maximo_contas) {
                        input = JOptionPane.showInputDialog("Número da conta: \n");
                        int numeroConta = Integer.parseInt(input);

                        boolean contaExistente = false;
                        for (int i = 0; i < numContas; i++) {
                            if (contas[i].getNumeroConta() == numeroConta) {
                                contaExistente = true;
                                break;
                            }
                        }

                        if (contaExistente) {
                            JOptionPane.showMessageDialog(null, "Já existe uma conta com esse número!");
                        } else {
                            String nomeTitular = JOptionPane.showInputDialog("Nome do titular: \n");

                            input = JOptionPane.showInputDialog("Saldo inicial:   \n");
                            double saldoInicial = Double.parseDouble(input);

                            contas[numContas] = new ContaBancaria(numeroConta, nomeTitular, saldoInicial);
                            numContas++;

                            JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Limite máximo de contas atingido!");
                    }
                    break;
                //realizar um deposito
                case 2:
                    input = JOptionPane.showInputDialog("Número da conta: ");
                    int numeroContaDeposito = Integer.parseInt(input);
                    input = JOptionPane.showInputDialog("Valor a depositar: ");
                    double valorDeposito = Double.parseDouble(input);

                    boolean depositoEncontrado = false;

                    for (int i = 0; i < numContas; i++) {
                        if (contas[i].getNumeroConta() == numeroContaDeposito) {
                            contas[i].depositar(valorDeposito);
                            depositoEncontrado = true;
                            break;
                        }
                    }

                    if (depositoEncontrado) {
                        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }
                    break;
                //realiza a parte de sacar 
                case 3:
                    input = JOptionPane.showInputDialog("Número da conta: ");
                    int numeroContaSaque = Integer.parseInt(input);
                    input = JOptionPane.showInputDialog("Valor a sacar: ");
                    double valorSaque = Double.parseDouble(input);

                    boolean saqueEncontrado = false;

                    for (int i = 0; i < numContas; i++) {
                        if (contas[i].getNumeroConta() == numeroContaSaque) {
                            if (contas[i].sacar(valorSaque)) {
                                saqueEncontrado = true;
                                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Saldo insuficiente para saque!");
                            }
                            break;
                        }
                    }

                    if (!saqueEncontrado) {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }
                    break;
                //verifica o saldo da conra 
                case 4:
                    input = JOptionPane.showInputDialog("Número da conta: ");
                    int numeroContaConsulta = Integer.parseInt(input);

                    boolean contaEncontrada = false;

                    for (int i = 0; i < numContas; i++) {
                        if (contas[i].getNumeroConta() == numeroContaConsulta) {
                            JOptionPane.showMessageDialog(null, "Saldo: " + contas[i].getSaldo());
                            contaEncontrada = true;
                            break;
                        }
                    }

                    if (!contaEncontrada) {
                        JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                    }
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, "Ordenar contas bancárias");

                    String menuOrdenacao = "Escolha uma opção de ordenação:\n"
                            + "1. Ordenar por número de conta\n"
                            + "2. Ordenar por saldo\n";

                    int opcaoOrdenacao = Integer.parseInt(JOptionPane.showInputDialog(null, menuOrdenacao));

                    switch (opcaoOrdenacao) {
                        case 1:
                            // Ordenar as contas por número de conta
                            for (int i = 0; i < numContas - 1; i++) {
                                int indiceMenor = i;

                                for (int j = i + 1; j < numContas; j++) {
                                    if (contas[j].getNumeroConta() < contas[indiceMenor].getNumeroConta()) {
                                        indiceMenor = j;
                                    }
                                }

                                if (indiceMenor != i) {
                                    ContaBancaria temp = contas[i];
                                    contas[i] = contas[indiceMenor];
                                    contas[indiceMenor] = temp;
                                }
                            }

                            //  as informações das contas ordenadas 
                            String contasOrdenadas = "Contas ordenadas por número:\n";
                            for (int i = 0; i < numContas; i++) {
                                contasOrdenadas += "Número da conta: " + contas[i].getNumeroConta() + "\n";
                                contasOrdenadas += "Titular: " + contas[i].getNomeTitular() + "\n";
                                contasOrdenadas += "Saldo: " + contas[i].getSaldo() + "\n\n";
                            }

                            JOptionPane.showMessageDialog(null, contasOrdenadas);
                            break;

                        case 2:
                            // Ordenar as contas por saldo
                            for (int i = 0; i < numContas - 1; i++) {
                                int indiceMenor = i;

                                for (int j = i + 1; j < numContas; j++) {
                                    if (contas[j].getSaldo() < contas[indiceMenor].getSaldo()) {
                                        indiceMenor = j;
                                    }
                                }

                                if (indiceMenor != i) {
                                    ContaBancaria temp = contas[i];
                                    contas[i] = contas[indiceMenor];
                                    contas[indiceMenor] = temp;
                                }
                            }

                            //  as informações das contas ordenadas 
                            String contasOrdenadas1;
                            contasOrdenadas1 = "Contas ordenadas por saldo:\n";
                            for (int i = 0; i < numContas; i++) {
                                contasOrdenadas1 += "Número da conta: " + contas[i].getNumeroConta() + "\n";
                                contasOrdenadas1 += "Titular: " + contas[i].getNomeTitular() + "\n";
                                contasOrdenadas1 += "Saldo: " + contas[i].getSaldo() + "\n\n";
                            }

                            JOptionPane.showMessageDialog(null, contasOrdenadas1);
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida.");
                            break;
                    }
                    break;
                case 6:
                    double saldoTotal = calcularSaldoTotal(contas, numContas);
                    JOptionPane.showMessageDialog(null, "Saldo Total do Banco: " + saldoTotal);
                    break;
                case 7:
                    boolean saldoNegativo = verificarSaldoNegativo(contas, numContas);
                    if (!saldoNegativo) {
                        JOptionPane.showMessageDialog(null, "Nenhuma conta possui saldo negativo.");
                    }
                    break;
                case 8:
                    sair = true;
                    JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }

            JOptionPane.showMessageDialog(null, "Opção finalizada");

        }
    }

    public static double calcularSaldoTotal(ContaBancaria[] contas, int numContas) {
        
        if (numContas == 0) {
            return 0.0;
        } else {
            //  calcular o saldo total das contas restantes
            double saldoTotalRestante = calcularSaldoTotal(contas, numContas - 1);

            // Obter o saldo da conta atual
            double saldoContaAtual = contas[numContas - 1].getSaldo();

            // Somar o saldo da conta atual com o saldo total das contas restantes
            return saldoContaAtual + saldoTotalRestante;
        }
    }

    public static boolean verificarSaldoNegativo(ContaBancaria[] contas, int numContas) {
       
        if (numContas == 0) {
            return false;
        } else {
           
            boolean saldoNegativoRestante = verificarSaldoNegativo(contas, numContas - 1);

            // Verificar saldo da conta atual
            double saldoContaAtual = contas[numContas - 1].getSaldo();

            // Verificar se saldo é negativo
            if (saldoContaAtual < 0) {
                JOptionPane.showMessageDialog(null, "Conta " + contas[numContas - 1].getNumeroConta()
                        + " possui saldo negativo: " + saldoContaAtual);
                return true;
            }

            // Verificar saldo negativo nas contas restantes
            return saldoNegativoRestante;
        }
    }
}
