
// The following shows the general form of a server that
// processes requests from different clients one-at-a-time.
// That is, it listens for a request from a client, processes
// that request, then looks for another client, and another,
// and another, etc.
//
// Note that the loop is an infinite loop ("while(true)"),
// so the program must be terminated manually (using Linux's
// CTRL-c command, for example).
//
// "..." below stands for omitted processing statements that
// would be resolved with specific processing statements,
// depending on the desired server behavior.
//
import java.util.*;
import java.net.*;
import java.io.*;
public class p2
{
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int port;

        List<String> par = new LinkedList<String>();
        int num_lines = 0;
        String operation;
        String outputLine;
        boolean finished;
        boolean listening = true; // assume serverSocket creation
        // was OK
        // get port # from command-line
        port = Integer.parseInt(args[0]);
        // try to create a server socket
        try
        {
            serverSocket = new ServerSocket(port);
             
        }
        catch(IOException e)
        {
            System.out.println(e);
            listening = false;
        }
        if(listening) // i.e., serverSocket successfully created
        {
            // continue to:
            //
            //   (1) Listen for a client request
            //   (2) Read data from the client
            //   (3) Process the request: do calculation and return value
            //
        while(true) // main processing loop
        {
        try
        {
            // Listen for a connection request from a client
            socket = serverSocket.accept();
            // Establish the input and output streams on the socket
            PrintWriter out = new
            PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(new
                    InputStreamReader(socket.getInputStream()));
            // Read data from the client, do calculation(s),
            // return data value(s)

            if (!(par.isEmpty())){
                par.clear();
            }
   
            operation = in.nextLine();
            System.out.println(operation);

            // If it's "quit," we're done
            if(operation.equalsIgnoreCase("quit")){
                finished = true;
            }    
            else{
//operations---------------------------------------------------------------------------------------
                if(operation.equalsIgnoreCase("split")){
                    //receive rest of data
                    num_lines = in.nextInt();
                    in.nextLine();//clears

                    for(int i = 0; i < num_lines; i++){
                        par.add(in.nextLine()); //without position #, it adds to the end of the list
                    }
                    
                    par = splitAndSwap(par);

                    out.println(par.size());
                    
                    for(int i = 0; i < par.size(); i++){
                        out.println(par.get(i));
                    }
                }
                            

                else if(operation.equalsIgnoreCase("cypher")){
                    //receive rest of data
                    num_lines = in.nextInt();
                    in.nextLine();//clears


                    for(int i = 1; i <= num_lines; i++){
                        par.add(in.nextLine()); //without position #, it adds to the end of the list
                    }
                    
                    par = isCaesar(par); //call method
                    
                    out.println(par.size());

                    for(int i = 0; i < par.size(); i++){
                        out.println(par.get(i));
                    }
                                
                }  
                            

                else if(operation.equalsIgnoreCase("isogram")){
                    //receive rest of data
                    num_lines = in.nextInt();
                    in.nextLine();//clears

                    for(int i = 1; i <= num_lines; i++){
                        par.add(in.nextLine()); //without position #, it adds to the end of the list
                    }
                                            
                    outputLine = long_isogram(par); //sends longest word
                    out.println(2); 
                    out.println("Longest isogram: " + outputLine);
                    out.println("Length: " + outputLine.length());

                } 
                else{
                    num_lines = in.nextInt();
                    in.nextLine();//clears

                    for(int i = 1; i <= num_lines; i++){
                        par.add(in.nextLine()); //without position #, it adds to the end of the list
                    }
                    
                    outputLine = "Error: unknown question";

                    out.println(1); 
                    out.println(outputLine);
                }
            }
                  
            // close connection to client
                out.close();
                in.close();
                socket.close();
                }
            catch(IOException e)
                {
                    System.out.println(e);
                }
            // end while (main processing loop)
        }//end while true 
    } // end if listening
} // end main

    //convert the original paragraph into a new paragraph where the original's first  half  is  swapped  with  its  second half. 
    public static List<String> splitAndSwap(List<String> par){
        List<String> StringList = new LinkedList<String>(); //new LinkedList
         int n = par.size(); //size
         int split = n % 2;//get remainder

         //even if
         if (split == 0){
             for (int i = 0; i < n/2; i++){
                StringList.add(par.get(n/2+i));
            }//end for loop

            for (int j = 0; j < n/2; j++){
                 StringList.add(par.get(j));
             
            }//end for loop
            return StringList;
         }// if even
        //odd if
         else if (split == 1){
            for (int k = 0; k < n/2; k++){
                StringList.add(par.get(n/2+k+1));
            }//end for loop
            StringList.add(par.get(n/2));
            for (int l = 0; l < n/2; l++){
                 StringList.add(par.get(l));
            }//end for loop
            return StringList;
         }// if odd
         else{
            for (int m = 0; m < n; m++){
                StringList.add(par.get(m));
            }//end for loop
            return StringList;
         }
            
    } // end splitAndSwap

    //applies a Caesar cipher with a left shift of 5
    public static List<String> isCaesar(List<String> par){
    List<String> cypher = new LinkedList<String>(); //new LinkedList
    
    String newWord = "";

    for (int i = 0; i < par.size(); i++){

        String word = par.get(i).toString();
        
        for(int j = 0; j < word.length(); j++){
            char ch = word.charAt(j);
            
            if(Character.isLetter(ch)){

                if(Character.isLowerCase(ch)){

                    char c = (char)(ch - 5); 

                    if(c < 'a'){
                        newWord = newWord + (char)(ch + (26-5));
                    }
                    else{
                        newWord = newWord + c;
                    }
                }
                else if(Character.isUpperCase(ch)){
                    char c = (char)(ch - 5); 
                
                    if(c < 'A'){
                        newWord = newWord + (char)(ch + (26-5));
                    }
                    else{
                        newWord = newWord + c;
                    }
                }   
            }
            else{
                newWord = newWord + ch; //adds punctuation
            }  
            
        }//end nested for loop
        System.out.println(newWord);
        cypher.add(newWord);
        newWord = "";
    }//end for loop
        System.out.println(cypher);
    
    return(cypher);
    
    } // end isCaesar

    //searches for a word in a paragraph that has no repeating letters
    public static String long_isogram(List<String> par){
        //Creating and populating LinkedList
    LinkedList<String> isograms = new LinkedList<String>();
    String largestisogram = "";
     //Converting LinkedList to Array
    String[] array = par.toArray(new String[par.size()]);
    String word = Arrays.toString(array).replaceAll("\\p{Punct}", "");
    String[] lowercase = word.split(" "); 
    
     //Displaying Array content

    for (int i = 0; i < lowercase.length; i++)
    {
       if (lowercase[i].chars().distinct().count() == lowercase[i].length()){
           isograms.add(lowercase[i]);
           
       }
    } 

    if (isograms.isEmpty()){
        return "None";
    }
    else{
         //check for longest isogram
        largestisogram = isograms.stream().max(Comparator.comparingInt(String::length)).get();
        return(largestisogram);
    }
                 
                

    } // end long_isogram

} // end p2