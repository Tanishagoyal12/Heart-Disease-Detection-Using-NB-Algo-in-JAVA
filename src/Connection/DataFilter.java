package Connection;
import java.util.*;
import java.sql.*;
import java.util.HashMap;	
class patient
{
int id,age, gender, cholesterol, trest_bp, chest_pain, blood_sugar, rest_ecg, thalach, ps,exang, thal, bmi=0, heart_attack,value;
float height, weight;
patient() throws SQLException{
System.out.println("Enter Patient ID");
Scanner sc=new Scanner(System.in);
id=sc.nextInt();
System.out.println("Enter Patient Age");
age=sc.nextInt();
if(age>0 && age<=20)
				{
					if(thalach>170)
						thalach = 1;
					else if(thalach<100)
						thalach = 0;
					else
						thalach = 2;
				}
				else if(age>20 && age<=35)
				{
					if(thalach>162)
						thalach = 1;
					else if(thalach<93)
						thalach = 0;
					else 
						thalach = 2;
				}
				else if(age>35 && age<=45)
				{
					if(thalach>157)
						thalach = 1;
					else if(thalach<88)
						thalach = 0;
					else
						thalach = 2;
				}
				else if(age>45 && age<=60)
				{
					if(thalach>149)
						thalach = 1;
					else if(thalach<80)
						thalach = 0;
					else
						thalach = 2;
				}
				else if(age>60 && age<=100)
				{
					if(thalach>138)
						thalach = 1;
					else if(thalach<75)
						thalach = 0;
					else
						thalach = 2;
				}
System.out.println("Enter Patient Gender");
gender= sc.nextInt();
System.out.println("Enter Patient Cholesterol");
cholesterol= sc.nextInt();
if(cholesterol<200)
					cholesterol = 1;
				else if(cholesterol>=200 && cholesterol<=239)
					cholesterol = 2;
				else if(cholesterol>=240 && cholesterol<=280)
					cholesterol = 3;
				else
					cholesterol = 4;

				Connection con = null;
				con = MysqlConnection.getConnection();
				System.out.println(con);
		        Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select *from dataset_12ppl");
				System.out.println(rs);
				trest_bp=10;
				//trest_bp = rs.getInt("trest_bp");
				//System.out.println(trest_bp);
				if(trest_bp<90)
					trest_bp = 0;
				else if(trest_bp >=90 && trest_bp<120)
					trest_bp = 1;
				else if(trest_bp >=120 && trest_bp<140)
					trest_bp = 2;
				else
					trest_bp = 3;
//System.out.println("Enter Patient Trest_bp");
if(trest_bp<90)
					trest_bp = 0;
				else if(trest_bp >=90 && trest_bp<120)
					trest_bp = 1;
				else if(trest_bp >=120 && trest_bp<140)
					trest_bp = 2;
				else
					trest_bp = 3;
//System.out.println("Enter Patient chestpain");

//System.out.println("Enter Patient blood_sugar");
//System.out.println("Enter Patient rest_ecg");
//System.out.println("Enter Patient thalach");
//System.out.println("Enter Patient exang");
//System.out.println("Enter Patient thal");
//System.out.println("Enter Patient bmi");
if(value < 18.5)
					bmi = 0;
				else if(value >= 18.5 && value <= 24.9)
					bmi = 1;
				else if(value >= 25 && value <= 29.9)
					bmi = 2;
				else if(value >=30)
					bmi = 3;
//System.out.println("Enter Patient heart_attack");

}
}

public class DataFilter extends patient{

	DataFilter() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	private static int weight = 0;

	Connection con = null;

	public static HashMap<String,String> map = new HashMap<String,String>();
	
	static
	{
		map.put("gender1", "male");
		map.put("gender0", "female");
		map.put("chest-pain1", "normal");
		map.put("chest-pain2", "high");
		map.put("chest-pain3", "severe");
		map.put("fasting-sugar1", "yes");
		map.put("fasting-sugar0", "no");
		map.put("rest-ecg2", "Definite left hypertrophy");
		map.put("rest-ecg1", "Wave abnormality");
		map.put("rest-ecg0", "normal");
		map.put("exang1", "yes");
		map.put("exang0", "no");
	}
	
