
import javax.swing.JOptionPane;


public class Main
{
    // imprimo el tablero en su estado actual.
    public static String dibujar(Tablero obj)
    {
        // declaro la variable "datos" de tipo string la utilizare para acumular todo el tablero
        String datos = "";
        // recorreré toda la matriz con el ciclo for
        for (int i = 0; i < obj.getDimension(); i++)
        {
            for (int j = 0; j < obj.getDimension(); j++)
            {
                // imprimo el tablero si esta en la posicion 0
                if (obj.getTablero(i, j) == 0)
                {
                    // guardo en datos el valor de datos mas la barrita que indica la posicion en donde se encuentra el usuario actualmente
                    datos = datos + "     _  ";
                }
                else
                {
                    // si el numero que esta en el tablero enta entre el rango de 1 y 9
                    if (obj.getTablero(i, j) > 0 && obj.getTablero(i, j) < 10)
                    {
                        /**
                         * entonces le dare un espacio mas a los cuadros, por que los numeros 1,2,3,..9 
                         * se veran de esta forma |1| |2| pero si hay un numero como el |10| entonces quedan mal esteticamente
                         * en Java no he encontrado una forma de poder identar esto, nisiquiera con el "\t"
                         */
                        datos = datos + "   | " + String.valueOf(obj.getTablero(i, j)) + " |";
                    }
                    else
                    {
                        // sino lo imprimo normal sin el espacio despues del "|"
                        datos = datos + "   |" + String.valueOf(obj.getTablero(i, j)) + "|";
                    }
                }
            }
            // doy un salto de linea
            datos = datos + "\n";
        }
        return datos;
    }

    public static String creditos()
    {
        String datos = "";
        datos = datos + "=====================================================================\n";
        datos = datos + "=============   BIENVENIDO AL JUEGO DESLIZANTE MENTAL   =================\n";
        datos = datos + "=====================================================================\n";
        datos = datos + "=====================================================================\n";
        datos = datos + "================   CREADO POR OMAR FLOREZ (Vlph7_7)   ===================\n";
        datos = datos + "=====================================================================\n";
        return datos;
    }
    
    public static void main(String[] args)
    {
        Tablero obj = new Tablero();
        int dim;
        // con el ciclo do..while me asegurare de que se ingrese una dimension adecuada
        do
        {
            // pido en pantalla que se digite la dimension del tablero y la guardo en "dim"
            dim = Integer.parseInt(JOptionPane.showInputDialog("Digite dimension del tablero:"));
            
            // encapsulo la variable "dim"
            obj.setDimension(dim);
            // si la dimension que digito el usuario es menor que la dimension minima ó si la dimension es mayor que la dimension maxima
            if (dim < 3 || dim > 9)
            {
                // entonces se mostrara en pantalla que debe ingresar una dimension valida
                JOptionPane.showMessageDialog(null, "El tablero debe tener una dimension entre 3x3 y 9x9. \n");
            }
        }
        // repetira esto hasta que el usuario ingrese una dimension valida
        while(dim < 3 || dim > 9);

        // mostrare los creditos
        JOptionPane.showMessageDialog(null, creditos());

        // inicializare el tablero
        obj.iniciar();

        // repetire todas las intrucciones que dentro del ciclo while hasta que el usuario gane el juego
        while (true)
        {
            // compruebo si el usuario ha ganado el juego
            if (obj.gano())
            {
                // si gana el juego, mostrara el texto en pantalla y rompera el programa
                JOptionPane.showMessageDialog(null, " Has ganado! \n");
                break;
            }

            // creo la variable de tipo entero "mov" (movimiento)
            int mov;
            // pido un movimiento al usuario y guardo el movimeinto que ingreso el usuario en la variable "mov"
            
            mov = Integer.parseInt(JOptionPane.showInputDialog(dibujar(obj) + "\nDigita el numero del cuadro a donde quieres moverte:"));

            // se movera si es posible, pero sino entonces mostrara un mensaje de movimiento invalido
            if (!obj.mover(mov))
            {
                JOptionPane.showMessageDialog(null, "Este movimiento no se puede hacer.");
            }
        }
    }
}