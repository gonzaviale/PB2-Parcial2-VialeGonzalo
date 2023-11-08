package ar.edu.unlam.pb2.Parcial01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TiendaTest {

	/**
	 * Resolver los siguientes tests
	 * @throws VendedorDeLicenciaException 
	 */

	@Test (expected = VendedorDeLicenciaException.class) 
	public void queAlIntentarAgregarUnaVentaParaUnVendedorDeLicenciaSeLanceUnaVendedorDeLicenciaException() throws VendedorDeLicenciaException {
		Tienda tienda = new Tienda("111", "TiendaGon");
		Vendedor vendedor = new Vendedor("111","Alan");
		vendedor.setDeLicencia(true);
		tienda.agregarVendedor(vendedor); 
		Venta ventaMouse = new Venta("1", new Cliente("111","RespInscripto"), vendedor);
		tienda.agregarCliente(new Cliente("111","RespInscripto"));
		tienda.agregarVenta(ventaMouse);
	}

	@Test (expected = VendibleInexistenteException.class) 
	public void queAlIntentarAgregarUnVendibleInexistenteAUnaVentaSeLanceUnaVendibleInexistenteException() throws VendedorDeLicenciaException, VendibleInexistenteException, VentaInexistenteException {
		Tienda tienda = new Tienda("111", "TiendaGon");
		Vendedor vendedor = new Vendedor("111","Alan");
		tienda.agregarVendedor(vendedor); 
		Item mouse = new Producto(1, "mouse", 100.0, 3);
		Venta ventaMouse = new Venta("1", new Cliente("111","RespInscripto"), vendedor);
		tienda.agregarCliente(new Cliente("111","RespInscripto"));
		tienda.agregarVenta(ventaMouse);
		tienda.agregarProductoAVenta("1", (Producto) mouse);  
	}

	@Test
	public void queSePuedaObtenerUnaListaDeProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion() {
		Tienda tienda = new Tienda("111", "TiendaGon");
		Vendedor vendedor = new Vendedor("111","Alan");
		tienda.agregarVendedor(vendedor); 
		Item mouse = new Producto(1, "mouse", 100.0, 3);
		Item teclado = new Producto(2, "teclado", 100.0, 3);
		tienda.agregarProducto((Producto) mouse, 3);
		tienda.agregarProducto((Producto) teclado, 4);
		assertTrue(tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion().contains(mouse));
		assertFalse(tienda.obtenerProductosCuyoStockEsMenorOIgualAlPuntoDeReposicion().contains(teclado));
	}

	@Test
	public void queSePuedaObtenerUnSetDeClientesOrdenadosPorRazonSocialDescendente() {
		Tienda tienda = new Tienda("111", "TiendaGon");
		Cliente cliente1 = new Cliente("2222", "R");
		Cliente cliente2 = new Cliente("3333", "A");
		tienda.agregarCliente(cliente2);
		tienda.agregarCliente(cliente1);
		assertEquals(cliente1, tienda.obtenerClientesOrdenadosPorRazonSocialDescendente().get(0));
	}

	@Test
	public void queSePuedaObtenerUnMapaDeVentasRealizadasPorCadaVendedor() throws VendedorDeLicenciaException, VendibleInexistenteException, VentaInexistenteException {
		// TODO: usar como key el vendedor y Set<Venta> para las ventas
		Tienda tienda = new Tienda("11", "TiendaGon");
		Vendedor vendedor = new Vendedor("111","Alan");
		Vendedor vendedor2 = new Vendedor("222","Kevin");
		Cliente cliente = new Cliente("1111","RespInscripto");
		Cliente cliente2 = new Cliente("2222","RespInscripto");
		Cliente cliente3 = new Cliente("333","RespInscripto");
		Cliente cliente4 = new Cliente("444","RespInscripto");
		Cliente cliente5 = new Cliente("555","RespInscripto");
		
		tienda.agregarVendedor(vendedor); 
		tienda.agregarVendedor(vendedor2);
		Item mouse = new Producto(1, "mouse", 100.0, 3);
		tienda.agregarProducto((Producto) mouse, 10);
		Venta ventaMouse = new Venta("1", cliente, vendedor);
		Venta ventaMouse2 = new Venta("2", cliente2, vendedor);
		Venta ventaMouse3 = new Venta("3", cliente3, vendedor);
		Venta ventaMouse4 = new Venta("4", cliente4, vendedor2);
		Venta ventaMouse5 = new Venta("5", cliente5, vendedor2);
		tienda.agregarCliente(cliente);
		tienda.agregarCliente(cliente2);
		tienda.agregarCliente(cliente3);
		tienda.agregarCliente(cliente4);
		tienda.agregarCliente(cliente5);
		tienda.agregarVenta(ventaMouse);
		tienda.agregarVenta(ventaMouse2);
		tienda.agregarVenta(ventaMouse3);
		tienda.agregarVenta(ventaMouse4);
		tienda.agregarVenta(ventaMouse5);
		tienda.agregarProductoAVenta("1", (Producto) mouse);
		tienda.agregarProductoAVenta("2", (Producto) mouse); 
		tienda.agregarProductoAVenta("3", (Producto) mouse); 
		tienda.agregarProductoAVenta("4", (Producto) mouse); 
		tienda.agregarProductoAVenta("5", (Producto) mouse); 
		
		assertEquals(2, tienda.obtenerVentasPorVendedor().size());
	
		
	}

	@Test
	public void queSePuedaObtenerElTotalDeVentasDeServicios() {
	}

	@Test
	public void queAlRealizarLaVentaDeUnProductoElStockSeActualiceCorrectamente() {
	}
}
