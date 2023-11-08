package ar.edu.unlam.pb2.Parcial01;

public class Producto extends Item implements Vendible{
	
	private Integer puntoDeReposicion;
	
	public Producto(Integer codigo, String nombre, Double precio, Integer puntoDeReposicion) {
		super(codigo, nombre, precio);
		// TODO: Completar el constructor para el correcto funcionamiento del software
		this.setPuntoDeReposicion(puntoDeReposicion);
	}

	// TODO: Completar con los getters y setters necesarios
	
	@Override
	public Integer getCodigo() {
		// TODO Auto-generated method stub
		return this.getCodigoItem(); 
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.getNombreItem();
	}

	@Override
	public Double getPrecio() {
		// TODO Auto-generated method stub
		return this.getPrecioItem();
	}

	public Integer getPuntoDeReposicion() {
		return puntoDeReposicion;
	}

	public void setPuntoDeReposicion(Integer puntoDeReposicion) {
		this.puntoDeReposicion = puntoDeReposicion;
	}

	
}
