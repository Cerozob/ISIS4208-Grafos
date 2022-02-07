ISIS 4206 - Tarea 1 - Problema 5

Enunciado:
    5. Una ciudad se diseño de tal modo que todas sus calles fueran de una sola vía. Con el paso
    del tiempo la cantidad de habitantes de la ciudad creció y esto produjo grandes trancones en
    algunas de las vias debido a algunos desvíos innecesarios que tienen que tomar los habitantes
    de la ciudad para poder llegar a sus trabajos. Por lo tanto, el alcalde tomó la decisión de
    ampliar algunas vias para que puedan convertirse en doble via. Dado el mapa de la ciudad y el
    costo de convertir cada via actual en doble via, determinar qué vias se deben convertir, de
    modo que se pueda transitar de cualquier punto a cualquier punto de la ciudad por dobles vias
    y que el costo de la conversión sea el mínimo posible.

Cómo usar el programa?


Generar un caso de prueba para resolver:

    El archivo Problema5Generator.py es capaz de generar casos de prueba con la sintaxis que lee 
    el programa (la sintaxis se explica al final de este README). Al inicio del generador de casos
    hay variables que permiten configurar la forma que tendrá el grafo generado por el archivo:

    ORDER -> que cantidad de vértices se pueden generar como máximo, el valor por defecto es 10
    NEGATIVEWEIGHTS -> True si se desea que los ejes generados puedan tener pesos negativos, el valor por defecto es False
    COST -> El rango de costos de los ejes, si NEGATIVEWEIGHTS es False, el rango será [0,COST], de lo contrario el rango será [-COST,COST]
            ** COST controla solamente el rango de los pesos, valores como 0, 1 y -1 siguen siendo valores de peso y no indican si un eje existe o no 
    ISCONNECTED -> True si se desea que el grafo sea siempre conectado, False de lo contrario, el algoritmo de solución de Java cambia de implementación de acuerdo a este valor (automáticamente)
    ISDIRECTED -> True si se desea que el grafo sea dirigido, False de lo contrario. El algoritmo de solución calcula el MST, por lo que este valor no deberia ser True
                ** calcular un MST en un grafo dirigido no tiene mucho sentido (tampoco en el enunciado del problema) y no se tiene en cuenta en la solución

Solucionar el problema:

Interpretación:

    El problema se interpretó con un Grafo no dirigido, en el que los vértices son las esquinas
    o intersecciones de vías en la ciudad, y los ejes son las vías, de modo que un eje E-F indica
    que hay una vía entre las esquinas E y F, el costo de un eje indica el costo de ampliar la vía
    a una doble vía.

Programación:

    El programa implementado que resuelve el problema es el del archivo Problema5.java, este recibe
    los datos de entrada por la entrada estándar y retorna la respuesta por la salida estándar siguiendo 
    la sintaxis que se explica a continuación.


Sintaxis de entrada:

    La primera línea de la entrada es de la siguiente forma

    n letra

    donde n es el número de vértices y G es una letra que indica si el grafo es conectado o no (por comodidad, se podría saber con los 'ejes' por medio de BFS)
    'letra' puede ser D o C, con D = desconectado y C = conectado.

    Las siguientes líneas son 3 numeros enteros separados por un espacio entre ellos que tienen la siguiente forma:

    a b c

    Donde la línea 'a b c' indica que hay un eje entre 'a' y 'b' con un costo de 'c'.

    Al final del archivo hay un '0', que indica el final de los datos de entrada.

Sintaxis de salida:

    La sintaxis es muy similar a la de entrada, la salida se compone de líneas de la forma 

    a b c

    Donde la línea 'a b c' indica que hay un eje entre 'a' y 'b' con un costo de 'c'. Y hace parte del MST de respuesta.

A continuación un caso de entrada de ejemplo:

6 D
0 1 11
0 2 7
0 3 12
1 0 11
1 1 8
1 3 1
1 4 1
1 5 4
2 0 7
2 3 11
2 4 12
3 0 12
3 1 1
3 2 11
3 3 14
4 1 1
4 2 12
4 4 4
5 1 4
0

Y la salida correspondiente:

1 3 1
1 4 1
1 5 4
0 2 7
0 1 11
