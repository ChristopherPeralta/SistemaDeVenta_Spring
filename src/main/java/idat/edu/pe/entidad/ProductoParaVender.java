package idat.edu.pe.entidad;

public class ProductoParaVender extends Producto{

	private int cantidad;

    public ProductoParaVender(String COD, String DESCRIPCION, Float PRECIO_VENTA, Float STOCK, int i) {
        super(COD, DESCRIPCION, PRECIO_VENTA, STOCK);
        this.cantidad = i;
    }

	public void aumentarCantidad() {
        this.cantidad++;
    }
	
	public void restarCantidad() {
        this.cantidad--;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Float getTotal() {
        return this.getPRECIO_VENTA() * this.cantidad;
    }
}
