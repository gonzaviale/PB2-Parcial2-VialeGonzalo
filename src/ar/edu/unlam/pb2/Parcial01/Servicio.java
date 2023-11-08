package ar.edu.unlam.pb2.Parcial01;

public class Servicio extends Item implements Vendible{
	
	private String fechaDeInicio;
	private String fechaDeFinalizacion;

	public Servicio(Integer codigo, String nombre, Double precio, String fechaDeInicio, String fechaDeFinalizacion) {
		super(codigo, nombre, precio); 
		// TODO: Completar el constructor para el correcto funcionamiento del software
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeFinalizacion = fechaDeFinalizacion;
	}
	
	// TODO: Completar con los getters y setters necesarios

	public String getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(String fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public String getFechaDeFinalizacion() {
		return fechaDeFinalizacion;
	}

	public void setFechaDeFinalizacion(String fechaDeFinalizacion) {
		this.fechaDeFinalizacion = fechaDeFinalizacion;
	}

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
	
}
