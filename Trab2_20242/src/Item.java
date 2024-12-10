public class Item {
    private Produto p;
    private int quantidade;

    //Retornar quantidade de itens
    public int getQuantidade() {return quantidade;}

    //Retornar respectivo produto
    public Produto getP() {return p;}

    Item(int quantidade, Produto p) {
        this.quantidade = quantidade;
        this.p = p;
    }
    @Override
    //Exibição de itens
    public String toString() {
        return (this.p.getNome() + " (QTD: " + this.quantidade + ")");

    }

    //Valor total dos itens
    public double valorTotal(){
        return (this.p.getValor())*quantidade;
    }
}
