package Negocio;

import java.util.List;

import Entidades.Coche;
import Integracion.DAOCoche;

public class SACoche {
	
	DAOCoche daoCoche;
	
	public SACoche() {
		daoCoche= new DAOCoche();
	}

	public String altaCoche(Coche c) {
		if(daoCoche.buscarPorMatricula(c.getMatricula())==null) {
			boolean alta=daoCoche.altaCoche(c);
		if(alta) {
			return "El coche con matricula "+ c.getMatricula() + " ha sido dado de alta.";
		}
		else {
			return "No se ha podido crear el usuario. Comprueba los datos.";
		}
		}
		return "La matricula ya se encuentra en la base de datos, compruebela de nuevo.";
	}
	
	public String borrarCoche(int id) {
		if(daoCoche.eliminarCoche(id)) {
			return "Se ha dado de baja el coche con el id " + id;
		}
		return "No se ha podido dar de baja el coche con el id " + id;
	}
	
	public String modificarCoche(Coche c) {
		if(daoCoche.modificarCoche(c)) {
			return "Se ha modificado el coche con el id " + c.getId();
		}
		else {
			return "Error en la base de datos. No se ha encontrado el coche con id "+ c.getId();
		}
		
		
	}
	public String buscarPorId(int id) {
		Coche x=daoCoche.buscarPorId(id);
		if(x!=null) {
			return "Coche con id "+id+":"+"\r\n"
				+ "    -Matricula: "+x.getMatricula()+"\r\n"
				+ "    -Marca: "+x.getMarca()+"\r\n"
				+ "    -Modelo: "+x.getModelo()+"\r\n"
				+ "    -Kilometros: "+x.getKilometros()+"\r\n";
		}
		else {
			return "No se ha encontrado el coche con id "+ id;
		}
		
		
	}
	public String buscarPorMatricula(String matricula) {
		Coche x=daoCoche.buscarPorMatricula(matricula);
		if(x!=null) {
			return "Coche con matricula "+matricula+":"+"\r\n"
				+ "    -ID: "+x.getId()+"\r\n"
				+ "    -Marca: "+x.getMarca()+"\r\n"
				+ "    -Modelo: "+x.getModelo()+"\r\n"
				+ "    -Kilometros: "+x.getKilometros()+"\r\n";
		}
		else {
			return "No se ha encontrado el coche con la matricula "+ matricula;
		}
		
		
	}
	
	public String buscarPorMarca(String marca) {
		String x="";
		List<Coche> listaC=daoCoche.buscarPorMarca(marca);
		if(listaC.isEmpty()) {
			return "No hay coches en la base de datos con la marca "+marca;
		}
		else {
			for(Coche c: listaC) {
				x+="-Coche con ID: "+c.getId()+"\r\n"
						+ "    -Matricula: "+c.getMatricula()+"\r\n"
						+ "    -Marca: "+c.getMarca()+"\r\n"
						+ "    -Modelo: "+c.getModelo()+"\r\n"
						+ "    -Kilometros: "+c.getKilometros()+"\r\n";
			}
			return "La lista de coches son:\r\n"
					+x;
		}
	}
	
	public String buscarPorModelo(String modelo) {
		String x="";
		List<Coche> listaC=daoCoche.buscarPorModelo(modelo);
		if(listaC.isEmpty()) {
			return "No hay coches en la base de datos con el modelo "+modelo;
		}
		else {
			for(Coche c: listaC) {
				x+="-Coche con ID: "+c.getId()+"\r\n"
						+ "    -Matricula: "+c.getMatricula()+"\r\n"
						+ "    -Marca: "+c.getMarca()+"\r\n"
						+ "    -Modelo: "+c.getModelo()+"\r\n"
						+ "    -Kilometros: "+c.getKilometros()+"\r\n";
			}
			return "La lista de coches son:\r\n"
					+x;
		}
	}
	
	public String listarCoches() {
		String x="";
		List<Coche> listaC=daoCoche.listarCoches();
		if(listaC.isEmpty()) {
			return "No hay coches en la base de datos";
		}
		else {
			for(Coche c: listaC) {
				x+="-Coche con ID: "+c.getId()+"\r\n"
						+ "    -Matricula: "+c.getMatricula()+"\r\n"
						+ "    -Marca: "+c.getMarca()+"\r\n"
						+ "    -Modelo: "+c.getModelo()+"\r\n"
						+ "    -Kilometros: "+c.getKilometros()+"\r\n";
			}
			return "La lista de coches son:\r\n"
					+x;
		}
	}
}
