import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private String cod;
    private Aluno cliente, entregador;
    private Sala s;
    private Item[] carrinho;
    private boolean entregue;

    //Construtor da classe Pedido
    public Pedido(String cod, Aluno cliente){
        this.cod = cod;
        this.cliente = cliente;
        this.s = null;
        this.carrinho = new Item[0];
        this.entregue = false;
    }

    public void setS(Sala s) {
        this.s = s;
    }

    public Sala getS() {
        return s;
    }

    //Exibição do Pedido - EX: "001 - Entregador: João - Cliente: Manuzinha - Sala: 904T - Itens: Coxinha, Refrigerante - Finalizado: True
    public String toString(){
        return this.cod + " - Entregador: " + this.entregador + " - Cliente: " + this.cliente + " - Sala: " + this.s + " - Itens: " + this.carrinho + " - Finalizado: " + this.entregue;
    }

    //Atribuir aluno a função de entregador
    public void atribuirEntregador(Aluno aluno){
        this.entregador = aluno;
    }

    //Verificar se o pedido foi entregue
    public boolean isEntregue() {return this.entregue;}

    //Retornar o cliente do pedido
    public Aluno getCliente(){
        return this.cliente;
    }

    public void adicionarItem(Item novoItem) {
        // Cria um novo array com tamanho maior
        Item[] novoCarrinho = new Item[carrinho.length + 1];

        // Copia os itens antigos para o novo array
        System.arraycopy(carrinho, 0, novoCarrinho, 0, carrinho.length);

        // Adiciona o novo item ao final
        novoCarrinho[carrinho.length] = novoItem;

        // Atualiza a referência do carrinho
        carrinho = novoCarrinho;
    }

    //Confirmar a entrega do pedido
    public void marcarComoEntregue(){this.entregue = true;}

    //Verificar a disponibilidade do pedido para entrega
    public boolean disponivel(){
        return this.entregador == null;
    }

    public double valorTotal(){
        double valorPedido = 0;
        for(Item item : carrinho){
            valorPedido += item.valorTotal();
        }
        return valorPedido;
    }

    //Atualizar o saldo e o estoque do produto adquirido
    public void confirmar(){
        for(Item item: carrinho){
            item.getP().retirarDeEstoque(item.getQuantidade());
        }
        cliente.retirarSaldo(this.valorTotal());
        // output da confirmação
    }

    public String getCod(){
        return cod;
    }

}

