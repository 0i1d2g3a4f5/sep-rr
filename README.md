# "Robo Rally" by Desperate Drosseln

## Description
This projects goal is to entail a fully functional digital version of the popular board game Robo Rally.
If you're a first-timer, please find the rules attached: https://media.wizards.com/2017/rules/roborally_rules.pdf
<br/>For further documentation regarding the creation process feel free to view our Wiki :)

## Installation

1. When prompted allow Maven structure to be added
2. Edit configurations:
   1. For the server application: Add application > Set main class as ServerApplication > Add VM Options > Link your local file path to the JavaFX lib. 
   2. Repeat for client application with ClientApplication.
3. Add the JavaFX library: File > Project Structure > Libraries > + > Java > Select the JavaFX lib file path

## Start the Game

1. Launch the server: Run desperate_drosseln_hp > src > main > java > server_application > ServerApplication.java
2. Open a client: Run desperate_drosseln_hp > src > main > java > client_application > ClientApplication.java. Repeat for amount needed.
3. Choose whether to play a basic or advanced game by choosing the respective game. Follow the on-screen instructions to start a game.
4. Have fun!

## Authors and Acknowledgement
This project was created by Vivian Kafadar, Sarp Erdogan, Mark Ringer, Isabel Muhm and Qinyi Hui for the Software Engineering Practical Course at LMU Munich rooted in the BSc Computer Science degree programme. Supervised by Nicolas Brauner in the Summer Semester 2022.

## Project Status
In progress.
<br/>Future Plans: Allow easier installation and start of game by assembling .jar-files.