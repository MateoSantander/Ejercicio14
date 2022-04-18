package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Coche;

public class DAOCoche implements DAOCocheInterfaz{
	
	private Connection conexion; 
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/ejercicio14";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean altaCoche(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coches (MATRICULA,MARCA,MODELO,KILOMETROS) "
				+ " values(?,?,?,?)";
		try {
			
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getKilometros());
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
		
	}

	@Override
	public boolean eliminarCoche(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from coches where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado;
		
	}

	@Override
	public boolean modificarCoche(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update coches set MATRICULA=?, MARCA=?, MODELO=?, "
				+ "KILOMETROS=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getKilometros());
			ps.setInt(5, c.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
		} catch (SQLException e) {
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
		
	}

	@Override
	public Coche buscarPorId(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Coche coche = null;
		
		String query = "select ID,MATRICULA,MARCA,MODELO,KILOMETROS from coches "
				+ "where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				coche.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return coche;
	}

	@Override
	public Coche buscarPorMatricula(String matricula) {
		if(!abrirConexion()){
			return null;
		}		
		Coche coche = null;
		
		String query = "select ID,MATRICULA,MARCA,MODELO,KILOMETROS from coches "
				+ "where matricula = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, matricula);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				coche.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return coche;
	}

	@Override
	public List<Coche> buscarPorMarca(String marca) {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MATRICULA,MARCA,MODELO,KILOMETROS from coches"
				+ " where marca= ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, marca);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				coche.setId(rs.getInt(1));
				listaCoches.add(coche);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaCoches;
	}

	@Override
	public List<Coche> buscarPorModelo(String modelo) {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MATRICULA,MARCA,MODELO,KILOMETROS from coches"
				+ " where modelo= ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, modelo);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				coche.setId(rs.getInt(1));
				listaCoches.add(coche);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaCoches;
	}

	@Override
	public List<Coche> listarCoches() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MATRICULA,MARCA,MODELO,KILOMETROS from coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				coche.setId(rs.getInt(1));
				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaCoches;
	}

}
