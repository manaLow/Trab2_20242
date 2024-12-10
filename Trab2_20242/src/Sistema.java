import java.util.ArrayList;

public class Sistema {
    //Listas cadastradas no Sistema
    ArrayList<Aluno> alunos;
    ArrayList<Admin> adms;
    ArrayList<Produto> prods;
    ArrayList<Pedido> pedidos;
    ArrayList<Sala> salas;


    //Inicializa as listas
    public Sistema() {
        this.alunos = new ArrayList<>();
        this.adms = new ArrayList<>();
        this.prods = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.salas = new ArrayList<>();

    }

    //Adicionar objetos à ArrayList
    public void addAdmin(Admin a) {
        this.adms.add(a);
    }
    public void addAluno(Aluno al) { this.alunos.add(al); }
    public void addProd(Produto prod) { this.prods.add(prod); }
    public void addPedido(Pedido ped) { this.pedidos.add(ped); }
    public void addSala(Sala sal) { this.salas.add(sal); }

    //Encontrar aluno por meio do CPF
    public Aluno getAluno(String cpf) {
        for(Aluno a : this.alunos) {
            if (cpf.equals(a.getCPF())) return a;
        }

        return null;
    }

    //Encontrar admin por meio do CPF
    public Admin getAdmin(String cpf) {
        for(Admin a : this.adms) {
            if (cpf.equals(a.getCPF())) return a;
        }

        return null;
    }

    //Encontrar produto por meio do código
    public Produto getProduto(String cod) {
        for(Produto prod : this.prods) {
            if (cod.equals(prod.getCodigo())) return prod;
        }

        return null;
    }

    //Procura e retorna a sala com respectivo bloco, sala e andar
    public Sala getSala(Sala sal) {
        for(Sala s : this.salas) {
            if (sal.equals(s)) return s;
        }

        return null;
    }

    //Verificar se há administradores registrados no sistema
    public boolean sistemaVazio() {
        return this.adms.size() == 0;
    }

    //Gerar código dos produtos
    public String gerarCodigoProduto() {
        //Incrementar 1 ao número de produtos existentes
        int novoCodigo = this.prods.size() + 1;
        return "PROD-" + novoCodigo;
    }

    //Criação de código do pedido
    public String gerarCodigoPedido() {
        //Verificar a quantidade de pedidos
        int QuantidadeDePedidos = this.pedidos.size();
        //Incrementar 1 a quantidade de pedidos
        int novoPedido = QuantidadeDePedidos + 1;
        return "PEDIDO-" + novoPedido;
    }

    //Exibir produtos registrados
    public void listarProdutos(){
        if(this.prods.isEmpty()){
            System.out.println("Não há produtos registrados.");
            return;
        }
        System.out.println("\n **Lista de Produtos:** \n");
        for(Produto p: this.prods){
            System.out.println(p.toString());
        }
    }

    //Exibir salas registradas
    public void listarSalas(){
        if(this.salas.isEmpty()){
            System.out.println("Não há salas registradas.");
            return;
        }
        System.out.println("\n **Lista de Salas:** \n");
        for(Sala sal: this.salas){
            System.out.println(sal.toString());
        }
    }

    //Apenas os pedidos que estiverem disponíveis
    public Pedido[] filtrarPedidos(boolean disponivel){

        //Criar ArrayList
        ArrayList<Pedido> pedidosDisponiveis = new ArrayList<>();

        //Verificar a disponibilidade dos pedidos e adicioná-las à ArrayList
        for(Pedido pedido : this.pedidos){
            if(!pedido.isEntregue()){
                pedidosDisponiveis.add(pedido);
            }
        }

        return pedidosDisponiveis.toArray(new Pedido[0]);
    }

    //Pedidos feito pelo aluno
    public Pedido[] filtrarPedidos(Aluno a){
        ArrayList<Pedido> pedidosAluno = new ArrayList<>();

        //Verificar quais pedidos pertencem ao aluno e adicioná-los
        for (Pedido pedido : this.pedidos){
            if(pedido.getCliente().equals(a)){
                pedidosAluno.add(pedido);
            }
        }
        return pedidosAluno.toArray(new Pedido[0]);
    }
}
