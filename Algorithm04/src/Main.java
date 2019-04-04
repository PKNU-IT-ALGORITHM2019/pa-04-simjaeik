import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static Scanner kb;

	public static MyLogData [] logData = new MyLogData[16000];

	public static void main(String[] args) throws FileNotFoundException {
		// read, sort -s , print, exit
		kb = new Scanner(System.in);

		command(kb);

		kb.close();
	}

	private static void command(Scanner kb2) throws FileNotFoundException {

		while(true) {
			System.out.print("$ ");
			String commandLine = kb.nextLine();

			if(commandLine.split(" ")[0].equals("read")) {
				String read = commandLine.split(" ")[1];
				handleRead(read);
			}
			else if(commandLine.split(" ")[0].equals("sort")){
				String option = commandLine.split(" ")[1];
				handleSort(option);
			}
			else if(commandLine.equals("print")){
				handlePrint();
			}
			else if(commandLine.equals("exit")){
				break;
			}
		}
	}

	
	private static void handlePrint() {
		for(int i = 0; i < MyLogData.size - 2; i++ )
			System.out.println(logData[i].toString());
	}

	
	private static void handleSort(String option) {
		option = option.substring(1);

		if(option.equals("t"))
			Arrays.sort(logData, 0, MyLogData.size-2, MyLogData.timeComparator);
		else if (option.equals("ip"))
			Arrays.sort(logData, 0, MyLogData.size-2, MyLogData.ipComparator);
	}

	
	private static void handleRead(String read) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(read));
		String line = fileReader.nextLine();			//첫 문자열은 받지않는다.
		
		while (fileReader.hasNextLine()) {
			MyLogData newLog = new MyLogData(fileReader.nextLine());
			logData[MyLogData.size-1] = newLog;
		}
		fileReader.close();
	}

}