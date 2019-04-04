import java.util.Comparator;

public class MyLogData implements Comparable<MyLogData>{

	private static boolean sortType = true;		// true�� ip sort, false�� time sort , default�� ip��

	public String ip;
	public String time ;
	public String url;
	public String status;
	public static int size = 0;				// data�� ����

	public MyLogData(String line) {
		ip = line.split(",")[0];
		time = (line.split(",")[1]).substring(1);	// �տ� [�� ���ֹ���
		url = line.split(",")[2];
		status = line.split(",")[3];
		size++;
	}

	// time���� ����
	public static Comparator<MyLogData> timeComparator = new Comparator<MyLogData>() {
		
		public int compare(MyLogData data1, MyLogData data2) {
			int check = 0;				// ����� data1�� ��ũ��, ������ data2�� �� ũ��
			sortType = false;

			String time1 = data1.time;
			String time2 = data2.time;

			// 2018:12:43:16�� :�� ��ū�ؼ� 2018�� ����
			int year1 = Integer.parseInt( (time1.split("/")[2]).split(":")[0] );
			int year2 = Integer.parseInt( (time2.split("/")[2]).split(":")[0] );

			check = goCompare(year1, year2);
			if( check != 0)
				return check;

			int month1 = month(time1.split("/")[1]);
			int month2 = month(time2.split("/")[1]);

			check = goCompare(month1, month2);
			if( check != 0)
				return check;

			int day1 = Integer.parseInt(time1.split("/")[0]);
			int day2 = Integer.parseInt(time2.split("/")[0]);

			check = goCompare(day1, day2);
			if( check != 0)
				return check;

			// 2018:12:43:16�� 5��° �ε������� �߶� 12:43:16���� �߶� :�� ���� int�� ��ȯ��Ŵ
			int clock1 = Integer.parseInt( ( (time1.split("/")[2]).substring(5)).replace(":", "") );
			int clock2 = Integer.parseInt( ( (time2.split("/")[2]).substring(5)).replace(":", "") );

			check = goCompare(clock1, clock2);
			return check;
		}

		private int month(String month) {	// �Է¹��� ���� ���ڷ� ��ȯ

			switch (month) {
			case "Jan":
				return 1;
			case "Feb":
				return 2;
			case "Mar":
				return 3;
			case "Apr":
				return 4;
			case "May":
				return 5;
			case "Jun":
				return 6;
			case "Jul":
				return 7;
			case "Aug":
				return 8;
			case "Sep":
				return 9;
			case "Oct":
				return 10;
			case "Nov":
				return 11;
			case "Dec":
				return 12;
			default:
				return 0;
			}
		}
	};

	
	// ip�� ����
	public static Comparator<MyLogData> ipComparator = new Comparator<MyLogData>() {

		public int compare(MyLogData data1, MyLogData data2) {
			int check = 0;
			sortType = true;

			for(int i=0; i<4; i++) {
				check = compareIP(data1.ip, data2.ip, i);
				if(check!=0)
					break;
			}
			return check;
		}

		private int compareIP(String data1, String data2, int i) {
			int num1 = Integer.parseInt(data1.split("\\.")[i]);
			int num2 = Integer.parseInt(data2.split("\\.")[i]);

			return goCompare(num1, num2);
		}
	};

	
	
	public String toString() {	//sortType�� ���� ����ϴ� ������ �޶�����.
		if(sortType)
			return ( "IP: " + ip + "\nTime: " + time + "\nURL: " + url + "\nStatus: " + status + "\n" );
		else
			return ( "Time: " + time + "\nIP: " + ip + "\nURL: " + url + "\nStatus: " + status + "\n" );
	}

	
	@Override
	public int compareTo(MyLogData o) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int goCompare(int num1, int num2) {
		if(num1 > num2)
			return 1;
		else if (num1 < num2)
			return -1;
		else
			return 0;
	}
}
