
from random import randint, choice

ORDER = 10  # habrán entre 1 y ORDER vertices.
NEGATIVEWEIGHTS = False  # quiero pesos negativos o no
COST = 15  # el costo de los ejes será entre [-COST,COST] si se quieren negativos o [0, COST] si no
ISCONNECTED = False  # Controlar si el grafo es conectado o no
ISDIRECTED = False  # Controlar si es dirigido o no 

with open("Problema5.in", "w+") as f:
    n = randint(1, ORDER)  # Cantidad de vertices, 0-indexed
    connection = "C" if ISCONNECTED else "D"
    f.write(f"{n} {connection}\n")
    randomAdjacencyMatrix = [[None for i in range(n)] for j in range(n)]

    for i in range(len(randomAdjacencyMatrix)):
        for j in range(len(randomAdjacencyMatrix)):
            lowerEnd = 1 if not NEGATIVEWEIGHTS else -COST
            randomArray = [None]*3+[randint(lowerEnd, COST) for i in range(7)]
            edgeCost = choice(randomArray) if not ISCONNECTED else choice(randomArray[3:])
            randomAdjacencyMatrix[i][j] = edgeCost
            if not ISDIRECTED:
                randomAdjacencyMatrix[j][i] = randomAdjacencyMatrix[i][j]

    for i in range(len(randomAdjacencyMatrix)):
        for j in range(len(randomAdjacencyMatrix)):
            costNumber = randomAdjacencyMatrix[i][j]
            if costNumber is not None:
                f.write(f"{i} {j} {costNumber}\n")
    f.write("0")


def binarysearch(list,n):
    # asume que esta ordenada
    encontrado = None
    j = int(len(list)/2)
    while encontrado is not None:
        if list[j] == n:
            return True
        elif list[j] > n:
            j = int(j//2)
        else:
            j += int(j//2)

        if j == 0 or j == len(list):
            return False 


def factorial(n):
    if n == 0:
        return 1
    else:
        return n*factorial(n-1)


def fibonacci(n):
    if n < 2:
        return n
    else:
        return fibonacci(n-1)+fibonacci(n-2)