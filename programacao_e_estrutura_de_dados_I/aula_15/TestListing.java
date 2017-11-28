public class TestListing {
    public static void main(String args[]) {
        Listing listing = new Listing("/home/augusto/Documentos/aula/programacao_e_estrutura_de_dados_I/");
        listing.listByDate();
        listing.listBySize();
    }
}