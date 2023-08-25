package idat.edu.pe.entidad;

public class ProductoParaComprar extends Producto{

	private int cantidad;

	public ProductoParaComprar(String COD, String DESCRIPCION,Float STOCK , Float PRECIO_VENTA, int cantidad) {
        super(COD, DESCRIPCION,STOCK, PRECIO_VENTA );
        this.cantidad = cantidad;
    }

	public void aumentarCantidad() {
        this.cantidad++;
    }
	

    public int getCantidad() {
        return cantidad;
    }

    public Float getTotal() {
        return this.getSTOCK() * this.cantidad;
    }
}


