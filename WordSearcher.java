//Made by Adam Rood v1.1
//solved caps lock issues
import java.util.*;
import java.io.*;
import java.text.NumberFormat;

public class WordSearcher{
	public static void main (String[] args) throws IOException{
		int gw = 0, gh = 0, count;
		double numberofwords = 0, found = 0; //VARIABLES!!!!
		boolean flag = true, bad = false;
		while (flag == true){ //loop
			Scanner input = new Scanner(System.in);
			System.out.println("\n--------------------------------------------------------------------------------WordSearcher v1.1 - Adam Rood\n--------------------------------------------------------------------------------");
			System.out.print("Available wordsearches:\n-----------------------\n"); //title stuff
			File directory = new File("c:\\Program Files\\Word Searcher\\Word Searches"); //entire directory
			File[] files = directory.listFiles(); //files available to be searched
			for (int index = 0; index < files.length; index++){ //filters out .txt at the end
			   System.out.println(files[index].toString().substring(45, files[index].toString().length() - 4));}
			System.out.print("\nWhich wordsearch would you like the solution to? ");
			String filename = input.nextLine();
			filename = filename.toLowerCase(); //caps lock fix
			filename = "Word Searches\\" + filename + ".txt"; //rewording file name for folder and format
			boolean works = false;
			while (works == false){
				works = true;
				try{
					Scanner tester = new Scanner(new File(filename));}
				catch (Exception e){
					System.out.print("\n--------------------------------------------------------------------------------");
					System.out.print(">ERROR: File not found. Please make sure you entered in the filename correctly.<");
					System.out.print("--------------------------------------------------------------------------------");
					System.out.print("What is the name of the file to be searched? ");
					filename = input.nextLine();
					filename = "Word Searches\\" + filename + ".txt";
					works = false;}} //check for requested file
			works = false;
			System.out.println("\n");
			Scanner scan = new Scanner(new File(filename)); //read file
			ArrayList<String> alFile = new ArrayList<String>();
			gw = scan.nextInt(); //gw and gh should've been switched... gw = grid height
			while(scan.hasNext()){ //shove grid and words to find into an arraylist
				alFile.add(scan.next());}
			String[] words = new String[alFile.size()]; //arrays are easier to use
			alFile.toArray(words); //convert al > a
			gh = words[0].length(); //gw and gh should've been switched... gh = grid width
			numberofwords = alFile.size() - gw; //# of words to find
			String letters[][] =  new String[gw + 2][gh + 2]; //set up grid w/ 2 extra rows and columns
			for (int x = 0; x < gw; x++){ //prints row #'s
				if (x < 9)
					System.out.print((x + 1) + "  - ");
				else
					System.out.print((x + 1) + " - ");
				for (int y = 0; y < gh; y++){ //prints letters to go w/ rows
					letters[x + 1][y + 1] = words[x].substring(y, y + 1);
					System.out.print(letters[x + 1][y + 1]);}
				System.out.println();}
			System.out.print("\n********************************************************************************");
			for (int a = 0; a < gh + 2; a++){ //gives grid boundaries
				letters[0][a] = "#";
				letters[gw + 1][a] = "#";}
			for (int a = 0; a < gw + 2; a++){
				letters[a][0] = "#";
				letters[a][gh + 1] = "#";}
			System.out.println();
			String searcher = "";
			boolean hitt = false;
			found = numberofwords; //for accuracy comparison later
			for (int z = 0; z < numberofwords; z++){ //search row by row for 1st letter hit
				searcher = words[z + gw];
				hitt = Check(letters, searcher, gw, gh); //1st letter = word to find?
				if (hitt == false){
					found--; //didn't find =(
					System.out.println(searcher + " does not exist in the given grid.");}} //because f u, thts y =P
			NumberFormat perc = NumberFormat.getPercentInstance();
			System.out.print("\n********************************************************************************");
			System.out.println((int)found + "/" + (int)numberofwords + " = " + perc.format(found / numberofwords) + " of words found\n");
			if (found / numberofwords < 1.0){ //didn't find'm all? tell the user they suck
				System.out.print("--------------------------------------------------------------------------------");
				System.out.print(">ERROR: All words not found, make sure the grid and words are entered correctly<");
				System.out.print("--------------------------------------------------------------------------------\n");}
			String ques = "";
			while (works == false){ //need anything else u lzsob?
				System.out.print("\nWould you like to solve another word search (y/n)? ");
				ques = input.nextLine();
				ques = ques.toLowerCase(); //caps lock catcher
				if (ques.equals("y")){
					flag = true;
					works = true;} //keep going
				else if (ques.equals("n")){
					flag = false;
					works = true; //stop
					System.out.println("\nGoodbye!\n");} //l8tr bro
				else{ //HOW HARD IS IT TO ENTER Y OR N??????
					System.out.print("\n--------------------------------------------------------------------------------");
					System.out.print("------------------------->ERROR: Enter y or n<----------------------------------");
					System.out.print("--------------------------------------------------------------------------------");}}}}

