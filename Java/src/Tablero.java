public class Tablero
{
    // declaro las variable constantes DIM_MIX y le asigno 3 y DIM_MAX y le asigno 9
    private final int DIM_MIN = 3;
    private final int DIM_MAX = 9;
    // declaro una matriz o un vector multidimensional y le asigno las dimension maxima que haya ingresado el usuario
    private int [][] tablero = new int[DIM_MAX][DIM_MAX];
    // declaro la variable d, sera la dimension que ingresara el usuario
    private int d;

   /**
    * Se inicializa el tablero del juego con los movimientos numerados desde 1 a d*d - 1
    * (es decir llena la matriz con valores, pero en realidad no los imprime)
    */
   public void iniciar()
   {
       // declaro la variable "total" de tipo entero y le asignare el numero total de espacios de la matriz
       int total = getDimension() * getDimension();

       // agrego los espacios o los cuadros en el tablero
       for (int i = 0; i < getDimension(); i++)
       {
           for (int j = 0; j < getDimension(); j++)
           {
               // decremento el valor de "total" en 1 y se lo asigno al arreglo "tablero" en la posicion actual de i,j
               setTablero(i, j, --total);
           }
       }

       // debo intercambiar 2 y 1 si el número de espacios en total del arreglo es par
       if ((getDimension() * getDimension()) % 2 == 0)
       {
           setTablero(d-1, d-3, 1);
           setTablero(d-1, d-2, 2);
       }
   }

   /**
    * Si el cuadro esta al lado del espacio vacio (_) entonces movera
    * el cuadro y devolvera true, sino devolvera false
    */
   public boolean mover(int mov)
   {
       // compuebo si el movimiento es valido
       if (mov > getDimension() * getDimension() - 1 || mov < 1)
       {
           return false;
       }

       // recorrere el tablero con el ciclo for desde las filas y columnas
       int fil = 0, col = 0;

       for (int i = 0; i < getDimension(); i++)
       {
           for (int j = 0; j < getDimension(); j++)
           {
               // si el cuadro que esta en posicion [i],[j] del "tablero" es igual al movimeinto que ingreso el usuario
               if (getTablero(i,j) == mov)
               {
                   // entonces le asignare a "fil" el valor de "i"
                   fil = i;
                   // y a "col" le asignare el valor de "j"
                   col = j;
               }
           }
       }

       // compruebo los espacios cercanos, si se logra encontrar uno se devolverea true de lo contrario devolvera false
       if (fil - 1 >= 0 && getTablero(fil-1, col) == 0)
       {
           setTablero(fil-1, col, getTablero(fil, col));
           setTablero(fil, col, 0);
           return true;
       }
       else if (fil + 1 < getDimension() && getTablero(fil+1, col) == 0)
       {
           setTablero(fil+1, col, getTablero(fil, col));
           setTablero(fil, col, 0);
           return true;
       }
       else if (col - 1 >= 0 && getTablero(fil, col-1) == 0)
       {
           setTablero(fil, col-1, getTablero(fil, col));
           setTablero(fil, col, 0);
           return true;
       }
       else if (col + 1 < d && getTablero(fil, col+1) == 0)
       {
           setTablero(fil, col+1, getTablero(fil, col));
           setTablero(fil, col, 0);
           return true;
       }
       return false;
   }

   /**
    * Devolvere true si se gana el juego,
    * Sino devolvere false.
    */
   public boolean gano()
   {
       // declaro una variable de tipo entero "contador" y la inicializo en 0
       int contador = 0;

       // revise cada cuadro para asegurarme de que esté en orden
       for (int i = 0; i < getDimension(); i++)
       {
           for (int j = 0; j < getDimension(); j++)
           {
               // compruebo si el último punto es correcto y si no es el valor correcto
               if (++contador != (d * d) && getTablero(i, j) != contador)
               {
                   // si eso es verdadero entonces devolvere false y el jugador aun no habra ganado
                   return false;
               }
           }
       }
       // devolvere true y el jugador ganara el juego
       return true;
   }

   public void setTablero(int fil, int col, int val)
   {
       tablero[fil][col] = val;
   }

   public int getTablero(int fil, int col)
   {
       return tablero[fil][col];
   }

   public void setDimension(int val)
   {
       d = val;
   }

   public int getDimension()
   {
       return d;
   }
}