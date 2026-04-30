public class AplicacionEjercicios {
    private final ImpresorConsola impresor;
    private final EjerciciosSeccionUno seccionUno;
    private final EjerciciosSeccionDos seccionDos;
    private final EjerciciosSeccionTres seccionTres;
    private final EjerciciosSeccionCuatro seccionCuatro;

    public AplicacionEjercicios() {
        this.impresor = new ImpresorConsola();
        this.seccionUno = new EjerciciosSeccionUno(impresor);
        this.seccionDos = new EjerciciosSeccionDos(impresor);
        this.seccionTres = new EjerciciosSeccionTres(impresor);
        this.seccionCuatro = new EjerciciosSeccionCuatro(impresor);
    }

    public void ejecutar() {
        seccionUno.ejecutar();
        seccionDos.ejecutar();
        seccionTres.ejecutar();
        seccionCuatro.ejecutar();
    }
}
