package Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CalculateProbability {

	float HA_YES, HA_NO; // important factors
	public static float EXTERNAL_HA_YES,EXTERNAL_HA_NO;
	Connection con = null;
	public static boolean calculatingTruee= true ;
	
	public boolean 
	getter(boolean val) {
		 calculatingTruee =val;
		 System.out.println(val);
		return val;}
	
	public void calculateProbability() {
		
		con = MysqlConnection.getConnection();
		try {
			Statement st = con.createStatement();
			String columnName = "";
			String query1 = "describe dataset_12ppl";
			ResultSet columnResultSet = st.executeQuery(query1);
			while (columnResultSet.next()) {
				columnName = columnResultSet.getString(1);
				if (columnName.equals("id"))
					continue;
				else {
					List<Integer> list = getDistinctValues(con, columnName);
					for (int i = 0; i < list.size(); i++) {
						int value = (int) list.get(i);
						int result = getCount(con, columnName, value);
						float probability = 0.0f;
						if (calculatingTruee) {
							probability = EXTERNAL_HA_YES * (result / HA_YES);
							if (probability == 0.0f || probability == -0.0f
									|| Math.abs(probability) == 0)
					
								probability = 1;
						
							insertToDatabase(con, value, columnName, result,probability, calculatingTruee);
						} else {
							probability = EXTERNAL_HA_NO * (result / HA_NO);
							if (probability == 0 || probability == -0.0f
									|| Math.abs(probability) == 0)
								probability = 1;
							
							insertToDatabase(con, value, columnName, result,probability, calculatingTruee);
						}
					}
				}
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}

	public void initializeFactors() 
	{
		Connection con1 = MysqlConnection.getConnection();
		try {
			Statement st = con1.createStatement();
			ResultSet rs = st.executeQuery("select count(id) from dataset_12ppl");
			int total = 1;
			while(rs.next())
				total = rs.getInt(1);
			String query = "select count(heart_attack) from dataset_12ppl where heart_attack=1";
			rs = st.executeQuery(query);
			while (rs.next())
				HA_YES = (float)rs.getInt(1);

			query = "select count(heart_attack) from dataset_12ppl where heart_attack=0";
			rs = st.executeQuery(query);
			while (rs.next())
				HA_NO = (float)rs.getInt(1);
			EXTERNAL_HA_YES = HA_YES/ (float) total;
			EXTERNAL_HA_NO = HA_NO / (float) total;
			System.out.println(HA_YES+"  "+HA_NO);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@SuppressWarnings("removal")
	public List<Integer> getDistinctValues(Connection con, String columnName) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			String query = "select distinct(" + columnName
					+ ") from dataset_12ppl";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				list.add(new Integer(rs.getInt(1)));
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getCount(Connection con, String columnName, int value) {
		int count = 0;
		try {
			String query = "select count(" + columnName
					+ ") from dataset_12ppl where " + columnName + "="
					+ value;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				count = rs.getInt(1);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public void insertToDatabase(Connection con, int uniqueValue,String columnName, int count, float probability, boolean usingTrue) {
		//System.out.println(+uniqueValue+" "+count);
		
		//System.out.println("lol"+uniqueValue+" "+count);
		try {
			DataFilter filter=new DataFilter();
			filter.perform();
			//System.out.println("lol1"+uniqueValue+" "+count);
			
			if (usingTrue) {
				System.out.println("0");
							String query = "update probability_storage_yes set count="
						+ count + ",probability=" + probability
						+ " where attribute='" + columnName
						+ "' and attribute_value=" + uniqueValue;
				Statement st = con.createStatement();
				
				try
				{
					//System.out.println("HEY TRY *****************");

					st.executeUpdate(query);
					//System.out.println("Yes");
					//System.out.println("\nHEY TRY after querry *****************");

				}
				catch(SQLException e)
				{
					System.out.println("Yess");
				}
			} else {
				System.out.println("1");
				String query = "update probability_storage_no set count="
						+ count + ",probability=" + probability
						+ " where attribute='" + columnName
						+ "' and attribute_value=" + uniqueValue;
				Statement st = con.createStatement();
				st.executeUpdate(query);

			}
		} catch (SQLException e) {
			System.out.println("lol3"
					+ ""+uniqueValue+" "+count);
			
			e.printStackTrace();
		}
	}

	public void updateBothTables() {
		try {
			
			Connection con1 = MysqlConnection.getConnection();
			Statement st = con1.createStatement();
			String query = "update probability_storage_yes set count=0,probability=0";
			st.executeUpdate(query);
			query = "update probability_storage_no set count=0,probability=0";
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}