public class Aluno extends Usuario {
    private double saldo;

    //Construtor do aluno
    public Aluno(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
        this.saldo = 0;
    }



    //Exibição do aluno
    public String toString() {
        return super.toString() + " (Saldo: R$" + String.format("%.2f", this.saldo) + ")";
    }

    //Inserir valor na conta do usuário
    public void inserirSaldo(double valor){
        this.saldo += valor;
        System.out.println("Saldo após inserir valor: R$" + this.saldo);
    }

    //Retirar valor no saldo do usuário após compra
    public boolean retirarSaldo(double valor) {
        //Verificar se há saldo suficiente
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Saldo atualizado: R$:" + this.saldo);
            return true;
        }
        else {
            System.out.println("Saldo insuficiente.");
            return false;
        }
    }

    //Retornar saldo do aluno
    double getSaldo(){
        return this.saldo;
    }
}
