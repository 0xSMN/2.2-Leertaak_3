
import fileController.FileController;
import server.Server;

public class Main {
    public static void main(String[] args) {

        new Server();
        String temp = "INSERT 1,2019-01-02,24.3,100,2,3,4,5,6,7,8,9,1 " +
                      "INSERT 2,2019-01-01,22.3,200,2,3,4,5,6,7,8,9,1 ";
        FileController.incomingMessage(temp);
        
    }
}
