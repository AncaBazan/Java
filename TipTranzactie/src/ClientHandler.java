import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class ClientHandler extends Thread {
    private Socket client;
    private List<Tranzactie> listaTranzactie;

    public ClientHandler(Socket client, List<Tranzactie> listaTranzactie) {
        this.client = client;
        this.listaTranzactie = listaTranzactie;
    }

    @Override
    public void run() {
        try(var s = client.getInputStream()) {
            var output = client.getOutputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter printWriter=new PrintWriter(client.getOutputStream(),true);

            String codProdus = bufferedReader.readLine();
            int codParsat = Integer.parseInt(codProdus);
            if(!listaTranzactie.stream().collect(Collectors.groupingBy(Tranzactie::getCod_produs)).containsKey(codParsat)){
                output.write(codParsat);
            }
            else{
                var sb = new StringBuilder();
                for(Tranzactie t: listaTranzactie){
                    if(t.getCod_produs()==codParsat){
                        sb.append(t).append("\n");
                    }
                    output.write(sb.toString().getBytes(StandardCharsets.UTF_8));
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
