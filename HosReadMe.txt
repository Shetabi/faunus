1)download and install git from git-scm.com (we have already downloaded Git-2.42.0.2-64-bit into D:\Tools\Git) and installed with defaults

2)Create local working directiry for local repository. in this case D:\_DevelopDeskTop\ProjectWorks\Faunus

3) If you heven'n created remote repository-> goto github.com->signup and create your teams repository

4) If anyone of your team's member created team's repository-> navigate to url
	*- in our shared fanus project url is :https://github.com/Shetabi/faunus.git
	*- in github your shared project you can click on code button and copy url under Https Tab


3) RClick on local repository (project working space) and select Open GitBash Here from context menu

6) execute git clone command on gitbash cmd
$ git clone https://github.com/Shetabi/faunus.git

$ ls to see repository content

7) if you added new files in master repository (github) you should commit them

8) for get master repository just added files to local repository (local working space) you should execute git pull
$ git pull
$ clear fro clearing screen

9) to see content of file use cat command
$ cat filename.ext

10) for send local just added file use git add command
$ git add filename.ext

11) to see status of files on local use git status
$ git status

12) for identify your identity use git config command
$ git config --global user.email "ghosein.shetabivash@gmail.com"  or
$ git config --global user.name ""

13) for commiting just added file to master rep use git commit command
$ git commit -m "Message"

14)for sending changed files from staging area to repository use push command
$ git push <rep> <branch>   <for shared project origin main

14) for adding node files to faunus project execute npm inastall in npm cmd
cmd> cd D:\_DevelopDeskTop\ProjectWorks\Faunus\faunus\faunus-ui
cmd>npm install

15) for building project download anf install make for windows
cmd> make <as guide>


$ git add faunus-ui/HosReadMe.txt



 make a new branch
 git checkout -b feature/t2

store changes in stack
git stash


get latest changes from branch main
git pull origin main

be carefull using
git fetch origin main


inteligAdea Terminal> make run
