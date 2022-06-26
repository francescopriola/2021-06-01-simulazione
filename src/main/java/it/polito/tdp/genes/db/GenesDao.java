package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Arco;
import it.polito.tdp.genes.model.Genes;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Genes> getEssentialGenes(){
		String sql = "SELECT DISTINCT `GeneID`, `Essential`, `Chromosome` "
				+ "FROM `genes` "
				+ "WHERE `Essential` = 'essential' "
				+ "ORDER BY `GeneID`";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Arco> getArchi(Map<String, Genes> idMap){
		String sql = "SELECT * FROM interactions";
		List<Arco> result = new ArrayList<Arco>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				Genes gene1 = idMap.get(res.getString("geneID1")) ;
				Genes gene2 = idMap.get(res.getString("geneID2")) ;
				
				if( gene1!=null && gene2!=null && !gene1.equals(gene2) ) {
					result.add(new Arco(gene1, gene2,
							res.getString("type"), res.getDouble("Expression_Corr"))) ;
				}
			}
			conn.close();
			return result ;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	


	
}
