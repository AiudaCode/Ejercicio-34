#ifndef TABLERO_H
#define TABLERO_H
// defino dos constantes, 1 para la dimension minima que sera 3 y la otra para la dimension maxima que sera 9
#define DIM_MIN 3
#define DIM_MAX 9

class Tablero
{
    public:
        Tablero();
        virtual ~Tablero();
        void limpiar();
        void iniciar();
        bool mover(int mov);
        bool gano();
        void setTablero(int fil, int col, int val);
        int getTablero(int fil, int col);
        void setDimension(int val);
        int getDimension();
    protected:

    private:
        // declaro una matriz o un vector multidimensional y le asigno las dimension maxima que haya ingresado el usuario
        int tablero[DIM_MAX][DIM_MAX];
        // declaro la variable d, sera la dimension que ingresara el usuario
        int d;
};

#endif // TABLERO_H