	public void filtered_records() 
	{
		try {
			Connection con = null;
			con = MysqlConnection.getConnection();
			System.out.println(con);
		Statement st = con.createStatement();
		//ResultSet rs = st.executeQuery("select count(id) from dataset_12ppl ");
		//emptyTable(con);
		PreparedStatement ps = con
				.prepareStatement("insert into dataset_12ppl values(?,?,?,?,?,?,?,?,?,?,?,?)");
		String query = "select * from dataset_12ppl";
		ResultSet rs = st.executeQuery(query);
		System.out.println(rs);
		int id,age, gender, cholesterol, trest_bp, chest_pain = 0, blood_sugar = 0, rest_ecg, thalach, exang, thal, height = 0, weight = 0 , bmi=0, heart_attack;
		
			while(rs.next()) 
			{
				id = rs.getInt("id");
				age = rs.getInt("age");
				gender = rs.getInt("gender");
			
				 cholesterol = rs.getInt("cholesterol");
				if(cholesterol<200)
					cholesterol = 1;
				else if(cholesterol>=200 && cholesterol<=239)
					cholesterol = 2;
				else if(cholesterol>=240 && cholesterol<=280)
					cholesterol = 3;
				else
					cholesterol = 4;
				
				 trest_bp = rs.getInt("trest_bp");
				if(trest_bp < 90)
					trest_bp = 0;
				else if(trest_bp >=90 && trest_bp<120)
					trest_bp = 1;
				else if(trest_bp >=120 && trest_bp<140) {
				} else
				
				chest_pain = rs.getInt("chest_pain");
				int fasting_blood_sugar = rs.getInt("blood_sugar");
				rest_ecg = rs.getInt("rest_ecg");
			 thalach = rs.getInt("thalach");
				if(age>0 && age<=20)
				{
					if(thalach>170)
						thalach = 1;
					else if(thalach<100)
						thalach = 0;
					else
						thalach = 2;
				}
				else if(age>20 && age<=35)
				{
					if(thalach>162)
						thalach = 1;
					else if(thalach<93)
						thalach = 0;
					else 
						thalach = 2;
				}
				else if(age>35 && age<=45)
				{
					if(thalach>157)
						thalach = 1;
					else if(thalach<88)
						thalach = 0;
					else
						thalach = 2;
				}
				else if(age>45 && age<=60)
				{
					if(thalach>149)
						thalach = 1;
					else if(thalach<80)
						thalach = 0;
					else
						thalach = 2;
				}
				else if(age>60 && age<=100)
				{
					if(thalach>138)
						thalach = 1;
					else if(thalach<75)
						thalach = 0;
					else
						thalach = 2;
				}
				 exang = rs.getInt("exang");
				thal = rs.getInt("thalach");
				
				float value = (float)(703 * weight) / (float) (height*height);
				if(value < 18.5)
					bmi = 0;
				else if(value >= 18.5 && value <= 24.9)
					bmi = 1;
				else if(value >= 25 && value <= 29.9)
					bmi = 2;
				else if(value >=30)
					bmi = 3;
				 heart_attack = rs.getInt("heart_attack");
				System.out.println(age + " " + gender + " " + cholesterol + " "
						+ trest_bp + " " + chest_pain + " " + blood_sugar + " "
						+ rest_ecg + " " + thalach + " " + exang + " " + thal
						+ " " + height + " " + weight + " " + heart_attack);
//				ps.setInt(1, id);
//				ps.setInt(2, gender);
//				ps.setInt(3, chest_pain);
//				ps.setInt(4, trest_bp);
//				ps.setInt(5, cholesterol);
//				ps.setInt(6, blood_sugar);
//				ps.setInt(7, rest_ecg);
//				ps.setInt(8, thalach);
//				ps.setInt(9, exang);
//				ps.setInt(10, thal);
//				ps.setInt(11, bmi);
//				ps.setInt(12, heart_attack);
//				//ps.executeUpdate();
			} 
	}
			catch (SQLException e) {
				
				e.printStackTrace();}
		} 
		

	public void perform() 
	
	{
		
		long start = System.currentTimeMillis();
		filtered_records();
		long end = System.currentTimeMillis();
		System.out.println("Total time taken:"+(end-start));
		start = System.currentTimeMillis();
		CalculateProbability calculate = new CalculateProbability();
		calculate.initializeFactors();
		calculate.updateBothTables();
		CalculateProbability.calculatingTruee = true;
		CalculateProbability obj=new CalculateProbability();
		boolean b= obj.getter(true);
		System.out.println(b);
	
		calculate.calculateProbability();
		CalculateProbability.calculatingTruee = false;
		boolean c=obj.getter(false);
	System.out.println(c);
		calculate.calculateProbability();
		end = System.currentTimeMillis();
	}
	public void emptyTable(Connection con)
	{
		try
		{
			Statement st = con.createStatement();
			String query = "truncate table dataset_12ppl";
			st.executeUpdate(query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
