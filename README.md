# 2022_OSD_ProgrammingAssignment1
***
Subject: Operatign Systems Design  
Prof: Youn-Ho Lee    
Date: 2022.04.06.  
Student No.: 20102117  
Name: Lee Jeonghyeon    
***

The following command can run:
- __pwd__: an abbreviation of ‘print working directory’
- __ls__: to list their contents
- __ps__: a utility called ps for viewing information related with the processes on a system which stands as abbreviation for “Process Status”
- __cat__: short for “concatenate“
- __exit__ or __quit__: exit and quit command in linux is used to exit the shell where it is currently running
- __cd__: an abbreviation for ‘change directory’
    - __cd__ or __cd ~__: a quick shortcut to get back to your home directory
    - __cd .__: change into the current directory or, in other words, the command will do nothing
    - __cd ..__: To go up to the parent directory, in this case back to “/”, use the special syntax of two dots (..) when changing directory 
    - __cd '_Relative Path_'__: That is, the place you end up at depends on your current working directory
    - __cd '_Absolute Path_'__: No matter what your current working directory is, they’ll have the same effect
    - __cd '_Not Exist Directory_'__: print out no such file or directory
- __history__: keeps a list of all the other commands that have been run from that terminal session, then allows you to replay or reuse those commands instead of retyping them
- __!!__: execute the previous command
- __!#(_number_)__: execute the nth command from the history

Reference: <https://ubuntu.com/tutorials/command-line-for-beginners#1-overview>

---
## Requirements
I'm using Java version 11.

---
## Running the SimpleShell From the Command Line

To compile the SimpleShell, open your terminal and type

    $ javac SimpleShell.java

To run the generated class file, use

    $ java SimpleShell


## Usage
### pwd
To check the location of the current directory, use

    $pwd


### ls
To list their contents of current directory, use

    $ls


### ps
To print out process status, use

    $ps


### cat
To Concatenate more than one file and output, use

    $cat <file>

### exit or quit
To shut down the shell, use

    $exit

or

    $quit


### cd
#### cd or cd ~
To get back to your home directory, use

    $cd

or

    $cd ~


#### cd .
To get back to your home directory, use

    $cd


#### cd ..
To go up to the parent directory, use

    $cd ..

#### cd _Relative Path_
To get back to your home directory, use

    $cd <Relative Path>

For example,

    $cd Project1

then, your current directory /home/user/Project1

#### cd _Absolute Path_
To get back to your home directory, use

    $cd <Absolute Path>

For example,

    $cd /home

then, you can go to home directory

#### cd _Not exist Directory_
To get back to your home directory, use

    $cd <Not Exsit Directory>

then the shell printout

    $<Not Exsit Directory>: No such file or directory

### history
To print out a list of all the other commands that have been run from that terminal session, use

    $history


### !!
To execute the previous command, use

    $!!

### !#
To execute the nth command from the history, use

    $!<number>

### Error
If you enter a command that cannot be executed, the shell print out

    <cannot execute command>: not found

or

    <cannot execute command>: event not found