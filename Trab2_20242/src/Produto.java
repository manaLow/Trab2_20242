public class Produto {
    private String codigo, nome;
    private int qtd;
    private double valor;

    //Construtor do produto
    public Produto(String codigo, String nome, int qtd, double valor){
        this.codigo = codigo;
        this.nome = nome;
        this.qtd = qtd;
        this.valor = valor;
    }

    //Retornar código do produto
    public String getCodigo() {return codigo; }

    //Retornar valor do produto
    public double getValor() {
        return valor;
    }

    //Retornar nome do produto
    public String getNome() {
        return nome;
    }

    public int getQtd() {return qtd;}

    //Exibição do produto EX: "213 - Coxinha".
    public String toString(){
        return this.codigo + ": " + this.nome;
    }

    //Retirar produtos do estoque
    public void retirarDeEstoque (int qtd){
        //Verificar disponibilidade de produtos no estoque
        if (this.qtd <= 0){
            System.out.println("Estoque vazio.");
        }
        else if (qtd > this.qtd){
            System.out.println("Quantidade indisponível. Estoque atual: " + this.qtd);
        }
        else {
            this.qtd -= qtd;
            System.out.println("Quantidade retirada com sucesso. Estoque restante: " + this.qtd);
        }
    }
}
