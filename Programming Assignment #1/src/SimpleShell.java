import java.io.*;
import java.util.*;

public class SimpleShell {

    public static void main(String[] args) throws IOException {
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ProcessBuilder pb = new ProcessBuilder();
        File startDir = new File(System.getProperty("user.dir"));
        pb.directory(startDir);
        String beforeCmd;

        ArrayList<List<String>> history = new ArrayList<>();

        // we break out with <control><C>
        while (true) {
            // read what the user entered
            System.out.print("jsh>");
            commandLine = console.readLine();

            // making Arraylist
            List<String> commandArr = Arrays.asList(commandLine.split(" "));

            if(!commandLine.contains("!")){
                if(history.size() == 0){
                    history.add(commandArr);
                }
                else{
                    beforeCmd = String.join(" ", history.get(history.size()-1));

                    if(!commandLine.equals(beforeCmd))
                        history.add(commandArr);
                }

            }


            // if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;

            // if 'exit' or 'quit' entered, the shell output "Goodbye." and exits the program
            if (commandLine.equals("exit") || commandLine.equals("quit")){
                System.out.println("Goodbye.");
                System.exit(0);
            }


//          !! command
            if (commandLine.matches("!!")){
                if(history.size() == 0){
                    System.out.println(commandLine + ": event not found");
                    continue;
                }
                else{
                    String lastCmd = String.join(" ", history.get(history.size()-1));

                    commandArr = Arrays.asList(lastCmd.split(" "));
                    commandLine = lastCmd;

                    beforeCmd = String.join(" ", history.get(history.size()-2));

                    if(!commandLine.equals(beforeCmd))
                        history.add(commandArr);

                }
            }

//          !# command
//          if enter !<#> -> history recorded the # of history command
            else if(String.valueOf(commandLine.charAt(0)).matches("!")){
                int idx = Character.getNumericValue(commandLine.charAt(1));
                if(idx < history.size()){
                    String idxCmd = String.join(" ", history.get(idx));

                    commandArr = Arrays.asList(idxCmd.split(" "));
                    commandLine = idxCmd;

                    beforeCmd = String.join(" ", history.get(history.size()-1));

                    if(!commandLine.equals(beforeCmd))
                        history.add(commandArr);

                }
                else{
                    System.out.println(commandLine+": event not found");
                    continue;
                }
            }

            try {
//              history command
                if(commandLine.matches("history")){

                    for (int i = 0; i<history.size(); i++){
                        System.out.println(i+" "+String.join(" ", history.get(i)));
                    }
                    continue;
                }


//                cd command
                else if (commandLine.contains("cd")){
                    String dir = commandArr.get(1);


                    if (commandLine.matches("cd") | dir.equals("~")){
                        File home = new File(System.getProperty("user.home"));
                        System.out.println(home);
                        pb.directory(home);
                        continue;
                    }

                    else if (dir.startsWith("~")){
                        File newDir = new File(System.getProperty("user.home")+File.separator+dir.replace("~/", ""));
                        pb.directory(newDir);
                        startDir = new File(dir);
                    }

                    else if (dir.equals("..")){
                        File parentDir = new File(pb.directory().getParent());
                        System.out.println(parentDir);
                        pb.directory(parentDir);
                        continue;
                    }

                    else if (dir.equals(".")){
                        System.out.println(startDir);
                        pb.directory(startDir);
                        continue;
                    }
                    else if (dir.startsWith("/")){
                        pb.directory(new File(dir));
                        startDir = new File(dir);
                    }
                    else if (dir.startsWith("./")){
                        if(dir.equals("./"))
                            continue;
                        else{
                            File newDir = new File(pb.directory() + File.separator + dir);
                            pb.directory(newDir);
                            startDir = new File(dir);
                        }
                    }

                    else{
                        File newDir = new File(pb.directory() + File.separator + dir);

                        if (newDir.isDirectory()){
                            System.out.println(newDir);
                            pb.directory(newDir);
                            startDir = new File(dir);
                            continue;
                        }
                        else{
                            System.out.println(dir + ": No such file or directory");
                            continue;
                        }

                    }
                }


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

            } catch (Exception e) {
                System.out.println(commandLine+ ": not found");
                continue;
            }


//            OSProcess Add
//            can run 'pwd, ls, ps, cat'



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
