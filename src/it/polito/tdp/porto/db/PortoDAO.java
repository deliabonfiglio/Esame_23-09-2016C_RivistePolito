package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), 1);
				return autore;
			}
			conn.close();
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(0,rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				conn.close();
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	// Test main
	public static void main(String[] args) {

		PortoDAO pd = new PortoDAO();
		System.out.println(pd.getAutore(85));
		System.out.println(pd.getArticolo(2293546));
		System.out.println(pd.getArticolo(1941144));
	}

	public List<Author> getAutori(AuthorIdMap mapA) {
		final String sql = "SELECT * FROM author ";

		List<Author> autori= new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Author autore = mapA.get(rs.getInt("id"));
				
				if(autore ==null){
				 autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), 1);
				 autore = mapA.put(autore);
				 
				}
				autori.add(autore);
			}
			
			conn.close();
			return autori;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Paper> getPapers() {
		String sql = "select p.eprintid, p.title, p.issn, p.publication, p.`type`, p.types "+
						"from paper as p "+
						"where p.`type`= 'article' ";
		List<Paper> papers = new ArrayList<> ();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Paper paper = new Paper(0,rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				papers.add(paper);
			}
			conn.close();
			return papers;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
			
		}
	}

	public int articoloDellAutore(Author a1, Paper p2) {
		String sql = "select count(*) as cnt "+
						"from creator "+
					"where creator.authorid= ? and creator.eprintid= ? ";
		int cnt =0;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setInt(2, p2.getEprintid());
			
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			conn.close();
			return cnt;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
			
		}
	}
	
	public Map<String, Integer> getRiviste(){
		String sql = "SELECT p.publication ,count(p.eprintid) as cnt "+
						"from paper as p "+
						"where p.eprintid in (select p1.eprintid "+
											"from paper as p1 "+
											"where p1.`type`= 'article' ) "+
											"group by p.publication "+
						"order by cnt desc ";
		Map<String, Integer> riviste = new LinkedHashMap<> ();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				riviste.put(rs.getString("publication"),rs.getInt("cnt") );
			}
			conn.close();
			return riviste;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
			
		}
		
		
	}

	public Set<Author> getAutoriDellaRivista(String r) {
		
		final String sql = "SELECT a.* "+
							"from author as a, paper as p, creator as c "+
							"where a.id=c.authorid and p.eprintid= c.eprintid and p.publication=? " ;  						

		Set<Author> p = new HashSet<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, r);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Author a = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), 1);
				
				p.add(a);
			}
			
			conn.close();
			return p;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
}