#include <iostream>
#include "Tablero.h"
/**
 * incluire la clase "unistd.h" para poder utilizar el metodo  usleep() para que el programa "duerma"
 * y realize una pequeña animacion por unos segundos que le asigne como parametro
 */
#include <unistd.h>

// defino dos constantes, 1 para la dimension minima que sera 3 y la otra para la dimension maxima que sera 9
#define DIM_MIN 3
#define DIM_MAX 9

using namespace std;

// funciones prototipo, las funciones prototipo solo le dicen al compilador que utilizare esta funcion o metodo
// pero mas tarde
void dibujar(Tablero *obj);
void creditos();

int main(void)
{
    Tablero *obj = new Tablero();
    int dim;
    // con el ciclo do..while me asegurare de que se ingrese una dimension adecuada
    do
    {
        // pido en pantalla que se digite la dimension del tablero
        cout << "Digite dimension del tablero" << endl;
        cout << ">> ";
        // guardo la dimension en la variable d
        cin >> dim;
        obj->setDimension(dim);
        // si la dimension que digito el usuario es menor que la dimension minima ó si la dimension es mayor que la dimension maxima
        if (dim < DIM_MIN || dim > DIM_MAX)
        {
            // entonces se mostrara en pantalla que debe ingresaar una dimension valida
            cout << "El tablero debe tener una dimension entre " << DIM_MIN << "x" << DIM_MIN << " y " << DIM_MAX << "x" << DIM_MAX << "." << endl;
        }
    }
    // repetira esto hasta que el usuario ingrese una dimension valida
    while(dim < DIM_MIN || dim > DIM_MAX);

    // limpiare la pantalla
    obj->limpiar();
    // mostrare los creditos
    creditos();

    // inicializare el tablero
    obj->iniciar();

    // repetire todas las intrucciones que dentro del ciclo while hasta que el usuario gane el juego
    while (true)
    {
        // limpio la pantalla
        obj->limpiar();
        // dibujo el estado actual del tablero
        dibujar(obj);

        // compruebo si el usuario ha ganado el juego
        if (obj->gano())
        {
            // si gana el juego, mostrara el texto en pantalla y rompera el programa
            cout << " Has ganado!" << endl;
            break;
        }

        // pido un movimiento al usuario
        cout << "Digita el numero del cuadro a donde quieres moverte" << endl;
        cout << ">> ";
        // creo la variable de tipo entero "mov" (movimiento)
        int mov;
        // y guardo el movimeinto que ingreso el usuario en la variable "mov"
        cin >> mov;

        // se movera si es posible, pero sino entonces mostrara un mensaje de movimiento invalido
        if (!obj->mover(mov))
        {
            cout << "\nEste movimiento no se puede hacer." << endl;
            // dormira por 500000 microsegundos
            usleep(500000);
        }

        usleep(500000);
    }
}

/**
 * Imprimo el tablero en su estado actual.
 */
void dibujar(Tablero *obj)
{
    // recorreré toda la matriz con el ciclo for
    for (int i = 0; i < obj->getDimension(); i++)
    {
        for (int j = 0; j < obj->getDimension(); j++)
        {
            // imprimo el tablero si esta en la posicion 0
            if (obj->getTablero(i, j) == 0)
            {
                // imprimire la barrita que indica la posicion en donde se encuentra el usuario actualmente
                cout << "\t _";
            }
            else
            {
                // imprimire los datos que hay en el arreglo identados ("\t")
                cout << "\t|" << obj->getTablero(i, j) << "|";
            }
        }
        // dare dos saltos de linea para que se vea mas estetico la tabla del juego
        cout << endl << endl;
    }
}

void creditos()
{
    //              0    1    2    3    4    5    6    7
    char sim[8] = {201, 205, 187, 186, 200, 188, 204, 185};
    cout << sim[0];
    for (int i = 0; i < 51; i++)
        cout  << sim[1];
    cout << sim[2] << endl;

    cout << sim[3] << "       BIENVENIDO AL JUEGO DESLIZANTE MENTAL       " << sim[3] << endl;
    cout << sim[6];
    for (int i = 0; i < 51; i++)
        cout  << sim[1];
    cout << sim[7] << endl;
    cout << sim[3] << "                                                   " << sim[3] << endl;
    cout << sim[3] << "         CREADO POR OMAR FLOREZ (Vlph7_7)          " << sim[3] << endl;
    cout << sim[3] << "                                                   " << sim[3] << endl;
    cout << sim[4];
    for (int i = 0; i < 51; i++)
        cout  << sim[1];
    cout << sim[5] << endl;
    usleep(900000);
}
