import java.io.*;
import java.util.*;

public class SimpleShell {

    public static void main(String[] args) throws IOException {
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ProcessBuilder pb = new ProcessBuilder();
        File startDir = new File(System.getProperty("user.dir"));
        pb.directory(startDir);

        ArrayList<List<String>> history = new ArrayList<>();

        // we break out with <control><C>
        while (true) {
            // read what the user entered
            System.out.print("jsh>");
            commandLine = console.readLine();

            // making Arraylist
            List<String> commandArr = Arrays.asList(commandLine.split(" "));

//            System.out.println(commandArr.getClass().getName());

            // commandArr append in history array
            history.add(commandArr);

            // if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;

            // if 'exit' or 'quit' entered, the shell output "Goodbye." and exits the program
            if (commandLine.equals("exit") || commandLine.equals("quit")){
                System.out.println("Goodbye.");
                System.exit(0);
            }

            try {

//                !! command
                if (commandLine.matches("!!")){
                    if(history.size() == 0){
                        System.out.println("event not found");
                        continue;
                    }
                    else{
                        String lastCmd = null;
                        for (String str : history.get(history.size()-2)){
                            lastCmd = str + " ";
                        }

                        commandArr = Arrays.asList(lastCmd.split(" "));
                        commandLine = lastCmd;
                        history.add(commandArr);


                    }
                }

                if(String.valueOf(commandLine.charAt(0)).matches("!")){
                    int idx = Character.getNumericValue(commandLine.charAt(1));
                    if(idx <= history.size()){
                        String idxCmd = null;
                        for (String str : history.get(idx)){
                            idxCmd = str + " ";
                        }

                        commandArr = Arrays.asList(idxCmd.split(" "));
                        commandLine = idxCmd;
                        history.add(commandArr);
                    }
                }


//              history command
                if(commandLine.matches("history")){
                    for (int i = 0; i<history.size(); i++){
                        System.out.print(i+" ");
                        for (int j = 0; j<history.get(i).size(); j++){
                            System.out.print(history.get(i).get(j)+" ");
                        }
                        System.out.println();
                    }
                    continue;
                }



//                cd command
                if (commandLine.contains("cd")){
                    if (commandLine.matches("cd") == true){
                        File home = new File(System.getProperty("user.home"));
                        System.out.println(home);
                        pb.directory(home);
                        continue;
                    }
                    else if (commandLine.matches("cd ..")){
                        File parentDir = new File(pb.directory().getParent());
                        System.out.println(parentDir);
                        pb.directory(parentDir);
                        continue;

                    }

                    else{
                        String dir = commandArr.get(1);
                        File newDir = new File(pb.directory() + File.separator + dir);

                        if (newDir.isDirectory()){
                            System.out.println(newDir);
                            pb.directory(newDir);
                            continue;
                        }
                        else{
                            System.out.println(dir + ": No such file or directory");
                            continue;
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


//            OSProcess Add

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
