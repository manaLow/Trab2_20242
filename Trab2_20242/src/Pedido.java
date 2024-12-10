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


//Código do Pedido: PEDIDO-1
//Produtos:
//PROD-1: Coxinha de Frango com Catupiry (QTD: 2)
//PROD-2: Suco de Laranja 300ml (QTD: 3)
//PROD-3: Empadão de Camarão (QTD: 1)
//Status: Em aberto
//Valor total: R$39,00
    public String toString(){
        System.out.println("Pedido do(a) " + getCliente().toString());
        String msg = "Código do Pedido: " + this.cod + "\n" +
                "Produtos:\n";
            for (Item i : carrinho) {
                msg += i.toString() + "\n";
            }
            msg += "Status: ";
            if(entregue){
                msg += "Entregue\n";
            }
            else {
                msg += "Em aberto\n";
            }
            msg += "Valor Total: R$" + String.format("%.2f",this.valorTotal()) + "\n";
            return msg;
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
        double valorPedido = 1; //Taxa de entrega
        for(Item item : carrinho){
            valorPedido += item.valorTotal();
        }
        return valorPedido;
    }

    //Atualizar o saldo e o estoque do produto adquirido
    public void confirmar(){

        if(cliente.retirarSaldo(this.valorTotal())) {

            for (Item item : carrinho) {
                item.getP().retirarDeEstoque(item.getQuantidade());
            }
        }
        // output da confirmação
    }

    public String getCod(){
        return cod;
    }

}

