package Integracion;

import java.util.ArrayList;
import java.util.List;

import Entidades.Coche;

public interface DAOCocheInterfaz {
	
	public boolean altaCoche(Coche c);
	public boolean eliminarCoche(int id);
	public boolean modificarCoche(Coche c);
	public Coche buscarPorId(int id);
	public Coche buscarPorMatricula(String matricula);
	public List<Coche> buscarPorMarca(String marca );
	public List<Coche> buscarPorModelo(String modelo);
	public List<Coche> listarCoches();
}
