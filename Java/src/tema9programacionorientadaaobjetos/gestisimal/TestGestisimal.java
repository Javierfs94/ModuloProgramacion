package tema9programacionorientadaaobjetos.gestisimal;

import java.util.Scanner;

import tema9programacionorientadaaobjetos.gestisimal.almacen.Almacen;
import tema9programacionorientadaaobjetos.gestisimal.almacen.Articulo;
import tema9programacionorientadaaobjetos.gestisimal.exceptions.ArticuloNoExisteException;
import tema9programacionorientadaaobjetos.gestisimal.exceptions.CantidadNegativaExceptions;
import tema9programacionorientadaaobjetos.gestisimal.exceptions.CodigoNoValidoExceptions;
import tema9programacionorientadaaobjetos.gestisimal.exceptions.StockNegativoExceptions;

/**
 * <p>
 * Se comunica con el usuario (E/S de datos por consola) Comprueba si existe o
 * no el artículo en el almacén Comprueba que el stock no sea negativo en el
 * almacén Comprueba que el articulo exista para borrarlo del almacén.
 * </p>
 * <p>
 * Test para comprobar la clase Gestisimal.
 * </p>
 * 
 * @author Rafael Miguel Cruz Álvarez
 * @author Fco Javier Frías Serrano
 * @version 1.0
 */
public class TestGestisimal {

	static Almacen almacen = new Almacen();
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		int opcion;

		almacenDePrueba();

		do {
			mostrarMenu();
			opcion = pedirOpcion();

			switch ((opcion)) {
			case 1:
				System.out.println(almacen);
				break;
			case 2:
				annadir();
				break;
			case 3:
				baja();
				break;
			case 4:
				modificar();
				break;
			case 5:
				entradaAlmacen();
				break;
			case 6:
				salidaAlmacen();
				break;
			case 7:
				salir();
				break;
			}
		} while (opcion != 7);
	}

	private static void almacenDePrueba() {
		try {
			almacen.annadir("1", 11, 11, 11);
			almacen.annadir("2", 22, 22, 22);
			almacen.annadir("33", 11, 11, 33);
			almacen.annadir("44", 11, 11, 44);
			almacen.annadir("55", 11, 11, 55);
		} catch (Exception e) {
			System.out.println("ESTO NO DEBE APARECERRRRRRRRRRRRRRRRR");
		}
	}

	/**
	 * Sale del programa
	 */
	private static void salir() {
		System.out.println("**********************************");
		System.out.println("Saliendo de la gestión del almacén");
		System.out.println("**********************************");
	}

	/**
	 * Da de alta un artículo al almacén
	 * 
	 * @throws Exception
	 */
	private static void annadir() throws Exception {

		try {
			System.out.println("--AÑADIR ARTÍCULO--");
			System.out.println("Introduzca la descripción del artículo:");
			String descripcion = entrada.next();
			System.out.println("Introduzca el precio de compra del artículo:");
			double precioCompra = entrada.nextDouble();
			System.out.println("Introduzca el precio de venta del artículo:");
			double precioVenta = entrada.nextDouble();
			System.out.println("Introduzca el stock del artículo:");
			int stock = entrada.nextInt();

			almacen.annadir(descripcion, precioCompra, precioVenta, stock);
			System.out.println("Artículo añadido.");
		} catch (Exception e) {
			System.err.println("No se ha podido dar de alta al artículo. " + e.getMessage());
			entrada.nextLine();
		}
	}

	/**
	 * Da de baja un artículo del almacén
	 * 
	 * @throws CodigoNoValidoExceptions
	 */
	private static void baja() throws CodigoNoValidoExceptions {
		System.out.println("Introduce el códido del artículo a eliminar.");
		int codigo = entrada.nextInt();
		entrada.nextLine();

		if (almacen.baja(codigo))
			System.out.println("Artículo eliminado.");
		else
			System.err.println("El artículo no se ha podido eliminar. No existe un artículo con ese código en el almacen.");

		entrada.nextLine();
	}

	/**
	 * Modifica el stock del almacen
	 * 
	 * @throws StockNegativoExceptions
	 */
	private static void modificar() throws StockNegativoExceptions {

		try {
			System.out.println("--MODIFICAR ARTÍCULO--");
			System.out.println("Introduce el codigo del articulo a modificar.");
			int codigo = entrada.nextInt();
			Articulo articulo = almacen.get(codigo);
			System.out.println(articulo);

			System.out.println("Introduzca la nueva descripción del artículo:");
			String descripcion = entrada.next();
			System.out.println("Introduzca el nuevo precio de compra del artículo:");
			double precioCompra = entrada.nextDouble();
			System.out.println("Introduzca el nuevo precio de venta del artículo:");
			double precioVenta = entrada.nextDouble();
			System.out.println("Introduzca el nuevo stock del artículo:");
			int stock = entrada.nextInt();

			almacen.set(articulo, descripcion, precioCompra, precioVenta, stock);

		} catch (ArticuloNoExisteException e) {
			System.err.println("No se ha podido modificar el artículo." + e.getMessage());
		}

	}

	/**
	 * Aumentar el stock de un artículo
	 * 
	 * @throws StockNegativoExceptions
	 * @throws CantidadNegativaExceptions
	 */
	private static void entradaAlmacen() throws StockNegativoExceptions, CantidadNegativaExceptions {
		try {
			System.out.println("--INCREMENTAR STOCK--");
			System.out.println("Introduce el codigo del articulo para incrementar su stock.");
			int codigo = entrada.nextInt();
			Articulo articulo = almacen.get(codigo);
			System.out.println(articulo);

			System.out.println("Introduzca el número de artículos entregados al almacen.");
			int cantidad = entrada.nextInt();
			almacen.incrementar(codigo, cantidad);

		} catch (ArticuloNoExisteException e) {
			System.err.println("No se ha podido incrementar el stock del artículo." + e.getMessage());
		}

	}

	/**
	 * Disminuye el stock de un artículo (este no puede ser negativo)
	 * 
	 * @throws StockNegativoExceptions
	 * @throws CantidadNegativaExceptions
	 */
	private static void salidaAlmacen() throws StockNegativoExceptions, CantidadNegativaExceptions {
		try {
			System.out.println("--DECREMENTAR STOCK--");
			System.out.println("Introduce el codigo del articulo para decrementar su stock.");
			int codigo = entrada.nextInt();
			Articulo articulo = almacen.get(codigo);
			System.out.println(articulo);

			System.out.println("Introduzca el número de artículos eliminados del almacen.");
			int cantidad = entrada.nextInt();
			almacen.decrementar(codigo, cantidad);

		} catch (ArticuloNoExisteException e) {
			System.err.println("No se ha podido incrementar el stock del artículo." + e.getMessage());
		}

	}

	/**
	 * Muestra el menú princial al usuario
	 */
	public static void mostrarMenu() {
		System.out.println("----MENÚ GESTISIMAL----");
		System.out.println("1º)Listado.");
		System.out.println("2º)Alta.");
		System.out.println("3º)Baja.");
		System.out.println("4º)Modificación.");
		System.out.println("5º)Entrada de mercancía.");
		System.out.println("6º)Salida de mercancía.");
		System.out.println("7º)Salir.");
	}

	/**
	 * Recoge la opción valida del menú
	 * 
	 * @return opción
	 */
	public static int pedirOpcion() {
		int opcion = 0;
		try {
			System.out.print("Introduzca una opción: ");
			opcion = entrada.nextInt();
		} catch (Exception e) {
			System.err.println("Error al introducir la opción");
			e.printStackTrace();
		} finally {
			entrada.nextLine();
		}
		return opcion;
	}
}