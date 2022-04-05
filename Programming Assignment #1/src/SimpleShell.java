import java.io.*;
import java.util.*;

public class SimpleShell {
    public static void main(String[] args) throws IOException {
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ProcessBuilder pb = new ProcessBuilder();
        File startDir = new File(System.getProperty("user.dir"));


        // we break out with <control><C>
        while (true) {
            // read what the user entered
            System.out.print("jsh>");
            commandLine = console.readLine();

            // making Arraylist
            List<String> commandArr = Arrays.asList(commandLine.split(" "));

            // if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;

//            try {
//                if (commandLine.contains("cd")){
//                    if (commandLine.matches("cd") == true){
//                        File home = new File(System.getProperty("user.home"));
//                        System.out.println(home);
//                        pb.directory(home);
//                        continue;
//                    }
//                    else if (commandLine.matches("cd ..")){
//                        File parentDir = new File(pb.directory().getParent());
//                    }
//                    else{
//
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            pb.command(commandArr);

            Process process = pb.start();

            // obtain the input stream
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // read the output of the process
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);

            br.close();



            // args[0] is the command that is run in a separate process



//            for(String line : ProcessorBuilder){
//                System.out.println(line);
//            }

            /** The steps are:
             * (1) parse the input to obtain the command and any parameters
             * (2) create a ProcessBuilder object
             * (3) start the process
             * (4) obtain the output stream
             * (5) output the contents returned by the command
             */
        }
    }

}
