import random
boole = True
conj = set()


class Estilo:
    negrita = "\033[1m"
    subrayo = "\033[4m"
    final = "\033[0m"


def gennum():

    for i in range(manolo):
        numero_aleatorio = random.randint(1, 6)
    return numero_aleatorio


def invoc():

    dicc = {
        1: "\n\n   *\n", 2: "\n     * \n\n*", 3: "\n     *\n   *\n *", 4: "\n*     * \n\n*     *",
        5: "\n*     *\n   *\n*     *", 6: "\n*     *\n*     *\n*     *"
    }
    for c in range(manolo):
        rafa = gennum()
        c += 1
        print(f"El dado numero {c} ha generado aleatoriamente un: {dicc[rafa]}")
        conj.add(rafa)


print(f"\n\t\t\t\t\t\t{Estilo.negrita}{Estilo.subrayo}PROGRAMA GENERA DADOS{Estilo.final}")

while boole:

    manolo = int(input("\n¿Cuántos dados lanzamos? "))

    if manolo < 0:
        print("Por favor, ingresa un número positivo.")
    elif manolo > 0:
        print()
        invoc()
        print()

    elif manolo == 0:
        boole = False
        conj = list(conj)
        conj = ", ".join(map(str, conj))
        print(f"\nLos valores de los dados lanzados fueron: {conj}")
        print("\nFin del programa")