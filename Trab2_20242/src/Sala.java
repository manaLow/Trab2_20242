public class Sala {
    private String bloco;
    private String sala;
    private String andar;

    Sala(String bloco, String sala, String andar) {
        this.bloco = bloco;
        this.sala = sala;
        this.andar = andar;
    }

    public String toString() {
        return bloco + sala + andar;
    }
}