	static boolean Check(String scrambled[][], String wordtofind, int w, int h){ //check subprogram runs through and decides whether main found a word or not
		boolean hit = false; //haven't found one yet
		int numberofwords = 0;
		numberofwords++; //total # of words, not sure why i'm keeping track of it here
		for (int x = 1; x < w + 1; x++){ //again, x & y are switched...
			for (int y = 1; y < h + 1; y++){
				if (scrambled[x][y].equals(wordtofind.substring(0, 1))){
					if (scrambled[x][y + 1].equals(wordtofind.substring(1, 2))){ //checks right
						for (int a = 2; a < wordtofind.length(); a++){ //check program runs based off first letter
							if (a < wordtofind.length() - 1){ //then scans the 8 letters surrounding it for the 2nd letter in the
								if (!scrambled[x][y + a].equals(wordtofind.substring(a, a + 1))){ //word, if it finds the 2nd letter
									a = wordtofind.length();}} //then it directly compares the rest of the letters in that direction
							if (a == wordtofind.length() - 1){ //with the rest of the letters in the word
								if (!scrambled[x][y + a].equals(wordtofind.substring(a))){
									a = wordtofind.length();}
								else if (scrambled[x][y + a].equals(wordtofind.substring(a))){
									System.out.println(wordtofind + " starts at row " + x + ", column " + y + " and goes right.");
									hit = true;}}}}
					if (scrambled[x + 1][y + 1].equals(wordtofind.substring(1, 2))){ //checks lower right
						for (int a = 2; a < wordtofind.length(); a++){
							if (a < wordtofind.length() - 1){
								if (!scrambled[x + a][y + a].equals(wordtofind.substring(a, a + 1))){
									a = wordtofind.length();}}
							if (a == wordtofind.length() - 1){
								if (!scrambled[x + a][y + a].equals(wordtofind.substring(a))){
									a = wordtofind.length();}
								else if (scrambled[x + a][y + a].equals(wordtofind.substring(a))){
									System.out.println(wordtofind + " starts at row " + x + ", column " + y + "  and goes down and right.");
									hit = true;}}}}
					if (scrambled[x + 1][y].equals(wordtofind.substring(1, 2))){ //checks down
						for (int a = 2; a < wordtofind.length(); a++){
							if (a < wordtofind.length() - 1){
								if (!scrambled[x + a][y].equals(wordtofind.substring(a, a + 1))){
									a = wordtofind.length();}}
							if (a == wordtofind.length() - 1){
								if (!scrambled[x + a][y].equals(wordtofind.substring(a))){
									a = wordtofind.length();}
								else if (scrambled[x + a][y].equals(wordtofind.substring(a))){
									System.out.println(wordtofind + " starts at row " + x + ", column " + y + " and goes down.");
									hit = true;}}}}
					if (scrambled[x - 1][y + 1].equals(wordtofind.substring(1, 2))){ //checks lower left
						for (int a = 2; a < wordtofind.length(); a++){
							if (a < wordtofind.length() - 1){
								if (!scrambled[x - a][y + a].equals(wordtofind.substring(a, a + 1))){
									a = wordtofind.length();}}
							if (a == wordtofind.length() - 1){
								if (!scrambled[x - a][y + a].equals(wordtofind.substring(a))){
									a = wordtofind.length();}
								else if (scrambled[x - a][y + a].equals(wordtofind.substring(a))){
									System.out.println(wordtofind + " starts at row " + x + ", column " + y + " and goes up and right.");
									hit = true;}}}}
					if (scrambled[x - 1][y].equals(wordtofind.substring(1, 2))){ //checks up
						for (int a = 2; a < wordtofind.length(); a++){
							if (a < wordtofind.length() - 1){
								if (!scrambled[x - a][y].equals(wordtofind.substring(a, a + 1))){
									a = wordtofind.length();}}
							if (a == wordtofind.length() - 1){
								if (!scrambled[x - a][y].equals(wordtofind.substring(a))){
									a = wordtofind.length();}
								else if (scrambled[x - a][y].equals(wordtofind.substring(a))){
									System.out.println(wordtofind + " starts at row " + x + ", column " + y + " and goes up.");
									hit = true;}}}}
					if (scrambled[x - 1][y - 1].equals(wordtofind.substring(1, 2))){ //checks upper left
						for (int a = 2; a < wordtofind.length(); a++){
							if (a < wordtofind.length() - 1){
								if (!scrambled[x - a][y - a].equals(wordtofind.substring(a, a + 1))){
									a = wordtofind.length();}}
							if (a == wordtofind.length() - 1){
								if (!scrambled[x - a][y - a].equals(wordtofind.substring(a))){
									a = wordtofind.length();}
								else if (scrambled[x - a][y - a].equals(wordtofind.substring(a))){
									System.out.println(wordtofind + " starts at row " + x + ", column " + y + " and goes up and left.");
									hit = true;}}}}
					if (scrambled[x][y - 1].equals(wordtofind.substring(1, 2))){ //checks left
						for (int a = 2; a < wordtofind.length(); a++){
							if (a < wordtofind.length() - 1){
								if (!scrambled[x][y - a].equals(wordtofind.substring(a, a + 1))){
									a = wordtofind.length();}}
							if (a == wordtofind.length() - 1){
								if (!scrambled[x][y - a].equals(wordtofind.substring(a))){
									a = wordtofind.length();}
								else if (scrambled[x][y - a].equals(wordtofind.substring(a))){
									System.out.println(wordtofind + " starts at row " + x + ", column " + y + " and goes left.");
									hit = true;}}}}
					if (scrambled[x + 1][y - 1].equals(wordtofind.substring(1, 2))){ //checks upper right
						for (int a = 2; a < wordtofind.length(); a++){
							if (a < wordtofind.length() - 1){
								if (!scrambled[x + a][y - a].equals(wordtofind.substring(a, a + 1))){
									a = wordtofind.length();}}
							if (a == wordtofind.length() - 1){
								if (!scrambled[x + a][y - a].equals(wordtofind.substring(a))){
									a = wordtofind.length();}
								else if (scrambled[x + a][y - a].equals(wordtofind.substring(a))){
									System.out.println(wordtofind + " starts at row " + x + ", column " + y + " and goes down and left.");
									hit = true;}}}}}}}
		return hit;}} //obvious