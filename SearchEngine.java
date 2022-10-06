import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> lst1 = new ArrayList<>();

    public String handleRequest(URI url) {
        
        if (url.getPath().equals("/")) {
            return String.format("List of String: %s", lst1);
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    lst1.add(parameters[1]);
                    return String.format("List of Strings added %s! It's now %s", parameters[1], lst1);
                }
            }
            else if(url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    ArrayList<String> ex = new ArrayList<>();
                    for (int i = 0; i < lst1.size(); i++) {
                        if (lst1.get(i).contains(parameters[1])) {
                            ex.add(lst1.get(i));
                        }
                    }
                    return String.format("Search words: %s", ex);
                
                }
            }
            
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
