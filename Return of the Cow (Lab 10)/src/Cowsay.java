public class Cowsay {
	public static void main (String[] args){
		Cow[] cows = HeiferGenerator.getCows();//Uses the HeiferGenerator to create list of Cows
		Cow[] fileCows = HeiferGenerator.getFileCows();
		boolean msgChk = false;
		int cowPos = -1;
		if (args[0].equals("-l")){//If the first argument is -l, it returns the list of cows
			System.out.print("Regular cows available: ");
			for (int k = 0; k < cows.length; k++){
				System.out.print(cows[k].getName() + " ");
			}
			System.out.println();
			System.out.print("File cows available: ");
			for (int j = 0; j < fileCows.length; j++){
				System.out.print(fileCows[j].getName() + " ");
			}
			System.out.println();
		}
		else if (args[0].equals("-n")){//If the first argument is -n, it attempts to use the given cow
			for (int i = 0; i < cows.length;i++){//For loop looks for the cow from the arguments.
				if(args[1].equals(cows[i].getName())){
					msgChk = true;
					cowPos = i;
				}
			}
			if (msgChk){//If the cow is found using the for loop, it returns an image and message.
				for(int j = 2;j<args.length;j++){
					System.out.print(args[j] + " ");
				}
				System.out.println("\n"+ cows[cowPos].getImage());
			}
			else//If the cow is not found, it returns a failure message.
				System.out.println("Could not find " + args[1] + " cow!");
		}
		else if (args[0].equals("-f")){//If the first argument is -f, it attempts to use the given fileCow
			for(int w = 0; w < fileCows.length; w++){
				if(args[1].equals(fileCows[w].getName())){
					msgChk = true;
					cowPos = w;
				}
			}//The code in -f is similar to that of -n, however, it uses the fileCows array instead of the cows array.
			if (msgChk){
				for (int v = 2;v<args.length;v++){
					System.out.print(args[v] + " ");
				}
				System.out.println("\n" + fileCows[cowPos].getImage());
			}
			else
				System.out.println("Could not find " + args[1] + " cow!");
		}
		else{//If neither -l or -n were used, it displays the default cow with the given message.
			for (int l = 0; l< args.length; l++){
				System.out.print(args[l] + " ");
			}
			System.out.println("\n" + cows[0].getImage());
		}
	}
}
