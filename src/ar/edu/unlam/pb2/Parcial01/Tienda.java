package ar.edu.unlam.pb2.Parcial01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Tienda {

	/**
	 * En esta ocasion deberemos resolver un producto software que nos permita
	 * administrar la venta de productos o servicios de nuestra tienda. Venderemos
	 * entonces, productos como mouse o teclados y servicios como el soporte tecnico
	 * a domicilio. Sabemos que la tienda cuenta con items Vendibles que pueden ser
	 * del tipo Producto o Servicio. Ademas, podemos registrar el stock de los
	 * productos, los clientes a quienes les vendemos algun producto o servicio, las
	 * ventas y los vendedores de la tienda. Antes de realizar alguna operacion, se
	 * debera obtener el elemento correspondiente de las colecciones. Ejemplo: Si
	 * quisiera realizar alguna operacion con un cliente, el mismo debe obtenerse de
	 * la coleccion de clientes.
	 * 
	 * Cada Venta contiene renglones los cuales representa a los productos o
	 * servicios que se incluyen en la misma. Tambien cuenta con el Cliente y
	 * Vendedor que participan en la Venta. Cuando agregamos un vendible a una
	 * venta, lo haremos con 1 unidad. En una version posterior, admitiremos
	 * cantidades variables.
	 * 
	 * Cada Item debe compararse por nombre y precio, en caso de ser necesario.
	 * Recordar que los items deben ser Vendibles.
	 * 
	 */

	private String cuit;
	private String nombre;
	private Set<Vendible> vendibles;
	private Map<Producto, Integer> stock; 
	private List<Cliente> clientes;
	private Set<Venta> ventas;
	private Set<Vendedor> vendedores;

	public Tienda(String cuit, String nombre) {

		// TODO: Completar el constructor para el correcto funcionamiento del software
		this.setCuit(cuit);
		this.setNombre(nombre);
		this.vendibles = new HashSet<Vendible>(); 
		this.stock = new HashMap<Producto, Integer>();
		this.clientes = new ArrayList<Cliente>();
		this.ventas = new HashSet<Venta>();
		this.vendedores = new HashSet<Vendedor>();
	}

	// TODO: Completar con los getters y setters necesarios

	public Vendible getVendible(Integer codigo) {
		// TODO: Obtiene un producto o servicio de la coleccion de vendibles utilizando
		// el codigo. En caso de no existir devuelve null.
		for (Vendible vendible : vendibles) {
			if(vendible.getCodigo().equals(codigo))
				return vendible;
		}
		return null;
	}

	public void agregarProducto(Producto producto) {
		this.agregarProducto(producto, 0);
	} 

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Vendible> getVendibles() {
		return vendibles;
	}

	public void setVendibles(Set<Vendible> vendibles) {
		this.vendibles = vendibles;
	}

	public Map<Producto, Integer> getStock() {
		return stock;
	}

	public void setStock(Map<Producto, Integer> stock) {
		this.stock = stock;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public Set<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(Set<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public void agregarProducto(Producto producto, Integer stockInicial) {
		// TODO: Agrega un producto a la coleccion de vendibles y pone en la coleccion
		// de stocks al producto con su stock inicial
		this.vendibles.add(producto);
		this.stock.put(producto, stockInicial);
	}

	public void agregarServicio(Servicio servicio) {
		// TODO: Agrega un servicio a la coleccion de vendibles
		this.vendibles.add(servicio);
	}

	public Integer getStock(Producto producto) {
		return stock.get(producto);
	}

	public void agregarStock(Producto producto, Integer incremento){
		// TODO: se debe agregar stock a un producto existente
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if(key.equals(producto))
				entry.setValue(incremento+val);
		}
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void agregarVendedor(Vendedor vendedor) {
		this.vendedores.add(vendedor); 
	}

	public void agregarVenta(Venta venta) throws VendedorDeLicenciaException {
		// TODO: Agrega una venta a la coleccion correspondiente. En caso de que el
		// vendedor este de licencia, arroja una
		// VendedorDeLicenciaException
		verificarVendedor(venta); 
		this.ventas.add(venta);
	}

	private Boolean verificarVendedor(Venta venta) throws VendedorDeLicenciaException{ 
		// TODO Auto-generated method stub
		if(venta.getVendedor().isDeLicencia())
			throw new VendedorDeLicenciaException();  
		return true;
	}

	public Producto obtenerProductoPorCodigo(Integer codigo) {
		// TODO: Obtiene un producto de los posibles por su codigo. En caso de no
		// encontrarlo se debera devolver null
		for (Vendible producto : vendibles) {
			 if(producto.getCodigo().equals(codigo))
				 return ((Producto)producto);
		} 
		return null;
	}

	public void agregarProductoAVenta(String codigoVenta, Producto producto) throws VendibleInexistenteException, VentaInexistenteException {

		// TODO: Agrega un producto a una venta. Si el vendible no existe (utilizando su
		// codigo), se debe lanzar una VendibleInexistenteException
		// Se debe actualizar el stock en la tienda del producto que se agrega a la
		// venta
		verificarVendible(producto);
		verificarVenta(codigoVenta);
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if(key.equals(producto))
				entry.setValue(val-1);
		}
		for (Venta venta : ventas) {
			if(venta.getCodigo().equals(codigoVenta))
				venta.agregarRenglon(producto, 1);
		} 
		
	}

	private Boolean verificarVenta(String codigoVenta) throws VentaInexistenteException {
		// TODO Auto-generated method stub
		for (Venta venta : ventas) {
			if(venta.getCodigo().equals(codigoVenta))
				return true;
		}
		throw new VentaInexistenteException();
	}

	private Boolean verificarVendible(Producto producto) throws VendibleInexistenteException {
		// TODO Auto-generated method stub

		if(!vendibles.contains(producto))
			throw new VendibleInexistenteException();
		
		return true;
		
	}

	public void agregarServicioAVenta(String codigoVenta, Servicio servicio) throws VentaInexistenteException {
		// TODO: Agrega un servicio a la venta. Recordar que los productos y servicios
		// se traducen en renglones
		verificarVenta(codigoVenta);
		for (Venta venta : ventas) {
			if(venta.getCodigo().equals(codigoVenta))
				venta.agregarRenglon(servicio, 1);
		}
	}

	public List<Producto> obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		// TODO: Obtiene una lista de productos cuyo stock es menor o igual al punto de
		// reposicion. El punto de reposicion, es un valor que
		// definimos de manera estrategica para que nos indique cuando debemos reponer
		// stock para no quedarnos sin productos
		List<Producto> productosPuntoReposicion = new ArrayList<Producto>(); 
		for (Map.Entry<Producto, Integer> entry : stock.entrySet()) {
			Producto key = entry.getKey();
			Integer val = entry.getValue();
			if(val <= key.getPuntoDeReposicion())
				productosPuntoReposicion.add(key);
		}
		return productosPuntoReposicion; 
	}

	public List<Cliente> obtenerClientesOrdenadosPorRazonSocialDescendente() {
		// TODO: obtiene una lista de clientes ordenados por su razon social de manera
		// descendente
		TreeSet<Cliente> ordenados = new TreeSet<Cliente>(new ClientesOrdenados()); 
		for (Cliente cliente : clientes) {
			ordenados.add(cliente);
		}
		List <Cliente> ordenadosList = new ArrayList<>();
		for (Cliente cliente : ordenados) {
			ordenadosList.add(cliente);
		}
		return ordenadosList;  
	}

	public Map<Vendedor, Set<Venta>> obtenerVentasPorVendedor() {
		// TODO: Obtiene un mapa que contiene las ventas realizadas por cada vendedor.
		Map <Vendedor, Set<Venta>> ventasPorVendedor = new HashMap<>(); 
		for (Vendedor vendedor : vendedores) {
			Set<Venta> aux = new HashSet<Venta>();
			for (Venta venta : ventas) {
				if(venta.getVendedor().equals(vendedor)) {
					aux.add(venta);
				}
			}
			ventasPorVendedor.put(vendedor, aux);
		}
		return ventasPorVendedor; 
	}

	public Double obtenerTotalDeVentasDeServicios() {
		// TODO: obtiene el total acumulado de los vendibles que son servicios incluidos
		// en todas las ventas.
		// Si una venta incluye productos y servicios, solo nos interesa saber el total
		// de los servicios
		return null;
	}
}
