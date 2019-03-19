package tema9programacionorientadaaobjetos.gestisimal.almacen;

import java.util.ArrayList;

import tema9programacionorientadaaobjetos.gestisimal.exceptions.ArticuloNoExisteException;
import tema9programacionorientadaaobjetos.gestisimal.exceptions.ArticuloYaExisteException;
import tema9programacionorientadaaobjetos.gestisimal.exceptions.CantidadNegativaExceptions;
import tema9programacionorientadaaobjetos.gestisimal.exceptions.CodigoNoValidoExceptions;
import tema9programacionorientadaaobjetos.gestisimal.exceptions.StockNegativoExceptions;

/**
 * <p>
 * Gestiona el conjunto de artículos del almacén
 * </p>
 * 
 * @author Rafael Miguel Cruz Álvarez
 * @author Fco Javier Frías Serrano
 * @version 1.0
 */
public class Almacen {

	private ArrayList<Articulo> arrayList = new ArrayList<Articulo>();

	/**
	 * Añadir un articulo
	 * 
	 * @param codigo
	 * @param descripcion
	 * @param precioCompra
	 * @param precioVenta
	 * @param stock
	 * @throws Exception
	 */
	public void annadir(String descripcion, double precioCompra, double precioVenta, int stock) throws Exception {
		Articulo articulo = new Articulo(descripcion, precioCompra, precioVenta, stock);
		if (!(arrayList.contains(articulo)))
			arrayList.add(articulo);
		else
			throw new ArticuloYaExisteException("El árticulo ya existe.");

	}

	/**
	 * Elimina el artículo del almacén(Array List)
	 * 
	 * @param codigo
	 *          Código del artículo a eliminar
	 * @return true si se ha eliminado. false en otro caso.
	 */
	public boolean baja(int codigo) throws CodigoNoValidoExceptions {
		return arrayList.remove(new Articulo(codigo));
	}

	/**
	 * Modificar articulo
	 * 
	 * @param articulo
	 * @param descripcion
	 * @param precioCompra
	 * @param precioVenta
	 * @param stock
	 * @throws StockNegativoExceptions
	 */
	public void set(Articulo articulo, String descripcion, double precioCompra, double precioVenta, int stock)
			throws StockNegativoExceptions {
		int indice = arrayList.indexOf(articulo);
		articulo.set(descripcion, precioCompra, precioVenta, stock);
		arrayList.set(indice, arrayList.get(indice));
	}

	/**
	 * Método get para obtener el codigo del artículo
	 * 
	 * @param codigo
	 * @return
	 * @throws ArticuloNoExisteException
	 */
	public Articulo get(int codigo) throws ArticuloNoExisteException {
		try {
			return arrayList.get(arrayList.indexOf(new Articulo(codigo)));
		} catch (IndexOutOfBoundsException e) {
			throw new ArticuloNoExisteException("El código del artículo no existe en el almacén.");
		}
	}

	/**
	 * Método incrementar, que aumenta las unidades de stock de un artículo.
	 * 
	 * @param codigo
	 * @param cantidad
	 * @throws CantidadNegativaExceptions
	 * @throws StockNegativoExceptions
	 */
	public void incrementar(int codigo, int cantidad) throws StockNegativoExceptions, CantidadNegativaExceptions {
		Articulo articulo = arrayList.get(arrayList.indexOf(new Articulo(codigo)));
		try {
			articulo.incrementaStock(cantidad);
		} catch (CantidadNegativaExceptions e) {
			System.err.println("No se ha podido incrementar el stock del artículo." + e.getMessage());
		}
	}

	/**
	 * Método decrementar, que disminuye las unidades de stock de un artículo.
	 * 
	 * @param codigo
	 * @param cantidad
	 * @throws CantidadNegativaExceptions
	 * @throws StockNegativoExceptions
	 */
	public void decrementar(int codigo, int cantidad) throws StockNegativoExceptions, CantidadNegativaExceptions {
		Articulo articulo = arrayList.get(arrayList.indexOf(new Articulo(codigo)));
		try {
			articulo.decrementaStock(cantidad);
		} catch (CantidadNegativaExceptions e) {
			System.err.println("No se ha podido decrementar el stock del artículo." + e.getMessage());
		} catch (StockNegativoExceptions e) {
			System.err.println("No se ha podido decrementar el stock del artículo." + e.getMessage());
		}
	}

	/**
	 * Método toString
	 */
	@Override
	public String toString() {
		return "Artículo " + arrayList + "";
	}
}
